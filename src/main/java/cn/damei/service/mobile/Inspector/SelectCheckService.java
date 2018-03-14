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

/**
 * 质检端 抽检
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class SelectCheckService extends CrudService2<SelectCheckDao, Order> {

	@Autowired
	private PhoneMessageDao messageDao;
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	@Autowired
	private CheckItemService checkItemService;
	/**
	 * 通过质检员id查询订单
	 * 
	 * @param order
	 * @return
	 */
	public List<Order> findOrderByInspectorId(Order order) {
		return dao.findOrderByInspectorId(order);
	}

	/**
	 * 根据订单id查询抽检单
	 * 
	 * @param orderId
	 * @return
	 */
	public BizQcBill findBizQcBillByOrderId(Integer orderId) {
		return dao.findBizQcBillByOrderId(orderId);
	}

	/**
	 * 根据抽检单id查询是否有暂存记录
	 * 
	 * @param qcBillId
	 * @return
	 */
	public List<InspectItem> findZanCun(int qcBillId) {
		return dao.findZanCun(qcBillId);
	}

	/**
	 * 根据订单id查询订单所在门店
	 * 
	 * @param orderId
	 * @return
	 */
	public Integer findStoreByOrderId(Integer orderId) {
		return dao.findStoreByOrderId(orderId);
	}

	/**
	 * 查询检查分类和检查项
	 * 
	 * @param storeId
	 * @return
	 */
	public List<InspectKind> selectAllCheckItem(Integer storeId) {
		return dao.selectAllCheckItem(storeId);
	}

	/**
	 * 根据抽检单id查询图片
	 * 
	 * @param qcBillId
	 * @return
	 */
	public List<ReportCheckDetailsPic> findPic(int qcBillId) {
		return dao.findPic(qcBillId);
	}

	/**
	 * 根据抽检单id查询是否有签到信息
	 * 
	 * @param qcBillId
	 * @return
	 */
	public Integer findSign(Integer qcBillId) {
		return dao.findSign(qcBillId);
	}

	/**
	 * 根据抽检单id 删除之前所选的检查项
	 * 
	 * @param qcBillId
	 */
	@Transactional(readOnly = false)
	public void deleteCheckItemsByCheckId(int qcBillId) {
		dao.deleteCheckItemsByCheckId(qcBillId);
	}

	/**
	 * 创建抽检单
	 * 
	 * @param bizQcBill
	 */
	@Transactional(readOnly = false)
	public Integer saveBizQcBill(BizQcBill bizQcBill) {
		return dao.saveBizQcBill(bizQcBill);
	}

	/**
	 * 保存选中的检查项
	 * 
	 * @param item
	 */
	@Transactional(readOnly = false)
	public void saveItems(InspectItem item) {
		dao.saveItems(item);
	}

	/**
	 * 查询已经选择过的检查项
	 * 
	 * @param inspectId
	 * @return
	 */
	public List<InspectKind> changeCheckItem(int inspectId) {
		return dao.changeCheckItem(inspectId);
	}

	/**
	 * 通过检查项id查询违规形式
	 * 
	 * @param checkItemId
	 * @return
	 */
	public List<IllegalModality> findIllegalModalityByCheckItemId(int checkItemId) {
		return dao.findIllegalModalityByCheckItemId(checkItemId);
	}

	/**
	 * 对应的违规形式id,根据id查询检查项id ,所占分数,是否有备注
	 * 
	 * @param id
	 * @return
	 */
	public InspectItem selectCheckItemInfoByIllegalModalityId(int id) {
		return dao.selectCheckItemInfoByIllegalModalityId(id);
	}

	/**
	 * 保存检查项纪录表
	 * 
	 * @param item
	 */
	@Transactional(readOnly = false)
	public void updateCheckItem(InspectItem item) {
		dao.updateCheckItem(item);
	}

	/**
	 * parameter: 检查项id 和质检单id 查询需要保存的违规形式的检查项外键
	 * 
	 * @param item
	 * @return
	 */
	public Integer selectCheckItemId(InspectItem item) {
		return dao.selectCheckItemId(item);
	}

	/**
	 * 保存违规形式纪录表
	 * 
	 * @param modality
	 */
	@Transactional(readOnly = false)
	public void saveIllegalModality(IllegalModality modality) {
		dao.saveIllegalModality(modality);
	}

	/**
	 * 根据检查项id 查询是否有记录 如果有 ,为不合格, 无为合格
	 * 
	 * @param map
	 * @return
	 */
	public	List<InspectItem> selectScoresFromCheckItemRecord(Map<String, Object> map) {
		return dao.selectScoresFromCheckItemRecord(map);
	}

	/**
	 * 根据合格的检查项id查询分数
	 * 
	 * @param checkItemId
	 * @return
	 */
	public Double selectCheckItemScores(int checkItemId) {
		return dao.selectCheckItemScores(checkItemId);
	}

	/**
	 * 更新质检单
	 * 
	 * @param kind
	 */
	@Transactional(readOnly = false)
	public void updateInspect(InspectKind kind) {
		dao.updateInspect(kind);
	}


	/**
	 * 删除数据库图片
	 * 
	 * @param picId
	 */
	@Transactional(readOnly = false)
	public void deletePic(Integer picId) {
		dao.deletePic(picId);
	}

	/**
	 * 获取抽检单编码
	 * 
	 * @return
	 */
	public PurchaseTwoCode getCode() {
		return dao.getCode();
	}

	/**
	 * 更新抽检单编码
	 * 
	 * @param code
	 */
	@Transactional(readOnly = false)
	public void updateCode(PurchaseTwoCode code) {
		dao.updateCode(code);
	}

	/**
	 * 插入抽检单编码
	 * 
	 * @param purchaseObjVo
	 */
	@Transactional(readOnly = false)
	public void insertPurchase(PurchaseTwoCode purchaseObjVo) {
		dao.insertPurchase(purchaseObjVo);
	}

	/**
	 * 查询订单的字段
	 * 
	 * @param billId
	 * @return
	 */
	public InspectKind findOrderIdByBillId(Integer billId) {

		return dao.findOrderIdByBillId(billId);
	}

	/**
	 * 保存复检单
	 *
	 * @param inspectKind
	 */
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
	
	/**
	 * 保存复检单对应的不合格检查项
	 * @param recheck
	 */
	@Transactional(readOnly = false)
	public void saveRecheckCheckItem(Recheck recheck){
		
		
		dao.saveRecheckCheckItem(recheck);
	}

	/**
	 * 批量保存所有选中的检查项
	 * @param list
	 */
	@Transactional(readOnly = false)
	public void saveItemsAll(List<InspectItem> list) {
		dao.saveItemsAll(list);
	}

	/**
	 * 批量保存合格的检查项
	 * @param hege
	 */
	@Transactional(readOnly = false)
	public void updateCheckItemAll(List<InspectItem> hege) {
		dao.updateCheckItemAll(hege);
	}

	/**
	 * 批量保存不合格的检查项
	 * @param buhege
	 */
	@Transactional(readOnly = false)
	public void saveRecheckCheckItemAll(List<Recheck> buhege) {
		dao.saveRecheckCheckItemAll(buhege);
	}

	/**
	 * 批量保存违规形式纪录表
	 * @param list
	 */
	@Transactional(readOnly = false)
	public void saveIllegalModalityAll(List<IllegalModality> list) {
		dao.saveIllegalModalityAll(list);
	}

	/**
	 * 查询该订单最新一次抽检的时间是否间隔有5分钟
	 * @param orderId 
	 * @return
	 */
	public BizQcBill findTimeSpan(Integer orderId) {
		return dao.findTimeSpan(orderId);
	}
	//17-2-16 更新
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

	/**
	 * 质检领导查询订单列表
	 * @param or
	 * @return
	 */
	public List<Order> findOrderByLeaderInspectorId(Order or) {
		return dao.findOrderByLeaderInspectorId(or);
	}


	/**
	 * 检查抽检单是否存在, 做重复校验
	 * @param orderId
	 * @return
	 */
	public BizQcBill findSelectCheckIsExist(Integer orderId){

		return dao.findSelectCheckIsExist(orderId);
	}








	public boolean saveMessageContent( QualityControl control,Integer pqcId,String pqcName,String pqcPhone) {


		String content = "订单（" + control.getCommunityName() + "-" + control.getBuildNumber() + "-" + control.getBuildUnit() + "-" + control.getBuildRoom() + "-" + control.getCustomerName() + "-" + control.getCustomerPhone() + "，质检员（" + pqcName + "-" +pqcPhone + "），已产生复检单，请及时登录APP查看详情。	";

		return saveMessage(content,control.getItemManagerId(),control.getPhone(),pqcId);
	}



	public boolean saveMsgContent(  QualityControl control,Integer recheckId,String pqcName,String pqcPhone){

		String content = "订单（" + control.getCommunityName() + "-" + control.getBuildNumber() + "-" + control.getBuildUnit() + "-" + control.getBuildRoom() + "-" + control.getCustomerName() + "-" + control.getCustomerPhone() + "，质检员（" + pqcName+ "-" +pqcPhone+ "），已产生复检单，请及时登录APP查看详情。	";

		//=====================================消息推送start========================================================

		return  saveAppMsg(content,recheckId,control.getItemManagerId(),false);
		//=====================================消息推送end========================================================

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
		// 检查项的各项分数总分, 保存在质检单中
		Double totalScores = 0d;
		Double actualScores = 0d;

		//复检单变量
		Integer x = 0;
		//复检单返回id
		Integer recheckId = null;


		List<Recheck>rechecks =new ArrayList<>();
		if (inspectItems.size() > 0) {
			Integer checkItemsSize = inspectItems.size();

			for (int v = 0; v < checkItemsSize; v++) {

				InspectItem item = inspectItems.get(v);

				if (null == item.getIsOk() || !"0".equals(item.getIsOk())) {
					// 2:如果是合格的, 保存到检查项记录表中
					// 合格
					item.setIsOk("1");

					// 该检查项不扣分
					item.setPreScores(item.getScores());
					item.setActualScores(item.getScores());
					item.setId(item.getItemId());
					// 保存合格的检查项
					itemList.add(item);

					// 合格的实际分数
					actualScores =  PqcUtil.doubleAdd(actualScores, item.getScores());
					totalScores = PqcUtil.doubleAdd(totalScores, item.getScores());

				} else if ("0".equals(item.getIsOk())) {
					// 3:不合格,总分相加, 实际得分没有
					totalScores =  PqcUtil.doubleAdd(totalScores, item.getScores());

					// 不合格, 要看是否为限期整改
					// 如果不是暂存
					if ("5".equals(status)) {
						if ("1".equals(item.getIsLimitDateChange())) {
							// 如果是限期整改 , 插入质检单 ,保存不合格的检查项, 作为复检内容
							// 需要保存的数据有,biz_qc_bill : code,type isRecheck
							// relatedBillId
							// , status
							// 只保存一条复检单数据
							if (x != 1) {
								InspectKind inspectKind = dao.findOrderIdByBillId(item.getInspectBillId());

								recheckId = saveRecheck(inspectKind);

								x++;

								// 保存发送短信内容
								// 订单（东晨小区-10-4-202-王维-13333333333），质检员（王毅-13212341234），已产生复检单，请及时登录APP查看详情。
								//发给项目经理, 短信内质检员
								QualityControl control = dao.findMessageInfoByInspectId(inspector.getId());
								if(null!=control){
									saveMessageContent(control,inspector.getId(),inspector.getRealName(),inspector.getPhone());


									saveMsgContent(control,recheckId,inspector.getRealName(),inspector.getPhone());


								}



							}
						}

						// 在保存该复检单的不合格检查项
						// 检查项表: biz_qc_bill_check_item : billId ,
						// relatedCheckItemId ,checkItemId ,isOk PreScores
						// ,actualScore
						// changeWay
						if ("1".equals(item.getIsLimitDateChange())) {

							batchSaveRecheckList(item,recheckId,rechecks);
						}


						// 产生结算类目明细 罚款

						// 如果有罚款
						if ("1".equals(item.getIsPunishMoney())) {



							//经理罚款

							if (null != item.getActualPunishMoney() && Double.valueOf(item.getActualPunishMoney()) > 0) {

								if (null != item.getManagerId()) {

									BalanceFine bf = new BalanceFine();
									bf.setOrderId(item.getOrderId());
									//罚款人id
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


							//质检员罚款
							if (null != item.getInspectorMoney() && Double.valueOf(item.getInspectorMoney()) > 0) {
								if (null != item.getPqcId()) {

									BalanceFine bf = new BalanceFine();
									bf.setOrderId(item.getOrderId());
									//罚款人id
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
			//批量更新检查项的状态
			dao.updateCheckItemAll(itemList);
		}
		if(rechecks.size()>0){
			//批量插入复检项
			dao.batchSaveRecheckCheckItem(rechecks);
		}

		if(fakuan.size()>0){

			//批量保存罚款明细
			checkItemService.saveSettleFineRecordAll(fakuan);
		}

		//=====================================更新质检单=======================================================

		//1:根据质检单id查询检查项记录表的得分情况
		InspectKind kind = new InspectKind();

		//质检单id
		kind.setInspectBillId(Integer.parseInt(inspectBillId));
		//实际质检人id
		kind.setActualCheckPersonId(inspector.getId());
		//质检时间
		kind.setCheckDate(date);
		//总分
		kind.setTotalScores(totalScores);
		//实际得分
		kind.setActualScores(actualScores);
		//暂存还是提交报告
		kind.setStatus(status);//0或者5

		//2:   更新抽检单
		dao.updateInspect(kind);




		//图片路径
		if (photos != null) {
			if (photos.length > 0) {
				//批量插入抽检图片
				checkConfirmService.savePicAll(photos, request, Integer.parseInt(inspectBillId), "2");
			}
		}

	}


	@Autowired
	private PurchaseCodeUtils codeUtils;

//	//抽检单编码
//	@Transactional(readOnly = false)
//	public String  qcBillCode(){
//		//以PO开头
//		String purchaseCode = "ZJ";
//
//Date date =new Date();
//		StringBuilder builder = new StringBuilder();
//
//		//num和date
//		//获取抽检单编码
//		PurchaseTwoCode purchaseObj = dao.getCode();
//		String format ="";
//		String code ="";
//
//		if(purchaseObj!=null){
//			//流水号+1
//			purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode())+1));
//			//更新抽检单编码数据库
//			dao.updateCode(purchaseObj);
//			//格式后的时间戳
//			format = new SimpleDateFormat("yyyyMMdd").format(date);
//			//得到的流水号
//			code = purchaseObj.getPurchaseCode();
//		}else{
//			PurchaseTwoCode purchaseObjVo = new PurchaseTwoCode();
//			//流水号+1
//			purchaseObjVo.setId(3);
//			purchaseObjVo.setPurchaseCode(String.valueOf(1));
//			purchaseObjVo.setAuxiliaryDate(new Date());
//			//插入抽检单编码数据库
//			dao.insertPurchase(purchaseObjVo);
//			//格式后的时间戳
//			format = new SimpleDateFormat("yyyyMMdd").format(new Date());
//			//得到的流水号
//			code = "1";
//		}
//
//
//
//		builder.append(purchaseCode).append(format);
//		//判断长度
//		if(code.length()==1){
//
//			builder.append("000").append(code);
//
//		}else if(code.length()==2){
//			//拼接采购单编号
//			builder.append("00").append(code);
//		}else if(code.length()==3){
//			builder.append("0").append(code);
//		}else if(code.length()>=4){
//			builder.append(code);
//		}
//
//		//返回采购单编号
//		return builder.toString();
//	}


	public void batchSaveRecheckList(   InspectItem item,Integer recheckId,List<Recheck> rechecks){

		Recheck recheck = new Recheck();
		// 检查项相关联的复检单id
		recheck.setRelatedBillId(recheckId);
		// 不合格的检查项id
		recheck.setRelatedCheckItemId(item.getId());
		recheck.setCheckItemId(item.getCheckItemId());
		// 肯定不合格
		recheck.setIsOk("0");
		// 得分
		recheck.setPreScores(item.getPreScores());
		// 实际得分
		recheck.setActualScores(0d);
		// 线上线下
		recheck.setChangeWay(item.getLimitChangeWay());

		rechecks.add(recheck);

	}




	/**
	 * 质检端复检短信保存(待修改)
	 *
	 * @param messageContent
	 * @param receiveEmployeeId
	 * @param receivePhone
	 * @param relatedBusinessId
	 * @return
	 */
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


	/**
	 * 质检端抽约检站内消息推送(待修改)
	 *
	 * @param msgContent
	 * @param businessId
	 * @param employeeId
	 * @return
	 */
	public  boolean saveAppMsg(String msgContent, Integer businessId, Integer employeeId, boolean checkDiff) {


		BizMsg bizMsg = new BizMsg();


		bizMsg.setMsgTitle("质检员产生复检单");
		bizMsg.setMsgTime(new Date());
		bizMsg.setMsgContent(msgContent);
		bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
		//约检为true  抽检为false
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



	/**
	 * 根据抽检单id查询是否存在
	 * @param qcBillId
	 * @return
	 */
	public Integer findIsExistSelectQcBillById(String qcBillId){



		return dao.findIsExistSelectQcBillById(qcBillId);
	}






	@Transactional(readOnly = false)
	public void saveItems(String orderId,String qcBillId,  Model model, HttpServletRequest request) {
		Date date = new Date();
		//查询没有提交的抽检单
		BizQcBill bill = this.dao.findSelectCheckIsExist(Integer.parseInt(orderId));
		Order order = dao.queryOrderid(Integer.parseInt(orderId));
		if (null != bill) {

			qcBillId = String.valueOf(bill.getQcBillId());

			// 根据质检单id ,查询 选择检查项的表
			// 如果有记录, 那么是之前选择的,要全删除
			// 如果没有记录, 表示第一次选择
			this.dao.deleteCheckItemsByCheckId(Integer.parseInt(qcBillId));
			logger.info("抽检单 : id " + qcBillId + "执行了更改检查项操作 ,添加新的检查项, 删除旧的检查项" + " 订单id : " + orderId + "当前日期 :" + DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss"));

		}


		//查询是否有图片
		List<ReportCheckDetailsPic> picList = this.dao.findPic(Integer.parseInt(qcBillId));
		int picLength = 0;
		if (picList.size() > 0) {
			picLength = picList.size();
		} else {
			picLength = 0;
		}

		//根据抽检单id查询是否有签到信息
		Integer signNum = this.dao.findSign(Integer.valueOf(qcBillId));

		String[] checkItemId = request.getParameterValues("checkItemId");
		String[] checkItemName = request.getParameterValues("checkItemName");

		// 扭转到下一个页面的参数
		List<InspectItem> list = new ArrayList<>();

		int inspectId = 0;
		if (qcBillId.equals("0")) {
			//没有抽检单 创建一个抽检单
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
					// 检查项对应的质检单
					item.setInspectBillId(inspectId);

					// 检查项名称不保存,作为下一页面的使用
					item.setCheckItemName(checkItemName[v]);
					// 对应biz_qc_check_item表中的 检查项id
					item.setCheckItemId(Integer.parseInt(checkItemId[v]));
					item.setCreateDate(date);
					item.setUpdateDate(date);
					item.setDelFlag("0");
					list.add(item);
					// 保存所有选中的检查项
//					selectCheckService.saveItems(item);
				}

			}

			//批量保存所有选中的检查项
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

		//17-2-16  不合格弹出层更新  显示 质检员+项目经理+工人组长+任务包
		//根据 质检单中的订单id  查询  该订单的已分配的任务包和工人组信息(工人组id+组长姓名) + 质检员id+项目经理id+名称
		List<InspectItem> workerManagerInspectorPackageInfoByOrderId = this.dao.findWorkerManagerInspectorPackageInfoByOrderId(inspectId);
		//工人组+任务包+经理id+质检员id
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

	/**
	 * 删除之前的该检查项的违规形式
	 * @param
	 */
	@Transactional(readOnly = false)
	public void batchDeleteQcBillCheckItemBreak(int checkItemId) {
		dao.batchDeleteQcBillCheckItemBreak(checkItemId);
	}



}
