package com.bootdo.application.service.impl;

import com.bootdo.application.service.BlockShineWebCallService;
import com.bootdo.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.application.dao.ApplicationDao;
import com.bootdo.application.domain.ApplicationDO;
import com.bootdo.application.service.ApplicationService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ApplicationServiceImpl implements ApplicationService {
	@Autowired
	private ApplicationDao applicationDao;
	@Autowired
	private BlockShineWebCallService blockShineWebCallService;

	@Override
	public ApplicationDO get(Long appId) {
		return applicationDao.get(appId);
	}

	@Override
	public List<ApplicationDO> list(Map<String, Object> map) {
		return applicationDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return applicationDao.count(map);
	}

	@Override
	public int save(ApplicationDO application) {
		return applicationDao.save(application);
	}

	@Override
	public int update(ApplicationDO application) {
		return applicationDao.update(application);
	}

	@Override
	public int remove(Long appId) {
		return applicationDao.remove(appId);
	}

	@Override
	public int batchRemove(Long[] appIds) {
		return applicationDao.batchRemove(appIds);
	}


	@Override
	@Transactional
	public R createApplication(ApplicationDO application) {

		application.setCreated(new Date());
		application.setStatus(1);
		application.setUpdated(new Date());

		int save = applicationDao.save(application);
		R r = R.ok();
		if (save > 0) {
			r = blockShineWebCallService.bsw_newAddress(application);
			if (Integer.valueOf(0).equals(r.get("code"))) {
				r.put("msg", "应用创建成功");
			}
		} else {
			r.put("msg", "应用创建失败");
		}
		return r;


	}
}