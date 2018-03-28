package com.bootdo.application.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.application.domain.ApplicationDO;
import com.bootdo.application.service.ApplicationService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author allen.gu
 * @email 1992lcg@163.com
 * @date 2018-03-27 18:49:46
 */
 
@Controller
@RequestMapping("/application")
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;
	
	@GetMapping()
	//@RequiresPermissions("application:application:application")
	String Application(){
	    return "application/application/application";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("application:application:application")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ApplicationDO> applicationList = applicationService.list(query);
		int total = applicationService.count(query);
		PageUtils pageUtils = new PageUtils(applicationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	//@RequiresPermissions("application:application:add")
	String add(){
	    return "application/application/add";
	}

	@GetMapping("/edit/{appId}")
	//@RequiresPermissions("application:application:edit")
	String edit(@PathVariable("appId") Long appId,Model model){
		ApplicationDO application = applicationService.get(appId);
		model.addAttribute("application", application);
	    return "application/application/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("application:application:add")
	public R save(@RequestBody ApplicationDO application){
		return  applicationService.createApplication(application);
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	//@RequiresPermissions("application:application:edit")
	public R update(@RequestBody ApplicationDO application){
		applicationService.update(application);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	//@RequiresPermissions("application:application:remove")
	public R remove( Long appId){
		if(applicationService.remove(appId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	//@RequiresPermissions("application:application:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] appIds){
		applicationService.batchRemove(appIds);
		return R.ok();
	}



	
}
