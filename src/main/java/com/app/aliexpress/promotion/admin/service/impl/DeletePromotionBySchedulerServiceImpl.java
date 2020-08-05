package com.app.aliexpress.promotion.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aliexpress.promotion.admin.service.DeletePromotionBySchedulerService;
import com.app.aliexpress.promotion.mapper.PromotionMapper;

@Service("adminDeletePromotionbySchedulerService")
public class DeletePromotionBySchedulerServiceImpl implements DeletePromotionBySchedulerService {

	@Autowired
	private PromotionMapper promotionMapper;
	
	@Override
	public void deletePromotionByScheduler() {
		System.out.println(promotionMapper.deletePromotionByScheduler());
	}

}
