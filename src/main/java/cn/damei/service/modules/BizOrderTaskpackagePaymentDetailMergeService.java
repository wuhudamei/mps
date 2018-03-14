
package cn.damei.service.modules;

import java.util.*;

import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizMsg;
import cn.damei.dao.modules.BizQcBillDao;
import cn.damei.dao.modules.BizSynDataDao;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.dao.modules.OrderDao;
import cn.damei.entity.modules.BizPhoneMsg;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.mobile.Inspector.MaterialsInfoDao;
import cn.damei.dao.mobile.Manager.TaskPackageDao;
import cn.damei.entity.mobile.Manager.TaskPackage;
import cn.damei.dao.mobile.Manager.OrderTaskpackageSettlementDao;
import cn.damei.entity.mobile.Manager.OrderTaskpackageSettlement;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMerge;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeTxtVo;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeVo;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

import cn.damei.dao.modules.BizOrderTaskpackagePaymentDao;
import cn.damei.entity.modules.BizOrderTaskpackagePayment;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentVo;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetaiVo;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetail;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentSummaryDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentSummary;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailMergeDao;


@Service
@Transactional(readOnly = true)
public class BizOrderTaskpackagePaymentDetailMergeService
		extends CrudService2<BizOrderTaskpackagePaymentDetailMergeDao, BizOrderTaskpackagePaymentDetailMerge> {

	@Autowired
	private BizOrderTaskpackagePaymentDetailDao bizOrderTaskpackagePaymentDetailDao;

	@Autowired
	private BizOrderTaskpackagePaymentDao bizOrderTaskpackagePaymentDao;

	@Autowired
	private BizOrderTaskpackagePaymentSummaryDao bizOrderTaskpackagePaymentSummaryDao;

	@Autowired
	private TaskPackageDao orderTaskPackageDao;

	@Autowired
	private OrderTaskpackageSettlementDao orderTaskpackageSettlementDao;

	@Autowired
	private BizMessagegroupService bizMessagegroupService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BizQcBillDao bizQcBillDao;
	
	@Autowired
	private MaterialsInfoDao materialsInfoDao;
	@Autowired
	private BizSynDataDao bizSynDataDao;

	public BizOrderTaskpackagePaymentDetailMerge get(Integer id) {
		return super.get(id);
	}

	public List<BizOrderTaskpackagePaymentDetailMerge> findList(
			BizOrderTaskpackagePaymentDetailMerge bizOrderTaskpackagePaymentDetailMerge) {
		return super.findList(bizOrderTaskpackagePaymentDetailMerge);
	}

	public Page<BizOrderTaskpackagePaymentDetailMerge> findPage(Page<BizOrderTaskpackagePaymentDetailMerge> page,
			BizOrderTaskpackagePaymentDetailMerge bizOrderTaskpackagePaymentDetailMerge) {
		return super.findPage(page, bizOrderTaskpackagePaymentDetailMerge);
	}

	@Transactional(readOnly = false)
	public void save(BizOrderTaskpackagePaymentDetailMerge bizOrderTaskpackagePaymentDetailMerge) {
		super.save(bizOrderTaskpackagePaymentDetailMerge);
	}

	@Transactional(readOnly = false)
	public void delete(BizOrderTaskpackagePaymentDetailMerge bizOrderTaskpackagePaymentDetailMerge) {
		super.delete(bizOrderTaskpackagePaymentDetailMerge);
	}


	public List<BizOrderTaskpackagePaymentDetailMerge> queryTaskpackagePaymentDetailMergeBySummaryId(
			Integer summaryId) {
		return dao.queryTaskpackagePaymentDetailMergeBySummaryId(summaryId);
	}


	public List<BizOrderTaskpackagePaymentDetailMergeVo> queryPaymentDetailMergeBySummaryId(Integer summaryId) {
		return dao.queryPaymentDetailMergeBySummaryId(summaryId);
	}


	public String queryPaymentDetailMergeForTxt(Integer summaryId) {
		List<BizOrderTaskpackagePaymentDetailMergeTxtVo> list = dao.queryPaymentDetailMergeForTxtAndExcel(summaryId);
		StringBuffer strb = new StringBuffer();
		for (BizOrderTaskpackagePaymentDetailMergeTxtVo txtVo : list) {
			strb.append("|" + ConstantUtils.COMPANY_BANK_NO + "|" + ConstantUtils.COMPANY_NAME + "|" + txtVo.getAmount()
					+ "|" + txtVo.getBankCardNo() + "|" + txtVo.getRealName() + "|" + txtVo.getBankName() + "|"
					+ ConstantUtils.CURRENCY + "|" + ConstantUtils.SERVICE + "|" + txtVo.getBankNo() + "|\r\n");
		}
		return strb.toString();
	}
	

	public List<BizOrderTaskpackagePaymentDetailMergeTxtVo> exportChinaCiticBank(Integer summaryId){
		
		return dao.exportChinaCiticBank(summaryId);
	}
	


	public HSSFWorkbook queryPaymentDetailMergeForExcel(Integer summaryId) {
		List<BizOrderTaskpackagePaymentDetailMergeTxtVo> list = dao.queryPaymentDetailMergeForTxtAndExcel(summaryId);

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("中国银行");


		sheet.setColumnWidth(0, 8000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 2500);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 5000);


		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.createCell(0).setCellValue("账号");
		rowTitle.createCell(1).setCellValue("户名");
		rowTitle.createCell(2).setCellValue("金额");
		rowTitle.createCell(3).setCellValue("账号所属省份");
		rowTitle.createCell(4).setCellValue("收款行CNAPS号");
		rowTitle.createCell(5).setCellValue("证件类型");
		rowTitle.createCell(6).setCellValue("证件号码");
		rowTitle.createCell(7).setCellValue("用途");
		rowTitle.createCell(8).setCellValue("附言");


		for (int i = 0; i < list.size(); i++) {
			BizOrderTaskpackagePaymentDetailMergeTxtVo vo = list.get(i);
			HSSFRow row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(vo.getBankCardNo());
			row.createCell(1).setCellValue(vo.getRealName());
			row.createCell(2).setCellValue(vo.getAmount().toString());
			row.createCell(3).setCellValue(vo.getProvinceName());
			row.createCell(4).setCellValue(vo.getBankNo());
		}
		return wb;
	}

	@Transactional(readOnly = false)
	public void confirmPayment(Integer summaryId, Integer[] ids) {
		Date date = new Date();
		User user = UserUtils.getUser();


		for (Integer id : ids) {
			BizOrderTaskpackagePaymentDetailMerge merge = dao.get(id);
			merge.setStatus(ConstantUtils.PAYMENT_DETAIL_MERGE_STATUS_1);
			merge.setStatusDatetime(date);
			merge.setPayDatetime(date);
			merge.setUpdateBy(user);
			merge.setUpdateDate(date);
			dao.update(merge);


			List<BizOrderTaskpackagePaymentDetail> detailList = bizOrderTaskpackagePaymentDetailDao
					.queryPaymentDetailByMergeId(id);
			for (BizOrderTaskpackagePaymentDetail detail : detailList) {
				detail.setStatus(ConstantUtils.PAYMENT_DETAIL_STATUS_1);
				detail.setStatusDatetime(date);
				detail.setPayDatetime(date);
				detail.setUpdateBy(user);
				detail.setUpdateDate(date);
				bizOrderTaskpackagePaymentDetailDao.update(detail);
			}
		}


		List<BizOrderTaskpackagePaymentDetaiVo> paymentDetailList = bizOrderTaskpackagePaymentDetailDao
				.queryPaymentDetailCountBySummaryId(summaryId);
		for (BizOrderTaskpackagePaymentDetaiVo paymentDetail : paymentDetailList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paymentId", paymentDetail.getOrderTaskpackagePaymentId());
			map.put("status", ConstantUtils.PAYMENT_DETAIL_STATUS_1);
			Integer count = bizOrderTaskpackagePaymentDetailDao.queryPaymentDetailCountByPaymentIdAndStatus(map);
			BizOrderTaskpackagePayment payment = bizOrderTaskpackagePaymentDao
					.get(paymentDetail.getOrderTaskpackagePaymentId());
			payment.setStatusDatetime(date);
			payment.setUpdateBy(user);
			payment.setUpdateDate(date);
			if (paymentDetail.getCount() == count) {
				payment.setStatus(ConstantUtils.PAYMENT_STATUS_100);
			} else {
				if (count > 0) {
					payment.setStatus(ConstantUtils.PAYMENT_STATUS_40);
				}
			}
			bizOrderTaskpackagePaymentDao.update(payment);
		}


		Map<String, Object> map = new HashMap<String, Object>();
		map.put("summaryId", summaryId);
		Integer allCount = dao.queryPaymentDetailMergeCountByMap(map);
		map.put("status", ConstantUtils.PAYMENT_DETAIL_MERGE_STATUS_1);
		Integer count    = dao.queryPaymentDetailMergeCountByMap(map);
		BizOrderTaskpackagePaymentSummary summary = bizOrderTaskpackagePaymentSummaryDao.get(summaryId);
		summary.setUpdateBy(user);
		summary.setUpdateDate(date);
		if (allCount.equals(count)) {
			summary.setStatus(ConstantUtils.SUMMARY_STATUS_100);
		} else {
			summary.setStatus(ConstantUtils.SUMMARY_STATUS_50);
		}
		bizOrderTaskpackagePaymentSummaryDao.update(summary);


		List<BizOrderTaskpackagePaymentVo> paymentList = bizOrderTaskpackagePaymentDao
				.queryPaymentSettlementBySummaryId(summaryId);
		for (BizOrderTaskpackagePaymentVo payment : paymentList) {
			if (payment.getAdvancePaymentRates() == 100) {
				Map<String, Object> paymentMap = new HashMap<String, Object>();
				paymentMap.put("orderTaskpackageSettlementId", payment.getOrderTaskpackageSettlementId());
				paymentMap.put("orderTaskpackagPaymentType", ConstantUtils.ORDER_TASKPACKAGE_PAYMENT_TYPE_0);
				String status = bizOrderTaskpackagePaymentDao.queryPaymentStatusByCondition(paymentMap);
				if (ConstantUtils.PAYMENT_STATUS_100.equals(status)) {
					TaskPackage taskPackage = orderTaskPackageDao.get(payment.getOrderTaskpackageId() + "");
					taskPackage.setPackageStateId(ConstantUtils.ORDER_TASK_STATUS_160_VALUE);
					taskPackage.setPackageStatename(ConstantUtils.ORDER_TASK_STATUS_160_VALUE_REMARK);
					taskPackage.setUpdateDate(date);
					taskPackage.setUpdateBy(user);
					orderTaskPackageDao.update(taskPackage);
					
					/*Map<String,Object> qcBillMap = new HashMap<String,Object>();
					qcBillMap.put("qcBillType", "1");
					qcBillMap.put("orderId", taskPackage.getOrderId());
					qcBillMap.put("nodeInex", 6);
					BizQcBill bizQcBill = bizQcBillDao.queryQcBillByParam(qcBillMap);
					if(bizQcBill != null && bizQcBill.getId() != null){//判断订单是否已通过基装验收
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("packId", taskPackage.getId());
						param.put("type", 9);
						TaskPackage taskPackage1 = orderTaskPackageDao.queryOrderTaskPackageByParam(param);
						if (taskPackage1 != null && taskPackage1.getId() != null) {// 判断是否属于涂饰任务包
							Order order = orderDao.get(String.valueOf(taskPackage1.getOrderId()));
							//获取辅料
							List<MaterialsInfo> flMaterialsList = materialsInfoDao.queryFlMaterialsInfo(order.getOrderId());
							if(flMaterialsList!= null && flMaterialsList.size()>0){
								JSONObject jsonObject = new JSONObject();
								JSONArray jsonArray = new JSONArray();
								jsonObject.put("orderNumber",order.getOrderNumber());
								jsonObject.put("isFloor",false+"");
								jsonObject.put("assistInfo", JSONArray.fromObject(flMaterialsList));
								String[] paramArr = new String[]{"orderNumber="+order.getOrderNumber(),"isFloor=false"};
								String key = KeyAuthenticateUtils2.getKey(paramArr,BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
								jsonObject.put("key",key);
								jsonArray.add(jsonObject);
								
								//向同步表插入数据 以便发送 ------------------------
								BizSynData bizSynData = new BizSynData();
								bizSynData.setBusinessData(jsonArray.toString());
						        bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
						        bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
						    	bizSynData.setBusinessOnlyMarkInt(summaryId);
						    	bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
						    	bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
						    	bizSynData.preInsert();
						    	
						    	bizSynDataDao.insert(bizSynData);
							}
						}else{
							param.put("type", 7);
							TaskPackage taskPackage2 = orderTaskPackageDao.queryOrderTaskPackageByParam(param);
							if (taskPackage2 != null && taskPackage2.getId() != null) {// 判断是否属于泥瓦任务包
								Order order = orderDao.get(String.valueOf(taskPackage2.getOrderId()));
								//获取沙子水泥
								List<MaterialsInfo> sandMaterials = materialsInfoDao.querySandMaterialsInfo(order.getOrderId());
								if(sandMaterials!= null && sandMaterials.size()>0){
									JSONObject jsonObject = new JSONObject();
									JSONArray jsonArray = new JSONArray();
									String[] paramArr = new String[]{"orderNumber="+order.getOrderNumber(),"isFloor=false"};
									String key = KeyAuthenticateUtils2.getKey(paramArr,BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
									jsonObject.put("key",key);
									jsonObject.put("orderNumber",order.getOrderNumber());
									jsonObject.put("isFloor",false+"");
									jsonObject.put("assistInfo", JSONArray.fromObject(sandMaterials));
									jsonArray.add(jsonObject);
									
									//向同步表插入数据 以便发送 ------------------------
									BizSynData bizSynData = new BizSynData();
									bizSynData.setBusinessData(jsonArray.toString());
							        bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
							        bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
							    	bizSynData.setBusinessOnlyMarkInt(summaryId);
							    	bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
							    	bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
							    	bizSynData.preInsert();
							    	
							    	bizSynDataDao.insert(bizSynData);
								}
							}
						}
					}*/
					
					
					// 6.更新结算单
					OrderTaskpackageSettlement settlement = orderTaskpackageSettlementDao
							.get(payment.getOrderTaskpackageSettlementId());
					settlement.setStatus(ConstantUtils.SETTLEMENT_STATUS_100);
					settlement.setStatusDate(date);
					settlement.setUpdateBy(user);
					settlement.setUpdateDate(date);
					orderTaskpackageSettlementDao.update(settlement);
				}
			} else {
				Map<String, Object> paymentMap = new HashMap<String, Object>();
				paymentMap.put("orderTaskpackageSettlementId", payment.getOrderTaskpackageSettlementId());
				paymentMap.put("orderTaskpackagPaymentType", ConstantUtils.ORDER_TASKPACKAGE_PAYMENT_TYPE_0);
				String status = bizOrderTaskpackagePaymentDao.queryPaymentStatusByCondition(paymentMap);
				if (ConstantUtils.PAYMENT_STATUS_100.equals(status)) {
					TaskPackage taskPackage = orderTaskPackageDao.get(payment.getOrderTaskpackageId() + "");
					taskPackage.setPackageStateId(ConstantUtils.ORDER_TASK_STATUS_150_VALUE);
					taskPackage.setPackageStatename(ConstantUtils.ORDER_TASK_STATUS_150_VALUE_REMARK);
					taskPackage.setUpdateBy(user);
					taskPackage.setUpdateDate(date);
					orderTaskPackageDao.update(taskPackage);

					// 6.更新结算单
					OrderTaskpackageSettlement settlement = orderTaskpackageSettlementDao
							.get(payment.getOrderTaskpackageSettlementId());
					settlement.setStatus(ConstantUtils.SETTLEMENT_STATUS_90);
					settlement.setStatusDate(date);
					settlement.setUpdateBy(user);
					settlement.setUpdateDate(date);
					orderTaskpackageSettlementDao.update(settlement);
				}

				Map<String, Object> paymentMap1 = new HashMap<String, Object>();
				paymentMap1.put("orderTaskpackageSettlementId", payment.getOrderTaskpackageSettlementId());
				paymentMap1.put("orderTaskpackagPaymentType", ConstantUtils.ORDER_TASKPACKAGE_PAYMENT_TYPE_1);
				String status1 = bizOrderTaskpackagePaymentDao.queryPaymentStatusByCondition(paymentMap1);
				if (ConstantUtils.PAYMENT_STATUS_100.equals(status1)) {
					TaskPackage taskPackage = orderTaskPackageDao.get(payment.getOrderTaskpackageId() + "");
					taskPackage.setPackageStateId(ConstantUtils.ORDER_TASK_STATUS_160_VALUE);
					taskPackage.setPackageStatename(ConstantUtils.ORDER_TASK_STATUS_160_VALUE_REMARK);
					taskPackage.setUpdateDate(date);
					taskPackage.setUpdateBy(user);
					orderTaskPackageDao.update(taskPackage);
					
					/*Map<String,Object> qcBillMap = new HashMap<String,Object>();
					qcBillMap.put("qcBillType", "1");
					qcBillMap.put("orderId", taskPackage.getOrderId());
					qcBillMap.put("nodeInex", 6);
					BizQcBill bizQcBill = bizQcBillDao.queryQcBillByParam(qcBillMap);
					if(bizQcBill != null && bizQcBill.getId() != null){//判断订单是否已通过基装验收
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("packId", taskPackage.getId());
						param.put("type", 9);
						TaskPackage taskPackage1 = orderTaskPackageDao.queryOrderTaskPackageByParam(param);
						Order order = orderDao.get(String.valueOf(taskPackage1.getOrderId()));
						if (taskPackage1 != null && taskPackage1.getId() != null) {// 判断是否属于涂饰任务包
							//获取辅料
							List<MaterialsInfo> flMaterialsList = materialsInfoDao.queryFlMaterialsInfo(order.getOrderId());
							if(flMaterialsList!= null && flMaterialsList.size()>0){
								JSONObject jsonObject = new JSONObject();
								JSONArray jsonArray = new JSONArray();
								jsonObject.put("orderNumber",order.getOrderNumber());
								jsonObject.put("isFloor",false+"");
								jsonObject.put("assistInfo", JSONArray.fromObject(flMaterialsList));
								String[] paramArr = new String[]{"orderNumber="+order.getOrderNumber(),"isFloor=false"};
								String key = KeyAuthenticateUtils2.getKey(paramArr,BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
								jsonObject.put("key",key);
								jsonArray.add(jsonObject);
								
								//向同步表插入数据 以便发送 ------------------------
								BizSynData bizSynData = new BizSynData();
								bizSynData.setBusinessData(jsonArray.toString());
						        bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
						        bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
						    	bizSynData.setBusinessOnlyMarkInt(summaryId);
						    	bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
						    	bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
						    	bizSynData.preInsert();
						    	
						    	bizSynDataDao.insert(bizSynData);
							}
						}else{
							param.put("type", 7);
							TaskPackage taskPackage2 = orderTaskPackageDao.queryOrderTaskPackageByParam(param);
							if (taskPackage2 != null && taskPackage2.getId() != null) {// 判断是否属于泥瓦任务包
								//获取沙子水泥
								List<MaterialsInfo> sandMaterials = materialsInfoDao.querySandMaterialsInfo(order.getOrderId());
								if(sandMaterials!= null && sandMaterials.size()>0){
									JSONObject jsonObject = new JSONObject();
									JSONArray jsonArray = new JSONArray();
									String[] paramArr = new String[]{"orderNumber="+order.getOrderNumber(),"isFloor=false"};
									String key = KeyAuthenticateUtils2.getKey(paramArr,BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
									jsonObject.put("key",key);
									jsonObject.put("orderNumber",order.getOrderNumber());
									jsonObject.put("isFloor",false+"");
									jsonObject.put("assistInfo", JSONArray.fromObject(sandMaterials));
									jsonArray.add(jsonObject);
									
									//向同步表插入数据 以便发送 ------------------------
									BizSynData bizSynData = new BizSynData();
									bizSynData.setBusinessData(jsonArray.toString());
							        bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
							        bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
							    	bizSynData.setBusinessOnlyMarkInt(summaryId);
							    	bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
							    	bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
							    	bizSynData.preInsert();
							    	
							    	bizSynDataDao.insert(bizSynData);
								}
							}
						}
					}*/

					// 6.更新结算单
					OrderTaskpackageSettlement settlement = orderTaskpackageSettlementDao
							.get(payment.getOrderTaskpackageSettlementId());
					settlement.setStatus(ConstantUtils.SETTLEMENT_STATUS_100);
					settlement.setStatusDate(date);
					settlement.setUpdateBy(user);
					settlement.setUpdateDate(date);
					orderTaskpackageSettlementDao.update(settlement);
				}
			}
		}

		// 7.发短信
		// 给结算员发短信
		BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(summary.getStoreId() + "",
				ConstantUtils.MESSAGE_GROUP_TYPE_100);
		List<Integer> list = new ArrayList<Integer>();
		if (bizMessagegroup != null && StringUtils.isNoneBlank(bizMessagegroup.getEmployees())) {
			String[] str = bizMessagegroup.getEmployees().split(",");
			for (String id1 : str) {
				list.add(Integer.valueOf(id1));
			}
			BizOrderTaskpackagePaymentSummary paymentSummary = bizOrderTaskpackagePaymentSummaryDao
					.querySendMsgForSummary(summaryId);

			BizPhoneMsg msg = new BizPhoneMsg();
			msg.setReceiveEmployeeId(paymentSummary.getEmployeeId());
			msg.setReceivePhone(paymentSummary.getPhone());
			msg.setMsgGenerateDatetime(date);
			msg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			msg.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_201201);
			msg.setRelatedBusinessIdInt(summaryId);

			List<BizEmployee2> employeelist = bizEmployeeService2.getById(list);
			if (CollectionUtils.isNotEmpty(employeelist)) {
				for (BizEmployee2 employee : employeelist) {
					String content = "批次编号（" + paymentSummary.getOrderTaskpackagePaymentSummaryCode() + "），付款员：（"
							+ employee.getRealname() + "-" + employee.getPhone() + "）批次已付款完成，请登录系统查看详情。";
					msg.setMsgContent(content);
					bizPhoneMsgService.insert(msg);
				}
			}
		}

		// 给工人发短信,发送通知
		List<BizOrderTaskpackagePaymentDetailMergeVo> voList = dao.querySendMsgForEmployee(summaryId);
		if (CollectionUtils.isNotEmpty(voList)) {
			BizPhoneMsg msg = new BizPhoneMsg();
			msg.setMsgGenerateDatetime(date);
			msg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			msg.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_201202);
			msg.setRelatedBusinessIdInt(summaryId);

			BizMsg bizMsg = new BizMsg();
			bizMsg.setMsgTitle("任务包已付款");
			bizMsg.setMsgTime(date);
			bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
			bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_106001001);
			bizMsg.setBusiIdInt(summaryId);

			for (BizOrderTaskpackagePaymentDetailMergeVo vo : voList) {
				msg.setReceiveEmployeeId(vo.getEmployeeId());
				msg.setReceivePhone(vo.getPhone());
				String content = "亲爱的工人师傅，您的任务包结算款（" + vo.getAmount() + "）元，已转入您在公司预留银行卡中，尾号（" + vo.getBankCardNo()
						+ "），预计24小时内到账。可在【我的账单】中查看详情。";
				msg.setMsgContent(content);
				bizPhoneMsgService.insert(msg);

				bizMsg.setEmployeeId(vo.getEmployeeId());
				bizMsg.setMsgContent(content);
				bizProjectChangeBillService.saveBizMsg(bizMsg);
			}
		}
	}
}