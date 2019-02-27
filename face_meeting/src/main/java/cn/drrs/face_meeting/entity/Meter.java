package cn.drrs.face_meeting.entity;

import java.io.Serializable;

public class Meter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String appId;
    private String serviceId;
    private String deviceId;
    private String gatewayId;
    private Integer status;
    private String timestamp;
    
	public Meter(Integer id, String appId, String serviceId, String deviceId, String gatewayId, Integer status,
			String timestamp) {
		super();
		this.id = id;
		this.appId = appId;
		this.serviceId = serviceId;
		this.deviceId = deviceId;
		this.gatewayId = gatewayId;
		this.status = status;
		this.timestamp = timestamp;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
    public String toString() {
        return "Meter{" +
                "appId='" + appId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", gatewayId='" + gatewayId + '\'' +
                ", status=" + status +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
