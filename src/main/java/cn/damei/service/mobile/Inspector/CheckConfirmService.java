package cn.damei.service.mobile.Inspector;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.modules.BizCustomerReturnVisitTraditionOrderData;
import cn.damei.service.modules.BizCustomerReturnVisitRecordService;
import cn.damei.service.modules.BizPhoneMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.toDoConstant.ApplyCheckToDoConstatntUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.KeyAuthenticateUtils2;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.SessionUtils;
import cn.damei.common.utils.PqcUtil;
import cn.damei.dao.mobile.Inspector.CheckConfirmDao;
import cn.damei.entity.mobile.Inspector.CheckConfirm;
import cn.damei.entity.mobile.Inspector.BizEvalScore;
import cn.damei.entity.mobile.Inspector.BizEvalScoreRole;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Inspector.MaterialsInfo;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.EvalScoreRoleIndex;
import cn.damei.service.mobile.Manager.BizEvalTaskpackRoleIndexScoreService;
import cn.damei.entity.mobile.Manager.EvalScoreRole;
import cn.damei.service.mobile.Manager.BizEvalTaskpackRoleScoreService;
import cn.damei.entity.mobile.Manager.EvalScore;
import cn.damei.service.mobile.Manager.BizEvalTaskpackScoreService;
import cn.damei.service.mobile.Manager.OrderInstallPlanService;
import cn.damei.entity.modules.ToDoItemEntity;
import cn.damei.service.modules.ToDoItemService;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizQcBill;
import cn.damei.service.modules.BizQcBillService;
import cn.damei.entity.modules.BizSynData;
import cn.damei.service.modules.BizSynDataService;
import cn.damei.service.modules.BizBusinessUrgePayService;
import cn.damei.entity.modules.CheckNode;
import cn.damei.service.modules.CheckNodeService;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizEvalActivityIndex;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.dao.modules.OrderDao2;
import cn.damei.entity.modules.Order2;
import cn.damei.dao.modules.BizPhoneMsgDao;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.dao.modules.InspectorConfirmDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
@Transactional(readOnly = true)
public class CheckConfirmService extends CrudService2<CheckConfirmDao, CheckConfirm> {
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizQcBillService bizQcBillService;
	@Autowired
	private BizEvalTaskpackRoleScoreService bizEvalTaskpackRoleScoreService;

	@Autowired
	private InspectorEvaluateWorkerService inspectorEvaluateWorkerService;

	@Autowired
	private BizEvalTaskpackScoreService bizEvalTaskpackScoreService;

	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;

	@Autowired
	private BizEvalTaskpackRoleIndexScoreService bizEvalTaskpackRoleIndexScoreService;
	@Autowired
	private BizEmployeeDao bizEmployeeDao;
	
	@Autowired
	private OrderDao2 orderDao2;
	@Autowired
	private InspectorConfirmDao inspectorConfirmDao;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizPhoneMsgDao bizPhoneMsgDao;

	@Autowired
	private BizCustomerReturnVisitRecordService bizCustomerReturnVisitRecordService;


	public CheckConfirm findQcBillById(int id) {
		return dao.findQcBillById(id);
	}


