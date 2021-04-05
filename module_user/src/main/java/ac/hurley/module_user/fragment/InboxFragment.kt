package ac.hurley.module_user.fragment

import ac.hurley.module_common.base.fragment.BaseFragment
import ac.hurley.module_user.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * <pre>
 *      @author hurley
 *      date    : 4/1/21 4:40 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 私信界面
 * </pre>
 */
class InboxFragment : BaseFragment() {

    companion object {
        fun newInstance() = InboxFragment()
    }

    override val getLayoutRes: Int
        get() = R.layout.user_login_tips_fragment

    override fun initView() {
    }

    override fun initData() {
    }
}