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

public class PersonLight implements Serializable {
	private String pId = null;
	private String pName = null;
	private String pRole = null;
	private String pDept = null;
	private byte[] pIcon = null;
	
	public PersonLight() {
		super();
	}
	public PersonLight(String pId, String pPass) {
		super();
		this.pId = pId;
		this.setpName("--");
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
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
	public byte[] getpIcon() {
		return pIcon;
	}
	public void setpIcon(byte[] pIcon) {
		this.pIcon = pIcon;
	}



}
