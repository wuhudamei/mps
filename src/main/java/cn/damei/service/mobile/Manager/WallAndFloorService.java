package cn.damei.service.mobile.Manager;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.constantUtils.WallFloorTileRecheckConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.Base64Util;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.dao.mobile.Manager.MaterialManagementDao;
import cn.damei.dao.mobile.Manager.WallAndFloorDao;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.entity.mobile.Manager.OrderMainMate;
import cn.damei.entity.mobile.Manager.Purchase;
import cn.damei.entity.mobile.Manager.PurchaseMainMate;
import cn.damei.entity.mobile.Manager.PurchasePic;
import cn.damei.entity.mobile.Manager.WallRecheckManage;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.dao.modules.BizMaterialsChoiceBillDao;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.service.modules.BizMaterialsChoiceBillService;
import cn.damei.dao.modules.BizWallFloorTileOrderCountDao;
import cn.damei.entity.modules.BizWallFloorTileOrderCount;
import cn.damei.dao.modules.BizWallFloorTileReturnDao;
import cn.damei.dao.modules.BizEmployeeDao2;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.dao.modules.RecheckCnfgDao;
import cn.damei.dao.modules.WallRecheckDao;
import cn.damei.entity.modules.RecheckCnfg;
import cn.damei.entity.modules.WallRecheck;
import cn.damei.dao.modules.BizMessagegroupDao;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;

