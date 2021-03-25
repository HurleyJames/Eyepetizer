package ac.hurley.module_hot.fragment

import ac.hurley.module_common.base.fragment.BaseMVVMFragment
import ac.hurley.module_hot.R
import ac.hurley.module_hot.viewmodel.HotViewModel
import ac.hurley.module_provider.router.RouterPath
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * <pre>
 *      @author hurley
 *      date    : 3/25/21 2:34 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 热门界面
 * </pre>
 */

@Route(path = RouterPath.Hot.PATH_HOT_HOME)
class HotFragment : BaseMVVMFragment<HotViewModel>() {

    override val getLayoutRes: Int
        get() = R.layout.hot_fragment

    override fun initView() {

    }
}