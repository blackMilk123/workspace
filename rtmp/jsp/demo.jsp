<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <!doctype html>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${staticPath }/css/screen/swiper.min.css"/>
</head>
<body>
<div>
    //初始化一个视频播放框
   <div class="media-box">
                        <div class="media" id="player" style="width: 100%;height:40%;">
                        </div>
                    </div>
</div>
<script src="${staticPath }/js/lib/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/sewise-player/sewise.player.min.js"></script>
<script>
    //进页面时加载
    $(function () {
        hospital04();
    });
    function hospital04() {

        var title = "${videoDeviceInfo3.videoName != null ? videoDeviceInfo3.videoName : videoDeviceInfo3.deviceName}";
        var videoUrl = "${videoDeviceInfo3.videoUrl}";
        var poster = '${staticPath}/images/index/bg.jpg';
        if (videoUrl.indexOf('rtmp://') == 0) {
            SewisePlayer.setup({
                server: "live",
                type: "rtmp",
                autostart: "true",
                streamurl: videoUrl,
                skin: "",
                title: title,
                claritybutton: "disable",//[可选]是否显示多码率按钮 "enable"、"disable"，缺省默认值为："enable"
                timedisplay: "disable",//[可选]是否显示播放控制栏上的时间 "enable"、"disable"，缺省默认值为："enable"
                controlbardisplay: "disable",//[可选]是否显示播放控制栏 "enable"、"disable"，缺省默认值为："enable"
                topbardisplay: "disable",//[可选]是否显示顶部标题 "enable"、"disable"，缺省默认值为："enable"
                draggable: false,
                buffer: 0,
                primary: "html5",
                lang: "zh_CN",
                poster: poster,//[可选]视频播放前显示的图像
                logo: " ",//[可选]播放器角落logo
            }, "player");
        } else if (videoUrl.indexOf('.m3u8') > -1) {
            SewisePlayer.setup({
                server: "vod",
                type: "m3u8",
                autostart: "true",
                videourl: videoUrl,
                skin: "vodFlowPlayer",
                title: "",
                claritybutton: "disable",
                lang: "zh_CN",
                logo: "",
            }, "player");
        } else if (videoUrl.indexOf('.flv') > -1) {
            SewisePlayer.setup({
                server: "vod",
                type: "flv",
                autostart: "true",
                videourl: videoUrl,
                skin: "vodWhite",
                title: "",
                claritybutton: "disable",
                lang: "zh_CN",
                logo: "",
            }, "player");
        }
    }

</script>
</body>

</html>