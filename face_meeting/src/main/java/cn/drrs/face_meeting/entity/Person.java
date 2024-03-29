package cn.drrs.face_meeting.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 参照数据库中的表定义
 * 属性名于字段名保持一致
 * 属性类型与字段类型保持一致
 * 实现序列化
 */

@JsonIgnoreProperties(value={"pMeetingList","pAttendMeetingList","pInformMeetingList"})
public class Person implements Serializable {
	private String pId = null;
	private String pPass = null;
	private String pName = null;
	private String pGender = null;
	private String pRole = null;
	private String pDept = null;
	private int pPrivilege = -1;
	private byte[] pFace = null;
	private byte[] pIcon = null;
	private String pTel = null;

	// 集合映射
	private List<Meeting> pMeetingList;
	private List<Meeting> pAttendMeetingList;
	private List<Meeting> pInformMeetingList;
	// FIXME 集合映射Group

	public Person() {
		super();
	}

	public Person(String pId, String pPass, String pName) {
		super();
		this.pId = pId;
		this.pPass = pPass;
		this.pName = pName;
	}

	public Person(String pId, String pPass) {
		super();
		this.pId = pId;
		this.pPass = pPass;
		this.pName = "--";
	}

	

	@Override
	public String toString() {
		return "Person [pId=" + pId + ", pPass=" + pPass + ", pName=" + pName + ", pGender=" + pGender + ", pRole="
				+ pRole + ", pDept=" + pDept + ", pPrivilege=" + pPrivilege + ", pFace=" + Arrays.toString(pFace)
				+ ", pIcon=" + Arrays.toString(pIcon) + ", pTel=" + pTel + ", pMeetingList=" + pMeetingList
				+ ", pAttendMeetingList=" + pAttendMeetingList + ", pInformMeetingList=" + pInformMeetingList + "]";
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
		if (pMeetingList == null) {
			pMeetingList = new ArrayList<Meeting>();
		}
		return pMeetingList;
	}

	public void setMeetings(List<Meeting> meetings) {
		this.pMeetingList = meetings;
	}

	public List<Meeting> getpAttendMeetingList() {
		if (pAttendMeetingList == null) {
			pAttendMeetingList = new ArrayList<Meeting>();
		}
		return pAttendMeetingList;
	}

	public void setpAttendMeetingList(List<Meeting> pAttendMeetingList) {
		this.pAttendMeetingList = pAttendMeetingList;
	}

	public List<Meeting> getpInformMeetingList() {
		if (pInformMeetingList == null) {
			pInformMeetingList = new ArrayList<Meeting>();
		}
		return pInformMeetingList;
	}

	public void setpInformMeetingList(List<Meeting> pInformMeetingList) {
		this.pInformMeetingList = pInformMeetingList;
	}

	public byte[] getpFace() {
		return pFace;
	}

	public void setpFace(byte[] face) {
		this.pFace = face;
	}

	public byte[] getpIcon() {
		return pIcon;
	}

	public void setpIcon(byte[] pIcon) {
		this.pIcon = pIcon;
	}

	public String getpTel() {
		return pTel;
	}

	public void setpTel(String pTel) {
		this.pTel = pTel;
	}



}
