package cn.drrs.face_meeting.service;

import com.arcsoft.facerecognition.AFR_FSDKEngine;
import com.arcsoft.facerecognition.AFR_FSDKFace;

import cn.drrs.face_meeting.entity.Group;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.util.NoteResult;

public interface FaceService {

	AFR_FSDKEngine engine = new AFR_FSDKEngine();
	AFR_FSDKFace result = new AFR_FSDKFace();
	byte[] mImageNV21 = null;
	//提供人脸特征，返回匹配用户
	public NoteResult<Person> faceRecognition(AFR_FSDKFace feature);
	//指定用户、会议，进行签到并开门2-22
	public NoteResult<Object> CheckIn(Meeting m,String pId);
	
	

}
