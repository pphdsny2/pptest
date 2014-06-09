package ca.eatto.android.home.list;

import java.util.ArrayList;
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
import ca.eatto.android.models.Restaurant;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

public class HomeListFragment extends BaseFragment {

	@ViewInject(R.id.lv_list)
	private ListView lv_list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.il_home_list, null);
		ViewUtils.inject(this, view);
		init();
		loadData();
		return view;
	}
	
	private void init() {
		
	}

	private void loadData() {
		//TODO 模拟数据
		List<Restaurant> data = new ArrayList<Restaurant>();
		data.add(new Restaurant("辛香汇1", "2", "川菜1", "$20",1));
		data.add(new Restaurant("辛香汇2", "3", "川菜2", "$30",3));
		data.add(new Restaurant("辛香汇3", "2", "川菜3", "$10",2));
		data.add(new Restaurant("辛香汇4", "1", "台湾菜", "$60",1));
		data.add(new Restaurant("辛香汇5", "10", "湘菜", "$60",1));
		data.add(new Restaurant("辛香汇6", "5", "本帮菜", "$50",5));
		data.add(new Restaurant("辛香汇7", "1", "鲁菜", "$64",4));
		data.add(new Restaurant("辛香汇2", "1", "贛菜", "$30",3));
		lv_list.setAdapter(new HomeListAdapter(getActivity(), data));
	}
	
	@OnItemClick(R.id.lv_list)
	public void clickRestaurantItem(AdapterView<?> parent, View view, int position, long id){
		Intent intent = new Intent(getActivity(),RestaurantDetailActivity.class);
		startActivity(intent);
	}
}
