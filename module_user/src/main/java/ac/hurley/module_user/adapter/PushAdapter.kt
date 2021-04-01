package ac.hurley.module_user.adapter

import ac.hurley.module_user.R
import ac.hurley.module_user.databinding.NotificationPushItemBinding
import ac.hurley.module_user.model.Message
import android.app.Activity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * <pre>
 *      @author hurley
 *      date    : 4/1/21 10:26 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 推送消息适配器类
 * </pre>
 */
class PushAdapter(private val mActivity: Activity) :
    BaseQuickAdapter<Message, BaseViewHolder>(R.layout.notification_push_item), LoadMoreModule {


    override fun convert(holder: BaseViewHolder, item: Message) {
        val bindingHolder = BaseDataBindingHolder<NotificationPushItemBinding>(holder.itemView)
        bindingHolder.dataBinding?.model = item

        bindingHolder.dataBinding?.rlNotificationPush?.setOnClickListener {

        }
    }
}