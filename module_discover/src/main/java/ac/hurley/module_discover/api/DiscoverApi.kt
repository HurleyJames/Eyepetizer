package ac.hurley.module_discover.api

import ac.hurley.module_discover.model.CategoryModel
import ac.hurley.module_discover.model.TopicModel
import ac.hurley.module_provider.model.Issue
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 1:06 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
interface DiscoverApi {

    @GET("v4/categories")
    suspend fun getCategoryList(): List<CategoryModel>

    @GET
    suspend fun getCategoryDetailList(@Url url: String): Issue

    @GET("v3/specialTopics")
    suspend fun getTopicList(): TopicModel

    @GET
    suspend fun getTopicList(@Url url: String): TopicModel
}