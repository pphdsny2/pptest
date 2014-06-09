package ca.eatto.android.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import ca.eatto.android.Configure;
import ca.eatto.android.GlobalParams;
import ca.eatto.android.R;
import ca.eatto.android.base.BaseFragmentActivity;
import ca.eatto.android.search.comment.SearchCommentFragment;
import ca.eatto.android.utils.SoftKeyboardManager;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class SearchActivity extends BaseFragmentActivity {

	@ViewInject(R.id.ll_switch)
	private LinearLayout ll_switch;
	@ViewInject(R.id.iv_collect)
	private ImageView iv_collect;
	@ViewInject(R.id.iv_search_history)
	private ImageView iv_search_history;
	@ViewInject(R.id.iv_hot)
	private ImageView iv_hot;
	@ViewInject(R.id.iv_food)
	private ImageView iv_food;
	@ViewInject(R.id.fl_primary)
	private FrameLayout fl_primary;
	// search
	@ViewInject(R.id.et_search)
	private EditText et_search;
	@ViewInject(R.id.tv_search)
	private TextView tv_search;
	@ViewInject(R.id.iv_bg)
	private ImageView iv_bg;

	private SearchFragmentAdapter searchFragmentAdapter;
	private int currentPosition;
	private SoftKeyboardManager mSoftKeyboardManager;
	private String searchContent ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setLayout() {
		super.setLayout();
		setContentView(R.layout.il_search);
	}

	@Override
	protected void init() {
		super.init();
		initParams();
		initSearchEdit();
		searchFragmentAdapter = new SearchFragmentAdapter(getSupportFragmentManager());
		currentPosition = Configure.SEARCH_DEFAULT_FRAGMENT;
		Fragment fragment = switchFragment(currentPosition);
		if(fragment instanceof SearchCommentFragment){
			((SearchCommentFragment) fragment).setStatus(SearchCommentFragment.STATUS_LOAD_COMMENT);
		}
	}

	/**
	 * 搜索框的一些监听
	 */
	private void initSearchEdit() {
		mSoftKeyboardManager = new SoftKeyboardManager(this, et_search);
		et_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				tv_search.setText(s);
			}
		});
		et_search.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {// 修改回车键功能
					if (event.getAction() == KeyEvent.ACTION_DOWN) {
						searchContent = et_search.getText().toString();
						loadSearchResult(et_search.getText().toString()); 
						hideSearch();
					}
					return true;
				}
				return false;
			}
		});
	}

	private void initParams() {
		LayoutParams layoutParams = ll_switch.getLayoutParams();
		layoutParams.height = (GlobalParams.screenWidth - (getResources().getDimensionPixelOffset(R.dimen.search_bar_margin) * 2)) / 4;
	}


	/**
	 * 根据关键词，搜索餐厅内容
	 * @param searchContent
	 */
	public void loadSearchResult(String searchContent) {
		SearchCommentFragment fragment = (SearchCommentFragment) switchFragment(Configure.SEARCH_RESULT_FRAGMENT);
		fragment.setStatus(SearchCommentFragment.STATUS_LOAD_SEARCH_RESULT);
		fragment.setSearchContent(searchContent);
		try {
			fragment.loadSearchResult(searchContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 切换search fragment
	 * 
	 * @param position
	 */
	public Fragment switchFragment(int position) {
		this.currentPosition = position;
		Fragment fragment = (Fragment) searchFragmentAdapter.instantiateItem(fl_primary, position);
		searchFragmentAdapter.setPrimaryItem(fl_primary, position, fragment);
		searchFragmentAdapter.finishUpdate(fl_primary);

		showMenuSelect(position);
		return fragment;
	}

	private void showMenuSelect(int position) {
		switch (position) {
		case Configure.SEARCH_DEFAULT_FRAGMENT:
		case Configure.SEARCH_RESULT_FRAGMENT:
			iv_collect.setSelected(false);
			iv_search_history.setSelected(false);
			iv_hot.setSelected(false);
			iv_food.setSelected(false);
			break;
		case Configure.SEARCH_COLLECT_FRAGMENT:
			iv_collect.setSelected(true);
			iv_search_history.setSelected(false);
			iv_hot.setSelected(false);
			iv_food.setSelected(false);
			break;
		case Configure.SEARCH_HISTORY_FRAGMENT:
			iv_collect.setSelected(false);
			iv_search_history.setSelected(true);
			iv_hot.setSelected(false);
			iv_food.setSelected(false);
			break;
		case Configure.SEARCH_HOT_FRAGMENT:
			iv_collect.setSelected(false);
			iv_search_history.setSelected(false);
			iv_hot.setSelected(true);
			iv_food.setSelected(false);
			break;
		case Configure.SEARCH_FOOD_FRAGMENT:
			iv_collect.setSelected(false);
			iv_search_history.setSelected(false);
			iv_hot.setSelected(false);
			iv_food.setSelected(true);
			break;

		default:
			break;
		}
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		super.loadData();
	}

	@OnClick(R.id.iv_collect)
	public void clickSwitchCollect(View v) {
		switchFragment(Configure.SEARCH_COLLECT_FRAGMENT);
	}

	@OnClick(R.id.iv_search_history)
	public void clickSwitchSearchHistory(View v) {
		switchFragment(Configure.SEARCH_HISTORY_FRAGMENT);
	}

	@OnClick(R.id.iv_hot)
	public void clickSwitchHot(View v) {
		switchFragment(Configure.SEARCH_HOT_FRAGMENT);
	}

	@OnClick(R.id.iv_food)
	public void clickSwitchFoot(View v) {
		switchFragment(Configure.SEARCH_FOOD_FRAGMENT);
	}

	@OnClick(R.id.iv_title_back)
	public void clickBack(View v) {
		this.finish();
	}
	
	@OnClick(R.id.tv_search)
	public void clickSearch(View v){
		showSearch();
	}
	
	@OnClick(R.id.iv_bg)
	public void clickSearchBg(View v){
		hideSearch();
	}
	
	private void showSearch(){
		tv_search.setVisibility(View.GONE);
		iv_bg.setVisibility(View.VISIBLE);
		mSoftKeyboardManager.showKeyboard(et_search);
	}
	
	private void hideSearch(){
		tv_search.setVisibility(View.VISIBLE);
		iv_bg.setVisibility(View.GONE);
		mSoftKeyboardManager.hideKeyboard(et_search);
	}
	
	@Override
	public void onBackPressed() {
		if(iv_bg.getVisibility() == View.VISIBLE){
			hideSearch();
			return ;
		}
		super.onBackPressed();
	}

}
