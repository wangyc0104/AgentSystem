package org.agent.pojo;

import java.util.Date;

/**
 * 角色
 * @author Yicheng Wang
 */
public class Role extends Base {
	/*
	 * CREATE TABLE `as_role` ( `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT
	 * COMMENT '角色主键ID', `roleName` varchar(100) COLLATE utf8_unicode_ci NOT
	 * NULL COMMENT '角色名称', `creationTime` datetime NOT NULL COMMENT '创建时间',
	 * `createdBy` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建者',
	 * `lastUpdateTime` datetime DEFAULT '2013-01-01 00:00:01' COMMENT '最后修改时间',
	 * `isStart` int(11) DEFAULT '1' COMMENT '是否启用1为启用0为不启用', PRIMARY KEY (`id`)
	 */
	private String roleName;
	private Date creationTime;
	private String createdBy;
	private Date lastUpdateTime;
	private Integer isStart;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
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

	public Integer getIsStart() {
		return isStart;
	}

	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}

}
