package ac.hurley.module_common.base.activity

import ac.hurley.module_common.base.viewmodel.BaseViewModel
import ac.hurley.module_common.base.viewmodel.ErrorState
import ac.hurley.module_common.base.viewmodel.LoadState
import ac.hurley.module_common.base.viewmodel.SuccessState
import ac.hurley.module_common.util.errorToast
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import java.lang.reflect.ParameterizedType

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 2:45 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : MVVM 模式下的 Activity 的基类
 * </pre>
 */
abstract class BaseMVVMActivity<VM : BaseViewModel> : AppCompatActivity() {

    abstract val getLayoutRes: Int

    // 使用 lateinit 关键字修饰后，变量在定义时不需要初始化
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initWindow()
        super.onCreate(savedInstanceState)
        setLayout()
        initView()
        initViewModel()
        initData()
        initEvent()
    }

    private fun initViewModel() {
        setContentView(getLayoutRes)
    }

    open fun setLayout() {
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
        mViewModel = ViewModelProvider(this)[parameterizedType.actualTypeArguments[0] as Class<VM>]
        mViewModel.mStateLiveData.observe(this) { state ->
            when (state) {
                LoadState -> {
                    showLoading()
                }
                SuccessState -> {
                    hideLoading()
                }
                is ErrorState -> {
                    hideLoading()
                    state.errorMsg?.let { errorToast(it) }
                    handleError()
                }
            }
        }
    }

    // 显示进度条
    open fun showLoading() {}

    // 隐藏进度条
    open fun hideLoading() {}

    // 处理错误
    open fun handleError() {}

    // 初始化窗口
    open fun initWindow() {}

    // 初始化 View
    abstract fun initView()

    // 初始化数据
    abstract fun initData()

    // 初始化事件
    abstract fun initEvent()
}