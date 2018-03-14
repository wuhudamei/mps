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

/**
 * 
 * @author 梅浩
 * @2016年11月9日
 * @mdn大美装饰管理平台
 * @author_phone : 18610507472
 */
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

	/**
	 * 到场签到
	 */
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

		String mapAddress = service.getOrderLngLatByOrderId(order.getId());

		String[] split = mapAddress.split(",");
		return JSONArray.fromObject(split);
	}

//	/**
//	 * 质检员签到
//	 * 
//	 * @param request
//	 * @param sign
//	 * @param model
//	 * @param inspectId
//	 * @return
//	 */
//	@RequestMapping(value = "pqcsign", method = RequestMethod.POST)
//	public @ResponseBody String pqcsign(HttpServletRequest request, InspectSign inspectSign, Model model,
//			String recheckId) {
//		// 0:先存储好数据
//		// 签到时间
//		inspectSign.setSignDateTime(new Date());
//		// 质检员信息id
//		inspectSign.setSignEmployeeId(SessionUtils.getInspectorSession(request).getId());
//		// 复检单id
//		inspectSign.setRelatedBusinessId(Integer.parseInt(recheckId));
//		// 登录人类型 (复检)
//		inspectSign.setSignType("303");
//		// 经纬度
//		Double x = LngAndLatUtils.getLngAndLat(inspectSign.getSignAddress()).get("lng");
//		Double y = LngAndLatUtils.getLngAndLat(inspectSign.getSignAddress()).get("lat");
//		inspectSign.setSignXy(x + "," + y);
//		// 1:根据复检单id,节点查询签到表
//		Integer record = service.findInspectSignRecord(Integer.parseInt(recheckId));
//		// 2:如果该复检单已经签到过 只更新签到时间, 否则 保存该签到
//		if (null != record && record != 0) {
//			// 已经签到过
//			service.updateInspectRecord(inspectSign);
//
//			return "OK";
//		} else {
//			// 没有签到
//			service.inspectorSign(inspectSign);
//			return "OK";
//
//		}
//	}
	
	
	/**
	 * 
	 * @param detail
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "pqcsign")
	public @ResponseBody String pqcsign(InspectSign detail, Model model, HttpServletRequest request) {
		String flag = "error";
		try{
			// 获取项目经理id
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
			
			// 1:根据复检单id,节点查询签到表
			Integer record = service.findInspectSignRecord(detail.getRelatedBusinessId());
			//2:如果该质检单已经签到过  只更新签到时间, 否则 保存该签到
			if(null!=record&&record!=0){
				//已经签到过	
				service.updateInspectRecord(inspectSign);
			}else{
				//没有签到
				service.inspectorSign(inspectSign);
			}
			flag = "success";
		}catch(Exception e){
			flag = "error";
		}
		
		return flag;
	}
	
	

	/**
	 * 复检单的不合格检查项,照片
	 * 
	 * @return
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "checkRecheckCheckItem")
	public String checkRecheckCheckItem(String recheckId, Model model,String customerInfo) throws NumberFormatException, IOException {
		// 根据复检单 查询不合格的检查项
model.addAttribute("customerInfo",customerInfo);
		if (null != recheckId && !"".equals(recheckId)) {
			service.getCheckItemByRecheckId(Integer.parseInt(recheckId), model);
		}

		return "mobile/modules/pqc/recheck/repeat_check_details";
	}

	
	/**
	 * 保存质检员审核的复检信息 (检查项合格与否, 图片,次数+1)
	 */
	@RequestMapping(value = "saveRecheck") // 复检单id 图片 检查项id 是否合格
	public @ResponseBody String saveRecheck(String recheckId, String[] photo, String []checkItemId, String[] isPassed,HttpServletRequest request) {


		//检查质检单是否重复
		RecheckEntity recheck=service.findRecheckById(Integer.parseInt(recheckId==null?"0":recheckId));
	if(null!=recheck){

		if("3".equals(recheck.getRecheckStatus()) ||"4".equals(recheck.getRecheckStatus()) ){

		return "repeat";
	}

}
		//保存图片,如果新增了的话
		//保存检查项的检查结果,是否合格, 
		//更改复检单的状态,复检次数+1
		
				boolean b = service.recheckManRecheckedRecheckCheckItemAndPhoto(recheckId, photo, checkItemId, isPassed,request);
		if(b){
			return "OK";
		}else{
			return "NO";
		}
			
			
		
		
	}	
	
	/**
	 * 删除复检单照片
	 * @param picId
	 * @return
	 */
	@RequestMapping(value = "deletePic")
	public @ResponseBody String deletePic(String picId) {

		if (null != picId) {

			service.deletePic(Integer.parseInt(picId));
			return "OK";
		}

		return "NO";

	}

}
