/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.damei.dao.modules.BizEmployeeDao2;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.entity.modules.BizPaymentDetailSplitBankcard;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailSplit;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailSplitVo;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.BizEmployeeBankcardDao2;
import cn.damei.entity.modules.BizEmployeeBankcard2;
import cn.damei.dao.modules.BizEmployeeBankcardRelatedIdcardDao;
import cn.damei.entity.modules.BizEmployeeBankcardRelatedIdcard;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDao;
import cn.damei.entity.modules.BizOrderTaskpackagePayment;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetail;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailSplitDao;

/**
 * 付款单明细拆分Service
 * @author www
 * @version 2016-10-31
 */
@Service
@Transactional(readOnly = true)
public class BizOrderTaskpackagePaymentDetailSplitService extends CrudService2<BizOrderTaskpackagePaymentDetailSplitDao, BizOrderTaskpackagePaymentDetailSplit> {
	
	@Autowired
	private BizOrderTaskpackagePaymentDetailSplitDao bizOrderTaskpackagePaymentDetailSplitDao;
	
	@Autowired
	private BizOrderTaskpackagePaymentDao bizOrderTaskpackagePaymentDao;
	
	@Autowired
	private BizOrderTaskpackagePaymentDetailDao bizOrderTaskpackagePaymentDetailDao;
	
	@Autowired
	private BizEmployeeBankcardDao2 bizEmployeeBankcardDao2;
	
	@Autowired
	private BizEmployeeBankcardRelatedIdcardDao bizEmployeeBankcardRelatedIdcardDao;

	@Autowired
	private BizEmployeeDao2 bizEmployeeDao2;
	
	public BizOrderTaskpackagePaymentDetailSplit get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderTaskpackagePaymentDetailSplit> findList(BizOrderTaskpackagePaymentDetailSplit bizOrderTaskpackagePaymentDetailSplit) {
		return super.findList(bizOrderTaskpackagePaymentDetailSplit);
	}
	
	public Page<BizOrderTaskpackagePaymentDetailSplit> findPage(Page<BizOrderTaskpackagePaymentDetailSplit> page, BizOrderTaskpackagePaymentDetailSplit bizOrderTaskpackagePaymentDetailSplit) {
		return super.findPage(page, bizOrderTaskpackagePaymentDetailSplit);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderTaskpackagePaymentDetailSplit bizOrderTaskpackagePaymentDetailSplit) {
		super.save(bizOrderTaskpackagePaymentDetailSplit);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderTaskpackagePaymentDetailSplit bizOrderTaskpackagePaymentDetailSplit) {
		super.delete(bizOrderTaskpackagePaymentDetailSplit);
	}

