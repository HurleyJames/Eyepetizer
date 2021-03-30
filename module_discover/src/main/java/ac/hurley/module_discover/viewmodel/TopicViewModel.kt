package ac.hurley.module_discover.viewmodel

import ac.hurley.module_discover.service.DiscoverService

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 8:05 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class TopicViewModel : DiscoverListViewModel() {

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