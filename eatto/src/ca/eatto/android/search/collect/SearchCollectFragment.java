package ca.eatto.android.search.collect;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ca.eatto.android.R;
import ca.eatto.android.base.BaseFragment;
import ca.eatto.android.detail.RestaurantDetailActivity;
import ca.eatto.android.engine.SearchEngine;
import ca.eatto.android.models.Restaurant;
import ca.eatto.android.search.RestaurantAdapter;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

public class SearchCollectFragment extends BaseFragment {
	
	@ViewInject(R.id.lv_collect)
	private ListView lv_collect;
	private SearchEngine searchEngine;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.il_search_collect, null);
		ViewUtils.inject(this, view);
		init();
		loadData();
		return view;
	}
	
	private void init() {
		searchEngine = new SearchEngine();
	}

	private void loadData() {
		List<Restaurant> collectRestaurants = searchEngine.getCollectRestaurants();
		lv_collect.setAdapter(new RestaurantAdapter(getActivity(), collectRestaurants));
	}
	
	@OnItemClick(R.id.lv_collect)
	public void clickCollectItem(AdapterView<?> parent, View view, int position, long id){
		Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
		startActivity(intent);
	}
}
