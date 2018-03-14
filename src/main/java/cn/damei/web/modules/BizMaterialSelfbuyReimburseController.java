/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.MaterialSelfbuyConstantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.ApplyMaterialSelfbuyReimburseStatusLog;
import cn.damei.service.mobile.Manager.ProblemService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizMaterialSelfbuyReimburse;
import cn.damei.service.modules.BizMaterialSelfbuyReimburseService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.entity.modules.PmSettleCategoryDetail;
import cn.damei.service.modules.InspectorConfirmService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 材料自采报销单Controller
 * @author wyb
 * @version 2017-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburse")
public class BizMaterialSelfbuyReimburseController extends BaseController {

	@Autowired
	private BizMaterialSelfbuyReimburseService bizMaterialSelfbuyReimburseService;
	@Autowired
	private ProblemService problemService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private InspectorConfirmService inspectorConfirmService;

	@Autowired
	private OrderService2 orderService2;

	@ModelAttribute
	public BizMaterialSelfbuyReimburse get(@RequestParam(required=false) Integer id) {
		BizMaterialSelfbuyReimburse entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizMaterialSelfbuyReimburseService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialSelfbuyReimburse();
		}
		return entity;
	}

	@RequiresPermissions("bizmaterialselfbuyreimburse:bizMaterialSelfbuyReimburse:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse, HttpServletRequest request, HttpServletResponse response, Model model) {



		User user = UserUtils.getUser();
		//过滤工程模式
		if(StringUtils.isBlank(bizMaterialSelfbuyReimburse.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizMaterialSelfbuyReimburse.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizMaterialSelfbuyReimburse.setProjectMode(user.getProjectMode());
			}
		}


		bizMaterialSelfbuyReimburse.setReimburseStatus(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_10+","+MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_15);


		return "modules/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburseList";
	}

	@RequiresPermissions("bizmaterialselfbuyreimburse:bizMaterialSelfbuyReimburse:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();
		//过滤工程模式
		if(StringUtils.isBlank(bizMaterialSelfbuyReimburse.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizMaterialSelfbuyReimburse.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizMaterialSelfbuyReimburse.setProjectMode(user.getProjectMode());
			}
		}

		if(bizMaterialSelfbuyReimburse.getReimburseStatus()!=null){
			String[] status = bizMaterialSelfbuyReimburse.getReimburseStatus().split(",");
			if(null!=status && status.length>0){
				bizMaterialSelfbuyReimburse.setReimburseStatusList(Arrays.asList(status));
			}
		}

		Page<BizMaterialSelfbuyReimburse> page = bizMaterialSelfbuyReimburseService.findPage(new Page<BizMaterialSelfbuyReimburse>(request, response), bizMaterialSelfbuyReimburse);
		model.addAttribute("page", page);
		return "modules/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburseList";
	}


	/**
	 * 自采材料  驳回
	 * @param materialId
	 * @param relatedReimburseId
	 * @param reimburseStatusRemarks
	 * @return
	 */
	@RequestMapping(value = "update_reject_ajax")
	public @ResponseBody String updateRejectAjax(String materialId,String relatedReimburseId,String reimburseStatusRemarks) {

		String result = "0";

		//1.自采材料id
		if(StringUtils.isBlank(materialId)){
			result = "1";
			return result;
		}
		//2.最初的自采材料id
		if(StringUtils.isBlank(relatedReimburseId)){
			result = "2";
			return result;
		}
		//3.驳回原因
		if(StringUtils.isBlank(reimburseStatusRemarks)){
			result = "3";
			return result;
		}
		//4.当前登录人
		User user = UserUtils.getUser();
		Integer managerId = null;
		if(null==user){
			result = "4";
			return result;
		}else if(null != user.getEmpId()){
			managerId = Integer.parseInt(user.getEmpId());
		}

		//5.根据本次自采材料id查询详情
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = bizMaterialSelfbuyReimburseService.findMaterialAndOrderByMaterialId(Integer.valueOf(materialId));
		if(null==bizMaterialSelfbuyReimburse){
			result = "5";
			return result;
		}

		//6.本次自采材料是否已经审核
		if(bizMaterialSelfbuyReimburse.getReimburseStatus().equals(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_25) || bizMaterialSelfbuyReimburse.getReimburseStatus().equals(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_20)){
			result = "6";
			return result;
		}

		//7.保存自采材料报销状态日志
		Integer businessStatusLogId = bizMaterialSelfbuyReimburseService.saveBusinessStatusLog(Integer.valueOf(materialId),managerId,
				MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_25,reimburseStatusRemarks,
				BusinessLogConstantUtil.BUSINESS_TYPE_210);
		if(null==businessStatusLogId || businessStatusLogId<1){
			result = "7";
			return result;
		}
		//8.更新本次申请的自采材料报销
		boolean flag = bizMaterialSelfbuyReimburseService.updateMaterialSelfbuyReimburse(Integer.valueOf(materialId),MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_25,reimburseStatusRemarks);
		if(!flag){
			result = "8";
			return result;
		}
		//9.更新最初的自采材料报销
		if(!materialId.equals(relatedReimburseId)){
			boolean flagTwo = bizMaterialSelfbuyReimburseService.updateMaterialSelfbuyReimburse(Integer.valueOf(relatedReimburseId),MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_25,reimburseStatusRemarks);
			if(!flagTwo){
				result = "9";
				return result;
			}
		}

		//10.发送短信给项目经理
		if(StringUtils.isNotBlank(bizMaterialSelfbuyReimburse.getItemManagerPhone())){
			//=====================================短信start========================================================
			/**
			 * 自采材料驳回==项目经理
			 */
			//订单（小区名称-楼号-单元号-门牌号-客户姓名）的（自采材料名称）自采材料报销申请已被结算员驳回（驳回原因），请您到APP的申请记录里重新提交申请。
			String content = "订单（"+bizMaterialSelfbuyReimburse.getCommunityName()+"-"+bizMaterialSelfbuyReimburse.getBuildNumber()+"-"+bizMaterialSelfbuyReimburse.getBuildUnit()+"-"+bizMaterialSelfbuyReimburse.getBuildRoom()+"-"+bizMaterialSelfbuyReimburse.getCustomerName()+"的（"+bizMaterialSelfbuyReimburse.getMaterialName()+"）自采材料报销申请已被结算员驳回（"+reimburseStatusRemarks+"），请您到APP的申请记录里重新提交申请。";
			BizPhoneMsg phone = new BizPhoneMsg();
			phone.setReceiveEmployeeId(bizMaterialSelfbuyReimburse.getItemManagerId());
			phone.setReceivePhone(bizMaterialSelfbuyReimburse.getItemManagerPhone());
			phone.setMsgContent(content);
			phone.setMsgGenerateDatetime(new Date());
			phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_801001);
			phone.setRelatedBusinessIdInt(Integer.valueOf(materialId));
			bizPhoneMsgService.insert(phone);

			//=====================================短信end========================================================
			if(null==phone.getId()){
				result = "10";
				return result;
			}
		}


		return result;
	}

	/**
	 * 自采材料  通过
	 * @param materialId
	 * @param relatedReimburseId
	 * @return
	 */
	@RequestMapping(value = "update_pass_ajax")
	public @ResponseBody String updatePassAjax(String materialId,String relatedReimburseId) {

		String result = "0";

		//1.自采材料id
		if(StringUtils.isBlank(materialId)){
			result = "1";
			return result;
		}
		//2.最初的自采材料id
		if(StringUtils.isBlank(relatedReimburseId)){
			result = "2";
			return result;
		}
		//3.当前登录人
		User user = UserUtils.getUser();
		Integer managerId = null;
		if(null==user){
			result = "3";
			return result;
		}else if(null != user.getEmpId()){
			managerId = Integer.parseInt(user.getEmpId());
		}
		//4.根据本次自采材料id查询详情
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = bizMaterialSelfbuyReimburseService.findMaterialAndOrderByMaterialId(Integer.valueOf(materialId));

		if(null==bizMaterialSelfbuyReimburse){
			result = "4";
			return result;
		}

		//判断订单是否竣工
		Order2 order = orderService2.get(bizMaterialSelfbuyReimburse.getOrderId());
		if(Integer.valueOf(order.getOrderStatusNumber()) >= 340){
			bizMaterialSelfbuyReimburseService.updateMaterialSelfbuyReimburse(Integer.valueOf(materialId),MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_90,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_90);
			bizMaterialSelfbuyReimburseService.saveBusinessStatusLog(Integer.valueOf(materialId),managerId,
					MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_90,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_90,
					BusinessLogConstantUtil.BUSINESS_TYPE_210);
			result = "-1";
			return result;
		}

		//5.本次自采材料是否已经审核
		if(bizMaterialSelfbuyReimburse.getReimburseStatus().equals(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_25) || bizMaterialSelfbuyReimburse.getReimburseStatus().equals(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_20)){
			result = "5";
			return result;
		}
		//6.保存自采材料报销状态日志
		Integer businessStatusLogId = bizMaterialSelfbuyReimburseService.saveBusinessStatusLog(Integer.valueOf(materialId),managerId,
				MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_20,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_20,
				BusinessLogConstantUtil.BUSINESS_TYPE_210);
		if(null==businessStatusLogId || businessStatusLogId<1){
			result = "6";
			return result;
		}
		//7.更新本次申请的自采材料报销
		boolean flag = bizMaterialSelfbuyReimburseService.updateMaterialSelfbuyReimburse(Integer.valueOf(materialId),MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_20,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_20);
		if(!flag){
			result = "7";
			return result;
		}
		//8.更新最初的自采材料报销
		if(!materialId.equals(relatedReimburseId)){
			boolean flagTwo = bizMaterialSelfbuyReimburseService.updateMaterialSelfbuyReimburse(Integer.valueOf(relatedReimburseId),MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_20,MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_REIMBURSE_STATUS_REMARKS_20);
			if(!flagTwo){
				result = "8";
				return result;
			}
		}

		//9.插入结算明细
		PmSettleCategoryDetail  details = new PmSettleCategoryDetail();
		details.setOrderId(bizMaterialSelfbuyReimburse.getOrderId());
		details.setPmEmployeeId(bizMaterialSelfbuyReimburse.getItemManagerId());
		details.setSettleCategory(ConstantUtils.QC_SETTLE_CATEGORY_11);
		details.setSettleAmount(bizMaterialSelfbuyReimburse.getSettleAmount());
		details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
		details.setSettleStatusTime(new Date());
		details.setSettleRemark(bizMaterialSelfbuyReimburse.getReimburseRemarks());
		details.setRelatedBussinessId(bizMaterialSelfbuyReimburse.getId());
		details.preInsert();
		details.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
		inspectorConfirmService.saveDetail(details);

		return result;
	}

	/**
	 * 详情页
	 * @param materialId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "details")
	public String details(String materialId, Model model) {
		//订单详情
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = bizMaterialSelfbuyReimburseService.findMaterialAndOrderByMaterialId(Integer.valueOf(materialId));
		//自采材料报销详情
		List<BizMaterialSelfbuyReimburse> list = bizMaterialSelfbuyReimburseService.findMaterialSelfbuyReimburseDetails(Integer.valueOf(materialId));
		//自采材料报销 状态 详情
		List<ApplyMaterialSelfbuyReimburseStatusLog> statusLogList = bizMaterialSelfbuyReimburseService.findMaterialStatusLogDetails(Integer.valueOf(materialId),BusinessLogConstantUtil.BUSINESS_TYPE_210);

		model.addAttribute("bizMaterialSelfbuyReimburse", bizMaterialSelfbuyReimburse);
		model.addAttribute("list", list);
		model.addAttribute("statusLogList", statusLogList);

		return "modules/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburseDetails";
	}


/*	*//**
	 * 图片
	 * @param materialId
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 *//*
	@RequestMapping(value = "photo")
	public String photo(String materialId,Model model,HttpServletRequest request) throws IOException{

		List<ReportCheckDetailsPic> picList = null;
		if(StringUtils.isNotBlank(materialId)){
			//图片
			picList = problemService.findPic(Integer.valueOf(materialId),PictureTypeContantUtil.PICTURE_TYPE_2091);
		}

		model.addAttribute("picList", picList);


		return "modules/bizmaterialselfbuyreimburse/photo";
	}*/
	/**
	 * 图片Ajax
	 * @param materialId
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "photo")
	@ResponseBody
	public Map<Object, Object> photo(String materialId, Model model, HttpServletRequest request) throws IOException{

		List<ReportCheckDetailsPic> picList = null;
		if(StringUtils.isNotBlank(materialId)){
			//图片
			picList = problemService.findPic(Integer.valueOf(materialId),PictureTypeContantUtil.PICTURE_TYPE_2091);
		}

		model.addAttribute("picList", picList);

		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", picList);
		return mapObject;
	}

}