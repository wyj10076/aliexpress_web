package com.app.aliexpress.contact.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.contact.admin.service.SelectContactService;
import com.app.aliexpress.contact.mapper.ContactMapper;

@Service("adminShowContactService")
public class SelectContactServiceImpl implements SelectContactService {

	@Autowired
	private ContactMapper contactMapper;
	
	@Override
	public void selectContact(Map<String, Object> map) {
		Map<String, Object> contact = contactMapper.selectContact(map);
		map.put("contact", contact);
		
		if (contact != null && contact.get("CONTACT_READ_YMD") == null) {
			contactMapper.updateReadYMDToSysdate(map);
		}
	}

}
