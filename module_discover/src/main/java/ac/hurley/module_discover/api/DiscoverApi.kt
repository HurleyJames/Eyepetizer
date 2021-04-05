package ac.hurley.module_discover.api

import ac.hurley.module_discover.model.CategoryModel
import ac.hurley.module_discover.model.TopicDetailModel
import ac.hurley.module_discover.model.TopicModel
import ac.hurley.module_provider.model.Issue
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 1:06 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 发现界面的请求 API 地址
 * </pre>
 */
interface DiscoverApi {

    /**
     * 获取分类列表
     */
    @GET("v4/categories")
    suspend fun getCategoryList(): List<CategoryModel>

    /**
     * 获取某个分类下的具体数据
     */
    @GET
    suspend fun getCategoryDetailList(@Url url: String): Issue

    /**
     * 获取主题列表
     */
    @GET("v3/specialTopics")
    suspend fun getTopicList(): TopicModel

    /**
     * 获取主题列表
     */
    @GET
    suspend fun getTopicList(@Url url: String): TopicModel

    /**
     * 获取某个主题下的具体数据
     */
    @GET("v3/lightTopics/internal/{id}")
    suspend fun getTopicDetail(@Path("id") id: Int): TopicDetailModel
}