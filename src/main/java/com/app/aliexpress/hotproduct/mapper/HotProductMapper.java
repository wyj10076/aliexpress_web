package com.app.aliexpress.hotproduct.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface HotProductMapper {

	List<Map<String, Object>> selectHotProductList(Map<String, Object> map);
	int insertHotProductList(Map<String, Object> map);
	int deleteHotProductList(Map<String, Object> map);
}
