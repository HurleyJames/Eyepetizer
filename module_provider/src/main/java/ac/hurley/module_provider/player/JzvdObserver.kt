package ac.hurley.module_provider.player

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import cn.jzvd.Jzvd

/**
 * <pre>
 *      @author hurley
 *      date    : 4/3/21 11:32 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class JzvdObserver : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_PAUSE) {
            Jzvd.releaseAllVideos()
        }
    }
}