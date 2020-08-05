package com.app.aliexpress.promotion.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PromotionMapper {

	List<Map<String, Object>> selectPromotionList(Map<String, Object> map);
	Map<String, Object> selectPromotion(Map<String, Object> map);
	int insertPromotion(Map<String, Object> map);
	int updatePromotion(Map<String, Object> map);
	int deletePromotion(Map<String, Object> map);
	int deletePromotionByScheduler();
}
