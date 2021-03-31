package ac.hurley.module_common.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * <pre>
 *      @author hurley
 *      date    : 4/1/21 12:46 AM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */

fun AppCompatActivity.setToolBar(toolbar: Toolbar, title: String) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setHomeButtonEnabled(true)
    toolbar.setNavigationOnClickListener { onBackPressed() }
    toolbar.title = title
}