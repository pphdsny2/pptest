package ca.eatto.android.models;

import java.util.List;

public class MenuTypeTitle {

	private String title;
	private List<MenuType> data;
	private String note;
	private int numCount;

	public MenuTypeTitle(String title, List<MenuType> data) {
		super();
		this.title = title;
		this.data = data;
	}

	public MenuTypeTitle(String title, List<MenuType> data, String note, int numCount) {
		super();
		this.title = title;
		this.data = data;
		this.note = note;
		this.numCount = numCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<MenuType> getData() {
		return data;
	}

	public void setData(List<MenuType> data) {
		this.data = data;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getNumCount() {
		return numCount;
	}

	public void setNumCount(int numCount) {
		this.numCount = numCount;
	}

}
