package org.agent.service.function;

import java.util.List;

import org.agent.pojo.Function;

/**
 * 功能Service
 * @author Yicheng Wang
 *
 */
public interface FunctionService {

	/**
	 * 查询所有的功能
	 * @return
	 */
	public List<Function> getFunctionList();

	/**
	 * 查询单个功能
	 * @param function
	 * @return
	 */
	public Function getFunctionById(Function function);

	/**
	 * 查询一级功能
	 * @return
	 */
	public List<Function> getMenuFunction();

	/**
	 * 按父ID查询功能列表
	 * @param parentId
	 * @return
	 */
	public List<Function> getFunctionByParentId(int parentId);
	
}
