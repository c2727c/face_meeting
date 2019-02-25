package cn.drrs.face_meeting.entity;

public class PGJoin {
	private String gId;
	private String pId;
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public PGJoin(String gId, String pId) {
		super();
		this.gId = gId;
		this.pId = pId;
	}


}
