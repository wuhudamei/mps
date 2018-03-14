
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
		

		Order order = new Order();

		if(null!=bizPrepareOrder.getStoreId() ){
			order.setStoreId(String.valueOf(bizPrepareOrder.getStoreId()));
		}

		order.setProjectMode(bizPrepareOrder.getProjectMode());

		order.setOrderNumber(bizPrepareOrder.getOrderNumber());

		order.setEngineDepartId(bizPrepareOrder.getEnginDepartId());

		order.setContractNumber(bizPrepareOrder.getContractNumber());

		order.setCustomerType(bizPrepareOrder.getCustomerType());

		order.setCustomerDescription(bizPrepareOrder.getCustomerDescription());

		order.setCustomerName(bizPrepareOrder.getCustomerName());

		order.setCustomerPhone(bizPrepareOrder.getCustomerPhone());


		order.setDistanceFee(bizPrepareOrder.getLongwayAmount());

		order.setDetailAddress(bizPrepareOrder.getDetailAddress());

		order.setCommunityName(bizPrepareOrder.getCommunityName());

		order.setBuildNumber(bizPrepareOrder.getBuildNumber());

		order.setBuildUnit(bizPrepareOrder.getBuildUnit());

		order.setBuildRoom(bizPrepareOrder.getBuildRoom());

		order.setMapCoordinate(bizPrepareOrder.getMapCoordinate());

		order.setProvince(bizPrepareOrder.getProvince());

		order.setCity(bizPrepareOrder.getCity());

		order.setCounty(bizPrepareOrder.getCounty());

		order.setSaleType(bizPrepareOrder.getSaleType());

		order.setBuildType(bizPrepareOrder.getBuildType());

		order.setHouseType(bizPrepareOrder.getHouseType());

		order.setHouseIsNew(bizPrepareOrder.getHouseIsNew());

		order.setIsElevator(bizPrepareOrder.getIsElevator());

		order.setDesignerName(bizPrepareOrder.getDesignerName());

		order.setDesignerPhone(bizPrepareOrder.getDesignerPhone());

		order.setOrderReporterName(bizPrepareOrder.getOrderReporterName());

		order.setOrderReporterPhone(bizPrepareOrder.getOrderReporterPhone());

		order.setServiceName(bizPrepareOrder.getServiceName());

		order.setServicePhone(bizPrepareOrder.getServicePhone());

		order.setContractStartDate(bizPrepareOrder.getContractStartDate());

		order.setContractEndDate(bizPrepareOrder.getContractEndDate());

		order.setCoveredArea(bizPrepareOrder.getCoveredArea());

		order.setContractArea(bizPrepareOrder.getContractArea());

		order.setContractTime(bizPrepareOrder.getContractTime());

		order.setSignContractDate(bizPrepareOrder.getSignContractDate());

		if(null!=bizPrepareOrder.getBizOrderAcceptArea() && !"".equals(bizPrepareOrder.getBizOrderAcceptArea())){
			order.setAcceptAreaId(Integer.valueOf(bizPrepareOrder.getBizOrderAcceptArea()));
		}

		order.setAcceptOrderDate(bizPrepareOrder.getGetOrderDatetime());

		order.setCustomerAddress(bizPrepareOrder.getCustomerAddress());

		order.setCreateBy(user);

		order.setCreateDate(date);

		order.setUpdateBy(user);

		order.setUpdateDate(date);

		order.setDelFlag(OrderConstantUtil.ORDER_DEL_FLAG_NO_0);

		order.setRemarks(bizPrepareOrder.getRemarks());

		order.setOrderStatusNumber(OrderConstantUtil.ORDER_STATUS_RECEIVE_THE_ORDER_105);

		order.setOrderStatusDescription(OrderConstantUtil.ORDER_STATUS_NAME_RECEIVE_THE_ORDER_105);

		order.setOrderTaskPackStatus(OrderConstantUtil.ORDER_ORDERTASKPACKAGE_STATUS_NO_0);

		order.setPrepareOrderId(bizPrepareOrder.getId());

		order.setFloor(bizPrepareOrder.getFloor());

		order.setContractAmount(bizPrepareOrder.getContractAmount());

		order.setIsScrap(OrderConstantUtil.ORDER_IS_SCRAP_NO_0);

		order.setAuditorEmployeeId(bizPrepareOrder.getAuditorEmployeeId());

		order.setAuditorName(bizPrepareOrder.getAuditorName());

		order.setAuditorPhone(bizPrepareOrder.getAuditorPhone());
		
		

		Map<String,String> paramaterMap = new HashMap<>();
		paramaterMap.put("empName", bizPrepareOrder.getDesignerName());
		paramaterMap.put("empPhone", bizPrepareOrder.getDesignerPhone());
		List<BizEmployee> employeeListByEmpType = bizEmployeeDao.getEmployeeListByEmpType(paramaterMap);
		order.setDesignerEmployeeId(employeeListByEmpType.get(0).getId());
		
		orderService.save(order);

		Order reportOrder=orderService.findRelatedReportInfoByCustomerPhone(order.getCustomerPhone());
		if(null!=reportOrder){


			reportOrder.setOrderId(order.getOrderId());
			reportOrder.preInsert();
			reportOrder.setOrderNumber(order.getOrderNumber());
			orderService.batchInsertOrderReportRelatedInfo(reportOrder);









































		}

		if(null==order.getOrderId() || order.getOrderId()<1){
			message += ",接收失败";
		}else{
			message += ",接收成功";

			
			dao.updateCadOrderId(order.getOrderNumber(),order.getOrderId()+"");
		}

		return message;
	}


	public String verificationPrepareOrder(BizPrepareOrder bizPrepareOrder) {
		
		String message = "预备订单校验";


		if(null==bizPrepareOrder.getOrderNumber() || "".equals(bizPrepareOrder.getOrderNumber())){
			message += ",订单编号不可为空";
		}else{

			Integer result = orderService.getOrderNumberById(bizPrepareOrder.getOrderNumber());
			if (null != result && result>0) {
				return "预备订单接收失败，订单编号在订单列表中已包含！";
			} 
		}

		if( null == bizPrepareOrder.getStoreId() ){
			message += ",门店不可为空";
		}

		if(null == bizPrepareOrder.getProjectMode() || "".equals(bizPrepareOrder.getProjectMode())){
			message += ",工程模式不可为空";
		}

		if(null == bizPrepareOrder.getEnginDepartId() ){
			message += ",区域不可为空";
		}

		if(null == bizPrepareOrder.getGetOrderDatetime() ){
			message += ",接单时间不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getCustomerName() ) ){
			message += ",客户姓名不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getCustomerPhone() ) ){
			message += ",客户电话不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getCommunityName() ) ){
			message += ",小区名称不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getBuildNumber() ) ){
			message += ",几号楼不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getBuildUnit() ) ){
			message += ",几单元不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getBuildRoom() ) ){
			message += ",哪一室不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getDetailAddress() ) ){
			message += ",详细地址不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getProvince() ) ){
			message += ",省不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getCity() ) ){
			message += ",市不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getCounty() ) ){
			message += ",县不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getBuildType() ) ){
			message += ",房屋类型不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getHouseIsNew() ) ){
			message += ",新老房不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getIsElevator() ) ){
			message += ",有无电梯不可为空";
		}

		if(null == bizPrepareOrder.getContractStartDate()){
			message += ",合同开工日期不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getCustomerAddress() ) ){
			message += ",地址不可为空";
		}

		if( null == bizPrepareOrder.getFloor() ){
			message += ",楼层不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getMapCoordinate() ) ){
			message += ",经纬度不可为空";
		}

		if(null == bizPrepareOrder.getContractAmount() ){
			message += ",合同金额不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getSaleType() ) ){
			message += ",套餐类型不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getDesignerName() ) ){
			message += ",设计师不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getDesignerPhone() ) ){
			message += ",设计师电话不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getContractArea() ) ){
			message += ",合同面积不可为空";
		}

		if( StringUtils.isBlank( bizPrepareOrder.getDesignerName() ) ){
			message += ",设计师不匹配";
		}else{

			Map<String, String> paramaterMap = new HashMap<>();
			paramaterMap.put("empName", bizPrepareOrder.getDesignerName());
			paramaterMap.put("empPhone", bizPrepareOrder.getDesignerPhone());
			
			int employeeCount = bizEmployeeDao.getEmployeeCount(paramaterMap);
			if(employeeCount < 1){
				message += ",设计师不匹配";
			}
		}
		

		if(StringUtils.isBlank(bizPrepareOrder.getAuditorName()) || StringUtils.isBlank(bizPrepareOrder.getAuditorPhone()) || null==bizPrepareOrder.getAuditorEmployeeId() ){
			message += ",审计员不匹配";
		}else{

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

		return dao.findPrepareOrderCount(temp);
	}

	public List<OrdertaskingCount> ordertaskingCount(OrdertaskingCount ordertaskingCount) {

		return dao.ordertaskingCount(ordertaskingCount);
	}

	public Integer findMaterialsChoiceBillId(String orderNumber) {

		return dao.findMaterialsChoiceBillId(orderNumber);
	}
	
}