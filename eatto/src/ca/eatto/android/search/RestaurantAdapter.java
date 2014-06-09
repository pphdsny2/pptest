package ca.eatto.android.search;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import ca.eatto.android.R;
import ca.eatto.android.models.Restaurant;

public class RestaurantAdapter extends BaseAdapter {

	private Context context;
	private List<Restaurant> data;
	private LayoutInflater mInflater;
	private BitmapDrawable defaultImageDrawable;

	public RestaurantAdapter(Context context, List<Restaurant> data) {
		this.context = context;
		this.data = data;
		mInflater = LayoutInflater.from(context);

		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_icon);
		defaultImageDrawable = new BitmapDrawable(context.getResources(), bitmap);
	}

	public List<Restaurant> getData() {
		return data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.il_home_list_item, null);
		}

		RestaurantViewHolder holder = getHolder(convertView);
		Restaurant restaurant = data.get(position);

		holder.build(restaurant);
		return convertView;
	}

	private RestaurantViewHolder getHolder(View view) {
		RestaurantViewHolder holder = (RestaurantViewHolder) view.getTag();
		if (holder == null) {
			holder = new RestaurantViewHolder(view, context);
			holder.setDefaultImageDrawable(defaultImageDrawable);
			view.setTag(holder);
		}
		return holder;
	}

}
