package ac.hurley.module_player.adapter

import ac.hurley.module_player.R
import ac.hurley.module_provider.databinding.RelateVideoItemBinding
import ac.hurley.module_provider.model.Item
import ac.hurley.module_provider.router.jumpToVideoPlayer
import android.app.Activity
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * <pre>
 *      @author hurley
 *      date    : 4/4/21 10:35 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 相关视频的具体视频显示适配器类
 * </pre>
 */
class RelateVideoItemProvider(private val mActivity: Activity) : BaseItemProvider<Item>() {

    override val itemViewType: Int
        get() = RelateVideoAdapter.TYPE_VIDEO
    override val layoutId: Int
        get() = R.layout.relate_video_item

    override fun convert(helper: BaseViewHolder, item: Item) {
        val baseDataBindingHolder = BaseDataBindingHolder<RelateVideoItemBinding>(helper.itemView)
        baseDataBindingHolder.dataBinding?.model = item.data
        baseDataBindingHolder.dataBinding?.root?.setOnClickListener {
            jumpToVideoPlayer(mActivity, null, item.data, true)
        }
    }
}