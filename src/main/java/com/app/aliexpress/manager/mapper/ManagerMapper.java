package com.app.aliexpress.manager.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ManagerMapper {
	
	Map<String, Object> selectManager(Map<String, Object> map);
	int updateManager(Map<String, Object> map);
	
}
