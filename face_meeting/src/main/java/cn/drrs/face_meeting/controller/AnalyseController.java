package cn.drrs.face_meeting.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.drrs.face_meeting.service.AnalyseService;
import cn.drrs.face_meeting.util.NoteResult;

@Controller	//加入到扫描当中
@RequestMapping("/analyse")	//匹配请求路径
public class AnalyseController {
	
//	@Resource
//	private AnalyseService analyseService;
//	public NoteResult<Map<Time,Integer>> dailyAvilable(String dateInString){
//		NoteResult<Map<Time,Integer>> result;
//		Date date = Date.valueOf(dateInString); 
//		result = analyseService.dailyAvilable(date);
//		return result;
//	}

}
