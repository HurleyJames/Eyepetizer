package ac.hurley.module_daily.adapter

import ac.hurley.module_daily.databinding.DailyItemBannerImageBinding
import ac.hurley.module_provider.model.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.adapter.BannerAdapter

/**
 * <pre>
 *      @author hurley
 *      date    : 3/28/21 8:54 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Banner 图片适配器类
 * </pre>
 */
class BannerImageAdapter(context: Context, datas: List<Item>) :
    BannerAdapter<Item, BannerImageAdapter.BannerViewHolder>(datas) {

    private val mInflater = LayoutInflater.from(context)

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val binding =
            DailyItemBannerImageBinding.inflate(mInflater, parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindView(holder: BannerViewHolder, data: Item, position: Int, size: Int) {
        holder.binding.model = data
    }

    class BannerViewHolder(val binding: DailyItemBannerImageBinding) :
        RecyclerView.ViewHolder(binding.root)
}