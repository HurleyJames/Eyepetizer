package ac.hurley.module_home

import ac.hurley.module_common.base.activity.BaseActivity
import ac.hurley.module_provider.router.RouterPath
import android.Manifest
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.splash_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * <pre>
 *      @author hurley
 *      date    : 4/2/21 1:05 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 启动闪屏页面
 * </pre>
 */
class SplashActivity : BaseActivity() {

    private val job by lazy { Job() }

    private val mSplashDuration = 3 * 1000L

    private val alphaAnimation by lazy {
        AlphaAnimation(0.5f, 1.0f).apply {
            duration = mSplashDuration
            fillAfter = true
        }
    }

    private val scaleAnimation by lazy {
        ScaleAnimation(
            1f,
            1.05f,
            1f,
            1.05f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = mSplashDuration
            fillAfter = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
//        requestWriteExternalStoragePermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun setupViews() {
        super.setupViews()
        iv_slogan.startAnimation(alphaAnimation)
        iv_splash.startAnimation(scaleAnimation)
        CoroutineScope(job).launch {
            delay(mSplashDuration)
            HomeActivity.start(this@SplashActivity)
        }
        isFirstOpenApp = false
    }

    /**
     * 请求获取读写外部存储的权限
     */
    private fun requestWriteExternalStoragePermission() {
        PermissionX.init(this@SplashActivity)
            .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .onExplainRequestReason { scope, deniedList ->
                val message = getString(R.string.permission_picture)
                scope.showRequestReasonDialog(
                    deniedList,
                    message,
                    getString(R.string.ok),
                    getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                val message = getString(R.string.permission_picture)
                scope.showForwardToSettingsDialog(
                    deniedList,
                    message,
                    getString(R.string.settings),
                    getString(R.string.cancel)
                )
            }
            .request { allGranted, grantedList, deniedList ->
                requestReadPhoneStatePermission()
            }
    }

    /**
     * 请求获取读取手机信息的权限
     */
    private fun requestReadPhoneStatePermission() {
        PermissionX.init(this@SplashActivity).permissions(Manifest.permission.READ_PHONE_STATE)
            .onExplainRequestReason { scope, deniedList ->
                val message = getString(R.string.permission_access_phone)
                scope.showRequestReasonDialog(
                    deniedList,
                    message,
                    getString(R.string.ok),
                    getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                val message = getString(R.string.permission_access_phone)
                scope.showForwardToSettingsDialog(
                    deniedList,
                    message,
                    getString(R.string.settings),
                    getString(R.string.cancel)
                )
            }
            .request { allGranted, grantedList, deniedList ->
                setContentView(R.layout.splash_activity)
            }
    }

    companion object {
        /**
         * 是否第一次打开该应用
         */
        var isFirstOpenApp: Boolean
            get() = SPUtils.getInstance().getBoolean("isFirstOpenApp", true)
            set(value) = SPUtils.getInstance().put("isFirstOpenApp", value)
    }
}