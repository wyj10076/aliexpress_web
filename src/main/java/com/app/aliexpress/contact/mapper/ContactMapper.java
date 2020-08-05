package com.app.aliexpress.contact.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ContactMapper {

	int insertContact(Map<String, Object> map);
	List<Map<String, Object>> selectContactList();
	Map<String, Object> selectContact(Map<String, Object> map);
	int replyContact(Map<String, Object> map);
	int updateReadYMDToSysdate(Map<String, Object> map);
}
