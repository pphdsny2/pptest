package ca.eatto.android;

import android.app.Activity;
import ca.eatto.android.utils.Utils;

/**
 * 存储变量
 * @author pphdsny
 *
 */
public class GlobalParams {

	public static Activity context;
	// 屏幕高度
	public static int screenWidth;
	public static int screenHeight;

	public void init(Activity activity) {
		screenWidth = Utils.getScreenWidth(activity);
		screenHeight = Utils.getScreenHeight(activity);

		context = activity;

	}
}
