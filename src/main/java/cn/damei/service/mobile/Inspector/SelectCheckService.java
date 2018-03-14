package cn.damei.service.mobile.Inspector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.entity.mobile.Inspector.*;
import cn.damei.common.ProjectIssueUtil.Purchase.PurchaseCodeUtils;
import cn.damei.common.SessionUtils;
import cn.damei.common.utils.PqcUtil;
import cn.damei.entity.modules.BizMsg;
import cn.damei.service.modules.BizProjectChangeBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Inspector.SelectCheckDao;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.entity.mobile.Manager.QualityControl;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;


@Service
@Transactional(readOnly = true)
public class SelectCheckService extends CrudService2<SelectCheckDao, Order> {

	@Autowired
	private PhoneMessageDao messageDao;
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	@Autowired
	private CheckItemService checkItemService;

	public List<Order> findOrderByInspectorId(Order order) {
		return dao.findOrderByInspectorId(order);
	}


	public BizQcBill findBizQcBillByOrderId(Integer orderId) {
		return dao.findBizQcBillByOrderId(orderId);
	}


	public List<InspectItem> findZanCun(int qcBillId) {
		return dao.findZanCun(qcBillId);
	}


	public Integer findStoreByOrderId(Integer orderId) {
		return dao.findStoreByOrderId(orderId);
	}


	public List<InspectKind> selectAllCheckItem(Integer storeId) {
		return dao.selectAllCheckItem(storeId);
	}


	public List<ReportCheckDetailsPic> findPic(int qcBillId) {
		return dao.findPic(qcBillId);
	}


	public Integer findSign(Integer qcBillId) {
		return dao.findSign(qcBillId);
	}


	@Transactional(readOnly = false)
	public void deleteCheckItemsByCheckId(int qcBillId) {
		dao.deleteCheckItemsByCheckId(qcBillId);
	}


	@Transactional(readOnly = false)
	public Integer saveBizQcBill(BizQcBill bizQcBill) {
		return dao.saveBizQcBill(bizQcBill);
	}


	@Transactional(readOnly = false)
	public void saveItems(InspectItem item) {
		dao.saveItems(item);
	}


	public List<InspectKind> changeCheckItem(int inspectId) {
		return dao.changeCheckItem(inspectId);
	}


	public List<IllegalModality> findIllegalModalityByCheckItemId(int checkItemId) {
		return dao.findIllegalModalityByCheckItemId(checkItemId);
	}


	public InspectItem selectCheckItemInfoByIllegalModalityId(int id) {
		return dao.selectCheckItemInfoByIllegalModalityId(id);
	}


	@Transactional(readOnly = false)
	public void updateCheckItem(InspectItem item) {
		dao.updateCheckItem(item);
	}


	public Integer selectCheckItemId(InspectItem item) {
		return dao.selectCheckItemId(item);
	}


	@Transactional(readOnly = false)
	public void saveIllegalModality(IllegalModality modality) {
		dao.saveIllegalModality(modality);
	}


	public	List<InspectItem> selectScoresFromCheckItemRecord(Map<String, Object> map) {
		return dao.selectScoresFromCheckItemRecord(map);
	}


	public Double selectCheckItemScores(int checkItemId) {
		return dao.selectCheckItemScores(checkItemId);
	}


	@Transactional(readOnly = false)
	public void updateInspect(InspectKind kind) {
		dao.updateInspect(kind);
	}



	@Transactional(readOnly = false)
	public void deletePic(Integer picId) {
		dao.deletePic(picId);
	}


	public PurchaseTwoCode getCode() {
		return dao.getCode();
	}


	@Transactional(readOnly = false)
	public void updateCode(PurchaseTwoCode code) {
		dao.updateCode(code);
	}


	@Transactional(readOnly = false)
	public void insertPurchase(PurchaseTwoCode purchaseObjVo) {
		dao.insertPurchase(purchaseObjVo);
	}


	public InspectKind findOrderIdByBillId(Integer billId) {

		return dao.findOrderIdByBillId(billId);
	}


	@Transactional(readOnly = false)
	public int saveRecheck(InspectKind inspectKind) {
		Date date = new Date();
		Recheck recheck = new Recheck();
		recheck.setReCheckCode(codeUtils.qcBillCode());
		recheck.setType("2");
		recheck.setIsReCheck("1");
		recheck.setRelatedBillId(inspectKind.getInspectBillId());
		recheck.setStatus("1");
		recheck.setCreateDate(date);
		recheck.setOrderId(inspectKind.getOrderId());
		recheck.setCheckNodeId(inspectKind.getCheckNodeId());
		dao.saveRecheck(recheck);
		return recheck.getReCheckId();
	}
	

