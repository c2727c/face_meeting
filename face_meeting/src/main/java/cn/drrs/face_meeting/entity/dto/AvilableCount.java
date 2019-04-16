package cn.drrs.face_meeting.entity.dto;

import java.sql.Time;

public class AvilableCount {
	private Time count_point;
	private int avilable_count;
	
	AvilableCount(){
		
	}
	
	public Time getCount_point() {
		return count_point;
	}
	public void setCount_point(Time count_point) {
		this.count_point = count_point;
	}
	public int getAvilable_count() {
		return avilable_count;
	}
	public void setAvilable_count(int avilable_count) {
		this.avilable_count = avilable_count;
	}

}
