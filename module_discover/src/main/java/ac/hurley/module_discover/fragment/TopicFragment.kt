package ac.hurley.module_discover.fragment

import ac.hurley.module_discover.adapter.TopicAdapter
import ac.hurley.module_discover.model.TopicItemModel
import ac.hurley.module_discover.model.TopicListModel
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * <pre>
 *      @author hurley
 *      date    : 3/30/21 2:00 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class TopicFragment : DiscoverListFragment<TopicListModel, TopicItemModel>() {


    override fun getAdapter(): BaseQuickAdapter<TopicItemModel, BaseViewHolder> = TopicAdapter()

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }
}