	/**
	 * 根据批次id查询拆分信息
	 * @param summaryId
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentDetailSplitVo> findPaymentDetailSplitBySummaryId(Integer summaryId) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		//List<BizOrderTaskpackagePaymentDetailSplitVo> list = new ArrayList<BizOrderTaskpackagePaymentDetailSplitVo>();
		Map<String,Double> map = new HashMap<String,Double>();
		//根据批次id查询拆分信息
		List<BizOrderTaskpackagePaymentDetailSplitVo> splits = bizOrderTaskpackagePaymentDetailSplitDao.findPaymentDetailSplitBySummaryId(summaryId);
		DecimalFormat df = new DecimalFormat("#.00");
		for (BizOrderTaskpackagePaymentDetailSplitVo bizOrderTaskpackagePaymentDetailSplitVo : splits) {
			
			Integer detailId = bizOrderTaskpackagePaymentDetailSplitVo.getOrderTaskpackagePaymentDetailId();//明细id
			//根据明细id查询付款单
			BizOrderTaskpackagePayment payment = bizOrderTaskpackagePaymentDao.queryPaymentByPaymentDetailId(detailId);
			bizOrderTaskpackagePaymentDetailSplitVo.setPaymentCode(payment.getOrderTaskpackagePaymentCode());
			//List<BizOrderTaskpackagePaymentDetailSplit> list1 = bizOrderTaskpackagePaymentDetailSplitDao.queryPaymentDetailSplitByRelateIdCard(bizOrderTaskpackagePaymentDetailSplitVo.getRelatedIdcardNo(),summaryId);
			// 计算本月剩余打款金额
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("employeeBankcardRelatedIdcardId", bizOrderTaskpackagePaymentDetailSplitVo.getEmployeeBankcardRelatedIdcardId());
			map1.put("createDate", format.format(date));
			Double sumPayAmountSplit = dao.queryPaymentAmountSplit(map1);
			Double restMoney = Double.parseDouble(df.format(ConstantUtils.PAYMENT_DETAIL_SPLIT_3500 - sumPayAmountSplit));
			bizOrderTaskpackagePaymentDetailSplitVo.setRestMoney(restMoney);
		}
		
		//根据批次id查询拆分信息
		/*List<BizOrderTaskpackagePaymentDetailSplit> splits = bizOrderTaskpackagePaymentDetailSplitDao.findPaymentDetailSplitBySummaryId(summaryId);
		Map<String,Double> map = new HashMap<String,Double>();
		for (BizOrderTaskpackagePaymentDetailSplit bizOrderTaskpackagePaymentDetailSplit : splits) {
			BizOrderTaskpackagePaymentDetailSplitVo split = new BizOrderTaskpackagePaymentDetailSplitVo();
			Integer detailId = bizOrderTaskpackagePaymentDetailSplit.getOrderTaskpackagePaymentDetailId();//明细id
			//根据明细id查询付款单
			BizOrderTaskpackagePayment payment = bizOrderTaskpackagePaymentDao.queryPaymentByPaymentDetailId(detailId);
			List<BizOrderTaskpackagePaymentDetailSplit> list1 = bizOrderTaskpackagePaymentDetailSplitDao.queryPaymentDetailSplitByRelateIdCard(bizOrderTaskpackagePaymentDetailSplit.getRelatedIdcardNo(),summaryId);
			double money = 0;
			for (BizOrderTaskpackagePaymentDetailSplit detailSplit : list1) {
				money = detailSplit.getPayAmountSplit() + money;
			}
			split.setRestMoney(ConstantUtils.EVERYMONTH_MONEY - money); 
			split.setId(bizOrderTaskpackagePaymentDetailSplit.getId());
			split.setEmployeeBankcardId(bizOrderTaskpackagePaymentDetailSplit.getEmployeeBankcardId());
			split.setEmployeeBankcardRelatedIdcardId(bizOrderTaskpackagePaymentDetailSplit.getEmployeeBankcardRelatedIdcardId());
			split.setOrderTaskpackagePaymentDetailId(bizOrderTaskpackagePaymentDetailSplit.getOrderTaskpackagePaymentDetailId());
			split.setPayAmountSplit(bizOrderTaskpackagePaymentDetailSplit.getPayAmountSplit());
			split.setPayAmountTotal(bizOrderTaskpackagePaymentDetailSplit.getPayAmountTotal());
			split.setRelatedIdcardNo(bizOrderTaskpackagePaymentDetailSplit.getRelatedIdcardNo());
			split.setRelatedName(bizOrderTaskpackagePaymentDetailSplit.getRelatedName());
			split.setRemarks(bizOrderTaskpackagePaymentDetailSplit.getRemarks());
			split.setSalaryEmployeeBankcard(bizOrderTaskpackagePaymentDetailSplit.getSalaryEmployeeBankcard());
			split.setSalaryEmployeeId(bizOrderTaskpackagePaymentDetailSplit.getSalaryEmployeeId());
			split.setSalaryEmployeeIdcardNo(bizOrderTaskpackagePaymentDetailSplit.getSalaryEmployeeIdcardNo());
			split.setSalaryEmployeeName(bizOrderTaskpackagePaymentDetailSplit.getSalaryEmployeeName());
			split.setPaymentCode(payment.getOrderTaskpackagePaymentCode());
			list.add(split);
		}*/
		return splits;
	}
	
	@Transactional(readOnly = false)
	public void updateDetailSplitById(int id, double money) {
		dao.updateDetailSplitById(id,money);
	}

	@Transactional(readOnly = false)
	public Map<String, Object> insertPaymentDetailSplit(Integer summaryId) {
		Date date = new Date();
		User user = UserUtils.getUser();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		DecimalFormat df = new DecimalFormat("#.00");
		List<BizOrderTaskpackagePaymentDetailSplit> splitList = new ArrayList<BizOrderTaskpackagePaymentDetailSplit>();
		// 异步请求后返回Map
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 关联身份证本月已使用客度集合
		Map<Integer, Double> sumPayAmountSplitMap = new HashMap<Integer, Double>();
		Map<Integer, Double> bankcardMap = new HashMap<Integer, Double>();
		// 没有关联身份证员工的集合
		Set<String> relateList = new HashSet<String>();
		//Double totalAmount = 0d;

		try{
			// 按批次查询付款明细
			List<BizOrderTaskpackagePaymentDetail> paymentDetail1List = bizOrderTaskpackagePaymentDetailDao.queryPaymentDetailBySummaryId(summaryId);
			for(BizOrderTaskpackagePaymentDetail paymentDetail:paymentDetail1List){
				//totalAmount = Double.parseDouble(df.format(totalAmount + paymentDetail.getAmount()));
				BizEmployeeBankcard2 emp2 = null;
				// 按员工id查询银行卡信息
				List<BizEmployeeBankcard2> emp2List = bizEmployeeBankcardDao2.queryEmployeeBankcardByEmpId(paymentDetail.getEmployeeId());
				if(CollectionUtils.isNotEmpty(emp2List)){
					emp2 = emp2List.get(0);
				}

				// 按员工id查询关联身份证信息
				List<BizEmployeeBankcardRelatedIdcard> relateIdcardList = bizEmployeeBankcardRelatedIdcardDao.queryEmployeeBankcardRelatedIdcardByEmpId(paymentDetail.getEmployeeId());
				//  声明总金额余额变量(即一条付款单明细的金额)
				Double balanceTotalAmount = paymentDetail.getAmount();
				// 不为空，表示有关联身份证
				if(CollectionUtils.isNotEmpty(relateIdcardList)){
					// 如果条数大于1，表示除了本人身份证外，还有其它关联身份证
					if(relateIdcardList.size() > 1){
						for(int i=0;i<relateIdcardList.size();i++){
							if(balanceTotalAmount <= 0){
								break;
							}
							BizEmployeeBankcardRelatedIdcard relateIdcard = relateIdcardList.get(i);

							BizOrderTaskpackagePaymentDetailSplit split = new BizOrderTaskpackagePaymentDetailSplit();
							split.setOrderTaskpackagePaymentDetailId(paymentDetail.getId());
							split.setSalaryEmployeeId(paymentDetail.getEmployeeId());
							if(emp2 != null){
								split.setEmployeeBankcardId(emp2.getId());
								split.setSalaryEmployeeName(emp2.getEmpRealName());
								split.setSalaryEmployeeIdcardNo(emp2.getIdCardNo());
								split.setSalaryEmployeeBankcard(emp2.getBankCardNo());
							}
							split.setEmployeeBankcardRelatedIdcardId(relateIdcard.getId());
							split.setRelatedName(relateIdcard.getRelatedName());
							split.setRelatedIdcardNo(relateIdcard.getRelatedIdcardNo());
							split.setCreateBy(user);
							split.setCreateDate(date);
							split.setUpdateBy(user);
							split.setUpdateDate(date);
							split.setPayAmountTotal(paymentDetail.getAmount());

							Map<String, Object> map = new HashMap<String, Object>();
							map.put("employeeBankcardRelatedIdcardId", relateIdcard.getId());
							map.put("createDate", format.format(date));
							//Double sumPayAmountSplit = dao.queryPaymentAmountSplit(map);
							// 当前身份证本月已使用额度
							Double sumPayAmountSplit = Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + dao.queryPaymentAmountSplit(map)));
							// 本人身份证时业务处理
							if(i == 0){
								// 当前身份证本月已使用额度大于等于3500时，此次可打款金额为0
								if(sumPayAmountSplit >= ConstantUtils.PAYMENT_DETAIL_SPLIT_3500){
									split.setPayAmountSplit(0);
									// 更新sumPayAmountSplitMap里的此身份证的额度
									sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + 0)));
									splitList.add(split);
									continue;
								}else{ // 当前身份证本月已使用额度小于3500时，判断3500-当前身份证本月已使用额度的差值
									Double balanceAmount = Double.parseDouble(df.format(ConstantUtils.PAYMENT_DETAIL_SPLIT_3500 - sumPayAmountSplit));
									// 如果差值大于等于100的业务
									if(balanceAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){
										// 如果付款单明细的金额大于等于100,此次打款金额为100
										if(balanceTotalAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){
											split.setPayAmountSplit(ConstantUtils.PAYMENT_DETAIL_SPLIT_100);
											// 更新sumPayAmountSplitMap里的此身份证的额度
											sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + ConstantUtils.PAYMENT_DETAIL_SPLIT_100)));
											// 更新付款单明细的金额
											balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - ConstantUtils.PAYMENT_DETAIL_SPLIT_100));
											splitList.add(split);
											continue;
										}else{ // 如果付款单明细的金额小于100,此次打款金额为付款单明细的金额
											split.setPayAmountSplit(balanceTotalAmount);
											// 更新sumPayAmountSplitMap里的此身份证的额度
											sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceTotalAmount)));
											// 更新付款单明细的金额
											balanceTotalAmount = 0d;
											splitList.add(split);
											continue;
										}
									}else{
									 	// 如果差值小于100的业务
										// 如果付款单明细的金额大于等于100，此次打款金额为付款单明细的金额
										if(balanceTotalAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){
											split.setPayAmountSplit(balanceAmount);
											// 更新sumPayAmountSplitMap里的此身份证的额度
											sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceAmount)));
											// 更新付款单明细的金额
											balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - balanceAmount));
											splitList.add(split);
											continue;
										}else{
											// 如果付款单明细的金额小于100的业务
											// 如果差值大于等于付款单明细的金额，此次打款金额为付款单明细的金额
											if(balanceAmount >= balanceTotalAmount){
												split.setPayAmountSplit(balanceTotalAmount);
												sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceTotalAmount)));
												balanceTotalAmount = 0d;
												splitList.add(split);
												continue;
											}else{ // 如果差值小于付款单明细的金额，此次打款金额为差值
												split.setPayAmountSplit(balanceAmount);
												// 更新sumPayAmountSplitMap里的此身份证的额度
												sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceAmount)));
												// 更新付款单明细的金额
												balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - balanceAmount));
												splitList.add(split);
												continue;
											}
										}
									}
								}
							}else if(i == relateIdcardList.size() -1){ // 最后一条数据，计算相差身份证
								// 如果当前身份证本月已使用额度大于等于3500时
								if(sumPayAmountSplit >= ConstantUtils.PAYMENT_DETAIL_SPLIT_3500){
									// 如果付款单明细的金额大于0
									if(balanceTotalAmount > 0){
										// 判断还差几张关联身份证
										if(bankcardMap.get(paymentDetail.getEmployeeId()) == null){
											bankcardMap.put(paymentDetail.getEmployeeId(), balanceTotalAmount);
										}else{
											bankcardMap.put(paymentDetail.getEmployeeId(), Double.parseDouble(df.format(bankcardMap.get(paymentDetail.getEmployeeId()) + balanceTotalAmount)));
										}
									}
									continue;
								}else{ // 如果当前身份证本月已使用额度小于3500时,计算差额
									Double balanceAmount = Double.parseDouble(df.format(ConstantUtils.PAYMENT_DETAIL_SPLIT_3500 - sumPayAmountSplit));
									// 如果付款单明细的金额大于等于差额，处理业务
									if(balanceTotalAmount >= balanceAmount){
										split.setPayAmountSplit(balanceAmount);
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceAmount)));
										balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - balanceAmount));
										splitList.add(split);
										// 如果付款单明细的金额大于0
										if(balanceTotalAmount > 0){
											// 判断还差几张关联身份证
											if(bankcardMap.get(paymentDetail.getEmployeeId()) == null){
												bankcardMap.put(paymentDetail.getEmployeeId(), balanceTotalAmount);
											}else{
												bankcardMap.put(paymentDetail.getEmployeeId(), Double.parseDouble(df.format(bankcardMap.get(paymentDetail.getEmployeeId()) + balanceTotalAmount)));
											}
										}
										continue;
									}else{// 如果付款单明细的金额小于差额，处理业务
										split.setPayAmountSplit(balanceTotalAmount);
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceTotalAmount)));
										balanceTotalAmount = 0d;
										splitList.add(split);
										break;
									}
								}
							}else{
								// 当前身份证本月已使用额度大于等于3500
								if(sumPayAmountSplit >= ConstantUtils.PAYMENT_DETAIL_SPLIT_3500){
									continue;
								}else{ // 否则，计算差值
									Double balanceAmount = Double.parseDouble(df.format(ConstantUtils.PAYMENT_DETAIL_SPLIT_3500 - sumPayAmountSplit));
									// 如果付款单明细的金额大于等于差额
									if(balanceTotalAmount >= balanceAmount){
										// 当前可用额度就是差额的值
										split.setPayAmountSplit(balanceAmount);
										// 更新sumPayAmountSplitMap里的此身份证的额度
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceAmount)));
										balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - balanceAmount));
										splitList.add(split);
										continue;
									}else{ // 如果款单明细的金额小于差额
										split.setPayAmountSplit(balanceTotalAmount);
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceTotalAmount)));
										balanceTotalAmount = 0d;
										splitList.add(split);
										break;
									}
								}
							}
						}
					}else{
						// 查询条数为1，表示只有本人身份证，没有其它关联身份证
						for(int i=0;i<relateIdcardList.size();i++){
							if(balanceTotalAmount <= 0){
								break;
							}
							BizEmployeeBankcardRelatedIdcard relateIdcard = relateIdcardList.get(i);
							BizOrderTaskpackagePaymentDetailSplit split = new BizOrderTaskpackagePaymentDetailSplit();
							split.setOrderTaskpackagePaymentDetailId(paymentDetail.getId());
							split.setSalaryEmployeeId(paymentDetail.getEmployeeId());
							if(emp2 != null){
								split.setEmployeeBankcardId(emp2.getId());
								split.setSalaryEmployeeName(emp2.getEmpRealName());
								split.setSalaryEmployeeIdcardNo(emp2.getIdCardNo());
								split.setSalaryEmployeeBankcard(emp2.getBankCardNo());
							}
							split.setEmployeeBankcardRelatedIdcardId(relateIdcard.getId());
							split.setRelatedName(relateIdcard.getRelatedName());
							split.setRelatedIdcardNo(relateIdcard.getRelatedIdcardNo());
							split.setCreateBy(user);
							split.setCreateDate(date);
							split.setUpdateBy(user);
							split.setUpdateDate(date);
							split.setPayAmountTotal(paymentDetail.getAmount());

							Map<String, Object> map = new HashMap<String, Object>();
							map.put("employeeBankcardRelatedIdcardId", relateIdcard.getId());
							map.put("createDate", format.format(date));
							//Double sumPayAmountSplit = dao.queryPaymentAmountSplit(map);
							Double sumPayAmountSplit = Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + dao.queryPaymentAmountSplit(map)));

							// 当前此身份证本月已使用额度大于等于3500时
							if(sumPayAmountSplit >= ConstantUtils.PAYMENT_DETAIL_SPLIT_3500){
								split.setPayAmountSplit(0);
								sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + 0)));
								splitList.add(split);

								// 判断付款明细的金额大于0时,表示差身份证
								if(balanceTotalAmount > 0){
									// 判断还差几张关联身份证
									if(bankcardMap.get(paymentDetail.getEmployeeId()) == null){
										bankcardMap.put(paymentDetail.getEmployeeId(), balanceTotalAmount);
									}else{
										bankcardMap.put(paymentDetail.getEmployeeId(), Double.parseDouble(df.format(bankcardMap.get(paymentDetail.getEmployeeId()) + balanceTotalAmount)));
									}
								}
								continue;
							}else{ // 当前此身份证本月已使用额度小于3500时
								Double balanceAmount = Double.parseDouble(df.format(ConstantUtils.PAYMENT_DETAIL_SPLIT_3500 - sumPayAmountSplit));
								// 当差额大于等于100时
								if(balanceAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){
									// 判断付款明细的金额大于等于100时
									if(balanceTotalAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){
										// 更新此身份证可付款额度为100
										split.setPayAmountSplit(ConstantUtils.PAYMENT_DETAIL_SPLIT_100);
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + ConstantUtils.PAYMENT_DETAIL_SPLIT_100)));
										balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - ConstantUtils.PAYMENT_DETAIL_SPLIT_100));
										splitList.add(split);
										// 付款明细的金额大于0时，表示身份证不够使用
										if(balanceTotalAmount > 0){
											// 判断还差几张关联身份证
											if(bankcardMap.get(paymentDetail.getEmployeeId()) == null){
												bankcardMap.put(paymentDetail.getEmployeeId(), balanceTotalAmount);
											}else{
												bankcardMap.put(paymentDetail.getEmployeeId(), Double.parseDouble(df.format(bankcardMap.get(paymentDetail.getEmployeeId()) + balanceTotalAmount)));
											}
										}
										continue;
									}else{ // 判断付款明细的金额小于100时
										split.setPayAmountSplit(balanceTotalAmount);
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceTotalAmount)));
										balanceTotalAmount = 0d;
										splitList.add(split);
										continue;
									}
								}else{ // 当差额小于100时
									// 判断付款明细的金额大于等于100时
									if(balanceTotalAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){
										// 更新此身份证可付款额度为差额
										split.setPayAmountSplit(balanceAmount);
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceAmount)));
										// 更新付款明细的金额
										balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - balanceAmount));
										splitList.add(split);

										// 判断付款明细的金额大于等于0时
										if(balanceTotalAmount > 0){
											// 判断还差几张关联身份证
											if(bankcardMap.get(paymentDetail.getEmployeeId()) == null){
												bankcardMap.put(paymentDetail.getEmployeeId(), balanceTotalAmount);
											}else{
												bankcardMap.put(paymentDetail.getEmployeeId(), Double.parseDouble(df.format(bankcardMap.get(paymentDetail.getEmployeeId()) + balanceTotalAmount)));
											}
										}
										continue;
									}else{ // 判断付款明细的金额小于100时
										// 判断差额大于等于付款明细的金额时
										if(balanceAmount >= balanceTotalAmount){
											split.setPayAmountSplit(balanceTotalAmount);
											sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceTotalAmount)));
											balanceTotalAmount = 0d;
											splitList.add(split);
											continue;
										}else{
											split.setPayAmountSplit(balanceAmount);
											sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceAmount)));
											balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - balanceAmount));
											splitList.add(split);

											if(balanceTotalAmount > 0){
												// 判断还差几张关联身份证
												if(bankcardMap.get(paymentDetail.getEmployeeId()) == null){
													bankcardMap.put(paymentDetail.getEmployeeId(), balanceTotalAmount);
												}else{
													bankcardMap.put(paymentDetail.getEmployeeId(), Double.parseDouble(df.format(bankcardMap.get(paymentDetail.getEmployeeId()) + balanceTotalAmount)));
												}
											}
											continue;
										}
									}
								}
							}
						}
					}
				}else{
					// 没有关联身份证，按员工id查询员工真实姓名
					BizEmployee2 employee2  = bizEmployeeDao2.get(paymentDetail.getEmployeeId());
					relateList.add(employee2.getRealname());
				}
			}

			if(CollectionUtils.isNotEmpty(relateList)){
				resultMap.put("status", 3);
				resultMap.put("data", relateList);
				return resultMap;
			}

			// 关联银行卡个数不足
			if(bankcardMap != null && bankcardMap.size() > 0){
				resultMap.put("status", 2);
				List<BizPaymentDetailSplitBankcard> bankcardList = new ArrayList<BizPaymentDetailSplitBankcard>();
				for(Integer key:bankcardMap.keySet()){
					BizEmployee2 employee2  = bizEmployeeDao2.get(key);
					BizPaymentDetailSplitBankcard bankcard = new BizPaymentDetailSplitBankcard();
					if(employee2 != null){
						bankcard.setRealName(employee2.getRealname());
					}

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("employeeId", key);
					map.put("summaryId", summaryId);
					Double splitAmountTotal = bizOrderTaskpackagePaymentDetailDao.querySumAmountBySummaryIdAndEmployeeId(map);
					bankcard.setSplitAmountTotal(splitAmountTotal);
					bankcard.setRelateRemainAmount(Double.parseDouble(df.format(splitAmountTotal - bankcardMap.get(key))));
					bankcard.setRelateDiffAmount(bankcardMap.get(key));
					Double relateCount = Math.ceil(bankcardMap.get(key) / ConstantUtils.PAYMENT_DETAIL_SPLIT_3500);
					bankcard.setRelateCount(relateCount.intValue());
					bankcardList.add(bankcard);
				}
				resultMap.put("data", bankcardList);
			}else{
				resultMap.put("status", 1);
				dao.insertBatch(splitList);
			}
		}catch (Exception e){
			resultMap.put("status", 0);
		}

		return resultMap;
	}
}