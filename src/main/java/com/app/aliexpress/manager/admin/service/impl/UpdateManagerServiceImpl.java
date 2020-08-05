package com.app.aliexpress.manager.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.manager.admin.service.UpdateManagerService;
import com.app.aliexpress.manager.mapper.ManagerMapper;

@Service("adminUpdateManagerService")
public class UpdateManagerServiceImpl implements UpdateManagerService {

	@Autowired
	private ManagerMapper managerMapper;
	
	@Override
	public void updateManager(Map<String, Object> map) {
		try {
			int result = managerMapper.updateManager(map);
			
			if (result != 0) {
				map.put("result", "success");
			} else {
				map.put("result", "fail");
			}
			
		} catch (Exception e) {
			map.put("result", "fail");
		}

	}

}
