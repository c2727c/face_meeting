package cn.drrs.face_meeting.service.imple;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arcsoft.facerecognition.AFR_FSDKEngine;
import com.arcsoft.facerecognition.AFR_FSDKFace;
import com.arcsoft.facerecognition.AFR_FSDKMatching;

import cn.drrs.face_meeting.dao.PMAttendDao;
import cn.drrs.face_meeting.dao.PersonDao;
import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.FaceService;
import cn.drrs.face_meeting.util.NoteResult;


//@Service("faceService")
public class FaceServiceImple implements FaceService {
	@Resource
	private PersonDao personDao;
	@Resource
	private PMAttendDao attendDao;
	/*//usb继电器控制类
	private static UrDllBridge usbRelay = new UrDllBridge();
	static {
		int Hdl = usbRelay.USBRELAY_Open(1);
        System.out.printf("OpenPort result = %d, 0x%x\n", Hdl, Hdl);
        if (Hdl <= 0) {
            System.out.printf("OpenPort Error\n");
        }
	}*/

	//提供人脸特征值，返回匹配用户
	public NoteResult<Person> faceRecognition(AFR_FSDKFace feature){
		AFR_FSDKEngine engine = new AFR_FSDKEngine();
		NoteResult<Person> nr = new NoteResult<Person>();
		AFR_FSDKMatching score = new AFR_FSDKMatching();
		//记录匹配度的最大值
		float max = 0.0f;
		//用来存匹配后得知的名字
		Person p = null;
		for (Person tmp : personDao.findAll()) {//获取所有用户对象
			//比较两份【人脸特征信息】的匹配度，结果保存在score里。 
			engine.AFR_FSDK_FacePairMatching(result, new AFR_FSDKFace(tmp.getpFace()), score);
			if (max < score.getScore()) {
				max = score.getScore();//更新最大值
				p = tmp;//更新认定结果
			} 
		}
		if (max > 0.6f) {
			nr.setAll(0, "匹配成功，用户id"+p.getpId()+"匹配度："+max, p);
		}else{
			nr.setAll(1,"匹配失败",null);
		}
		return nr;
	}

	public NoteResult<Object> CheckIn(Meeting m, String pId) {
		NoteResult<Object> nr = new NoteResult<Object>();
		for(Person tmp : m.getmAttendList()) {
			//验证成功
			if(tmp.getpId().equals(pId)) {
				//签到
				attendDao.update(new Attend(m.getmNo(),pId,"checkedIn"));
				//TODO 开门
				nr.setAll(0, "验证成功，已为您签到", null);
				return nr;
			}
		}
		nr.setAll(1, "验证失败，不在参会名单中", null);
		return nr;
	}
	
	
	/*//提供人脸特征值和会议信息，进行签到，返回匹配结果
	public NoteResult<Person> check(AFR_FSDKFace feature,Meeting m){
		AFR_FSDKEngine engine = new AFR_FSDKEngine();
		NoteResult<Person> nr = new NoteResult<Person>();
		AFR_FSDKMatching score = new AFR_FSDKMatching();
		//记录匹配度的最大值
		float max = 0.0f;
		//用来存匹配后得知的名字
		Person p = null;
		for (Person tmp : m.getmAttendList()) {//获取所有用户对象
			//比较两份【人脸特征信息】的匹配度，结果保存在score里。 
			engine.AFR_FSDK_FacePairMatching(result, tmp.getFace(), score);
			if (max < score.getScore()) {
				max = score.getScore();//更新最大值
				p = tmp;//更新认定结果
			}
		}
		if (max > 0.6f) {
			//TODO 签到
			nr.setAll(0, "匹配成功，用户id"+p.getpId()+"匹配度："+max, p);
		}else{
			nr.setAll(1,"匹配失败",null);
		}
		return nr;
	}*/

}
