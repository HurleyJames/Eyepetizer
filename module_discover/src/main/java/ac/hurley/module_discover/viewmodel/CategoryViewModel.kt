package ac.hurley.module_discover.viewmodel

import ac.hurley.module_common.base.viewmodel.BaseViewModel
import ac.hurley.module_common.global.ConfigKeys
import ac.hurley.module_common.global.Configurator
import ac.hurley.module_discover.model.CategoryModel
import ac.hurley.module_discover.service.DiscoverService
import ac.hurley.module_provider.model.Item
import android.service.autofill.Validators.and
import androidx.lifecycle.LiveData

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 1:20 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 分类的 ViewModel 类
 * </pre>
 */
class CategoryViewModel : BaseViewModel() {

    private val URL_END = "&udid=d2807c895f0348a180148c9dfa6f2feeac0781b5&deviceModel=Android"
    private var mNextPageUrl: String? = null

    /**
     * 获取分类列表
     */
    fun getCategoryList(): LiveData<List<CategoryModel>> = liveDataEx {
        DiscoverService.getCategoryList()
    }

    /**
     * 获取某个分类下具体的数据
     */
    fun getCategoryDetailList(id: Int): LiveData<List<Item>> = liveDataEx {
        if ((mNextPageUrl == null) and (id == -1)) {
            mutableListOf()
        } else {
            val url: String = if (id != -1) {
                "${Configurator.instance.getConfiguration<String>(ConfigKeys.WEB_API_HOST)}v4/categories/videoList?id=${id}${URL_END}"
            } else {
                "${mNextPageUrl}${URL_END}"
            }
            val issue = DiscoverService.getCategoryDetailList(url)
            mNextPageUrl = issue.nextPageUrl
            issue.itemList
        }
    }
}