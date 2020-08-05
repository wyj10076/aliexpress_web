package com.app.aliexpress.maincontent.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MainContentMapper {
	
	List<Map<String, Object>> selectMainContentList(Map<String, Object> map);
	int insertMainContentList(Map<String, List<Map<String, Object>>> map);
	int updateMainContentList(Map<String, List<Map<String, Object>>> map);
	int deleteMainContentList(Map<String, List<Map<String, Object>>> map);
	int updateOrderMainContentList(Map<String, List<Map<String, Object>>> map);
}
