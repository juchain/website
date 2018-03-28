package com.bootdo.enterprise.dao;

import com.bootdo.enterprise.domain.EnterpriseInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author allen.gu
 * @email 1992lcg@163.com
 * @date 2018-03-27 16:44:41
 */
@Mapper
public interface EnterpriseInfoDao {

	EnterpriseInfoDO get(Long id);
	
	List<EnterpriseInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EnterpriseInfoDO enterpriseInfo);
	
	int update(EnterpriseInfoDO enterpriseInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
