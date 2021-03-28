package ac.hurley.module_daily.fragment

import ac.hurley.module_common.base.fragment.BaseMVVMFragment
import ac.hurley.module_daily.R
import ac.hurley.module_daily.adapter.DailyAdapter
import ac.hurley.module_daily.viewmodel.DailyViewModel
import ac.hurley.module_provider.router.RouterPath
import android.widget.LinearLayout
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
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

    /**
     * 是否加载更多
     */
    private var mIsLoadMore = false

    private val mAdapter: DailyAdapter by lazy { DailyAdapter(mActivity, this) }

    override val getLayoutRes: Int
        get() = R.layout.daily_fragment

    override fun initView() {
        srl_daily.isRefreshing = true
        srl_daily.setOnRefreshListener {
            mIsLoadMore = false
            lazyLoadData()
        }
        rv_daily.layoutManager = LinearLayoutManager(context)
        rv_daily.adapter = mAdapter

        mAdapter.loadMoreModule.setOnLoadMoreListener {
            mIsLoadMore = true
            // TODO
        }
    }

    override fun hideLoading() {
        srl_daily.isRefreshing = false
    }

    override fun lazyLoadData() {
        mViewModel.getDailyBanner().observe(viewLifecycleOwner) {
            mAdapter.setList(mutableListOf())
            mAdapter.addData(it)
        }
    }
}