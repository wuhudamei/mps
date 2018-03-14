package cn.damei.service.mobile.Manager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.damei.common.constantUtils.*;
import cn.damei.common.utils.*;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.*;
import cn.damei.service.modules.BizBusinessStatusLogService;
import cn.damei.dao.modules.BizMainMaterialsUnqualifiedReasonDao;
import cn.damei.entity.modules.BizMainMaterialsUnqualifiedReason;
import cn.damei.dao.modules.BizSupplierInstallBillDao;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.dao.modules.BizSupplierInstallConstructBillDao;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.servlet.SendPostUtils;
import cn.damei.common.MD5Utils;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.dao.mobile.Manager.OrderInstallPlanDao;

import javax.servlet.http.HttpServletRequest;


@Service
@Transactional(readOnly = true)
public class OrderInstallPlanService extends CrudService2<OrderInstallPlanDao, OrderInstallPlan> {

	@Autowired
	private NewEnginInstallService newEnginInstallService;
	@Autowired
	private OrderInstallPlanPicService orderInstallPlanPicService;
	@Autowired
	private BizSupplierInstallBillDao bizSupplierInstallBillDao;
	@Autowired
	private BizSupplierInstallConstructBillDao bizSupplierInstallConstructBillDao;
	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	@Autowired
	private BizMainMaterialsUnqualifiedReasonDao bizMainMaterialsUnqualifiedReasonDao;


	@Transactional(readOnly = true)
	public OrderInstallPlan getById(Integer id) {
		return dao.getById(id);
	}


	public List<EnginInstall> queryInstallAcceptOrderList(Integer managerId, String text) {
		EnginInstall enginInstall = new EnginInstall();
		enginInstall.setManagerId(managerId);
		enginInstall.setDelFlag(OrderConstantUtil.ORDER_DEL_FLAG_NO_0);
		enginInstall.setText(text);
		return dao.queryInstallAcceptOrderList(enginInstall);
	}



	public List<OrderInstallPlan> queryOrderInstallAcceptList(Integer id) {
		return dao.queryOrderInstallAcceptList(id);
	}



	public List<OrderInstallPlan> queryOrderInstallAcceptDetailList(Integer id) {
		return dao.queryOrderInstallAcceptDetailList(id);
	}




	public EnginInstall querySupplierInstallBillMessage(Integer id) {
		return dao.querySupplierInstallBillMessage(id);
	}



	@Transactional(readOnly = false)
	public String acceptanceSubmit(OrderInstallPlan orderInstallPlan, String realAcceptDateString, String realIntoDateString, String realCompleteDateString, String[] photo, HttpServletRequest request) {
		String result = "0";
		try {
			String isQualified = orderInstallPlan.getIsQualified();
			String installMode = orderInstallPlan.getInstallMode();
			Integer orderInstallPlanId = orderInstallPlan.getId();
			Integer orderId = orderInstallPlan.getOrderId();


			OrderInstallPlan orderInstallPlanOld = dao.getById(orderInstallPlanId);
			if(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_4.equals(orderInstallPlanOld.getStatus())){
				result = "1";
				return result;
			}

			Integer count = dao.queryOrderInstallPlanAcceptLog(orderInstallPlanId);
			if(null != count && count > 0){
				result = "2";
				return result;
			}


			if(InstallPlanConstantUtil.INSTALL_IS_QUALIFIED_0.equals(isQualified)){
				if(null != orderInstallPlan.getUnqualifiedReasonId()){
                    BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason = bizMainMaterialsUnqualifiedReasonDao.get(orderInstallPlan.getUnqualifiedReasonId());
                    orderInstallPlan.setUnqualifiedReason(bizMainMaterialsUnqualifiedReason.getUnqualifiedReason());
                }
			}

			boolean flag = updateOrderInstallPlanAccept(orderInstallPlan,realAcceptDateString,realIntoDateString,
					realCompleteDateString,orderInstallPlanOld.getUnqualifiedTimes());
			if(!flag){
				result = "3";
				return result;
			}


			Manager manager = (Manager) request.getSession().getAttribute("manager");
			Integer orderInstallPlanAcceptLogId = saveOrderInstallPlanAcceptLog(orderId,orderInstallPlanId,isQualified,
					orderInstallPlanOld.getOrderInstallItemId(),orderInstallPlanOld.getInstallItemName(),
					orderInstallPlan.getUnqualifiedReasonId(),orderInstallPlan.getUnqualifiedReason(),orderInstallPlan.getUnqualifiedRemarks(),manager.getId());


			if(InstallPlanConstantUtil.INSTALL_IS_QUALIFIED_1.equals(isQualified) && InstallPlanConstantUtil.INSTALL_MODE_1.equals(installMode)){
				saveSupplierInstallBill(orderInstallPlanId,manager);
			}

			if(InstallPlanConstantUtil.INSTALL_MODE_2.equals(installMode)){
				try {
					insertsyndata(String.valueOf(orderId), String.valueOf(orderInstallPlanId), manager, ConstantUtils.BUSINESS_TYPE_401);
				}catch (Exception e) {
					logger.info("-------------------------主材信息推送到订单流转系统：失败!--------------------------------");
				}
			}


			if(InstallPlanConstantUtil.INSTALL_IS_QUALIFIED_1.equals(isQualified)){

				try {
					orderInstallPlanPicService.deletePic(orderInstallPlan.getId());
					orderInstallPlanPicService.saveInstallPlanPicBatch(orderInstallPlanId,photo,request);
				} catch (IOException e) {
					e.printStackTrace();
					result = "20";
					return result;
				}
			}else{

				newEnginInstallService.saveProblemPic(orderInstallPlanAcceptLogId, PictureTypeContantUtil.PICTURE_TYPE_2076, photo,
						PicturePathContantUtil.UPLOAD_INSTALL_ACCEPTANCE_SUBMIT_PATH, request);
			}

		}catch (NumberFormatException e) {
			result = "20";
			e.printStackTrace();
		}
		return result;
	}



