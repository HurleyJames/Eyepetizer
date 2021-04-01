package ac.hurley.module_common.base.fragment

import ac.hurley.module_common.base.viewmodel.BaseViewModel
import ac.hurley.module_common.base.viewmodel.ErrorState
import ac.hurley.module_common.base.viewmodel.LoadState
import ac.hurley.module_common.base.viewmodel.SuccessState
import ac.hurley.module_common.util.errorToast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import java.lang.reflect.ParameterizedType

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 8:52 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : MVVM 模式下的 Fragment 的基类
 * </pre>
 */
abstract class BaseMVVMFragment<VM : BaseViewModel> : BaseFragment() {

    lateinit var mViewModel: VM

    override fun initData() {
        initViewModel()
        lazyLoadData()
    }

    private fun initViewModel() {
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
        mViewModel = ViewModelProvider(this)[parameterizedType.actualTypeArguments[0] as Class<VM>]
        mViewModel.mStateLiveData.observe(viewLifecycleOwner) { state ->
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
                    handlerError()
                }
            }
        }
    }

    open fun showLoading() {}

    open fun hideLoading() {}

    open fun handlerError() {}

    open fun lazyLoadData() {}
}