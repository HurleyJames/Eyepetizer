package ac.hurley.module_common.util

import android.content.Context
import android.content.Intent

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 1:09 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 分享工具类
 * </pre>
 */

object ShareUtils {

    /**
     * 分享文本
     */
    fun shareText(context: Context, text: String) {
        var shareIntent = Intent()
        // 设置分享行为
        shareIntent.action = Intent.ACTION_SEND
        // 设置分享内容的类型
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        shareIntent = Intent.createChooser(shareIntent, "")
        context.startActivity(shareIntent)
    }
}