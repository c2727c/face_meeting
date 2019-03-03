package cn.drrs.face_meeting.entity;

import java.sql.Timestamp;

public class Message {
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getmNo() {
		return mNo;
	}
	public void setmNo(int mNo) {
		this.mNo = mNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRelativeMeetingTile() {
		return relativeMeetingTile;
	}
	public void setRelativeMeetingTile(String relativeMeetingTile) {
		this.relativeMeetingTile = relativeMeetingTile;
	}
	public Event getRelativeEvent() {
		return relativeEvent;
	}
	public void setRelativeEvent(Event relativeEvent) {
		this.relativeEvent = relativeEvent;
	}
	private String messageId;
	
	
	private String sender;
	private String receiver;//群发时在发送者处此项缺省
	private String owner;//消息的所有者，会产生分别属于发送者和接收者的两条消息
	private int mNo;
	
	private String title;
	private String content;
	private Timestamp sendTime=null;
	private String type;//系统通知system/会议公告meeting/请假request/私信private
	private boolean isRead;
	private String state;//主要是请假类消息的批准与否//none/rejected/accepted
	
	//通过association填写
	private String relativeMeetingTile;//取出来会议名
	private Event relativeEvent;//包含时间，地点，会议号
	
	
	

}
