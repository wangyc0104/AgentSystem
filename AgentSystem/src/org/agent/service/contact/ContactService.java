package org.agent.service.contact;

import java.util.List;

import org.agent.pojo.Contact;

/**
 * 联系人Service
 * @author Yicheng Wang
 *
 */
public interface ContactService {
	
	/**
	 * 获取联系人列表
	 * @param contact
	 * @return
	 */
	public List<Contact> getContactList(Contact contact);

	/**
	 * 添加联系人
	 * @param contact
	 * @return
	 */
	public int addContact(Contact contact);

	/**
	 * 修改联系人
	 * @param contact
	 * @return
	 */
	public int modifyContact(Contact contact);

	/**
	 * 删除联系人
	 * @param contact
	 * @return
	 */
	public int deleteContact(Contact contact);
	
}
