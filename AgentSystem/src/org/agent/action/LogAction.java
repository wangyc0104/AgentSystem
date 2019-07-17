package org.agent.action;

import java.text.SimpleDateFormat;
import java.util.List;

import org.agent.pojo.Logs;
import org.agent.pojo.User;

/**
 * 日志Action
 * @author Yicheng Wang
 */
@SuppressWarnings("serial")
public class LogAction extends BaseAction {

	private Logs logs;
	private List<Logs> logsList;
	private User user;

	public Logs getLogs() {
		return logs;
	}

	public void setLogs(Logs logs) {
		this.logs = logs;
	}

	public List<Logs> getLogsList() {
		return logsList;
	}

	public void setLogsList(List<Logs> logsList) {
		this.logsList = logsList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String logList() {
		if (logs == null) {
			logs = new Logs();
		}
		if (user != null && user.getId() != null) {
			logs.setUserId(user.getId());
			logs.setUserName(user.getUserName());
		} else {
			logs.setUserId(this.getCurrentUser().getId());
		}
		// 把输入的时间格式转成字符串
		if (logs.getOperateDatetime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String odt = sdf.format(logs.getOperateDatetime());
			logs.setOdt(odt);
		}
		// logs.setStartNum(0);
		this.getPager().setPageSize(3);
		logs.setPageSize(this.getPager().getPageSize());
		// 设置每页的开始索引（注：第一个索引为0而不是1）
		logs.setStartNum((this.getPager().getPage() - 1) * this.getPager().getPageSize());
		this.getPager().setTotalCount(this.getLogsService().count(logs));
		this.logsList = this.getLogsService().getList(logs);
		this.getPager().setItems(logsList); // 迭代pager.items
		return "success";
	}
}
