package ac.hurley.module_common.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 1:15 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 日期工具类
 * </pre>
 */

object DateUtils {

    fun formatDateMsByMs(milliseconds: Long): String {
        val simpleDataFormat = SimpleDateFormat("mm:ss")
        return simpleDataFormat.format(Date(milliseconds))
    }

    fun formatDateMsByYMD(milliseconds: Long): String {
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
        return simpleDateFormat.format(Date(milliseconds))
    }

    fun formatDateMsByYMDHM(milliseconds: Long): String {
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm")
        return simpleDateFormat.format(Date(milliseconds))
    }
}