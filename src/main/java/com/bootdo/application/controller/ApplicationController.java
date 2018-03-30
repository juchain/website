package com.bootdo.application.controller;

import java.text.SimpleDateFormat;
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
	@RequiresPermissions("application:application:application")
	String Application(){
	    return "application/application";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("application:application:application")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ApplicationDO> applicationList = applicationService.list(query);
		int total = applicationService.count(query);
		PageUtils pageUtils = new PageUtils(applicationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("application:application:add")
	String add(){
	    return "application/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("application:application:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ApplicationDO application = applicationService.get(id);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		String pbd = sdf.format(application.getCreated());
		application.setCreatedString(pbd);
		model.addAttribute("applications", application);
	    return "application/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("application:application:add")
	public R save(ApplicationDO application){
		return  applicationService.createApplication(application);
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("application:application:edit")
	public R update(ApplicationDO application){
		applicationService.update(application);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("application:application:remove")
	public R remove( Long id){
		if(applicationService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("application:application:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		applicationService.batchRemove(ids);
		return R.ok();
	}



	
}
