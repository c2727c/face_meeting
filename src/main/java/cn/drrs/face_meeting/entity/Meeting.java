package cn.drrs.face_meeting.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;



public class Meeting {
	private int mNo=-1;/*Ψһ���������ɴ�������Ⱥ�˳��*/
	private String mTitle=null;
	private String mInfo=null;
	private int mSize=-1;;/*��������*/
	private int mSpan=-1;/*ʱ��,������*/
	private String tName=null;/*������Ϣ*/
	private String pId_FQ=null;/*������*/
	private	String state=null;
	private Timestamp mCreateTime=null;
	
	
	//����ӳ��
	private	List<Event> mEventList; 
	private	List<Person> mAttendList;
	private	List<Person> mInformList;
	//DONE �λ�&��������ӳ��
	
	public Meeting() {
		
	}
	//���ڸ���
	public Meeting(int mNo, String mTitle, String mInfo, int mSize,int mSpan,String tName) {
		super();
		this.mNo = mNo;
		this.mTitle = mTitle;
		this.mInfo = mInfo;
		this.mSize = mSize;
		this.mSpan = mSpan;
		this.tName = tName;
	}
	public int getmNo() {
		return mNo;
	}
	public void setmNo(int mNo) {
		this.mNo = mNo;
	}
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public String getmInfo() {
		return mInfo;
	}
	public void setmInfo(String mInfo) {
		this.mInfo = mInfo;
	}
	public int getmSize() {
		return mSize;
	}
	public void setmSize(int mSize) {
		this.mSize = mSize;
	}
	public int getmSpan() {
		return mSpan;
	}
	public void setmSpan(int mSpan) {
		this.mSpan = mSpan;
	}

	public String getpId_FQ() {
		return pId_FQ;
	}
	public void setpId_FQ(String pId_FQ) {
		this.pId_FQ = pId_FQ;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Timestamp getmCreateTime() {
		return mCreateTime;
	}
	public void setmCreateTime(Timestamp mCreateTime) {
		this.mCreateTime = mCreateTime;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public List<Event> getmEventList() {
		if(mEventList!=null) {
			for(Event e: mEventList) {
				if(e.getrId()==null) {
					mEventList.remove(e);
					System.out.println("remove");
					return mEventList;
				}
			}
		}
		return mEventList;
	}
	public void setmEventList(List<Event> mEventList) {
		/*if(mEventList!=null)System.out.println("setmEventList"+mEventList.toString());
		for(Event e: mEventList) {
			if(e.getrId()==null) {
				mEventList.remove(0);
				System.out.println("remove");
			}
			System.out.println("for");
		}*/
		this.mEventList = mEventList;
	}
	public List<Person> getmAttendList() {
		return mAttendList;
	}
	public void setmAttendList(List<Person> mAttendList) {
		this.mAttendList = mAttendList;
	}
	public List<Person> getmInformList() {
		return mInformList;
	}
	public void setmInformList(List<Person> mInformList) {
		this.mInformList = mInformList;
	} 
	

}
