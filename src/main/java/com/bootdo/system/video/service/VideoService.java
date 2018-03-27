package com.bootdo.system.video.service;

import com.bootdo.common.utils.R;

/**
 * 视频点播服务
 */
public interface VideoService {

   R  uploadLocalVideo(String title,String fileName);
   R  uploadURLStream(String title,String fileName,String url);

    /**
     * 获取点播视频播放凭证
     * @param videoId
     * @return
     */
    R getVideoPlayAuth(String videoId);

}
