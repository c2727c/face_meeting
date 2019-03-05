package cn.drrs.face_meeting.entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class RoomRestrict {
	public RoomRestrict() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RoomRestrict [mSize=" + mSize + ", tName=" + tName + ", startDate=" + startDate + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}
	private int mSize;
	private String tName;
	private LocalDate startDate;
	private LocalTime startTime;
	private LocalTime endTime;
	public RoomRestrict(Meeting m, LocalDate startDate, LocalTime startTime) {
		this.mSize=m.getmSize();
		this.tName=m.gettName();
		this.startDate=startDate;
		this.startTime=startTime;
		this.endTime = startTime.plus(65, ChronoUnit.MINUTES);
	}
	public RoomRestrict(Meeting m, LocalTime startTime,int minuteSpan) {
		this.mSize=m.getmSize();
		this.tName=m.gettName();
		this.startDate=LocalDate.now();
		this.startTime=startTime;
		this.endTime = startTime.plus(minuteSpan, ChronoUnit.MINUTES);
	}
	public int getmSize() {
		return mSize;
	}
	public void setmSize(int mSize) {
		this.mSize = mSize;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
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


}
