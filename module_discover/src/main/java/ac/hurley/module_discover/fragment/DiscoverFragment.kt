package ac.hurley.module_discover.fragment

import ac.hurley.module_common.base.fragment.BaseFragment
import ac.hurley.module_discover.R
import ac.hurley.module_provider.router.RouterPath
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * <pre>
 *      @author hurley
 *      date    : 3/25/21 2:38 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
@Route(path = RouterPath.Discover.PATH_DISCOVER_HOME)
class DiscoverFragment : BaseFragment() {

    override val getLayoutRes: Int
        get() = R.layout.discover_fragment

    override fun initView() {
    }

    override fun initData() {
    }
}