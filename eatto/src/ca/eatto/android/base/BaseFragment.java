package ca.eatto.android.base;

import android.support.v4.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment {

	@Override
	public void onResume() {
		super.onResume();
//		MobclickAgent.onPageStart(this.getClass().getName());
	}

	@Override
	public void onPause() {
		super.onPause();
//		MobclickAgent.onPageEnd(this.getClass().getName());
	}

	@Override
	public void setMenuVisibility(boolean menuVisible) {
		super.setMenuVisibility(menuVisible);
		if (this.getView() != null)
			this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
	}
}
