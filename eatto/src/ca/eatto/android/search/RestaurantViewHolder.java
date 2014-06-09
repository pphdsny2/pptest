package ca.eatto.android.search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import ca.eatto.android.R;
import ca.eatto.android.models.Restaurant;

public class RestaurantViewHolder {

	private Context context;

	private ImageView iv_icon;
	private TextView tv_name;
	private TextView tv_location;
	private TextView tv_detail_desc;
	private RatingBar rb_star;

	private Drawable defaultImageDrawable;

	public RestaurantViewHolder(View view, Context context) {
		this.context = context;
		rb_star = (RatingBar) view.findViewById(R.id.rb_star);
		iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
		tv_name = (TextView) view.findViewById(R.id.tv_name);
		tv_location = (TextView) view.findViewById(R.id.tv_location);
		tv_detail_desc = (TextView) view.findViewById(R.id.tv_detail_desc);
	}

	public void build(Restaurant restaurant) {
		tv_name.setText(restaurant.getName());
		tv_location.setText(restaurant.getLocation() + "KM");
		tv_detail_desc.setText(context.getResources().getString(R.string.note_restaurant_desc, restaurant.getType(), restaurant.getMoney()));
		// TODO 加载图片
		if (TextUtils.isEmpty(restaurant.getImage())) {
			iv_icon.setImageDrawable(defaultImageDrawable);
		}
		rb_star.setRating(restaurant.getSource());
	}

	public Drawable getDefaultImageDrawable() {
		return defaultImageDrawable;
	}

	public void setDefaultImageDrawable(Drawable defaultImageDrawable) {
		this.defaultImageDrawable = defaultImageDrawable;
	}

}
