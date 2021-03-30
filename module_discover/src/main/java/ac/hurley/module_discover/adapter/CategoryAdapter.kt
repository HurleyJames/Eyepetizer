package ac.hurley.module_discover.adapter

import ac.hurley.module_discover.R
import ac.hurley.module_discover.databinding.DiscoverCategoryItemBinding
import ac.hurley.module_discover.model.CategoryModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 1:38 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 发现页面的分类版块的适配器类
 * </pre>
 */
class CategoryAdapter :
    BaseQuickAdapter<CategoryModel, BaseViewHolder>(R.layout.discover_category_item) {


    override fun convert(holder: BaseViewHolder, item: CategoryModel) {
        val bindingHolder = BaseDataBindingHolder<DiscoverCategoryItemBinding>(holder.itemView)
        bindingHolder.dataBinding?.categoryModel = item
    }
}