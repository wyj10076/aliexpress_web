package com.app.aliexpress.server.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aliexpress.server.admin.service.UpdateServerService;
import com.app.aliexpress.server.mapper.ServerMapper;

@Service("adminUpdateServerService")
public class UpdateServerServiceImpl implements UpdateServerService {

	@Autowired
	private ServerMapper serverMapper;
	
	@Transactional
	@Override
	public void updateServerService(Map<String, Object> map) {
		try {
			serverMapper.updateUseYNToN();
			serverMapper.updateServer(map);

			map.put("result", "success");
			
		} catch (Exception e) {
			map.put("result", "fail");
		}
	}

}
