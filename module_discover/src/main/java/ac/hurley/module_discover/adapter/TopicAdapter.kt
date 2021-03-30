package ac.hurley.module_discover.adapter

import ac.hurley.module_discover.R
import ac.hurley.module_discover.databinding.DiscoverTopicItemBinding
import ac.hurley.module_discover.model.TopicItemModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 5:10 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 发现页面的主题版块的适配器类
 * </pre>
 */
class TopicAdapter : BaseQuickAdapter<TopicItemModel, BaseViewHolder>(R.layout.discover_topic_item),
    LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: TopicItemModel) {
        val bindingHolder = BaseDataBindingHolder<DiscoverTopicItemBinding>(holder.itemView)
        bindingHolder.dataBinding?.model = item
    }
}