	@Transactional(readOnly = false)
	public String updateInform(String delayReasonQc, String id, String[] photo, String bizEvalActivityId,
			String[] bizEvalActivityIndexId, String[] bizEvalActivityIndexEvalTotalScore,
			String[] bizEvalActivityIndexSelectCount, HttpServletRequest request) {

		String result = "0";

		Date date = new Date();







		CheckConfirm checkConfirm = new CheckConfirm();
		checkConfirm.setId(Integer.valueOf(id));
		checkConfirm.setAcceptCheckDatetime(date);
		checkConfirm.setUpdateDate(date);
		checkConfirm.setDelayReasonQc(delayReasonQc);

		Integer count = findCheckNodeRel(Integer.valueOf(id));

		CheckConfirm confirm = findSettlement(Integer.valueOf(id));
        String oldStatus = confirm.getStatus();
		if (null != confirm.getProjectMode() && confirm.getProjectMode().equals("2")) {
			checkConfirm.setStatus("30");
		} else {
			if (count != 0 || confirm.getManagerCount() != 0 || confirm.getInspectorCount() != 0) {
				checkConfirm.setStatus("10");
			} else {
				checkConfirm.setStatus("30");
			}
		}
		Inspector ins = (Inspector) request.getSession().getAttribute("inspector");


		dao.updateInform(checkConfirm);

		if (confirm.getQcCheckNodeId() != null && confirm.getQcCheckNodeId() == 9) {
			confirm.setUpdateDate(date);
			dao.updateOrderActualEndDateByCheckConfirm(confirm);
		}

		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3200);
		statusLog.setBusinessOnlyMarkInt(Integer.parseInt(id));
		statusLog.setBusinessStatus(checkConfirm.getStatus());
		statusLog.setStatusDatetime(new Date());
		statusLog.setBusinessEmployeeId(ins.getId());
		statusLog.setBusinessRemarks("质检员确认验收约检节点");
		statusLog.preInsert();
		bizBusinessStatusLogDao.insert(statusLog);

		if (null != photo && photo.length > 0) {


			savePicAll(photo, request, Integer.valueOf(id), "3");
		}

		try {


			saveAnotherInfo(Integer.valueOf(id), request, confirm);
		} catch (Exception e) {

			e.printStackTrace();
			result = "101";
		}

		try {


			saveEvalInfo(id, date, bizEvalActivityId, bizEvalActivityIndexId, bizEvalActivityIndexEvalTotalScore,
					bizEvalActivityIndexSelectCount);

		} catch (Exception e) {

			e.printStackTrace();
			result = "102";
		}

		try {


			savePqcToDoInfo(String.valueOf(confirm.getOrderId()), String.valueOf(confirm.getQcCheckNodeId()));

		} catch (Exception e) {

			e.printStackTrace();
			result = "103";
		}




