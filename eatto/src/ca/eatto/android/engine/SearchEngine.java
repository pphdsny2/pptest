package ca.eatto.android.engine;

import java.util.ArrayList;
import java.util.List;

import ca.eatto.android.models.OperateHistory;
import ca.eatto.android.models.Restaurant;

public class SearchEngine {

	/**
	 * 获取搜索的结果
	 * @param searchContent 
	 * 
	 * @return
	 */
	public List<Restaurant> getSearchRestaurants(String searchContent) {
		List<Restaurant> list = new ArrayList<Restaurant>();
		for (int i = 0; i < 5; i++) {
			list.add(new Restaurant("搜索结果菜肴" + i, i % 3 + "", "赣菜", "45", i % 5));
		}
		
		return list;
	}
	/**
	 * 获取新餐厅
	 * 
	 * @return
	 */
	public List<Restaurant> getNewRestaurants() {
		List<Restaurant> list = new ArrayList<Restaurant>();
		for (int i = 0; i < 5; i++) {
			list.add(new Restaurant("菜肴故事" + i, i % 3 + "", "赣菜", "45", i % 5));
		}

		return list;
	}

	/**
	 * 获取热门搜索餐厅
	 * 
	 * @return
	 */
	public List<Restaurant> getHotSearchRestaurants() {
		List<Restaurant> list = new ArrayList<Restaurant>();
		for (int i = 0; i < 7; i++) {
			list.add(new Restaurant("湘西山寨" + i, i % 5 + "", "湘菜", "45", i % 5));
		}
		return list;
	}

	/**
	 * 获取收藏的餐厅信息
	 * 
	 * @return
	 */
	public List<Restaurant> getCollectRestaurants() {
		List<Restaurant> list = new ArrayList<Restaurant>();
		for (int i = 0; i < 9; i++) {
			list.add(new Restaurant("测试收藏湘菜" + i, i % 5 + "", "湘菜", "45", i % 6));
		}

		return list;
	}

	/**
	 * 获取热门的餐厅信息
	 * 
	 * @return
	 */
	public List<Restaurant> getHotRestaurants() {
		List<Restaurant> list = new ArrayList<Restaurant>();
		for (int i = 0; i < 9; i++) {
			list.add(new Restaurant("热门湘菜" + i, i % 5 + "", "湘菜", "45", i % 6));
		}

		return list;
	}

	/**
	 * 获取美食推荐的餐厅信息
	 * 
	 * @return
	 */
	public List<Restaurant> getFoodRestaurants() {
		List<Restaurant> list = new ArrayList<Restaurant>();
		for (int i = 0; i < 9; i++) {
			list.add(new Restaurant("美食推荐湘菜" + i, i % 5 + "", "湘菜", "45", i % 6));
		}

		return list;
	}

	/**
	 * 获取搜索的历史记录
	 * 
	 * @return
	 */
	public List<OperateHistory> getSearchHistory() {
		List<OperateHistory> list = new ArrayList<OperateHistory>();
		for (int i = 0; i < 10; i++) {
			list.add(new OperateHistory(1, "S外婆菜" + i, System.currentTimeMillis(), OperateHistory.STATUS_SEARCH_HISTORY));
		}
		return list;
	}

	/**
	 * 获取浏览的历史记录
	 * 
	 * @return
	 */
	public List<OperateHistory> getLookHistory() {
		List<OperateHistory> list = new ArrayList<OperateHistory>();
		for (int i = 0; i < 10; i++) {
			list.add(new OperateHistory(1, "L外婆菜" + i, System.currentTimeMillis(), OperateHistory.STATUS_LOOK_HISTORY));
		}
		return list;
	}

}
