package ac.hurley.module_common.base.activity

import ac.hurley.module_common.R
import ac.hurley.module_common.ext.setToolBar
import ac.hurley.module_common.net.AgentWebObserver
import ac.hurley.module_common.util.immersionStatusBar
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.web_view_activity.*

/**
 * <pre>
 *      @author hurley
 *      date    : 3/31/21 11:56 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
class WebViewActivity : AppCompatActivity() {

    private var mTitle: String = ""

    private var mUrl: String = ""

    private var mIsTitleFixed: Boolean = false

    private val mAgentWebObserver by lazy {
        AgentWebObserver(
            ll_webview,
            this
        )
    }

    companion object {

        const val WEB_TITLE = "web_title"

        const val WEB_URL = "web_url"

        const val IS_TITLE_FIXED = "is_title_fixed"


        fun start(context: Context, title: String, url: String, isTitleFixed: Boolean = true) {
            Intent(context, WebViewActivity::class.java).also {
                it.putExtra(WEB_TITLE, title)
                it.putExtra(WEB_URL, url)
                it.putExtra(IS_TITLE_FIXED, isTitleFixed)
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
        setContentView(R.layout.web_view_activity)
        lifecycle.addObserver(mAgentWebObserver)
        immersionStatusBar(true, android.R.color.white, true, 0.2f)
        setToolBar(tl_webview, mTitle)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (mAgentWebObserver.onKeyDown(keyCode, event)) {
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    private fun initParams() {
        mTitle = intent.getStringExtra(WEB_TITLE).toString()
        mUrl = intent.getStringExtra(WEB_URL).toString()
        mIsTitleFixed = intent.getStringExtra(IS_TITLE_FIXED).toBoolean()
    }
}