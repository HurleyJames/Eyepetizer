package ac.hurley.module_discover.model

import ac.hurley.module_discover.service.DiscoverService
import ac.hurley.module_discover.viewmodel.DiscoverListViewModel

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 5:05 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class TopicListModel : DiscoverListViewModel() {


    override suspend fun <T> getRefreshList(): List<T> {
        val topicModel = DiscoverService.getTopicList()
        mNextPageUrl = topicModel.nextPageUrl
        return topicModel.itemList as List<T>
    }

    override suspend fun <T> getLoadMoreList(): List<T> {
        val topicModel = DiscoverService.getTopicList(mNextPageUrl!!)
        mNextPageUrl = topicModel.nextPageUrl
        return topicModel.itemList as List<T>
    }
}