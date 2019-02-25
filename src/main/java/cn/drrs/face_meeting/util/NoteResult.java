package cn.drrs.face_meeting.util;

import java.io.Serializable;

public class NoteResult<T> implements Serializable {

	private int status;
	private String msg;
	
	public NoteResult( ) {
		super();
	}
	public NoteResult(int status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	private T data;
	public void setAll(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		String s = "NoteResult [status=" + status + ", msg=" + msg + ", data=" + data.toString() + "]";
		//说明data不能为null
		System.err.println(s);
		return s;
	}

}
