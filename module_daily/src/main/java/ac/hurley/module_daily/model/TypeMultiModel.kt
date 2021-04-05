package ac.hurley.module_daily.model

import ac.hurley.module_provider.model.Item
import androidx.annotation.IntDef

/**
 * <pre>
 *      @author hurley
 *      date    : 3/28/21 3:34 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 多个种类的实体类
 * </pre>
 */
class TypeMultiModel(
    @Type val type: Int,
    val item: Item? = null,
    val items: List<Item> = mutableListOf()
) {
    // 轮播图 | 标题 | 头像图片
    @IntDef(value = [Type.TYPE_BANNER, Type.TYPE_TITLE, Type.TYPE_IMAGE])
    @Target(AnnotationTarget.VALUE_PARAMETER)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Type {
        companion object {
            const val TYPE_BANNER = 0
            const val TYPE_TITLE = 1
            const val TYPE_IMAGE = 2
        }
    }
}