	@Transactional(readOnly = false)
	private boolean updateOrderInstallPlanAccept(OrderInstallPlan orderInstallPlan, String realAcceptDateString, String realIntoDateString, String realCompleteDateString, int unqualifiedTimes) {

		String isQualified = orderInstallPlan.getIsQualified();

		String installMode = orderInstallPlan.getInstallMode();

		OrderInstallPlan plan = new OrderInstallPlan();

		plan.setId(orderInstallPlan.getId());

		plan.setIsQualified(isQualified);
		if(InstallPlanConstantUtil.INSTALL_IS_QUALIFIED_1.equals(isQualified)){

			plan.setStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_4);

			if(StringUtils.isNotBlank(realAcceptDateString)){
				plan.setRealAcceptDate(DateUtils.parseDate(realAcceptDateString));
			}else{
				plan.setRealAcceptDate(new Date());
			}

			plan.setIsCompleteDelay(orderInstallPlan.getIsCompleteDelay());

			plan.setDelayDays(orderInstallPlan.getDelayDays());

			plan.setCompleteDelayReason(orderInstallPlan.getCompleteDelayReason());

			plan.setCompleteDelayRemarks(orderInstallPlan.getCompleteDelayRemarks());


			if(InstallPlanConstantUtil.INSTALL_MODE_2.equals(installMode)){

				DateUtils.parseDate(realIntoDateString);
				if(StringUtils.isNotBlank(realIntoDateString)){
					plan.setRealIntoDate(DateUtils.parseDate(realIntoDateString));
				}

				if(StringUtils.isNotBlank(realCompleteDateString)){
					plan.setRealCompleteDate(DateUtils.parseDate(realCompleteDateString));
				}
			}

			plan.setUnqualifiedTimes(unqualifiedTimes);

			BigDecimal b1 = new BigDecimal(Double.toString(1));
			BigDecimal b2 = new BigDecimal(Double.toString(unqualifiedTimes+1));
			double passRate = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
			plan.setFirstPassRate(passRate);

		}else{

			plan.setStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_401);

			plan.setUnqualifiedAcceptTime(new Date());

			plan.setUnqualifiedReason(orderInstallPlan.getUnqualifiedReason());

			plan.setUnqualifiedRemarks(orderInstallPlan.getUnqualifiedRemarks());

