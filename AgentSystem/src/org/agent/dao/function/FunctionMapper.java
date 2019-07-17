package org.agent.dao.function;

import java.util.List;

import org.agent.pojo.Function;

public interface FunctionMapper {

	// 查询所有的功能
	public List<Function> getFunctionList();
	
	// 查询单个功能
	public Function getFunctionById(Function function);
	
	// 查询一级功能
	public List<Function> getMenuFunction();
	
	/**
	 * 按父ID查询功能列表
	 * @param parentId
	 * @return
	 */
	public List<Function> getFunctionByParentId(int parentId);
	
}
