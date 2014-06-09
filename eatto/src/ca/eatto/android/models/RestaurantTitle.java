package ca.eatto.android.models;

import java.util.List;

public class RestaurantTitle {

	private String title;
	private List<Restaurant> restaurants;

	public RestaurantTitle(String title, List<Restaurant> restaurants) {
		super();
		this.title = title;
		this.restaurants = restaurants;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

}
