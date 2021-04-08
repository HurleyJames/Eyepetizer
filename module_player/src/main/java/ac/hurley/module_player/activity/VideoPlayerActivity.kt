package ac.hurley.module_player.activity

import ac.hurley.module_common.base.activity.BaseBindActivity
import ac.hurley.module_player.R
import ac.hurley.module_player.adapter.RelateVideoAdapter
import ac.hurley.module_player.databinding.VideoPlayerActivityBinding
import ac.hurley.module_player.observer.JzvdObserver
import ac.hurley.module_player.viewmodel.VideoViewModel
import ac.hurley.module_player.adapter.TransitionListenerAdapter
import ac.hurley.module_player.util.WatchHistoryManager
import ac.hurley.module_provider.constant.Constant
import ac.hurley.module_provider.event.VideoAutoPlayEvent
import ac.hurley.module_provider.event.WatchVideoEvent
import ac.hurley.module_provider.model.Data
import ac.hurley.module_provider.player.*
import ac.hurley.module_provider.router.RouterPath
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.transition.Transition
import android.view.ViewGroup
import android.view.ViewParent
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jzvd.Jzvd
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.fmt.livedatabus.LiveDataBus
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.video_player_activity.*

/**
 * <pre>
 *      @author hurley
 *      date    : 4/3/21 4:29 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 播放视频界面
 * </pre>
 */
@Route(path = RouterPath.Video.PATH_VIDEO_HOME)
class VideoPlayerActivity : BaseBindActivity<VideoViewModel, VideoPlayerActivityBinding>() {

    private val mDuration: Long = 250

    private var mTransition: Transition? = null

    private lateinit var mCurrentAttr: ViewAttr

    private lateinit var videoModel: Data

    @JvmField
    @Autowired(name = Constant.VIDEO_MODE_KEY)
    var videoModelJson: String = ""

    @JvmField
    @Autowired(name = Constant.VIDEO_IS_FROM_RELATE_KEY)
    var fromRelate: Boolean = false

    @Autowired(name = Constant.VIDEO_IS_FROM_PLAYLIST_KEY)
    lateinit var viewAttr: ViewAttr

    private val mAdapter: RelateVideoAdapter by lazy { RelateVideoAdapter(this) }

    override val getLayoutRes: Int
        get() = R.layout.video_player_activity

    override fun initView() {
        ImmersionBar.with(this).init()

        srl_video_player.setOnRefreshListener {
            getRelateVideoList()
        }
        rv_video_list.layoutManager = LinearLayoutManager(this)
        rv_video_list.isNestedScrollingEnabled = false
        rv_video_list.adapter = mAdapter
    }

    override fun initData() {
        ARouter.getInstance().inject(this)
        videoModel = fromJson(videoModelJson)
        mBind.videoModel = videoModel
        // 添加观看记录
        WatchHistoryManager.addWatchHistoryRecord(videoModel)
        LiveDataBus.with<WatchVideoEvent>(Constant.WATCH_VIDEO_EVENT).setData(WatchVideoEvent())

        // 从自动播放页面进入时
        if (isInitViewAttr()) {
            addVideoViewFromList()
        } else {
            addVideoView()
            lifecycle.addObserver(JzvdObserver())
            // 从相关视频点击进入
            if (fromRelate) {
                getRelateVideoList()
            } else {
                initTransition()
            }
        }
    }

    override fun showLoading() {
        srl_video_player.isRefreshing = true
    }

    override fun hideLoading() {
        srl_video_player.isRefreshing = false
    }

    private fun addVideoView() {
        fl_container.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                fl_container.viewTreeObserver.removeOnPreDrawListener(this)
                val jzvdStd = JzvdStdShowTitleAfterFullscreen(this@VideoPlayerActivity).apply {
                    setUp(videoModel.playUrl, videoModel.title)
                    startVideo()
                }
                fl_container.addView(
                    jzvdStd,
                    FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                )
                return true
            }

        })
    }

    private fun addVideoViewFromList() {
        fl_container.viewTreeObserver
            .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    fl_container.viewTreeObserver.removeOnPreDrawListener(this)
                    val parent: ViewParent = JzvdStdRv.CURRENT_JZVD.parent
                    (parent as ViewGroup).removeView(JzvdStdRv.CURRENT_JZVD)
                    fl_container.addView(
                        JzvdStdRv.CURRENT_JZVD, FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    )
                    mCurrentAttr = ViewAttr()
                    val location = IntArray(2)
                    fl_container.getLocationInWindow(location)
                    mCurrentAttr.x = location[0]
                    mCurrentAttr.y = location[1]
                    mCurrentAttr.width = fl_container.measuredWidth
                    mCurrentAttr.height = fl_container.measuredHeight
                    ViewMoveHelper(fl_container, viewAttr, mCurrentAttr, mDuration).startAnim()
                        .addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                getRelateVideoList()
                            }
                        })
                    return true
                }
            })
    }

    /**
     * 获取相关视频列表
     */
    private fun getRelateVideoList() {
        srl_video_player.isVisible = true
        mViewModel.getRelatedVideoList(videoModel.id).observe(this) {
            mAdapter.addData(it)
        }
    }

    /**
     * 返回方法
     */
    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        if (isInitViewAttr()) {
            backAnimation()
        } else {
            super.onBackPressed()
        }
    }

    private fun initTransition() {
        // 因为进入视频详情页面后还需请求数据，所以在过渡动画完成后在请求数据
        // 延迟动画执行
        postponeEnterTransition()
        // 设置共用元素对应的 View
        ViewCompat.setTransitionName(fl_container, Constant.SHARED_IMAGE_NAME)
        // 获取共享元素进入转场对象
        mTransition = window.sharedElementEnterTransition
        // 设置共享元素动画执行完成的回调事件
        mTransition?.addListener(object : TransitionListenerAdapter() {
            override fun onTransitionEnd(transition: Transition?) {
                getRelateVideoList()
                // 移除共享元素动画监听事件
                mTransition?.removeListener(this)
            }
        })
        // 开始动画执行
        startPostponedEnterTransition()
    }

    /**
     * 返回的动画
     */
    private fun backAnimation() {
        ViewMoveHelper(fl_container, mCurrentAttr, viewAttr, mDuration).startAnim()
        iv_video_background.isVisible = false
        srl_video_player.isVisible = false
        fl_container.postDelayed({
            LiveDataBus.with<VideoAutoPlayEvent>(Constant.VIDEO_AUTO_PLAY_EVENT)
                .setData(VideoAutoPlayEvent())
            finish()
            overridePendingTransition(0, 0)
        }, mDuration)
    }

    /**
     * 是否是从自动播放页面进入
     */
    private fun isInitViewAttr() = ::viewAttr.isInitialized

    private inline fun <reified T> fromJson(json: String): T = Gson().fromJson(json, T::class.java)
}