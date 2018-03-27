package com.bootdo.blog.controller;

import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * @author bootdo 1992lcg@163.com
 */
@RequestMapping("/notice")
@Controller
@Api(description = "公告api", position = 1)
public class NoticeController {
	
	@Autowired
    ContentService bContentService;

	@GetMapping()
	String blog() {
		return "notice/index/main";
	}

	@ResponseBody
	@GetMapping("/open/list")
	@ApiOperation(value = "公告列表")
	public R opentList(@RequestParam int offset,@RequestParam int limit) {
		Map<String,Object> params = new HashMap<>();
		params.put("offset",offset);
		params.put("limit",limit);

		params.put("isTop", 0);
		params.put("type", Constant.TYPE_NOTICE);
		params.put("status", 1);

		Query query1 = new Query(params);
		List<ContentDO> notTopList = bContentService.listNotTop(query1);




		Map params2 = new HashMap();
		params2.putAll(params);

		params2.put("isTop", 1);


		Query query2 = new Query(params2);
		List<ContentDO> topList = bContentService.listTop(query2);

		LinkedList<ContentDO> resultList =new LinkedList<>();
		resultList.addAll(topList);
		resultList.addAll(notTopList);

		Map params3 = new HashMap();
		params3.put("type", Constant.TYPE_NOTICE);
		params3.put("status", 1);

		PageUtils pageUtils = new PageUtils(resultList, bContentService.count(params3));

		R r = R.ok();
		r.put("data",pageUtils);
		return r;




	}
	
	@GetMapping("/list")
	@ResponseBody
	@ApiOperation(value = "公告列表")
	public List<ContentDO> newsList() {
		List<ContentDO> bContentList = bContentService.news(Constant.TYPE_NOTICE);
		return bContentList;
	}


	@GetMapping("/open/post/{cid}")
	@ApiOperation(value = "根据id获取单个公告")
	@ResponseBody
	String post(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
//		model.addAttribute("bContent", bContentDO);
//		model.addAttribute("gtmModified", DateUtils.format(bContentDO.getGtmModified()));
//		return "blog/index/post";


		return bContentDO.getContent();
	}



	@GetMapping("/open/post1/{cid}")
	@ApiOperation(value = "根据id获取单个公告")
	@ResponseBody
	R post(@PathVariable("cid") Long cid) {
		ContentDO bContentDO = bContentService.get(cid);

		R r = R.ok();
		if(bContentDO!=null){
			r.put("data",bContentDO);
		}else{
			r.put("data","");
		}
		return r;
	}

	@ApiOperation(value = "根据类别获取公告")
	@GetMapping("/open/page/{categories}")
	String about(@PathVariable("categories") String categories, Model model) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("categories", categories);
		map.put("type", Constant.TYPE_NOTICE);
		ContentDO bContentDO =null;
		if(bContentService.list(map).size()>0){
			 bContentDO = bContentService.list(map).get(0);
		}
		model.addAttribute("bContent", bContentDO);
		return "notice/index/post";
	}
}
