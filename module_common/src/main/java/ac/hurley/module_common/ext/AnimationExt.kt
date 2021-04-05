package ac.hurley.module_common.ext

import ac.hurley.module_common.R
import android.R.id.content
import android.app.Activity
import android.graphics.Color.*
import android.view.View
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

/**
 * <pre>
 *      @author hurley
 *      date    : 4/5/21 3:20 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */

/**
 * 通用容器变换动画
 */
fun Activity.commonMaterialContainerTransform() {
    // 设置页面进入时的过渡动画
    window.sharedElementEnterTransition = MaterialContainerTransform().apply {
        addTarget(content)
        duration = 250L
        setAllContainerColors(WHITE)
    }

    // 设置页面退出时的过渡动画
    window.sharedElementEnterTransition = MaterialContainerTransform().apply {
        addTarget(content)
        duration = 250L
        setAllContainerColors(WHITE)
    }

    // 设置过渡元素控件以及名称
    findViewById<View>(content).transitionName = getString(R.string.shared_element_container)

    // 设置共享元素页面进入回调监听
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
}