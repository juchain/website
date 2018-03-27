package com.bootdo.system.video.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.R;

import com.bootdo.system.video.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/vod")
@Api(description = "视频相关接口", position = 2)
public class VODController extends BaseController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/playAuth")
    @ResponseBody
    @ApiOperation("获取点播视频播放凭证")
    public R applyPlayAuth(@RequestParam String videoId){
        return videoService.getVideoPlayAuth(videoId);
    }
}
