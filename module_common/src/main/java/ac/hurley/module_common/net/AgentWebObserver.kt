package ac.hurley.module_common.net

import ac.hurley.module_common.base.activity.WebViewActivity
import android.app.Activity
import android.view.KeyEvent
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.just.agentweb.AgentWeb
import com.just.agentweb.WebViewClient

/**
 * <pre>
 *      @author hurley
 *      date    : 3/31/21 11:24 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class AgentWebObserver(private val container: LinearLayout, private val mActivity: Activity) :
    DefaultLifecycleObserver {

    lateinit var mAgentWeb: AgentWeb

    override fun onCreate(owner: LifecycleOwner) {
        mAgentWeb = AgentWeb.with(mActivity)
            .setAgentWebParent(container, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setWebViewClient(mWebViewClient)
            .createAgentWeb()
            .ready()
            .go(mActivity.intent.getStringExtra(WebViewActivity.WEB_URL))
    }

    override fun onPause(owner: LifecycleOwner) {
        mAgentWeb.webLifeCycle.onPause()
    }

    override fun onResume(owner: LifecycleOwner) {
        mAgentWeb.webLifeCycle.onResume()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        mAgentWeb.webLifeCycle.onDestroy()
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean =
        mAgentWeb.handleKeyEvent(keyCode, event)

    private val mWebViewClient: WebViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            val js =
                "javascript:(function() {document.getElementsByClassName(\"share-bar-container\")[0].style.display=\'none\';" +
                        "document.getElementsByClassName(\"footer-container j-footer-container\")[0].style.display=\'none\';" +
                        "document.getElementsByClassName(\"kyt-promotion-bar-positioner\")[0].style.display=\'none\';" +
                        "})()"
            mAgentWeb.jsAccessEntrace.callJs(js)
        }
    }
}