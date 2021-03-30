package ac.hurley.module_user.viewmodel

import ac.hurley.module_common.base.viewmodel.BaseViewModel
import ac.hurley.module_user.R
import android.content.Context
import android.view.View
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.SPUtils

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 9:30 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class SettingViewModel : BaseViewModel() {

    var rbDailySwitch: Boolean
        get() = SPUtils.getInstance().getBoolean("dailyOnOff", true)
        set(value) = SPUtils.getInstance().put("dailyOnOff", value)

    var rbWifiSwitch: Boolean
        get() = SPUtils.getInstance().getBoolean("wifiOnOff", true)
        set(value) = SPUtils.getInstance().put("wifiOnOff", value)

    var rbTranslateSwitch: Boolean
        get() = SPUtils.getInstance().getBoolean("translateOnOff", true)
        set(value) = SPUtils.getInstance().put("translateOnOff", value)

    fun onClick(view: View) {
        when (view.id) {
            R.id.tv_clear_cache -> {

            }
            R.id.tv_cache_path, R.id.tv_play_definition, R.id.tv_cache_definition -> {

            }
            R.id.tv_user_agreement -> {

            }
            R.id.tv_check_version -> {

            }
            R.id.tv_legal_notices -> {

            }
            R.id.tv_video_function, R.id.tv_copyright -> {

            }
            R.id.tv_slogan, R.id.tv_description -> {

            }
            R.id.ll_settings -> {

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