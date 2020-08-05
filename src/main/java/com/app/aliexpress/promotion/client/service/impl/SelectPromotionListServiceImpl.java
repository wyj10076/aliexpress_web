package com.app.aliexpress.promotion.client.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.promotion.client.service.SelectPromotionListService;
import com.app.aliexpress.promotion.mapper.PromotionMapper;

@Service("clientSelectPromotionListService")
public class SelectPromotionListServiceImpl implements SelectPromotionListService {

	@Autowired
	private PromotionMapper promotionMapper;

	@Override
	public void selectPromotionList(Map<String, Object> map) {
		map.put("promotionType", "periodical");
		map.put("order", true);
		List<Map<String, Object>> promotionList = promotionMapper.selectPromotionList(map);
		
		map.put("promotionType", "ongoing");
		promotionList.addAll(promotionMapper.selectPromotionList(map));
		map.put("promotionList", promotionList);
	}
	
}
