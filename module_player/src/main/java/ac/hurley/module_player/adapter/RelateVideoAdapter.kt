package ac.hurley.module_player.adapter

import ac.hurley.module_provider.model.Item
import android.app.Activity
import com.chad.library.adapter.base.BaseProviderMultiAdapter

/**
 * <pre>
 *      @author hurley
 *      date    : 4/3/21 11:36 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 相关视频适配器类
 * </pre>
 */
class RelateVideoAdapter(mActivity: Activity) : BaseProviderMultiAdapter<Item>() {

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_VIDEO = 1
    }

    init {
        addItemProvider(RelateVideoTitleProvider())
        addItemProvider(RelateVideoItemProvider(mActivity))
    }

    override fun getItemType(data: List<Item>, position: Int): Int = data[position].itemType
}