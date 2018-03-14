/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentSummaryVo;
import cn.damei.entity.modules.Payment;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.modules.BizOrderTaskpackagePayment;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetails;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentVo;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDao;

/**
 * 付款单Service
 * @author qww
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class BizOrderTaskpackagePaymentService extends CrudService2<BizOrderTaskpackagePaymentDao, BizOrderTaskpackagePayment> {

	public BizOrderTaskpackagePayment get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderTaskpackagePayment> findList(BizOrderTaskpackagePayment bizOrderTaskpackagePayment) {
		return super.findList(bizOrderTaskpackagePayment);
	}
	
	public Page<BizOrderTaskpackagePayment> findPage(Page<BizOrderTaskpackagePayment> page, BizOrderTaskpackagePayment bizOrderTaskpackagePayment) {
		return super.findPage(page, bizOrderTaskpackagePayment);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderTaskpackagePayment bizOrderTaskpackagePayment) {
		super.save(bizOrderTaskpackagePayment);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderTaskpackagePayment bizOrderTaskpackagePayment) {
		super.delete(bizOrderTaskpackagePayment);
	}
	@Transactional(readOnly = false)
	public void insertPayment(BizOrderTaskpackagePayment payment) {
		payment.preInsert();
		dao.insert(payment);
		
	}

	public List<BizOrderTaskpackagePayment> findPaymentBySettlementId(Integer id) {
		
		return dao.findPaymentBySettlementId(id);
	}
	
	public List<BizOrderTaskpackagePaymentVo> queryPaymentByCondition(BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo){
		
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", ConstantUtils.PAYMENT_STATUS);
		map.put("paymentType0", ConstantUtils.ORDER_TASKPACKAGE_PAYMENT_TYPE_0);
		map.put("paymentType1", ConstantUtils.ORDER_TASKPACKAGE_PAYMENT_TYPE_1);
		map.put("orderStatusNumber", ConstantUtils.ORDER_STATUS_NUMBER_320);
		List<String> list = new ArrayList<String>();
		list.add(ConstantUtils.PAYMENT_STATUS_10);
		list.add(ConstantUtils.PAYMENT_STATUS_90);
		map.put("list", list);
		if(bizOrderTaskpackagePaymentVo.getStoreId() != null){
			map.put("storeId", bizOrderTaskpackagePaymentVo.getStoreId());
		}
		if(bizOrderTaskpackagePaymentVo.getStartDate() != null){
			map.put("startDate", bizOrderTaskpackagePaymentVo.getStartDate());
		}
		if(bizOrderTaskpackagePaymentVo.getEndDate() != null){
			map.put("endDate", bizOrderTaskpackagePaymentVo.getEndDate());
		}
		return dao.queryPaymentByCondition(map);*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", ConstantUtils.PAYMENT_STATUS);
		map.put("paymentType0", ConstantUtils.ORDER_TASKPACKAGE_PAYMENT_TYPE_0);//首款
		map.put("paymentType1", ConstantUtils.ORDER_TASKPACKAGE_PAYMENT_TYPE_1);//尾款
		map.put("qcStatus", "30");
		map.put("qcType", "1");
		map.put("ischeck", "0");
		map.put("cnrStatus", "1");//任务包和之间节点关系状态--启用
		
		//map.put("orderStatusNumber", ConstantUtils.ORDER_STATUS_NUMBER_320);
		List<String> list = new ArrayList<String>();
		//list.add(ConstantUtils.PAYMENT_STATUS_10);---www
		if(bizOrderTaskpackagePaymentVo.getStatus() == null || bizOrderTaskpackagePaymentVo.getStatus().equals("")){
			list.add(ConstantUtils.PAYMENT_STATUS_15);//---www
			list.add(ConstantUtils.PAYMENT_STATUS_90);
		}else{
			list.add(bizOrderTaskpackagePaymentVo.getStatus());
		}
		
		map.put("list", list);
		if(bizOrderTaskpackagePaymentVo.getStoreId() != null){
			map.put("storeId", bizOrderTaskpackagePaymentVo.getStoreId());
		}
		if(bizOrderTaskpackagePaymentVo.getProjectMode() != null){
			map.put("projectMode", bizOrderTaskpackagePaymentVo.getProjectMode());
		}
		if(bizOrderTaskpackagePaymentVo.getStartDate() != null){
			map.put("startDate", bizOrderTaskpackagePaymentVo.getStartDate());
		}
		if(bizOrderTaskpackagePaymentVo.getEndDate() != null){
			map.put("endDate", bizOrderTaskpackagePaymentVo.getEndDate());
		}
		if(bizOrderTaskpackagePaymentVo.getEnginDepartId() != null){
			map.put("enginDepartId", bizOrderTaskpackagePaymentVo.getEnginDepartId());
		}
		if(CollectionUtils.isNotEmpty(bizOrderTaskpackagePaymentVo.getEnginDepartIds())){
			map.put("enginDepartIds", bizOrderTaskpackagePaymentVo.getEnginDepartIds());
		}
		if(bizOrderTaskpackagePaymentVo.getCustomerName() != null && !bizOrderTaskpackagePaymentVo.getCustomerName().equals("")){
			map.put("customerName",bizOrderTaskpackagePaymentVo.getCustomerName());
		}
		if(bizOrderTaskpackagePaymentVo.getRealName() != null && !bizOrderTaskpackagePaymentVo.getRealName().equals("")){
			map.put("realName",bizOrderTaskpackagePaymentVo.getRealName());
		}
		if(bizOrderTaskpackagePaymentVo.getGroupRealname() != null && !bizOrderTaskpackagePaymentVo.getGroupRealname().equals("")){
			map.put("groupRealname",bizOrderTaskpackagePaymentVo.getGroupRealname());
		}
		return dao.queryPaymentByCondition(map);
	}

	public List<BizOrderTaskpackagePayment> queryPaymentBySummaryId(Integer id) {
		
		return dao.queryPaymentBySummaryId(id);
	}
	
	public BizOrderTaskpackagePayment queryPaymentByPaymentDetailId(Integer detailId){
		
		return dao.queryPaymentByPaymentDetailId(detailId);
	}

	public List<BizOrderTaskpackagePaymentVo> queryPaymentForCheck(Map<String,Object> map) {
		
		return dao.queryPaymentForCheck(map);
	}

	public List<BizOrderTaskpackagePaymentVo> queryPaymentForCheckByQcBillId(Map<String,Object> map) {
		
		return dao.queryPaymentForCheckByQcBillId(map);
	}
	
	//查看验收详情
	public BizOrderTaskpackagePaymentDetails findQcBill(Integer qcBillId) {
		return dao.findQcBill(qcBillId);
	}

	//查看验收图片
	public List<ReportCheckDetailsPic> findPic(Integer qcBillId) {
		return dao.findPic(qcBillId);
	}
	
	//修改质检单状态
	@Transactional(readOnly = false)
	public void updateQcbillStatusById(Integer qcBillId, String status,String reason) {

		dao.updateQcbillStatusById(qcBillId,status,reason);
	}
	
	//修改付款单状态
	@Transactional(readOnly = false)
	public void updateStatusByPaymentId(Integer paymentId, String status) {
		dao.updateStatusByPaymentId(paymentId,status);
	}

	public Page<BizOrderTaskpackagePaymentVo> findPaymentPage(Page<BizOrderTaskpackagePaymentVo> page, BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo) {
		bizOrderTaskpackagePaymentVo.setPage(page);
		if(StringUtils.isNoneBlank(bizOrderTaskpackagePaymentVo.getStatus())){
			List<String> list = new ArrayList<String>();
			String[] status = bizOrderTaskpackagePaymentVo.getStatus().split(",");
			for(String s:status){
				list.add(s);
			}
			bizOrderTaskpackagePaymentVo.setPaymentStatus(list);
		}
		page.setList(dao.findPaymentList(bizOrderTaskpackagePaymentVo));
		return page;
	}
	
	public Page<BizOrderTaskpackagePaymentVo> findPaymentFreezePage(Page<BizOrderTaskpackagePaymentVo> page, BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo) {
		bizOrderTaskpackagePaymentVo.setPage(page);
		List<String> list = new ArrayList<String>();
		if(StringUtils.isNoneBlank(bizOrderTaskpackagePaymentVo.getStatus())){
			String[] status = bizOrderTaskpackagePaymentVo.getStatus().split(",");
			for(String s:status){
				list.add(s);
			}
		}else{
			list.add("15");
			list.add("18");
			list.add("90");
		}
		bizOrderTaskpackagePaymentVo.setPaymentStatus(list);
		page.setList(dao.findPaymentFreezeList(bizOrderTaskpackagePaymentVo));
		return page;
	}

	public BizOrderTaskpackagePaymentVo findPaymentListView(Integer id){
		return dao.findPaymentListView(id);
	}

	public List<Payment> findPayments(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findPayments(map);
	}

	public Page<BizOrderTaskpackagePaymentSummaryVo> findPaymentSummaryPage(Page<BizOrderTaskpackagePaymentSummaryVo> page, BizOrderTaskpackagePaymentSummaryVo bizOrderTaskpackagePaymentSummaryVo) {
		bizOrderTaskpackagePaymentSummaryVo.setPage(page);
		List<String> summaryStatusList = new ArrayList<String>();
		if(StringUtils.isNoneBlank(bizOrderTaskpackagePaymentSummaryVo.getSummaryStatus())){
			String[] status = bizOrderTaskpackagePaymentSummaryVo.getSummaryStatus().split(",");
			for(String s:status){
				summaryStatusList.add(s);
			}
		}else{
			summaryStatusList.add(ConstantUtils.SUMMARY_STATUS_10);
			summaryStatusList.add(ConstantUtils.SUMMARY_STATUS_20);
			summaryStatusList.add(ConstantUtils.SUMMARY_STATUS_50);
			summaryStatusList.add(ConstantUtils.SUMMARY_STATUS_100);
		}

		List<String> paymentStatusList = new ArrayList<String>();
		if(StringUtils.isNoneBlank(bizOrderTaskpackagePaymentSummaryVo.getPaymentStatus())){
			String[] status = bizOrderTaskpackagePaymentSummaryVo.getPaymentStatus().split(",");
			for(String s:status){
				paymentStatusList.add(s);
			}
		}else{
			paymentStatusList.add(ConstantUtils.PAYMENT_STATUS_10);
			paymentStatusList.add(ConstantUtils.PAYMENT_STATUS_15);
			paymentStatusList.add(ConstantUtils.PAYMENT_STATUS_20);
			paymentStatusList.add(ConstantUtils.PAYMENT_STATUS_30);
			paymentStatusList.add(ConstantUtils.PAYMENT_STATUS_40);
			paymentStatusList.add(ConstantUtils.PAYMENT_STATUS_100);
		}

		bizOrderTaskpackagePaymentSummaryVo.setSummaryStatusList(summaryStatusList);
		bizOrderTaskpackagePaymentSummaryVo.setPaymentStatusList(paymentStatusList);
		page.setList(dao.findPaymentSummaryList(bizOrderTaskpackagePaymentSummaryVo));
		return page;
	}
	
	public List<BizOrderTaskpackagePaymentVo> checkPaymentByIds(String ids){
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> list = new ArrayList<String>();
		list.add(ids);
		map.put("list",list);
		return dao.checkPaymentByIds(map);
	}
	
	public List<BizOrderTaskpackagePaymentVo> findPaymentVoBySettlementId(Integer id){
		return dao.findPaymentVoBySettlementId(id);
	}
	
	public int checkPaymentIsExistByParam(Map<String,Object> param){
		return dao.checkPaymentIsExistByParam(param);
	}
	
	public List<BizOrderTaskpackagePaymentVo> findPaymentVoByOrderId(Integer orderId){
		return dao.findPaymentVoByOrderId(orderId);
	}
	
	public BizOrderTaskpackagePayment findBalancePaymentByPaymentId(Integer paymentId){
		return dao.findBalancePaymentByPaymentId(paymentId);
	}
}