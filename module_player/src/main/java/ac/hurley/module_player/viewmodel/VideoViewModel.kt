package ac.hurley.module_player.viewmodel

import ac.hurley.module_common.base.viewmodel.BaseViewModel
import ac.hurley.module_player.adapter.RelateVideoAdapter
import ac.hurley.module_player.service.VideoService
import ac.hurley.module_provider.model.Item
import androidx.lifecycle.LiveData

/**
 * <pre>
 *      @author hurley
 *      date    : 4/3/21 3:24 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 视频 ViewModel 类
 * </pre>
 */
class VideoViewModel : BaseViewModel() {

    private val VIDEO_SMALL_CARD = "videoSmallCard"

    fun getRelatedVideoList(id: Int): LiveData<List<Item>> = liveDataEx {
        val issue = VideoService.getRelatedVideoList(id)
        issue.itemList.forEach {
            // videoSmallCard
            if (it.type == VIDEO_SMALL_CARD) {
                it.itemType = RelateVideoAdapter.TYPE_VIDEO
                // textCard
            } else {
                it.itemType = RelateVideoAdapter.TYPE_TITLE
            }
        }
        issue.itemList
    }
}