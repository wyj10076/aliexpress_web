package com.app.aliexpress.primarycategory.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PrimaryCategoryMapper {

	List<Map<String, Object>> selectPrimaryCategoryList();
	int insertPrimaryCategoryList(Map<String, Object> map);
	int updatePrimaryCategoryList(Map<String, Object> map);
	int deletePrimaryCategoryList(Map<String, Object> map);
}
