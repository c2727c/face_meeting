package cn.drrs.face_meeting.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.drrs.face_meeting.entity.Meter;

@Component("MeterMapper")
public interface MeterMapper {
	// 增加客户信息
	void addBathClient(List<Meter> meterList);

	// 分页查询数据
	List<Meter> queryData(Map<String, Integer> map);

	// 查询数据总数
	int queryCount();
}
