package ac.hurley.module_discover.viewmodel

import ac.hurley.module_common.base.viewmodel.BaseViewModel
import ac.hurley.module_discover.model.CategoryModel
import ac.hurley.module_discover.service.DiscoverService
import androidx.lifecycle.LiveData

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 1:20 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class CategoryViewModel : BaseViewModel() {

    /**
     * 获取分类列表
     */
    fun getCategoryList(): LiveData<List<CategoryModel>> = liveDataEx {
        DiscoverService.getCategoryList()
    }
}