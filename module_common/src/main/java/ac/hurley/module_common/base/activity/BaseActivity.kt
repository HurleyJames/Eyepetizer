package ac.hurley.module_common.base.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import java.lang.ref.WeakReference

/**
 * <pre>
 *      @author hurley
 *      date    : 4/2/21 12:48 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 无需使用 MVVM 绑定的 Activity 基类
 * </pre>
 */
open class BaseActivity : AppCompatActivity() {

    /**
     * 判断当前 Activity 是否在前台
     */
    protected var isActive: Boolean = false

    /**
     * 当前 Activity 的实例
     */
    protected var activity: Activity? = null

    /**
     * 当前 Activity 的弱引用，防止内存泄漏
     */
    private var activityWR: WeakReference<Activity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("BaseActivity --> onCreate()")

        activity = this
        activityWR = WeakReference(activity!!)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtils.d("BaseActivity --> onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        LogUtils.d("BaseActivity --> onRestoreInstanceState()")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtils.d("BaseActivity --> onNewIntent()")
    }

    override fun onRestart() {
        super.onRestart()
        LogUtils.d("BaseActivity --> onRestart()")
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d("BaseActivity --> onStart()")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d("BaseActivity --> onResume()")
        isActive = true
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d("BaseActivity --> onPause()")
        isActive = false
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d("BaseActivity --> onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("BaseActivity --> onDestroy()")
        activity = null
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setupViews()
    }

    protected open fun setupViews() {

    }
}