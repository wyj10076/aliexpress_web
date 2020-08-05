package com.app.aliexpress.secondarycategory.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.secondarycategory.admin.service.SelectSecondaryCategoryService;
import com.app.aliexpress.secondarycategory.mapper.SecondaryCategoryMapper;

@Service("adminSelectSecondaryCategoryService")
public class SelectSecondaryCategoryServiceImpl implements SelectSecondaryCategoryService {

	@Autowired
	private SecondaryCategoryMapper secondaryCategoryMapper;
	
	@Override
	public void selectSecondaryCategory(Map<String, Object> map) {
		map.put("secondaryCategory", secondaryCategoryMapper.selectSecondaryCategory(map));
	}

}
