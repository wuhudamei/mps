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

/**
 * 自采材料报销Controller
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value="${adminPath}/app/manager/applyMaterialSelfbuyReimburse")
public class ApplyMaterialSelfbuyReimburseController {
	
	@Autowired
	private ApplyMaterialSelfbuyReimburseService applyMaterialSelfbuyReimburseService;
	@Autowired
	private ProblemService problemService;
	@Autowired
	private BizMaterialSelfbuyReimburseService bizMaterialSelfbuyReimburseService;
	

	
	
	/**
	 * 自采材料报销--订单列表 
	 * @param text
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"list",""})
	public String list(HttpServletRequest request, Model model){
		
		// 获取项目经理sesseion
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if(null!=manager){
			model.addAttribute("managerId", manager.getId());
		}
		return "mobile/modules/Manager/applyMaterialSelfbuyReimburse/selfMetriList";
	}
	
	/**
	 * 动态加载自采材料报销列表
	 * @param managerId
	 * @param text
	 * @return
	 */
	@RequestMapping(value="material_selfbuy_reimburse_ajax_list")
	public @ResponseBody  List<MaterialManagement> materialSelfbuyReimburseAjaxList(String managerId,String text){
		
		List<MaterialManagement> list = null;
		if(null!=managerId && !("").equals(managerId)){
			list = applyMaterialSelfbuyReimburseService.findOrderList(Integer.valueOf(managerId),text);
		}
		
		return list;
	}
	
	
	/**
	 * 校验 自采材料报销  5分钟提交  重复校验
	 * @param managerId
	 * @param text
	 * @return
	 */
	@RequestMapping(value="material_selfbuy_reimburse_ajax_time")
	public @ResponseBody String materialSelfbuyReimburseAjaxTime(String orderId,String relatedReimburseId,String materialSelfbuyReimburseType){
		
		String result = "0";
		
		if(StringUtils.isBlank(orderId)){
			result = "1";
			return result;
		}
		//查询该订单5分钟内提交问题上报的数量
		Integer count = applyMaterialSelfbuyReimburseService.findMaterialSelfbuyReimburseCount(Integer.valueOf(orderId),relatedReimburseId, materialSelfbuyReimburseType);
		
		if(null!=count && count>0){
			result = "2";
			return result;
		}
		return result;
	}
	
	

	/**
	 * 自采材料报销 页
	 * @param text
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"material_selfbuy_reimburse",""})
	public String materialSelfbuyReimburse(String orderId,HttpServletRequest request, Model model){
		
		MaterialManagement materialManagement = null;
		List<BizMaterialSelfbuy> materialSelfbuyList = null;
		if(StringUtils.isNotBlank(orderId)){
			//订单详情
			materialManagement = applyMaterialSelfbuyReimburseService.findOrder(Integer.valueOf(orderId));
			//自采材料名称列表
			materialSelfbuyList = applyMaterialSelfbuyReimburseService.findMaterialSelfbuyList(materialManagement);
		}
		
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("materialSelfbuyList", materialSelfbuyList);
		model.addAttribute("orderId", orderId);
	
		return "mobile/modules/Manager/applyMaterialSelfbuyReimburse/selfMetri";
		
	}	
	
	
	
	/**
	 * 自采材料报销   提交
	 * @param orderId
	 * @param materialSelfbuyId
	 * @param customerPayAmount
	 * @param settleRateTwo
	 * @param settleStage
	 * @param settleAmount
	 * @param reimburseRemarks
	 * @param photo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="material_selfbuy_reimburse_submit" ,method=RequestMethod.POST)
	public @ResponseBody String materialSelfbuyReimburseSubmit(String orderId,String materialSelfbuyId,String customerPayAmount,
			String settleRateTwo,String settleStage,String settleAmount,String reimburseRemarks,String[] photo,HttpServletRequest request){
		String result = "0";
		
		//1.订单id为空
		if(StringUtils.isBlank(orderId)){
			result = "1";
			return result;
		}
		//2.自采材料名称为空
		if(StringUtils.isBlank(materialSelfbuyId)){
			result = "2";
			return result;
		}
		//3.客户交费金额为空
		if(StringUtils.isBlank(customerPayAmount)){
			result = "3";
			return result;
		}
		//4.结算比例为空
		if(StringUtils.isBlank(settleRateTwo)){
			result = "4";
			return result;
		}
		//5.所属结算阶段为空
		if(StringUtils.isBlank(settleStage)){
			result = "5";
			return result;
		}
		//6.实际结算金额为空
		if(StringUtils.isBlank(settleAmount)){
			result = "6";
			return result;
		}
		//7.自采材料报销凭证为空
		if(null==photo || photo.length<1){
			result = "7";
			return result;
		}
		//8.获取项目经理
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		if(null==manager || null==manager.getId()){
			result = "8";
			return result;
		}
		//9.保存自采材料报销
		Integer materialSelfbuyReimburseId = applyMaterialSelfbuyReimburseService.saveMaterialSelfbuyReimburse(
				Integer.valueOf(orderId),null,Integer.valueOf(materialSelfbuyId),Double.valueOf(customerPayAmount),
				Double.valueOf(settleRateTwo),settleStage,Double.valueOf(settleAmount),
				reimburseRemarks,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_TYPE_1,
				MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_10,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_10);
		
		if(null==materialSelfbuyReimburseId || materialSelfbuyReimburseId<1){
			result = "9";
			return result;
		}
		
		
		//10.保存自采材料报销状态日志
		Integer businessStatusLogId = applyMaterialSelfbuyReimburseService.saveBusinessStatusLog(materialSelfbuyReimburseId,manager.getId(),
				MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_10,reimburseRemarks,
				BusinessLogConstantUtil.BUSINESS_TYPE_210);
		if(null==businessStatusLogId || businessStatusLogId<1){
			applyMaterialSelfbuyReimburseService.deleteMaterialSelfbuyReimburse(materialSelfbuyReimburseId);
			result = "10";
			return result;
		}
		
		
		//11.保存自采材料报销 图片
		applyMaterialSelfbuyReimburseService.saveMaterialSelfbuyReimbursePic(materialSelfbuyReimburseId,PictureTypeContantUtil.PICTURE_TYPE_2091,photo,PicturePathContantUtil.UPLOAD_MANAGER_APPLY_MATERIAL_SELFBUT_REIMBURSE_UPLOAD_PHOTO,request);
		
		
		return result;
	}
	
	
	/**
	 * 自采材料报销 页  --重新申请
	 * @param text
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"material_selfbuy_reimburse_reapply",""})
	public String materialSelfbuyReimburseReapply(String orderId,String relatedReimburseId,HttpServletRequest request, Model model){
		
		MaterialManagement materialManagement = null;
		List<BizMaterialSelfbuy> materialSelfbuyList = null;
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = null;
		List<ReportCheckDetailsPic> picList = null;
		
		if(StringUtils.isNotBlank(orderId)){
			//订单详情
			materialManagement = applyMaterialSelfbuyReimburseService.findOrder(Integer.valueOf(orderId));
		}
		if(StringUtils.isNotBlank(relatedReimburseId)){
			//最新一次的自采材料报销申请内容
			bizMaterialSelfbuyReimburse = applyMaterialSelfbuyReimburseService.findLastTimeMaterialSelfbuyDetail(Integer.valueOf(orderId),Integer.valueOf(relatedReimburseId));
			//图片
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
	
	
	/**
	 * 自采材料报销   提交 --重新申请
	 * @param orderId
	 * @param materialSelfbuyId
	 * @param customerPayAmount
	 * @param settleRateTwo
	 * @param settleStage
	 * @param settleAmount
	 * @param reimburseRemarks
	 * @param photo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="material_selfbuy_reimburse_submit_reapply" ,method=RequestMethod.POST)
	public @ResponseBody String materialSelfbuyReimburseSubmitReapply(String orderId,String relatedReimburseId,String materialSelfbuyId,String customerPayAmount,
			String settleRateTwo,String settleStage,String settleAmount,String reimburseRemarks,String[] photo,String[] picUrlId,HttpServletRequest request){
		String result = "0";
		
		//1.订单id为空
		if(StringUtils.isBlank(orderId)){
			result = "1";
			return result;
		}
		//2.关联id为空
		if(StringUtils.isBlank(relatedReimburseId)){
			result = "2";
			return result;
		}
		//3.自采材料名称为空
		if(StringUtils.isBlank(materialSelfbuyId)){
			result = "3";
			return result;
		}
		//4.客户交费金额为空
		if(StringUtils.isBlank(customerPayAmount)){
			result = "4";
			return result;
		}
		//5.结算比例为空
		if(StringUtils.isBlank(settleRateTwo)){
			result = "5";
			return result;
		}
		//6.所属结算阶段为空
		if(StringUtils.isBlank(settleStage)){
			result = "6";
			return result;
		}
		//7.实际结算金额为空
		if(StringUtils.isBlank(settleAmount)){
			result = "7";
			return result;
		}
		//8.自采材料报销凭证为空
		if(!((null!=photo && photo.length > 0) || (null!=picUrlId && picUrlId.length > 0))){
			result = "8";
			return result;
		}
		//9.获取项目经理
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		if(null==manager || null==manager.getId()){
			result = "9";
			return result;
		}
		
		//10.更新初次申请的自采材料报销
		boolean flag = bizMaterialSelfbuyReimburseService.updateMaterialSelfbuyReimburse(Integer.valueOf(relatedReimburseId),MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_15,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_15);
		if(!flag){
			result = "10";
			return result;
		}
				
		//11.保存自采材料报销
		Integer materialSelfbuyReimburseId = applyMaterialSelfbuyReimburseService.saveMaterialSelfbuyReimburse(
				Integer.valueOf(orderId),Integer.valueOf(relatedReimburseId),Integer.valueOf(materialSelfbuyId),Double.valueOf(customerPayAmount),
				Double.valueOf(settleRateTwo),settleStage,Double.valueOf(settleAmount),
				reimburseRemarks,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_TYPE_2,
				MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_15,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_15);
		
		if(null==materialSelfbuyReimburseId || materialSelfbuyReimburseId<1){
			result = "11";
			return result;
		}
		
		
		//12.保存自采材料报销状态日志
		Integer businessStatusLogId = applyMaterialSelfbuyReimburseService.saveBusinessStatusLog(materialSelfbuyReimburseId,manager.getId(),
				MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_15,reimburseRemarks,
				BusinessLogConstantUtil.BUSINESS_TYPE_210);
		if(null==businessStatusLogId || businessStatusLogId<1){
			applyMaterialSelfbuyReimburseService.deleteMaterialSelfbuyReimburse(materialSelfbuyReimburseId);
			result = "12";
			return result;
		}
		
		
		//13.保存自采材料报销 图片
		if(null != photo && photo.length>0){
			applyMaterialSelfbuyReimburseService.saveMaterialSelfbuyReimbursePic(materialSelfbuyReimburseId,PictureTypeContantUtil.PICTURE_TYPE_2091,photo,PicturePathContantUtil.UPLOAD_MANAGER_APPLY_MATERIAL_SELFBUT_REIMBURSE_UPLOAD_PHOTO,request);
		}
		
		//14.保存自采材料报销  图片 之前
		if(null != picUrlId && picUrlId.length>0){
			
			//根据id查询出所有的 图片（之前的）并保存
			applyMaterialSelfbuyReimburseService.findLastPicListAndSave(picUrlId,materialSelfbuyReimburseId);
		}
		
		
		return result;
	}
	
		
	/**
	 * 自采材料报销  记录页
	 * @param orderId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"materialSelfbuyReimburseRecord",""})
	public String materialSelfbuyReimburseRecord(String orderId,HttpServletRequest request, Model model){
		
		MaterialManagement materialManagement = null;
		if(StringUtils.isNotBlank(orderId)){
			//订单详情
			materialManagement = applyMaterialSelfbuyReimburseService.findOrder(Integer.valueOf(orderId));
		}
		
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("orderId", orderId);
		
		return "mobile/modules/Manager/applyMaterialSelfbuyReimburse/selfMetriRecordList";
	}
	
	/**
	 * 动态加载自采材料报销  记录页面
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="material_selfbuy_reimburse_record_ajax_list")
	public @ResponseBody  List<BizMaterialSelfbuyReimburse> materialSelfbuyReimburseRecordAjaxList(String orderId){
		
		List<BizMaterialSelfbuyReimburse> list = null;
		if(StringUtils.isNotBlank(orderId)){
			list = applyMaterialSelfbuyReimburseService.findMaterialSelfbuyReimburseRecordList(Integer.valueOf(orderId));
		}
		
		return list;
	}
	
	
	/**
	 * 自采材料报销  记录详情页
	 * @param orderId
	 * @param relatedReimburseId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"material_selfbuy_reimburse_details",""})
	public String materialSelfbuyReimburseDetails(String orderId,String relatedReimburseId,HttpServletRequest request, Model model){
		
		MaterialManagement materialManagement = null;
		List<BizMaterialSelfbuyReimburse> list = null;
		List<ApplyMaterialSelfbuyReimburseStatusLog> statusLogList = null;
		if(StringUtils.isNotBlank(orderId)){
			//订单详情
			materialManagement = applyMaterialSelfbuyReimburseService.findOrder(Integer.valueOf(orderId));
			if(StringUtils.isNotBlank(relatedReimburseId)){
				//自采材料报销详情
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
	
	/**
	 * 自采材料报销 详情页  图片
	 * @param text
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"detailsPic",""})
	public String detailsPic(String materialId,HttpServletRequest request, Model model){
	
		List<ReportCheckDetailsPic> picList = null;
		if(StringUtils.isNotBlank(materialId)){
			//图片
			picList = problemService.findPic(Integer.valueOf(materialId),PictureTypeContantUtil.PICTURE_TYPE_2091);

		}
		
		model.addAttribute("picList", picList);
	
		return "mobile/modules/Manager/applyMaterialSelfbuyReimburse/photo";
		
	}	

}
