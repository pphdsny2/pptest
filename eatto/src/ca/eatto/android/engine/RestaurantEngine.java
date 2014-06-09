package ca.eatto.android.engine;

import java.util.ArrayList;
import java.util.List;

import ca.eatto.android.models.Restaurant;
import ca.eatto.android.models.RestaurantDiscuss;

public class RestaurantEngine {

	/**
	 * 根据ID获取餐厅详情信息
	 * @param id
	 * @return
	 */
	public Restaurant getRestaurantDetail(int id){
		return new Restaurant("", "", "", "", 4);
	}
	
	public List<RestaurantDiscuss> getRestaurantDiscuss(){
		List<RestaurantDiscuss> list = new ArrayList<RestaurantDiscuss>();
		for (int i = 0; i < 9; i++) {
			RestaurantDiscuss restaurantDiscuss = new RestaurantDiscuss();
			restaurantDiscuss.setName("user"+i);
			restaurantDiscuss.setType("美食家");
			restaurantDiscuss.setContent("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"+i);
			list.add(restaurantDiscuss);
		}
		return list;
	}
}
