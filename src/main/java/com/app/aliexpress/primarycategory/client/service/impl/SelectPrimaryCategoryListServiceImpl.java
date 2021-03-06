package com.app.aliexpress.primarycategory.client.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.primarycategory.client.service.SelectPrimaryCategoryListService;
import com.app.aliexpress.primarycategory.mapper.PrimaryCategoryMapper;

@Service("clientSelectPrimaryCategoryListService")
public class SelectPrimaryCategoryListServiceImpl implements SelectPrimaryCategoryListService {

	@Autowired
	private PrimaryCategoryMapper primaryCategoryMapper;
	
	@Override
	public void selectPrimaryCategoryList(Map<String, Object> map) {
		map.put("primaryCategoryList", primaryCategoryMapper.selectPrimaryCategoryList());
	}

}
