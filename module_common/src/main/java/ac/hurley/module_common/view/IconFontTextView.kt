package ac.hurley.module_common.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * <pre>
 *      @author hurley
 *      date    : 3/24/21 1:20 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 使用自定义字体显示 TextView
 * </pre>
 */
class IconFontTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        val typeface = Typeface.createFromAsset(context.assets, "iconfont.ttf")
        setTypeface(typeface)
    }
}