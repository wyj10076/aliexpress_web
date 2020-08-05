package com.app.aliexpress.secondarycategory.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.secondarycategory.admin.service.SelectSecondaryCategoryListService;
import com.app.aliexpress.secondarycategory.mapper.SecondaryCategoryMapper;

@Service("adminSelectSecondaryCategoryListService")
public class SelectSecondaryCategoryListServiceImpl implements SelectSecondaryCategoryListService {

	@Autowired
	private SecondaryCategoryMapper secondaryCategoryMapper;
	
	@Override
	public void selectSecondaryCategoryList(Map<String, Object> map) {
		map.put("secondaryCategoryList", secondaryCategoryMapper.selectSecondaryCategoryList(map));
	}

}
