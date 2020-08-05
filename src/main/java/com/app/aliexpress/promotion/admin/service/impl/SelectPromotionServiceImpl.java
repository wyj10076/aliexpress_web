package com.app.aliexpress.promotion.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.promotion.admin.service.SelectPromotionService;
import com.app.aliexpress.promotion.mapper.PromotionMapper;

@Service("adminSelectPromotionService")
public class SelectPromotionServiceImpl implements SelectPromotionService {

	@Autowired
	private PromotionMapper promotionMapper;
	
	@Override
	public void selectPromotion(Map<String, Object> map) {
		map.put("promotion", promotionMapper.selectPromotion(map));
	}

}
