package ac.hurley.module_user.activity

import ac.hurley.module_common.util.immersionStatusBar
import ac.hurley.module_provider.router.RouterPath
import ac.hurley.module_user.R
import ac.hurley.module_user.fragment.InboxFragment
import ac.hurley.module_user.fragment.InteractionFragment
import ac.hurley.module_user.fragment.PushFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.user_notification_activity.*
import kotlinx.android.synthetic.main.user_notification_activity.view.*

/**
 * <pre>
 *      @author hurley
 *      date    : 4/1/21 4:06 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 通知页面
 * </pre>
 */
@Route(path = RouterPath.User.PATH_USER_NOTIFICATION)
class NotificationActivity : AppCompatActivity() {

    /**
     * 顶部 Tab 栏
     */
    private val mTabTitles: Array<String> by lazy { resources.getStringArray(R.array.user_notifications) }

    private val mFragments by lazy {
        mutableListOf<Fragment>().apply {
            add(PushFragment())
            add(InteractionFragment())
            add(InboxFragment())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_notification_activity)
        immersionStatusBar(true, android.R.color.white, true, 0.2f)

        initTab()

        // 返回上一页面
        tv_notification_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initTab() {

        vp_notification.adapter = object : FragmentStateAdapter(this) {

            override fun getItemCount(): Int = mFragments.size

            override fun createFragment(position: Int): Fragment = mFragments[position]
        }

        TabLayoutMediator(tl_notification, vp_notification) { tab, position ->
            when (position) {
                0 -> tab.text = mTabTitles[0]
                1 -> tab.text = mTabTitles[1]
                else -> tab.text = mTabTitles[2]
            }
        }.attach()

    }

}