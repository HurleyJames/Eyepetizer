package ac.hurley.module_hot.adapter

import ac.hurley.module_common.util.ShareUtils
import ac.hurley.module_hot.R
import ac.hurley.module_hot.databinding.HotItemRankListBinding
import ac.hurley.module_provider.model.Item
import ac.hurley.module_provider.router.jumpToVideoPlayer
import android.app.Activity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * <pre>
 *      @author hurley
 *      date    : 3/29/21 10:38 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 热门排行榜适配器类
 * </pre>
 */
class HotRankListAdapter(private val mActivity: Activity) :
    BaseQuickAdapter<Item, BaseViewHolder>(R.layout.hot_item_rank_list), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: Item) {
        val bindingHolder = BaseDataBindingHolder<HotItemRankListBinding>(holder.itemView)
        bindingHolder.dataBinding?.model = item
        bindingHolder.dataBinding?.ivCover?.setOnClickListener { ivCover ->
            jumpToVideoPlayer(
                mActivity,
                ivCover,
                item.data
            )
        }

        bindingHolder.dataBinding?.tvShare?.setOnClickListener {
            ShareUtils.shareText(mActivity, item.data.playUrl)
        }
    }
}