package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.MaterialSelfbuyConstantUtil;
import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.ApplyMaterialSelfbuyReimburseStatusLog;
import cn.damei.service.mobile.Manager.ApplyMaterialSelfbuyReimburseService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.service.mobile.Manager.ProblemService;
import cn.damei.entity.modules.BizMaterialSelfbuy;
import cn.damei.entity.modules.BizMaterialSelfbuyReimburse;
import cn.damei.service.modules.BizMaterialSelfbuyReimburseService;



@Controller
@RequestMapping(value="${adminPath}/app/manager/applyMaterialSelfbuyReimburse")
public class ApplyMaterialSelfbuyReimburseController {
	
	@Autowired
	private ApplyMaterialSelfbuyReimburseService applyMaterialSelfbuyReimburseService;
	@Autowired
	private ProblemService problemService;
	@Autowired
	private BizMaterialSelfbuyReimburseService bizMaterialSelfbuyReimburseService;
	

	
	

	@RequestMapping(value={"list",""})
	public String list(HttpServletRequest request, Model model){
		

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if(null!=manager){
			model.addAttribute("managerId", manager.getId());
		}
		return "mobile/modules/Manager/applyMaterialSelfbuyReimburse/selfMetriList";
	}
	

	@RequestMapping(value="material_selfbuy_reimburse_ajax_list")
	public @ResponseBody  List<MaterialManagement> materialSelfbuyReimburseAjaxList(String managerId,String text){
		
		List<MaterialManagement> list = null;
		if(null!=managerId && !("").equals(managerId)){
			list = applyMaterialSelfbuyReimburseService.findOrderList(Integer.valueOf(managerId),text);
		}
		
		return list;
	}
	
	

	@RequestMapping(value="material_selfbuy_reimburse_ajax_time")
	public @ResponseBody String materialSelfbuyReimburseAjaxTime(String orderId,String relatedReimburseId,String materialSelfbuyReimburseType){
		
		String result = "0";
		
		if(StringUtils.isBlank(orderId)){
			result = "1";
			return result;
		}

		Integer count = applyMaterialSelfbuyReimburseService.findMaterialSelfbuyReimburseCount(Integer.valueOf(orderId),relatedReimburseId, materialSelfbuyReimburseType);
		
		if(null!=count && count>0){
			result = "2";
			return result;
		}
		return result;
	}
	
	


	@RequestMapping(value={"material_selfbuy_reimburse",""})
	public String materialSelfbuyReimburse(String orderId,HttpServletRequest request, Model model){
		
		MaterialManagement materialManagement = null;
		List<BizMaterialSelfbuy> materialSelfbuyList = null;
		if(StringUtils.isNotBlank(orderId)){

			materialManagement = applyMaterialSelfbuyReimburseService.findOrder(Integer.valueOf(orderId));

			materialSelfbuyList = applyMaterialSelfbuyReimburseService.findMaterialSelfbuyList(materialManagement);
		}
		
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("materialSelfbuyList", materialSelfbuyList);
		model.addAttribute("orderId", orderId);
	
		return "mobile/modules/Manager/applyMaterialSelfbuyReimburse/selfMetri";
		
	}	
	
	
	

	@RequestMapping(value="material_selfbuy_reimburse_submit" ,method=RequestMethod.POST)
	public @ResponseBody String materialSelfbuyReimburseSubmit(String orderId,String materialSelfbuyId,String customerPayAmount,
			String settleRateTwo,String settleStage,String settleAmount,String reimburseRemarks,String[] photo,HttpServletRequest request){
		String result = "0";
		

		if(StringUtils.isBlank(orderId)){
			result = "1";
			return result;
		}

		if(StringUtils.isBlank(materialSelfbuyId)){
			result = "2";
			return result;
		}

		if(StringUtils.isBlank(customerPayAmount)){
			result = "3";
			return result;
		}

		if(StringUtils.isBlank(settleRateTwo)){
			result = "4";
			return result;
		}

		if(StringUtils.isBlank(settleStage)){
			result = "5";
			return result;
		}

		if(StringUtils.isBlank(settleAmount)){
			result = "6";
			return result;
		}

		if(null==photo || photo.length<1){
			result = "7";
			return result;
		}

		Manager manager = (Manager)request.getSession().getAttribute("manager");
		if(null==manager || null==manager.getId()){
			result = "8";
			return result;
		}

		Integer materialSelfbuyReimburseId = applyMaterialSelfbuyReimburseService.saveMaterialSelfbuyReimburse(
				Integer.valueOf(orderId),null,Integer.valueOf(materialSelfbuyId),Double.valueOf(customerPayAmount),
				Double.valueOf(settleRateTwo),settleStage,Double.valueOf(settleAmount),
				reimburseRemarks,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_TYPE_1,
				MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_10,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_10);
		
		if(null==materialSelfbuyReimburseId || materialSelfbuyReimburseId<1){
			result = "9";
			return result;
		}
		
		

		Integer businessStatusLogId = applyMaterialSelfbuyReimburseService.saveBusinessStatusLog(materialSelfbuyReimburseId,manager.getId(),
				MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_10,reimburseRemarks,
				BusinessLogConstantUtil.BUSINESS_TYPE_210);
		if(null==businessStatusLogId || businessStatusLogId<1){
			applyMaterialSelfbuyReimburseService.deleteMaterialSelfbuyReimburse(materialSelfbuyReimburseId);
			result = "10";
			return result;
		}
		
		

		applyMaterialSelfbuyReimburseService.saveMaterialSelfbuyReimbursePic(materialSelfbuyReimburseId,PictureTypeContantUtil.PICTURE_TYPE_2091,photo,PicturePathContantUtil.UPLOAD_MANAGER_APPLY_MATERIAL_SELFBUT_REIMBURSE_UPLOAD_PHOTO,request);
		
		
		return result;
	}
	
	

