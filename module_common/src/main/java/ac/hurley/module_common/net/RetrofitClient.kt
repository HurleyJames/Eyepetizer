package ac.hurley.module_common.net

import ac.hurley.module_common.BuildConfig
import ac.hurley.module_common.global.ConfigKeys
import ac.hurley.module_common.global.Configurator
import com.blankj.utilcode.util.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 10:15 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 使用静态内部类的方式去创建 Retrofit 实例
 * </pre>
 */

class RetrofitClient private constructor() {

    // 伴生对象
    companion object {
        val instance = SingleTonProvider.holder
    }

    private object SingleTonProvider {
        val holder = RetrofitClient()
    }

    private val httpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                LogUtils.e(message)
            }
        }).also {
            it.level = HttpLoggingInterceptor.Level.BODY

        }

    private val okHttpClient = OkHttpClient.Builder()
        .also {
            if (BuildConfig.DEBUG) {
                it.addInterceptor(httpLoggingInterceptor)
            }
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Configurator.instance.getConfiguration<String>(ConfigKeys.WEB_API_HOST))
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

}