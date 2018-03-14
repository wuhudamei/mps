package cn.damei.web.mobile.Inspector;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.InspectSign;
import cn.damei.entity.mobile.Inspector.InspectorOrderVo;
import cn.damei.service.mobile.Inspector.OrderToCheckListService;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Manager.OrderSignVo;


@Controller
@RequestMapping(value = "${adminPath}/app/pqc/checkList")
public class OrderToCheckListController {

	@Autowired
	private OrderToCheckListService orderToCheckListService;
	

	@RequestMapping(value = "list")
	public String TimeToInspector(String timeForbidden,String text,Model model, HttpServletRequest request,String backUrl,String customerName) {

		Inspector inspector = SessionUtils.getInspectorSession(request);
		InspectorOrderVo vo = new InspectorOrderVo();
		if(text != null && !text.equals("")){
			vo.setText(text);
		}
		vo.setId(inspector.getId());

		List<InspectorOrderVo> list = orderToCheckListService.findOrderByInspectorId(vo);
		for(InspectorOrderVo iov :list){

			iov.setNoScoreCount(orderToCheckListService.noScoreCount(iov.getOrderId(),inspector.getId()));
		}
		model.addAttribute("list", list);

		if(null!=backUrl &&!"".equals(backUrl)){

			model.addAttribute("backUrl",backUrl);
			model.addAttribute("customerName",customerName);
		}

		model.addAttribute("timeForbidden",timeForbidden );
		model.addAttribute("text",text );
		return "mobile/modules/pqc/check/date_check";
	}
	@RequestMapping(value = "ajax_search_list")
	public @ResponseBody List<InspectorOrderVo> ajaxSearchList(Model model, HttpServletRequest request,String text) {
		
		Inspector inspector = SessionUtils.getInspectorSession(request);
		InspectorOrderVo vo = new InspectorOrderVo();
		vo.setId(inspector.getId());
		
		
			vo.setText(text);
	
		
		

		List<InspectorOrderVo> list = orderToCheckListService.findOrderByInspectorId(vo);
		
	
		return list;
	}


	@RequestMapping(value = { "pqcsign", "" })
	public String signByGPS(HttpServletRequest request, Model model, InspectorOrderVo vo) {

		String mapAddress = orderToCheckListService.getOrderLngLatByOrderId(vo.getOrderId());
		String[] split = mapAddress.split(",");
		model.addAttribute("lon", split[0]);
		model.addAttribute("lat", split[1]);
		model.addAttribute("inspectId", vo.getId());
		model.addAttribute("orderId", vo.getOrderId());
		return "mobile/modules/pqc/check/sign/map";
	}


	@RequestMapping(value = { "getAddress", "" })
	public @ResponseBody JSONArray getAddress(OrderSignVo order, HttpServletRequest request, Model model) {

		String mapAddress = orderToCheckListService.getOrderLngLatByOrderId(order.getId());

		String[] split = mapAddress.split(",");

		return JSONArray.fromObject(split);
	}








































	

	@RequestMapping(value = "savepqcsign")
	public @ResponseBody String saveOrUpdateSign(InspectSign detail, Model model, HttpServletRequest request) {
		String flag = "error";
		try{

			Inspector inspector = SessionUtils.getInspectorSession(request);
			Date date = new Date();
			
			InspectSign inspectSign = new InspectSign();
			
			inspectSign.setSignType(ConstantUtils.SIGN_TYPE_INSPECTOR_CHECK_301);
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
			

			Integer  record =orderToCheckListService.findInspectSignRecord(detail.getRelatedBusinessId());

			if(null!=record&&record!=0){

				orderToCheckListService.updateInspectRecord(inspectSign);
			}else{

				orderToCheckListService.inspectorSign(inspectSign);
			}
			flag = "success";
		}catch(Exception e){
			flag = "error";
		}
		
		return flag;
	}
	

}
