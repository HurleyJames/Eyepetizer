package ac.hurley.module_daily.api

import ac.hurley.module_daily.model.DailyModel
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * <pre>
 *      @author hurley
 *      date    : 3/28/21 3:29 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 日报界面的请求 API 地址
 * </pre>
 */
interface DailyApi {

    @GET("v2/feed?num=1")
    suspend fun getDailyBanner(): DailyModel

    @GET
    suspend fun getDailyList(@Url url: String): DailyModel
}