package ac.hurley.module_player.util

import com.blankj.utilcode.util.ScreenUtils

/**
 * <pre>
 *      @author hurley
 *      date    : 4/3/21 4:26 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */

object BlurredUtils {

    fun buildBlurred(blurred: String): String {
        val width: Int = ScreenUtils.getScreenWidth()
        val height: Int = ScreenUtils.getScreenHeight()
        return "${blurred}/thumbnail/${height}x${width}"
    }
}