package org.agent.action;

import java.util.Date;
import java.util.List;

import org.agent.pojo.Function;
import org.agent.pojo.Permission;
import org.agent.pojo.Role;
import org.agent.service.function.FunctionService;
import org.agent.service.permission.PermissionService;
import org.agent.service.role.RoleService;

import com.opensymphony.xwork2.Action;

/**
 * 功能权限管理Action
 * @author Yicheng Wang
 */
@SuppressWarnings("serial")
public class FunctionAction extends BaseAction {

	private FunctionService functionService;
	private RoleService roleService;
	private List<Function> funcList;
	private List<Role> roleList;
	private Integer roleId;
	private String checkList;
	private PermissionService permissionService;

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<Function> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<Function> funcList) {
		this.funcList = funcList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String roleList() {
		this.roleList = this.roleService.getRoleIdAndNameList();
		return Action.SUCCESS;
	}

	public String funcList() {
		this.funcList = this.functionService.getFunctionList();
		Permission permission = new Permission();
		permission.setRoleId(roleId);
		permission.setIsStart(1);
		// 获取指定角色的权限列表
		List<Permission> pList = this.permissionService.getList(permission);
		// 绑定到指定的功能
		if (pList != null) {
			for (Permission p : pList) {
				for (Function f : funcList) {
					if (p.getFunctionId().equals(f.getId())) {
						// 当前的f需要被选中
						f.setCheck(true);
					}
				}
			}
		}
		return Action.SUCCESS;
	}

	public String getCheckList() {
		return checkList;
	}

	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void saveRoleFunc() {
		// 调用业务处理方法，保存角色和功能的映射
		// roleId, checkList
		Permission pm = new Permission();
		pm.setRoleId(roleId);
		pm.setCreatedBy(this.getCurrentUser().getUserCode());
		pm.setCreationTime(new Date());
		pm.setIsStart(1);
		permissionService.tx_delAddPermission(pm, this.getCheckList());
		this.getOut().print("success");
	}

}
