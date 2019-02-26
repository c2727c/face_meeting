package cn.drrs.face_meeting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.drrs.face_meeting.entity.PGJoin;


public interface PGJoinDao {

	int insert(List<PGJoin> list);

	int delete(List<PGJoin> list);

}
