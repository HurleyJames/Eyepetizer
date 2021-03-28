package ac.hurley.module_daily.adapter

import ac.hurley.module_common.util.ShareUtils
import ac.hurley.module_daily.R
import ac.hurley.module_daily.databinding.DailyItemImageTextBinding
import ac.hurley.module_daily.model.TypeMultiModel
import android.app.Activity
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * <pre>
 *      @author hurley
 *      date    : 3/28/21 8:54 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class ImageTextItemProvider(private val mActivity: Activity) : BaseItemProvider<TypeMultiModel>() {

    override val itemViewType: Int
        get() = TypeMultiModel.Type.TYPE_IMAGE

    override val layoutId: Int
        get() = R.layout.daily_item_image_text

    override fun convert(helper: BaseViewHolder, model: TypeMultiModel) {
        val bindingHolder = BaseDataBindingHolder<DailyItemImageTextBinding>(helper.itemView)
        bindingHolder.dataBinding?.model = model
        bindingHolder.dataBinding?.ivCover?.setOnClickListener {
            // TODO
        }
        bindingHolder.dataBinding?.tvShare?.setOnClickListener {
            ShareUtils.shareText(mActivity, model.item!!.data.playUrl)
        }
    }

}