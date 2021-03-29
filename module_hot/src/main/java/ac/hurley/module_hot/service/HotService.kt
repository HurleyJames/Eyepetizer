package ac.hurley.module_hot.service

import ac.hurley.module_common.net.RetrofitClient
import ac.hurley.module_hot.api.HotApi

/**
 * <pre>
 *      @author hurley
 *      date    : 3/29/21 5:16 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */

object HotService : HotApi by RetrofitClient.instance.create(HotApi::class.java)