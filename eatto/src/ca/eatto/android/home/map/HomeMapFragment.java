package ca.eatto.android.home.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;

import ca.eatto.android.R;
import ca.eatto.android.base.BaseFragment;

public class HomeMapFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.il_home_map, null);
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
