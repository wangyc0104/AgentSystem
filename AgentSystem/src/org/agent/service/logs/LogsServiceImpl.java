package org.agent.service.logs;

import java.util.List;

import org.agent.dao.logs.LogsMapper;
import org.agent.pojo.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logsService")
public class LogsServiceImpl implements LogsService {

	@Autowired
	private LogsMapper mapper;
	public LogsMapper getMapper() {
		return mapper;
	}
	public void setMapper(LogsMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Logs> getList(Logs logs) {
		return mapper.getList(logs);
	}

	@Override
	public int addLogs(Logs logs) {
		return mapper.addLogs(logs);
	}

	@Override
	public int count(Logs logs) {
		return mapper.count(logs);
	}

}
