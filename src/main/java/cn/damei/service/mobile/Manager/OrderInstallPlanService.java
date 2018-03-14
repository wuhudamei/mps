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

/**
 * 工程安装
 * 
 * @author wyb
 */
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

	/**
	 * 安装项计划详情
	 *
	 * @param id
	 * @return bean
	 */
	@Transactional(readOnly = true)
	public OrderInstallPlan getById(Integer id) {
		return dao.getById(id);
	}

	/**
	 * 安装验收 订单列表
	 * @param managerId
	 * @param text
	 * @return
	 */
	public List<EnginInstall> queryInstallAcceptOrderList(Integer managerId, String text) {
		EnginInstall enginInstall = new EnginInstall();
		enginInstall.setManagerId(managerId);
		enginInstall.setDelFlag(OrderConstantUtil.ORDER_DEL_FLAG_NO_0);
		enginInstall.setText(text);
		return dao.queryInstallAcceptOrderList(enginInstall);
	}


	/**
	 * 主材安装验收列表页
	 * 安装项状态为：
	 * 	  安装模式-传统【3：已转给供应商】【401：验收不合格】
	 * 	  安装模式-传统【330：工人已申请完工】【401：验收不合格】
	 * @param id
	 * @return
	 */
	public List<OrderInstallPlan> queryOrderInstallAcceptList(Integer id) {
		return dao.queryOrderInstallAcceptList(id);
	}


	/**
	 * 主材安装验收明细列表页【4：已验收】
	 * @param id
	 * @return
	 */
	public List<OrderInstallPlan> queryOrderInstallAcceptDetailList(Integer id) {
		return dao.queryOrderInstallAcceptDetailList(id);
	}



	/**
	 * 查询该安装项【订单】【安装单】【施工单】信息
	 * @param id
	 * @return
	 */
	public EnginInstall querySupplierInstallBillMessage(Integer id) {
		return dao.querySupplierInstallBillMessage(id);
	}


	/**
	 * 确认验收-提交
	 * @param orderInstallPlan
	 * @param realAcceptDateString
	 *@param realIntoDateString
	 * @param realCompleteDateString
	 * @param photo
	 * @param request   @return
	 */
	@Transactional(readOnly = false)
	public String acceptanceSubmit(OrderInstallPlan orderInstallPlan, String realAcceptDateString, String realIntoDateString, String realCompleteDateString, String[] photo, HttpServletRequest request) {
		String result = "0";
		try {
			String isQualified = orderInstallPlan.getIsQualified();
			String installMode = orderInstallPlan.getInstallMode();
			Integer orderInstallPlanId = orderInstallPlan.getId();
			Integer orderId = orderInstallPlan.getOrderId();

			//1.查询该安装项是否已经验收【合格】
			OrderInstallPlan orderInstallPlanOld = dao.getById(orderInstallPlanId);
			if(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_4.equals(orderInstallPlanOld.getStatus())){
				result = "1";
				return result;
			}
			//2.查询该安装项【是否合格】验收的5分钟校验
			Integer count = dao.queryOrderInstallPlanAcceptLog(orderInstallPlanId);
			if(null != count && count > 0){
				result = "2";
				return result;
			}

			//3.1.如果是不合格验收，查询不合格验收原因
			if(InstallPlanConstantUtil.INSTALL_IS_QUALIFIED_0.equals(isQualified)){
				if(null != orderInstallPlan.getUnqualifiedReasonId()){
                    BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason = bizMainMaterialsUnqualifiedReasonDao.get(orderInstallPlan.getUnqualifiedReasonId());
                    orderInstallPlan.setUnqualifiedReason(bizMainMaterialsUnqualifiedReason.getUnqualifiedReason());
                }
			}
			//3.2.保存安装项计划
			boolean flag = updateOrderInstallPlanAccept(orderInstallPlan,realAcceptDateString,realIntoDateString,
					realCompleteDateString,orderInstallPlanOld.getUnqualifiedTimes());
			if(!flag){
				result = "3";
				return result;
			}

			//4.保存安装项验收状态日志
			Manager manager = (Manager) request.getSession().getAttribute("manager");
			Integer orderInstallPlanAcceptLogId = saveOrderInstallPlanAcceptLog(orderId,orderInstallPlanId,isQualified,
					orderInstallPlanOld.getOrderInstallItemId(),orderInstallPlanOld.getInstallItemName(),
					orderInstallPlan.getUnqualifiedReasonId(),orderInstallPlan.getUnqualifiedReason(),orderInstallPlan.getUnqualifiedRemarks(),manager.getId());

			//5.【产业】【合格】【安装单】【施工单】
			if(InstallPlanConstantUtil.INSTALL_IS_QUALIFIED_1.equals(isQualified) && InstallPlanConstantUtil.INSTALL_MODE_1.equals(installMode)){
				saveSupplierInstallBill(orderInstallPlanId,manager);
			}
			//6.【传统】插入订单同步数据表
			if(InstallPlanConstantUtil.INSTALL_MODE_2.equals(installMode)){
				try {
					insertsyndata(String.valueOf(orderId), String.valueOf(orderInstallPlanId), manager, ConstantUtils.BUSINESS_TYPE_401);
				}catch (Exception e) {
					logger.info("-------------------------主材信息推送到订单流转系统：失败!--------------------------------");
				}
			}

			//7.保存图片【合格-biz_order_install_plan_pic】【不合格-biz_business_pic】
			if(InstallPlanConstantUtil.INSTALL_IS_QUALIFIED_1.equals(isQualified)){
				//7.1【合格-biz_order_install_plan_pic】
				try {
					orderInstallPlanPicService.deletePic(orderInstallPlan.getId());
					orderInstallPlanPicService.saveInstallPlanPicBatch(orderInstallPlanId,photo,request);
				} catch (IOException e) {
					e.printStackTrace();
					result = "20";
					return result;
				}
			}else{
				//7.2【不合格-biz_business_pic】
				newEnginInstallService.saveProblemPic(orderInstallPlanAcceptLogId, PictureTypeContantUtil.PICTURE_TYPE_2076, photo,
						PicturePathContantUtil.UPLOAD_INSTALL_ACCEPTANCE_SUBMIT_PATH, request);
			}

		}catch (NumberFormatException e) {
			result = "20";
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 更新订单安装项计划
	 * @param orderInstallPlan
	 * @param realAcceptDateString
	 *@param realIntoDateString
	 * @param realCompleteDateString @return
	 */
	@Transactional(readOnly = false)
	private boolean updateOrderInstallPlanAccept(OrderInstallPlan orderInstallPlan, String realAcceptDateString, String realIntoDateString, String realCompleteDateString, int unqualifiedTimes) {
		//是否合格
		String isQualified = orderInstallPlan.getIsQualified();
		//安装模式
		String installMode = orderInstallPlan.getInstallMode();

		OrderInstallPlan plan = new OrderInstallPlan();
		//1.计划id
		plan.setId(orderInstallPlan.getId());
		//2.是否合格
		plan.setIsQualified(isQualified);
		if(InstallPlanConstantUtil.INSTALL_IS_QUALIFIED_1.equals(isQualified)){
			//3.合格
			plan.setStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_4);
			//3.1.实际验收时间
			if(StringUtils.isNotBlank(realAcceptDateString)){
				plan.setRealAcceptDate(DateUtils.parseDate(realAcceptDateString));
			}else{
				plan.setRealAcceptDate(new Date());
			}
			//3.2.是否延期
			plan.setIsCompleteDelay(orderInstallPlan.getIsCompleteDelay());
			//3.3.自装延期天数
			plan.setDelayDays(orderInstallPlan.getDelayDays());
			//3.4.延期原因
			plan.setCompleteDelayReason(orderInstallPlan.getCompleteDelayReason());
			//3.5.延期说明
			plan.setCompleteDelayRemarks(orderInstallPlan.getCompleteDelayRemarks());

			//传统
			if(InstallPlanConstantUtil.INSTALL_MODE_2.equals(installMode)){
				//3.6.实际进场日期
				DateUtils.parseDate(realIntoDateString);
				if(StringUtils.isNotBlank(realIntoDateString)){
					plan.setRealIntoDate(DateUtils.parseDate(realIntoDateString));
				}
				//3.7.实际完工日期
				if(StringUtils.isNotBlank(realCompleteDateString)){
					plan.setRealCompleteDate(DateUtils.parseDate(realCompleteDateString));
				}
			}
			//3.8.不合格次数
			plan.setUnqualifiedTimes(unqualifiedTimes);
			//3.9.一次合格率
			BigDecimal b1 = new BigDecimal(Double.toString(1));
			BigDecimal b2 = new BigDecimal(Double.toString(unqualifiedTimes+1));
			double passRate = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
			plan.setFirstPassRate(passRate);

		}else{
			//4.不合格
			plan.setStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_401);
			//4.1.不合格验收时间
			plan.setUnqualifiedAcceptTime(new Date());
			//4.2.不合格原因
			plan.setUnqualifiedReason(orderInstallPlan.getUnqualifiedReason());
			//4.3.不合格备注
			plan.setUnqualifiedRemarks(orderInstallPlan.getUnqualifiedRemarks());
			//4.4.不合格次数
			plan.setUnqualifiedTimes(unqualifiedTimes+1);

		}
		plan.preUpdate();

		return (dao.updateOrderInstallPlan(plan))?true:false;
	}

	/**
	 * 保存验收日志
	 * @param orderId
	 * @param orderInstallPlanId
	 * @param isQualified
	 * @param orderInstallIteamId
	 * @param orderInstallIteam
	 * @param unqualifiedReason
	 * @param acceptRemarks
	 * @param operaterId
	 * @return
	 */
	@Transactional(readOnly = false)
	private Integer saveOrderInstallPlanAcceptLog(Integer orderId, Integer orderInstallPlanId, String isQualified,
												  Integer orderInstallIteamId, String orderInstallIteam,
												  Integer unqualifiedReasonId,String unqualifiedReason, String acceptRemarks,
												  Integer operaterId) {

		OrderInstallPlanAcceptLog orderInstallPlanAcceptLog = new OrderInstallPlanAcceptLog();

		//订单id
		orderInstallPlanAcceptLog.setOrderId(orderId);
		//订单安装计划id
		orderInstallPlanAcceptLog.setOrderInstallPlanId(orderInstallPlanId);
		//验收类型（合格、不合格）
		if (InstallPlanConstantUtil.INSTALL_IS_QUALIFIED_1.equals(isQualified)) {
			//合格
			orderInstallPlanAcceptLog.setAcceptType(InstallPlanConstantUtil.INSTALL_ACCEPT_TYPE_1);
			orderInstallPlanAcceptLog.setRemarks(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_4);
		}else{
			//不合格
			orderInstallPlanAcceptLog.setAcceptType(InstallPlanConstantUtil.INSTALL_ACCEPT_TYPE_2);
			orderInstallPlanAcceptLog.setRemarks(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_401);
			//验收不合格原因
//			orderInstallPlanAcceptLog.setUnqualifiedReason(unqualifiedReason);
            //验收不合格原因id
			orderInstallPlanAcceptLog.setUnqualifiedReasonId(unqualifiedReasonId);
            //验收不合格原因名称
			orderInstallPlanAcceptLog.setUnqualifiedReasonConfigure(unqualifiedReason);
			//验收备注
			orderInstallPlanAcceptLog.setAcceptRemarks(acceptRemarks);
		}
		//安装项名称id
		orderInstallPlanAcceptLog.setOrderInstallIteamId(orderInstallIteamId);
		//安装项名称
		orderInstallPlanAcceptLog.setOrderInstallIteam(orderInstallIteam);
		//操作人id
		orderInstallPlanAcceptLog.setOperaterId(operaterId);
		orderInstallPlanAcceptLog.preInsert();
		dao.saveOrderInstallPlanAcceptLog(orderInstallPlanAcceptLog);
		return orderInstallPlanAcceptLog.getId();
	}


	/**
	 * 【产业】【合格】【安装单】【施工单】
	 * @param orderInstallPlanId
	 * @param manager
	 */
	@Transactional(readOnly = false)
	private void saveSupplierInstallBill(Integer orderInstallPlanId, Manager manager) {
		try {
			//5.1 【安装单】
			BizSupplierInstallBill entity = new BizSupplierInstallBill();
			entity.setOrderInstallPlanId(orderInstallPlanId);
			BizSupplierInstallBill bizSupplierInstallBill = bizSupplierInstallBillDao.getnot90(entity);
			if (bizSupplierInstallBill != null) {
				// 5.1.1.更新安装单
				updateSupplierInstallBill(bizSupplierInstallBill.getId(), InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_50);
				// 5.1.2.保存安装单日志
				bizBusinessStatusLogService.saveBusinessStatusLog(manager.getId(), bizSupplierInstallBill.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9011, InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_50, InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_50);
				//5.2.【施工单】
				BizSupplierInstallConstructBill bizSupplierInstallConstructBill = new BizSupplierInstallConstructBill();
				bizSupplierInstallConstructBill.setSupplierInstallBillId(bizSupplierInstallBill.getId());
				BizSupplierInstallConstructBill installItem = bizSupplierInstallConstructBillDao.getnot90(bizSupplierInstallConstructBill);
				if (installItem != null) {
					//5.2.1.更新施工单
					updateSupplierConstructBill(installItem.getId(), InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_50);
					//5.2.2.保存施工单日志
					bizBusinessStatusLogService.saveBusinessStatusLog(manager.getId(), installItem.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9012, InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_50, InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_NAME_50);
				}
			}
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新安装单
	 *
	 * @param installBillId
	 * @param supplierInstallBillStatus
	 * @return
	 */
	@Transactional(readOnly = false)
	private boolean updateSupplierInstallBill(Integer installBillId, String supplierInstallBillStatus) {

		BizSupplierInstallBill bizSupplierInstallBill = new BizSupplierInstallBill();
		Date date = new Date();
		// 1.安装单id
		bizSupplierInstallBill.setId(installBillId);
		// 2.状态
		bizSupplierInstallBill.setStatus(supplierInstallBillStatus);
		// 3.状态时间
		bizSupplierInstallBill.setStatusDatetime(date);
		// 4.实际完工日期
		bizSupplierInstallBill.setRealCompleteDate(date);
		bizSupplierInstallBill.preUpdate();

		return (dao.updateSupplierInstallBill(bizSupplierInstallBill)) ? true : false;
	}

	/**
	 * 更新施工单
	 *
	 * @param constructBillId
	 * @param supplierInstallConstructBillStatus
	 * @return
	 */
	@Transactional(readOnly = false)
	private boolean updateSupplierConstructBill(Integer constructBillId, String supplierInstallConstructBillStatus) {

		BizSupplierInstallConstructBill bizSupplierInstallConstructBill = new BizSupplierInstallConstructBill();
		Date date = new Date();
		// 1.施工单id
		bizSupplierInstallConstructBill.setId(constructBillId);
		// 2.状态
		bizSupplierInstallConstructBill.setStatus(supplierInstallConstructBillStatus);
		// 3.状态日期时间
		bizSupplierInstallConstructBill.setStatusDatetime(date);
		// 4.验收日期
		bizSupplierInstallConstructBill.setRealAcceptDate(date);
		bizSupplierInstallConstructBill.preUpdate();

		return (dao.updateSupplierConstructBill(bizSupplierInstallConstructBill)) ? true : false;
	}


	/**
	 * 插入订单同步数据表
	 * @param orderID
	 * @param id
	 * @param manager
	 * @param businessType506
	 * @throws UnsupportedEncodingException
	 */
	@Transactional(readOnly = false)
	public void insertsyndata(String orderID, String id, Object manager, String businessType506) throws UnsupportedEncodingException {
		long checkInstallItem = 0l;
		if (!id.equals("99999")) {
			// 根据订单安装项ID查询 订单安装项ID
			List<Integer> ItemId = dao.findInstallid(id);
			// 查询订单的安装项
			List<Integer> orderItemId = dao.findOrderInstallid(orderID);
			// 去两个集合的交集
			orderItemId.retainAll(ItemId);
			// 判断安装项是否都验收了
			CheckInstallItem check = new CheckInstallItem();
			check.setOrderId(orderID);
			check.setOrderItemId(orderItemId);
			checkInstallItem = dao.isCheckInstallItem(check);
		}

		// 如果都验收了，查询二级类目的code
		// 拼接数据
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


	/**
	 * 【不合格】验收日志
	 * @param id
	 * @return
	 */
	public List<OrderInstallPlanAcceptLog> queryAcceptUnqualifiedLog(Integer id) {
		return dao.queryAcceptUnqualifiedLog(id);
	}

	/**
	 * 查询验收【不合格】图片
	 * @param id
	 * @param businessType
	 * @return
	 */
	public List<ReportCheckDetailsPic> queryAcceptUnqualifiedPicList(Integer id, String businessType) {
		ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
		reportCheckDetailsPic.setBusinessIdInt(id);
		reportCheckDetailsPic.setBusinessType(businessType);
		return dao.queryAcceptUnqualifiedPicList(reportCheckDetailsPic);
	}

	/**
	 * 查询验收【合格】图片
	 * @param id
	 * @return
	 */
	public List<OrderInstallPlanPic> queryAcceptQualifiedPicList(Integer id) {
		return dao.queryAcceptQualifiedPicList(id);
	}


	public String md5key(Object object) throws UnsupportedEncodingException {
		// 双反约定的key
		String key = "7b5df6aq2we4r3t6y1vxnmhjklpewd23"; // 获取业务类型
		// String businessType = reciveJson.getBusiness_type();

		// 把对象转成json字符串
		JSONArray fromObject = JSONArray.fromObject(object);
		String string = fromObject.toString();

		// 把截取字符串
		String substring2 = string.substring(2, string.length() - 2);
		// 把字符转换成list数组
		String[] split = substring2.split(",\"");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String replaceFirst = split[i].replaceFirst("\"", "");
			list.add(replaceFirst);
		}
		String[] targetArr = new String[list.size()];
		String[] array = list.toArray(targetArr);
		// 对数组进行排序
		Arrays.sort(array);
		// 遍历数组
		String count = "";
		for (String sp : array) {
			// 截取字符串
			int lastIndexOf = sp.indexOf(':') + 2;
			String substring = sp.substring(lastIndexOf, sp.length() - 1);
			if (!substring.equals("") && substring != null) {
				count = count + substring;
			}
		}
		count = count + key;
		// MD5加密
		String md5 = MD5Utils.getMD5(count).toUpperCase();
		return md5;
	}

	/**
	 * 推送进度节点到订单流转系统
	 * 
	 * @param orderId
	 * @throws UnsupportedEncodingException
	 */

	public String sendNodePlan(String orderId) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String agreeKey = "7b5df6aq2we4r3t6y1vxnmhjklpewd23";
		// 根据订单ID查询订单信息
		InterfaceOrder order = dao.findOrderDetail(orderId);

		// 根据订单ID查询进度节点信息
		List<InterfaceNodePlan> List = dao.findNodePlanDetail(orderId);

		JSONArray fromObject2 = JSONArray.fromObject(List);

		JSONObject fromObject = JSONObject.fromObject(order);

		String string = fromObject.toString();
		String substring2 = string.substring(2, string.length() - 1);
		// 把字符转换成list数组
		String[] split = substring2.split(",\"");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String replaceFirst = split[i].replaceFirst("\"", "");
			list.add(replaceFirst);
		}
		String[] targetArr = new String[list.size()];
		String[] array = list.toArray(targetArr);
		// 对数组进行排序
		Arrays.sort(array);
		// 遍历数组
		String count = "";
		for (String sp : array) {
			// 截取字符串
			/* int lastIndexOf = sp.lastIndexOf(':') +2; */
			int indexOf = sp.indexOf(':') + 2;

			String substring = sp.substring(indexOf, sp.length() - 1);
			if (!substring.equals("") && substring != null) {
				count = count + substring;
			}
		}
		count = count + agreeKey;
		// MD5加密
		String md5 = MD5Utils.getMD5(count).toUpperCase();
		fromObject.put("key", md5);

		// 订单数据转义
		Iterator keys = fromObject.keys();
		while (keys.hasNext()) {
			String key = keys.next() + "";
			String val = fromObject.get(key) + "";
			val = URLEncoder.encode(val, "UTF-8");
			fromObject.put(key, val);
		}

		// 进度节点数据转义
		/*
		 * Iterator iterator = fromObject2.iterator(); while
		 * (iterator.hasNext()) { JSONObject fromObject3 =
		 * (JSONObject)iterator.next(); Iterator iterakeys = fromObject3.keys();
		 * while (iterakeys.hasNext()) { String key = iterakeys.next()+"";
		 * String val = fromObject3.get(key)+""; val = URLEncoder.encode(val,
		 * "UTF-8"); fromObject3.put(key, val); }
		 * 
		 * }
		 */
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




	/**
	 * 批量生成主材安装项计划
	 * @param installItemList
	 * @param actualStartDate
	 * @param manager
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveInstallPlanList(List<OrderInstallItem> installItemList, String actualStartDate, Manager manager) {
		
		boolean installPlanFlag  = true;
		Date date = new Date();
		List<OrderInstallPlan> list = new ArrayList<OrderInstallPlan>();
		
		if(null != installItemList && installItemList.size() > 0){
			for(OrderInstallItem installItem : installItemList){
				
				OrderInstallPlan plan = new OrderInstallPlan();
				plan.setId(null);
				// 订单ID
				plan.setOrderId(installItem.getOrderId());
				// 安装项ID
				plan.setOrderInstallItemId(installItem.getId());
				// 安装项名称
				plan.setInstallItemName(installItem.getInstallItemName());
				// 安装项顺序
				plan.setInstallItemSequence(installItem.getInstallItemSequence());
				//计划申请日期
				plan.setPlanIntoDate(DateUtils.addDate(DateUtils.parseDate(actualStartDate), installItem.getDaysToApplyInto()));// 申请进场日期
				//期望进场日期
				plan.setApplyIntoDate(null);
				// 实际进场日期
				plan.setRealIntoDate(null);
				// 实际完工日期
				plan.setRealCompleteDate(null);
				// 实际验收日期
				plan.setRealAcceptDate(null);
				// 状态 -- '1.已生成计划；2.已申请计划；3.已转给供应商；4.已验收
				plan.setStatus("1");
				// 申请进场备注
				plan.setApplyIntoRemarks(null);
				// 是否完工延期 -- '0.否；1.是
				plan.setIsCompleteDelay(null);
				// 完工延期原因 --// '1.发生变更；2.材料未送到；3.工人不够；4.物业不让施工；5.其他
				plan.setCompleteDelayReason(null);
				// 完工延期描述
				plan.setCompleteDelayRemarks(null);
				// 备注
				plan.setRemarks(null);
				// 安装模式
				plan.setInstallMode(installItem.getInstallMode());
				//计划完工日期
				plan.setPlanCompleteDate(DateUtils.addDate(DateUtils.parseDate(actualStartDate), installItem.getDaysPalnComplete()));
				//如果该主材安装项需要复尺时，保存计划申请复尺时间
				if(InstallPlanConstantUtil.INSTALL_IS_TO_CHECKSIZE_1.equals(installItem.getIsToChecksize())){
					plan.setAllowApplyChecksizeDate(DateUtils.addDate(DateUtils.parseDate(actualStartDate), installItem.getDaysPlanChecksize()));//可申请复尺日期
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
