package cn.drrs.face_meeting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.drrs.face_meeting.entity.PGJoin;
import cn.drrs.face_meeting.entity.ResponseData;


public interface PGJoinDao {

	int insert(List<PGJoin> list);

	int delete(List<PGJoin> list);

	List<PGJoin> getGroupMembersBygId(String gId);
	int queryMemberCountBygId(String gId);
}
