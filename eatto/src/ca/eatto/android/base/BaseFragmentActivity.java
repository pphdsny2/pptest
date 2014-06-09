package ca.eatto.android.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import ca.eatto.android.GlobalParams;

import com.lidroid.xutils.ViewUtils;

public class BaseFragmentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		new GlobalParams().init(this);
		setLayout();
		ViewUtils.inject(this);
		init();
		loadData();
	}

	/**
	 * 设置布局文件
	 */
	protected void setLayout() {
	}

	/**
	 * 做一些初始化工作
	 */
	protected void init() {
	}

	/**
	 * 加载数据
	 */
	protected void loadData() {
	}

	@Override
	protected void onResume() {
		super.onResume();
//		MobclickAgent.onPageStart(this.getClass().getName());
//		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
//		MobclickAgent.onPageEnd(this.getClass().getName());
//		MobclickAgent.onPause(this);
	}
}
