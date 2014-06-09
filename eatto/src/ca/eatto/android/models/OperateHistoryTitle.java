package ca.eatto.android.models;

import java.util.List;

public class OperateHistoryTitle {

	private String title;
	private List<OperateHistory> historys;

	public OperateHistoryTitle(String title, List<OperateHistory> restaurants) {
		super();
		this.title = title;
		this.historys = restaurants;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<OperateHistory> getHistorys() {
		return historys;
	}

	public void setHistorys(List<OperateHistory> historys) {
		this.historys = historys;
	}

}
