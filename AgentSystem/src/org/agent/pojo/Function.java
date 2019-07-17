package org.agent.pojo;

import java.util.Date;

/**
 * 功能
 * @author Yicheng Wang
 */
public class Function extends Base{

	// CREATE TABLE `as_function` (
	// `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	// `functionCode` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
	// `functionName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
	// `creationTime` datetime NOT NULL DEFAULT '2013-01-01 00:00:01',
	// `createdBy` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
	// `lastUpdateTime` datetime DEFAULT '2013-01-01 00:00:01',
	// `funcUrl` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
	// `isStart` int(11) DEFAULT '1' COMMENT '1为启用0为不启用',
	// `parentId` int(11) DEFAULT '0',
	// PRIMARY KEY (`id`)
	// )

	private String functionCode;
	private String functionName;
	private Date CreationTime;
	private String createdBy;
	private Date lastUpdateTime;
	private String funcUrl;
	private Integer isStart;
	private Integer parentId;
	public boolean check = false;
	
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public Date getCreationTime() {
		return CreationTime;
	}
	public void setCreationTime(Date creationTime) {
		CreationTime = creationTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getFuncUrl() {
		return funcUrl;
	}
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
	public Integer getIsStart() {
		return isStart;
	}
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
}
