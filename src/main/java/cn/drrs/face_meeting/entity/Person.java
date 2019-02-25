package cn.drrs.face_meeting.entity;

import java.io.Serializable;
import java.util.List;

/*
 * �������ݿ��еı���
 * ���������ֶ�������һ��
 * �����������ֶ����ͱ���һ��
 * ʵ�����л�
 */
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pId=null;
	private String pPass=null;
	private String pName=null;
	private String pGender=null;
	private String pRole=null;
	private String pDept=null;
	private String pPhone=null;
	private String pEmail=null;
	private int pPrivilege=-1;
	private String pPictPath="0";
	//����ӳ��
	private List<Meeting>	pMeetingList;
	private List<Meeting>	pAttendMeetingList;
	private List<Meeting>	pInformMeetingList;
	//FIXME ����ӳ��Group  
	
	public Person() {
		
	}
	
	
	@Override
	public String toString() {
		return "Personel [pId=" + pId + ", pPass=" + pPass + ", pName=" + pName + ", pGender=" + pGender + ", pRole="
				+ pRole + ", pDept=" + pDept + ", pPhone=" + pPhone + ", pEmail=" + pEmail + ", pPrivilege="
				+ pPrivilege + ", pPictPath=" + pPictPath + "]";
	}
	
	public String getpPhone() {
		return pPhone;
	}


	public void setpPhone(String pPhone) {
		this.pPhone = pPhone;
	}


	public String getpEmail() {
		return pEmail;
	}


	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}


	public String getpPictPath() {
		return pPictPath;
	}


	public void setpPictPath(String pPictPath) {
		this.pPictPath = pPictPath;
	}


	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getpPass() {
		return pPass;
	}
	public void setpPass(String pPass) {
		this.pPass = pPass;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpGender() {
		return pGender;
	}
	public void setpGender(String pGender) {
		this.pGender = pGender;
	}
	public String getpRole() {
		return pRole;
	}
	public void setpRole(String pRole) {
		this.pRole = pRole;
	}
	public String getpDept() {
		return pDept;
	}
	public void setpDept(String pDept) {
		this.pDept = pDept;
	}
	public int getpPrivilege() {
		return pPrivilege;
	}
	public void setpPrivilege(int pPrivilege) {
		this.pPrivilege = pPrivilege;
	}
	public List<Meeting> getMeetings() {
		return pMeetingList;
	}
	public void setMeetings(List<Meeting> meetings) {
		this.pMeetingList = meetings;
	}
	public List<Meeting> getpAttendMeetingList() {
		return pAttendMeetingList;
	}
	public void setpAttendMeetingList(List<Meeting> pAttendMeetingList) {
		this.pAttendMeetingList = pAttendMeetingList;
	}
	public List<Meeting> getpInformMeetingList() {
		return pInformMeetingList;
	}
	public void setpInformMeetingList(List<Meeting> pInformMeetingList) {
		this.pInformMeetingList = pInformMeetingList;
	}
	
	

}
