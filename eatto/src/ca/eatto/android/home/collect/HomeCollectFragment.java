package ca.eatto.android.home.collect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ca.eatto.android.R;
import ca.eatto.android.base.BaseFragment;

import com.lidroid.xutils.ViewUtils;

public class HomeCollectFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.il_home_collect, null);
		ViewUtils.inject(this, view);
		init();
		loadData();
		return view;
	}
	
	private void init() {
		
	}

	private void loadData() {
		
	}
	
}