	@RequestMapping(value={"material_selfbuy_reimburse_reapply",""})
	public String materialSelfbuyReimburseReapply(String orderId,String relatedReimburseId,HttpServletRequest request, Model model){
		
		MaterialManagement materialManagement = null;
		List<BizMaterialSelfbuy> materialSelfbuyList = null;
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = null;
		List<ReportCheckDetailsPic> picList = null;
		
		if(StringUtils.isNotBlank(orderId)){

			materialManagement = applyMaterialSelfbuyReimburseService.findOrder(Integer.valueOf(orderId));
		}
		if(StringUtils.isNotBlank(relatedReimburseId)){

			bizMaterialSelfbuyReimburse = applyMaterialSelfbuyReimburseService.findLastTimeMaterialSelfbuyDetail(Integer.valueOf(orderId),Integer.valueOf(relatedReimburseId));

			picList = problemService.findPic(bizMaterialSelfbuyReimburse.getId(),PictureTypeContantUtil.PICTURE_TYPE_2091);

		}
		
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("materialSelfbuyList", materialSelfbuyList);
		model.addAttribute("bizMaterialSelfbuyReimburse", bizMaterialSelfbuyReimburse);
		model.addAttribute("picList", picList);
		model.addAttribute("orderId", orderId);
		model.addAttribute("relatedReimburseId", relatedReimburseId);
	
		return "mobile/modules/Manager/applyMaterialSelfbuyReimburse/selfMetriReapply";
		
	}	
	
	

	@RequestMapping(value="material_selfbuy_reimburse_submit_reapply" ,method=RequestMethod.POST)
	public @ResponseBody String materialSelfbuyReimburseSubmitReapply(String orderId,String relatedReimburseId,String materialSelfbuyId,String customerPayAmount,
			String settleRateTwo,String settleStage,String settleAmount,String reimburseRemarks,String[] photo,String[] picUrlId,HttpServletRequest request){
		String result = "0";
		

		if(StringUtils.isBlank(orderId)){
			result = "1";
			return result;
		}

		if(StringUtils.isBlank(relatedReimburseId)){
			result = "2";
			return result;
		}

		if(StringUtils.isBlank(materialSelfbuyId)){
			result = "3";
			return result;
		}

		if(StringUtils.isBlank(customerPayAmount)){
			result = "4";
			return result;
		}

		if(StringUtils.isBlank(settleRateTwo)){
			result = "5";
			return result;
		}

		if(StringUtils.isBlank(settleStage)){
			result = "6";
			return result;
		}

		if(StringUtils.isBlank(settleAmount)){
			result = "7";
			return result;
		}

		if(!((null!=photo && photo.length > 0) || (null!=picUrlId && picUrlId.length > 0))){
			result = "8";
			return result;
		}

		Manager manager = (Manager)request.getSession().getAttribute("manager");
		if(null==manager || null==manager.getId()){
			result = "9";
			return result;
		}
		

		boolean flag = bizMaterialSelfbuyReimburseService.updateMaterialSelfbuyReimburse(Integer.valueOf(relatedReimburseId),MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_15,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_15);
		if(!flag){
			result = "10";
			return result;
		}
				

		Integer materialSelfbuyReimburseId = applyMaterialSelfbuyReimburseService.saveMaterialSelfbuyReimburse(
				Integer.valueOf(orderId),Integer.valueOf(relatedReimburseId),Integer.valueOf(materialSelfbuyId),Double.valueOf(customerPayAmount),
				Double.valueOf(settleRateTwo),settleStage,Double.valueOf(settleAmount),
				reimburseRemarks,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_TYPE_2,
				MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_15,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_15);
		
		if(null==materialSelfbuyReimburseId || materialSelfbuyReimburseId<1){
			result = "11";
			return result;
		}
		
		

		Integer businessStatusLogId = applyMaterialSelfbuyReimburseService.saveBusinessStatusLog(materialSelfbuyReimburseId,manager.getId(),
				MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_15,reimburseRemarks,
				BusinessLogConstantUtil.BUSINESS_TYPE_210);
		if(null==businessStatusLogId || businessStatusLogId<1){
			applyMaterialSelfbuyReimburseService.deleteMaterialSelfbuyReimburse(materialSelfbuyReimburseId);
			result = "12";
			return result;
		}
		
		

		if(null != photo && photo.length>0){
			applyMaterialSelfbuyReimburseService.saveMaterialSelfbuyReimbursePic(materialSelfbuyReimburseId,PictureTypeContantUtil.PICTURE_TYPE_2091,photo,PicturePathContantUtil.UPLOAD_MANAGER_APPLY_MATERIAL_SELFBUT_REIMBURSE_UPLOAD_PHOTO,request);
		}
		

		if(null != picUrlId && picUrlId.length>0){
			

			applyMaterialSelfbuyReimburseService.findLastPicListAndSave(picUrlId,materialSelfbuyReimburseId);
		}
		
		
		return result;
	}
	
		

