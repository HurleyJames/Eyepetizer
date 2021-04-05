package ac.hurley.module_discover.fragment

import ac.hurley.module_common.base.fragment.BaseFragment
import ac.hurley.module_discover.R
import ac.hurley.module_provider.router.RouterPath
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.discover_fragment.*

/**
 * <pre>
 *      @author hurley
 *      date    : 3/25/21 2:38 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 发现界面
 * </pre>
 */
@Route(path = RouterPath.Discover.PATH_DISCOVER_HOME)
class DiscoverFragment : BaseFragment() {

    /**
     * 顶部 Tab 栏
     */
    private val mTabTitles: Array<String> by lazy { resources.getStringArray(R.array.discover_titles) }

    private val mFragments by lazy {
        mutableListOf<Fragment>().apply {
            add(CategoryFragment())
            add(TopicFragment())
        }
    }

    override val getLayoutRes: Int
        get() = R.layout.discover_fragment

    override fun initView() {
        // 绑定 ViewPager 与 Tab
        vp_discover.adapter = object :
            FragmentStatePagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            override fun getItem(position: Int): Fragment = mFragments[position]

            override fun getCount(): Int = mTabTitles.size

            override fun getPageTitle(position: Int): CharSequence? = mTabTitles[position]

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

            }
        }

        tl_discover.setupWithViewPager(vp_discover)
    }

    override fun initData() {
    }
}