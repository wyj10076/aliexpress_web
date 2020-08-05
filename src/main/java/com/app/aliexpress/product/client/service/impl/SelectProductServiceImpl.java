package com.app.aliexpress.product.client.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.common.util.Taobao;
import com.app.aliexpress.product.client.service.SelectProductService;
import com.app.aliexpress.server.client.service.SelectServerService;

@Service("clientSelectProductService")
public class SelectProductServiceImpl implements SelectProductService {

	@Autowired
	private SelectServerService selectServerService;
	
	@Override
	public Map<String, Object> selectProduct(Map<String, Object> map) {
		map.put("serverUseYN", "Y");
		selectServerService.selectServer(map);
		
		try {
			Map<String, Object> product = Taobao.getProduct(map);
			
			if (product == null) {
				map.put("message", "wrong");
			} else {
				map.put("product", product);
				map.put("message", "success");
			}

		} catch (Exception e) {
			map.put("message", "error");
		}

		return map;
	}
	
}
