package cn.drrs.face_meeting.entity;

public class Attend {
	
	private int mNo;
	private String pId;
	private String state;
	public Attend(int mNo, String pId, String state) {
		super();
		this.mNo = mNo;
		this.pId = pId;
		this.state = state;
	}
	
	public int getmNo() {
		return mNo;
	}
	public void setmNo(int mNo) {
		this.mNo = mNo;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	

}
