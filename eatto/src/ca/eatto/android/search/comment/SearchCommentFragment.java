package ca.eatto.android.search.comment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import ca.eatto.android.R;
import ca.eatto.android.base.BaseFragment;
import ca.eatto.android.detail.RestaurantDetailActivity;
import ca.eatto.android.engine.SearchEngine;
import ca.eatto.android.models.Restaurant;
import ca.eatto.android.models.RestaurantTitle;
import ca.eatto.android.views.PinnedHeaderListView;
import ca.eatto.android.views.PinnedHeaderListView.OnItemClickListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SearchCommentFragment extends BaseFragment {

	public static final int STATUS_LOAD_COMMENT = 1;
	public static final int STATUS_LOAD_SEARCH_RESULT = 2;

	@ViewInject(R.id.lv_comment)
	private PinnedHeaderListView lv_comment;
	private SearchEngine searchEngine;

	private int status;
	private String searchContent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.il_search_comment, null);
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
		lv_comment.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int section, int position, long id) {
				Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
				startActivity(intent);
			}

			@Override
			public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {
				
			}
		});
	}

	private void loadData() {
		switch (status) {
		case STATUS_LOAD_COMMENT:
			loadComment();
			break;
		case STATUS_LOAD_SEARCH_RESULT:
			loadSearchResult(searchContent);
			break;

		default:
			break;
		}
	}

	public void loadComment() {
		List<Restaurant> newRestaurants = searchEngine.getNewRestaurants();
		List<Restaurant> hotSearchRestaurants = searchEngine.getHotSearchRestaurants();
		List<RestaurantTitle> data = new ArrayList<RestaurantTitle>();
		data.add(new RestaurantTitle("新餐厅推荐", newRestaurants));
		data.add(new RestaurantTitle("热门搜索", hotSearchRestaurants));
		SearchCommentAdapter adapter = new SearchCommentAdapter(getActivity(), data);
		lv_comment.setAdapter(adapter);
	}

	public void loadSearchResult(String searchContent) {
		List<Restaurant> searchRestaurants = searchEngine.getSearchRestaurants(searchContent);
		List<RestaurantTitle> data = new ArrayList<RestaurantTitle>();
		data.add(new RestaurantTitle("搜索结果", searchRestaurants));
		SearchCommentAdapter adapter = new SearchCommentAdapter(getActivity(), data);
		lv_comment.setAdapter(adapter);
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

}
