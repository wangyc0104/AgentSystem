package org.agent.dao.custom;

import java.util.List;

import org.agent.pojo.Custom;

public interface CustomMapper {

	public List<Custom> getList(Custom custom);

	public int addCustom(Custom custom);

	public int modifyCustom(Custom custom);

	public int deleteCustom(Custom custom);

	public int count(Custom custom);

	// 是否存在该客户名
	public int isExistCustomName(Custom custom);

	// 按ID查询客户
	public Custom getCustomById(Custom custom);

	// 搜索查询客户列表
	public List<Custom> getCustomBySearch(Custom custom);

	// 仅修改客户的状态
	public int modifyCustomStatus(Custom custom);

}
