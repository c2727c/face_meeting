package cn.drrs.face_meeting.util;

import java.util.UUID;

/**
 *	�������ID
 */
public class UUIDUtil {

	public static String getUID() {
		return UUID.randomUUID().toString();
	}

	public static void main(String[] args) {
		System.out.println(getUID().length());
	}
}