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

/**
 * 质检系统签到
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/app/pqc/checkList")
public class OrderToCheckListController {

	@Autowired
	private OrderToCheckListService orderToCheckListService;
	
	/**
	 * 约检的订单
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list")
	public String TimeToInspector(String timeForbidden,String text,Model model, HttpServletRequest request,String backUrl,String customerName) {

		Inspector inspector = SessionUtils.getInspectorSession(request);
		InspectorOrderVo vo = new InspectorOrderVo();
		if(text != null && !text.equals("")){
			vo.setText(text);
		}
		vo.setId(inspector.getId());
		// 根据质检员id 查询订单由项目经理申请约检的订单
		List<InspectorOrderVo> list = orderToCheckListService.findOrderByInspectorId(vo);
		for(InspectorOrderVo iov :list){
			//查出质检员是否有未评价工人的任务包
			iov.setNoScoreCount(orderToCheckListService.noScoreCount(iov.getOrderId(),inspector.getId()));
		}
		model.addAttribute("list", list);

		if(null!=backUrl &&!"".equals(backUrl)){

			model.addAttribute("backUrl",backUrl);
			model.addAttribute("customerName",customerName);
		}
		//提示确认验收按钮
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
	
		
		
		// 根据质检员id 查询订单由项目经理申请约检的订单
		List<InspectorOrderVo> list = orderToCheckListService.findOrderByInspectorId(vo);
		
	
		return list;
	}

	/**
	 * 到场签到
	 */
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

	/**
	 * 获取订单地址 经纬度
	 * 
	 * @param order
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getAddress", "" })
	public @ResponseBody JSONArray getAddress(OrderSignVo order, HttpServletRequest request, Model model) {

		String mapAddress = orderToCheckListService.getOrderLngLatByOrderId(order.getId());

		String[] split = mapAddress.split(",");

		return JSONArray.fromObject(split);
	}

//	/**
//	 * 质检员签到
//	 * @param request
//	 * @param sign
//	 * @param model
//	 * @param inspectId
//	 * @return
//	 */
//	@RequestMapping(value = "pqcsign", method = RequestMethod.POST)
//	public @ResponseBody String submitPic(HttpServletRequest request, InspectSign inspectSign, Model model,String inspectId) {
//		//0:先存储好数据
//		// 签到时间
//		inspectSign.setSignDateTime(new Date());
//		// 质检员信息id
//		inspectSign.setSignEmployeeId(SessionUtils.getInspectorSession(request).getId());
//		//质检单id
//		inspectSign.setRelatedBusinessId(Integer.parseInt(inspectId));
//		//登录人类型
//		inspectSign.setSignType("301");
//		//经纬度
//		Double  x =LngAndLatUtils.getLngAndLat(inspectSign.getSignAddress()).get("lng");
//		Double  y =LngAndLatUtils.getLngAndLat(inspectSign.getSignAddress()).get("lat");
//		inspectSign.setSignXy(x+","+y);
//		//1:根据质检单id,节点查询签到表
//		Integer  record =orderToCheckListService.findInspectSignRecord(Integer.parseInt(inspectId));
//		//2:如果该质检单已经签到过  只更新签到时间, 否则 保存该签到
//		
//		if(null!=record&&record!=0){
//		//已经签到过	
//		orderToCheckListService.updateInspectRecord(inspectSign);
//
//		return "OK";
//		}else{
//			//没有签到
//			orderToCheckListService.inspectorSign(inspectSign);
//			return "OK";
//			
//		}
//	}
	
	/**
	 * 质检  约检  签到
	 * @param detail
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "savepqcsign")
	public @ResponseBody String saveOrUpdateSign(InspectSign detail, Model model, HttpServletRequest request) {
		String flag = "error";
		try{
			// 获取质检员id
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
			
			//1:根据质检单id,节点查询签到表
			Integer  record =orderToCheckListService.findInspectSignRecord(detail.getRelatedBusinessId());
			//2:如果该质检单已经签到过  只更新签到时间, 否则 保存该签到
			if(null!=record&&record!=0){
				//已经签到过	
				orderToCheckListService.updateInspectRecord(inspectSign);
			}else{
				//没有签到
				orderToCheckListService.inspectorSign(inspectSign);
			}
			flag = "success";
		}catch(Exception e){
			flag = "error";
		}
		
		return flag;
	}
	

}
