package ca.eatto.android.search.history;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import ca.eatto.android.R;
import ca.eatto.android.models.OperateHistory;
import ca.eatto.android.models.OperateHistoryTitle;
import ca.eatto.android.utils.PromptManager;
import ca.eatto.android.views.SectionedBaseAdapter;

public class SearchHistoryAdapter extends SectionedBaseAdapter {

	private Context context;
	private List<OperateHistoryTitle> data;
	private LayoutInflater mInflater;

	public SearchHistoryAdapter(Context context, List<OperateHistoryTitle> data) {
		this.context = context;
		this.data = data;
		mInflater = LayoutInflater.from(context);
	}

	public List<OperateHistoryTitle> getData() {
		return data;
	}

	@Override
	public Object getItem(int section, int position) {
		return data.get(section).getHistorys().get(position);
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
		return data.get(section).getHistorys().size();
	}

	@Override
	public View getItemView(int section, int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.il_search_history_content_item, null);
		}

		ViewHolder holder = getHolder(convertView);
		OperateHistory history = data.get(section).getHistorys().get(position);

		holder.build(history);
		return convertView;

	}

	@Override
	public View getSectionHeaderView(final int section, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflator.inflate(R.layout.il_search_history_title_item, null);
		}
		TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
		View iv_history_delete = convertView.findViewById(R.id.iv_history_delete);
		iv_history_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (section) {
				case 0://搜索记录
					PromptManager.showToast(context, "delete search history");
					break;
				case 1://浏览记录
					PromptManager.showToast(context, "delete look history");
					break;
				}
			}
		});
		tv_title.setText(data.get(section).getTitle());
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
	
	private class ViewHolder{

		private TextView tv_content;

		public ViewHolder(View view) {
			tv_content = (TextView) view.findViewById(R.id.tv_content);
		}
		
		public void build(OperateHistory history){
			tv_content.setText(history.getName());
		}
		
	}
}
