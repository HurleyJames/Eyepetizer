package ac.hurley.module_user.viewmodel

import ac.hurley.module_common.base.viewmodel.BaseViewModel
import ac.hurley.module_user.model.PushModel
import ac.hurley.module_user.service.NotificationService
import androidx.lifecycle.LiveData
import com.blankj.utilcode.util.LogUtils

/**
 * <pre>
 *      @author hurley
 *      date    : 4/1/21 8:53 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class NotificationViewModel : BaseViewModel() {

    private var mNextPageUrl: String? = null

    fun getPushList(): LiveData<PushModel> = liveDataEx {
        val pushModel = NotificationService.getPushList()
        mNextPageUrl = pushModel.nextPageUrl
        val list = pushModel.messageList[0]
        pushModel
    }
}

