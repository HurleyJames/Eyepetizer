package ac.hurley.module_user.activity

import ac.hurley.module_common.util.immersionStatusBar
import ac.hurley.module_common.util.setToolBar
import ac.hurley.module_provider.constant.Constant
import ac.hurley.module_provider.event.WatchVideoEvent
import ac.hurley.module_user.R
import ac.hurley.module_user.adapter.WatchHistoryAdapter
import ac.hurley.module_user.helper.DiffUtilCallback
import ac.hurley.module_provider.service.wrap.WatchHistoryWrap
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.fmt.livedatabus.LiveDataBus
import com.yanzhenjie.recyclerview.*
import kotlinx.android.synthetic.main.user_watch_history_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * <pre>
 *      @author hurley
 *      date    : 4/5/21 5:24 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 观看历史记录界面
 * </pre>
 */
class WatchHistoryActivity : AppCompatActivity() {

    /**
     * 启动该 Activity
     */
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, WatchHistoryActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * 显示历史记录列表的适配器
     */
    private val mAdapter by lazy {
        WatchHistoryAdapter(this, WatchHistoryWrap.getVideoWatchHistoryList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 沉浸式状态栏
        immersionStatusBar(true, android.R.color.white, true, 0.2f)
        setContentView(R.layout.user_watch_history_activity)
        // 设置 ToolBar
        setToolBar(tl_watch_history)
        initSwipeMenu()
        initEvent()
    }

    /**
     * 初始化滑动按钮
     */
    private fun initSwipeMenu() {
        // 创建右滑的操作
        val mSwipeMenuCreator = SwipeMenuCreator { _, rightMenu, _ ->
            val deleteItem: SwipeMenuItem =
                SwipeMenuItem(this).setBackground(R.drawable.user_selector_red)
                    .setImage(R.drawable.ic_user_delete)
                    .setWidth(ConvertUtils.dp2px(70f))
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
            // 将该 Item 添加到 Menu 中
            rightMenu.addMenuItem(deleteItem)
        }

        // 监听 Menu 点击操作
        val mItemMenuClickListener = OnItemMenuClickListener { menuBridge, position ->
            menuBridge.closeMenu()

            // 如果方向是向右
            if (menuBridge.direction == SwipeRecyclerView.RIGHT_DIRECTION) {
                // 删除该位置的历史记录
                WatchHistoryWrap.removeVideoWatchHistory(mAdapter.mDataList[position])
                // 适配器列表中移除该选项
                mAdapter.remove(position)
            }
        }

        // 初始化 RecyclerView
        rv_watch_history.apply {
            setSwipeMenuCreator(mSwipeMenuCreator)
            setOnItemMenuClickListener(mItemMenuClickListener)
            isSwipeItemMenuEnabled = true
            layoutManager = LinearLayoutManager(this@WatchHistoryActivity)
            adapter = mAdapter
        }
    }

    private fun initEvent() {
        LiveDataBus.with<WatchVideoEvent>(Constant.WATCH_VIDEO_EVENT).observe(this) {
            updateWatchRecord()
        }
    }

    /**
     * 更新历史观看记录
     */
    private fun updateWatchRecord() {
        // 获取到观看的历史记录列表
        val newDataList = WatchHistoryWrap.getVideoWatchHistoryList()
        lifecycleScope.launch(Dispatchers.IO) {
            val diffResult = DiffUtil.calculateDiff(
                DiffUtilCallback(
                    mAdapter.mDataList,
                    newDataList
                )
            )

            withContext(Dispatchers.Main) {
                // 更新新的数据源
                mAdapter.newData(newDataList)
                // 通过差分数据的对比通知 Adapter 进行相应的数据源更新，防止不必要的全局刷新
                diffResult.dispatchUpdatesTo(mAdapter)
            }
        }
    }
}