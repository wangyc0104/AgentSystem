package org.agent.service.permission;

import java.util.List;

import org.agent.pojo.Permission;

/**
 * 角色权限Service
 * @author Yicheng Wang
 *
 */
public interface PermissionService {
	
	/**
	 * 获取权限列表
	 * @param permission
	 * @return
	 */
	public List<Permission> getList(Permission permission);

	/**
	 * 增加权限
	 * @param permission
	 * @return
	 */
	public int addPermission(Permission permission);

	/**
	 * 修改权限
	 * @param permission
	 * @return
	 */
	public int modifyPermission(Permission permission);

	/**
	 * 删除权限
	 * @param permission
	 * @return
	 */
	public int deletePermission(Permission permission);

	/**
	 * 删除并添加新的权限
	 * @param pm
	 * @param checkList
	 */
	public void tx_delAddPermission(Permission pm, String checkList);
	
}
