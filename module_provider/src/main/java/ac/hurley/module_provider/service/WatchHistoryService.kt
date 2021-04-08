package ac.hurley.module_provider.service

import ac.hurley.module_provider.model.Data
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * <pre>
 *      @author hurley
 *      date    : 4/5/21 9:47 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 视频观看历史的 Service
 * </pre>
 */
interface WatchHistoryService : IProvider {

    /**
     * 获得视频历史观看记录
     */
    fun getVideoWatchHistoryList(): MutableList<Data>

    /**
     * 删除该视频观看历史
     */
    fun removeVideoWatchHistory(data: Data)
}