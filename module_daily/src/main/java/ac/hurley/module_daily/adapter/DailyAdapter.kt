package ac.hurley.module_daily.adapter

import ac.hurley.module_daily.model.TypeMultiModel
import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.module.LoadMoreModule

/**
 * <pre>
 *      @author hurley
 *      date    : 3/28/21 5:31 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 日报页面整体的适配器类，包括整合其中各种不同的适配器类
 * </pre>
 */
class DailyAdapter(activity: Activity, owner: LifecycleOwner) :
    BaseProviderMultiAdapter<TypeMultiModel>(), LoadMoreModule {

    init {
        addItemProvider(BannerItemProvider(owner, activity))
        addItemProvider(HeaderTextItemProvider())
        addItemProvider(ImageTextItemProvider(activity))
    }

    override fun getItemType(data: List<TypeMultiModel>, position: Int): Int =
        data[position].type

}