package com.app.aliexpress.promotion.admin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.promotion.admin.service.UpdatePromotionService;
import com.app.aliexpress.promotion.mapper.PromotionMapper;

@Service("adminUpdatePromotionService")
public class UpdatePromotionServiceImpl implements UpdatePromotionService {

	@Autowired
	private PromotionMapper promotionMapper;
	
	@Override
	public void updatePromotion(Map<String, Object> map) {
		try {
			int result = promotionMapper.updatePromotion(map);
			
			if (result > 0) {
				map.put("result", "success");
			} else {
				map.put("result", "fail");
			}
			
		} catch (Exception e) {
			map.put("result", "fail");
		}

	}

}
