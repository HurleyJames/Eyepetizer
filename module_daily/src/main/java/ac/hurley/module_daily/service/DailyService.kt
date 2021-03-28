package ac.hurley.module_daily.service

import ac.hurley.module_common.net.RetrofitClient
import ac.hurley.module_daily.api.DailyApi

/**
 * <pre>
 *      @author hurley
 *      date    : 3/28/21 4:09 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 日报 Service 类
 * </pre>
 */
object DailyService : DailyApi by RetrofitClient.instance.create(DailyApi::class.java) {
}