package ac.hurley.module_user.fragment

import ac.hurley.module_common.base.fragment.BaseFragment
import ac.hurley.module_common.base.fragment.BaseMVVMFragment
import ac.hurley.module_user.R
import ac.hurley.module_user.adapter.PushAdapter
import ac.hurley.module_user.viewmodel.NotificationViewModel
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.notification_push_fragment.*

/**
 * <pre>
 *      @author hurley
 *      date    : 4/1/21 4:40 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class PushFragment : BaseMVVMFragment<NotificationViewModel>() {

    private val mAdapter by lazy { PushAdapter(mActivity) }

    /**
     * 是否加载更多
     */
    private var mIsLoadMore = false

    companion object {
        fun newInstance() = PushFragment()
    }

    override val getLayoutRes: Int
        get() = R.layout.notification_push_fragment

    override fun initView() {
        srl_notification_push.isRefreshing = true
        srl_notification_push.setOnRefreshListener {
            mIsLoadMore = false
            initData()
        }
        rv_notification_push.layoutManager = LinearLayoutManager(context)
        rv_notification_push.adapter = mAdapter

        mAdapter.loadMoreModule.setOnLoadMoreListener {
            mIsLoadMore = true
            getPushList()
        }
    }

    override fun lazyLoadData() {
        getPushList()
    }

    override fun showLoading() {
        srl_notification_push.isRefreshing = true
    }

    override fun hideLoading() {
        srl_notification_push.isRefreshing = false
    }

    override fun handlerError() {
        if (mIsLoadMore) {
            mAdapter.loadMoreModule.loadMoreFail()
        }
    }

    private fun getPushList() {
        mViewModel.getPushList().observe(viewLifecycleOwner) {
            if (it.messageList.isNotEmpty()) {
                if (mIsLoadMore) {
                    mAdapter.addData(it.messageList)
                    mAdapter.loadMoreModule.loadMoreComplete()
                } else {
                    mAdapter.setList(it.messageList)
                }
            } else {
                mAdapter.loadMoreModule.loadMoreEnd()
            }
        }
    }


}