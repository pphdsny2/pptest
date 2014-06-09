package ca.eatto.android.models;

public class OperateHistory {
	
	public static final int STATUS_SEARCH_HISTORY = 1;
	public static final int STATUS_LOOK_HISTORY = 2;

	private int id;
	private String name;
	private long operateDate;
	private int status;

	public OperateHistory(int id, String name, long operateDate, int status) {
		super();
		this.id = id;
		this.name = name;
		this.operateDate = operateDate;
		this.status = status;
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

	public long getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(long operateDate) {
		this.operateDate = operateDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
