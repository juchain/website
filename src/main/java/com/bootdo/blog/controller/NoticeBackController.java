package com.bootdo.blog.controller;

import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 文章内容
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-09 10:03:34
 */
@Controller
@RequestMapping("/notice/bContent")
public class NoticeBackController extends BaseController {
	@Autowired
    ContentService bContentService;

	@GetMapping()
	@RequiresPermissions("notice:bContent:bContent")
	String bContent() {
		return "notice/bContent/bContent";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("notice:bContent:bContent")
	public PageUtils list(@RequestParam Map<String, Object> params) {

		params.put("type", Constant.TYPE_NOTICE);
		params.put("isTop", 0);
		Query query1 = new Query(params);
		List<ContentDO> notTopList = bContentService.listNotTop(query1);




		params.put("isTop", 1);
		params.put("type", Constant.TYPE_NOTICE);
		Query query2 = new Query(params);
		List<ContentDO> topList = bContentService.listTop(query2);

		LinkedList<ContentDO> resultList =new LinkedList<>();
		resultList.addAll(topList);
		resultList.addAll(notTopList);

		Map params3 = new HashMap();
		params3.put("type", Constant.TYPE_NOTICE);

		PageUtils pageUtils = new PageUtils(resultList, bContentService.count(params3));




		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("notice:bContent:add")
	String add() {
		return "notice/bContent/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("notice:bContent:edit")
	String edit(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		String pbd = sdf.format(bContentDO.getPublishDate());
		bContentDO.setPbd(pbd);
		model.addAttribute("bContent", bContentDO);
		return "notice/bContent/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequiresPermissions("notice:bContent:add")
	@PostMapping("/save")
	public R save(ContentDO bContent) {

		bContent.setType(Constant.TYPE_NOTICE);
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (bContent.getAllowComment() == null) {
			bContent.setAllowComment(0);
		}
		if (bContent.getAllowFeed() == null) {
			bContent.setAllowFeed(0);
		}
		if(null==bContent.getType()) {
			bContent.setType("article");
		}
		if("news".equals(bContent.getCategories())) {
			//从类型判断 去除富文本的 p标签
			bContent.setContent(bContent.getContent().replace("<p>", "").replace("</p>", ""));
		}
//		if (bContent.getContent().contains("<p>")||bContent.getContent().contains("</p>")) {
//			//直接从内容判断 去除富文本的 p标签
//			bContent.getContent().replaceAll("<p>", "").replaceAll("</p>", "");
//		}
		
		
		bContent.setGtmCreate(new Date());
		bContent.setGtmModified(new Date());
		int count;
		if (bContent.getCid() == null || "".equals(bContent.getCid())) {
			count = bContentService.save(bContent);
		} else {
			count = bContentService.update(bContent);
		}
		if (count > 0) {
			return R.ok().put("cid", bContent.getCid());
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@RequiresPermissions("notice:bContent:edit")
	@ResponseBody
	@RequestMapping("/update")
	public R update( ContentDO bContent) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		bContent.setGtmCreate(new Date());
		bContentService.update(bContent);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("notice:bContent:remove")
	@PostMapping("/remove")
	@ResponseBody
	public R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (bContentService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("notice:bContent:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] cids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		bContentService.batchRemove(cids);
		return R.ok();
	}

	/**
	 * 置顶
	 */
	@RequiresPermissions("notice:bContent:top")
	@PostMapping("/top")
	@ResponseBody
	public R top(@RequestParam Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		bContentService.updateTop(id);
		return R.ok();
	}
}
