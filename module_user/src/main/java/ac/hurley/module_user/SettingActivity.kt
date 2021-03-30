package ac.hurley.module_user

import ac.hurley.module_common.base.activity.BaseMVVMActivity
import ac.hurley.module_common.util.immersionStatusBar
import ac.hurley.module_provider.router.RouterPath
import ac.hurley.module_user.viewmodel.SettingViewModel
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 11:55 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
@Route(path = RouterPath.User.PATH_USER_SETTING)
class SettingActivity : BaseMVVMActivity<SettingViewModel>() {

    override val getLayoutRes: Int
        get() = R.layout.user_setting_activity

    override fun initView() {
        immersionStatusBar(true, android.R.color.white, true, 0.2f)
    }

    override fun initData() {
    }

    override fun initEvent() {
    }
}