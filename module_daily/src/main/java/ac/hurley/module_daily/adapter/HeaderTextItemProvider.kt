package ac.hurley.module_daily.adapter

import ac.hurley.module_daily.R
import ac.hurley.module_daily.databinding.DailyItemHeaderTextBinding
import ac.hurley.module_daily.model.TypeMultiModel
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * <pre>
 *      @author hurley
 *      date    : 3/28/21 4:53 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 头部文字 Provider 类，用来表示日期
 * </pre>
 */
class HeaderTextItemProvider : BaseItemProvider<TypeMultiModel>() {

    override fun convert(helper: BaseViewHolder, item: TypeMultiModel) {
        val baseDataBindingHolder =
            BaseDataBindingHolder<DailyItemHeaderTextBinding>(helper.itemView)
        baseDataBindingHolder.dataBinding?.model = item
    }

    override val layoutId: Int
        get() = R.layout.daily_item_header_text
    override val itemViewType: Int
        get() = TypeMultiModel.Type.TYPE_TITLE


}