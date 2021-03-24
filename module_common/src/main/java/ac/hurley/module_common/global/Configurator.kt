package ac.hurley.module_common.global

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 10:41 AM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class Configurator private constructor() {
    companion object {
        val instance: Configurator by lazy { Configurator() }
    }

    // 声明一个键值对 CONFIGs
    private val CONFIGs = mutableMapOf<Any, Any>()

    // 初始化代码块
    init {
        // 配置未开始
        CONFIGs[ConfigKeys.CONFIG_READY] = false
    }

    fun withWebApiHost(apiHost: String): Configurator {
        CONFIGs[ConfigKeys.WEB_API_HOST] = apiHost
        return this
    }

    fun <T> getConfiguration(key: Any): T {
        // as 表示特定类型的转换
        val ready = CONFIGs[ConfigKeys.CONFIG_READY] as Boolean
        check(ready) {
            "Configuration is not ready, call configure"
        }

        val value = CONFIGs[key]

        require(value != null) {
            "$key is null"
        }
        return value as T
    }

    fun configure() {
        CONFIGs[ConfigKeys.CONFIG_READY] = true
    }

}