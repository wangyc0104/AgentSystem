package org.agent.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.agent.common.MD5;
import org.agent.common.SQLTools;
import org.agent.pojo.Role;
import org.agent.pojo.User;
import org.agent.service.role.RoleService;

import com.opensymphony.xwork2.Action;

/**
 * 用户相关Action
 * @author Yicheng Wang
 */
public class UserAction extends BaseAction {

	private User user;
	private List<User> userList;
	private String type;

	// 搜索字段
	private String uname; // 用来接收查询的关键字
	private RoleService roleService;
	private List<Role> roleList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * 检索用户列表
	 * 
	 * @return
	 */
	public String userList() {
		if (user == null)
			user = new User();
		if (uname != null) {
			if (this.getRequest().getMethod().equals("GET")) {
				try {
					uname = new String(uname.getBytes("ISO-8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			user.setUserName(SQLTools.transfer(uname));
		}
		// 分页设置
		user.setStartNum((this.getPager().getPage() - 1) * this.getPager().getPageSize());
		user.setPageSize(this.getPager().getPageSize());
		Integer totalCount = this.getUserService().count(user);
		this.getPager().setTotalCount(totalCount);
		this.userList = this.getUserService().getUserList(user);
		this.getPager().setItems(userList);
		this.roleList = this.roleService.getRoleIdAndNameList();
		return SUCCESS;
	}

	/**
	 * 添加&修改用户
	 */
	public void editUser() {
		String flag = "failed";
		if (null != user && user.getUserCode() != null
				&& user.getUserCode() != "" && user.getUserName() != null
				&& user.getUserName() != "" && user.getUserPassword() != null
				&& user.getUserPassword() != "") {
			user.setCreatedBy(this.getCurrentUser().getUserCode());
			if (type.equals("add")) {
				if (this.getUserService().getUser(user) != null)
					flag = "repeat";
				else {
					user.setCreationTime(new Date());
					user.setUserPassword(MD5.MD5Encode(user.getUserPassword()));
					// this.getUserService().addUser(user);
					this.getUserService().tx_AddUser(user);
					flag = "success";
				}
			} else if (type.equals("modify")) {
				user.setLastUpdateTime(new Date());
				this.getUserService().modifyUser(user);
				flag = "success";
			}
		}
		this.getOut().print(flag);
	}

	/**
	 * 删除用户
	 */
	public void delUser() {
		boolean flag = false;
		if (user != null && user.getId() != null) {
			int i = this.getUserService().deleteUser(user);
			if (i > 0)
				flag = true;
		}
		if (flag)
			this.getOut().print("success");
		else
			this.getOut().print("failed");
	}

}
