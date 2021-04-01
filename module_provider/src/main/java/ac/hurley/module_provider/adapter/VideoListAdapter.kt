package ac.hurley.module_provider.adapter

import ac.hurley.module_common.util.ShareUtils
import ac.hurley.module_provider.R
import ac.hurley.module_provider.databinding.VideoListItemBinding
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
 *      desc    : 视频列表适配器类
 * </pre>
 */
class VideoListAdapter(private val mActivity: Activity) :
    BaseQuickAdapter<Item, BaseViewHolder>(R.layout.video_list_item), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: Item) {
        val bindingHolder = BaseDataBindingHolder<VideoListItemBinding>(holder.itemView)
        bindingHolder.dataBinding?.model = item
        bindingHolder.dataBinding?.ivCover?.setOnClickListener { ivCover ->
            jumpToVideoPlayer(
                mActivity,
                ivCover,
                item.data
            )
        }

        /**
         * 分享该视频
         */
        bindingHolder.dataBinding?.tvShare?.setOnClickListener {
            ShareUtils.shareText(mActivity, item.data.playUrl)
        }
    }
}