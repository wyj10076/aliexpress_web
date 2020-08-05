package com.app.aliexpress.contact.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.contact.admin.service.SelectContactListService;
import com.app.aliexpress.contact.mapper.ContactMapper;

@Service("adminShowContactListService")
public class SelectContactListServiceImpl implements SelectContactListService {

	@Autowired
	private ContactMapper contactMapper;
	
	@Override
	public void selectContactList(Map<String, Object> map) {
		map.put("contactList", contactMapper.selectContactList());
	}
}
