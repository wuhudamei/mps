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

/**
 * 主材安装申请  处理Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/enginInstallNewDeal/enginInstallNewDeal")
public class EnginInstallNewDealController{

    @Autowired
    private EnginInstallNewDealService enginInstallNewDealService;
    @Autowired
    private EnginInstallNewService enginInstallNewService;
    @Autowired
    private BizBusinessUrgeService bizBusinessUrgeService;


  

	/**
	 * 下达供应商---安装模式为：传统模式
	 * @param installId
	 * @param orderId
	 * @param supplierConfirmIntoDate
	 * @param supplierConfirmRemarks
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "updateByStatus", "" })
	public @ResponseBody String updateByStatus(String installId,String orderId,String supplierConfirmIntoDate,String supplierConfirmRemarks,
			HttpServletRequest request ,HttpServletResponse response) {
	
		String result = "0";
		//1.安装项ID为空
		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}
		//2.订单ID为空
		if(StringUtils.isBlank(orderId)){
			result = "2";
			return result;
		}
		//3.供应商确认日期为空
		if(StringUtils.isBlank(supplierConfirmIntoDate)){
			result = "3";
			return result;
		}
		//4.当前登录人
		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}
		
		//5.查询安装项详情 只有状态为2:已申请 6：驳回后申请  才可以下达供应商
		EnginInstallNew enginInstall = enginInstallNewService.get(Integer.valueOf(installId));
		if(!(enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2) || enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6))){
			result = "5";
			return result;
		}
		
		//6.更新订单安装项
		boolean flag = enginInstallNewDealService.updateSupplier(Integer.valueOf(installId),InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,supplierConfirmIntoDate,supplierConfirmRemarks,null);
		if(!flag){
			result = "6";
			return result;
		}
		
		//7.保存状态日志
		Integer logId = enginInstallNewDealService.saveBusinessStatusLog(empId,Integer.valueOf(installId),BusinessLogConstantUtil.BUSINESS_TYPE_901,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,supplierConfirmRemarks,supplierConfirmIntoDate);
		if(null==logId || logId<1){
			result = "7";
			return result;  
		}
		
		//8.发送短信
		if(StringUtils.isNotBlank(enginInstall.getManagerPhone())){
			//订单（小区名称-楼号-单元号-门牌号-客户姓名-带货安装项名称）材料员已转单给供应商，供应商确认进场日期为（2017-02-20），请您知晓。”
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
		
		//9.站内消息
		//enginInstallNewDealService.saveMsg(enginInstall,Integer.valueOf(installId),supplierConfirmIntoDate);
		
		return result;
	}
	
	/**
	 * 下达供应商---安装模式为：产业模式
	 * @param installId
	 * @param orderId
	 * @param supplierId
	 * @param supplierConfirmRemarks
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "updateByStatusSupplierId", "" })
	public @ResponseBody String updateByStatusSupplierId(String installId,String orderId,String supplierId,String supplierConfirmRemarks,
			HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";
		//1.安装项ID为空
		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}
		//2.订单ID为空
		if(StringUtils.isBlank(orderId)){
			result = "2";
			return result;
		}
		//3.供应商为空
		if(StringUtils.isBlank(supplierId)){
			result = "3";
			return result;
		}
		//4.当前登录人
		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}
		
		//5.查询安装项详情 只有状态为2:已申请 6：驳回后申请  才可以下达供应商
		EnginInstallNew enginInstall = enginInstallNewService.get(Integer.valueOf(installId));
		if(!(enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2) || enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6))){
			result = "5";
			return result;
		}
		
		//6.查询供应商信息
		BizSupplier bizSupplier = enginInstallNewDealService.findSupplierMessage(Integer.valueOf(supplierId));
		if(null==bizSupplier){
			result = "6";
			return result;
		}
				
		//7.更新订单安装项
		boolean flag = enginInstallNewDealService.updateSupplier(Integer.valueOf(installId),InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,null,supplierConfirmRemarks,Integer.valueOf(supplierId));
		if(!flag){
			result = "7";
			return result;
		}
		
		//8.保存安装项状态日志
		Integer logId = enginInstallNewDealService.saveBusinessStatusLog(empId,Integer.valueOf(installId),BusinessLogConstantUtil.BUSINESS_TYPE_901,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3,supplierConfirmRemarks,supplierId);
		if(null==logId || logId<1){
			result = "8";
			return result;  
		}
		
		//9.保存安装单
		Integer supplierInstallBillId = enginInstallNewDealService.saveSupplierInstallBill(Integer.valueOf(installId),Integer.valueOf(supplierId),InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10);
		if(null==supplierInstallBillId || supplierInstallBillId < 1){
			result = "9";
			return result;  
		}
		
		//10.保存安装单状态日志
		Integer BillLogId = enginInstallNewDealService.saveBusinessStatusLog(empId,supplierInstallBillId,BusinessLogConstantUtil.BUSINESS_TYPE_9011,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_10,supplierId);
		if(null==BillLogId || BillLogId<1){
			result = "10";
			return result;  
		}
		
		//11.发送短信给项目经理
		if(StringUtils.isNotBlank(enginInstall.getManagerPhone())){
			//订单（合顺家园-18-2-205-张三丰）的主材安装（橱柜）已经分派给供应商了，供应商联系人（联系人-手机号），请您知晓。
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
		
		//12.发送短信给供应商
		if(StringUtils.isNotBlank(bizSupplier.getContactsPhone())){
			//订单（合顺家园-18-2-205-张三丰）的主材安装（橱柜）已经分派给您了，请您及时登录产业工人管理系统进行确认工期分派工人组。
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
	
	
	/**
	 * 下达供应商---安装模式为：产业模式--重新下达供应商
	 * @param installId
	 * @param orderId
	 * @param supplierId
	 * @param supplierConfirmRemarks
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "updateByStatusSupplierIdAgain", "" })
	public @ResponseBody String updateByStatusSupplierIdAgain(String installId,String orderId,String supplierId,String supplierConfirmRemarks,
			HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";
		//1.安装项ID为空
		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}
		//2.订单ID为空
		if(StringUtils.isBlank(orderId)){
			result = "2";
			return result;
		}
		//3.供应商为空
		if(StringUtils.isBlank(supplierId)){
			result = "3";
			return result;
		}
		//4.当前登录人
		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}
		
		//5.查询安装项详情 只有状态为3:已转给供应商   310：已确定工人组  320： 施工中   才可以重新下达供应商
		EnginInstallNew enginInstall = enginInstallNewService.get(Integer.valueOf(installId));
		if(!(enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3) || enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_310) || enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_320) )){
			result = "5";
			return result;
		}
		
		//6.查询供应商信息是否与上次是同一个
		if(Integer.valueOf(supplierId).equals(enginInstall.getSendSupplierId())){
			result = "6";
			return result;
		}
		
		//7.重新转给供应商
		result = enginInstallNewDealService.updateByStatusSupplierIdAgain(Integer.valueOf(installId),empId,supplierId,supplierConfirmRemarks);
		
		
		return result;
	}
	
	/**
	 * 回复
	 * @param installPlanId
	 * @param urgeReply
	 * @param request
	 * @return
	 */
	@RequestMapping(value="save_install_reply")
    public @ResponseBody  String saveInstallReply(String installPlanId,String urgeReply,HttpServletRequest request){
		
		String result  = "0";
		
		//1.安装申请回复 安装项ID为空
		if(StringUtils.isBlank(installPlanId)){
			result = "1";
			return result;
		}
		//2.获取材料部
		User user = UserUtils.getUser();
		Integer managerId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			managerId = Integer.valueOf(user.getEmpId());
		}
		//3.安装申请回复--5分钟校验
		Integer count = bizBusinessUrgeService.findCountByfiveTime(Integer.valueOf(installPlanId),managerId,BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_2 );
		if(null == count || count > 0){
			result = "3";
			return result;
		}
		//4.安装申请回复内容为空
		if(StringUtils.isBlank(urgeReply)){
			result = "4";
			return result;
		}
		//5.保存安装申请回复
		Integer urgeId = bizBusinessUrgeService.saveBusinessUrge(managerId,urgeReply,Integer.valueOf(installPlanId),BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2,BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_2);
		if(null==urgeId){
			result = "5";
			return result;
		}
		//6.保存安装申请回复图片
		//List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();
		//checkConfirmService.savePicAll(pList);
		return result;

    }

	/**
	 * 驳回
	 * @param installId
	 * @param orderId
	 * @param rejectId
	 * @param rejectedRemarks
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "updateRejected", "" })
	public @ResponseBody String updateRejected(String installId,String orderId,String rejectId,String rejectedRemarks,
			HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";
		//1.安装项ID为空
		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}
		//2.订单ID为空
		if(StringUtils.isBlank(orderId)){
			result = "2";
			return result;
		}
		//3.驳回类型为空
		if(StringUtils.isBlank(rejectId)){
			result = "3";
			return result;
		}
		//4.当驳回类型为其他时，驳回备注为空
		if(rejectId.equals("5") && StringUtils.isBlank(rejectedRemarks)){
			result = "4";
			return result;
		}
		
		//5.当前登录人
		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}
		
		//6.查询安装项详情 只有状态为2:已申请 6：驳回后申请  才可以驳回
		EnginInstallNew enginInstall = enginInstallNewService.get(Integer.valueOf(installId));
		if(!(enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2) || enginInstall.getInstallStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6))){
			result = "6";
			return result;
		}
		
		//7.更新订单安装项
		boolean flag = enginInstallNewDealService.updateReject(Integer.valueOf(installId),InstallPlanConstantUtil.INSTALL_PLAN_STATUS_5);
		if(!flag){
			result = "7";
			return result;
		}
		
		//8.保存状态日志
		Integer logId = enginInstallNewDealService.saveBusinessStatusLog(empId,Integer.valueOf(installId),BusinessLogConstantUtil.BUSINESS_TYPE_901,InstallPlanConstantUtil.INSTALL_PLAN_STATUS_5,rejectedRemarks,rejectId);
		if(null==logId || logId<1){
			result = "8";
			return result;  
		}
		
		return result;
	}
	
	
	/**
	 * 安装说明
	 * @param installId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "installExplain", "" })
	public @ResponseBody String installExplain(String installId,HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";
		//1.安装项ID为空
		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}
		//2.查询安装说明
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
	
	
	/**
	 * 二期款
	 * @param orderId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "secondPayment", "" })
	public @ResponseBody String secondPayment(String orderId,HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";
		
		//1.订单ID为空
		if(StringUtils.isBlank(orderId)){
			result = "1";
			return result;
		}
		
		//2.查询二期款
		BizPrePmSettleFin bizPrePmSettleFin = enginInstallNewDealService.findSecondPayment(Integer.valueOf(orderId));
		if(null==bizPrePmSettleFin){
			result = "2";
			return result;
		}else{
			result = "缴款日期："+bizPrePmSettleFin.getReceiveMoneyTime()+"="+"交款金额："+bizPrePmSettleFin.getReceiveMoneyAmount();
		}
		
		return result;
	}
	
	
	/**
	 * 复尺情况
	 * @param installId
	 * @param orderId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "checkSize", "" })
	public @ResponseBody String checkSize(String installId,String orderId,HttpServletRequest request ,HttpServletResponse response) {
		
		String result = "0";
		//1.安装项ID为空
		if(StringUtils.isBlank(installId)){
			result = "1";
			return result;
		}
		//2.订单ID为空
		if(StringUtils.isBlank(orderId)){
			result = "2";
			return result;
		}
		//3.查询是否需要复尺
		BizProjectInstallItem bizProjectInstallItem = enginInstallNewDealService.installExplain(Integer.valueOf(installId));
		if(null==bizProjectInstallItem){
			result = "3";
			return result;
		}
		if(!bizProjectInstallItem.getIsToChecksize().equals("1")){
			result = "3";
			return result;
		}
		//4.安装项item的id为空
		if(null==bizProjectInstallItem.getOrderInstallItemId()){
			result = "4";
			return result;
		}
		//5.查询复尺内容
		BizOrderChecksizeEntity bizOrderChecksize = enginInstallNewDealService.findCheckSize(Integer.valueOf(orderId),bizProjectInstallItem.getOrderInstallItemId());
		if(null==bizOrderChecksize){
			result = "5";
			return result;
		}else{
			result = "复尺状态："+bizOrderChecksize.getChecksizeStatusName()+"="+"项目经理申请日期："+bizOrderChecksize.getApplyDate();
		}
		return result;
	}
	
	/**
	 * 下达供应商--查询该安装项的供应商列表
	 * @param installId
	 * @return
	 */
	@RequestMapping(value = { "findSupplierList", "" })
	public @ResponseBody List<EnginInstallSupplier> findSupplierList(String installId) {
		
		List<EnginInstallSupplier> list = null;
		//查询该安装项的供应商列表
		if(StringUtils.isNotBlank(installId)){
			list = enginInstallNewDealService.findSupplierList(Integer.valueOf(installId));
		}
		
		return list;
	}
    
}