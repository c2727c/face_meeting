package cn.drrs.face_meeting.service;

import java.util.List;

import cn.drrs.face_meeting.entity.Meter;
import cn.drrs.face_meeting.entity.ResponseData;

public interface MeterService {

	void addBathMeter(List<Meter> meterList);

	ResponseData queryData(int page, int limit);
	
}
