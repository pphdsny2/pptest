package ca.eatto.android.search.hot;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class SearchHotFragment extends BaseFragment {

	@ViewInject(R.id.lv_hot)
	private ListView lv_hot;
	private SearchEngine searchEngine;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.il_search_hot, null);
		ViewUtils.inject(this, view);
		init();
		loadData();
		return view;
	}

	private void init() {
		searchEngine = new SearchEngine();
	}

	private void loadData() {
		List<Restaurant> collectRestaurants = searchEngine.getHotRestaurants();
		lv_hot.setAdapter(new RestaurantAdapter(getActivity(), collectRestaurants));
	}

	@OnItemClick(R.id.lv_hot)
	public void clickCollectItem(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
		startActivity(intent);
	}
}
