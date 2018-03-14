/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.damei.common.web.BaseController;
import cn.damei.common.MD5Utils;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.SysAppVersion;
import cn.damei.service.modules.SysAppVersionService;
import net.sf.json.JSONObject;

/**
 * 手机app版本Controller
 * @author qww
 * @version 2016-12-27
 */
@Controller
@RequestMapping(value = "/api/appVersion")
public class AppVersionController extends BaseController {

	@Autowired
	private SysAppVersionService sysAppVersionService;

	/**
	 * 接口（查询app最新版本接口）
	 * @param appType
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "queryNewestAppVersion",method=RequestMethod.POST)
	public @ResponseBody String queryNewestAppVersion(String appType, String sign) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			// 1.验证sign
			Map<String, String> signMap = new HashMap<String, String>();
			signMap.put("appType", appType);
			if(!MD5Utils.MD5(signMap).equalsIgnoreCase(sign)){
				map.put("code", "406");
				map.put("message", "签名认证失败");
				return JSONObject.fromObject(map).toString();
			}
			
			if(StringUtils.isEmpty(appType)){
				map.put("code", "401");
				map.put("message", "必填参数为空");
				return JSONObject.fromObject(map).toString();
			}
			
			SysAppVersion version = sysAppVersionService.queryNewestVersion(appType);
			if(version != null){
				map.put("code", "200");
				map.put("message", "成功");
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", "1");
				data.put("version", version.getVersion());
				Properties props = new Properties();
		        props.load(AppVersionController.class.getClassLoader().getResourceAsStream("config.properties"));
		        String picUrlBase = props.getProperty("app_pic_url_base");
				data.put("url", picUrlBase + version.getDownloadUrl());
				map.put("data", data);
				return JSONObject.fromObject(map).toString();
			}else{
				map.put("code", "200");
				map.put("message", "成功");
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", "2");
				map.put("data", data);
				return JSONObject.fromObject(map).toString();
			}
		}catch(Exception e){
			map.put("code", "500");
			map.put("message", "系统异常");
			return JSONObject.fromObject(map).toString();
		}
	}
}