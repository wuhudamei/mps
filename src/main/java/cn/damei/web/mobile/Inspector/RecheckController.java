package cn.damei.web.mobile.Inspector;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.InspectSign;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Inspector.RecheckEntity;
import cn.damei.service.mobile.Inspector.RecheckService;
import cn.damei.entity.mobile.Manager.OrderSignVo;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "${adminPath}/app/pqc/recheck")
public class RecheckController {

	@Autowired
	private RecheckService service;

	@RequestMapping(value = "recheckList")
	public String recheckList(HttpServletRequest request, Model model) {
		Inspector inspector = SessionUtils.getInspectorSession(request);
		List<RecheckEntity> recheckList = service.findRecheckList(inspector.getId());
		if (null != recheckList && recheckList.size() > 0) {

			model.addAttribute("list", recheckList);

		} else {

			model.addAttribute("error", "您暂时没有需要检查的复检单");
		}

		return "mobile/modules/pqc/recheck/repeat_check";
	}


	@RequestMapping(value = { "signByGPS", "" })
	public String signByGPS(HttpServletRequest request, Model model) {
		String orderId = request.getParameter("orderId");
		String recheckId = request.getParameter("recheckId");
		
		String mapAddress = service.getOrderLngLatByOrderId(Integer.valueOf(orderId));
		String[] split = mapAddress.split(",");
		model.addAttribute("lon", split[0]);
		model.addAttribute("lat", split[1]);
		
		model.addAttribute("orderId", orderId);
		model.addAttribute("recheckId", recheckId);
		return "mobile/modules/pqc/recheck/map";
	}


	@RequestMapping(value = { "getAddress", "" })
	public @ResponseBody JSONArray getAddress(OrderSignVo order, HttpServletRequest request, Model model) {

		String mapAddress = service.getOrderLngLatByOrderId(order.getId());

		String[] split = mapAddress.split(",");
		return JSONArray.fromObject(split);
	}









































	
	

	@RequestMapping(value = "pqcsign")
	public @ResponseBody String pqcsign(InspectSign detail, Model model, HttpServletRequest request) {
		String flag = "error";
		try{

			Inspector inspector = SessionUtils.getInspectorSession(request);
			Date date = new Date();
			
			InspectSign inspectSign = new InspectSign();
			
			inspectSign.setSignType(ConstantUtils.SIGN_TYPE_INSPECTOR_CHECK_303);
			inspectSign.setRelatedBusinessId(detail.getRelatedBusinessId());
			inspectSign.setSignEmployeeId(inspector.getId());
			inspectSign.setSignDateTime(date);
			inspectSign.setSignAddress(detail.getSignAddress());
			inspectSign.setSignXy(detail.getLon()+","+detail.getLat());
			inspectSign.setSignErrorDistance(detail.getSignErrorDistance());
			inspectSign.setCreateBy(inspector.getId());
			inspectSign.setCreateDate(date);
			inspectSign.setUpdateBy(inspector.getId());
			inspectSign.setUpdateDate(date);
			

			Integer record = service.findInspectSignRecord(detail.getRelatedBusinessId());

			if(null!=record&&record!=0){

				service.updateInspectRecord(inspectSign);
			}else{

				service.inspectorSign(inspectSign);
			}
			flag = "success";
		}catch(Exception e){
			flag = "error";
		}
		
		return flag;
	}
	
	


	@RequestMapping(value = "checkRecheckCheckItem")
	public String checkRecheckCheckItem(String recheckId, Model model,String customerInfo) throws NumberFormatException, IOException {

model.addAttribute("customerInfo",customerInfo);
		if (null != recheckId && !"".equals(recheckId)) {
			service.getCheckItemByRecheckId(Integer.parseInt(recheckId), model);
		}

		return "mobile/modules/pqc/recheck/repeat_check_details";
	}

	

	@RequestMapping(value = "saveRecheck")
	public @ResponseBody String saveRecheck(String recheckId, String[] photo, String []checkItemId, String[] isPassed,HttpServletRequest request) {



		RecheckEntity recheck=service.findRecheckById(Integer.parseInt(recheckId==null?"0":recheckId));
	if(null!=recheck){

		if("3".equals(recheck.getRecheckStatus()) ||"4".equals(recheck.getRecheckStatus()) ){

		return "repeat";
	}

}



		
				boolean b = service.recheckManRecheckedRecheckCheckItemAndPhoto(recheckId, photo, checkItemId, isPassed,request);
		if(b){
			return "OK";
		}else{
			return "NO";
		}
			
			
		
		
	}	
	

	@RequestMapping(value = "deletePic")
	public @ResponseBody String deletePic(String picId) {

		if (null != picId) {

			service.deletePic(Integer.parseInt(picId));
			return "OK";
		}

		return "NO";

	}

}
