package ac.hurley.eyepetizer

import ac.hurley.module_common.BuildConfig
import ac.hurley.module_common.global.Configurator
import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV

/**
 * <pre>
 *      @author hurley
 *      date    : 3/25/21 11:36 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 定义 App 初始化的 Initializer，实现 Startup 库的 Initializer 接口
 * </pre>
 */
class AppInitializer : Initializer<Unit> {

    // 进行第三方库的初始化操作
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
        }
        Configurator.instance.withWebApiHost("http://baobab.kaiyanapp.com/api/").configure()
        MMKV.initialize(context)
        ARouter.init(context.applicationContext as Application)
    }

    // 取决于当前的 Initializer 是否还依赖于其他的 Initializer。如果有，Startup 会保证先初始化依赖的 Initializer，然后才会初始化当前的 Initializer。
    // 绝大多数情况下，初始化操作都是不会依赖于其他 Initializer 的，所以通常直接返回一个 emptyList() 就可以
    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

}