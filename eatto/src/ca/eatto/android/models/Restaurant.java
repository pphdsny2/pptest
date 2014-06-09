package ca.eatto.android.models;

public class Restaurant {

	private int id;
	private String name;
	private String location;
	private int source;
	private String type;
	private String money;
	private String image;

	public Restaurant(String name, String location, String type, String money,int source) {
		super();
		this.name = name;
		this.location = location;
		this.type = type;
		this.money = money;
		this.source = source;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
