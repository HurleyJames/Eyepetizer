package ac.hurley.module_common.base.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 12:02 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 全局 Application 类
 * </pre>
 */

object GlobalApplication {

    private var mApplication: Application? = null
        @SuppressLint("PrivateApi")
        get() {
            if (field == null) {
                return Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                    .invoke(null) as Application?
            }
            return field
        }

    fun get(): Application? = mApplication

}