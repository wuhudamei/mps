/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPmPreIndustrySettleBillDao;
import cn.damei.dao.modules.BizOrderDistributeLogDao;
import cn.damei.dao.modules.BizQcLongwayCommissionCnfgSnapDao;
import cn.damei.entity.modules.BizQcLongwayCommissionCnfgSnap;
import cn.damei.dao.modules.BizQcStarCommissionCnfgSnapDao;
import cn.damei.entity.modules.BizQcStarCommissionCnfgSnap;
import cn.damei.entity.modules.DropModel;
import cn.damei.dao.modules.BizEmployeeDao2;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.dao.modules.BizPmGuaranteeMoneyCnfgDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfg;
import cn.damei.dao.modules.OrderDao2;
import cn.damei.entity.modules.ItemManagerMap;
import cn.damei.entity.modules.Order2;
import cn.damei.dao.modules.BizPmGuaranteeMoneyCnfgSnapDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;
import cn.damei.dao.modules.BizPmOwnpayCnfgSnapDao;
import cn.damei.entity.modules.BizPmOwnpayCnfgSnap;
import cn.damei.dao.modules.BizPmSettleCategoryDetailDao;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;
import cn.damei.dao.modules.BizPmStarCommissionCnfgSnapDao;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;

/**
 * 订单管理Service
 * @author wyb
 * @version 2016-09-08
 */
@Service
@Transactional(readOnly = true)
public class OrderService2 extends CrudService2<OrderDao2, Order2> {
	
	@Autowired
	private BizPmGuaranteeMoneyCnfgDao bizPmGuaranteeMoneyCnfgDao;
	@Autowired
	private BizPmGuaranteeMoneyCnfgSnapDao bizPmGuaranteeMoneyCnfgSnapDao;
	@Autowired
	private BizPmStarCommissionCnfgSnapDao bizPmStarCommissionCnfgSnapDao;
	@Autowired
	private BizEmployeeDao2 bizEmployeeDao2;
	@Autowired
	private BizPmOwnpayCnfgSnapDao bizPmOwnpayCnfgSnapDao;
	@Autowired
	private BizPmSettleCategoryDetailDao bizPmSettleCategoryDetailDao;
	@Autowired
	private BizQcLongwayCommissionCnfgSnapDao bizQcLongwayCommissionCnfgSnapDao;
	@Autowired
	private BizQcStarCommissionCnfgSnapDao bizQcStarCommissionCnfgSnapDao;
	@Autowired
	private OrderDao2 orderDao;
	@Autowired
	private BizOrderDistributeLogDao bizOrderDistributeLogDao;
	@Autowired
	private BizPmPreIndustrySettleBillDao bizPmPreIndustrySettleBillDao;


	public List<Map<String ,String>> findOrder() {

		return orderDao.findOrder();
	}
	public List<Map<String ,String>> orderByActualEndDate(String actualEndDate) {
		if(actualEndDate == null){
			return  null;
		}
		return orderDao.orderByActualEndDate(actualEndDate);
	}

	public Order2 get(Integer id) {
		return super.get(id);
	}
	
	public List<Order2> findList(Order2 order2) {
		return super.findList(order2);
	}
	
	public Page<Order2> findPage(Page<Order2> page, Order2 order2) {
		return super.findPage(page, order2);
	}
	
	@Transactional(readOnly = false)
	public void save(Order2 order2) {
		super.save(order2);
	}
	
	@Transactional(readOnly = false)
	public void delete(Order2 order2) {
		super.delete(order2);
	}
	
	public String getOrderNumberById(String orderNumber){
		
		return dao.getOrderNumberById(orderNumber);
	}
	
	public Order2 findOrderById(Integer id){
		return dao.get(id);
	}

	public Integer findCheckedCount(Integer orderInspectorId) {
		// TODO Auto-generated method stub
		return dao.findCheckedCount(orderInspectorId);
	}

	public Integer findCheckingCount(Integer orderInspectorId) {
		// TODO Auto-generated method stub
		return dao.findCheckingCount(orderInspectorId);
	}

	public Integer findBuildingCount(Integer id) {
		// TODO Auto-generated method stub
		return dao.findBuildingCount(id);
	}

	public Integer findOrderCount(Integer id) {
		// TODO Auto-generated method stub
		return dao.findOrderCount(id);
	}
	
