package ac.hurley.module_hot.api

import ac.hurley.module_hot.model.TabInfo
import ac.hurley.module_provider.model.Issue
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * <pre>
 *      @author hurley
 *      date    : 3/29/21 4:56 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 热门界面的请求 API 地址
 * </pre>
 */
interface HotApi {

    @GET("v4/rankList")
    suspend fun getHotTabs(): TabInfo

    @GET
    suspend fun getRankList(@Url url: String): Issue
}