package org.agent.service.role;

import java.util.List;

import org.agent.dao.role.RoleMapper;
import org.agent.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper mapper;
	
	public RoleMapper getMapper() {
		return mapper;
	}

	public void setMapper(RoleMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		return mapper.getRoleList();
	}

	@Override
	public Role getRole(Role role) {
		// TODO Auto-generated method stub
		return mapper.getRole(role);
	}

	@Override
	public int addRole(Role role) {
		// TODO Auto-generated method stub
		return mapper.addRole(role);
	}

	@Override
	public int deleteRole(Role role) {
		// TODO Auto-generated method stub
		return mapper.deleteRole(role);
	}

	@Override
	public int modifyRole(Role role) {
		// TODO Auto-generated method stub
		return mapper.modifyRole(role);
	}

	@Override
	public List<Role> getRoleIdAndNameList() {
		// TODO Auto-generated method stub
		return mapper.getRoleIdAndNameList();
	}

}
