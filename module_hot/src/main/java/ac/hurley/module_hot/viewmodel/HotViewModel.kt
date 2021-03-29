package ac.hurley.module_hot.viewmodel

import ac.hurley.module_common.base.viewmodel.BaseViewModel
import ac.hurley.module_hot.model.TabInfo
import ac.hurley.module_hot.service.HotService
import ac.hurley.module_provider.model.Item
import androidx.lifecycle.LiveData
import retrofit2.http.Url

/**
 * <pre>
 *      @author hurley
 *      date    : 3/25/21 2:35 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class HotViewModel : BaseViewModel() {

    fun getHotTabs(): LiveData<TabInfo> = liveDataEx {
        HotService.getHotTabs()
    }

    fun getRankList(apiUrl: String): LiveData<MutableList<Item>> = liveDataEx {
        HotService.getRankList(apiUrl).itemList
    }
}