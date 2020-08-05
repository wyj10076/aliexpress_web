package com.app.aliexpress.secondarycategory.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SecondaryCategoryMapper {
	
	List<Map<String, Object>> selectSecondaryCategoryList(Map<String, Object> map);
	Map<String, Object> selectSecondaryCategory(Map<String, Object> map);
	int insertSecondaryCategoryList(Map<String, Object> map);
	int updateSecondaryCategoryList(Map<String, Object> map);
	int deleteSecondaryCategoryList(Map<String, Object> map);
	int updateFileSecondaryCategory(Map<String, Object> map);
}
