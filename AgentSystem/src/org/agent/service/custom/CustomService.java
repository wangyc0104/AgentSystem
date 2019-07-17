package org.agent.service.custom;

import java.util.List;

import org.agent.pojo.Contact;
import org.agent.pojo.Custom;

/**
 * 客户Service
 * @author Yicheng Wang
 *
 */
public interface CustomService {

	/**
	 * 获取客户列表
	 * @param custom
	 * @return
	 */
	public List<Custom> getList(Custom custom);

	/**
	 * 增加客户
	 * @param custom
	 * @return
	 */
	public int addCustom(Custom custom);

	/**
	 * 修改客户
	 * @param custom
	 * @return
	 */
	public int modifyCustom(Custom custom);

	/**
	 * 删除客户
	 * @param custom
	 * @return
	 */
	public int deleteCustom(Custom custom);

	/**
	 * 获取客户数量
	 * @param custom
	 * @return
	 */
	public int count(Custom custom);

	/**
	 * 是否存在该客户名
	 * @param custom
	 * @return
	 */
	public int isExistCustomName(Custom custom);

	/**
	 * 按ID查询客户
	 * @param custom
	 * @return
	 */
	public Custom getCustomById(Custom custom);

	/**
	 * 搜索查询客户列表
	 * @param custom
	 * @return
	 */
	public List<Custom> getCustomBySearch(Custom custom);
	
	/**
	 * 仅修改客户的状态
	 * @param custom
	 * @return
	 */
	public int modifyCustomStatus(Custom custom);

	/**
	 * 在事务中添加和删除，同时添加和修改客户及客户的联系人
	 * @param custom
	 * @param contactList
	 * @return
	 */
	public boolean tx_addCustomContact(Custom custom, List<Contact> contactList);

	/**
	 * 在事务中修改客户及客户的联系人
	 * @param custom
	 * @param contactList
	 * @return
	 */
	public boolean tx_modifyCustomContact(Custom custom, List<Contact> contactList);

}
