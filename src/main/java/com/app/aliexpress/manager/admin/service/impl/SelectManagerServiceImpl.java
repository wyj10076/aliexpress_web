package com.app.aliexpress.manager.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.manager.admin.service.SelectManagerService;
import com.app.aliexpress.manager.mapper.ManagerMapper;

@Service("adminSelectManagerService")
public class SelectManagerServiceImpl implements SelectManagerService {

	@Autowired
	private ManagerMapper managerMapper;
	
	@Override
	public void selectManager(Map<String, Object> map) {
		
		try {
			Map<String, Object> manager = managerMapper.selectManager(map);
			if (manager != null && manager.get("MANAGER_PASSWORD").equals(map.get("password"))) {
				map.put("manager", manager);
				map.put("result", "success");
				
			} else {
				map.put("result", "fail");
			}
			
		} catch(Exception e) {
			map.put("result", "error");
		}
	}

}
