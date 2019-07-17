package org.agent.action;

import java.util.Date;

import org.agent.common.Constants;
import org.agent.common.MD5;
import org.agent.pojo.Account;
import org.agent.pojo.User;
import org.agent.service.account.AccountService;

/**
 * 登录Action
 * @author Yicheng Wang
 *
 */
@SuppressWarnings("serial")
public class LoginAction extends BaseAction {

	// 用于接收form表单提交的数据
	private User user;
	private Account account;
	private AccountService accountService;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public String login() {
		if (user != null) {
			// 密码加密处理
			user.setUserPassword(MD5.MD5Encode(user.getUserPassword()));
			User _user = this.getUserService().getLoginUser(user);
			if (_user != null) {
				this.getRequest().getSession().setAttribute(Constants.SESSION_USER, _user);
				_user.setLastLoginTime(new Date());
				this.getUserService().modifyUser(_user); // 更新用户状态
				this.setLog(_user, Constants.OPERATE_INFO_USER_LOGIN_SUCCESS);
				return SUCCESS;
			} else
				this.setLog(_user, Constants.OPERATE_INFO_USER_LOGIN_FAILED);
				return INPUT;
		} else
			return INPUT;
	}

	public String checkUser() {
		/**
		 * noexitusercode errorpwd failed success
		 */
		String flag = "failed";
		if (user != null && user.getUserCode() != "") {
			user.setUserPassword(MD5.MD5Encode(user.getUserPassword()));
			int n = this.getUserService().isExitLoginUser(user);
			if (n == 0) {
				flag = "noexitusercode";
			} else if (this.getUserService().getLoginUser(user) == null) {
				flag = "errorpwd";
			} else {
				flag = "success";
			}
		}
		this.getOut().write(flag);
		return null; // Struts2不再进行页面的显示处理
	}

	public String main() {
		// 加入权限验证（通过<interceptor-ref name="adminInterceptor"></interceptor-ref>来实现）
		account = new Account();
		account.setUserId(this.getCurrentUser().getId()); // 为了防止CurrentUser为空，使用了拦截器
		account = accountService.getAccount(account);
		return SUCCESS;
	}

	public void modifyPwd() {
		// _user:已登录用户；user:页面接收的用户
		User _user = (User) this.getRequest().getSession().getAttribute(Constants.SESSION_USER);
		// 老密码用userName属性传递过来
		if (MD5.MD5Encode(user.getUserName()).equals(_user.getUserPassword())) {
			_user.setUserPassword(MD5.MD5Encode(user.getUserPassword()));
			if (this.getUserService().modifyUser(_user) > 0) {
				this.setLog(this.getCurrentUser(), Constants.OPERATE_INFO_USER_MODIFY_PASSWORD);
				this.getOut().print("success");
				this.getOut().flush();
				this.getOut().close();
			}
		}
	}
	
	public String exit() {
		if(this.getCurrentUser()!=null)
			this.setLog(this.getCurrentUser(), Constants.OPERATE_INFO_USER_LOGOUT_SUCCESS);
		this.getRequest().getSession().invalidate();
		this.getRequest().getSession().removeAttribute(Constants.SESSION_USER);
		return SUCCESS;
	}
	
}
