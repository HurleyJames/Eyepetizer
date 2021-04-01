package ac.hurley.module_user.model

/**
 * <pre>
 *      @author hurley
 *      date    : 4/1/21 8:48 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 通知页面的推送消息实体类
 * </pre>
 */
data class PushModel(
    val messageList: List<Message>,
    val nextPageUrl: String,
    val updateTime: Long
)

data class Message(
    val actionUrl: String,
    val content: String,
    val date: Long,
    val icon: String,
    val id: Int,
    val ifPush: Boolean,
    val pushStatus: Int,
    val title: String,
    val uid: Any,
    val viewed: Boolean
)