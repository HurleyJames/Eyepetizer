package ac.hurley.module_provider.service.wrap

import ac.hurley.module_provider.model.Data
import ac.hurley.module_provider.router.RouterPath
import ac.hurley.module_provider.service.WatchHistoryService
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter

/**
 * <pre>
 *      @author hurley
 *      date    : 4/5/21 10:23 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 跨组件调用时外界无需知道 Service 初始化的细节，进行包装
 * </pre>
 */

object WatchHistoryWrap {

    /**
     * 通过 ARouter，跳转到 Service 的具体实现类
     */
    @Autowired(name = RouterPath.Video.PATH_VIDEO_WATCH)
    lateinit var mWatchHistoryService: WatchHistoryService

    init {
        ARouter.getInstance().inject(this)
    }

    /**
     * 获取历史观看记录列表
     */
    fun getVideoWatchHistoryList(): MutableList<Data> =
        mWatchHistoryService.getVideoWatchHistoryList()

    /**
     * 移除该历史观看记录
     */
    fun removeVideoWatchHistory(data: Data) {
        mWatchHistoryService.removeVideoWatchHistory(data)
    }
}