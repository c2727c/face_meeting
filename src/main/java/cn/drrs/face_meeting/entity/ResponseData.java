package cn.drrs.face_meeting.entity;

import java.io.Serializable;

public class ResponseData implements Serializable{          
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  String code;//成功的状态码，默认：0
    private String msg;//状态信息的字段名称，默认：msg
    private String count;//数据总数的字段名称，默认：count
    private Object data;//数据列表的字段名称，默认：data
    

	public ResponseData(String code, String msg, String count, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
	public ResponseData() {
		// TODO Auto-generated constructor stub
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
    public String toString() {
        return "ResponseData{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", count='" + count + '\'' +
                ", data=" + data +
                '}';
    }
}
