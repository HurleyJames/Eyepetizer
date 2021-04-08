package ac.hurley.module_user.helper

import ac.hurley.module_provider.model.Data
import androidx.recyclerview.widget.DiffUtil

/**
 * <pre>
 *      @author hurley
 *      date    : 4/5/21 11:21 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 差分数据对比实现类
 * </pre>
 */
class DiffUtilCallback(private val oldList: List<Data>, private val newList: List<Data>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

}