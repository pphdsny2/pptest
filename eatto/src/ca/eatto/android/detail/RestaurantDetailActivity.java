package ca.eatto.android.detail;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import ca.eatto.android.R;
import ca.eatto.android.base.BaseActivity;
import ca.eatto.android.engine.RestaurantEngine;
import ca.eatto.android.models.Restaurant;
import ca.eatto.android.models.RestaurantDiscuss;
import ca.eatto.android.utils.PromptManager;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class RestaurantDetailActivity extends BaseActivity {
	
	@ViewInject(R.id.iv_collect)
	private ImageView iv_collect;
	@ViewInject(R.id.lv_discuss)
	private ListView lv_discuss;
	
	private RestaurantEngine restaurantEngine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setLayout() {
		super.setLayout();
		setContentView(R.layout.il_restaurant_detail);
	}

	@Override
	protected void init() {
		super.init();
		restaurantEngine = new RestaurantEngine();
	}

	@Override
	protected void loadData() {
		super.loadData();
		Restaurant restaurantDetail = restaurantEngine.getRestaurantDetail(0);
		List<RestaurantDiscuss> restaurantDiscuss = restaurantEngine.getRestaurantDiscuss();
		
	}

	@OnClick(R.id.rl_back)
	public void clickBack(View v){
		this.finish();
	}
	
	@OnClick(R.id.iv_title_sahre)
	public void clickShare(View v){
		PromptManager.showToast(this, "share");
	}
	
	@OnClick(R.id.rl_collect)
	public void clickCollect(View v){
		PromptManager.showToast(this, iv_collect.isSelected()?"撤销":"喜欢");
		iv_collect.setSelected(!iv_collect.isSelected());
	}
	
	@OnClick(R.id.iv_bannet_cancel)
	public void clickBannerCancel(View v){
		PromptManager.showToast(this, "cacnel");
	}
}
