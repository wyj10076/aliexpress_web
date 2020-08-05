package com.app.aliexpress.server.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aliexpress.server.admin.service.DeleteServerService;
import com.app.aliexpress.server.mapper.ServerMapper;

@Service("adminDeleteServerService")
public class DeleteServerServiceImpl implements DeleteServerService {

	@Autowired
	private ServerMapper serverMapper;
	
	@Transactional
	@Override
	public void deleteServer(Map<String, Object> map) {
		try {
			String serverUseYN = (String) map.get("serverUseYN");
			
			serverMapper.deleteServer(map);
			
			if (serverUseYN.equals("Y")) {
				map.put("serverId", "default");
				serverMapper.updateServer(map);
			}
			
			map.put("result", "success");
			
		} catch (Exception e) {
			map.put("result", "fail");
		}
	}

}
