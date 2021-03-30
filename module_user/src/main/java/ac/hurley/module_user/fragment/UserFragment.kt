package ac.hurley.module_user.fragment

import ac.hurley.module_provider.router.RouterPath
import ac.hurley.module_user.SettingActivity
import ac.hurley.module_user.databinding.UserFragmentBinding
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import kotlinx.android.synthetic.main.user_fragment.*

/**
 * <pre>
 *      @author hurley
 *      date    : 3/25/21 1:26 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 用户界面
 * </pre>
 */
@Route(path = RouterPath.User.PATH_USER_HOME)
class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UserFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        iv_user_settings.setOnClickListener {
            ARouter.getInstance().build(RouterPath.User.PATH_USER_SETTING).navigation()
        }
    }


}