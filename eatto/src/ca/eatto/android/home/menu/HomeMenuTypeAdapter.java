package ca.eatto.android.home.menu;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ca.eatto.android.R;
import ca.eatto.android.models.MenuType;

public class HomeMenuTypeAdapter extends BaseAdapter {

	private Context context;
	private List<MenuType> data;
	private LayoutInflater mInflater;

	public HomeMenuTypeAdapter(Context context, List<MenuType> data) {
		this.context = context;
		this.data = data;
		mInflater = LayoutInflater.from(context);
	}

	public List<MenuType> getData() {
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
			convertView = mInflater.inflate(R.layout.il_home_menu_type_item, null);
		}
		TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
		tv_title.setTag("title_"+position);
		MenuType menuType = data.get(position);
		tv_title.setText(menuType.getName());
		tv_title.setSelected(menuType.isSelect());
		return convertView;
	}

}
