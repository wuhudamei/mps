/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.dao.modules.BizPrepareOrderDao;
import cn.damei.entity.modules.BizPrepareOrder;
import cn.damei.entity.modules.OrdertaskingCount;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.Order;
import cn.damei.dao.modules.BizOrderReportDao;
import cn.damei.dao.modules.BizOrderReportLogDao;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预备订单表Service
 * @author wyb
 * @version 2017-03-15
 */
@Service
@Transactional(readOnly = true)
public class BizPrepareOrderService extends CrudService2<BizPrepareOrderDao, BizPrepareOrder> {

	@Autowired
	private OrderService orderService;
	
	public BizPrepareOrder get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPrepareOrder> findList(BizPrepareOrder bizPrepareOrder) {
		return super.findList(bizPrepareOrder);
	}
	
	public Page<BizPrepareOrder> findPage(Page<BizPrepareOrder> page, BizPrepareOrder bizPrepareOrder) {
		return super.findPage(page, bizPrepareOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPrepareOrder bizPrepareOrder) {
		super.save(bizPrepareOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPrepareOrder bizPrepareOrder) {
		super.delete(bizPrepareOrder);
	}

	/**
	 * 接收预备订单
	 * @param bizPrepareOrder
	 * @return
	 */

	@Autowired
	private PhoneMessageDao messageDao;
	@Autowired
	private BizOrderReportLogDao orderReportLogDao;
	@Autowired
	private BizOrderReportDao orderReportDao;
	@Autowired
	private BizEmployeeDao bizEmployeeDao;
	@Transactional(readOnly = false)
	public String receiveOrder(BizPrepareOrder bizPrepareOrder) {
		
		User user = UserUtils.getUser();
		Date date = new Date();
		
		String message = "预备订单";
		
		//接收订单
		Order order = new Order();
		//1.门店
		if(null!=bizPrepareOrder.getStoreId() ){
			order.setStoreId(String.valueOf(bizPrepareOrder.getStoreId()));
		}
		//2.工程模式
		order.setProjectMode(bizPrepareOrder.getProjectMode());
		//3.订单编号
		order.setOrderNumber(bizPrepareOrder.getOrderNumber());
		//4.区域
		order.setEngineDepartId(bizPrepareOrder.getEnginDepartId());
		//5.合同编号
		order.setContractNumber(bizPrepareOrder.getContractNumber());
		//6.客户类型
		order.setCustomerType(bizPrepareOrder.getCustomerType());
		//7.客户属性描述
		order.setCustomerDescription(bizPrepareOrder.getCustomerDescription());
		//8.客户姓名
		order.setCustomerName(bizPrepareOrder.getCustomerName());
		//9.客户手机
		order.setCustomerPhone(bizPrepareOrder.getCustomerPhone());
		//10.是否有远程费
		//11.远程费金额
		order.setDistanceFee(bizPrepareOrder.getLongwayAmount());
		//12.详细地址
		order.setDetailAddress(bizPrepareOrder.getDetailAddress());
		//13.小区名称
		order.setCommunityName(bizPrepareOrder.getCommunityName());
		//14.楼
		order.setBuildNumber(bizPrepareOrder.getBuildNumber());
		//15.单元
		order.setBuildUnit(bizPrepareOrder.getBuildUnit());
		//16.室
		order.setBuildRoom(bizPrepareOrder.getBuildRoom());
		//17.坐标
		order.setMapCoordinate(bizPrepareOrder.getMapCoordinate());
		//18.省
		order.setProvince(bizPrepareOrder.getProvince());
		//19.市
		order.setCity(bizPrepareOrder.getCity());
		//20.县
		order.setCounty(bizPrepareOrder.getCounty());
		//21.套餐类型
		order.setSaleType(bizPrepareOrder.getSaleType());
		//22.房屋类型
		order.setBuildType(bizPrepareOrder.getBuildType());
		//23.户型
		order.setHouseType(bizPrepareOrder.getHouseType());
		//24.是否新房
		order.setHouseIsNew(bizPrepareOrder.getHouseIsNew());
		//25.有无电梯
		order.setIsElevator(bizPrepareOrder.getIsElevator());
		//26.设计师姓名
		order.setDesignerName(bizPrepareOrder.getDesignerName());
		//27.设计师电话
		order.setDesignerPhone(bizPrepareOrder.getDesignerPhone());
		//28.跟单员姓名
		order.setOrderReporterName(bizPrepareOrder.getOrderReporterName());
		//29.跟单员电话
		order.setOrderReporterPhone(bizPrepareOrder.getOrderReporterPhone());
		//30.客服姓名
		order.setServiceName(bizPrepareOrder.getServiceName());
		//31.客服电话
		order.setServicePhone(bizPrepareOrder.getServicePhone());
		//32.合同开工时间
		order.setContractStartDate(bizPrepareOrder.getContractStartDate());
		//33.合同竣工时间
		order.setContractEndDate(bizPrepareOrder.getContractEndDate());
		//34.建筑面积
		order.setCoveredArea(bizPrepareOrder.getCoveredArea());
		//35.合同面积
		order.setContractArea(bizPrepareOrder.getContractArea());
		//36.合同工期
		order.setContractTime(bizPrepareOrder.getContractTime());
		//37.签约日期
		order.setSignContractDate(bizPrepareOrder.getSignContractDate());
		//38.接单区域
		if(null!=bizPrepareOrder.getBizOrderAcceptArea() && !"".equals(bizPrepareOrder.getBizOrderAcceptArea())){
			order.setAcceptAreaId(Integer.valueOf(bizPrepareOrder.getBizOrderAcceptArea()));
		}
		//39.接单日期
		order.setAcceptOrderDate(bizPrepareOrder.getGetOrderDatetime());
		//40.地址
		order.setCustomerAddress(bizPrepareOrder.getCustomerAddress());
		//41.创建人
		order.setCreateBy(user);
		//42.创建日期
		order.setCreateDate(date);
		//43.更新人
		order.setUpdateBy(user);
		//44.更新日期
		order.setUpdateDate(date);
		//45.是否删除标记
		order.setDelFlag(OrderConstantUtil.ORDER_DEL_FLAG_NO_0);
		//46.备注
		order.setRemarks(bizPrepareOrder.getRemarks());
		//47.订单状态
		order.setOrderStatusNumber(OrderConstantUtil.ORDER_STATUS_RECEIVE_THE_ORDER_105);
		//48.订单状态描述
		order.setOrderStatusDescription(OrderConstantUtil.ORDER_STATUS_NAME_RECEIVE_THE_ORDER_105);
		//49.订单任务包生成状态
		order.setOrderTaskPackStatus(OrderConstantUtil.ORDER_ORDERTASKPACKAGE_STATUS_NO_0);
		//50.预备订单ID
		order.setPrepareOrderId(bizPrepareOrder.getId());
		//51.楼层
		order.setFloor(bizPrepareOrder.getFloor());
		//52.合同金额
		order.setContractAmount(bizPrepareOrder.getContractAmount());
		//53.是否作废
		order.setIsScrap(OrderConstantUtil.ORDER_IS_SCRAP_NO_0);
		//54.审计员id
		order.setAuditorEmployeeId(bizPrepareOrder.getAuditorEmployeeId());
		//54.审计员姓名
		order.setAuditorName(bizPrepareOrder.getAuditorName());
		//54.审计员电话
		order.setAuditorPhone(bizPrepareOrder.getAuditorPhone());
		
		
		//根据设计师电话和姓名查出 设计师ID
		Map<String,String> paramaterMap = new HashMap<>();
		paramaterMap.put("empName", bizPrepareOrder.getDesignerName());
		paramaterMap.put("empPhone", bizPrepareOrder.getDesignerPhone());
		List<BizEmployee> employeeListByEmpType = bizEmployeeDao.getEmployeeListByEmpType(paramaterMap);
		order.setDesignerEmployeeId(employeeListByEmpType.get(0).getId());
		
		orderService.save(order);
		//2017-5-8 加入返单关联
		Order reportOrder=orderService.findRelatedReportInfoByCustomerPhone(order.getCustomerPhone());
		if(null!=reportOrder){
			//更新返单

			reportOrder.setOrderId(order.getOrderId());
			reportOrder.preInsert();
			reportOrder.setOrderNumber(order.getOrderNumber());
			orderService.batchInsertOrderReportRelatedInfo(reportOrder);

//			BizOrderReport orderReport= orderReportDao.get(reportOrder.getOrderReportId());
//
//			OrderReportLogEntity logEntity = new OrderReportLogEntity();
//			Map<String,Object> logRelatedMap = new HashMap<>();
//
//			logEntity.setOrderReportId(orderReport.getId());
//			logEntity.setLogType(BizOrderReportConstantUtil.SIGN_TYPE_1);
//			logEntity.setSignBillDateTime(reportOrder.getAcceptOrderDate());

//			logEntity.setOperateDateTime(new Date());
//			logEntity.setOperateEmployeeName(UserUtils.getUser().getName());
//			logEntity.preInsert();
//			orderReportLogDao.saveSignLog(logEntity);
//			//保存该次log关联订单
//			logRelatedMap.put("logId",logEntity.getId());
//			logRelatedMap.put("orderNums",reportOrder.getOrderNumber());
//
//			orderReportLogDao.saveLogRelatedOrderNumber(logRelatedMap);
//			//保存log日志

//			PhoneMessageEntity phone = new PhoneMessageEntity();
//
//
//			String content = "【美得你】亲爱的美得你员工，您推荐的客户"+orderReport.getCustomerName()+"已进美得你门店了，您的奖励将在下月兑现。如有新的客户，记得再次向“小美返单”推荐哦";
//
//			phone.setReceivePhone(orderReport.getReporterPhone());
//			phone.setMessageContent(content);
//			phone.setMessageGenerateTime(new Date());
//			phone.setStatus(ConstantUtils.SEND_MSG_STATUS_0);
//			phone.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_66666);
//			phone.setRelatedBusinessId(orderReport.getId());
//			phone.setRelatedBusinessVarchar(logEntity.getId());
//			Integer count =messageDao.checkIsExistByTypeAndBusinessId(phone);
//			if(null==count || count==0){
//				messageDao.saveMessageContent(phone);
//
//
//			}


		}

		if(null==order.getOrderId() || order.getOrderId()<1){
			message += ",接收失败";
		}else{
			message += ",接收成功";
			//更新cad表中的订单ID
			
			dao.updateCadOrderId(order.getOrderNumber(),order.getOrderId()+"");
		}
		//返回
		return message;
	}

	/**
	 * 校验数据是否正确
	 * @param bizPrepareOrder
	 * @return
	 */
	public String verificationPrepareOrder(BizPrepareOrder bizPrepareOrder) {
		
		String message = "预备订单校验";
		//一.校验必填项
		//1.订单编号
		if(null==bizPrepareOrder.getOrderNumber() || "".equals(bizPrepareOrder.getOrderNumber())){
			message += ",订单编号不可为空";
		}else{
			//查询订单表中是否有相同的订单编号
			Integer result = orderService.getOrderNumberById(bizPrepareOrder.getOrderNumber());
			if (null != result && result>0) {
				return "预备订单接收失败，订单编号在订单列表中已包含！";
			} 
		}
		//2.门店
		if( null == bizPrepareOrder.getStoreId() ){
			message += ",门店不可为空";
		}
		//3.工程模式
		if(null == bizPrepareOrder.getProjectMode() || "".equals(bizPrepareOrder.getProjectMode())){
			message += ",工程模式不可为空";
		}
		//4.区域
		if(null == bizPrepareOrder.getEnginDepartId() ){
			message += ",区域不可为空";
		}
		//5.接单时间
		if(null == bizPrepareOrder.getGetOrderDatetime() ){
			message += ",接单时间不可为空";
		}
		//6.客户姓名
		if( StringUtils.isBlank( bizPrepareOrder.getCustomerName() ) ){
			message += ",客户姓名不可为空";
		}
		//7.客户手机号
		if( StringUtils.isBlank( bizPrepareOrder.getCustomerPhone() ) ){
			message += ",客户电话不可为空";
		}
		//8.小区名称
		if( StringUtils.isBlank( bizPrepareOrder.getCommunityName() ) ){
			message += ",小区名称不可为空";
		}
		//9.楼
		if( StringUtils.isBlank( bizPrepareOrder.getBuildNumber() ) ){
			message += ",几号楼不可为空";
		}
		//10.单元
		if( StringUtils.isBlank( bizPrepareOrder.getBuildUnit() ) ){
			message += ",几单元不可为空";
		}
		//11.室
		if( StringUtils.isBlank( bizPrepareOrder.getBuildRoom() ) ){
			message += ",哪一室不可为空";
		}
		//12.详细地址
		if( StringUtils.isBlank( bizPrepareOrder.getDetailAddress() ) ){
			message += ",详细地址不可为空";
		}
		//13.省
		if( StringUtils.isBlank( bizPrepareOrder.getProvince() ) ){
			message += ",省不可为空";
		}
		//14.市
		if( StringUtils.isBlank( bizPrepareOrder.getCity() ) ){
			message += ",市不可为空";
		}
		//15.县
		if( StringUtils.isBlank( bizPrepareOrder.getCounty() ) ){
			message += ",县不可为空";
		}
		//16.房屋类型
		if( StringUtils.isBlank( bizPrepareOrder.getBuildType() ) ){
			message += ",房屋类型不可为空";
		}
		//17.新老房
		if( StringUtils.isBlank( bizPrepareOrder.getHouseIsNew() ) ){
			message += ",新老房不可为空";
		}
		//18.有无电梯
		if( StringUtils.isBlank( bizPrepareOrder.getIsElevator() ) ){
			message += ",有无电梯不可为空";
		}
		//19.合同开工日期
		if(null == bizPrepareOrder.getContractStartDate()){
			message += ",合同开工日期不可为空";
		}
		//20.地址
		if( StringUtils.isBlank( bizPrepareOrder.getCustomerAddress() ) ){
			message += ",地址不可为空";
		}
		//21.楼层
		if( null == bizPrepareOrder.getFloor() ){
			message += ",楼层不可为空";
		}
		//22.经纬度
		if( StringUtils.isBlank( bizPrepareOrder.getMapCoordinate() ) ){
			message += ",经纬度不可为空";
		}
		//23.合同金额
		if(null == bizPrepareOrder.getContractAmount() ){
			message += ",合同金额不可为空";
		}
		//24.套餐类型
		if( StringUtils.isBlank( bizPrepareOrder.getSaleType() ) ){
			message += ",套餐类型不可为空";
		}
		//25.设计师
		if( StringUtils.isBlank( bizPrepareOrder.getDesignerName() ) ){
			message += ",设计师不可为空";
		}
		//26.设计师电话
		if( StringUtils.isBlank( bizPrepareOrder.getDesignerPhone() ) ){
			message += ",设计师电话不可为空";
		}
		//27.合同面积
		if( StringUtils.isBlank( bizPrepareOrder.getContractArea() ) ){
			message += ",合同面积不可为空";
		}
		//28.设计师是否跟系统里的设计师匹配
		if( StringUtils.isBlank( bizPrepareOrder.getDesignerName() ) ){
			message += ",设计师不匹配";
		}else{
			//根据设计师姓名和电话查询是否符合
			Map<String, String> paramaterMap = new HashMap<>();
			paramaterMap.put("empName", bizPrepareOrder.getDesignerName());
			paramaterMap.put("empPhone", bizPrepareOrder.getDesignerPhone());
			
			int employeeCount = bizEmployeeDao.getEmployeeCount(paramaterMap);
			if(employeeCount < 1){
				message += ",设计师不匹配";
			}
		}
		
		//29.审计员是否跟系统里的设计师匹配
		if(StringUtils.isBlank(bizPrepareOrder.getAuditorName()) || StringUtils.isBlank(bizPrepareOrder.getAuditorPhone()) || null==bizPrepareOrder.getAuditorEmployeeId() ){
			message += ",审计员不匹配";
		}else{
			//根据审计员姓名和电话查询是否符合
			Map<String, String> paramaterMap = new HashMap<>();
			paramaterMap.put("empName", bizPrepareOrder.getAuditorName());
			paramaterMap.put("empPhone", bizPrepareOrder.getAuditorPhone());
			
			Integer employeeCount = bizEmployeeDao.getEmployeeCount(paramaterMap);
			if(null==employeeCount || employeeCount < 1 ){
				message += ",审计员不匹配";
			}
		}
		
		return message;
	}
	public List<BizEmployee> getEmployeeListByType(String empType){
		Map<String, String> paramaterMap = new HashMap<>();
		paramaterMap.put("empType", empType);
		return bizEmployeeDao.getEmployeeListByEmpType(paramaterMap);
	}
	public int getEmployeeCount(String empName, String empPhone){
		Map<String, String> paramaterMap = new HashMap<>();
		paramaterMap.put("empName", empName);
		paramaterMap.put("empPhone", empPhone);
		
		int employeeCount = bizEmployeeDao.getEmployeeCount(paramaterMap);
		return employeeCount;
	}
	public long findPrepareOrderCount(BizPrepareOrder temp) {
		// TODO Auto-generated method stub
		return dao.findPrepareOrderCount(temp);
	}

	public List<OrdertaskingCount> ordertaskingCount(OrdertaskingCount ordertaskingCount) {
		// TODO Auto-generated method stub
		return dao.ordertaskingCount(ordertaskingCount);
	}

	public Integer findMaterialsChoiceBillId(String orderNumber) {
		// TODO Auto-generated method stub
		return dao.findMaterialsChoiceBillId(orderNumber);
	}
	
}