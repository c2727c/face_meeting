package cn.drrs.face_meeting.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NoteResult<T> implements Serializable {

	private int status;
	private String msg;
	private T data;
	
	public NoteResult<JSONObject> toJSONObject() {
		NoteResult<JSONObject> nr = new NoteResult<JSONObject>();
		nr.setAll(this.status, this.msg, JSONObject.fromObject(this.data));
		return nr;
	}
	public NoteResult<JSONArray> toJSONArray() {
		NoteResult<JSONArray> nr = new NoteResult<JSONArray>();
		nr.setAll(this.status, this.msg, JSONArray.fromCollection((Collection)this.data));
		return nr;
	}
	
	public static NoteResult<JSONArray> tofJSONArray(NoteResult<List<Object>> n) {
		NoteResult<JSONArray> nr = new NoteResult<JSONArray>();
		nr.setAll(n.getStatus(), n.getMsg(), JSONArray.fromCollection(n.getData()));
		return nr;
	}
	
	
	
	public NoteResult( ) {
		super();
	}
	public NoteResult(int status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	
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
		String s;
		if(data!=null) {
				s = "NoteResult [status=" + status + ", msg=" + msg + ", data=" + data.toString() + "]";
		}else {	s = "NoteResult [status=" + status + ", msg=" + msg + ", data= null ]";}
		System.err.println(s);
		return s;
		
	}
	
	public String printJSON() {
		String s = JSONObject.fromObject(this).toString();
		System.err.println(s);
		return s;
		
	}

}

