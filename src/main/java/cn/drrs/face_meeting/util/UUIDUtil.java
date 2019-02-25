package cn.drrs.face_meeting.util;

import java.util.UUID;

/**
 *	生成随机ID
 */
public class UUIDUtil {

	public static String getUID() {
		return UUID.randomUUID().toString();
	}

	public static void main(String[] args) {
		System.out.println(getUID());
	}
}