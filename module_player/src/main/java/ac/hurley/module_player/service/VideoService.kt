package ac.hurley.module_player.service

import ac.hurley.module_common.net.RetrofitClient
import ac.hurley.module_player.api.VideoApi
import retrofit2.Retrofit

/**
 * <pre>
 *      @author hurley
 *      date    : 4/3/21 3:23 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
object VideoService : VideoApi by RetrofitClient.instance.create(VideoApi::class.java)