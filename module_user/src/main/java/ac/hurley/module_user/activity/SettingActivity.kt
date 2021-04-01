package ac.hurley.module_user.activity

import ac.hurley.module_common.util.immersionStatusBar
import ac.hurley.module_common.util.setToolBar
import ac.hurley.module_provider.router.RouterPath
import ac.hurley.module_user.databinding.UserSettingActivityBinding
import ac.hurley.module_user.viewmodel.SettingViewModel
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.user_setting_activity.*

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 11:55 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
@Route(path = RouterPath.User.PATH_USER_SETTING)
class SettingActivity : AppCompatActivity() {

    private val mBinding by lazy { UserSettingActivityBinding.inflate(LayoutInflater.from(this)) }

    private val mViewModel by lazy { ViewModelProvider(this).get(SettingViewModel::class.java) }


//    private lateinit var mSettingViewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        immersionStatusBar(true, android.R.color.white, true, 0.2f)
        setToolBar(tl_setting)
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
    }

}