package com.bootdo.blog.controller;

import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(description = "官网", position = 1)
public class WebSiteController {
	@Autowired
    ContentService bContentService;
	
	@GetMapping("/julian")
	@ApiOperation(value = "聚链官网")
	@ResponseBody
	String post() {
		ContentDO bContentDO = bContentService.get(249L);
	    return bContentDO.getContent();
	}

}
