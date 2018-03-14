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


	public List<MaterialManagement> findOrderList(Integer itemManagerId) {
		return dao.findOrderList(itemManagerId);
	}


	public Purchase findPurchaseViewAndTimeMessage(Integer orderId) {
		return dao.findPurchaseViewAndTimeMessage(orderId);
	}


	public MaterialManagement findOrderMessage(Integer orderId) {
		return dao.findOrderMessage(orderId);
	}


	public List<OrderMainMate> findWallByOrderId(Integer orderId) {
		return dao.findWallByOrderId(orderId);
	}


	public List<OrderMainMate> findFloorByOrderId(Integer orderId) {
		return dao.findFloorByOrderId(orderId);
	}


	public Double findSquarePurchaseTotal(Integer orderId) {
		return dao.findSquarePurchaseTotal(orderId);
	}


	@Transactional(readOnly = false)
	public String savePurchaseWallFloor(Integer orderId, String projectMode, String inputDate, String purchaseRemarks, String applyCountaTotal, String[] photo, String[] id, String[] applyCounta, String[] applySquare, String[] remarks, String squareBudgetOne, String squareBudgetTwo, String squarePurchaseTotal, String price, Manager manager, HttpServletRequest request) {

		String result = "0";

		try {


			Integer purchaseId = savePurchase(orderId, inputDate, purchaseRemarks, manager.getId(), PurchaseConstantUtil.PURCHASE_TYPE_5, PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_10, PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_NAME_10);


			saveBusinessStatusLog(manager.getId(), purchaseId, BusinessLogConstantUtil.BUSINESS_TYPE_2011, PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_10, PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_NAME_10, null);


			savePurchaseMainMateList(orderId, purchaseId, id, applyCounta, applySquare, remarks);


			savePic(purchaseId, photo, request);


			sendWallAndFloorMessage(orderId, purchaseId, manager);


			updateOrderWallFloorTile(orderId);


			if (projectMode.equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1)) {
				MaterialManagement order = findOrderMessage(Integer.valueOf(orderId));


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


	@Transactional(readOnly = false)
	public void updateOrderWallFloorTile(Integer orderId) {
		dao.updateOrderWallFloorTile(orderId);
	}


	@Transactional(readOnly = false)
	public void saveWallFloorTileRecheck(Integer orderId, Integer managerId) {


		MaterialManagement order = dao.findOrderMessage(orderId);


		BizWallFloorTileOrderCount bizWallFloorTileOrderCount = new BizWallFloorTileOrderCount();

		bizWallFloorTileOrderCount.setId(order.getWallFloorTileOrderCountId());

		bizWallFloorTileOrderCount.setOrderId(order.getOrderId());

		bizWallFloorTileOrderCount.setSquareSettle(order.getSquareSettle());

		bizWallFloorTileOrderCount.setSquareMeasure(order.getSquareMeasure());


		Double squarePurchaseTotal = dao.findSquarePurchaseTotal(orderId);

		bizWallFloorTileOrderCount.setSquarePurchaseTotal(squarePurchaseTotal);

		if (null == order.getWallFloorTileOrderCountId()) {


			Double squareReturn = bizWallFloorTileReturnDao.findSquareReturnAll(orderId);

			bizWallFloorTileOrderCount.setSquareReturn(squareReturn);


			bizWallFloorTileOrderCount.setSquarePurchaseReal(squarePurchaseTotal - squareReturn);


			BizMaterialsChoiceBill materials = bizMaterialsChoiceBillDao.findWallFloorTileSquareBudgetAllCount(order.getOrderNumber());

			if (null != materials) {
				if (null == materials.getOrderId()) {
					materials.setOrderId(order.getOrderId());
					bizMaterialsChoiceBillDao.updateMaterialsChoiceBill(materials);
				}

				if (null != materials.getWallFloorTileSquareBudget()) {
					bizWallFloorTileOrderCount.setSquareBudget(materials.getWallFloorTileSquareBudget());
				} else {
					bizWallFloorTileOrderCount.setSquareBudget(0.00);
				}
			} else {

				bizWallFloorTileOrderCount.setSquareBudget(0.00);

			}

			bizWallFloorTileOrderCount.preInsert();

			bizWallFloorTileOrderCountDao.insert(bizWallFloorTileOrderCount);

		} else {

			bizWallFloorTileOrderCount.setSquareReturn(order.getSquareReturn());


			bizWallFloorTileOrderCount.setSquarePurchaseReal(squarePurchaseTotal - order.getSquareReturn());


			bizWallFloorTileOrderCount.setSquareBudget(order.getSquareBudget());

			bizWallFloorTileOrderCount.preUpdate();

			bizWallFloorTileOrderCountDao.update(bizWallFloorTileOrderCount);
		}


		WallRecheck wallRecheck = wallRecheckDao.findwallRecheckMessage(orderId);


		if (null != wallRecheck) {
			wallRecheck.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_90);
			wallRecheck.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_90);
			wallRecheckDao.update(wallRecheck);
			saveBusinessStatusLog(managerId, wallRecheck.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_90, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_90, null);
		}

		BigDecimal b1 = new BigDecimal(Double.toString(bizWallFloorTileOrderCount.getSquareBudget()));

		BigDecimal b2 = new BigDecimal(Double.toString(1.08));

		Double squareBudget = b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		Double squareQuota = squareBudget + 1;

		if (squareQuota < bizWallFloorTileOrderCount.getSquarePurchaseReal()) {


			RecheckCnfg recheckCnfg = recheckCnfgDao.findRecheckCnfgMessage();


			WallRecheck wallRecheckNew = new WallRecheck();

			wallRecheckNew.setOrderId(orderId);

			wallRecheckNew.setSquareBudget(squareBudget);

			wallRecheckNew.setSquareQuota(squareQuota);

			wallRecheckNew.setSquarePurchase(squarePurchaseTotal);
			if (null != wallRecheck) {

				wallRecheckNew.setSquareMeasure(wallRecheck.getSquareMeasure());

				wallRecheckNew.setPlanMeasureDate(wallRecheck.getPlanMeasureDate());
			}

			if (null != recheckCnfg && StringUtils.isNotBlank(recheckCnfg.getPrice())) {
				wallRecheckNew.setPrice(Double.valueOf(recheckCnfg.getPrice()));
			}

			wallRecheckNew.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_10);

			wallRecheckNew.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_10);

			wallRecheckNew.setStatusDatetime(new Date());

			wallRecheckNew.setStatusOperateEmployeeId(managerId);
			wallRecheckNew.preInsert();
			wallRecheckDao.insert(wallRecheckNew);

			saveBusinessStatusLog(managerId, wallRecheckNew.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_10, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_10, null);

		}

	}


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


	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer managerId, Integer purchaseId, String businessType, String status, String remarks, String dataday2) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

		bizBusinessStatusLog.setBusinessOnlyMarkInt(purchaseId);

		bizBusinessStatusLog.setBusinessType(businessType);

		bizBusinessStatusLog.setBusinessStatus(status);

		bizBusinessStatusLog.setBusinessRemarks(remarks);

		bizBusinessStatusLog.setStatusDatetime(new Date());

		bizBusinessStatusLog.setBusinessEmployeeId(managerId);

		bizBusinessStatusLog.setRemarks(dataday2);
		bizBusinessStatusLog.preInsert();

		bizBusinessStatusLogDao.insert(bizBusinessStatusLog);

		return bizBusinessStatusLog.getId();

	}


	@Transactional(readOnly = false)
	public void savePurchaseMainMateList(Integer orderId, Integer purchaseId, String[] id, String[] applyCounta, String[] applySquare, String[] remarks) {

		List<PurchaseMainMate> list = new ArrayList<PurchaseMainMate>();

		List<OrderMainMate> orderMainMateList = dao.findWallAndFloorByOrderId(orderId);
		for (OrderMainMate orderMainMate : orderMainMateList) {

		}

		if (null != id && id.length > 0) {
			for (int v = 0; v < id.length; v++) {
				if ((StringUtils.isNotBlank(applyCounta[v]) && Double.valueOf(applyCounta[v]) > 0) || (StringUtils.isNotBlank(applyCounta[v]) && Double.valueOf(applyCounta[v]) > 0)) {

					for (OrderMainMate orderMainMate : orderMainMateList) {



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


							if (applyCounta.length > v) {
								if (StringUtils.isNotBlank(applyCounta[v])) {
									purchaseMainMate.setApplyCounta(Double.valueOf(applyCounta[v]));
									purchaseMainMate.setOwedWallFloorCount(Double.valueOf(applyCounta[v]));
								}
							}


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

							purchaseMainMate.setIsCountSquare(orderMainMate.getIsCountSquare());

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

				materialManagementDao.savePurchaseMainMateAll(list);
			}
		}

	}


	@Transactional(readOnly = false)
	public boolean savePic(Integer purchaseId, String[] photo, HttpServletRequest request) {

		List<PurchasePic> pList = new ArrayList<PurchasePic>();
		boolean flag = false;

		if (null != photo && photo.length > 0) {
			for (String p : photo) {

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");

				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_WALLAPPLY + DateUtils.getDate1());

				if (!filePath.exists() && !filePath.isDirectory()) {
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);

				String picpath = ConstantUtils.UPLOAD_WALLAPPLY + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";


				PurchasePic purchasePic = new PurchasePic();
				purchasePic.setPurchaseId(purchaseId);
				purchasePic.setPicUrl(picpath);
				purchasePic.preInsert();
				pList.add(purchasePic);

			}

			if (null != pList && pList.size() > 0) {
				materialManagementDao.saveMainPicAll(pList);
			}
		}

		return flag;

	}


	@Transactional(readOnly = false)
	public void sendWallAndFloorMessage(Integer orderId, Integer purchaseId, Manager manager) {

		MaterialManagement order = dao.findOrderMessage(orderId);


		BizMessagegroup bizMessagegroup = bizMessagegroupDao.getByStoreId(String.valueOf(order.getStoreId()), "4");
		List<Integer> list = new ArrayList<Integer>();
		List<BizEmployee2> employeelist = null;

		if (null != bizMessagegroup) {
			String[] str = bizMessagegroup.getEmployees().split(",");
			for (String id1 : str) {
				list.add(Integer.valueOf(id1));
			}
			employeelist = bizEmployeeDao2.getById(list);

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


	@Transactional(readOnly = false)
	public String purchaseCode() {

		String purchaseCode = ConstantUtils.PURCHASE_NUMBER;

		StringBuilder builder = new StringBuilder();


		PurchaseTwoCode purchaseObj = materialManagementDao.getCode();

		purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode()) + 1));

		materialManagementDao.updateCode(purchaseObj);


		String format = new SimpleDateFormat("yyyyMMdd").format(purchaseObj.getAuxiliaryDate());

		String code = purchaseObj.getPurchaseCode();

		builder.append(purchaseCode).append(format);

		if (code.length() == 1) {

			builder.append("000").append(code);

		} else if (code.length() == 2) {

			builder.append("00").append(code);
		} else if (code.length() == 3) {
			builder.append("0").append(code);
		} else if (code.length() == 4) {
			builder.append(code);
		}


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
