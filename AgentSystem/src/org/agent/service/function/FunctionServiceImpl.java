package org.agent.service.function;

import java.util.List;

import org.agent.dao.function.FunctionMapper;
import org.agent.pojo.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("functionService")
public class FunctionServiceImpl implements FunctionService {
	
	@Autowired
	private FunctionMapper mapper;
	
	public FunctionMapper getMapper() {
		return mapper;
	}

	public void setMapper(FunctionMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	// 查询所有的功能
	public List<Function> getFunctionList() {
		return mapper.getFunctionList();
	}

	@Override
	// 查询单个功能
	public Function getFunctionById(Function function) {
		return mapper.getFunctionById(function);
	}

	@Override
	// 查询一级功能
	public List<Function> getMenuFunction() {
		return mapper.getMenuFunction();
	}

	@Override
	/**
	 * 按父ID查询功能列表
	 * @param parentId
	 * @return
	 */
	public List<Function> getFunctionByParentId(int parentId) {
		return mapper.getFunctionByParentId(parentId);
	}

}
