package org.agent.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agent.common.Constants;
import org.agent.common.PageSupport;
import org.agent.pojo.Function;
import org.agent.pojo.Logs;
import org.agent.pojo.RoleFunctions;
import org.agent.pojo.User;
import org.agent.service.logs.LogsService;
import org.agent.service.user.UserService;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 基础Action对象，包含日志、获取输入输出流功能
 * @author Yicheng Wang
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {

	private UserService userService;
	private User currentUser;
	private String actionMessage;
	private PrintWriter out;
	private PageSupport pager;
	private LogsService logsService;
	private List<RoleFunctions> roleFunctions;

	// 记录日志
	public void setLog(User user, String operateInfo) {
		Logs logs = new Logs();
		logs.setUserId(user.getId());
		logs.setUserName(user.getUserCode());
		logs.setOperateInfo(operateInfo);
		logs.setOperateDatetime(new Date());
		this.logsService.addLogs(logs);
	}

	public BaseAction() {
		this.setCurrentUser((User) this.getRequest().getSession().getAttribute(Constants.SESSION_USER));
		this.getResponse().setCharacterEncoding("UTF-8");
		if (this.pager == null)
			this.pager = new PageSupport(); // 初始化分页对象
		if (this.getCurrentUser() != null)
			roleFunctions = Constants.MENU.get(this.getCurrentUser().getRoleId());
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public PrintWriter getOut() {
		try {
			if (out == null) {
				this.out = this.getResponse().getWriter();
				return out;
			}
			else
				return out;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public PageSupport getPager() {
		if (this.pager == null)
			pager = new PageSupport();
		return pager;
	}

	public void setPager(PageSupport pager) {
		this.pager = pager;
	}

	public LogsService getLogsService() {
		return logsService;
	}

	public void setLogsService(LogsService logsService) {
		this.logsService = logsService;
	}

	public List<RoleFunctions> getRoleFunctions() {
		return roleFunctions;
	}

	public void setRoleFunctions(List<RoleFunctions> roleFunctions) {
		this.roleFunctions = roleFunctions;
	}
	
}
