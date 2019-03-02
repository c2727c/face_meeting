package cn.drrs.face_meeting.entity;

import java.util.List;

public class Group {
	

	private String gId;
	private String gInfo;
	
	//TODO 集合映射
	private List<Person> memberList;
	
	
	public Group(String gId, String gInfo) {
		super();
		this.gId=gId;
		this.gInfo = gInfo;
	}


	public String getgInfo() {
		return gInfo;
	}

	public void setgInfo(String gInfo) {
		this.gInfo = gInfo;
	}

	public List<Person> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Person> memberList) {
		this.memberList = memberList;
	}

	@Override
	public String toString() {
		return "Group [gName=" + getgId() + ", Info=" + gInfo + ", memberList=" + memberList + "]";
	}


	public String getgId() {
		return gId;
	}


	public void setgId(String gId) {
		this.gId = gId;
	}

}
