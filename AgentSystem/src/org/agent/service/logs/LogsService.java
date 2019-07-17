package org.agent.service.logs;

import java.util.List;

import org.agent.pojo.Logs;

/**
 * 日志Service
 * @author Yicheng Wang
 *
 */
public interface LogsService {
	
	/**
	 * 获取操作日志列表
	 * @param logs
	 * @return
	 */
	public List<Logs> getList(Logs logs);

	/**
	 * 增加日志
	 * @param logs
	 * @return
	 */
	public int addLogs(Logs logs);

	/**
	 * 获取日志数量
	 * @param logs
	 * @return
	 */
	public int count(Logs logs);
	
}
