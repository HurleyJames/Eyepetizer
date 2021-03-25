package ac.hurley.module_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 11:21 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class HomeViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val HOME_INDEX = "home_index"
    private val mLiveData = MutableLiveData<Int>()

    fun getSelected(): LiveData<Int> {
        // 从缓存中读取
        if (mLiveData.value == null) {
            val index = savedStateHandle.get<Int>(HOME_INDEX) ?: 0
            mLiveData.postValue(index)
        }
        return mLiveData
    }

    // 保存每一次的选中下标
    fun saveSelected(selectIndex: Int) {
        savedStateHandle.set(HOME_INDEX, selectIndex)
    }
}