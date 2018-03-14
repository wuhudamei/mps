
package cn.damei.web.modules;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.damei.common.utils.DateUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.web.BaseController;
import cn.damei.service.modules.MaterialInterfaceService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "${adminPath}/api/bizmaterialchoicebill/bizMaterialsChoiceBill")
public class MaterialInterfaceController extends BaseController {

	@Autowired
	private MaterialInterfaceService materialInterfaceService;

	private Logger log = LoggerFactory.getLogger(MaterialInterfaceController.class);
	


	@RequestMapping(value = "receiveJsonDate")
	@ResponseBody
	public String receiveJsonData(HttpServletRequest request) throws IOException{

		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = request.getParameterMap();
		
		Object object = map.get("data");
		JSONArray jsonArray = JSONArray.fromObject(object);
		
		List<Map<String, Object>> mapListJson = (List<Map<String, Object>>) jsonArray;
		Map<String, Object> obj = mapListJson.get(0);
		String s="";
		try{
			 s = materialInterfaceService.receiveJsonData(obj);

		}catch (Exception e){
			log.error("==========选材清单接收数据异常!!　时间为: "+date +"　  　选材清单接口接收数据为: " +jsonArray.toString()+"===========");
			log.error("==========选材清单接收数据异常!!　时间为: "+date +"　  　选材清单接口接收数据为: " +jsonArray.toString()+"===========");
			log.error("==========选材清单接收数据异常!!　时间为: "+date +"　  　选材清单接口接收数据为: " +jsonArray.toString()+"===========");
			log.error("==========选材清单接口异常!! =====  异常时间:"+date+"=====异常为: "+ e);


			Map<String,String> resultMap = new HashMap<String,String>();
			resultMap.put("code", "500");
			resultMap.put("message", "保存失败");
			s = JSONObject.fromObject(resultMap).toString();
		}

		return s;
	}
	
	
	
	
	

}