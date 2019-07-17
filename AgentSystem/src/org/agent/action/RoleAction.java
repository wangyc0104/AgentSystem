package org.agent.action;

import java.util.Date;
import java.util.List;

import org.agent.common.Constants;
import org.agent.pojo.Role;
import org.agent.service.role.RoleService;

/**
 * 角色Action
 * @author Yicheng Wang
 */
public class RoleAction extends BaseAction {

	private RoleService roleService;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	private Role role;
	private List<Role> roleList;

	private String type; // 处理“新增add”和“修改modify”两种请求

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// 查询所有角色
	public String roleList() {
		this.roleList = this.getRoleService().getRoleList();
		return SUCCESS;
	}

	// 新增(add)|修改(modify)角色
	public void editRole() {

		if (type.equals("add")) {
			// this.getRequest().getSession().getAttribute(Constants.SESSION_USER)
			role.setCreatedBy(this.getCurrentUser().getUserCode());
			role.setCreationTime(new Date());
			this.getRoleService().addRole(role);
			this.getOut().print("success");
		} else if (type.equals("modify")) {
			role.setLastUpdateTime(new Date());
			this.getRoleService().modifyRole(role);
			this.getOut().print("success");
		}
	}

	// 删除(delete)角色
	public void deleteRole() {
		if (this.getRoleService().deleteRole(role) >= 1)
			this.getOut().print("success");
	}
}
