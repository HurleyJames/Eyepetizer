package ac.hurley.module_common.base.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 4:03 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Fragment 的基类
 * </pre>
 */
abstract class BaseFragment : Fragment() {

    abstract val getLayoutRes: Int

    private var mRootView: View? = null

    lateinit var mActivity: Activity

    /**
     * 是否加载数据
     */
    private var mHasLoadData = false

    /**
     * 绑定 Fragment 到 Activity
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutRes, container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        if (!mHasLoadData) {
            initData()
            mHasLoadData = true
        }
    }

    abstract fun initView()

    abstract fun initData()
}