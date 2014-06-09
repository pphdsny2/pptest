package ca.eatto.android.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import ca.eatto.android.Configure;
import ca.eatto.android.home.HomeFragmentAdapter;
import ca.eatto.android.search.collect.SearchCollectFragment;
import ca.eatto.android.search.comment.SearchCommentFragment;
import ca.eatto.android.search.food.SearchFoodFragment;
import ca.eatto.android.search.history.SearchHistoryFragment;
import ca.eatto.android.search.hot.SearchHotFragment;

public class SearchFragmentAdapter extends HomeFragmentAdapter{

	public SearchFragmentAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int position) {
		Fragment ft = null;
		switch (position) {
		case Configure.SEARCH_DEFAULT_FRAGMENT:
			ft = new SearchCommentFragment();
			break;
		case Configure.SEARCH_COLLECT_FRAGMENT:
			ft = new SearchCollectFragment();
			break;
		case Configure.SEARCH_HISTORY_FRAGMENT:
			ft = new SearchHistoryFragment();
			break;
		case Configure.SEARCH_HOT_FRAGMENT:
			ft = new SearchHotFragment();
			break;
		case Configure.SEARCH_FOOD_FRAGMENT:
			ft = new SearchFoodFragment();
			break;
		case Configure.SEARCH_RESULT_FRAGMENT:
			ft = new SearchCommentFragment();
			break;
		default:
			break;
		}
		return ft;
	}
	
	@Override
	public int getCount() {
		return 6;
	}

}
