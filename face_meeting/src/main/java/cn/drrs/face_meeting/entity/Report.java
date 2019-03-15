package cn.drrs.face_meeting.entity;

import java.util.List;

public class Report {
	
	private int mNo;
	private int noshowNum;
	private int checkedinNum;
	private int canceledNum;
	private List<PersonLight> noshow;
	private List<PersonLight> canceled;
	private List<PersonLight> checkedin;
	public int getmNo() {
		return mNo;
	}
	public void setmNo(int mNo) {
		this.mNo = mNo;
	}
	public int getNoshowNum() {
		return noshowNum;
	}
	public void setNoshowNum(int noshowNum) {
		this.noshowNum = noshowNum;
	}
	public int getCheckedinNum() {
		return checkedinNum;
	}
	public void setCheckedinNum(int checkedinNum) {
		this.checkedinNum = checkedinNum;
	}
	public int getCanceledNum() {
		return canceledNum;
	}
	public void setCanceledNum(int canceledNum) {
		this.canceledNum = canceledNum;
	}
	public List<PersonLight> getNoshow() {
		return noshow;
	}
	public void setNoshow(List<PersonLight> noshow) {
		this.noshow = noshow;
	}
	public List<PersonLight> getCanceled() {
		return canceled;
	}
	public void setCanceled(List<PersonLight> canceled) {
		this.canceled = canceled;
	}
	public List<PersonLight> getCheckedin() {
		return checkedin;
	}
	public void setCheckedin(List<PersonLight> checkedin) {
		this.checkedin = checkedin;
	}
	

}