	@Transactional(readOnly = false)
	public void updateOrder(Order2 order) {
		try{
			//1----保存质保金快照
			//根据门店和工程模式查询质保金
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("storeId", order.getStoreId());
			map.put("projectMode", order.getProjectMode());
			BizPmGuaranteeMoneyCnfg gmc = bizPmGuaranteeMoneyCnfgDao.queryByStoreIdAndProjectModel(map);
			if( gmc != null){
				BizPmGuaranteeMoneyCnfgSnap gmcs = new BizPmGuaranteeMoneyCnfgSnap();
				gmcs.setStoreId(gmc.getStoreId());
				gmcs.setProjectMode(gmc.getProjectMode());
				gmcs.setPmEmployeeId(order.getItemManagerId());
				gmcs.setGuaranteeMoneyMax(gmc.getGuaranteeMoneyMax());
				gmcs.setOrderId(order.getId());
				gmcs.setGuaranteeMoneyPerOrder(gmc.getGuaranteeMoneyPerOrder());
				gmcs.setGeneratedDatetime(new Date());
				gmcs.preInsert();
				bizPmGuaranteeMoneyCnfgSnapDao.insert(gmcs);
			}
			//2----保存项目经理星级提成快照
			BizEmployee2 bizEmployee = bizEmployeeDao2.get(order.getItemManagerId());
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("storeId", order.getStoreId());
			map1.put("isOldNew", ""); //不区分新房老房
			map1.put("starLever", bizEmployee.getStar());
			map1.put("isEnabled", ConstantUtils.IS_ENABLE_1);//启用
			List<BizPmStarCommissionCnfgSnap> bizPmStarCommissionCnfgSnapList = bizPmStarCommissionCnfgSnapDao.queryByMap(map1);
			//把新房老房的数据循环插入快照表
			if(bizPmStarCommissionCnfgSnapList.size() > 0){
				for (BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap : bizPmStarCommissionCnfgSnapList) {
					bizPmStarCommissionCnfgSnap.setOrderId(order.getId());
					bizPmStarCommissionCnfgSnap.setPmEmployeeId(order.getItemManagerId());
					bizPmStarCommissionCnfgSnap.preInsert();
					bizPmStarCommissionCnfgSnapDao.insert(bizPmStarCommissionCnfgSnap);
				}
			}
			List<BizPmSettleCategoryDetail> detaillist = bizPmSettleCategoryDetailDao.findByOrderId(order.getId(),ConstantUtils.PM_SETTLE_CATEGORY_2);
			
			if(null == detaillist || detaillist.size() == 0){
				//3----保存自主支配快照
				List<BizPmOwnpayCnfgSnap> snaps = bizPmOwnpayCnfgSnapDao.findByOrderId(order.getId());
				if(null != snaps && snaps.size()>0){
					bizPmOwnpayCnfgSnapDao.deleteByOrderId(order.getId());
				}
				Map<String,Object> map2 = new HashMap<String,Object>();
				map2.put("storeId", order.getStoreId());
				map2.put("projectMode", order.getProjectMode());
				map2.put("isEnabled", ConstantUtils.IS_ENABLE_1);
				map2.put("isOldNew", "");//不区分新房老房
				List<BizPmOwnpayCnfgSnap> list = bizPmOwnpayCnfgSnapDao.findListByMap(map2);
				for (BizPmOwnpayCnfgSnap bizPmOwnpayCnfgSnap : list) {
					bizPmOwnpayCnfgSnap.setOrderId(order.getId());
					bizPmOwnpayCnfgSnap.preInsert();
					bizPmOwnpayCnfgSnapDao.insert(bizPmOwnpayCnfgSnap);//改成批量插入
				}
				//bizPmOwnpayCnfgSnapDao.insertList(list);
			}
			dao.update(order);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Page<Order2> findOrderPage(Page<Order2> page, Order2 order2) {
		order2.setPage(page);
		if(StringUtils.isNoneBlank(order2.getOrderStatusNumber())){
			List<String> list = new ArrayList<String>();
			String[] status = order2.getOrderStatusNumber().split(",");
			for(String s:status){
				list.add(s);
			}
			order2.setOrderStatus(list);
		}
		page.setList(dao.findOrderList(order2));
		return page;
	}

	public Page<Order2> findOrderPaymentPage(Page<Order2> page, Order2 order2) {
		order2.setPage(page);
		if(StringUtils.isNoneBlank(order2.getOrderStatusNumber())){
			List<String> list = new ArrayList<String>();
			String[] status = order2.getOrderStatusNumber().split(",");
			for(String s:status){
				list.add(s);
			}
			order2.setOrderStatus(list);
		}
		if(StringUtils.isNoneBlank(order2.getPaymentStatus())){
			if("1".equals(order2.getPaymentStatus())){ // 未付款
				List<String> list = new ArrayList<String>();
				list.add(ConstantUtils.PAYMENT_STATUS_10);
				list.add(ConstantUtils.PAYMENT_STATUS_15);
				list.add(ConstantUtils.PAYMENT_STATUS_20);
				list.add(ConstantUtils.PAYMENT_STATUS_30);
				list.add(ConstantUtils.PAYMENT_STATUS_90);
				order2.setPaymentStatusList(list);
			}else if("2".equals(order2.getPaymentStatus())){
				List<String> list = new ArrayList<String>();
				list.add(ConstantUtils.PAYMENT_STATUS_40);
				order2.setPaymentStatusList(list);
			}else if("3".equals(order2.getPaymentStatus())){
				List<String> list = new ArrayList<String>();
				list.add(ConstantUtils.PAYMENT_STATUS_100);
				order2.setPaymentStatusList(list);
			}
		}
		page.setList(dao.findOrderPaymentList(order2));
		return page;
	}

	/**
	 * 根据地图分配项目经理--根据订单id查询项目经理
	 * @param itemManagerMap
	 * @return
	 */
	public List<ItemManagerMap> findMapList(ItemManagerMap itemManagerMap) {
		return dao.findMapList(itemManagerMap);
	}

	
	//地图经理数据查询 
		/**
		 * 
		 * @param managerName,star,storeId,engineDepartId,projectMode
		 * @return
		 */
		public List<Order2>findManagerInfo(Order2 Order2){
			
			
			
			
			return dao.findManagerInfo(Order2);
		}
	/**
	 * 工人地图查询
	 * @param Order2
	 * @return
	 */
		public List<Order2>findWorkerMapInfo(Order2 Order2){
			
			
			
			
			return dao.findWorkerMapInfo(Order2);
		}
		
	
	
	/**
	 * 根据项目经理查询名下订单
	 * @param empId
	 * @return
	 */
	public List<DropModel> findOrderListByCondition(Integer empId) {
		return dao.findOrderListByCondition(empId);
	}
	
	@Transactional(readOnly = false)
	public void update(Order2 order) {
		dao.update(order);
	}

	public List<Order2> findOrderByPhone(String username) {

		return dao.findOrderByPhone(username);
	}

	public Page<Order2> findOrderManagerGuranteeMoneyPage(Page<Order2> page, Order2 entity) {
		entity.setPage(page);
		page.setList(dao.findOrderManagerGuranteeMoneyList(entity));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void updateOrderInspector(Order2 order) {
		
		try{
			//1----保存远程费快照信息 有则保存 没有则不保存
			//根据门店查询
			if(ConstantUtils.ISLONGWAYCOMMISSION_1.equals(order.getIsLongwayCommission())){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("storeId", order.getStoreId());
				map.put("isEnabled", ConstantUtils.IS_ENABLE_1);
				BizQcLongwayCommissionCnfgSnap bizQcLongwayCommissionCnfgSnap = bizQcLongwayCommissionCnfgSnapDao.queryByMap(map);
				if(bizQcLongwayCommissionCnfgSnap != null){
					bizQcLongwayCommissionCnfgSnap.setOrderId(order.getId());
					bizQcLongwayCommissionCnfgSnap.setPmEmployeeId(order.getOrderInspectorId());
					bizQcLongwayCommissionCnfgSnap.preInsert();
					bizQcLongwayCommissionCnfgSnapDao.insert(bizQcLongwayCommissionCnfgSnap);
				}
			}
			//2----保存质检员星级提成快照信息	
			BizEmployee2 bizEmployee = bizEmployeeDao2.get(order.getOrderInspectorId());
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("storeId", order.getStoreId());
			map1.put("isOldNew", order.getHouseIsNew());
			map1.put("houseType", order.getBuildType());
			map1.put("starLevel", bizEmployee.getStar());
			map1.put("isEnabled", ConstantUtils.IS_ENABLE_1);//启用
			BizQcStarCommissionCnfgSnap bizQcStarCommissionCnfgSnap = bizQcStarCommissionCnfgSnapDao.queryByMap(map1);
			if(bizQcStarCommissionCnfgSnap != null){
				bizQcStarCommissionCnfgSnap.setOrderId(order.getId());
				bizQcStarCommissionCnfgSnap.setPmEmployeeId(order.getOrderInspectorId());
				bizQcStarCommissionCnfgSnap.preInsert();
				bizQcStarCommissionCnfgSnapDao.insert(bizQcStarCommissionCnfgSnap);
			}
			dao.update(order);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Integer findUnfinishedOrderByEmployeeId(Map<String,Integer> map) {
		return dao.findUnfinishedOrderByEmployeeId(map);
	}
	public List<ItemManagerMap> findManagerMoreCount(String date){
		
		return dao.findManagerMoreCount(date);
	}
	public List<ItemManagerMap> findManagerMoreCount1(List<ItemManagerMap> list){
		return dao.findManagerMoreCount1(list);
		
	}

	/**
	 * 校验订单数据是否正确
	 * @param order
	 * @return
	 */
	public String verificationPrepareOrder(Order2 order) {
		
		String result = "true";
		//一.校验必填项
		//1.订单编号

		if(order == null || null==order.getOrderNumber() || "".equals(order.getOrderNumber())){
			result = "error";
			return result;
		}
		//2.门店
		if(null == order.getStoreId() || "".equals(order.getStoreId())){
			result = "error";
			return result;
		}
		//3.工程模式
		if(null == order.getProjectMode() || "".equals(order.getProjectMode())){
			result = "error";
			return result;
		}
		//4.区域
		if(null == order.getEnginDepartId()){
			result = "error";
			return result;
		}
		//5.接单时间
		if(null == order.getGetOrderDatetime()){
			result = "error";
			return result;
		}
		//6.客户姓名
		if(null == order.getCustomerName() || "".equals(order.getCustomerName())){
			result = "error";
			return result;
		}
		//7.客户手机号
		if(null == order.getCustomerPhone() || "".equals(order.getCustomerPhone())){
			result = "error";
			return result;
		}
		//8.小区名称
		if(null == order.getCommunityName() || "".equals(order.getCommunityName())){
			result = "error";
			return result;
		}
		//9.楼
		if(null == order.getBuildNumber() || "".equals(order.getBuildNumber())){
			result = "error";
			return result;
		}
		//10.单元
		if(null == order.getBuildUnit() || "".equals(order.getBuildUnit())){
			result = "error";
			return result;
		}
		//11.室
		if(null == order.getBuildRoom() || "".equals(order.getBuildRoom())){
			result = "error";
			return result;
		}
		//12.详细地址
		if(null == order.getDetailAddress() || "".equals(order.getDetailAddress())){
			result = "error";
			return result;
		}
		//13.省
		if(null == order.getProvince() || "".equals(order.getProvince())){
			result = "error";
			return result;
		}
		//14.市
		if(null == order.getCity() || "".equals(order.getCity())){
			result = "error";
			return result;
		}
		//15.县
		if(null == order.getCounty() || "".equals(order.getCounty())){
			result = "error";
			return result;
		}
		//16.房屋类型
		if(null == order.getBuildType() || "".equals(order.getBuildType())){
			result = "error";
			return result;
		}
		//17.新老房
		if(null == order.getHouseIsNew() || "".equals(order.getHouseIsNew())){
			result = "error";
			return result;
		}
		//18.有无电梯
		if(null == order.getIsElevator() || "".equals(order.getIsElevator())){
			result = "error";
			return result;
		}
		//19.合同开工日期
		if(null == order.getContractStartDate()){
			result = "error";
			return result;
		}
		//20.地址
		if(null == order.getCustomerAddress() || "".equals(order.getCustomerAddress())){
			result = "error";
			return result;
		}
		//21.楼层
		if(null == order.getFloor()){
			result = "error";
			return result;
		}
		//22.经纬度
		if(null == order.getMapCoordinate() || "".equals(order.getMapCoordinate())){
			result = "error";
			return result;
		}
		//23.合同金额
//		if(null == order.getContractAmount() || "".equals(order.getContractAmount())){
//			result = "error";
//			return result;
//		}
		//24.套餐类型
		if(null == order.getSaleType() || "".equals(order.getSaleType())){
			result = "error";
			return result;
		}
		//25.设计师
		if(null == order.getDesignerName() || "".equals(order.getDesignerName())){
			result = "error";
			return result;
		}
		//26.设计师电话
		if(null == order.getDesignerPhone() || "".equals(order.getDesignerPhone())){
			result = "error";
			return result;
		}
		//27.合同面积
		if(null == order.getContractArea() || "".equals(order.getContractArea())){
			result = "error";
			return result;
		}
		return result;
	}
	@Transactional(readOnly = true)
	public int getUnAllotCount(Order2 order) {
		return dao.getUnAllotCount(order);
	}
	@Transactional(readOnly = true)
	public int getUnInspectorCount(Order2 order) {
		// TODO Auto-generated method stub
		return dao.getUnInspectorCount(order);
	}

	public Page<Order2> getProMangerDaily(Page<Order2> page, Order2 order) {
		order.setPage(page);
		page.setList(dao.getProMangerDaily(order));
		return page;
	}

	public int getProMangerCount(Order2 order) {
		return dao.getProMangerCount(order);
	}

	public Page<Order2> getInspectorDaily(Page<Order2> page, Order2 order) {
		order.setPage(page);
		page.setList(dao.getInspectorDaily(order));
		return page;
	}
	
	public int getInspectorCount(Order2 order) {
		return dao.getInspectorCount(order);
	}

	public int checkPmPreIndustry(Map<String, Object> paramaterMap) {
		return bizPmPreIndustrySettleBillDao.checkPmPreIndustrySettleBillByParam(paramaterMap);
	}

	public String findDesignerId(Order2 orderId) {
		// TODO Auto-generated method stub
		return dao.findDesignerId(orderId);
	}



}