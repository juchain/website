package com.bootdo.application.dao;

import com.bootdo.application.domain.ApplicationDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author allen.gu
 * @email 1992lcg@163.com
 * @date 2018-03-27 18:49:46
 */
@Mapper
public interface ApplicationDao {

	ApplicationDO get(Long appId);
	
	List<ApplicationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ApplicationDO application);
	
	int update(ApplicationDO application);
	
	int remove(Long app_id);
	
	int batchRemove(Long[] appIds);
}
