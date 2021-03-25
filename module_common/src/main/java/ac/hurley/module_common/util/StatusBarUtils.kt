package ac.hurley.module_common.util

import android.app.Activity
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.FloatRange
import com.gyf.immersionbar.ktx.immersionBar

/**
 * <pre>
 *      @author hurley
 *      date    : 3/25/21 10:31 AM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */

fun Activity.immersionStatusBar() {
    immersionBar {}
}

fun Activity.immersionStatusBar(
    fits: Boolean = true,
    @ColorRes statusBarColor: Int,
    isDarkFont: Boolean,
    @FloatRange(from = 0.0, to = 1.0) statusAlpha: Float
) {
    immersionBar {
        fitsSystemWindows(fits)
        statusBarColor(statusBarColor)
        statusBarDarkFont(isDarkFont, statusAlpha)
    }
}

fun Activity.immersionStatusBar(
    titleBar: View,
    @ColorRes statusBarColor: Int,
    isDarkFont: Boolean,
    @FloatRange(from = 0.0, to = 1.0) statusAlpha: Float
) {
    immersionBar {
        titleBar(titleBar)
        statusBarColor(statusBarColor)
        statusBarDarkFont(isDarkFont, statusAlpha)
    }
}
