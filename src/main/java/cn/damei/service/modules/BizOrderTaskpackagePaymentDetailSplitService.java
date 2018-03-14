
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


	public List<BizOrderTaskpackagePaymentDetailSplitVo> findPaymentDetailSplitBySummaryId(Integer summaryId) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();

		Map<String,Double> map = new HashMap<String,Double>();

		List<BizOrderTaskpackagePaymentDetailSplitVo> splits = bizOrderTaskpackagePaymentDetailSplitDao.findPaymentDetailSplitBySummaryId(summaryId);
		DecimalFormat df = new DecimalFormat("#.00");
		for (BizOrderTaskpackagePaymentDetailSplitVo bizOrderTaskpackagePaymentDetailSplitVo : splits) {
			
			Integer detailId = bizOrderTaskpackagePaymentDetailSplitVo.getOrderTaskpackagePaymentDetailId();

			BizOrderTaskpackagePayment payment = bizOrderTaskpackagePaymentDao.queryPaymentByPaymentDetailId(detailId);
			bizOrderTaskpackagePaymentDetailSplitVo.setPaymentCode(payment.getOrderTaskpackagePaymentCode());


			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("employeeBankcardRelatedIdcardId", bizOrderTaskpackagePaymentDetailSplitVo.getEmployeeBankcardRelatedIdcardId());
			map1.put("createDate", format.format(date));
			Double sumPayAmountSplit = dao.queryPaymentAmountSplit(map1);
			Double restMoney = Double.parseDouble(df.format(ConstantUtils.PAYMENT_DETAIL_SPLIT_3500 - sumPayAmountSplit));
			bizOrderTaskpackagePaymentDetailSplitVo.setRestMoney(restMoney);
		}
		


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

		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<Integer, Double> sumPayAmountSplitMap = new HashMap<Integer, Double>();
		Map<Integer, Double> bankcardMap = new HashMap<Integer, Double>();

		Set<String> relateList = new HashSet<String>();


		try{

			List<BizOrderTaskpackagePaymentDetail> paymentDetail1List = bizOrderTaskpackagePaymentDetailDao.queryPaymentDetailBySummaryId(summaryId);
			for(BizOrderTaskpackagePaymentDetail paymentDetail:paymentDetail1List){

				BizEmployeeBankcard2 emp2 = null;

				List<BizEmployeeBankcard2> emp2List = bizEmployeeBankcardDao2.queryEmployeeBankcardByEmpId(paymentDetail.getEmployeeId());
				if(CollectionUtils.isNotEmpty(emp2List)){
					emp2 = emp2List.get(0);
				}


				List<BizEmployeeBankcardRelatedIdcard> relateIdcardList = bizEmployeeBankcardRelatedIdcardDao.queryEmployeeBankcardRelatedIdcardByEmpId(paymentDetail.getEmployeeId());

				Double balanceTotalAmount = paymentDetail.getAmount();

				if(CollectionUtils.isNotEmpty(relateIdcardList)){

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


							Double sumPayAmountSplit = Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + dao.queryPaymentAmountSplit(map)));

							if(i == 0){

								if(sumPayAmountSplit >= ConstantUtils.PAYMENT_DETAIL_SPLIT_3500){
									split.setPayAmountSplit(0);

									sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + 0)));
									splitList.add(split);
									continue;
								}else{
									Double balanceAmount = Double.parseDouble(df.format(ConstantUtils.PAYMENT_DETAIL_SPLIT_3500 - sumPayAmountSplit));

									if(balanceAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){

										if(balanceTotalAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){
											split.setPayAmountSplit(ConstantUtils.PAYMENT_DETAIL_SPLIT_100);

											sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + ConstantUtils.PAYMENT_DETAIL_SPLIT_100)));

											balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - ConstantUtils.PAYMENT_DETAIL_SPLIT_100));
											splitList.add(split);
											continue;
										}else{
											split.setPayAmountSplit(balanceTotalAmount);

											sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceTotalAmount)));

											balanceTotalAmount = 0d;
											splitList.add(split);
											continue;
										}
									}else{


										if(balanceTotalAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){
											split.setPayAmountSplit(balanceAmount);

											sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceAmount)));

											balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - balanceAmount));
											splitList.add(split);
											continue;
										}else{


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
												continue;
											}
										}
									}
								}
							}else if(i == relateIdcardList.size() -1){

								if(sumPayAmountSplit >= ConstantUtils.PAYMENT_DETAIL_SPLIT_3500){

									if(balanceTotalAmount > 0){

										if(bankcardMap.get(paymentDetail.getEmployeeId()) == null){
											bankcardMap.put(paymentDetail.getEmployeeId(), balanceTotalAmount);
										}else{
											bankcardMap.put(paymentDetail.getEmployeeId(), Double.parseDouble(df.format(bankcardMap.get(paymentDetail.getEmployeeId()) + balanceTotalAmount)));
										}
									}
									continue;
								}else{
									Double balanceAmount = Double.parseDouble(df.format(ConstantUtils.PAYMENT_DETAIL_SPLIT_3500 - sumPayAmountSplit));

									if(balanceTotalAmount >= balanceAmount){
										split.setPayAmountSplit(balanceAmount);
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceAmount)));
										balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - balanceAmount));
										splitList.add(split);

										if(balanceTotalAmount > 0){

											if(bankcardMap.get(paymentDetail.getEmployeeId()) == null){
												bankcardMap.put(paymentDetail.getEmployeeId(), balanceTotalAmount);
											}else{
												bankcardMap.put(paymentDetail.getEmployeeId(), Double.parseDouble(df.format(bankcardMap.get(paymentDetail.getEmployeeId()) + balanceTotalAmount)));
											}
										}
										continue;
									}else{
										split.setPayAmountSplit(balanceTotalAmount);
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceTotalAmount)));
										balanceTotalAmount = 0d;
										splitList.add(split);
										break;
									}
								}
							}else{

								if(sumPayAmountSplit >= ConstantUtils.PAYMENT_DETAIL_SPLIT_3500){
									continue;
								}else{
									Double balanceAmount = Double.parseDouble(df.format(ConstantUtils.PAYMENT_DETAIL_SPLIT_3500 - sumPayAmountSplit));

									if(balanceTotalAmount >= balanceAmount){

										split.setPayAmountSplit(balanceAmount);

										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceAmount)));
										balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - balanceAmount));
										splitList.add(split);
										continue;
									}else{
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

							Double sumPayAmountSplit = Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + dao.queryPaymentAmountSplit(map)));


							if(sumPayAmountSplit >= ConstantUtils.PAYMENT_DETAIL_SPLIT_3500){
								split.setPayAmountSplit(0);
								sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + 0)));
								splitList.add(split);


								if(balanceTotalAmount > 0){

									if(bankcardMap.get(paymentDetail.getEmployeeId()) == null){
										bankcardMap.put(paymentDetail.getEmployeeId(), balanceTotalAmount);
									}else{
										bankcardMap.put(paymentDetail.getEmployeeId(), Double.parseDouble(df.format(bankcardMap.get(paymentDetail.getEmployeeId()) + balanceTotalAmount)));
									}
								}
								continue;
							}else{
								Double balanceAmount = Double.parseDouble(df.format(ConstantUtils.PAYMENT_DETAIL_SPLIT_3500 - sumPayAmountSplit));

								if(balanceAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){

									if(balanceTotalAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){

										split.setPayAmountSplit(ConstantUtils.PAYMENT_DETAIL_SPLIT_100);
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + ConstantUtils.PAYMENT_DETAIL_SPLIT_100)));
										balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - ConstantUtils.PAYMENT_DETAIL_SPLIT_100));
										splitList.add(split);

										if(balanceTotalAmount > 0){

											if(bankcardMap.get(paymentDetail.getEmployeeId()) == null){
												bankcardMap.put(paymentDetail.getEmployeeId(), balanceTotalAmount);
											}else{
												bankcardMap.put(paymentDetail.getEmployeeId(), Double.parseDouble(df.format(bankcardMap.get(paymentDetail.getEmployeeId()) + balanceTotalAmount)));
											}
										}
										continue;
									}else{
										split.setPayAmountSplit(balanceTotalAmount);
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceTotalAmount)));
										balanceTotalAmount = 0d;
										splitList.add(split);
										continue;
									}
								}else{

									if(balanceTotalAmount >= ConstantUtils.PAYMENT_DETAIL_SPLIT_100){

										split.setPayAmountSplit(balanceAmount);
										sumPayAmountSplitMap.put(relateIdcard.getId(), Double.parseDouble(df.format((sumPayAmountSplitMap.get(relateIdcard.getId()) == null ? 0 : sumPayAmountSplitMap.get(relateIdcard.getId())) + balanceAmount)));

										balanceTotalAmount = Double.parseDouble(df.format(balanceTotalAmount - balanceAmount));
										splitList.add(split);


										if(balanceTotalAmount > 0){

											if(bankcardMap.get(paymentDetail.getEmployeeId()) == null){
												bankcardMap.put(paymentDetail.getEmployeeId(), balanceTotalAmount);
											}else{
												bankcardMap.put(paymentDetail.getEmployeeId(), Double.parseDouble(df.format(bankcardMap.get(paymentDetail.getEmployeeId()) + balanceTotalAmount)));
											}
										}
										continue;
									}else{

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

					BizEmployee2 employee2  = bizEmployeeDao2.get(paymentDetail.getEmployeeId());
					relateList.add(employee2.getRealname());
				}
			}

			if(CollectionUtils.isNotEmpty(relateList)){
				resultMap.put("status", 3);
				resultMap.put("data", relateList);
				return resultMap;
			}


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