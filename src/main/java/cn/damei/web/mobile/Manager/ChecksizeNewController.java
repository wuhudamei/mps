package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.damei.service.modules.BizOrderChecksizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Checksize;
import cn.damei.entity.mobile.Manager.ChecksizeOrder;
import cn.damei.entity.mobile.Manager.ChecksizeType;
import cn.damei.service.mobile.Manager.ChecksizeNewService;
import cn.damei.entity.mobile.Manager.OrderInstallPlan;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.modules.BizOrderChecksizePic;



@Controller
@RequestMapping(value="${adminPath}/app/manager/checksize")
public class ChecksizeNewController {

	@Autowired
	private ChecksizeNewService checksizeNewService;
	@Autowired
	private BizOrderChecksizeService bizOrderChecksizeService;
	
	

	@RequestMapping(value={"checksizeList",""})
	public String checksizeList(HttpServletRequest request,Model model){
	
		return "mobile/modules/Manager/progressMain/checksize/new/reRulerList";
	}
	
	

	@RequestMapping(value = "checksize_order_list_ajax")
	public @ResponseBody List<ChecksizeOrder> checksizeOrderListAjax(String text,HttpServletRequest request) {


		Manager manager = SessionUtils.getManagerSession(request);
		List<ChecksizeOrder> list = null;
		if (null!=manager && null != manager.getId()) {

			list = checksizeNewService.findOrderByManagerId(manager.getId(),text);
		}
		return list;

	}
	
	

	@RequestMapping(value = "check_checksize_data_ajax")
	@ResponseBody
	public String checkChecksizeDataAjax(String orderId,String type,String orderInstallItemId) {

		return checksizeNewService.queryChecksizeCheckData(orderId,type,orderInstallItemId);
	}
	
	
	

	@RequestMapping(value={"applyChecksize",""})
	public String applyChecksize(String orderId,HttpServletRequest request,Model model){
		
		
		ChecksizeOrder order = null;
		List<ChecksizeType> list = null;
		if(StringUtils.isNotBlank(orderId)){

			order = checksizeNewService.findOrder(Integer.valueOf(orderId));

			list = checksizeNewService.findChecksizeTypeList(Integer.valueOf(orderId));
		}
		
		model.addAttribute("order", order);
		model.addAttribute("list", list);
		
		return "mobile/modules/Manager/progressMain/checksize/new/reRuler";
	}
	
	

	@RequestMapping(value = "apply_checksize_submit_ajax", method = RequestMethod.POST)
	@ResponseBody
	public  String applyChecksizeSubmitAjax(String orderId,String checksizeDate,String orderInstallItemId,String remarks,String[] photo,HttpServletRequest request) throws ParseException {
		
		Manager manager = SessionUtils.getManagerSession(request);
		return checksizeNewService.saveChecksize(orderId,checksizeDate,orderInstallItemId,remarks,photo,request,manager);

	}

	
	

	@RequestMapping(value={"checksizeRecord",""})
	public String checksizeRecord(String orderId,HttpServletRequest request,Model model){
		
		model.addAttribute("orderId", orderId);
		return "mobile/modules/Manager/progressMain/checksize/new/reRulerDetails";
	}
	
	

	@RequestMapping(value = "checksize_record_list_ajax")
	public @ResponseBody List<Checksize> checksizeRecordListAjax(Integer orderId,String text,HttpServletRequest request) {
		

		return checksizeNewService.findCheckSizeList(orderId,text);

	}
	

	@RequestMapping(value={"checksizeRecordPic",""})
	public String checksizeRecordPic(Integer checksizeId,HttpServletRequest request,Model model) throws IOException{

		List<BizOrderChecksizePic> picList = bizOrderChecksizeService.selectPicByOrderChecksizeId(checksizeId);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);
		
		return "mobile/modules/Manager/progressMain/checksize/new/viewPic";
	}
		
	

	@RequestMapping(value = { "installChecksizeAdvanceApply", "" })
	public String installPlanAdvanceApply(Integer id, Integer orderId, HttpServletRequest request, Model model) {
		ChecksizeOrder order = checksizeNewService.findOrder(Integer.valueOf(orderId));
		OrderInstallPlan orderInstall = checksizeNewService.queryInstallItemDetail(id);
		model.addAttribute("order", order);
		model.addAttribute("orderInstall", orderInstall);
		return "mobile/modules/Manager/progressMain/checksize/new/reRulerApply-earlyApply";
	}
	
	

	@RequestMapping(value = "save_install_apply_checksize_advance_apply_ajax")
	public @ResponseBody String saveInstallApplyChecksizeAdvanceApplyAjax(String orderInstallItemId,String[] photo, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		return 	checksizeNewService.saveInstallApplyChecksizeAdvanceApplyAjax(orderInstallItemId,photo,manager,request);
	}
	
	
}
