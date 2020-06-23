package com.hys.videoService;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;

/**
 * @Auth 86191
 * @Date 2020/4/19
 */
public class uploadVideoToAliyun {

    //账号AK信息请填写(必选)
    private static final String accessKeyId = "LTAI4FsjFUENH4wsNC8Ekgib";
    //账号AK信息请填写(必选)
    private static final String accessKeySecret = "oaiKUWKNuWvAWVMEiaGBTiOgygZea8";

    public static void main(String[] args) {

        String title = "testVideo1";   //上传之后文件名称
        String fileName = "D:\\java视频\\尚硅谷谷粒学院项目视频教程\\1-阿里云上传测试视频\\6 - What If I Want to Move Faster.mp4";  //本地文件路径和名称
        //上传视频的方法
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }

    }
}
