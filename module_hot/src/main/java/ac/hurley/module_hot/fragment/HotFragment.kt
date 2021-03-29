package ac.hurley.module_hot.fragment

import ac.hurley.module_common.base.fragment.BaseMVVMFragment
import ac.hurley.module_hot.R
import ac.hurley.module_hot.model.TabInfo
import ac.hurley.module_hot.viewmodel.HotViewModel
import ac.hurley.module_provider.router.RouterPath
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayoutMediator
import com.youth.banner.util.LogUtils
import kotlinx.android.synthetic.main.hot_fragment.*

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

    override fun lazyLoadData() {
        mViewModel.getHotTabs().observe(viewLifecycleOwner) {
            initTabInfo(it)
        }
    }

    private fun initTabInfo(tabInfo: TabInfo) {
        val fragments = mutableListOf<Fragment>()
        tabInfo.tabInfo.tabList.forEach { tab ->
            tl_hot.addTab(tl_hot.newTab())
            fragments.add(HotRankListFragment.newInstance(tab.apiUrl))
        }
        vp_hot.adapter = object : FragmentStateAdapter(this) {

            override fun getItemCount(): Int = fragments.size

            override fun createFragment(position: Int): Fragment = fragments[position]
        }
        TabLayoutMediator(tl_hot, vp_hot) { tab, position ->
            tab.text = tabInfo.tabInfo.tabList[position].name
        }.attach()

    }
}