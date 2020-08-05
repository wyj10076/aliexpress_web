package com.app.aliexpress.server.admin.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.aliexpress.common.util.Taobao;
import com.app.aliexpress.server.admin.service.CheckServerAppService;

@Service("adminCheckServerAppService")
public class CheckServerAppServiceImpl implements CheckServerAppService {

	@Override
	public void checkApp(Map<String, Object> map) {
		map.put("SERVER_APP_URL", map.get("appUrl"));
		map.put("SERVER_APP_KEY", map.get("appKey"));
		map.put("SERVER_SECRET_KEY", map.get("secretKey"));
		map.put("SERVER_TRACKING_ID", map.get("trackingId"));
		map.put("source", "http://www.aliexpress.com");
		
		try {
			String link = Taobao.linkGenerate(map);
			
			if (link != null) {
				map.put("result", "success");
			} else {
				map.put("result", "fail");
			}
			
		} catch (Exception e) {
			map.put("result", "fail");
		}
	}

}