			plan.setUnqualifiedTimes(unqualifiedTimes+1);

		}
		plan.preUpdate();

		return (dao.updateOrderInstallPlan(plan))?true:false;
	}


	@Transactional(readOnly = false)
	private Integer saveOrderInstallPlanAcceptLog(Integer orderId, Integer orderInstallPlanId, String isQualified,
												  Integer orderInstallIteamId, String orderInstallIteam,
												  Integer unqualifiedReasonId,String unqualifiedReason, String acceptRemarks,
												  Integer operaterId) {

		OrderInstallPlanAcceptLog orderInstallPlanAcceptLog = new OrderInstallPlanAcceptLog();


		orderInstallPlanAcceptLog.setOrderId(orderId);

		orderInstallPlanAcceptLog.setOrderInstallPlanId(orderInstallPlanId);

		if (InstallPlanConstantUtil.INSTALL_IS_QUALIFIED_1.equals(isQualified)) {

			orderInstallPlanAcceptLog.setAcceptType(InstallPlanConstantUtil.INSTALL_ACCEPT_TYPE_1);
			orderInstallPlanAcceptLog.setRemarks(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_4);
		}else{

			orderInstallPlanAcceptLog.setAcceptType(InstallPlanConstantUtil.INSTALL_ACCEPT_TYPE_2);
			orderInstallPlanAcceptLog.setRemarks(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_401);



			orderInstallPlanAcceptLog.setUnqualifiedReasonId(unqualifiedReasonId);

			orderInstallPlanAcceptLog.setUnqualifiedReasonConfigure(unqualifiedReason);

			orderInstallPlanAcceptLog.setAcceptRemarks(acceptRemarks);
		}

		orderInstallPlanAcceptLog.setOrderInstallIteamId(orderInstallIteamId);

		orderInstallPlanAcceptLog.setOrderInstallIteam(orderInstallIteam);

		orderInstallPlanAcceptLog.setOperaterId(operaterId);
		orderInstallPlanAcceptLog.preInsert();
		dao.saveOrderInstallPlanAcceptLog(orderInstallPlanAcceptLog);
		return orderInstallPlanAcceptLog.getId();
	}



	@Transactional(readOnly = false)
	private void saveSupplierInstallBill(Integer orderInstallPlanId, Manager manager) {
		try {

			BizSupplierInstallBill entity = new BizSupplierInstallBill();
			entity.setOrderInstallPlanId(orderInstallPlanId);
			BizSupplierInstallBill bizSupplierInstallBill = bizSupplierInstallBillDao.getnot90(entity);
			if (bizSupplierInstallBill != null) {

				updateSupplierInstallBill(bizSupplierInstallBill.getId(), InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_50);

				bizBusinessStatusLogService.saveBusinessStatusLog(manager.getId(), bizSupplierInstallBill.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9011, InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_50, InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_50);

				BizSupplierInstallConstructBill bizSupplierInstallConstructBill = new BizSupplierInstallConstructBill();
				bizSupplierInstallConstructBill.setSupplierInstallBillId(bizSupplierInstallBill.getId());
				BizSupplierInstallConstructBill installItem = bizSupplierInstallConstructBillDao.getnot90(bizSupplierInstallConstructBill);
				if (installItem != null) {

					updateSupplierConstructBill(installItem.getId(), InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_50);

					bizBusinessStatusLogService.saveBusinessStatusLog(manager.getId(), installItem.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9012, InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_50, InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_NAME_50);
				}
			}
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}


	@Transactional(readOnly = false)
	private boolean updateSupplierInstallBill(Integer installBillId, String supplierInstallBillStatus) {

		BizSupplierInstallBill bizSupplierInstallBill = new BizSupplierInstallBill();
		Date date = new Date();

		bizSupplierInstallBill.setId(installBillId);

		bizSupplierInstallBill.setStatus(supplierInstallBillStatus);

		bizSupplierInstallBill.setStatusDatetime(date);

		bizSupplierInstallBill.setRealCompleteDate(date);
		bizSupplierInstallBill.preUpdate();

		return (dao.updateSupplierInstallBill(bizSupplierInstallBill)) ? true : false;
	}


	@Transactional(readOnly = false)
	private boolean updateSupplierConstructBill(Integer constructBillId, String supplierInstallConstructBillStatus) {

		BizSupplierInstallConstructBill bizSupplierInstallConstructBill = new BizSupplierInstallConstructBill();
		Date date = new Date();

		bizSupplierInstallConstructBill.setId(constructBillId);

		bizSupplierInstallConstructBill.setStatus(supplierInstallConstructBillStatus);

		bizSupplierInstallConstructBill.setStatusDatetime(date);

		bizSupplierInstallConstructBill.setRealAcceptDate(date);
		bizSupplierInstallConstructBill.preUpdate();

		return (dao.updateSupplierConstructBill(bizSupplierInstallConstructBill)) ? true : false;
	}



	@Transactional(readOnly = false)
	public void insertsyndata(String orderID, String id, Object manager, String businessType506) throws UnsupportedEncodingException {
		long checkInstallItem = 0l;
		if (!id.equals("99999")) {

			List<Integer> ItemId = dao.findInstallid(id);

			List<Integer> orderItemId = dao.findOrderInstallid(orderID);

			orderItemId.retainAll(ItemId);

			CheckInstallItem check = new CheckInstallItem();
			check.setOrderId(orderID);
			check.setOrderItemId(orderItemId);
			checkInstallItem = dao.isCheckInstallItem(check);
		}



		if (checkInstallItem == 0) {
			List<String> code = null;
			MainInterfaceEntity mft = new MainInterfaceEntity();
			if (!id.equals("99999")) {
				code = dao.findCategoryTowCode(id);
				Manager man = (Manager) manager;
				mft.setProjectManagerMobile(man.getPhone());
				mft.setProjectManagerName(man.getRealname());
				EnginInstall order = dao.findOrderDtails(id);
				mft.setMainInstallItemName(order.getRemarks());
				mft.setOrderNumber(order.getOrderNumber());

			} else {
				code = dao.findWallAndFloor(id);
				Inspector qpc = (Inspector) manager;
				mft.setProjectManagerMobile(qpc.getPhone());
				mft.setProjectManagerName(qpc.getRealName());
				mft.setOrderNumber(orderID);
				mft.setMainInstallItemName("墙地砖");
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			mft.setAcceptanceDate(date);
			String key = md5key(mft);
			mft.setKey(key);
			int length = code.toString().length();
			String substring = code.toString().substring(1, length - 1);
			mft.setSecondCateCodeList(substring);
			String string2 = JSONObject.fromObject(mft).toString();
			dao.saveJSONDate("[" + string2 + "]", businessType506, new Date());
		}

	}



	public List<OrderInstallPlanAcceptLog> queryAcceptUnqualifiedLog(Integer id) {
		return dao.queryAcceptUnqualifiedLog(id);
	}


	public List<ReportCheckDetailsPic> queryAcceptUnqualifiedPicList(Integer id, String businessType) {
		ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
		reportCheckDetailsPic.setBusinessIdInt(id);
		reportCheckDetailsPic.setBusinessType(businessType);
		return dao.queryAcceptUnqualifiedPicList(reportCheckDetailsPic);
	}


	public List<OrderInstallPlanPic> queryAcceptQualifiedPicList(Integer id) {
		return dao.queryAcceptQualifiedPicList(id);
	}


	public String md5key(Object object) throws UnsupportedEncodingException {

		String key = "7b5df6aq2we4r3t6y1vxnmhjklpewd23";



		JSONArray fromObject = JSONArray.fromObject(object);
		String string = fromObject.toString();


		String substring2 = string.substring(2, string.length() - 2);

		String[] split = substring2.split(",\"");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String replaceFirst = split[i].replaceFirst("\"", "");
			list.add(replaceFirst);
		}
		String[] targetArr = new String[list.size()];
		String[] array = list.toArray(targetArr);

		Arrays.sort(array);

		String count = "";
		for (String sp : array) {

			int lastIndexOf = sp.indexOf(':') + 2;
			String substring = sp.substring(lastIndexOf, sp.length() - 1);
			if (!substring.equals("") && substring != null) {
				count = count + substring;
			}
		}
		count = count + key;

		String md5 = MD5Utils.getMD5(count).toUpperCase();
		return md5;
	}



	public String sendNodePlan(String orderId) throws UnsupportedEncodingException {

		String agreeKey = "7b5df6aq2we4r3t6y1vxnmhjklpewd23";

		InterfaceOrder order = dao.findOrderDetail(orderId);


		List<InterfaceNodePlan> List = dao.findNodePlanDetail(orderId);

		JSONArray fromObject2 = JSONArray.fromObject(List);

		JSONObject fromObject = JSONObject.fromObject(order);

		String string = fromObject.toString();
		String substring2 = string.substring(2, string.length() - 1);

		String[] split = substring2.split(",\"");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String replaceFirst = split[i].replaceFirst("\"", "");
			list.add(replaceFirst);
		}
		String[] targetArr = new String[list.size()];
		String[] array = list.toArray(targetArr);

		Arrays.sort(array);

		String count = "";
		for (String sp : array) {


			int indexOf = sp.indexOf(':') + 2;

			String substring = sp.substring(indexOf, sp.length() - 1);
			if (!substring.equals("") && substring != null) {
				count = count + substring;
			}
		}
		count = count + agreeKey;

		String md5 = MD5Utils.getMD5(count).toUpperCase();
		fromObject.put("key", md5);


		Iterator keys = fromObject.keys();
		while (keys.hasNext()) {
			String key = keys.next() + "";
			String val = fromObject.get(key) + "";
			val = URLEncoder.encode(val, "UTF-8");
			fromObject.put(key, val);
		}



		String strget = URLEncoder.encode(fromObject2.toString(), "UTF-8");
		fromObject.put("scheduleNodeInfo", strget);

		String sum = "";
		Iterator keys3 = fromObject.keys();
		while (keys3.hasNext()) {
			String keyGET = keys3.next() + "";
			String val = fromObject.get(keyGET) + "";
			sum = keyGET + "=" + val + "&" + sum;
		}

		String url = "http://order.bj.mdni.cn/ScheduleNodeAPI/ScheduleNodeInsertInfo";
		String sendPost = SendPostUtils.sendPost(url, sum);
		String xmString = new String(sendPost.toString().getBytes("GBK"), "UTF-8");

		return xmString;

	}





	@Transactional(readOnly = false)
	public boolean saveInstallPlanList(List<OrderInstallItem> installItemList, String actualStartDate, Manager manager) {
		
		boolean installPlanFlag  = true;
		Date date = new Date();
		List<OrderInstallPlan> list = new ArrayList<OrderInstallPlan>();
		
		if(null != installItemList && installItemList.size() > 0){
			for(OrderInstallItem installItem : installItemList){
				
				OrderInstallPlan plan = new OrderInstallPlan();
				plan.setId(null);

				plan.setOrderId(installItem.getOrderId());

				plan.setOrderInstallItemId(installItem.getId());

				plan.setInstallItemName(installItem.getInstallItemName());

				plan.setInstallItemSequence(installItem.getInstallItemSequence());

				plan.setPlanIntoDate(DateUtils.addDate(DateUtils.parseDate(actualStartDate), installItem.getDaysToApplyInto()));

				plan.setApplyIntoDate(null);

				plan.setRealIntoDate(null);

				plan.setRealCompleteDate(null);

				plan.setRealAcceptDate(null);

				plan.setStatus("1");

				plan.setApplyIntoRemarks(null);

				plan.setIsCompleteDelay(null);

				plan.setCompleteDelayReason(null);

				plan.setCompleteDelayRemarks(null);

				plan.setRemarks(null);

				plan.setInstallMode(installItem.getInstallMode());

				plan.setPlanCompleteDate(DateUtils.addDate(DateUtils.parseDate(actualStartDate), installItem.getDaysPalnComplete()));

				if(InstallPlanConstantUtil.INSTALL_IS_TO_CHECKSIZE_1.equals(installItem.getIsToChecksize())){
					plan.setAllowApplyChecksizeDate(DateUtils.addDate(DateUtils.parseDate(actualStartDate), installItem.getDaysPlanChecksize()));
				}
				plan.setCreateByAuthor(manager.getId().toString());
				plan.setCreateDate(date);
				plan.setUpdateByAuthor(manager.getId().toString());
				plan.setUpdateDate(date);
				plan.setDelFlag("0");
				list.add(plan);
			}
			
			installPlanFlag = dao.saveInstallPlanList(list);
		}
		
		
		return installPlanFlag;
		
			
	}



}
