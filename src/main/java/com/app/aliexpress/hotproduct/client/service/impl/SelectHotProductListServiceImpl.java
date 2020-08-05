package com.app.aliexpress.hotproduct.client.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.hotproduct.client.service.SelectHotProductListService;
import com.app.aliexpress.hotproduct.mapper.HotProductMapper;

@Service("clientSelectHotProductListService")
public class SelectHotProductListServiceImpl implements SelectHotProductListService {

	@Autowired
	private HotProductMapper hotProductMapper;
	
	@Override
	public void selectHotProductListService(Map<String, Object> map) {
		map.put("hotProductList", hotProductMapper.selectHotProductList(map));
	}

}
