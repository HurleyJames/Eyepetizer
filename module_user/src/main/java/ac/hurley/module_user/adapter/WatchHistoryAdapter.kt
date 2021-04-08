package ac.hurley.module_user.adapter

import ac.hurley.module_provider.constant.Constant
import ac.hurley.module_provider.databinding.RelateVideoItemBinding
import ac.hurley.module_provider.model.Data
import ac.hurley.module_provider.router.jumpToVideoPlayer
import ac.hurley.module_user.R
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * <pre>
 *      @author hurley
 *      date    : 4/8/21 12:50 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 观看历史记录适配器类
 * </pre>
 */
class WatchHistoryAdapter(
    private val mActivity: Activity,
    val mDataList: MutableList<Data> = mutableListOf()
) : RecyclerView.Adapter<WatchHistoryAdapter.WatchHistoryViewHolder>() {

    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mActivity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchHistoryViewHolder {
        return WatchHistoryViewHolder(
            RelateVideoItemBinding.inflate(
                mLayoutInflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: WatchHistoryViewHolder, position: Int) {
        holder.setData(mDataList[position])
    }

    /**
     * 新创建数据
     */
    fun newData(dataList: List<Data>) {
        mDataList.clear()
        mDataList.addAll(dataList)
    }

    /**
     * 移除某个位置上的元素
     */
    fun remove(position: Int) {
        mDataList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class WatchHistoryViewHolder(private val binding: RelateVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(data: Data) {
            binding.tvTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.color_black_87))

            binding.tvCategory.setTextColor(
                ContextCompat.getColor(
                    mActivity,
                    R.color.color_black_87
                )
            )

            ViewCompat.setTransitionName(binding.ivCover, Constant.SHARED_IMAGE_NAME)

            binding.model = data
            binding.root.setOnClickListener {
                jumpToVideoPlayer(mActivity, binding.ivCover, data)
            }
        }
    }
}