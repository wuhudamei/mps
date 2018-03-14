package cn.damei.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.modules.PackageInfo;
import cn.damei.entity.modules.PmSettleInfo;
import cn.damei.service.modules.PackageInfoService;
import cn.damei.service.modules.PmSettleInfoService;
import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.utils.KeyAuthenticateUtils2;
import cn.damei.common.web.BaseController;

import net.sf.json.JSONObject;

/**
 * 综管系统和产业工人打通接口-基装成本
 * 
 * @author hyh
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/api/baseLoadingCost")
public class BaseLoadingCostController extends BaseController {

	@Autowired
	private PackageInfoService packageInfoService;
	
	@Autowired
	private PmSettleInfoService pmSettleInfoService;

	@RequestMapping(value = "findOrderTaskpackageInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Object findOrderTaskpackageInfo(@RequestParam String orderId, String key) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (orderId == null || key == null) {
			map.put("code", "0");
			map.put("message", "必填参数为空");
			return JSONObject.fromObject(map).toString();
		}
		String[] paramArr = new String[] { "orderId=" + orderId };
		String myKey = KeyAuthenticateUtils2.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
		if (!myKey.equals(key)) {// 签名认证失败
			map.put("code", "0");
			map.put("message", "签名认证失败");
			return JSONObject.fromObject(map).toString();
		}
		List<PackageInfo> packageInfoList = packageInfoService.queryPackInfoByOrderNumber(orderId);
		if (packageInfoList == null || packageInfoList.size() == 0) {
			map.put("code", "0");
			map.put("message", "不存在有效数据");
			return JSONObject.fromObject(map).toString();
		}
		map.put("code", "1");
		map.put("message", "成功");
		map.put("packageInfo",packageInfoList);
		return JSONObject.fromObject(map).toString();
	}
	
	@RequestMapping(value = "queryPmSettleInfoByOrderNumber", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Object queryPmSettleInfoByOrderNumber(@RequestParam String orderId, String key){
		Map<String, Object> map = new HashMap<String, Object>();
		if (orderId == null || key == null) {
			map.put("code", "0");
			map.put("message", "必填参数为空");
			return JSONObject.fromObject(map).toString();
		}
		String[] paramArr = new String[] { "orderId=" + orderId };
		String myKey = KeyAuthenticateUtils2.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
		if (!myKey.equals(key)) {// 签名认证失败
			map.put("code", "0");
			map.put("message", "签名认证失败");
			return JSONObject.fromObject(map).toString();
		}
		List<PmSettleInfo> pmSettleInfoList = pmSettleInfoService.queryPmSettleInfoByOrderNumber(orderId);
		if (pmSettleInfoList == null || pmSettleInfoList.size() == 0) {
			map.put("code", "0");
			map.put("message", "不存在有效数据");
			return JSONObject.fromObject(map).toString();
		}
		map.put("code", "1");
		map.put("message", "成功");
		map.put("pmSettleInfo",pmSettleInfoList);
		return JSONObject.fromObject(map).toString();
	}

}
