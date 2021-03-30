package ac.hurley.module_discover.model

import ac.hurley.module_provider.model.Data
import ac.hurley.module_provider.model.Header

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 7:57 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 发现页面中主题版块具体数据的实体类
 * </pre>
 */
data class TopicDetailModel(
    val adTrack: Any,
    val brief: String,
    val count: Int,
    val headerImage: String,
    val id: Int,
    val itemList: List<Item>,
    val shareLink: String,
    val text: String
)

data class Item(
    val adIndex: Int,
    val `data`: ItemData,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class ItemData(
    val dataType: String,
    val header: Header,
    val content: Content,
)

data class Content(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)