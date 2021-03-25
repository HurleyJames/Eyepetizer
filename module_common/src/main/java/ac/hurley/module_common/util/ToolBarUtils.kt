package ac.hurley.module_common.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * <pre>
 *      @author hurley
 *      date    : 3/25/21 10:27 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : ToolBar 工具类
 * </pre>
 */

fun AppCompatActivity.setToolBar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setHomeButtonEnabled(true)
    toolbar.setNavigationOnClickListener {
        onBackPressed()
    }
}