package ac.hurley.module_provider.router

import ac.hurley.module_provider.constant.Constant
import ac.hurley.module_provider.model.Data
import ac.hurley.module_provider.player.ViewAttr
import android.app.Activity
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.GsonUtils

/**
 * <pre>
 *      @author hurley
 *      date    : 3/28/21 5:10 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 页面跳转到播放器类
 * </pre>
 */

/**
 * 是否从相关页面跳转到播放器页面
 */
fun jumpToVideoPlayer(
    activity: Activity,
    view: View?,
    data: Data,
    fromRelate: Boolean = false
) {
    ARouter.getInstance().build(RouterPath.Video.PATH_VIDEO_HOME)
        .also { postcard ->
            view?.let { shareView ->
                postcard.withOptionsCompat(
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, shareView, Constant.SHARED_IMAGE_NAME
                    )
                )
            }
        }
        .withString(Constant.VIDEO_MODE_KEY, GsonUtils.toJson(data))
        .withBoolean(Constant.VIDEO_IS_FROM_RELATE_KEY, fromRelate)
        .navigation(activity)
}

/**
 * 直接跳转到播放器页面
 */
fun jumpToVideoPlayer(
    activity: Activity,
    data: Data,
    viewAttr: ViewAttr
) {
    ARouter.getInstance().build(RouterPath.Video.PATH_VIDEO_HOME)
        .withTransition(0, 0)
        .withString(Constant.VIDEO_MODE_KEY, GsonUtils.toJson(data))
        .withBoolean(Constant.VIDEO_IS_FROM_RELATE_KEY, false)
        .withParcelable(Constant.VIDEO_IS_FROM_PLAYLIST_KEY, viewAttr)
        .navigation(activity)
}