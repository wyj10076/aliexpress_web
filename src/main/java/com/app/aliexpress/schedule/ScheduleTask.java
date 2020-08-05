package com.app.aliexpress.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.aliexpress.promotion.admin.service.DeletePromotionBySchedulerService;

@Component
public class ScheduleTask {
	
	@Autowired
	private DeletePromotionBySchedulerService deletePromotionBySchedulerService;
	
	@Scheduled(cron = "0 0 0 * * ?", zone="Asia/Seoul")
	public void task1() {
		deletePromotionBySchedulerService.deletePromotionByScheduler();
	}
	
	
}
