package com.bootdo.system.video.on.demand.playauth;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;

/**
 * 获取视频播放凭证
 *
 * @author maxiaodong
 */
public class VideoPlayAuthDemo {
    static final String accessKeyId = "LTAIZVzt34519H5R";
    static final String accessKeySecret = "PhhStC9Uv6gIJIkOSb1MP0Rg44a8Gt";

    static DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKeyId, accessKeySecret);

    static  GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();

        request.setVideoId("561854c2f5c742c0915f00e2e3f1a5d8");//视频ID
        //request.setVideoId("afb9d951f6844309a867652b3492d737");//视频ID

        GetVideoPlayAuthResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.getStackTrace();
            throw new RuntimeException("GetVideoPlayAuthRequest Server failed");
        } catch (ClientException e) {
            e.getStackTrace();
            throw new RuntimeException("GetVideoPlayAuthRequest Client failed");
        }
        response.getPlayAuth();              //播放凭证
        System.out.println("播放凭证：" + response.getPlayAuth());
        response.getVideoMeta();             //视频Meta信息
        return response;
    }

    public static void main(String[] args) {
        DefaultAcsClient client = new DefaultAcsClient(profile);
        getVideoPlayAuth(client);
    }


}
