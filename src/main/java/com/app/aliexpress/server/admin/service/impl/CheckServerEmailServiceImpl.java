package com.app.aliexpress.server.admin.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.aliexpress.common.util.MailSender;
import com.app.aliexpress.server.admin.service.CheckServerEmailServcie;

@Service("adminCheckServerEmailService")
public class CheckServerEmailServiceImpl implements CheckServerEmailServcie {

	@Override
	public void checkServerEmail(Map<String, Object> map) {
		try {
			MailSender mailSender = new MailSender(map);
			mailSender.connect();
			mailSender.disconnect();
			
			map.put("result", "success");
			
		} catch (Exception e) {
			map.put("result", "fail");
		}
	}

}
