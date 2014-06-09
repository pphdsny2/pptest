package ca.eatto.android.models;

public class MenuType {

	public String name;
	private boolean isSelect;

	public MenuType(String name, boolean isSelect) {
		super();
		this.name = name;
		this.isSelect = isSelect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

}
