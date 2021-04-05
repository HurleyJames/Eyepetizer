package ac.hurley.eyepetizer

import ac.hurley.module_common.BuildConfig
import ac.hurley.module_common.global.Configurator
import ac.hurley.module_provider.constant.Constant
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
        // 这两行必须写在 init 之前，否则这些配置在 init 过程中将无效
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog()
            // 开启调试模式（如果在 InstantRun 模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险）
            ARouter.openDebug()
        }
        Configurator.instance.withWebApiHost(Constant.BASE_URL).configure()
        // 高性能通用 key-value 组件
        MMKV.initialize(context)
        // 在 Application 中初始化
        ARouter.init(context.applicationContext as Application)
    }

    // 取决于当前的 Initializer 是否还依赖于其他的 Initializer。如果有，Startup 会保证先初始化依赖的 Initializer，然后才会初始化当前的 Initializer。
    // 绝大多数情况下，初始化操作都是不会依赖于其他 Initializer 的，所以通常直接返回一个 emptyList() 就可以
    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

}