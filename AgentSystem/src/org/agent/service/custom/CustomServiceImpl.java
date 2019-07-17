package org.agent.service.custom;

import java.util.ArrayList;
import java.util.List;

import org.agent.dao.contact.ContactMapper;
import org.agent.dao.custom.CustomMapper;
import org.agent.pojo.Contact;
import org.agent.pojo.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customService")
public class CustomServiceImpl implements CustomService {

	@Autowired
	private CustomMapper mapper;
	@Autowired
	private ContactMapper contactMapper;

	public CustomMapper getMapper() {
		return mapper;
	}

	public void setMapper(CustomMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Custom> getList(Custom custom) {
		return mapper.getList(custom);
	}

	@Override
	public int addCustom(Custom custom) {
		return mapper.addCustom(custom);
	}

	@Override
	public int modifyCustom(Custom custom) {
		return mapper.modifyCustom(custom);
	}

	@Override
	public int deleteCustom(Custom custom) {
		return mapper.deleteCustom(custom);
	}

	@Override
	public int count(Custom custom) {
		return mapper.count(custom);
	}

	@Override
	public int isExistCustomName(Custom custom) {
		return mapper.isExistCustomName(custom);
	}

	@Override
	public Custom getCustomById(Custom custom) {
		return mapper.getCustomById(custom);
	}

	@Override
	public List<Custom> getCustomBySearch(Custom custom) {
		return mapper.getCustomBySearch(custom);
	}

	@Override
	public int modifyCustomStatus(Custom custom) {
		return mapper.modifyCustomStatus(custom);
	}

	@Override
	// 在事务中添加客户及客户的联系人
	public boolean tx_addCustomContact(Custom custom, List<Contact> contactList) {
		// 小心事务不能被回滚：原因就是try语句捕获异常后，AOP的事务管理器拿不到throwing这个连接点
		// 处理方式：1.不加try/catch 2.在catch中throw一个runtime异常
		try {
			// 保存Custom
			int addCustomId = 0;
			addCustomId = mapper.addCustom(custom); // 添加custom后，ID就生成了
			// 保存Contact
			if (null != contactList && contactList.size() > 0 && addCustomId != 0) {
				for (Contact contact : contactList) {
					contact.setCustomId(custom.getId());
					contactMapper.addContact(contact);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e); // 此处如果不抛出异常，事务管理器就无法捕获异常，做出回滚响应。
			// return false;
		}
	}

	@Override
	// 在事务中修改客户及客户的联系人
	public boolean tx_modifyCustomContact(Custom custom, List<Contact> contactList) {
		if (null == contactList)
			contactList = new ArrayList<Contact>();
		try {
			mapper.modifyCustom(custom);
			// 保存Custom
			Contact contact = new Contact();
			contact.setCustomId(custom.getId());
			// 保存Contact（保存客户的联系人之前，先删除）
			contactMapper.deleteContact(contact);
			for (Contact contactNew : contactList) {
				if (contactNew != null) {
					contactNew.setCustomId(custom.getId());
					// contactMapper.modifyContact(contactNew); 这里不是修改，而是增加
					contactMapper.addContact(contactNew);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
