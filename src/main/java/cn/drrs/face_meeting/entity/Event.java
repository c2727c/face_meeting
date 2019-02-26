package cn.drrs.face_meeting.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;

public class Event {
	
	private int mNo;
	private String rId;
	private LocalDate startDate;
	private LocalTime startTime;
	private LocalTime endTime;
	
	private Meeting meeting;//DONE Eventµ½Meeting¹ØÁªÓ³Éä
	
	
	
	public Event(int mNo, String rId, LocalDate startDate, LocalTime startTime, LocalTime endTime) {
		super();
		this.mNo = mNo;
		this.rId = rId;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public Event() {
		super();
	}
	public int getmNo() {
		return mNo;
	}
	public void setmNo(int mNo) {
		this.mNo = mNo;
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "Event [mNo=" + mNo + ", rId=" + rId + ", startDate=" + startDate + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
	public Meeting getMeeting() {
		return meeting;
	}
	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
}
