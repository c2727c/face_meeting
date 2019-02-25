package cn.drrs.face_meeting.entity;

import java.io.Serializable;

public class RoomTest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rId;
	private String rName;
	private String rInfo;
	private int rSize;
	private String rAddr = "3Floor";
	private String tName;
	
	public RoomTest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
    public String toString() {
        return "Room{" +
                "rId='" + rId + '\'' +
                ", rName='" + rName + '\'' +
                ", rInfo='" + rInfo + '\'' +
                ", rSize='" + rSize + '\'' +
                ", rAddr=" + rAddr +
                ", tName='" + tName + '\'' +
                '}';
    }
	public RoomTest(String rId, String rName, String rInfo, int rSize, String rAddr, String tName) {
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
	public String getrAddr() {
		return rAddr;
	}
	public void setrAddr(String rAddr) {
		this.rAddr = rAddr;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	
}
