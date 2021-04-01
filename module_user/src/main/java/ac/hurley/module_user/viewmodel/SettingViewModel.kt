package ac.hurley.module_user.viewmodel

import ac.hurley.module_common.base.activity.WebViewActivity
import ac.hurley.module_common.util.infoToast
import ac.hurley.module_provider.constant.Constant
import ac.hurley.module_user.R
import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.StringUtils.getString

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 9:30 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class SettingViewModel : ViewModel() {


    var rbDailySwitch: Boolean
        get() = SPUtils.getInstance().getBoolean("dailyOnOff", true)
        set(value) = SPUtils.getInstance().put("dailyOnOff", value)

    var rbWifiSwitch: Boolean
        get() = SPUtils.getInstance().getBoolean("wifiOnOff", true)
        set(value) = SPUtils.getInstance().put("wifiOnOff", value)

    var rbMobileNetworkSwitch: Boolean
        get() = SPUtils.getInstance().getBoolean("mobileNetworkOnOff", true)
        set(value) = SPUtils.getInstance().put("mobileNetworkOnOff", value)

    var rbTranslateSwitch: Boolean
        get() = SPUtils.getInstance().getBoolean("translateOnOff", true)
        set(value) = SPUtils.getInstance().put("translateOnOff", value)

    fun onClick(view: View) {
        when (view.id) {
            R.id.tv_clear_cache -> {
                infoToast(getString(R.string.feature_not_supported))
            }
            R.id.tv_cache_path, R.id.tv_play_definition, R.id.tv_cache_definition -> {
                infoToast(getString(R.string.feature_not_supported))
            }
            R.id.tv_user_agreement -> {
                WebViewActivity.start(
                    view.context,
                    getString(R.string.user_agreement),
                    Constant.USER_AGREEMENT,
                    false
                )
            }
            R.id.tv_check_version -> {
                infoToast(getString(R.string.feature_not_supported))
            }
            R.id.tv_legal_notices -> {
                WebViewActivity.start(
                    view.context,
                    getString(R.string.legal_notices),
                    Constant.LEGAL_NOTICES,
                    false
                )
            }
            R.id.tv_video_function, R.id.tv_copyright -> {
                WebViewActivity.start(
                    view.context,
                    getString(R.string.video_fun_statement),
                    Constant.VIDEO_FUNCTION_STATEMENT,
                    false
                )
            }
            R.id.tv_slogan, R.id.tv_description -> {
                infoToast(getString(R.string.feature_not_supported))
            }
            else -> {

            }
        }
    }

    /**
     * 清除所有缓存
     */
    private fun clearAllCache(context: Context) {}
}