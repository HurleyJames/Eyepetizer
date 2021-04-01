package ac.hurley.module_discover.activity

import ac.hurley.module_common.base.activity.BaseBindActivity
import ac.hurley.module_common.util.immersionStatusBar
import ac.hurley.module_common.util.setToolBar
import ac.hurley.module_discover.R
import ac.hurley.module_discover.databinding.DiscoverCategoryDetailActivityBinding
import ac.hurley.module_discover.viewmodel.CategoryViewModel
import ac.hurley.module_provider.adapter.VideoListAdapter
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.discover_category_detail_activity.*

/**
 * <pre>
 *      @author hurley
 *      date    : 4/1/21 1:08 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 某个分类下的具体 Activity 类
 * </pre>
 */
class CategoryDetailActivity :
    BaseBindActivity<CategoryViewModel, DiscoverCategoryDetailActivityBinding>() {

    private val mAdapter by lazy { VideoListAdapter(this) }

    private var mId: Int = 0

    companion object {
        const val ID = "id"
        const val TITLE = "title"
        const val HEADER_IMAGE = "headerImage"

        fun start(context: Activity, id: Int, title: String, headerImage: String, startView: View) {
            val intent = Intent(context, CategoryDetailActivity::class.java).apply {
                putExtra(ID, id)
                putExtra(TITLE, title)
                putExtra(HEADER_IMAGE, headerImage)
            }
            val options = ActivityOptions.makeSceneTransitionAnimation(
                context,
                startView,
                context.getString(R.string.shared_element_container)
            )
            context.startActivity(intent, options.toBundle())
        }
    }

    override val getLayoutRes: Int
        get() = R.layout.discover_category_detail_activity

    override fun initView() {
        immersionStatusBar()
        setToolBar(tl_discover_category)

        rv_discover_category_detail.layoutManager = LinearLayoutManager(this)
        mAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
        mAdapter.loadMoreModule.setOnLoadMoreListener {
            getCategoryDetail(-1)
        }
        rv_discover_category_detail.adapter = mAdapter
    }

    override fun initData() {
        mId = intent.getIntExtra(ID, 0)
        mBind.title = intent.getStringExtra(TITLE).toString()
        mBind.headerImage = intent.getStringExtra(HEADER_IMAGE).toString()
        getCategoryDetail(mId)
    }

    override fun handleError() {
        mAdapter.loadMoreModule.loadMoreFail()
    }

    /**
     * 获取某个分类下的具体数据
     */
    private fun getCategoryDetail(id: Int = -1) {
        mViewModel.getCategoryDetailList(id).observe(this) {
            if (it.isNotEmpty()) {
                mAdapter.loadMoreModule.loadMoreComplete()
                mAdapter.addData(it)
            } else {
                mAdapter.loadMoreModule.loadMoreEnd()
            }
        }
    }


}