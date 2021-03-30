package ac.hurley.module_discover.fragment

import ac.hurley.module_common.base.fragment.BaseMVVMFragment
import ac.hurley.module_discover.R
import ac.hurley.module_discover.viewmodel.DiscoverListViewModel
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.discover_list_fragment.*

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 2:04 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
abstract class DiscoverListFragment<VM : DiscoverListViewModel, T> : BaseMVVMFragment<VM>(),
    OnItemClickListener {

    private var mIsLoadMore = false

    lateinit var mAdapter: BaseQuickAdapter<T, BaseViewHolder>

    abstract fun getAdapter(): BaseQuickAdapter<T, BaseViewHolder>

    override val getLayoutRes: Int
        get() = R.layout.discover_list_fragment

    override fun initView() {
        srl_discover_list.isRefreshing = true
        srl_discover_list.setOnRefreshListener {
            lazyLoadData()
        }

        rv_discover_list.layoutManager = LinearLayoutManager(context)
        rv_discover_list.itemAnimator = null
        rv_discover_list.setHasFixedSize(true)
        mAdapter = getAdapter()
        mAdapter.setOnItemClickListener(this)
        mAdapter.loadMoreModule.setOnLoadMoreListener {
            mIsLoadMore = true
            getListData()
        }
        rv_discover_list.adapter = mAdapter
    }

    override fun hideLoading() {
        srl_discover_list.isRefreshing = false
    }

    override fun handlerError() {
        if (mIsLoadMore) {
            mAdapter.loadMoreModule.loadMoreFail()
        }
    }

    override fun lazyLoadData() {
        getListData(true)
    }

    private fun getListData(firstPage: Boolean = false) {
        mViewModel.getListData<T>(firstPage).observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                if (firstPage) {
                    mAdapter.setList(it)
                } else {
                    mAdapter.addData(it)
                    mAdapter.loadMoreModule.loadMoreComplete()
                }
            } else {
                if (mViewModel.mNextPageUrl == null) {
                    mAdapter.loadMoreModule.loadMoreEnd()
                } else {
                    mAdapter.loadMoreModule.loadMoreComplete()
                }
            }
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
    }
}