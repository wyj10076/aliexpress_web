package com.app.aliexpress.product.client.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.common.util.Taobao;
import com.app.aliexpress.product.client.service.SelectSimilarProductListService;
import com.app.aliexpress.server.client.service.SelectServerService;

@Service("clientSelectSimilarProductListService")
public class SelectSimilarProductListServiceImpl implements SelectSimilarProductListService {

	@Autowired
	private SelectServerService selectServerService;

	@Override
	public Map<String, Object> selectSimilarProductList(Map<String, Object> map) {

		map.put("serverUseYN", "Y");
		selectServerService.selectServer(map);
		
		try {
			map.put("productList", Taobao.getProductList(map));
			map.put("message", "success");

		} catch (Exception e) {
			map.put("message", "error");
		}

		return map;
	}

}
