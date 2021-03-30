package ac.hurley.module_discover.model

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 1:16 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 发现页面中分类版块的实体类
 * </pre>
 */
class CategoryModel(
    val id: Int,
    val name: String,
    val alias: Any,
    val description: String,
    val bgPicture: String,
    val bgColor: String,
    val headerImage: String,
    val defaultAuthorId: Int,
    val tagId: Int
)