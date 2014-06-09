package ca.eatto.android.home;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import ca.eatto.android.Configure;
import ca.eatto.android.R;
import ca.eatto.android.base.BaseFragmentActivity;
import ca.eatto.android.home.menu.HomeMenuMoreTypeAdapter;
import ca.eatto.android.home.menu.HomeMenuTypeAdapter;
import ca.eatto.android.menudrawer.MenuDrawer;
import ca.eatto.android.menudrawer.Position;
import ca.eatto.android.models.MenuType;
import ca.eatto.android.models.MenuTypeTitle;
import ca.eatto.android.search.SearchActivity;
import ca.eatto.android.views.PinnedHeaderListView;
import ca.eatto.android.views.RangeSeekBar;
import ca.eatto.android.views.RangeSeekBar.OnRangeSeekBarChangeListener;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

public class HomeActivity extends BaseFragmentActivity {

	@ViewInject(R.id.fl_primary)
	private FrameLayout fl_primary;
	/** buttom */
	@ViewInject(R.id.tab_list)
	private ImageView iv_list;
	@ViewInject(R.id.tab_map)
	private ImageView iv_map;
	@ViewInject(R.id.tab_collect)
	private ImageView iv_collect;
	/** menu */
	@ViewInject(R.id.rl_menu_left)
	private View rl_menu_left;
	@ViewInject(R.id.rl_menu_right)
	private View rl_menu_right;
	@ViewInject(R.id.ll_range_price)
	private LinearLayout ll_range_price;
	@ViewInject(R.id.tv_min_menoy)
	private TextView tv_min_price;
	@ViewInject(R.id.tv_max_menoy)
	private TextView tv_max_price;
	@ViewInject(R.id.gv_food_type)
	private GridView gv_food_type;
	@ViewInject(R.id.gv_other_type)
	private GridView gv_other_type;
	/**menu moren*/
	@ViewInject(R.id.lv_more_type)
	private PinnedHeaderListView lv_more_type;

