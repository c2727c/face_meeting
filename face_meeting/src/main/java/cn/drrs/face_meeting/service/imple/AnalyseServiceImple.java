package cn.drrs.face_meeting.service.imple;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.AnalyseDao;
import cn.drrs.face_meeting.entity.dto.AvilableCount;
import cn.drrs.face_meeting.entity.dto.EChart;
import cn.drrs.face_meeting.service.AnalyseService;
import cn.drrs.face_meeting.util.NoteResult;
@Service("analyseService")
public class AnalyseServiceImple implements AnalyseService{
	@Resource
	private AnalyseDao analyseDao;
	
	
	public NoteResult<EChart> dailyAvilable2(Date date) {
		List<AvilableCount> lav;
		lav = analyseDao.dailyAvilable2(date);
		NoteResult<EChart> result = 
				new NoteResult<EChart>(0,"成功",new EChart(lav));
		return result;
	}

}
