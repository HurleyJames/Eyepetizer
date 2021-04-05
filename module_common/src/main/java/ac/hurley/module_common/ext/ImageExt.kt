package ac.hurley.module_common.ext

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.blankj.utilcode.util.ConvertUtils.dp2px

/**
 * <pre>
 *      @author hurley
 *      date    : 3/28/21 8:55 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 图片拓展类
 * </pre>
 */

@BindingAdapter(
    // 使用 bind:url 的方式加载图片
    value = ["url", "showLoading", "strokeWidth", "centerRadius", "allowHardware"],
    requireAll = false
)
fun ImageView.loadUrl(
    url: String,
    showLoading: Boolean = false,
    strokeWidth: Float = dp2px(5f).toFloat(),
    centerRadius: Float = dp2px(30f).toFloat(),
    allowHardware: Boolean = true
) {
    if (url.isNotEmpty()) {
        load(url) {
            allowHardware(allowHardware)
            // 设置网络图片加载动画
            if (showLoading) {
                val circularProgressDrawable = CircularProgressDrawable(context)
                circularProgressDrawable.strokeWidth = strokeWidth
                circularProgressDrawable.centerRadius = centerRadius
                circularProgressDrawable.setColorSchemeColors(Color.RED)
                circularProgressDrawable.start()
                placeholder(circularProgressDrawable)
            }
        }
    }
}