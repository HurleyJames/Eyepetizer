package ac.hurley.module_provider.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import cn.jzvd.JzvdStd;

/**
 * <pre>
 *      @author hurley
 *      date    : 4/3/21 10:51 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public class JzvdStdRv extends JzvdStd {

    private ClickUi clickUi;
    private boolean isAtList;

    public JzvdStdRv(Context context) {
        super(context);
    }

    public JzvdStdRv(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setClickUi(ClickUi clickUi) {
        this.clickUi = clickUi;
    }

    public boolean isAtList() {
        return isAtList;
    }

    public void setAtList(boolean atList) {
        isAtList = atList;
        if (isAtList) bottomContainer.setVisibility(GONE);
    }

    @Override
    public void onClick(View v) {
        if (isAtList) bottomContainer.setVisibility(GONE);
        if (v.getId() == cn.jzvd.R.id.surface_container &&
                (state == STATE_PLAYING ||
                        state == STATE_PAUSE)) {
            if (clickUi != null) clickUi.onClickUiToggle();
        }
        super.onClick(v);
    }

    @Override
    public void startVideo() {
        super.startVideo();
        if (clickUi != null) clickUi.onClickStart();
    }

    public interface ClickUi {
        void onClickUiToggle();

        void onClickStart();
    }

}
