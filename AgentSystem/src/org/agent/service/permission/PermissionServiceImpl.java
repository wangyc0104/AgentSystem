package org.agent.service.permission;

import java.util.List;

import org.agent.dao.permission.PermissionMapper;
import org.agent.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper mapper;

	public PermissionMapper getMapper() {
		return mapper;
	}

	public void setMapper(PermissionMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Permission> getList(Permission permission) {
		return mapper.getList(permission);
	}

	@Override
	public int addPermission(Permission permission) {
		return mapper.addPermission(permission);
	}

	@Override
	public int modifyPermission(Permission permission) {
		return mapper.modifyPermission(permission);
	}

	@Override
	public int deletePermission(Permission permission) {
		return mapper.deletePermission(permission);
	}

	/**
	 * 添加一个权限。 要求：先删除再添加，保证权限的唯一性，避免冗余
	 */
	@Override
	public void tx_delAddPermission(Permission pm, String checkList) {
		// 1.组合所有角色和功能的映射 Permission
		String[] funcList = checkList.split(",");
		mapper.deletePermission(pm);
		
		// 2.保存（先删除再添加）
		if (null != funcList && !funcList.equals("")) {
			for (int i = 0; i < funcList.length; i++){
				pm.setFunctionId(Integer.valueOf(funcList[i]));
				mapper.addPermission(pm);
			}
		}
	}
}
