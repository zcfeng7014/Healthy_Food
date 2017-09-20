package Utils;

import android.content.Context;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class ViewUtils {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
