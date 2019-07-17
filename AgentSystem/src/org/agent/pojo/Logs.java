package org.agent.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志对象
 * @author Yicheng Wang
 */
public class Logs extends Base {

	private Integer userId;
	private String userName;
	private String operateInfo;
	private Date operateDatetime;
	
	// 操作时间格式（yyyy-MM-dd）
	private String odt;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOperateInfo() {
		return operateInfo;
	}
	public void setOperateInfo(String operateInfo) {
		this.operateInfo = operateInfo;
	}
	public Date getOperateDatetime() {
		return operateDatetime;
	}
	public void setOperateDatetime(Date operateDatetime) {
		this.operateDatetime = operateDatetime;
	}
	public void setOperateDatetime(String operateDatetime) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.operateDatetime = sdf.parse(operateDatetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getOdt() {
		return odt;
	}
	public void setOdt(String odt) {
		this.odt = odt;
	}
	
}
