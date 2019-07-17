package org.agent.dao.logs;

import java.util.List;

import org.agent.pojo.Logs;

public interface LogsMapper {

	public List<Logs> getList(Logs logs);
	
	public int addLogs(Logs logs);
	
	public int count(Logs logs);
	
}
