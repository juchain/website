package com.bootdo.system.video.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadURLStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.bootdo.common.utils.R;

import com.bootdo.system.video.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 点播视频服务
 * @author maxiaodong
 */
@Service
public class VideoServiceImpl implements VideoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * accessKeyId、accessKeySecret需要支持动态配置
     */
    static final String accessKeyId = "LTAIZVzt34519H5R";
    static final String accessKeySecret = "PhhStC9Uv6gIJIkOSb1MP0Rg44a8Gt";

    static DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKeyId, accessKeySecret);



    /**
     * 本地文件上传接口
     * @param title
     * @param fileName
     */
    @Override
    public R uploadLocalVideo(String title,String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        request.setPartSize(10 * 1024 * 1024L);     //可指定分片上传时每个分片的大小，默认为10M字节
        request.setTaskNum(1);                      //可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）
        request.setIsShowWaterMark(true);           //是否使用默认水印
        request.setCallback("http://callback.sample.com");  //设置上传完成后的回调URL(可选)
        request.setCateId(0);                       //视频分类ID(可选)
        request.setTags("标签1,标签2");              //视频标签,多个用逗号分隔(可选)
        request.setDescription("视频描述");          //视频描述(可选)
        request.setCoverURL("http://cover.sample.com/sample.jpg"); //封面图片(可选)
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        R r =R.ok();
        if (response.isSuccess()) {
            logger.info("VideoId=" + response.getVideoId() + "\n");
            r.put("videoId",response.getVideoId());
        } else {
            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            logger.info("VideoId=" + response.getVideoId() + "\n");
            logger.info("ErrorCode=" + response.getCode() + "\n");
            logger.info("ErrorMessage=" + response.getMessage() + "\n");
            r.put("videoId",response.getVideoId());
            r.put("code",response.getCode());
            r.put("msg",response.getMessage());
        }
        return r;
    }




    /**
     * 网络流上传接口
     * @param title
     * @param fileName
     * @param url
     */
    @Override
    public R uploadURLStream(String title,String fileName,String url) {
        UploadURLStreamRequest request = new UploadURLStreamRequest(accessKeyId, accessKeySecret, title, fileName, url);
        request.setShowWaterMark(true);             //是否使用默认水印
        request.setCallback("http://callback.sample.com");//设置上传完成后的回调URL(可选)
        request.setCateId(0);                       //视频分类ID(可选)
        request.setTags("标签1,标签2");              //视频标签,多个用逗号分隔(可选)
        request.setDescription("视频描述");          //视频描述(可选)
        request.setCoverURL("http://cover.sample.com/sample.jpg");  //封面图片(可选)
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadURLStreamResponse response = uploader.uploadURLStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n"); //请求视频点播服务的请求ID
        R r =R.ok();
        if (response.isSuccess()) {
            logger.info("VideoId=" + response.getVideoId() + "\n");
            r.put("videoId",response.getVideoId());
        } else {
            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            logger.info("VideoId=" + response.getVideoId() + "\n");
            logger.info("ErrorCode=" + response.getCode() + "\n");
            logger.info("ErrorMessage=" + response.getMessage() + "\n");
            r.put("videoId",response.getVideoId());
            r.put("code",response.getCode());
            r.put("msg",response.getMessage());
        }
        return r;
    }

    @Override
    public R getVideoPlayAuth(String videoId) {

        DefaultAcsClient client = new DefaultAcsClient(profile);

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();

        request.setVideoId(videoId);//视频ID

        GetVideoPlayAuthResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            logger.error("GetVideoPlayAuthRequest Server failed", e);
            return R.error("GetVideoPlayAuthRequest Server failed");

        } catch (ClientException e) {

            logger.error("GetVideoPlayAuthRequest Client failed", e);
            return R.error("GetVideoPlayAuthRequest Client failed");
        }

        R r = new R();
        r.put("playAuth", response.getPlayAuth());//播放凭证

        return r;
    }
}
