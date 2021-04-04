package ac.hurley.module_player.api

import ac.hurley.module_provider.model.Issue
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * <pre>
 *      @author hurley
 *      date    : 4/3/21 3:21 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
interface VideoApi {

    /**
     * 获得该视频相关的视频列表
     */
    @GET("v4/video/related")
    suspend fun getRelatedVideoList(@Query("id") id: Int): Issue
}