package cn.drrs.face_meeting.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import cn.drrs.face_meeting.entity.dto.AvilableCount;
import cn.drrs.face_meeting.entity.dto.EChart;

import java.sql.Date;
import java.sql.Time;

public interface AnalyseDao {

//	@MapKey("count_point")
//	public Map<Time,Integer> dailyAvilable(Date day);
	
	public List<AvilableCount> dailyAvilable2(Date day);
	
//	@MapKey("count_point")
//	public int dailyAvilable3(Date day);
	
}
