package cn.drrs.face_meeting.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Room {
	
	private String rId;
	private String rName;
	private String rInfo;
	private int rSize;
	private String rAddr = "3Floor";
	private String tName;
	
	//ºØ∫œ”≥…‰
	private  List<Event> rEventList;
	
	
	@Override
	public String toString() {
		String s = "Room [rId=" + rId + ", rName=" + rName + ", rInfo=" + rInfo + ", rSize=" + rSize + ", rAddr=" + rAddr
				+ ", tName=" + tName + ", rEventList=" + rEventList + "]";
		System.out.println(s);
		return s;
	}
	
	public Room(String rId, String rName, String rInfo, int rSize, String tName) {
		super();
		this.rId = rId;
		this.rName = rName;
		this.rInfo = rInfo;
		this.rSize = rSize;
		this.tName = tName;
	}
	public Room(String rId, String rName, String rInfo,Integer rSize, String rAddr,String tName) {
		super();
		this.rId = rId;
		this.rName = rName;
		this.rInfo = rInfo;
		this.rSize = rSize;
		this.rAddr = rAddr;
		this.tName = tName;
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrInfo() {
		return rInfo;
	}
	public void setrInfo(String rInfo) {
		this.rInfo = rInfo;
	}
	public int getrSize() {
		return rSize;
	}
	public void setrSize(int rSize) {
		this.rSize = rSize;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public List<Event> getrEventList() {
		return rEventList;
	}
	public List<Event> getrEventList(Date date) {
		List<Event> list= new ArrayList<Event>();
		for(Event e:rEventList) {
			if(e.getStartDate().equals(date)) {
				list.add(e);
			}
		}
		return list;
	}
	public void setrEventList(List<Event> rEventList) {
		this.rEventList = rEventList;
	}
	public String getrAddr() {
		return rAddr;
	}
	public void setrAddr(String rAddr) {
		this.rAddr = rAddr;
	}
	

}
