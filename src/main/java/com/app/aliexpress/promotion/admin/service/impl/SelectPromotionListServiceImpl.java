package com.app.aliexpress.promotion.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.promotion.admin.service.SelectPromotionListService;
import com.app.aliexpress.promotion.mapper.PromotionMapper;

@Service("adminSelectPromotionListService")
public class SelectPromotionListServiceImpl implements SelectPromotionListService {

	@Autowired
	private PromotionMapper promotionMapper;
	
	@Override
	public void selectPromotionList(Map<String, Object> map) {
		map.put("promotionList", promotionMapper.selectPromotionList(map));
	}

}