	@RequestMapping(value={"materialSelfbuyReimburseRecord",""})
	public String materialSelfbuyReimburseRecord(String orderId,HttpServletRequest request, Model model){
		
		MaterialManagement materialManagement = null;
		if(StringUtils.isNotBlank(orderId)){

			materialManagement = applyMaterialSelfbuyReimburseService.findOrder(Integer.valueOf(orderId));
		}
		
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("orderId", orderId);
		
		return "mobile/modules/Manager/applyMaterialSelfbuyReimburse/selfMetriRecordList";
	}
	

	@RequestMapping(value="material_selfbuy_reimburse_record_ajax_list")
	public @ResponseBody  List<BizMaterialSelfbuyReimburse> materialSelfbuyReimburseRecordAjaxList(String orderId){
		
		List<BizMaterialSelfbuyReimburse> list = null;
		if(StringUtils.isNotBlank(orderId)){
			list = applyMaterialSelfbuyReimburseService.findMaterialSelfbuyReimburseRecordList(Integer.valueOf(orderId));
		}
		
		return list;
	}
	
	

	@RequestMapping(value={"material_selfbuy_reimburse_details",""})
	public String materialSelfbuyReimburseDetails(String orderId,String relatedReimburseId,HttpServletRequest request, Model model){
		
		MaterialManagement materialManagement = null;
		List<BizMaterialSelfbuyReimburse> list = null;
		List<ApplyMaterialSelfbuyReimburseStatusLog> statusLogList = null;
		if(StringUtils.isNotBlank(orderId)){

			materialManagement = applyMaterialSelfbuyReimburseService.findOrder(Integer.valueOf(orderId));
			if(StringUtils.isNotBlank(relatedReimburseId)){

				list = applyMaterialSelfbuyReimburseService.findMaterialSelfbuyReimburseDetails(Integer.valueOf(orderId),Integer.valueOf(relatedReimburseId));
				statusLogList = applyMaterialSelfbuyReimburseService.findMaterialStatusLogDetails(Integer.valueOf(orderId),Integer.valueOf(relatedReimburseId),BusinessLogConstantUtil.BUSINESS_TYPE_210);
				
			}
		}
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("list", list);
		model.addAttribute("statusLogList", statusLogList);
		model.addAttribute("orderId", orderId);
		
		return "mobile/modules/Manager/applyMaterialSelfbuyReimburse/selfMetriRecordDetails";
	}
	

	@RequestMapping(value={"detailsPic",""})
	public String detailsPic(String materialId,HttpServletRequest request, Model model){
	
		List<ReportCheckDetailsPic> picList = null;
		if(StringUtils.isNotBlank(materialId)){

			picList = problemService.findPic(Integer.valueOf(materialId),PictureTypeContantUtil.PICTURE_TYPE_2091);

		}
		
		model.addAttribute("picList", picList);
	
		return "mobile/modules/Manager/applyMaterialSelfbuyReimburse/photo";
		
	}	

}