	@Transactional(readOnly = false)
	public void saveRecheckCheckItem(Recheck recheck){
		
		
		dao.saveRecheckCheckItem(recheck);
	}


	@Transactional(readOnly = false)
	public void saveItemsAll(List<InspectItem> list) {
		dao.saveItemsAll(list);
	}


	@Transactional(readOnly = false)
	public void updateCheckItemAll(List<InspectItem> hege) {
		dao.updateCheckItemAll(hege);
	}


	@Transactional(readOnly = false)
	public void saveRecheckCheckItemAll(List<Recheck> buhege) {
		dao.saveRecheckCheckItemAll(buhege);
	}


	@Transactional(readOnly = false)
	public void saveIllegalModalityAll(List<IllegalModality> list) {
		dao.saveIllegalModalityAll(list);
	}


	public BizQcBill findTimeSpan(Integer orderId) {
		return dao.findTimeSpan(orderId);
	}

	public List<InspectItem> findWorkerManagerInspectorPackageInfoByOrderId(Integer inspectId){
		
		return dao.findWorkerManagerInspectorPackageInfoByOrderId(inspectId);
	}
	public int findWorkerInfoByPackId(Integer packId){
		
		return dao.findWorkerInfoByPackId(packId);
	}
	public  QualityControl findMessageInfoByInspectId(Integer inspectId){
		
		return dao.findMessageInfoByInspectId(inspectId);
	}
	public  QualityControl findMessageInfoByInspectId2(Integer inspectId){
		
		return dao.findMessageInfoByInspectId2(inspectId);
	}


	public List<Order> findOrderByLeaderInspectorId(Order or) {
		return dao.findOrderByLeaderInspectorId(or);
	}



	public BizQcBill findSelectCheckIsExist(Integer orderId){

		return dao.findSelectCheckIsExist(orderId);
	}








	public boolean saveMessageContent( QualityControl control,Integer pqcId,String pqcName,String pqcPhone) {


		String content = "订单（" + control.getCommunityName() + "-" + control.getBuildNumber() + "-" + control.getBuildUnit() + "-" + control.getBuildRoom() + "-" + control.getCustomerName() + "-" + control.getCustomerPhone() + "，质检员（" + pqcName + "-" +pqcPhone + "），已产生复检单，请及时登录APP查看详情。	";

		return saveMessage(content,control.getItemManagerId(),control.getPhone(),pqcId);
	}



	public boolean saveMsgContent(  QualityControl control,Integer recheckId,String pqcName,String pqcPhone){

		String content = "订单（" + control.getCommunityName() + "-" + control.getBuildNumber() + "-" + control.getBuildUnit() + "-" + control.getBuildRoom() + "-" + control.getCustomerName() + "-" + control.getCustomerPhone() + "，质检员（" + pqcName+ "-" +pqcPhone+ "），已产生复检单，请及时登录APP查看详情。	";



		return  saveAppMsg(content,recheckId,control.getItemManagerId(),false);


	}


	@Autowired
	private CheckConfirmService checkConfirmService;