		if (count != 0) {
			Inspector inspector = SessionUtils.getInspectorSession(request);
			String storeId = inspector.getStoreId().toString();
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(storeId, "7");
			List<Integer> list = new ArrayList<Integer>();
			List<BizEmployee2> employeelist = null;

			if (null != bizMessagegroup) {
				String[] str = bizMessagegroup.getEmployees().split(",");
				for (String id1 : str) {
					list.add(Integer.valueOf(id1));
				}
				employeelist = bizEmployeeService2.getById(list);

				String content = "（" + confirm.getCommunityName() + "-" + confirm.getBuildNumber() + "-" + confirm.getBuildUnit()
						+ "-" + confirm.getBuildRoom() + "-" + confirm.getCustomerName() + "-"
						+ confirm.getQcCheckNodeName() + "）已提交了验收信息，质检员（" + inspector.getRealName() + "-"
						+ inspector.getPhone() + "），请您及时进行处理。";
				if (null != employeelist && employeelist.size() > 0) {
					for (BizEmployee2 bizEmployee2 : employeelist) {
						bizPhoneMsgService.sendMessage(bizEmployee2.getId(), bizEmployee2.getPhone(),
								content, SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200808, Integer.valueOf(id));
					}
				}
			}
		}
		if(oldStatus.equals("5")){
			Order2 order = orderDao2.get(confirm.getOrderId());
			BizEmployee bizEmployee = bizEmployeeDao.get(String.valueOf(order.getItemManagerId()));
			Map<String, Object> qcParam = new HashMap<String, Object>();
			qcParam.put("orderId", confirm.getOrderId());
			qcParam.put("settleNode", 1);
			qcParam.put("qcCheckNodeId", confirm.getQcCheckNodeId());
			int qcCount = inspectorConfirmDao.checkQcCheck(qcParam);
			if (qcCount > 0) {
				String content = "订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-"
						+ order.getBuildUnit() + "-" + order.getBuildRoom()
						+ "）家的中期预计结算金额已生成，请您及时登录APP在（结算管理-->订单结算金额预览）中查看。";
				BizPhoneMsg msg = new BizPhoneMsg();
				msg.setReceiveEmployeeId(order.getItemManagerId());
				msg.setReceivePhone(bizEmployee.getPhone());
				msg.setMsgContent(content);
				msg.setMsgGenerateDatetime(date);
				msg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				msg.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200804);
				msg.setRelatedBusinessIdInt(order.getId());
				bizPhoneMsgDao.insert(msg);
			}
			qcParam.put("settleNode", 2);
			qcCount = inspectorConfirmDao.checkQcCheck(qcParam);
			if (qcCount > 0) {
				String content = "订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-"
						+ order.getBuildUnit() + "-" + order.getBuildRoom()
						+ "）家的竣工预计结算金额已生成，请您及时登录APP在（结算管理-->订单结算金额预览）中查看。";
				BizPhoneMsg msg = new BizPhoneMsg();
				msg.setReceiveEmployeeId(order.getItemManagerId());
				msg.setReceivePhone(bizEmployee.getPhone());
				msg.setMsgContent(content);
				msg.setMsgGenerateDatetime(date);
				msg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				msg.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200804);
				msg.setRelatedBusinessIdInt(order.getId());
				bizPhoneMsgDao.insert(msg);
			}

			List<Map<String,Object>>ll=bizCustomerReturnVisitRecordService.findIsThereNode(order.getStoreId(),confirm.getQcCheckNodeId());
			if(ll.size()>0) {

				Integer i = 0;
				if (null != confirm.getOrderId() && null != confirm.getQcCheckNodeId()) {
					i = bizCustomerReturnVisitRecordService.findExistCount(confirm.getOrderId(), confirm.getQcCheckNodeId());
				}
				if (i == 0) {
					BizCustomerReturnVisitTraditionOrderData bto = new BizCustomerReturnVisitTraditionOrderData();
					bto.setOrderId(String.valueOf(confirm.getOrderId()));
					bto.setReturnVisitNode(String.valueOf(confirm.getQcCheckNodeId()));
					bto.setReturnVisitStatus(1);
					Date dates = new Date();
					bto.setCreateDate(dates);
					bizCustomerReturnVisitRecordService.insertBizCustomerReturnVisitTraditionOrderData(bto);
					bto.preInsert();
				}
			}

			List<BizCustomerReturnVisitTraditionOrderData>list=bizCustomerReturnVisitRecordService.findReturnVisitNode(confirm.getOrderId());
			if(list.size()>0){
				for(BizCustomerReturnVisitTraditionOrderData bto:list){
					bto.setReturnVisitStatus(0);
					bizCustomerReturnVisitRecordService.updateStatus(bto);
				}
			}
		}
		









































		return result;
	}


	@Transactional(readOnly = false)
	public void savePic(ReportCheckDetailsPic reportCheckDetailsPic) {
		dao.savePic(reportCheckDetailsPic);
	}


	public Integer findCheckNodeRel(Integer id) {
		return dao.findCheckNodeRel(id);
	}


	public Integer findSettleNodeRel(Integer qcCheckNodeId) {
		return dao.findSettleNodeRel(qcCheckNodeId);
	}


	@Transactional(readOnly = false)
	public void savePicAll(String[] photo, HttpServletRequest request, Integer inspectBillId, String type) {

		dao.savePicAll(PqcUtil.HandleSavePics(photo, request, inspectBillId, type));

	}


	public CheckConfirm findSettlement(Integer id) {
		return dao.findSettlement(id);
	}


	@Transactional(readOnly = false)
	public void savePicAll(List<ReportCheckDetailsPic> pList) {
		dao.savePicAll(pList);

	}

	@Autowired
	private OrderInstallPlanService orderInstallPlanService;
	@Autowired
	private CheckNodeService checkNodeService;
	@Autowired
	private MaterialsInfoService materialsInfoService;

	@Autowired
	private BizSynDataService bizSynDataService;

	@Transactional(readOnly = false)
	public void saveAnotherInfo(Integer id, HttpServletRequest request, CheckConfirm confirm) {


		if (confirm != null && confirm.getStatus().equals("5")) {
			Integer orderId = confirm.getOrderId();
			String orderNumber = confirm.getOrderNumber();
			Integer nodeIndex = confirm.getQcCheckNodeIndex();

			CheckNode checkNode = checkNodeService.get(confirm.getQcCheckNodeId());
			if (checkNode != null && checkNode.getQcCheckNodeIndex() == 6) {


				Map<String, Object> map = new HashMap<String, Object>();
				map.put("orderId", confirm.getOrderId());
				map.put("type", "1");
				List<MaterialsInfo> materialsStandardList = materialsInfoService.queryMaterialsStandardInfoByParam(map);
				if (materialsStandardList != null && materialsStandardList.size() > 0) {
					JSONObject jsonObject = new JSONObject();

					String[] paramArr = new String[] { "orderNumber=" + confirm.getOrderNumber(), "isFloor=false" };
					String key = KeyAuthenticateUtils2.getKey(paramArr,
							BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
					jsonObject.put("key", key);
					jsonObject.put("orderNumber", confirm.getOrderNumber());
					jsonObject.put("isFloor", false + "");
					jsonObject.put("assistInfo", JSONArray.fromObject(materialsStandardList).toString());

					BizSynData bizSynData = new BizSynData();
					bizSynData.setBusinessData(jsonObject.toString());
					bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
					bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
					bizSynData.setBusinessOnlyMarkInt(id);
					bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
					bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
					bizSynData.preInsert();

					bizSynDataService.save(bizSynData);
				}

				Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");

				try {
					orderInstallPlanService.insertsyndata(orderNumber + "", "99999", inspector,
							ConstantUtils.BUSINESS_TYPE_401);
				} catch (Exception e) {
					logger.info("-------------------------墙地砖信息推送到订单流转系统：失败!--------------------------------");
				}

			}

			if (nodeIndex.intValue() == 9) {



				BizSynData bizSynData = new BizSynData();

				JSONObject jsonObject = new JSONObject();




				String[] paramArr = new String[] { "orderNumber=" + orderNumber, "isFloor=" + false };

				String key = KeyAuthenticateUtils2.getKey(paramArr,
						BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
				jsonObject.put("key", key);

				jsonObject.put("orderNumber", orderNumber);
				jsonObject.put("isFloor", false + "");

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("orderId", orderId);
				map.put("type", 2 + "");

				List<MaterialsInfo> list = materialsInfoService.queryMaterialsStandardInfoByParam(map);


				jsonObject.put("assistInfo", JSONArray.fromObject(list));

				String jsonData = jsonObject.toString();

				bizSynData.setBusinessData(jsonData);
				bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
				bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
				bizSynData.setBusinessOnlyMarkInt(id);
				bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
				bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
				bizSynData.preInsert();

				bizSynDataService.save(bizSynData);


				BizSynData bizSynData1 = new BizSynData();

				JSONObject jsonObject2 = new JSONObject();

				String[] paramArr1 = new String[] { "orderNumber=" + orderNumber, "isFloor=" + false };

				String key1 = KeyAuthenticateUtils2.getKey(paramArr1,
						BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
				jsonObject2.put("key", key1);

				jsonObject2.put("orderNumber", orderNumber);
				jsonObject2.put("isFloor", false + "");



				List<MaterialsInfo> list2 = materialsInfoService.queryKgmbMaterialsInfo(orderId);

				jsonObject2.put("assistInfo", JSONArray.fromObject(list2));

				String jsonData1 = jsonObject2.toString();

				bizSynData1.setBusinessData(jsonData1);
				bizSynData1.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
				bizSynData1.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
				bizSynData1.setBusinessOnlyMarkInt(id);
				bizSynData1.setSynStatus(ConstantUtils.SYN_STATUS_4);
				bizSynData1.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
				bizSynData1.preInsert();

				bizSynDataService.save(bizSynData1);


				BizSynData bizSynData3 = new BizSynData();

				JSONObject jsonObject3 = new JSONObject();

				String[] paramArr3 = new String[] { "orderNumber=" + orderNumber, "isFloor=" + false };

				String key3 = KeyAuthenticateUtils2.getKey(paramArr3,
						BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
				jsonObject3.put("key", key3);

				jsonObject3.put("orderNumber", orderNumber);
				jsonObject3.put("isFloor", false + "");

				List<MaterialsInfo> list3 = materialsInfoService.querySandMaterialsInfo(orderId);

				jsonObject3.put("assistInfo", JSONArray.fromObject(list3));

				String jsonData3 = jsonObject3.toString();

				bizSynData3.setBusinessData(jsonData3);
				bizSynData3.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
				bizSynData3.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
				bizSynData3.setBusinessOnlyMarkInt(id);
				bizSynData3.setSynStatus(ConstantUtils.SYN_STATUS_4);
				bizSynData3.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
				bizSynData3.preInsert();

				bizSynDataService.save(bizSynData3);


				BizSynData bizSynData4 = new BizSynData();

				JSONObject jsonObject4 = new JSONObject();

				String[] paramArr4 = new String[] { "orderNumber=" + orderNumber, "isFloor=" + false };

				String key4 = KeyAuthenticateUtils2.getKey(paramArr4,
						BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
				jsonObject4.put("key", key4);

				jsonObject4.put("orderNumber", orderNumber);
				jsonObject4.put("isFloor", false + "");

				List<MaterialsInfo> list4 = materialsInfoService.queryFlMaterialsInfo(orderId);

				jsonObject4.put("assistInfo", JSONArray.fromObject(list4));

				String jsonData4 = jsonObject4.toString();

				bizSynData4.setBusinessData(jsonData4);
				bizSynData4.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
				bizSynData4.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
				bizSynData4.setBusinessOnlyMarkInt(id);
				bizSynData4.setSynStatus(ConstantUtils.SYN_STATUS_4);
				bizSynData4.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
				bizSynData4.preInsert();

				bizSynDataService.save(bizSynData4);

			}
		}

	}

	@Transactional(readOnly = false)
	public void saveEvalInfo(String id, Date date, String bizEvalActivityId, String[] bizEvalActivityIndexId,
			String[] bizEvalActivityIndexEvalTotalScore, String[] bizEvalActivityIndexSelectCount) {


		if (bizEvalActivityIndexId != null && bizEvalActivityIndexId.length > 0) {
			BizQcBill bizQcBill = bizQcBillService.get(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("relatedBusinessId", id);
			map.put("evalType", "2");
			map.put("groupLeaderEmployeeId", bizQcBill.getApplyEmployeeId());
			map.put("evalStatus", ConstantUtils.EVAL_STATUS_1);
			Integer evalScoreCount = bizEvalTaskpackScoreService.queryByCondition(map);
			if (evalScoreCount == 0) {
				EvalScore bizEvalScore = new EvalScore();
				bizEvalScore.setRelatedBusinessId(Integer.valueOf(id));
				bizEvalScore.setEvalType("2");
				bizEvalScore.setGroupLeaderEmployeeId(bizQcBill.getApplyEmployeeId());
				bizEvalScore.setEvalStatus(ConstantUtils.EVAL_STATUS_1);
				bizEvalScore.setStatusDatetime(date);
				bizEvalScore.setEvalStartDatetime(date);
				bizEvalScore.setCreateDate(date);
				bizEvalScore.setUpdateDate(date);
				bizEvalTaskpackScoreService.save(bizEvalScore);

				Double totalScore = 0d;
				for (int i = 0; i < bizEvalActivityIndexEvalTotalScore.length; i++) {
					Double evalScore = Double.valueOf(bizEvalActivityIndexEvalTotalScore[i]);
					totalScore = totalScore
							+ (Double.valueOf(evalScore) / 5) * Integer.valueOf(bizEvalActivityIndexSelectCount[i]);
				}
				EvalScoreRole bizEvalTaskpackRoleScore = new EvalScoreRole();
				bizEvalTaskpackRoleScore.setEvalScoreId(bizEvalScore.getId());
				bizEvalTaskpackRoleScore.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_201);
				bizEvalTaskpackRoleScore.setEvalByEmployeeId(bizQcBill.getApplyEmployeeId());
				bizEvalTaskpackRoleScore.setGotScore(totalScore);

				bizEvalTaskpackRoleScore.setEvalDatetime(date);
				bizEvalTaskpackRoleScore.setCreateDate(date);
				bizEvalTaskpackRoleScore.setUpdateDate(date);
				bizEvalTaskpackRoleScore.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
				bizEvalTaskpackRoleScoreService.save(bizEvalTaskpackRoleScore);

				List<EvalScoreRoleIndex> bizEvalTaskpackRoleIndexScoreList = new ArrayList<EvalScoreRoleIndex>();
				for (int i = 0; i < bizEvalActivityIndexId.length; i++) {
					EvalScoreRoleIndex bizEvalTaskpackRoleIndexScore = new EvalScoreRoleIndex();
					bizEvalTaskpackRoleIndexScore.setEvalScoreRoleId(bizEvalTaskpackRoleScore.getId());
					bizEvalTaskpackRoleIndexScore.setEvalActivityIndexId(Integer.valueOf(bizEvalActivityIndexId[i]));
					bizEvalTaskpackRoleIndexScore
							.setGotScore((Double.valueOf(bizEvalActivityIndexEvalTotalScore[i]) / 5)
									* Integer.valueOf(bizEvalActivityIndexSelectCount[i]));
					bizEvalTaskpackRoleIndexScore.setCreateDate(date);
					bizEvalTaskpackRoleIndexScore.setUpdateDate(date);
					bizEvalTaskpackRoleIndexScoreList.add(bizEvalTaskpackRoleIndexScore);
				}
				bizEvalTaskpackRoleIndexScoreService.insertBatch(bizEvalTaskpackRoleIndexScoreList);

				List<BizEvalActivityIndex> activityIndexList = inspectorEvaluateWorkerService
						.queryEvalActivityIndexByActivityId(Integer.valueOf(bizEvalActivityId));
				String pqcType = null;
				String custemerType = null;
				if (activityIndexList != null && activityIndexList.size() > 0) {
					for (BizEvalActivityIndex activityIndex : activityIndexList) {
						if (activityIndex.getEvalRoleType().equals("2")) {
							pqcType = "2";
						} else if (activityIndex.getEvalRoleType().equals("3")) {
							custemerType = "3";
						}
					}
				}

				BizEvalScoreRole scoreBean = new BizEvalScoreRole();
				scoreBean.setEvalScoreId(bizEvalScore.getId());
				scoreBean.setPqcType(pqcType);
				scoreBean.setCustemerType(custemerType);
				BizEvalScoreRole evalTaskpackRoleScore = inspectorEvaluateWorkerService.isEndEvaluate(scoreBean);
				if (null != evalTaskpackRoleScore && evalTaskpackRoleScore.getGotScore() > 0) {


					BizEvalScore evalTaskpackScore = new BizEvalScore();
					evalTaskpackScore.setId(bizEvalScore.getId());
					evalTaskpackScore.setGotScore(evalTaskpackRoleScore.getGotScore());
					evalTaskpackScore.setEvalStatus(ConstantUtils.EVAL_STATUS_2);
					evalTaskpackScore.setUpdateDate(date);
					evalTaskpackScore.setStatusDatetime(date);
					inspectorEvaluateWorkerService.updateEvalTaskpackScore(evalTaskpackScore);
				}
			}
		}

	}


	@Autowired
	private ToDoItemService toDoItemService;

	@Autowired
	private BizBusinessUrgePayService bizBusinessUrgePayService;

	@Transactional(readOnly = false)
	public void savePqcToDoInfo(String orderId, String qcNodeId) {



		String id = toDoItemService.selectId(ApplyCheckToDoConstatntUtil.APPLY_CHECK_TO_DO_BUSINESS_TYPE, qcNodeId,
				orderId);
		if (null != id) {

			int isUrgePay = toDoItemService.findUrgePay(qcNodeId);

			if (1 == isUrgePay) {


				String urgePayId = bizBusinessUrgePayService.insertUrgePayByOrderId(Integer.valueOf(orderId));



				Map<String, String> paraMap = new HashMap<>(12);
				paraMap.put("orderId", orderId);
				paraMap.put("relatedBusinessType", ApplyCheckToDoConstatntUtil.RELATED_BUSINESS_TYPE_200);
				paraMap.put("qcNodeId", qcNodeId);
				Map<String, Object> map = toDoItemService.getToDoInfoByMap(paraMap);
				if (null != map) {
					Date date = new Date();

					String url = "/app/manager/backlog/toDealErQiKuan?id=" + urgePayId;
					ToDoItemEntity entity = new ToDoItemEntity();

					entity.setToDoItemTypeId(Integer
							.valueOf(map.get("relatedTypeId") == null ? "0" : map.get("relatedTypeId").toString()));
					entity.setRelatedBusinessId(Integer.valueOf(urgePayId));
					entity.setRelatedBusinessType(ApplyCheckToDoConstatntUtil.APPLY_CHECK_TO_DO_BUSINESS_TYPE_999);
					entity.setOrderId(Integer.valueOf(orderId));
					entity.setToDoItemEmployeeId(Integer
							.valueOf(map.get("toDoEmployeeId") == null ? "0" : map.get("toDoEmployeeId").toString()));
					entity.setToDoItemRemindTitle(
							map.get("remindTitle") == null ? "0" : map.get("remindTitle").toString());
					entity.setToDoItemDealUrl(url);
					entity.setToDoItemGeneratedDatetime(date);
					entity.setToDoItemRemindDatetime(
							(map.get("confirmRemindTime") == null ? null : (Date) map.get("confirmRemindTime")));

					entity.setIsViewd("0");
					entity.setIsSolved("0");

					toDoItemService.insertToDoInfo(entity);

				}

			}

		}

		String nextQcNodeId = toDoItemService.findNextIdByPreId(qcNodeId);


		if (null != nextQcNodeId) {


			Map<String, String> paraMap = new HashMap<>(12);
			paraMap.put("orderId", orderId);
			paraMap.put("relatedBusinessType", ApplyCheckToDoConstatntUtil.RELATED_BUSINESS_TYPE);
			paraMap.put("qcNodeId", nextQcNodeId);
			Map<String, Object> map = toDoItemService.getToDoInfoByMap(paraMap);
			if (null != map && null != map.get("relatedTypeId")) {
				Date date = new Date();


				String url = "/app/manager/qualityCheck?id=" + orderId;
				ToDoItemEntity entity = new ToDoItemEntity();

				entity.setToDoItemTypeId(
						Integer.valueOf(map.get("relatedTypeId") == null ? "0" : map.get("relatedTypeId").toString()));
				entity.setRelatedBusinessId(Integer
						.valueOf(map.get("relatedBusinessId") == null ? "0" : map.get("relatedBusinessId").toString()));
				entity.setRelatedBusinessType(ApplyCheckToDoConstatntUtil.APPLY_CHECK_TO_DO_BUSINESS_TYPE);
				entity.setOrderId(Integer.valueOf(orderId));
				entity.setToDoItemEmployeeId(Integer
						.valueOf(map.get("toDoEmployeeId") == null ? "0" : map.get("toDoEmployeeId").toString()));
				entity.setToDoItemRemindTitle(map.get("remindTitle") == null ? "0" : map.get("remindTitle").toString());
				entity.setToDoItemDealUrl(url);
				entity.setToDoItemGeneratedDatetime(date);
				entity.setToDoItemRemindDatetime(
						(map.get("confirmRemindTime") == null ? null : (Date) map.get("confirmRemindTime")));

				entity.setIsViewd("0");
				entity.setIsSolved("0");

				toDoItemService.insertToDoInfo(entity);

			}

		}

	}

}
