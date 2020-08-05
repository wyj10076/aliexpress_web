package com.app.aliexpress.server.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.server.admin.service.SelectServerService;
import com.app.aliexpress.server.mapper.ServerMapper;

@Service("adminCheckServerIDService")
public class SelectServerServiceImpl implements SelectServerService {

	@Autowired
	private ServerMapper serverMapper;
	
	@Override
	public void selectServer(Map<String, Object> map) {
		
		try {
			Map<String, Object> server = serverMapper.selectServer(map);
			
			if (server != null) {
				map.put("server", server);
				map.put("result", "success");
				
			} else {
				map.put("result", "fail");
			}
			
		} catch (Exception e) {
			map.put("result", "fail");
		}
	}

}
