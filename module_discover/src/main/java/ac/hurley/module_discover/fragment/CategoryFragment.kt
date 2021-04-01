package ac.hurley.module_discover.fragment

import ac.hurley.module_common.base.fragment.BaseMVVMFragment
import ac.hurley.module_discover.R
import ac.hurley.module_discover.activity.CategoryDetailActivity
import ac.hurley.module_discover.adapter.CategoryAdapter
import ac.hurley.module_discover.viewmodel.CategoryViewModel
import android.util.TypedValue
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.fondesa.recyclerviewdivider.dividerBuilder
import kotlinx.android.synthetic.main.discover_category_fragment.*

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 1:14 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class CategoryFragment : BaseMVVMFragment<CategoryViewModel>(), OnItemClickListener {

    private val mAdapter by lazy { CategoryAdapter() }

    override val getLayoutRes: Int
        get() = R.layout.discover_category_fragment

    override fun initView() {
        srl_discover_category.setOnRefreshListener {
            initData()
        }
        rv_discover_category.layoutManager = GridLayoutManager(context, 2)
        mAdapter.setOnItemClickListener(this)
        rv_discover_category.adapter = mAdapter
        mActivity.dividerBuilder()
            .asSpace()
            .showSideDividers()
            .size(5, TypedValue.COMPLEX_UNIT_DIP).build().addTo(rv_discover_category)
    }

    override fun lazyLoadData() {
        mViewModel.getCategoryList().observe(viewLifecycleOwner) {
            mAdapter.setList(it)
        }
    }

    override fun showLoading() {
        srl_discover_category.isRefreshing = true
    }

    override fun hideLoading() {
        srl_discover_category.isRefreshing = false
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        CategoryDetailActivity.start(
            mActivity,
            mAdapter.data[position].id,
            mAdapter.data[position].name,
            mAdapter.data[position].headerImage,
            view.findViewById(R.id.fl_content)
        )
    }
}