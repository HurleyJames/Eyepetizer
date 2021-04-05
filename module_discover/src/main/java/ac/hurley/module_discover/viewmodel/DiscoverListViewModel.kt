package ac.hurley.module_discover.viewmodel

import ac.hurley.module_common.base.viewmodel.BaseViewModel
import androidx.lifecycle.LiveData

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 2:05 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 发现列表的 ViewModel 类
 * </pre>
 */
abstract class DiscoverListViewModel : BaseViewModel() {

    var mNextPageUrl: String? = null

    fun <T> getListData(firstPage: Boolean): LiveData<List<T>> = liveDataEx {
        if (mNextPageUrl == null && !firstPage) {
            mutableListOf<T>()
        } else {
            if (firstPage) {
                getRefreshList<T>()
            } else {
                getLoadMoreList<T>()
            }
        }
    }

    abstract suspend fun <T> getRefreshList(): List<T>

    abstract suspend fun <T> getLoadMoreList(): List<T>
}