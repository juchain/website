package com.bootdo.enterprise.service;

import com.bootdo.enterprise.domain.EnterpriseInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author allen.gu
 * @email 1992lcg@163.com
 * @date 2018-03-27 16:44:41
 */
public interface EnterpriseInfoService {
	
	EnterpriseInfoDO get(Long id);
	
	List<EnterpriseInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EnterpriseInfoDO enterpriseInfo);
	
	int update(EnterpriseInfoDO enterpriseInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
