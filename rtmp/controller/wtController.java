
@Controller
@RequestMapping("/waterMachineAnalysis")
public class WaterMachineAnalysisController extends BaseController<WaterMachineAnalysis> {

//获取指定站点的视频监控数据
	@RequestMapping("/getVideo")
	@ResponseBody
	public Object getVideo(HttpServletRequest request){
		String id = request.getParameter("id");
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("id",id);
		VideoDeviceInfo videoDeviceInfo = videoDeviceInfoService.selectOne(map1);
		if (videoDeviceInfo != null) {
			if (com.alibaba.druid.util.StringUtils.isEmpty(videoDeviceInfo.getVideoUrl())) {
				Return<VideoAppInfo> retVideoAppInfo = videoAppInfoService.updateVideoAppInfoToken(videoDeviceInfo.getAppId());
				if (retVideoAppInfo.isSuccess()) {
					videoDeviceInfoService.updateVideoDeviceInfoOnline(videoDeviceInfo, retVideoAppInfo.getData());
				}
			}
		}
		if (videoDeviceInfo == null){
			return new VideoDeviceInfo();
		}
		return  videoDeviceInfo;
	}
	}