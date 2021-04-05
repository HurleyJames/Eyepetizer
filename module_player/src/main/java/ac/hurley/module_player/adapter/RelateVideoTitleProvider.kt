package ac.hurley.module_player.adapter

import ac.hurley.module_player.R
import ac.hurley.module_player.databinding.RelateVideoTitleItemBinding
import ac.hurley.module_provider.model.Item
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * <pre>
 *      @author hurley
 *      date    : 4/4/21 10:32 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 相关视频的标题适配器
 * </pre>
 */
class RelateVideoTitleProvider : BaseItemProvider<Item>() {

    override val itemViewType: Int
        get() = RelateVideoAdapter.TYPE_TITLE
    override val layoutId: Int
        get() = R.layout.relate_video_title_item

    override fun convert(helper: BaseViewHolder, item: Item) {
        val baseDataBindingHolder =
            BaseDataBindingHolder<RelateVideoTitleItemBinding>(helper.itemView)
        baseDataBindingHolder.dataBinding?.model = item
    }
}