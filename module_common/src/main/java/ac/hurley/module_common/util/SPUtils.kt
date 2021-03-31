package ac.hurley.module_common.util

import android.content.SharedPreferences
import ac.hurley.module_common.base.app.AppGlobal
import android.content.Context

/**
 * <pre>
 *      @author hurley
 *      date    : 3/31/21 1:20 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */

val sharedPreferences: SharedPreferences = AppGlobal.get().let {
    it!!.getSharedPreferences(it!!.packageName + "_preferences", Context.MODE_PRIVATE)
}

fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    editor.apply()
}