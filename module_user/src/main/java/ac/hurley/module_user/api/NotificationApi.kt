package ac.hurley.module_user.api

import ac.hurley.module_user.model.Message
import ac.hurley.module_user.model.PushModel
import retrofit2.http.GET

/**
 * <pre>
 *      @author hurley
 *      date    : 4/1/21 8:44 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
interface NotificationApi {

    @GET("v3/messages")
    suspend fun getPushList(): PushModel
}