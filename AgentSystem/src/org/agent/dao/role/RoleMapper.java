package org.agent.dao.role;

import java.util.List;

import org.agent.pojo.Role;

public interface RoleMapper {

	public List<Role> getRoleList();
	
	public Role getRole(Role role);
	
	public int addRole(Role role);
	
	public int deleteRole(Role role);
	
	public int modifyRole(Role role);
	
	//仅查询id和name
	public List<Role> getRoleIdAndNameList();
	
}
