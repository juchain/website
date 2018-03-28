package com.bootdo.application.service;

import com.bootdo.application.domain.ApplicationDO;
import com.bootdo.common.utils.R;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author allen.gu
 * @email 1992lcg@163.com
 * @date 2018-03-27 18:49:46
 */
public interface ApplicationService {
	
	ApplicationDO get(Long appId);
	
	List<ApplicationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ApplicationDO application);
	
	int update(ApplicationDO application);
	
	int remove(Long appId);
	
	int batchRemove(Long[] appIds);

    R createApplication(ApplicationDO application);
}
