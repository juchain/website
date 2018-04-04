package com.bootdo.enterprise.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.enterprise.domain.EnterpriseInfoDO;
import com.bootdo.enterprise.service.EnterpriseInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author allen.gu
 * @email 1992lcg@163.com
 * @date 2018-03-27 16:44:41
 */

@Controller
@RequestMapping("/enterprise/enterpriseInfo")
public class EnterpriseInfoController {
	@Autowired
	private EnterpriseInfoService enterpriseInfoService;
	
	@GetMapping()
	@RequiresPermissions("enterprise:enterpriseInfo:enterpriseInfo")
	String EnterpriseInfo(){
	    return "enterprise/enterpriseInfo/enterpriseInfo";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("enterprise:enterpriseInfo:enterpriseInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = null;
		if(MapUtils.isNotEmpty(params)){
			 query = new Query(params);
		}

		List<EnterpriseInfoDO> enterpriseInfoList = enterpriseInfoService.list(query);
		int total = enterpriseInfoService.count(query);
		PageUtils pageUtils = new PageUtils(enterpriseInfoList, total);
		return pageUtils;
	}





	@ResponseBody
	@GetMapping("/listAll")
	public List<EnterpriseInfoDO>listAll(){
		List<EnterpriseInfoDO> enterpriseInfoList = enterpriseInfoService.list(null);
		return enterpriseInfoList;
	}




	@GetMapping("/add")
	@RequiresPermissions("enterprise:enterpriseInfo:add")
	String add(){
	    return "enterprise/enterpriseInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("enterprise:enterpriseInfo:edit")
	String edit(@PathVariable("id") Long id,Model model){
		EnterpriseInfoDO enterpriseInfo = enterpriseInfoService.get(id);
		model.addAttribute("enterpriseInfo", enterpriseInfo);
	    return "enterprise/enterpriseInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("enterprise:enterpriseInfo:add")
	public R save( EnterpriseInfoDO enterpriseInfo){
		if(enterpriseInfoService.save(enterpriseInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("enterprise:enterpriseInfo:edit")
	public R update( EnterpriseInfoDO enterpriseInfo){
		enterpriseInfoService.update(enterpriseInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("enterprise:enterpriseInfo:remove")
	public R remove( Long id){
		if(enterpriseInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("enterprise:enterpriseInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		enterpriseInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
