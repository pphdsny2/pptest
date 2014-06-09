package ca.eatto.android.search.comment;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ca.eatto.android.R;
import ca.eatto.android.models.Restaurant;
import ca.eatto.android.models.RestaurantTitle;
import ca.eatto.android.search.RestaurantViewHolder;
import ca.eatto.android.views.SectionedBaseAdapter;

public class SearchCommentAdapter extends SectionedBaseAdapter {

	private Context context;
	private List<RestaurantTitle> data;
	private LayoutInflater mInflater;
	private BitmapDrawable defaultImageDrawable;

	public SearchCommentAdapter(Context context, List<RestaurantTitle> data) {
		this.context = context;
		this.data = data;
		mInflater = LayoutInflater.from(context);

		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_icon);
		defaultImageDrawable = new BitmapDrawable(context.getResources(), bitmap);
	}

	public List<RestaurantTitle> getData() {
		return data;
	}

	@Override
	public Object getItem(int section, int position) {
		return data.get(section).getRestaurants().get(position);
	}

	@Override
	public long getItemId(int section, int position) {
		return section * position;
	}

	@Override
	public int getSectionCount() {
		return data.size();
	}

	@Override
	public int getCountForSection(int section) {
		return data.get(section).getRestaurants().size();
	}

	@Override
	public View getItemView(int section, int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.il_home_list_item, null);
		}

		RestaurantViewHolder holder = getHolder(convertView);
		Restaurant restaurant = data.get(section).getRestaurants().get(position);

		holder.build(restaurant);
		return convertView;

	}

	@Override
	public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflator.inflate(R.layout.il_search_comment_title_item, null);
		}
		TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
		tv_title.setText(data.get(section).getTitle());
		return convertView;

	}


	private RestaurantViewHolder getHolder(View view) {
		RestaurantViewHolder holder = (RestaurantViewHolder) view.getTag();
		if (holder == null) {
			holder = new RestaurantViewHolder(view,context);
			holder.setDefaultImageDrawable(defaultImageDrawable);
			view.setTag(holder);
		}
		return holder;
	}
}
