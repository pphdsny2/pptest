package ca.eatto.android.home.list;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ca.eatto.android.R;
import ca.eatto.android.models.Restaurant;

public class HomeListAdapter extends BaseAdapter {

	private Context context;
	private List<Restaurant> data;
	private LayoutInflater mInflater;
	private BitmapDrawable defaultImageDrawable;

	public HomeListAdapter(Context context, List<Restaurant> data) {
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

		ViewHolder holder = getHolder(convertView);
		Restaurant restaurant = data.get(position);

		holder.build(restaurant);
		return convertView;
	}

	private ViewHolder getHolder(View view) {
		ViewHolder holder = (ViewHolder) view.getTag();
		if (holder == null) {
			holder = new ViewHolder(view);
			view.setTag(holder);
		}
		return holder;
	}

	private class ViewHolder {

		private ImageView iv_icon;
		private TextView tv_name;
		private TextView tv_location;
		private TextView tv_detail_desc;

		public ViewHolder(View view) {
			iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
			tv_name = (TextView) view.findViewById(R.id.tv_name);
			tv_location = (TextView) view.findViewById(R.id.tv_location);
			tv_detail_desc = (TextView) view.findViewById(R.id.tv_detail_desc);
		}

		public void build(Restaurant restaurant) {
			tv_name.setText(restaurant.getName());
			tv_location.setText(restaurant.getLocation()+"KM");
			tv_detail_desc.setText(context.getResources().getString(R.string.note_restaurant_desc, restaurant.getType(),restaurant.getMoney()));
			//TODO 加载图片
			if(TextUtils.isEmpty(restaurant.getImage())){
				iv_icon.setImageDrawable(defaultImageDrawable);
			}
		}

	}

}
