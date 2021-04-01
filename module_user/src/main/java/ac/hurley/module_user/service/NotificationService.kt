package ac.hurley.module_user.service

import ac.hurley.module_common.net.RetrofitClient
import ac.hurley.module_user.api.NotificationApi

/**
 * <pre>
 *      @author hurley
 *      date    : 4/1/21 8:56 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */

object NotificationService : NotificationApi by RetrofitClient.instance.create(NotificationApi::class.java)