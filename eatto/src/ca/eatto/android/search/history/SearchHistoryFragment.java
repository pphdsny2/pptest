package ca.eatto.android.search.history;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import ca.eatto.android.R;
import ca.eatto.android.base.BaseFragment;
import ca.eatto.android.detail.RestaurantDetailActivity;
import ca.eatto.android.engine.SearchEngine;
import ca.eatto.android.models.OperateHistory;
import ca.eatto.android.models.OperateHistoryTitle;
import ca.eatto.android.search.SearchActivity;
import ca.eatto.android.views.PinnedHeaderListView;
import ca.eatto.android.views.PinnedHeaderListView.OnItemClickListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SearchHistoryFragment extends BaseFragment {

	@ViewInject(R.id.lv_history)
	private PinnedHeaderListView lv_history;
	private SearchEngine searchEngine;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.il_search_history, null);
		ViewUtils.inject(this, view);
		init();
		loadData();
		return view;
	}

	private void init() {
		searchEngine = new SearchEngine();
		initList();
	}

	private void initList() {
		lv_history.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int section, int position, long id) {
				switch (section) {
				case 0://搜索记录
					SearchHistoryAdapter adapter = (SearchHistoryAdapter) lv_history.getAdapter();
					OperateHistory operateHistory = adapter.getData().get(section).getHistorys().get(position);
					((SearchActivity)getActivity()).loadSearchResult(operateHistory.getName());
					break;
				case 1://浏览历史
					Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
					startActivity(intent);
					break;

				default:
					break;
				}
			}

			@Override
			public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void loadData() {
		List<OperateHistory> lookHistory = searchEngine.getLookHistory();
		List<OperateHistory> searchHistory = searchEngine.getSearchHistory();
		List<OperateHistoryTitle> data = new ArrayList<OperateHistoryTitle>();
		data.add(new OperateHistoryTitle("搜索记录", searchHistory));
		data.add(new OperateHistoryTitle("浏览过的餐厅", lookHistory));
		SearchHistoryAdapter adapter = new SearchHistoryAdapter(getActivity(), data);
		lv_history.setAdapter(adapter);
	}

}
