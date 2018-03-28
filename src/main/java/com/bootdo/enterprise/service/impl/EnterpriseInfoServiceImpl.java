package com.bootdo.enterprise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.enterprise.dao.EnterpriseInfoDao;
import com.bootdo.enterprise.domain.EnterpriseInfoDO;
import com.bootdo.enterprise.service.EnterpriseInfoService;



@Service
public class EnterpriseInfoServiceImpl implements EnterpriseInfoService {
	@Autowired
	private EnterpriseInfoDao enterpriseInfoDao;
	
	@Override
	public EnterpriseInfoDO get(Long id){
		return enterpriseInfoDao.get(id);
	}
	
	@Override
	public List<EnterpriseInfoDO> list(Map<String, Object> map){
		return enterpriseInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriseInfoDao.count(map);
	}
	
	@Override
	public int save(EnterpriseInfoDO enterpriseInfo){
		return enterpriseInfoDao.save(enterpriseInfo);
	}
	
	@Override
	public int update(EnterpriseInfoDO enterpriseInfo){
		return enterpriseInfoDao.update(enterpriseInfo);
	}
	
	@Override
	public int remove(Long id){
		return enterpriseInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return enterpriseInfoDao.batchRemove(ids);
	}
	
}
