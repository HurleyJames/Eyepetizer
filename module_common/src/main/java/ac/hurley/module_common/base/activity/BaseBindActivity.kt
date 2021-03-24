package ac.hurley.module_common.base.activity

import ac.hurley.module_common.base.viewmodel.BaseViewModel
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 3:14 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 绑定界面的 BaseBindActivity
 * </pre>
 */
abstract class BaseBindActivity<VM : BaseViewModel, VDB : ViewDataBinding> : BaseMVVMActivity<VM>() {

    lateinit var mBind: VDB

    override fun setLayout() {
        mBind = DataBindingUtil.setContentView(this, getLayoutRes)
    }

    override fun initEvent() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mBind.isInitialized) {
            mBind.unbind()
        }
    }
}