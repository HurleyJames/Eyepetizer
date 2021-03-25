package ac.hurley.module_daily.fragment

import ac.hurley.module_common.base.fragment.BaseMVVMFragment
import ac.hurley.module_daily.R
import ac.hurley.module_daily.viewmodel.DailyViewModel
import ac.hurley.module_provider.router.RouterPath
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.daily_fragment.*

/**
 * <pre>
 *      @author hurley
 *      date    : 3/25/21 2:04 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 日报界面
 * </pre>
 */
@Route(path = RouterPath.Daily.PATH_DAILY_HOME)
class DailyFragment : BaseMVVMFragment<DailyViewModel>() {

    override val getLayoutRes: Int
        get() = R.layout.daily_fragment

    override fun initView() {

    }
}