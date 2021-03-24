package ac.hurley.module_common.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.lang.Exception

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 1:28 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : BaseViewModel，ViewModel 的基类
 * </pre>
 */

/**
 * ViewModel 的主要作用是存放页面所需要的各种数据，而当这些数据发生变化时，我们采用接口的方式实现对页面的通知
 * 使用 LiveData 组件，来完成 ViewModel 与页面组件之间的通信
 */
open class BaseViewModel : ViewModel() {

    val mStateLiveData = MutableLiveData<State>()

    fun <T> liveDataEx(block: suspend () -> T) = liveData {
        try {
            mStateLiveData.value = LoadState
            emit(block())
            mStateLiveData.value = SuccessState
        } catch (e: Exception) {
            mStateLiveData.value = ErrorState(e.message)
        }
    }

    fun <T> flowEx(block: suspend () -> T) = flow {
        emit(block())
    }.onStart {
        mStateLiveData.value = LoadState
    }.onCompletion {
        mStateLiveData.value = SuccessState
    }.catch { cause ->
        mStateLiveData.value = ErrorState(cause.message)
    }.asLiveData()
}

// 使用密封类的时候，用 when 子句不用添加 else 子句
sealed class State
object LoadState : State()
object SuccessState : State()
class ErrorState(val errorMsg: String?) : State()