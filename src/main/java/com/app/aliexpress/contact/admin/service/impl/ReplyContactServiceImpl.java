package com.app.aliexpress.contact.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.common.util.MailSender;
import com.app.aliexpress.contact.admin.service.ReplyContactService;
import com.app.aliexpress.contact.mapper.ContactMapper;
import com.app.aliexpress.server.mapper.ServerMapper;

@Service("adminReplyContactService")
public class ReplyContactServiceImpl implements ReplyContactService {

	@Autowired
	private ServerMapper serverMapper;
	@Autowired
	private ContactMapper contactMapper;
	
	@Override
	public void replyContact(Map<String, Object> map) {
		try {
			map.put("serverUseYN", "Y");
			map.putAll(serverMapper.selectServer(map));
			
			MailSender mailSender = new MailSender(map);
			mailSender.connect();
			mailSender.sendMessage(map);
			mailSender.disconnect();
			
			contactMapper.replyContact(map);
			
			map.put("result", "success");
			
		} catch (Exception e) {
			map.put("result", "false");
		}
	}

}
