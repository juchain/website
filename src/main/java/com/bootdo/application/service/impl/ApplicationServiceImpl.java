package com.bootdo.application.service.impl;

import com.bootdo.application.service.BlockShineWebCallService;
import com.bootdo.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
	public ApplicationDO get(Long id) {
		return applicationDao.get(id);
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
	public int remove(Long id) {
		return applicationDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids) {
		return applicationDao.batchRemove(ids);
	}


	@Override
	@Transactional
	public R createApplication(ApplicationDO application) {

		application.setCreated(new Date());
		application.setStatus(1);
		application.setUpdated(new Date());
		application.setAppKey(UUID.randomUUID().toString().replace("-",""));
		application.setAppSecret(UUID.randomUUID().toString().replace("-",""));

		application.setAppId(UUID.randomUUID().toString().replace("-",""));


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