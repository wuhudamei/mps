package cn.damei.web.modules;



import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.BusinessUrgeConstantUtil;
import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.service.modules.BizBusinessUrgeService;
import cn.damei.entity.modules.BizOrderChecksizeEntity;
import cn.damei.entity.modules.BizPrePmSettleFin;
import cn.damei.entity.modules.EnginInstallNew;
import cn.damei.entity.modules.EnginInstallSupplier;
import cn.damei.service.modules.EnginInstallNewDealService;
import cn.damei.service.modules.EnginInstallNewService;
import cn.damei.entity.modules.BizProjectInstallItem;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/enginInstallNewDeal/enginInstallNewDeal")
public class EnginInstallNewDealController{

    @Autowired
    private EnginInstallNewDealService enginInstallNewDealService;
    @Autowired
    private EnginInstallNewService enginInstallNewService;
    @Autowired
    private BizBusinessUrgeService bizBusinessUrgeService;


  


	@RequestMapping(value = { "updateByStatus", "" })
	public @ResponseBody String updateByStatus(String installId,String orderId,String supplierConfirmIntoDate,String supplierConfirmRemarks,
			HttpServletRequest request ,HttpServletResponse response) {
	
		String result = "0";

		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}

		if(StringUtils.isBlank(orderId)){
			result = "2";
			return result;
		}

