
package cn.damei.web.modules;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.web.BaseController;
import cn.damei.service.modules.MaterialChangeBillInterfaceService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "${adminPath}/api/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBill")
public class MaterialChangeBillInterfaceController extends BaseController {

	@Autowired
	private MaterialChangeBillInterfaceService materialChangeBillInterfaceService;

	

	@RequestMapping(value = "receiveJsonDate")
	@ResponseBody
	public String receiveJsonData(HttpServletRequest request) throws IOException{	
		
		
		Map<String,Object> map = request.getParameterMap();
		
		Object object = map.get("data");
		JSONArray jsonArray = JSONArray.fromObject(object);
		
		List<Map<String, Object>> mapListJson = (List<Map<String, Object>>) jsonArray;
		Map<String, Object> obj = mapListJson.get(0);
		
		return materialChangeBillInterfaceService.receiveJsonData(obj);
	}

}