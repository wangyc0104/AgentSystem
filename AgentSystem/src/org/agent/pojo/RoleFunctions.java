package org.agent.pojo;

import java.util.List;

/**
 * 组合菜单
 * @author Yicheng Wang
 */
public class RoleFunctions {

	private Function mainFunction; // 主菜单
	private List<Function> subFunctions; // 子菜单

	public Function getMainFunction() {
		return mainFunction;
	}

	public void setMainFunction(Function mainFunction) {
		this.mainFunction = mainFunction;
	}

	public List<Function> getSubFunctions() {
		return subFunctions;
	}

	public void setSubFunctions(List<Function> subFunctions) {
		this.subFunctions = subFunctions;
	}
	
}
