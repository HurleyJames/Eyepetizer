package ac.hurley.module_home

import ac.hurley.module_common.util.immersionStatusBar
import ac.hurley.module_common.util.infoToast
import ac.hurley.module_provider.router.RouterPath
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.home_activity.*

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 11:14 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 主 Activity
 * </pre>
 */
class HomeActivity : AppCompatActivity() {

    /**
     * 退出时间
     */
    private var mExitTime: Long = 0

    private var mDailyFragment: Fragment? = null
    private var mDiscoveryFragment: Fragment? = null
    private var mHotFragment: Fragment? = null
    private var mUserFragment: Fragment? = null

    private lateinit var mHomeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        window.sharedElementsUseOverlay = false
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        // 沉浸式状态栏
        immersionStatusBar(true, android.R.color.white, true, 0.2f)
        initBottomNavigation()
        initViewModel()
    }

    private fun initViewModel() {
        mHomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        mHomeViewModel.getSelected().observe(this) { index ->
            switchFragment(index)
        }
    }

    private fun initBottomNavigation() {
        // 去掉底部默认选中的背景
        home_bnv.itemIconTintList = null
        home_bnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_daily -> saveAndSwitch(0)
                R.id.item_discover -> saveAndSwitch(1)
                R.id.item_hot -> saveAndSwitch(2)
                R.id.item_user -> saveAndSwitch(3)
            }
            true
        }

    }

    private fun saveAndSwitch(index: Int) {
        mHomeViewModel.saveSelected(index)
        switchFragment(index)
    }

    /**
     * 切换 Fragment
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 -> mDailyFragment?.let {
                transaction.show(it)
            } ?: (ARouter.getInstance().build(RouterPath.Daily.PATH_DAILY_HOME)
                .navigation() as Fragment).let {
                mDailyFragment = it
                transaction.add(R.id.home_frame, it, RouterPath.Daily.PATH_DAILY_HOME)
            }
            1 -> mDiscoveryFragment?.let {
                transaction.show(it)
            } ?: (ARouter.getInstance().build(RouterPath.Discover.PATH_DISCOVER_HOME)
                .navigation() as Fragment).let {
                mDiscoveryFragment = it
                transaction.add(R.id.home_frame, it, RouterPath.Discover.PATH_DISCOVER_HOME)
            }
            2 -> mHotFragment?.let {
                transaction.show(it)
            } ?: (ARouter.getInstance().build(RouterPath.Hot.PATH_HOT_HOME)
                .navigation() as Fragment).let {
                mHotFragment = it
                transaction.add(R.id.home_frame, it, RouterPath.Hot.PATH_HOT_HOME)
            }
            3 -> mUserFragment?.let {
                transaction.show(it)
            } ?: (ARouter.getInstance().build(RouterPath.User.PATH_USER_HOME)
                .navigation() as Fragment).let {
                mUserFragment = it
                transaction.add(R.id.home_frame, it, RouterPath.User.PATH_USER_HOME)
            }
        }
        transaction.commitNowAllowingStateLoss()
    }

    /**
     * 隐藏所有的 Fragment
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mDailyFragment?.let { transaction.hide(it) }
        mDiscoveryFragment?.let { transaction.hide(it) }
        mHotFragment?.let { transaction.hide(it) }
        mUserFragment?.let { transaction.hide(it) }
    }

    /**
     * 退出程序
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                infoToast(getString(R.string.home_exit_tips))
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }

}