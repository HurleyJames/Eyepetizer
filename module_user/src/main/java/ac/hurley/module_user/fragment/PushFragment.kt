package ac.hurley.module_user.fragment

import ac.hurley.module_common.base.fragment.BaseFragment
import ac.hurley.module_user.R

/**
 * <pre>
 *      @author hurley
 *      date    : 4/1/21 4:40 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class PushFragment : BaseFragment() {

    companion object {
        fun newInstance() = PushFragment()
    }

    override val getLayoutRes: Int
        get() = R.layout.user_login_tips_fragment

    override fun initView() {
    }

    override fun initData() {
    }
}