	@Transactional(readOnly = false)
	public void handleEachCheckItems(List<InspectItem> inspectItems, HttpServletRequest request, String inspectBillId,String []photos) {


		Inspector inspector= SessionUtils.getInspectorSession(request);
		List<BalanceFine> fakuan = new ArrayList<BalanceFine>();
		Date date = new Date();
		List<InspectItem> itemList = new ArrayList<InspectItem>();

		String status = request.getParameter("status");

		Double totalScores = 0d;
		Double actualScores = 0d;


		Integer x = 0;

		Integer recheckId = null;


		List<Recheck>rechecks =new ArrayList<>();
		if (inspectItems.size() > 0) {
			Integer checkItemsSize = inspectItems.size();

			for (int v = 0; v < checkItemsSize; v++) {

				InspectItem item = inspectItems.get(v);

				if (null == item.getIsOk() || !"0".equals(item.getIsOk())) {


					item.setIsOk("1");


					item.setPreScores(item.getScores());
					item.setActualScores(item.getScores());
					item.setId(item.getItemId());

					itemList.add(item);


					actualScores =  PqcUtil.doubleAdd(actualScores, item.getScores());
					totalScores = PqcUtil.doubleAdd(totalScores, item.getScores());

				} else if ("0".equals(item.getIsOk())) {

					totalScores =  PqcUtil.doubleAdd(totalScores, item.getScores());



					if ("5".equals(status)) {
						if ("1".equals(item.getIsLimitDateChange())) {





							if (x != 1) {
								InspectKind inspectKind = dao.findOrderIdByBillId(item.getInspectBillId());

								recheckId = saveRecheck(inspectKind);

								x++;




								QualityControl control = dao.findMessageInfoByInspectId(inspector.getId());
								if(null!=control){
									saveMessageContent(control,inspector.getId(),inspector.getRealName(),inspector.getPhone());


									saveMsgContent(control,recheckId,inspector.getRealName(),inspector.getPhone());


								}



							}
						}






						if ("1".equals(item.getIsLimitDateChange())) {

							batchSaveRecheckList(item,recheckId,rechecks);
						}





						if ("1".equals(item.getIsPunishMoney())) {





							if (null != item.getActualPunishMoney() && Double.valueOf(item.getActualPunishMoney()) > 0) {

								if (null != item.getManagerId()) {

									BalanceFine bf = new BalanceFine();
									bf.setOrderId(item.getOrderId());

									bf.setManagerId(item.getManagerId());
									bf.setSettleAmount(-Double.valueOf(item.getActualPunishMoney()));
									bf.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_4);
									bf.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
									bf.setSettleStatusTime(date);
									bf.setRelatedBussinessId(item.getItemId());
									bf.setCreateBy(SessionUtils.getInspectorSession(request).getId());
									bf.setCreateDate(date);
									bf.setDelFlag("0");
									bf.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
									fakuan.add(bf);

								}

							}



							if (null != item.getInspectorMoney() && Double.valueOf(item.getInspectorMoney()) > 0) {
								if (null != item.getPqcId()) {

									BalanceFine bf = new BalanceFine();
									bf.setOrderId(item.getOrderId());

									bf.setManagerId(item.getPqcId());
									bf.setSettleAmount(-Double.valueOf(item.getInspectorMoney()));
									bf.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_4);
									bf.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
									bf.setSettleStatusTime(date);
									bf.setRelatedBussinessId(item.getItemId());
									bf.setCreateBy(SessionUtils.getInspectorSession(request).getId());
									bf.setCreateDate(date);
									bf.setDelFlag("0");
									bf.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
									fakuan.add(bf);

								}


							}


						}
					}



				}

			}


		}


		if (itemList.size() > 0) {

			dao.updateCheckItemAll(itemList);
		}
		if(rechecks.size()>0){

			dao.batchSaveRecheckCheckItem(rechecks);
		}

		if(fakuan.size()>0){


			checkItemService.saveSettleFineRecordAll(fakuan);
		}




		InspectKind kind = new InspectKind();


		kind.setInspectBillId(Integer.parseInt(inspectBillId));

		kind.setActualCheckPersonId(inspector.getId());

		kind.setCheckDate(date);

		kind.setTotalScores(totalScores);

		kind.setActualScores(actualScores);

		kind.setStatus(status);


		dao.updateInspect(kind);





		if (photos != null) {
			if (photos.length > 0) {

				checkConfirmService.savePicAll(photos, request, Integer.parseInt(inspectBillId), "2");
			}
		}

	}


	@Autowired
	private PurchaseCodeUtils codeUtils;





























































	public void batchSaveRecheckList(   InspectItem item,Integer recheckId,List<Recheck> rechecks){

		Recheck recheck = new Recheck();

		recheck.setRelatedBillId(recheckId);

		recheck.setRelatedCheckItemId(item.getId());
		recheck.setCheckItemId(item.getCheckItemId());

		recheck.setIsOk("0");

		recheck.setPreScores(item.getPreScores());

		recheck.setActualScores(0d);

		recheck.setChangeWay(item.getLimitChangeWay());

		rechecks.add(recheck);

	}





	public  boolean saveMessage(String messageContent, Integer receiveEmployeeId, String receivePhone, Integer relatedBusinessId) {

		PhoneMessageEntity entity = new PhoneMessageEntity();

		entity.setMessageGenerateTime(new Date());
		entity.setMessageContent(messageContent);
		entity.setReceiveEmployeeId(receiveEmployeeId);
		entity.setReceivePhone(receivePhone);
		entity.setStatus("0");
		entity.setRelatedBusinessType("600201");
		entity.setRelatedBusinessId(relatedBusinessId);
		try {
			messageDao.saveMessageContent(entity);
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}


		return true;
	}



	public  boolean saveAppMsg(String msgContent, Integer businessId, Integer employeeId, boolean checkDiff) {


		BizMsg bizMsg = new BizMsg();


		bizMsg.setMsgTitle("质检员产生复检单");
		bizMsg.setMsgTime(new Date());
		bizMsg.setMsgContent(msgContent);
		bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);

		bizMsg.setBusiType(checkDiff ? MessagePushType.MESSAGE_PUSH_TYPE_103001001 : MessagePushType.MESSAGE_PUSH_TYPE_103001002);
		bizMsg.setBusiIdInt(businessId);
		bizMsg.setEmployeeId(employeeId);
		try {

			bizProjectChangeBillService.saveBizMsg(bizMsg);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;


	}


	@Transactional(readOnly = false)
	public String  qcBillCode(){

		return codeUtils.qcBillCode();
	}




	public Integer findIsExistSelectQcBillById(String qcBillId){



		return dao.findIsExistSelectQcBillById(qcBillId);
	}






	@Transactional(readOnly = false)
	public void saveItems(String orderId,String qcBillId,  Model model, HttpServletRequest request) {
		Date date = new Date();

		BizQcBill bill = this.dao.findSelectCheckIsExist(Integer.parseInt(orderId));
		Order order = dao.queryOrderid(Integer.parseInt(orderId));
		if (null != bill) {

			qcBillId = String.valueOf(bill.getQcBillId());




			this.dao.deleteCheckItemsByCheckId(Integer.parseInt(qcBillId));
			logger.info("抽检单 : id " + qcBillId + "执行了更改检查项操作 ,添加新的检查项, 删除旧的检查项" + " 订单id : " + orderId + "当前日期 :" + DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss"));

		}



		List<ReportCheckDetailsPic> picList = this.dao.findPic(Integer.parseInt(qcBillId));
		int picLength = 0;
		if (picList.size() > 0) {
			picLength = picList.size();
		} else {
			picLength = 0;
		}


		Integer signNum = this.dao.findSign(Integer.valueOf(qcBillId));

		String[] checkItemId = request.getParameterValues("checkItemId");
		String[] checkItemName = request.getParameterValues("checkItemName");


		List<InspectItem> list = new ArrayList<>();

		int inspectId = 0;
		if (qcBillId.equals("0")) {

			BizQcBill bizQcBill = new BizQcBill();
			bizQcBill.setQcBillCode(qcBillCode());
			bizQcBill.setQcBillType("2");
			bizQcBill.setIsRecheck("0");
			bizQcBill.setOrderId(Integer.valueOf(orderId));
			bizQcBill.setStatus("-1");
			bizQcBill.setCreateDate(date);
			bizQcBill.setUpdateDate(date);
			this.dao.saveBizQcBill(bizQcBill);
			inspectId = bizQcBill.getQcBillId();
		} else {
			inspectId = Integer.valueOf(qcBillId);
		}

		if (null != checkItemId && checkItemId.length > 0) {

			for (int v = 0; v < checkItemId.length; v++) {
				if (!checkItemId[v].equals("build")) {
					InspectItem item = new InspectItem();

					item.setInspectBillId(inspectId);


					item.setCheckItemName(checkItemName[v]);

					item.setCheckItemId(Integer.parseInt(checkItemId[v]));
					item.setCreateDate(date);
					item.setUpdateDate(date);
					item.setDelFlag("0");
					list.add(item);


				}

			}


			saveItemsAll(list);
		}

		model.addAttribute("list", list);
		model.addAttribute("inspectId", inspectId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("projectMode", order.getProjectMode());
		model.addAttribute("picList", picList);
		model.addAttribute("picLength", picLength);
		try {
			model.addAttribute("baseUrl", PicRootName.picPrefixName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("signNum", signNum);



		List<InspectItem> workerManagerInspectorPackageInfoByOrderId = this.dao.findWorkerManagerInspectorPackageInfoByOrderId(inspectId);

		if (workerManagerInspectorPackageInfoByOrderId != null && workerManagerInspectorPackageInfoByOrderId.size() > 0) {
			model.addAttribute("infoList", workerManagerInspectorPackageInfoByOrderId);
			model.addAttribute("managerId", workerManagerInspectorPackageInfoByOrderId.get(0).getManagerId());
			model.addAttribute("managerName", workerManagerInspectorPackageInfoByOrderId.get(0).getManagerName());
			model.addAttribute("inspectorId", workerManagerInspectorPackageInfoByOrderId.get(0).getInspectorId());
		} else {
			QualityControl qualityControl = findMessageInfoByInspectId(inspectId);
			QualityControl findMessageInfoByInspectId2 = this.dao.findMessageInfoByInspectId2(inspectId);
			if (null != qualityControl) {
				model.addAttribute("managerId", qualityControl.getItemManagerId());
				model.addAttribute("managerName", qualityControl.getItemManager());
			}
			if (null != findMessageInfoByInspectId2) {
				model.addAttribute("inspectorId", findMessageInfoByInspectId2.getOrderInspectorId());
			}
		}
	}


	@Transactional(readOnly = false)
	public void batchDeleteQcBillCheckItemBreak(int checkItemId) {
		dao.batchDeleteQcBillCheckItemBreak(checkItemId);
	}



}
