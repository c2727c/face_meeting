package cn.drrs.face_meeting.service.imple;

import java.sql.Date;
import java.sql.Time;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.AnalyseDao;
import cn.drrs.face_meeting.service.AnalyseService;
import cn.drrs.face_meeting.util.NoteResult;
@Service("analyseService")
public class AnalyseServiceImple implements AnalyseService{
	@Resource
	private AnalyseDao analyseDao;
	
	public NoteResult<Map<Time, Integer>> dailyAvilable(Date date) {
		Map<Time, Integer> map;
		map = analyseDao.dailyAvilable(date);
		NoteResult<Map<Time, Integer>> result = 
				new NoteResult<Map<Time, Integer>>(0,"成功",map);
		return result;
	}

}
