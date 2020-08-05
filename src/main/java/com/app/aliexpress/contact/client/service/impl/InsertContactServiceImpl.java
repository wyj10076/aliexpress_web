package com.app.aliexpress.contact.client.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.contact.client.service.InsertContactService;
import com.app.aliexpress.contact.mapper.ContactMapper;

@Service("clientInsertContactService")
public class InsertContactServiceImpl implements InsertContactService {

	@Autowired
	private ContactMapper contactMapper;
	
	@Override
	public void insertContact(Map<String, Object> map) {
		try {
			int result = contactMapper.insertContact(map);
			
			if (result > 0) {
				map.put("result", "success");
			} else {
				map.put("result", "fail");
			}
			
		} catch(Exception e) {
			map.put("result", "fail");
		}
	}

}