	private MenuDrawer mMenuDrawer;
	private HomeFragmentAdapter homeFragmentAdapter;
	private int currentPosition;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		sp = getSharedPreferences(Configure.SP_CONFIGURE, MODE_PRIVATE);
		currentPosition = sp.getInt("homePosition", Configure.HOME_MAP_FRAGMENT);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setLayout() {
		super.setLayout();
		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, Position.RIGHT, MenuDrawer.MENU_DRAG_WINDOW);
		mMenuDrawer.setContentView(R.layout.il_home);
		mMenuDrawer.setMenuView(R.layout.il_home_right_menu);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 保存上次的浏览项
		Editor edit = sp.edit();
		edit.putInt("homePosition", currentPosition);
		edit.commit();
	}

	@Override
	protected void init() {
		super.init();
		initMenu();
		homeFragmentAdapter = new HomeFragmentAdapter(getSupportFragmentManager());
		// 默认是打开地图，后续的打开保存的上次操作记录
		switchFragment(currentPosition);
	}

	private void initMenu() {
		initMenuSeekBar();
		initMenuType();
		initMenuMoreType();
	}

	private void initMenuMoreType() {
		List<MenuTypeTitle> data = new ArrayList<MenuTypeTitle>();
		List<MenuType> chainFood = new ArrayList<MenuType>();
		List<MenuType> foodType = new ArrayList<MenuType>();
		List<MenuType> otherFood = new ArrayList<MenuType>();
		List<MenuType> candies = new ArrayList<MenuType>();
		//中式料理
		chainFood.add(new MenuType("北方菜", true));
		chainFood.add(new MenuType("川菜", false));
		chainFood.add(new MenuType("港台菜", true));
		chainFood.add(new MenuType("粤菜", false));
		chainFood.add(new MenuType("苏菜", false));
		chainFood.add(new MenuType("沪菜", true));
		chainFood.add(new MenuType("湘菜", false));
		chainFood.add(new MenuType("家庭厨房", true));
		data.add(new MenuTypeTitle("中式料理", chainFood,"(点击菜系名称进行筛选)",4));
		//料理方法
		foodType.add(new MenuType("火锅", false));
		foodType.add(new MenuType("小吃", true));
		foodType.add(new MenuType("自助餐", false));
		data.add(new MenuTypeTitle("料理方法", foodType));
		//其他料理
		otherFood.add(new MenuType("韩国料理", false));
		otherFood.add(new MenuType("欧洲菜式", false));
		otherFood.add(new MenuType("日式料理", false));
		otherFood.add(new MenuType("海鲜餐厅", false));
		otherFood.add(new MenuType("北美菜式", false));
		otherFood.add(new MenuType("墨西哥餐", false));
		otherFood.add(new MenuType("希腊餐", false));
		otherFood.add(new MenuType("意大利餐", false));
		otherFood.add(new MenuType("东南亚餐", false));
		data.add(new MenuTypeTitle("其他料理", otherFood));
		//甜美及其他
		candies.add(new MenuType("甜品店", false));
		candies.add(new MenuType("其他", false));
		data.add(new MenuTypeTitle("甜品及其他", candies));
		
		HomeMenuMoreTypeAdapter adapter = new HomeMenuMoreTypeAdapter(this, data);
		lv_more_type.setAdapter(adapter);
	}

	private void initMenuType() {
		List<MenuType> foodMenus = new ArrayList<MenuType>();
		List<MenuType> otherMenus = new ArrayList<MenuType>();
		// food type
		foodMenus.add(new MenuType("北方菜", true));
		foodMenus.add(new MenuType("川菜", false));
		foodMenus.add(new MenuType("火锅", false));
		foodMenus.add(new MenuType("港台菜", false));
		foodMenus.add(new MenuType("粤菜", true));
		foodMenus.add(new MenuType("苏菜", false));
		// other type
		otherMenus.add(new MenuType("网络", false));
		otherMenus.add(new MenuType("外带", false));
		otherMenus.add(new MenuType("送餐", true));
		otherMenus.add(new MenuType("能喝酒", false));
		otherMenus.add(new MenuType("包厢", false));
		otherMenus.add(new MenuType("室外", false));
		otherMenus.add(new MenuType("正在营业", false));
		otherMenus.add(new MenuType("最新添加", false));
		gv_food_type.setAdapter(new HomeMenuTypeAdapter(this, foodMenus));
		gv_other_type.setAdapter(new HomeMenuTypeAdapter(this, otherMenus));
	}

	private void initMenuSeekBar() {
		int minPrice = 0;
		int maxPrice = 65;
		RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(minPrice, maxPrice, this);
		tv_min_price.setText("$" + minPrice);
		tv_max_price.setText("$" + maxPrice);
		seekBar.setOnRangeSeekBarChangeListener(new OnRangeSeekBarChangeListener<Integer>() {

			@Override
			public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
				tv_min_price.setText("$" + minValue);
				tv_max_price.setText("$" + maxValue);
			}
		});
		ll_range_price.addView(seekBar);
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		super.loadData();
	}

	/**
	 * 切换home fragment
	 * 
	 * @param position
	 */
	public Fragment switchFragment(int position) {
		this.currentPosition = position;
		Fragment fragment = (Fragment) homeFragmentAdapter.instantiateItem(fl_primary, position);
		homeFragmentAdapter.setPrimaryItem(fl_primary, position, fragment);
		homeFragmentAdapter.finishUpdate(fl_primary);

		showMenuSelect(position);
		return fragment;
	}

	/**
	 * 显示选中的icon
	 * 
	 * @param position
	 */
	private void showMenuSelect(int position) {
		switch (position) {
		case Configure.HOME_LIST_FRAGMENT:
			iv_list.setSelected(true);
			iv_map.setSelected(false);
			iv_collect.setSelected(false);
			break;
		case Configure.HOME_MAP_FRAGMENT:
			iv_list.setSelected(false);
			iv_map.setSelected(true);
			iv_collect.setSelected(false);
			break;
		case Configure.HOME_COLLECT_FRAGMENT:
			iv_list.setSelected(false);
			iv_map.setSelected(false);
			iv_collect.setSelected(true);
			break;

		default:
			break;
		}
	}

	/**
	 * 筛选的菜单左右切换
	 * 
	 * @param isSwitchMore
	 */
	private void switchMenuChoice(boolean isSwitchMore) {
		//TODO 动画过度
		if(isSwitchMore){
			Animation leftInAnimation = AnimationUtils.loadAnimation(this, R.anim.trans_right_in);
			rl_menu_right.clearAnimation();
			rl_menu_right.startAnimation(leftInAnimation);
			Animation rightOutAnimation = AnimationUtils.loadAnimation(this, R.anim.trans_left_out);
			rightOutAnimation.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					rl_menu_right.setVisibility(View.INVISIBLE);
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					rl_menu_left.setVisibility(View.GONE);
					rl_menu_right.setVisibility(View.VISIBLE);
				}
			});
			rl_menu_left.clearAnimation();
			rl_menu_left.startAnimation(rightOutAnimation);
		}else{
			Animation leftInAnimation = AnimationUtils.loadAnimation(this, R.anim.trans_left_in);
			rl_menu_left.clearAnimation();
			rl_menu_left.startAnimation(leftInAnimation);
			Animation rightOutAnimation = AnimationUtils.loadAnimation(this, R.anim.trans_right_out);
			rightOutAnimation.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					rl_menu_left.setVisibility(View.INVISIBLE);
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					rl_menu_left.setVisibility(View.VISIBLE);
					rl_menu_right.setVisibility(View.GONE);
				}
			});
			rl_menu_right.clearAnimation();
			rl_menu_right.startAnimation(rightOutAnimation);
			
		}
	}

	@OnClick(R.id.tab_list)
	public void clickSwitchList(View v) {
		switchFragment(Configure.HOME_LIST_FRAGMENT);
	}

	@OnClick(R.id.tab_map)
	public void clickSwitchMap(View v) {
		switchFragment(Configure.HOME_MAP_FRAGMENT);

	}

	@OnClick(R.id.tab_collect)
	public void clickSwitchCollect(View v) {
		switchFragment(Configure.HOME_COLLECT_FRAGMENT);

	}

	@OnClick(R.id.iv_home_right_menu)
	public void clickMore(View v) {
		mMenuDrawer.toggleMenu();
	}

	@OnClick(R.id.iv_home_search)
	public void clickTitleSearch(View v) {
		Intent intent = new Intent(this, SearchActivity.class);
		startActivity(intent);
	}

	// －－－－menu click ----
	@OnItemClick(R.id.gv_food_type)
	public void clickMenuFoodTypeItem(AdapterView<?> parent, View view, int position, long id) {
		HomeMenuTypeAdapter adapter = (HomeMenuTypeAdapter) gv_food_type.getAdapter();
		MenuType menuType = adapter.getData().get(position);
		menuType.setSelect(!menuType.isSelect());
		view.findViewWithTag("title_" + position).setSelected(menuType.isSelect());
	}

	@OnItemClick(R.id.gv_other_type)
	public void clickMenuOtherTypeItem(AdapterView<?> parent, View view, int position, long id) {
		HomeMenuTypeAdapter adapter = (HomeMenuTypeAdapter) gv_other_type.getAdapter();
		MenuType menuType = adapter.getData().get(position);
		menuType.setSelect(!menuType.isSelect());
		view.findViewWithTag("title_" + position).setSelected(menuType.isSelect());
	}

	@OnClick(R.id.iv_choice_more)
	public void clickMenuChoiceMore(View v) {
		switchMenuChoice(true);
	}
	
	//----menu more choice
	@OnClick(R.id.iv_choice_more_back)
	public void clickMenuChoicMoreBacl(View v){
		switchMenuChoice(false);
	}
	// －－－－menu click end----

}
