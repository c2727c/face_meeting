package cn.drrs.face_meeting.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import java.sql.Date;
import java.sql.Time;

public interface AnalyseDao {

	@MapKey("count_point")
	public Map<Time,Integer> dailyAvilable(Date day);
}
