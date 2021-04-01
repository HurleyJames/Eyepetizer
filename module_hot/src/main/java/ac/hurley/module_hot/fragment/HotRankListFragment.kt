package ac.hurley.module_hot.fragment

import ac.hurley.module_common.base.fragment.BaseMVVMFragment
import ac.hurley.module_hot.R
import ac.hurley.module_provider.adapter.VideoListAdapter
import ac.hurley.module_hot.viewmodel.HotViewModel
import android.os.Bundle
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.hot_fragment_rank_list.*

/**
 * <pre>
 *      @author hurley
 *      date    : 3/29/21 5:45 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 热门排行榜页面
 * </pre>
 */
class HotRankListFragment : BaseMVVMFragment<HotViewModel>() {

    // 排行榜页面只显示相应数量的热门视频，所以并不支持加载更多，只需要支持下拉刷新即可

    private val mAdapter by lazy {
        VideoListAdapter(
            mActivity
        )
    }

    companion object {
        const val API_URL_KEY = "api_url"
        fun newInstance(apiUrl: String): HotRankListFragment {
            val fragment = HotRankListFragment()
            val arguments = Bundle()
            arguments.putString(API_URL_KEY, apiUrl)
            fragment.arguments = arguments
            return fragment
        }
    }

    override val getLayoutRes: Int
        get() = R.layout.hot_fragment_rank_list

    override fun initView() {
        swl_rank.setOnRefreshListener {
            initData()
        }
        rv_rank.layoutManager = LinearLayoutManager(context)
        rv_rank.adapter = mAdapter
    }

    override fun showLoading() {
        swl_rank.isRefreshing = true
    }

    override fun hideLoading() {
        swl_rank.isRefreshing = false
    }

    override fun lazyLoadData() {
        arguments?.getString(API_URL_KEY)?.let { apiUrl -> getHotRankList(apiUrl) }
    }

    private fun getHotRankList(apiUrl: String) {
        mViewModel.getRankList(apiUrl).observe(viewLifecycleOwner) {
            mAdapter.setList(it)
        }
    }

}