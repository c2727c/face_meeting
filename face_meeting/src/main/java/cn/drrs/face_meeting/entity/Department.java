package cn.drrs.face_meeting.entity;

import java.util.List;

public class Department {
	private String deptName;
	private List<PersonLight> memberList;
	public Department(String deptName, List<PersonLight> memberList) {
		super();
		this.deptName = deptName;
		this.memberList = memberList;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public List<PersonLight> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<PersonLight> memberList) {
		this.memberList = memberList;
	}

}
