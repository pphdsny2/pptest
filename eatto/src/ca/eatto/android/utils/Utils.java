package ca.eatto.android.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class Utils {

	/**
	 * 得到数据库操作对象
	 * 
	 * @return
	 */
//	public static DbUtils getDBInstance() {
//		return DbUtils.create(TApplication.getInstance().getApplicationContext(), Configure.DB_NAME, Configure.DB_VERSION, new DbUpgradeListener() {
//
//			@Override
//			public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
//				//  数据库升级操作
//				switch (oldVersion) {
//				case 1://MessageConsultDetail 增加 userId int 登录的用户的ID,为了解决多用户登录
//					db.getDatabase().execSQL("alter table "+Configure.TABLE_CONSULT_DETAIL+" add userId int");
//					break;
//
//				default:
//					break;
//				}
//			}
//		});
//	}


	/**
	 * 获取应用版本号
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppVersion(Context context) {
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "版本号未知";
		}
	}

	/**
	 * 获取android设备唯一标识码
	 * 各种方式的利弊分析：http://blog.csdn.net/dai_zhenliang/article/details/8634042
	 * 
	 * @return
	 */
	public static String getUniqueCode(Context context) {
		// // 1、获取IMEI: 仅仅只对Android手机有效
		// TelephonyManager tm = (TelephonyManager)
		// context.getSystemService(Context.TELEPHONY_SERVICE);
		// String imei = tm.getDeviceId();
		// // 2、获取硬件拼接的一个串(两个手机应用完全一样的硬件设备，基本不可能)
		// StringBuilder sb = new StringBuilder();
		// sb.append(Build.BOARD.length() % 10);
		// sb.append(Build.BRAND.length() % 10);
		// sb.append(Build.CPU_ABI.length() % 10);
		// sb.append(Build.DEVICE.length() % 10);
		// sb.append(Build.DISPLAY.length() % 10);
		// sb.append(Build.HOST.length() % 10);
		// sb.append(Build.ID.length() % 10);
		// sb.append(Build.MANUFACTURER.length() % 10);
		// sb.append(Build.MODEL.length() % 10);
		// sb.append(Build.PRODUCT.length() % 10);
		// sb.append(Build.TAGS.length() % 10);
		// sb.append(Build.TYPE.length() % 10);
		// sb.append(Build.USER.length() % 10);
		// String devId = sb.toString(); // 13 digits
		// // 3、获取WLAN 的MAC的地址
		// WifiManager wm = (WifiManager)
		// context.getSystemService(Context.WIFI_SERVICE);
		// String mac = wm.getConnectionInfo().getMacAddress();
		// // 4、MD5(1+2+3)
		// String md5 = EncryptUtils.encodeMD5(imei + devId + mac);
		// return md5;

		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String result = tm.getDeviceId();
		if (TextUtils.isEmpty(result)) {
			WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifi.getConnectionInfo();
			result = info.getMacAddress();
		}
		return result;

	}

	/**
	 * 得到umeng测试设备的info
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceInfo(Context context) {
		try {
			org.json.JSONObject json = new org.json.JSONObject();
			android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

			String device_id = tm.getDeviceId();

			android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

			String mac = wifi.getConnectionInfo().getMacAddress();
			json.put("mac", mac);

			if (TextUtils.isEmpty(device_id)) {
				device_id = mac;
			}

			if (TextUtils.isEmpty(device_id)) {
				device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
			}

			json.put("device_id", device_id);

			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 开始加载动画
	 * 
	 * @param iv_loading
	 */
	public static void startLoading(ImageView iv_loading) {
		if (iv_loading == null || iv_loading.getVisibility() == View.VISIBLE) {
			return;
		}
		AnimationDrawable drawable = (AnimationDrawable) iv_loading.getDrawable();
		drawable.start();
		iv_loading.setVisibility(View.VISIBLE);
	}

	/**
	 * 停止加载动画
	 * 
	 * @param iv_loading
	 */
	public static void stopLoading(ImageView iv_loading) {
		if (iv_loading == null || iv_loading.getVisibility() == View.GONE) {
			return;
		}
		AnimationDrawable drawable = (AnimationDrawable) iv_loading.getDrawable();
		drawable.stop();
		iv_loading.setVisibility(View.GONE);
	}

	/**
	 * long 转换为 String 格式默认 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param createDate
	 * @return
	 */
	public static String formatTime(long date) {
		return formatTime(date, "yyyy-MM-dd HH:mm:ss");
	}

	@SuppressLint("SimpleDateFormat")
	public static String formatTime(long date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String desDate = formatter.format(new Date(date));
		return desDate;
	}

	/**
	 * String 转换为 long
	 * 
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static long paserTime(String date) {
		return paserTime(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static long paserTime(String date, String pattern) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			Date parseDate = formatter.parse(date);
			long time = parseDate.getTime();
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String getApplicationName(Context context, String packageName) {
		PackageManager packageManager = null;
		ApplicationInfo applicationInfo = null;
		try {
			packageManager = context.getPackageManager();
			applicationInfo = packageManager.getApplicationInfo(packageName, 0);
		} catch (PackageManager.NameNotFoundException e) {
			applicationInfo = null;
		}
		String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
		return applicationName;
	}

	/**
	 * 异步任务执行
	 * 
	 * @param task
	 * @param params
	 */
	public static <Params, Progress, Result> void executeAsyncTask(AsyncTask<Params, Progress, Result> task, Params... params) {
		if (Build.VERSION.SDK_INT >= 11) {
			task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
		} else {
			task.execute(params);
		}
	}

	public static int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
	}

	/**
	 * 扫描图库，添加缩略图
	 * 
	 * @param context
	 * @param file
	 * @param listener
	 */
	public static void scanMediaJpegFile(final Context context, final String filePath, final OnScanCompletedListener listener) {
		MediaScannerConnection.scanFile(context, new String[] { filePath }, new String[] { "image/jpg" }, listener);
	}

	/**
	 * SD卡是否可用
	 * 
	 * @return
	 */
	public static boolean isExternalStorageAvilable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	public static boolean hasHoneycomb() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
	}

	/**
	 * SDK是否大于12
	 * 
	 * @return
	 */
	public static boolean hasHoneycombMR1() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
	}


	/**
	 * 相机是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean hasCamera(Context context) {
		PackageManager pm = context.getPackageManager();
		return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) || pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
	}

	/**
	 * 打开相册
	 * 
	 * @param photoDir
	 *            图片的根路径
	 * @param fileName
	 *            图片名称
	 * @param activity
	 * @param requestCode
	 * @return null为失败，返回照相的地址
	 */
	public static File openCamera(String photoDir, String fileName, Activity activity, int requestCode) {
		try {
			File photo_dir = new File(photoDir);
			if (!photo_dir.exists()) {
				photo_dir.mkdirs();
			}
			// String fileName = "liba_house_identity_authentication.jpg";
			File mCurrentPhotoFile = new File(photo_dir, fileName);
			// 开启相机
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
			activity.startActivityForResult(intent, 1);
			return mCurrentPhotoFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get the screen height.
	 * 
	 * @param context
	 * @return the screen height
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static int getScreenHeight(Activity context) {

		Display display = context.getWindowManager().getDefaultDisplay();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			Point size = new Point();
			display.getSize(size);
			return size.y;
		}
		return display.getHeight();
	}

	/**
	 * Get the screen width.
	 * 
	 * @param context
	 * @return the screen width
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static int getScreenWidth(Activity context) {

		Display display = context.getWindowManager().getDefaultDisplay();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			Point size = new Point();
			display.getSize(size);
			return size.x;
		}
		return display.getWidth();
	}
}
