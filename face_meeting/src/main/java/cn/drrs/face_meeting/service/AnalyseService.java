package cn.drrs.face_meeting.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.entity.dto.EChart;
import cn.drrs.face_meeting.util.NoteResult;

public interface AnalyseService {
	//·查询会议室可用情况日分布（给出一个日期查询当日的会议室预约情况返回<时刻，可用会议室数>集）
	public NoteResult<Map<Time,Integer>> dailyAvilable(Date d);
	public NoteResult<EChart> dailyAvilable2(Date d);
	//·查询某日期区间内的会议室利用率
	//·根据用户已选参会人推荐分组
	// TODO 查询历史邀请用户（或按频数，最新排序后返回）
		//public NoteResult<List<Person>> findHistoryAtdBypId(String pId);
	
	//TODO 会程冲突检测功能 在预约时，已经参会人员的冲突情况检测//首先要获得所有核心人物关联的所有Event，与即将增加的时间一个个对比，看有没有冲突的，找到冲突的Event，查询该Event所关联会议所关联参会人名单，做交集


}
