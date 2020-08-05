package com.app.aliexpress.server.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aliexpress.server.admin.service.InsertServerService;
import com.app.aliexpress.server.mapper.ServerMapper;

@Service("adminInsertServerService")
public class InsertServerServiceImpl implements InsertServerService {

	@Autowired
	private ServerMapper serverMapper;
	
	@Transactional
	@Override
	public void insertServer(Map<String, Object> map) {
		try {
			serverMapper.updateUseYNToN();
			serverMapper.insertServer(map);
			map.put("result", "success");
			
		} catch (Exception e) {
			map.put("result", "fail");
		}
	}

}
