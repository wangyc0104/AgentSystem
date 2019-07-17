package org.agent.dao.permission;

import java.util.List;

import org.agent.pojo.Permission;

public interface PermissionMapper {

	public List<Permission> getList(Permission permission);

	public int addPermission(Permission permission);

	public int modifyPermission(Permission permission);

	public int deletePermission(Permission permission);
	
}
