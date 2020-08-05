package com.app.aliexpress.server.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.server.admin.service.SelectServerListService;
import com.app.aliexpress.server.mapper.ServerMapper;

@Service("adminSelectServerListService")
public class SelectServerListServiceImpl implements SelectServerListService {

	@Autowired
	private ServerMapper serverMapper;
	
	@Override
	public void selectServerList(Map<String, Object> map) {
		map.put("serverList", serverMapper.selectServerList());
	}

}
