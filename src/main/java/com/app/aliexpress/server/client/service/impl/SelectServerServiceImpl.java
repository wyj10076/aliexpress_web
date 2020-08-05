package com.app.aliexpress.server.client.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.server.client.service.SelectServerService;
import com.app.aliexpress.server.mapper.ServerMapper;

@Service("clientSelectServerService")
public class SelectServerServiceImpl implements SelectServerService {

	@Autowired
	private ServerMapper serverMapper;
	
	@Override
	public void selectServer(Map<String, Object> map) {
		map.putAll(serverMapper.selectServer(map));
	}

}