/**
 * 材料管理 申请墙地砖Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class WallAndFloorService {

	@Autowired
	private WallAndFloorDao dao;
	@Autowired
	private MaterialManagementDao materialManagementDao;
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
	@Autowired
	private BizMessagegroupDao bizMessagegroupDao;
	@Autowired
	private BizEmployeeDao2 bizEmployeeDao2;
	@Autowired
	private WallRecheckDao wallRecheckDao;
	@Autowired
	private RecheckCnfgDao recheckCnfgDao;
	@Autowired
	private BizMaterialsChoiceBillDao bizMaterialsChoiceBillDao;
	@Autowired
	private BizWallFloorTileReturnDao bizWallFloorTileReturnDao;
	@Autowired
	private BizWallFloorTileOrderCountDao bizWallFloorTileOrderCountDao;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizMaterialsChoiceBillService bizMaterialsChoiceBillService;

	/**
	 * 通过项目经理id查询项目经理下所有的订单
	 * 
	 * @param itemManagerId
	 * @return
	 */
	public List<MaterialManagement> findOrderList(Integer itemManagerId) {
		return dao.findOrderList(itemManagerId);
	}

	/**
	 * 申请墙地砖5分钟校验和是否查看详情
	 * 
	 * @param orderId
	 * @return
	 */
	public Purchase findPurchaseViewAndTimeMessage(Integer orderId) {
		return dao.findPurchaseViewAndTimeMessage(orderId);
	}

	/**
	 * 根据订单id查询订单信息
	 * 
	 * @param orderId
	 * @return
	 */
	public MaterialManagement findOrderMessage(Integer orderId) {
		return dao.findOrderMessage(orderId);
	}

	/**
	 * 通过订单id查询订单主材表的墙砖
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderMainMate> findWallByOrderId(Integer orderId) {
		return dao.findWallByOrderId(orderId);
	}

	/**
	 * 通过订单id查询订单主材表的地砖
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderMainMate> findFloorByOrderId(Integer orderId) {
		return dao.findFloorByOrderId(orderId);
	}

	/**
	 * 查询采购单面积之和（有效面积）
	 * 
	 * @param orderId
	 * @return
	 */
	public Double findSquarePurchaseTotal(Integer orderId) {
		return dao.findSquarePurchaseTotal(orderId);
	}

	/**
	 * 保存墙地砖采购单
	 * 
	 * @param orderId
	 * @param projectMode
	 * @param inputDate
	 * @param purchaseRemarks
	 * @param applyCountaTotal
	 * @param photo
	 * @param id
	 * @param applyCounta
	 * @param applySquare
	 * @param remarks
	 * @param squareBudgetOne
	 * @param squareBudgetTwo
	 * @param squarePurchaseTotal
	 * @param price
	 * @param manager
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public String savePurchaseWallFloor(Integer orderId, String projectMode, String inputDate, String purchaseRemarks, String applyCountaTotal, String[] photo, String[] id, String[] applyCounta, String[] applySquare, String[] remarks, String squareBudgetOne, String squareBudgetTwo, String squarePurchaseTotal, String price, Manager manager, HttpServletRequest request) {

		String result = "0";

		try {

			// 1.保存采购单
			Integer purchaseId = savePurchase(orderId, inputDate, purchaseRemarks, manager.getId(), PurchaseConstantUtil.PURCHASE_TYPE_5, PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_10, PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_NAME_10);

			// 2.保存采购单日志信息
			saveBusinessStatusLog(manager.getId(), purchaseId, BusinessLogConstantUtil.BUSINESS_TYPE_2011, PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_10, PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_NAME_10, null);

			// 3.保存采购单主材表
			savePurchaseMainMateList(orderId, purchaseId, id, applyCounta, applySquare, remarks);

			// 4.保存采购单图片
			savePic(purchaseId, photo, request);

			// 5.发送短信
			sendWallAndFloorMessage(orderId, purchaseId, manager);

			// 6.更新订单墙地砖表（已申请商品数量）
			updateOrderWallFloorTile(orderId);

			// 7.如果订单工程模式为产业(是否生成复尺表)
			if (projectMode.equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1)) {
				MaterialManagement order = findOrderMessage(Integer.valueOf(orderId));

				// 查询选材清单
				BizMaterialsChoiceBill materials = bizMaterialsChoiceBillService.findWallFloorTileSquareBudgetAllCount(order.getOrderNumber());
				if (null != materials) {
					saveWallFloorTileRecheck(orderId, manager.getId());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "9";
		}

		return result;
	}

	/**
	 * 更新订单墙地砖表（已申请商品数量）
	 * 
	 * @param orderId
	 */
	@Transactional(readOnly = false)
	public void updateOrderWallFloorTile(Integer orderId) {
		dao.updateOrderWallFloorTile(orderId);
	}

	/**
	 * 如果订单工程模式为产业(是否生成复尺表)
	 * 
	 * @param orderId
	 * @param managerId
	 */
	@Transactional(readOnly = false)
	public void saveWallFloorTileRecheck(Integer orderId, Integer managerId) {

		// 一、根据订单id查询订单的相关信息
		MaterialManagement order = dao.findOrderMessage(orderId);

		// aaa.订单的相关信息
		BizWallFloorTileOrderCount bizWallFloorTileOrderCount = new BizWallFloorTileOrderCount();
		// a.1:id
		bizWallFloorTileOrderCount.setId(order.getWallFloorTileOrderCountId());
		// a.2:订单id
		bizWallFloorTileOrderCount.setOrderId(order.getOrderId());
		// a.3:结算面积
		bizWallFloorTileOrderCount.setSquareSettle(order.getSquareSettle());
		// a.4:实测面积
		bizWallFloorTileOrderCount.setSquareMeasure(order.getSquareMeasure());

		// 二、根据订单id查询采购单合计面积
		Double squarePurchaseTotal = dao.findSquarePurchaseTotal(orderId);
		// a.5:采购单合计面积
		bizWallFloorTileOrderCount.setSquarePurchaseTotal(squarePurchaseTotal);

		if (null == order.getWallFloorTileOrderCountId()) {

			// 三、查询退货单的面积总和
			Double squareReturn = bizWallFloorTileReturnDao.findSquareReturnAll(orderId);
			// a.6:退货面积
			bizWallFloorTileOrderCount.setSquareReturn(squareReturn);

			// a.7:采购实际面积
			bizWallFloorTileOrderCount.setSquarePurchaseReal(squarePurchaseTotal - squareReturn);

			// 四、根据订单编号查询订单选材清单的墙地砖预算面积
			BizMaterialsChoiceBill materials = bizMaterialsChoiceBillDao.findWallFloorTileSquareBudgetAllCount(order.getOrderNumber());
			// 五、如果选材清单的订单id为空，则更新选材清单中的订单id
			if (null != materials) {
				if (null == materials.getOrderId()) {
					materials.setOrderId(order.getOrderId());
					bizMaterialsChoiceBillDao.updateMaterialsChoiceBill(materials);
				}
				// a.8:预算面积
				if (null != materials.getWallFloorTileSquareBudget()) {
					bizWallFloorTileOrderCount.setSquareBudget(materials.getWallFloorTileSquareBudget());
				} else {
					bizWallFloorTileOrderCount.setSquareBudget(0.00);
				}
			} else {
				// a.8:预算面积
				bizWallFloorTileOrderCount.setSquareBudget(0.00);

			}

			bizWallFloorTileOrderCount.preInsert();
			// 六、更新订单的墙地砖订单统计表
			bizWallFloorTileOrderCountDao.insert(bizWallFloorTileOrderCount);

		} else {
			// a.6:退货面积
			bizWallFloorTileOrderCount.setSquareReturn(order.getSquareReturn());

			// a.7:采购实际面积
			bizWallFloorTileOrderCount.setSquarePurchaseReal(squarePurchaseTotal - order.getSquareReturn());

			// a.8:预算面积
			bizWallFloorTileOrderCount.setSquareBudget(order.getSquareBudget());

			bizWallFloorTileOrderCount.preUpdate();
			// 六、更新订单的墙地砖订单统计表
			bizWallFloorTileOrderCountDao.update(bizWallFloorTileOrderCount);
		}

		// 七、查询是否有复尺单
		WallRecheck wallRecheck = wallRecheckDao.findwallRecheckMessage(orderId);

		// 八、如果存在复尺单，则删除
		if (null != wallRecheck) {
			wallRecheck.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_90);
			wallRecheck.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_90);
			wallRecheckDao.update(wallRecheck);
			saveBusinessStatusLog(managerId, wallRecheck.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_90, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_90, null);
		}
		// 九、当采购单的实际下单面积大于预算面积时，生成复尺单
		BigDecimal b1 = new BigDecimal(Double.toString(bizWallFloorTileOrderCount.getSquareBudget()));

		BigDecimal b2 = new BigDecimal(Double.toString(1.08));

		Double squareBudget = b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		Double squareQuota = squareBudget + 1;

		if (squareQuota < bizWallFloorTileOrderCount.getSquarePurchaseReal()) {

			// 十、查询墙地砖复尺配置表（材料单价）
			RecheckCnfg recheckCnfg = recheckCnfgDao.findRecheckCnfgMessage();

			// 十一、bbbb 生成新的复尺单
			WallRecheck wallRecheckNew = new WallRecheck();
			// b.1:订单id
			wallRecheckNew.setOrderId(orderId);
			// b.2:预算面积
			wallRecheckNew.setSquareBudget(squareBudget);
			// b.3:定额面积
			wallRecheckNew.setSquareQuota(squareQuota);
			// b.4:实际下单面积
			wallRecheckNew.setSquarePurchase(squarePurchaseTotal);
			if (null != wallRecheck) {
				// b.5:实测面积
				wallRecheckNew.setSquareMeasure(wallRecheck.getSquareMeasure());
				// b.6:计划测量日期
				wallRecheckNew.setPlanMeasureDate(wallRecheck.getPlanMeasureDate());
			}
			// b.9:墙地砖单价
			if (null != recheckCnfg && StringUtils.isNotBlank(recheckCnfg.getPrice())) {
				wallRecheckNew.setPrice(Double.valueOf(recheckCnfg.getPrice()));
			}
			// b.10:状态
			wallRecheckNew.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_10);
			// b.11:状态描述
			wallRecheckNew.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_10);
			// b.12:状态日期
			wallRecheckNew.setStatusDatetime(new Date());
			// b.13:状态操作人员ID
			wallRecheckNew.setStatusOperateEmployeeId(managerId);
			wallRecheckNew.preInsert();
			wallRecheckDao.insert(wallRecheckNew);
			// 十二、保存日志
			saveBusinessStatusLog(managerId, wallRecheckNew.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_10, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_10, null);

		}

	}

	/**
	 * 保存采购单
	 * 
	 * @param orderId
	 * @param inputDate
	 * @param purchaseRemarks
	 * @param managerId
	 * @param purchaseType
	 * @param purchaseAuxiliaryStatus
	 * @param purchaseAuxiliaryStatusName
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer savePurchase(Integer orderId, String inputDate, String purchaseRemarks, Integer managerId, String purchaseType, String purchaseAuxiliaryStatus, String purchaseAuxiliaryStatusName) {

		Purchase purchase = new Purchase();
		purchase.setOrderId(orderId);
		purchase.setPurchaseCode(purchaseCode());
		purchase.setPurchaseType(purchaseType);
		purchase.setApplyReceiveTime(DateUtils.parseDate(inputDate));
		purchase.setApplyEmployee(managerId);
		purchase.setApplyTime(new Date());
		purchase.setStatus(purchaseAuxiliaryStatus);
		purchase.setStatusDescribe(purchaseAuxiliaryStatusName);
		purchase.setRemarks(purchaseRemarks);
		purchase.preInsert();

		materialManagementDao.savePurchase(purchase);
		return purchase.getId();
	}

	/**
	 * 保存状态日志
	 * 
	 * @param purchaseId
	 * @param businessType
	 * @param status
	 * @param remarks
	 * @param dataday2
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer managerId, Integer purchaseId, String businessType, String status, String remarks, String dataday2) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		// 1.唯一标识
		bizBusinessStatusLog.setBusinessOnlyMarkInt(purchaseId);
		// 2.业务类型
		bizBusinessStatusLog.setBusinessType(businessType);
		// 3.业务状态
		bizBusinessStatusLog.setBusinessStatus(status);
		// 4.业务备注
		bizBusinessStatusLog.setBusinessRemarks(remarks);
		// 5.状态时间
		bizBusinessStatusLog.setStatusDatetime(new Date());
		// 6.业务人员员工id
		bizBusinessStatusLog.setBusinessEmployeeId(managerId);

		bizBusinessStatusLog.setRemarks(dataday2);
		bizBusinessStatusLog.preInsert();

		bizBusinessStatusLogDao.insert(bizBusinessStatusLog);

		return bizBusinessStatusLog.getId();

	}

	/**
	 * 保存采购单主材表
	 * 
	 * @param orderId
	 * @param purchaseId
	 * @param id
	 * @param applyCounta
	 * @param applySquare
	 * @param remarks
	 * @return
	 */
	@Transactional(readOnly = false)
	public void savePurchaseMainMateList(Integer orderId, Integer purchaseId, String[] id, String[] applyCounta, String[] applySquare, String[] remarks) {

		List<PurchaseMainMate> list = new ArrayList<PurchaseMainMate>();
		// 通过订单id查询订单主材表的材料
		List<OrderMainMate> orderMainMateList = dao.findWallAndFloorByOrderId(orderId);
		for (OrderMainMate orderMainMate : orderMainMateList) {
			// System.out.print(orderMainMate.getId() + ":");
		}

		if (null != id && id.length > 0) {
			for (int v = 0; v < id.length; v++) {
				if ((StringUtils.isNotBlank(applyCounta[v]) && Double.valueOf(applyCounta[v]) > 0) || (StringUtils.isNotBlank(applyCounta[v]) && Double.valueOf(applyCounta[v]) > 0)) {

					for (OrderMainMate orderMainMate : orderMainMateList) {
						// System.err.println(orderMainMate.getId() + ":" +
						// Integer.valueOf(id[v]));
						// Integer valueOf = Integer.valueOf(id[v]);
						if (orderMainMate.getId().equals(Integer.valueOf(id[v]))) {
							PurchaseMainMate purchaseMainMate = new PurchaseMainMate();

							purchaseMainMate.setPurchaseId(purchaseId);
							purchaseMainMate.setMainMateType(orderMainMate.getMainMateType());
							System.err.println(orderMainMate.getMainMateType());
							purchaseMainMate.setPosition(orderMainMate.getPosition());
							purchaseMainMate.setBrandCombo(orderMainMate.getBrandCombo());
							purchaseMainMate.setModel(orderMainMate.getModel());
							purchaseMainMate.setSpecification(orderMainMate.getSpecification());
							purchaseMainMate.setUnit(orderMainMate.getUnit());
							purchaseMainMate.setCount(orderMainMate.getCount());
							purchaseMainMate.setIncludLossCount(orderMainMate.getIncludLossCount());

							// 申请数量
							if (applyCounta.length > v) {
								if (StringUtils.isNotBlank(applyCounta[v])) {
									purchaseMainMate.setApplyCounta(Double.valueOf(applyCounta[v]));
									purchaseMainMate.setOwedWallFloorCount(Double.valueOf(applyCounta[v]));
								}
							}

							// 申请面积
							if (applySquare.length > v) {
								if (StringUtils.isNotBlank(applySquare[v])) {
									purchaseMainMate.setApplySquare(Double.valueOf(applySquare[v]));
								}
							}

							purchaseMainMate.setReceivedWallFloorCount(Double.valueOf(0));
							if (remarks.length > v) {
								if (StringUtils.isNotBlank(remarks[v])) {
									purchaseMainMate.setRemarks(remarks[v]);
								}
							}
							// 是否计算面积
							purchaseMainMate.setIsCountSquare(orderMainMate.getIsCountSquare());
							// 单位面积
							if (StringUtils.isNotBlank(orderMainMate.getUnitSquare())) {
								purchaseMainMate.setUnitSquare(Double.valueOf(orderMainMate.getUnitSquare()));
							}

							purchaseMainMate.preInsert();

							list.add(purchaseMainMate);
						}
					}
				}
			}
			if (null != list && list.size() > 0) {
				// 批量更新采购单主材表
				materialManagementDao.savePurchaseMainMateAll(list);
			}
		}

	}

	/**
	 * 保存 图片(墙地砖)
	 * 
	 * @param purchaseId
	 * @param photo
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean savePic(Integer purchaseId, String[] photo, HttpServletRequest request) {

		List<PurchasePic> pList = new ArrayList<PurchasePic>();
		boolean flag = false;
		// 保存图片
		if (null != photo && photo.length > 0) {
			for (String p : photo) {

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");

				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_WALLAPPLY + DateUtils.getDate1());
				// 判断该文件是否存在
				if (!filePath.exists() && !filePath.isDirectory()) {
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);

				String picpath = ConstantUtils.UPLOAD_WALLAPPLY + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";

				// 保存图片到数据库
				PurchasePic purchasePic = new PurchasePic();
				purchasePic.setPurchaseId(purchaseId);
				purchasePic.setPicUrl(picpath);
				purchasePic.preInsert();
				pList.add(purchasePic);

			}
			// 批量插入申请墙地砖图片
			if (null != pList && pList.size() > 0) {
				materialManagementDao.saveMainPicAll(pList);
			}
		}

		return flag;

	}

	/**
	 * 发送短信
	 * 
	 * @param orderId
	 * @param purchaseId
	 * @param manager
	 */
	@Transactional(readOnly = false)
	public void sendWallAndFloorMessage(Integer orderId, Integer purchaseId, Manager manager) {

		MaterialManagement order = dao.findOrderMessage(orderId);

		// 根据门店和短信组类型查找 messageGroupType : '4';
		BizMessagegroup bizMessagegroup = bizMessagegroupDao.getByStoreId(String.valueOf(order.getStoreId()), "4");
		List<Integer> list = new ArrayList<Integer>();
		List<BizEmployee2> employeelist = null;

		if (null != bizMessagegroup) {
			String[] str = bizMessagegroup.getEmployees().split(",");
			for (String id1 : str) {
				list.add(Integer.valueOf(id1));
			}
			employeelist = bizEmployeeDao2.getById(list);
			// 订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），项目经理（姓名-手机号），项目经理已申请墙地砖，请尽快登录系统查看详情。
			String content = "订单（" + order.getOrderNumber() + "," + order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-" + order.getCustomerPhone() + ",项目经理（" + manager.getRealname() + "-" + manager.getPhone() + "），项目经理已申请墙地砖，请尽快登录系统查看详情。";
			if (null != employeelist && employeelist.size() > 0) {
				for (BizEmployee2 bizEmployee2 : employeelist) {

					BizPhoneMsg ddMsg = new BizPhoneMsg();
					ddMsg.setId(null);
					ddMsg.setReceiveEmployeeId(bizEmployee2.getId());
					ddMsg.setReceivePhone(bizEmployee2.getPhone());
					ddMsg.setMsgContent(content);
					ddMsg.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
					ddMsg.setMsgTosendDatetime(null);
					ddMsg.setMsgSendedDatetime(null);
					ddMsg.setMsgStatus("0");
					ddMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_800401);
					ddMsg.setRelatedBusinessIdInt(purchaseId);
					ddMsg.setRelatedBusinessIdVarchar("");
					bizPhoneMsgService.save(ddMsg);

				}
			}
		}

	}

	/**
	 * 采购单编号生成方法
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public String purchaseCode() {
		// 以PO开头
		String purchaseCode = ConstantUtils.PURCHASE_NUMBER;

		StringBuilder builder = new StringBuilder();

		// num和date
		PurchaseTwoCode purchaseObj = materialManagementDao.getCode();
		// 流水号+1
		purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode()) + 1));
		// 更新数据库
		materialManagementDao.updateCode(purchaseObj);

		// 格式后的时间戳
		String format = new SimpleDateFormat("yyyyMMdd").format(purchaseObj.getAuxiliaryDate());
		// 得到的流水号
		String code = purchaseObj.getPurchaseCode();

		builder.append(purchaseCode).append(format);
		// 判断长度
		if (code.length() == 1) {

			builder.append("000").append(code);

		} else if (code.length() == 2) {
			// 拼接采购单编号
			builder.append("00").append(code);
		} else if (code.length() == 3) {
			builder.append("0").append(code);
		} else if (code.length() == 4) {
			builder.append(code);
		}

		// 返回采购单编号
		return builder.toString();
	}

	public MaterialManagement findOrderMessage1(Integer orderId) {
		return dao.findOrderMessage1(orderId);
	}

	public Double findSquareActual(Integer orderId) {
		return dao.findSquareActual(orderId);
	}

	public WallRecheckManage findWallRecheckManage(Integer orderId) {
		return dao.findWallRecheckManage(orderId);
	}

	public Double querySquate(Integer parseInt) {
		return dao.querySquate(parseInt);
	}

}
