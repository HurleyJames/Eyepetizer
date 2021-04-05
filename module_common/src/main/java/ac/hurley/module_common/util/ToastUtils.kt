package ac.hurley.module_common.util

import ac.hurley.module_common.base.app.GlobalApplication
import com.sdsmdg.tastytoast.TastyToast

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 3:07 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Toast 工具类
 * </pre>
 */

/**
 * 信息的 Toast
 */
fun infoToast(message: String) {
    GlobalApplication.get()?.let {
        TastyToast.makeText(it, message, TastyToast.LENGTH_LONG, TastyToast.INFO)
    }
}

/**
 * 错误的 Toast
 */
fun errorToast(message: String) {
    GlobalApplication.get()?.let {
        TastyToast.makeText(it, message, TastyToast.LENGTH_LONG, TastyToast.ERROR)
    }
}
