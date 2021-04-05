package ac.hurley.module_daily.adapter

import ac.hurley.module_daily.R
import ac.hurley.module_daily.databinding.DailyItemBannerBinding
import ac.hurley.module_daily.model.TypeMultiModel
import ac.hurley.module_provider.router.jumpToVideoPlayer
import android.app.Activity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator

/**
 * <pre>
 *      @author hurley
 *      date    : 3/28/21 5:00 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 轮播图的 Provider 类
 * </pre>
 */
class BannerItemProvider(private val owner: LifecycleOwner, private val activity: Activity) :
    BaseItemProvider<TypeMultiModel>() {


    override val itemViewType: Int
        get() = TypeMultiModel.Type.TYPE_BANNER
    override val layoutId: Int
        get() = R.layout.daily_item_banner

    override fun convert(helper: BaseViewHolder, item: TypeMultiModel) {
        val bindingHolder = BaseDataBindingHolder<DailyItemBannerBinding>(helper.itemView)
        bindingHolder.dataBinding?.model = item
        bindingHolder.dataBinding?.owner = owner
        bindingHolder.dataBinding?.activity = activity
    }

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["data", "owner", "activity"])
        fun setBannerData(
            banner: Banner<*, *>,
            data: TypeMultiModel,
            owner: LifecycleOwner,
            activity: Activity
        ) {
            banner.apply {
                adapter = BannerImageAdapter(banner.context, data.items)
                addBannerLifecycleObserver(owner)
                // 圆形指示器
                indicator = CircleIndicator(banner.context)
                setOnBannerListener { _, position ->
                    // 跳转到视频播放界面
                    jumpToVideoPlayer(activity, null, data.items[position].data, true)
                }
            }
        }
    }
}