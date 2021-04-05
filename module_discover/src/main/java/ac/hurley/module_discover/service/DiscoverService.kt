package ac.hurley.module_discover.service

import ac.hurley.module_common.net.RetrofitClient
import ac.hurley.module_discover.api.DiscoverApi

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 1:23 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 发现 Service 类
 * </pre>
 */

object DiscoverService : DiscoverApi by RetrofitClient.instance.create(DiscoverApi::class.java)