package ca.eatto.android.home.menu;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import ca.eatto.android.R;
import ca.eatto.android.models.MenuType;
import ca.eatto.android.models.MenuTypeTitle;
import ca.eatto.android.views.SectionedBaseAdapter;

public class HomeMenuMoreTypeAdapter extends SectionedBaseAdapter {

	private Context context;
	private List<MenuTypeTitle> data;
	private LayoutInflater mInflater;

	public HomeMenuMoreTypeAdapter(Context context, List<MenuTypeTitle> data) {
		this.context = context;
		this.data = data;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public Object getItem(int section, int position) {
		return null;
	}

	@Override
	public long getItemId(int section, int position) {
		return 0;
	}

	@Override
	public int getSectionCount() {
		return data.size();
	}

	@Override
	public int getCountForSection(int section) {
		return 1;
	}

	@Override
	public View getItemView(final int section, int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.il_home_menu_more_content_item, null);
		}
		GridView gv_type = (GridView) convertView.findViewById(R.id.gv_type);
		gv_type.setAdapter(new HomeMenuTypeAdapter(context, data.get(section).getData()));
		if (data.get(section).getNumCount() != 0) {
			gv_type.setNumColumns(data.get(section).getNumCount());
		}
		gv_type.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				MenuType menuType = data.get(section).getData().get(position);
				menuType.setSelect(!menuType.isSelect());
				view.findViewWithTag("title_" + position).setSelected(menuType.isSelect());
			}
		});
		return convertView;
	}

	@Override
	public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.il_home_menu_more_title_item, null);
		}
		TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
		TextView tv_note = (TextView) convertView.findViewById(R.id.tv_note);
		tv_title.setText(data.get(section).getTitle());
		if (TextUtils.isEmpty(data.get(section).getNote())) {
			tv_note.setText("");
		} else {
			tv_note.setText(data.get(section).getNote());
		}
		return convertView;
	}

}
