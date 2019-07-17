package org.agent.service.role;

import java.util.List;

import org.agent.pojo.Role;

/**
 * 角色Service
 * @author Yicheng Wang
 *
 */
public interface RoleService {

	/**
	 * 获取角色列表
	 * @return
	 */
	public List<Role> getRoleList();

	/**
	 * 获取特定角色
	 * @param role
	 * @return
	 */
	public Role getRole(Role role);

	/**
	 * 增加角色
	 * @param role
	 * @return
	 */
	public int addRole(Role role);

	/**
	 * 删除角色
	 * @param role
	 * @return
	 */
	public int deleteRole(Role role);

	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	public int modifyRole(Role role);

	/**
	 * 仅查询角色id和name
	 * @return
	 */
	public List<Role> getRoleIdAndNameList();

}
