package org.agent.service.contact;

import java.util.List;

import org.agent.dao.contact.ContactMapper;
import org.agent.pojo.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactMapper mapper;
	
	public ContactMapper getMapper() {
		return mapper;
	}
	public void setMapper(ContactMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Contact> getContactList(Contact contact) {
		return mapper.getContactList(contact);
	}

	@Override
	public int addContact(Contact contact) {
		return mapper.addContact(contact);
	}

	@Override
	public int modifyContact(Contact contact) {
		return mapper.modifyContact(contact);
	}

	@Override
	public int deleteContact(Contact contact) {
		return mapper.deleteContact(contact);
	}

}