		if(StringUtils.isBlank(supplierConfirmIntoDate)){
			result = "3";
			return result;
		}

		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}
		

		EnginInstallNew enginInstall = enginInstallNewService.get(Integer.valueOf(installId));
		if(!(enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2) || enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6))){
			result = "5";
			return result;
		}
		

		boolean flag = enginInstallNewDealService.updateSupplier(Integer.valueOf(installId),InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,supplierConfirmIntoDate,supplierConfirmRemarks,null);
		if(!flag){
			result = "6";
			return result;
		}
		

		Integer logId = enginInstallNewDealService.saveBusinessStatusLog(empId,Integer.valueOf(installId),BusinessLogConstantUtil.BUSINESS_TYPE_901,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,supplierConfirmRemarks,supplierConfirmIntoDate);
		if(null==logId || logId<1){
			result = "7";
			return result;  
		}
		

		if(StringUtils.isNotBlank(enginInstall.getManagerPhone())){

			String content = "订单（" + enginInstall.getCommunityName() + "-" + enginInstall.getBuildNumber() + "-" + enginInstall.getBuildUnit() + "-"
					+ enginInstall.getBuildRoom() + "-" + enginInstall.getCustomerName() + "-" + enginInstall.getInstallItemName() 
					+ ") 材料员已转单给供应商，供应商确认进场日期为("+ supplierConfirmIntoDate + ")，请您知晓";
			Integer messageId = enginInstallNewDealService.saveMessage(
					enginInstall.getManagerId(),enginInstall.getManagerPhone(),content,
					Integer.valueOf(installId),SendMsgBusinessType.RELATED_BUSINESS_TYPE_110203);
			if(null==messageId || messageId<1){
				result = "8";
				return result;  
			}
		}
		


		
		return result;
	}
	

	@RequestMapping(value = { "updateByStatusSupplierId", "" })
	public @ResponseBody String updateByStatusSupplierId(String installId,String orderId,String supplierId,String supplierConfirmRemarks,
			HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";

		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}

		if(StringUtils.isBlank(orderId)){
			result = "2";
			return result;
		}

		if(StringUtils.isBlank(supplierId)){
			result = "3";
			return result;
		}

		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}
		

		EnginInstallNew enginInstall = enginInstallNewService.get(Integer.valueOf(installId));
		if(!(enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2) || enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6))){
			result = "5";
			return result;
		}
		

		BizSupplier bizSupplier = enginInstallNewDealService.findSupplierMessage(Integer.valueOf(supplierId));
		if(null==bizSupplier){
			result = "6";
			return result;
		}
				

		boolean flag = enginInstallNewDealService.updateSupplier(Integer.valueOf(installId),InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,null,supplierConfirmRemarks,Integer.valueOf(supplierId));
		if(!flag){
			result = "7";
			return result;
		}
		

		Integer logId = enginInstallNewDealService.saveBusinessStatusLog(empId,Integer.valueOf(installId),BusinessLogConstantUtil.BUSINESS_TYPE_901,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,supplierConfirmRemarks,supplierId);
		if(null==logId || logId<1){
			result = "8";
			return result;  
		}
		

		Integer supplierInstallBillId = enginInstallNewDealService.saveSupplierInstallBill(Integer.valueOf(installId),Integer.valueOf(supplierId),InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10);
		if(null==supplierInstallBillId || supplierInstallBillId < 1){
			result = "9";
			return result;  
		}
		

		Integer BillLogId = enginInstallNewDealService.saveBusinessStatusLog(empId,supplierInstallBillId,BusinessLogConstantUtil.BUSINESS_TYPE_9011,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_10,supplierId);
		if(null==BillLogId || BillLogId<1){
			result = "10";
			return result;  
		}
		

		if(StringUtils.isNotBlank(enginInstall.getManagerPhone())){

			String content = "订单（" + enginInstall.getCommunityName() + "-" + enginInstall.getBuildNumber() + "-" + enginInstall.getBuildUnit() + "-"
					+ enginInstall.getBuildRoom() + "-" + enginInstall.getCustomerName() + "）的主材安装（"+enginInstall.getInstallItemName() +
					"）已经分派给供应商了，供应商联系人（"+ bizSupplier.getContacts()+"-"+bizSupplier.getContactsPhone() + "），请您知晓。";
			Integer messageId = enginInstallNewDealService.saveMessage(
					enginInstall.getManagerId(),enginInstall.getManagerPhone(),content,
					Integer.valueOf(installId),SendMsgBusinessType.RELATED_BUSINESS_TYPE_110204);
			if(null == messageId || messageId < 1){
				result = "11";
				return result;  
			}
		}
		

		if(StringUtils.isNotBlank(bizSupplier.getContactsPhone())){

			String content = "订单（" + enginInstall.getCommunityName() + "-" + enginInstall.getBuildNumber() + "-" + enginInstall.getBuildUnit() + "-"
					+ enginInstall.getBuildRoom() + "-" + enginInstall.getCustomerName() + "）的主材安装（"+enginInstall.getInstallItemName() +
					"）已经分派给您了，请您及时登录产业工人管理系统进行确认工期分派工人组。";
			Integer messageId = enginInstallNewDealService.saveMessage(
					null,bizSupplier.getContactsPhone(),content,
					Integer.valueOf(installId),SendMsgBusinessType.RELATED_BUSINESS_TYPE_110205);
			if(null==messageId || messageId<1){
				result = "12";
				return result;  
			}
		}
		
		
		
		return result;
	}
	
	

	@RequestMapping(value = { "updateByStatusSupplierIdAgain", "" })
	public @ResponseBody String updateByStatusSupplierIdAgain(String installId,String orderId,String supplierId,String supplierConfirmRemarks,
			HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";

		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}

		if(StringUtils.isBlank(orderId)){
			result = "2";
			return result;
		}

		if(StringUtils.isBlank(supplierId)){
			result = "3";
			return result;
		}

		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}
		

		EnginInstallNew enginInstall = enginInstallNewService.get(Integer.valueOf(installId));
		if(!(enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3) || enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_310) || enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_320) )){
			result = "5";
			return result;
		}
		

		if(Integer.valueOf(supplierId).equals(enginInstall.getSendSupplierId())){
			result = "6";
			return result;
		}
		

		result = enginInstallNewDealService.updateByStatusSupplierIdAgain(Integer.valueOf(installId),empId,supplierId,supplierConfirmRemarks);
		
		
		return result;
	}
	

	@RequestMapping(value="save_install_reply")
    public @ResponseBody  String saveInstallReply(String installPlanId,String urgeReply,HttpServletRequest request){
		
		String result  = "0";
		

		if(StringUtils.isBlank(installPlanId)){
			result = "1";
			return result;
		}

		User user = UserUtils.getUser();
		Integer managerId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			managerId = Integer.valueOf(user.getEmpId());
		}

		Integer count = bizBusinessUrgeService.findCountByfiveTime(Integer.valueOf(installPlanId),managerId,BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_2 );
		if(null == count || count > 0){
			result = "3";
			return result;
		}

		if(StringUtils.isBlank(urgeReply)){
			result = "4";
			return result;
		}

		Integer urgeId = bizBusinessUrgeService.saveBusinessUrge(managerId,urgeReply,Integer.valueOf(installPlanId),BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_2);
		if(null==urgeId){
			result = "5";
			return result;
		}



		return result;

    }


	@RequestMapping(value = { "updateRejected", "" })
	public @ResponseBody String updateRejected(String installId,String orderId,String rejectId,String rejectedRemarks,
			HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";

		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}

		if(StringUtils.isBlank(orderId)){
			result = "2";
			return result;
		}

		if(StringUtils.isBlank(rejectId)){
			result = "3";
			return result;
		}

		if(rejectId.equals("5") && StringUtils.isBlank(rejectedRemarks)){
			result = "4";
			return result;
		}
		

		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}
		

		EnginInstallNew enginInstall = enginInstallNewService.get(Integer.valueOf(installId));
		if(!(enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2) || enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6))){
			result = "6";
			return result;
		}
		

		boolean flag = enginInstallNewDealService.updateReject(Integer.valueOf(installId),InstallPlanConstantUtil.INSTALL_PLAN_STATUS_5);
		if(!flag){
			result = "7";
			return result;
		}
		

		Integer logId = enginInstallNewDealService.saveBusinessStatusLog(empId,Integer.valueOf(installId),BusinessLogConstantUtil.BUSINESS_TYPE_901,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_5,rejectedRemarks,rejectId);
		if(null==logId || logId<1){
			result = "8";
			return result;  
		}
		
		return result;
	}
	
	

	@RequestMapping(value = { "installExplain", "" })
	public @ResponseBody String installExplain(String installId,HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";

		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}

		BizProjectInstallItem bizProjectInstallItem = enginInstallNewDealService.installExplain(Integer.valueOf(installId));
		if(null == bizProjectInstallItem){
			result = "2";
			return result;
		}else if(bizProjectInstallItem.getIsShowInstallDescription().equals("1") && StringUtils.isNotBlank(bizProjectInstallItem.getInstallDescription())){
			result = bizProjectInstallItem.getInstallDescription();
		}else{
			result = "2";
			return result;
		}
		
		return result;
	}
	
	

	@RequestMapping(value = { "secondPayment", "" })
	public @ResponseBody String secondPayment(String orderId,HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";
		

		if(StringUtils.isBlank(orderId)){
			result = "1";
			return result;
		}
		

		BizPrePmSettleFin bizPrePmSettleFin = enginInstallNewDealService.findSecondPayment(Integer.valueOf(orderId));
		if(null==bizPrePmSettleFin){
			result = "2";
			return result;
		}else{
			result = "缴款日期："+bizPrePmSettleFin.getReceiveMoneyTime()+"="+"交款金额："+bizPrePmSettleFin.getReceiveMoneyAmount();
		}
		
		return result;
	}
	
	

	@RequestMapping(value = { "checkSize", "" })
	public @ResponseBody String checkSize(String installId,String orderId,HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";

		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}

		if(StringUtils.isBlank(orderId)){
			result = "2";
			return result;
		}

		BizProjectInstallItem bizProjectInstallItem = enginInstallNewDealService.installExplain(Integer.valueOf(installId));
		if(null==bizProjectInstallItem){
			result = "3";
			return result;
		}
		if(!bizProjectInstallItem.getIsToChecksize().equals("1")){
			result = "3";
			return result;
		}

		if(null==bizProjectInstallItem.getOrderInstallItemId()){
			result = "4";
			return result;
		}

		BizOrderChecksizeEntity bizOrderChecksize = enginInstallNewDealService.findCheckSize(Integer.valueOf(orderId),bizProjectInstallItem.getOrderInstallItemId());
		if(null==bizOrderChecksize){
			result = "5";
			return result;
		}else{
			result = "复尺状态："+bizOrderChecksize.getChecksizeStatusName()+"="+"项目经理申请日期："+bizOrderChecksize.getApplyDate();
		}
		return result;
	}
	

	@RequestMapping(value = { "findSupplierList", "" })
	public @ResponseBody List<EnginInstallSupplier> findSupplierList(String installId) {
		
		List<EnginInstallSupplier> list = null;

		if(StringUtils.isNotBlank(installId)){
			list = enginInstallNewDealService.findSupplierList(Integer.valueOf(installId));
		}
		
		return list;
	}
    
}