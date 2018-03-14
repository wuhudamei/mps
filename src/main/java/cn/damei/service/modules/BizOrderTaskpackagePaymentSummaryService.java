/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.*;

import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.entity.modules.BizPhoneMsg;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentSummary;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentSummaryRel;
import cn.damei.entity.modules.OrderInformation;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailMergeDao;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailMergeRelDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMerge;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeRel;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailSplitDao;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDao;
import cn.damei.entity.modules.BizOrderTaskpackagePayment;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetaiVo;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetail;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentSummaryDao;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentSummaryRelDao;

/**
 * 付款单批次Service
 * @author 汪文文
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class BizOrderTaskpackagePaymentSummaryService extends CrudService2<BizOrderTaskpackagePaymentSummaryDao, BizOrderTaskpackagePaymentSummary> {

	@Autowired
	private BizSeiralnumService bizSeiralnumService;
	
	@Autowired
	private BizOrderTaskpackagePaymentSummaryRelDao bizOrderTaskpackagePaymentSummaryRelDao;
	
	@Autowired
	private BizOrderTaskpackagePaymentDetailDao bizOrderTaskpackagePaymentDetailDao;
	
	@Autowired
	private BizOrderTaskpackagePaymentDao bizOrderTaskpackagePaymentDao;
	
	@Autowired
	private BizOrderTaskpackagePaymentDetailMergeDao bizOrderTaskpackagePaymentDetailMergeDao;
	
	@Autowired
	private BizOrderTaskpackagePaymentDetailMergeRelDao bizOrderTaskpackagePaymentDetailMergeRelDao;
	
	@Autowired
	private BizOrderTaskpackagePaymentDetailSplitDao bizOrderTaskpackagePaymentDetailSplitDao;

	@Autowired
	private BizMessagegroupService bizMessagegroupService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	
	public BizOrderTaskpackagePaymentSummary get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderTaskpackagePaymentSummary> findList(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary) {
		return super.findList(bizOrderTaskpackagePaymentSummary);
	}
	
	public Page<BizOrderTaskpackagePaymentSummary> findPage(Page<BizOrderTaskpackagePaymentSummary> page, BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary) {
		return super.findPage(page, bizOrderTaskpackagePaymentSummary);
	}
	
	/**
	 *  财务人员使用批次列表
	 */
	public Page<BizOrderTaskpackagePaymentSummary> findSummaryPage(Page<BizOrderTaskpackagePaymentSummary> page, BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary) {
		bizOrderTaskpackagePaymentSummary.setPage(page);
		page.setList(dao.findSummaryList(bizOrderTaskpackagePaymentSummary));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary) {
		super.save(bizOrderTaskpackagePaymentSummary);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary) {
		super.delete(bizOrderTaskpackagePaymentSummary);
	}
	
	@Transactional(readOnly = false)
	public void saveSummary(String[] ids) {
		String orderTaskpackagePaymentSummaryCode = bizSeiralnumService.getDateSequence("PC");
		User user = UserUtils.getUser();
		Date date = new Date();

		BizOrderTaskpackagePayment pay = bizOrderTaskpackagePaymentDao.get(Integer.parseInt(ids[0]));
		//1.生成批次表
		BizOrderTaskpackagePaymentSummary summary = new BizOrderTaskpackagePaymentSummary();
		summary.setOrderTaskpackagePaymentSummaryCode(orderTaskpackagePaymentSummaryCode);
		summary.setOrderTaskpackagePaymentCount(ids.length);
		summary.setGeneratedDatetime(date);
		summary.setApplyEmployeeId(Integer.parseInt(user.getId()));
		summary.setStatus(ConstantUtils.SUMMARY_STATUS_10);
		summary.setCreateBy(user);
		summary.setCreateDate(date);
		summary.setUpdateBy(user);
		summary.setUpdateDate(date);
		summary.setStoreId(pay.getStoreId());
		summary.setEnginDepartId(pay.getEnginDepartId());
		summary.setProjectMode(pay.getProjectMode());
		dao.insert(summary);
		
		//2.生成批次与付款单关系表
		BizOrderTaskpackagePaymentSummary paymentSummary = dao.queryPaymentSummaryByNo(orderTaskpackagePaymentSummaryCode);
		if(paymentSummary != null){
			for(String id:ids){
				BizOrderTaskpackagePaymentSummaryRel summaryRel = new BizOrderTaskpackagePaymentSummaryRel();
				summaryRel.setOrderTaskpackagePaymentSummaryId(paymentSummary.getId());
				summaryRel.setOrderTaskpackagePaymentId(Integer.parseInt(id));
				bizOrderTaskpackagePaymentSummaryRelDao.insert(summaryRel);
				
				//3.更新付款单状态
				BizOrderTaskpackagePayment payment = bizOrderTaskpackagePaymentDao.get(Integer.parseInt(id));
				if(payment != null){
					payment.setStatus(ConstantUtils.PAYMENT_STATUS_20);
					payment.setStatusDatetime(date);
					payment.setUpdateBy(user);
					payment.setUpdateDate(date);
					bizOrderTaskpackagePaymentDao.update(payment);
				}
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void aprovePass(Integer id) {
		User user = UserUtils.getUser();
		Date date = new Date();
		
		//1.新增付款单明细合并
		List<BizOrderTaskpackagePaymentDetaiVo> paymentDetailVoList =  bizOrderTaskpackagePaymentDetailDao.queryEmployeeAllAmount(id);
		for(BizOrderTaskpackagePaymentDetaiVo paymentDetailVo:paymentDetailVoList){
			BizOrderTaskpackagePaymentDetailMerge merge = new BizOrderTaskpackagePaymentDetailMerge();
			merge.setOrderTaskpackagePaymentSummaryId(id);
			merge.setEmployeeId(paymentDetailVo.getEmployeeId());
			merge.setAmount(paymentDetailVo.getAllAmount());
			merge.setStatus(ConstantUtils.PAYMENT_DETAIL_STATUS_0);
			merge.setStatusDatetime(date);
			merge.setCreateBy(user);
			merge.setCreateDate(date);
			merge.setUpdateBy(user);
			merge.setUpdateDate(date);
			bizOrderTaskpackagePaymentDetailMergeDao.insert(merge);
			
			//2.新增付款单明细合并关系
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("summaryId", id);
			map.put("employeeId", merge.getEmployeeId());
			BizOrderTaskpackagePaymentDetailMerge detailMerge = bizOrderTaskpackagePaymentDetailMergeDao.queryPaymentDetailMergeBySummaryIdAndEmployeeId(map);
			
			List<BizOrderTaskpackagePaymentDetail> paymentDetailList = bizOrderTaskpackagePaymentDetailDao.queryEmployeePaymentDetailId(map);
			for(BizOrderTaskpackagePaymentDetail paymentDetail:paymentDetailList){
				BizOrderTaskpackagePaymentDetailMergeRel mergeRel = new BizOrderTaskpackagePaymentDetailMergeRel();
				mergeRel.setOrderTaskpackagePaymentDetailMergeId(detailMerge.getId());
				mergeRel.setOrderTaskpackagePaymentDetailId(paymentDetail.getId());
				bizOrderTaskpackagePaymentDetailMergeRelDao.insert(mergeRel);
			}
		}
		
		//3.更新批次状态
		BizOrderTaskpackagePaymentSummary summary = dao.queryOrderTaskpackagePaymentSummaryById(id);
		summary.setStatus(ConstantUtils.SUMMARY_STATUS_20);
		summary.setExamineEmployeeId(Integer.parseInt(user.getId()));
		summary.setExamineDatetime(date);
		summary.setUpdateBy(user);
		summary.setUpdateDate(date);
		dao.update(summary);
		
		//4.更新付款单
		List<BizOrderTaskpackagePayment> paymentList = bizOrderTaskpackagePaymentDao.queryPaymentBySummaryId(id);
		for(BizOrderTaskpackagePayment payment:paymentList){
			payment.setStatus(ConstantUtils.PAYMENT_STATUS_30);
			payment.setStatusDatetime(date);
			payment.setUpdateBy(user);
			payment.setUpdateDate(date);
			bizOrderTaskpackagePaymentDao.update(payment);
		}

		//5.发短信
		BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(summary.getStoreId()+"", ConstantUtils.MESSAGE_GROUP_TYPE_100);
		List<Integer> list = new ArrayList<Integer>();
		if(bizMessagegroup != null && StringUtils.isNoneBlank(bizMessagegroup.getEmployees())){
			String[] str = bizMessagegroup.getEmployees().split(",");
			for(String id1: str) {
				list.add(Integer.valueOf(id1));
			}

			BizOrderTaskpackagePaymentSummary paymentSummary = dao.querySendMsgForSummary(id);
			String content = "批次编号（"+paymentSummary.getOrderTaskpackagePaymentSummaryCode()+"），结算员（"+paymentSummary.getRealName()+"-"+paymentSummary.getPhone()+
					"）批次审核通过，请及时登录系统进行付款操作。";
			BizPhoneMsg msg = new BizPhoneMsg();
			msg.setMsgContent(content);
			msg.setMsgGenerateDatetime(date);
			msg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			msg.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_201101);
			msg.setRelatedBusinessIdInt(id);

			List<BizEmployee2> employeelist = bizEmployeeService2.getById(list);
			if(CollectionUtils.isNotEmpty(employeelist)){
				for(BizEmployee2 employee:employeelist){
					msg.setReceiveEmployeeId(employee.getId());
					msg.setReceivePhone(employee.getPhone());
					bizPhoneMsgService.insert(msg);
				}
			}
		}
	}
	
	@Transactional(readOnly = false)
	public String summaryAbolish(Integer id,String cancleReason) {
		/*try{*/
			User user = UserUtils.getUser();
			Date date = new Date();

			//1.更新付款批次
			BizOrderTaskpackagePaymentSummary summary = dao.get(id);
			if(ConstantUtils.SUMMARY_STATUS_90.equals(summary.getStatus())){
				return "already";
			}
			summary.setStatus(ConstantUtils.SUMMARY_STATUS_90);
			summary.setExamineEmployeeId(Integer.parseInt(user.getId()));
			summary.setExamineDatetime(date);
			summary.setCancleReason(cancleReason);
			summary.setUpdateBy(user);
			summary.setUpdateDate(date);
			dao.update(summary);

			//2.更新付款单
			List<BizOrderTaskpackagePayment> paymentList = bizOrderTaskpackagePaymentDao.queryPaymentBySummaryId(id);
			for(BizOrderTaskpackagePayment payment:paymentList){
				payment.setStatus(ConstantUtils.PAYMENT_STATUS_90);
				payment.setStatusDatetime(date);
				payment.setUpdateBy(user);
				payment.setUpdateDate(date);
				bizOrderTaskpackagePaymentDao.update(payment);
			}
			
			//3.删除明细合并拆分
			List<Integer> list = bizOrderTaskpackagePaymentDetailSplitDao.queryPaymentDetailId(id);
			for (int i = 0; i < list.size(); i++) {
				bizOrderTaskpackagePaymentDetailSplitDao.deleteBySummaryId(list.get(i));
			}
			
//			bizOrderTaskpackagePaymentDetailSplitDao.deleteBySummaryId(id);
			return "success";
		/*}catch (Exception e){
			return "error";
		}*/
	}

	public OrderInformation queryOrderByPaymentCode(String paymentCode) {
		// TODO Auto-generated method stub
		return dao.queryOrderByPaymentCode(paymentCode);
	}

	public Page<BizOrderTaskpackagePaymentSummary> findPaymentSummaryList(Page<BizOrderTaskpackagePaymentSummary> page, BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary){
		bizOrderTaskpackagePaymentSummary.setPage(page);
		if(StringUtils.isNoneBlank(bizOrderTaskpackagePaymentSummary.getStatus())){
			List<String> list = new ArrayList<String>();
			String[] status = bizOrderTaskpackagePaymentSummary.getStatus().split(",");
			for(String s:status){
				list.add(s);
			}
			bizOrderTaskpackagePaymentSummary.setSummaryStatus(list);
		}
		List<BizOrderTaskpackagePaymentSummary> summaryList = dao.findPaymentSummaryList(bizOrderTaskpackagePaymentSummary);
		page.setList(summaryList);
		return page;
	}

	public Page<BizOrderTaskpackagePaymentSummary> findPaymentSummaryAllList(Page<BizOrderTaskpackagePaymentSummary> page, BizOrderTaskpackagePaymentSummary bizOrderTaskpackagePaymentSummary){
		bizOrderTaskpackagePaymentSummary.setPage(page);
		if(StringUtils.isNoneBlank(bizOrderTaskpackagePaymentSummary.getStatus())){
			List<String> list = new ArrayList<String>();
			String[] status = bizOrderTaskpackagePaymentSummary.getStatus().split(",");
			for(String s:status){
				list.add(s);
			}
			bizOrderTaskpackagePaymentSummary.setSummaryStatus(list);
		}
		List<BizOrderTaskpackagePaymentSummary> summaryList = dao.findPaymentSummaryAllList(bizOrderTaskpackagePaymentSummary);
		page.setList(summaryList);
		return page;
	}
}