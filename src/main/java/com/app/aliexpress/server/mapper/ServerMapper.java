package com.app.aliexpress.server.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ServerMapper {

	Map<String, Object> selectServer(Map<String, Object> map);
	List<Map<String, Object>> selectServerList();
	int updateUseYNToN();
	int insertServer(Map<String, Object> map);
	int updateServer(Map<String, Object> map);
	int deleteServer(Map<String, Object> map);
}
