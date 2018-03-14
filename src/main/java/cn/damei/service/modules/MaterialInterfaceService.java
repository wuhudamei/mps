package cn.damei.service.modules;

import java.math.BigDecimal;
import java.util.*;

import net.sf.json.JSONArray;

import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.constantUtils.WallFloorTileRecheckConstantUtil;
import cn.damei.common.constantUtils.WallfloorConstant;
import cn.damei.common.utils.KeyAuthenticateUtils;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.dao.modules.BizMaterialsChoiceBillDao;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.dao.modules.BizMaterialsChoiceBillItemDao;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.dao.modules.BizWallFloorTileOrderCountDao;
import cn.damei.entity.modules.BizWallFloorTileOrderCount;
import cn.damei.dao.modules.BizWallFloorTileReturnDao;
import cn.damei.entity.modules.BizWallFloorTileReturn;
import cn.damei.dao.modules.RecheckCnfgDao;
import cn.damei.dao.modules.WallRecheckDao;
import cn.damei.entity.modules.RecheckCnfg;
import cn.damei.entity.modules.WallRecheck;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

@Service
public class MaterialInterfaceService {

	@Autowired
	private BizMaterialsChoiceBillDao bizMaterialsChoiceBillDao;
	@Autowired
	private BizMaterialsChoiceBillItemDao bizMaterialsChoiceBillItemDao;
	@Autowired
	private BizWallFloorTileOrderCountDao bizWallFloorTileOrderCountDao;
	@Autowired
	private BizWallFloorTileReturnDao bizWallFloorTileReturnDao;
	@Autowired
	private WallRecheckDao wallRecheckDao;
	@Autowired
	private RecheckCnfgDao recheckCnfgDao;
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;


	public String receiveJsonData(Map<String, Object> map) {


		String key = (String) map.get("key");

		String orderNumber = (String) map.get("orderNumber");






		String[] array = new String[] { "orderNumber:" + orderNumber };


		String md5 = KeyAuthenticateUtils.getAndKey(array, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);

		Map<String,String> resultMap = new HashMap<String,String>();

		if (!md5.equals(key)) {

			resultMap.put("code", "406");
			resultMap.put("message", "签名认证失败");
			return JSONObject.fromObject(resultMap).toString();

		} else {
			try {
				String result = saveJSONDate(map);
				return result;

			} catch (Exception e) {
				throw e;
			}
		}

	}


	public String saveJSONDate(Map<String, Object> map) {



		String orderNumber = (String) map.get("orderNumber");




		Map<String,String> resultMap = new HashMap<String,String>();


		if (StringUtils.isBlank(orderNumber)) {

			resultMap.put("code", "401");
			resultMap.put("message", "必填参数订单编号为空");
			return JSONObject.fromObject(resultMap).toString();
		}


		Object object = map.get("materialInfo");
		JSONArray jsonArray = JSONArray.fromObject(object);

		List<Map<String, String>> mapListJson = (List<Map<String, String>>) jsonArray;

		if (CollectionUtils.isEmpty(mapListJson)) {

			resultMap.put("code", "401");
			resultMap.put("message", "选材单材料为空");
			return JSONObject.fromObject(resultMap).toString();
		}



		BizMaterialsChoiceBill bizMaterialsChoiceBill = bizMaterialsChoiceBillDao.findorderAndMaterialsMessage(orderNumber);
		if (null != bizMaterialsChoiceBill && null != bizMaterialsChoiceBill.getId()) {

			bizMaterialsChoiceBillDao.deleteMaterialsChoiceBill(bizMaterialsChoiceBill.getId());
			bizMaterialsChoiceBillItemDao.deleteMaterialsChoiceBillItem(bizMaterialsChoiceBill.getId());
		}


		Integer orderId = bizMaterialsChoiceBillDao.findOrderIdMessage(orderNumber);


		BizMaterialsChoiceBill materialsChoiceBill = new BizMaterialsChoiceBill();
		materialsChoiceBill.setOrderId(orderId);
		materialsChoiceBill.setOrderNumber(orderNumber);
		materialsChoiceBill.setIsdealedMain(WallfloorConstant.WAll_FLOOR_IFALSE0);
		materialsChoiceBill.setIsdealedWallFloor(WallfloorConstant.WAll_FLOOR_IFALSE0);



		materialsChoiceBill.preInsert();
		bizMaterialsChoiceBillDao.insert(materialsChoiceBill);


		if (null == materialsChoiceBill.getId()) {


			resultMap.put("code", "500");
			resultMap.put("message", "材料单保存失败");
			return JSONObject.fromObject(resultMap).toString();
		}


		List<BizMaterialsChoiceBillItem> list = new ArrayList<BizMaterialsChoiceBillItem>();
		for (int i = 0; i < mapListJson.size(); i++) {
			Map<String, String> obj = mapListJson.get(i);

			BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
			bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(materialsChoiceBill.getId());


			String materialsChoiceType = (String) obj.get("materialsChoiceType");
			if (StringUtils.isNotBlank(materialsChoiceType)) {
				bizMaterialsChoiceBillItem.setMaterialsChoiceType(materialsChoiceType);
			}

			String materialsChoiceCategoryCode = (String) obj.get("materialsChoiceCategoryCode");
			if (StringUtils.isNotBlank(materialsChoiceCategoryCode)) {
				bizMaterialsChoiceBillItem.setMaterialsChoiceCategoryCode(materialsChoiceCategoryCode);
			}

			String brand = (String) obj.get("brand");
			if (StringUtils.isNotBlank(brand)) {
				bizMaterialsChoiceBillItem.setBrand(brand);
			}

			String model = (String) obj.get("model");
			if (StringUtils.isNotBlank(model)) {
				bizMaterialsChoiceBillItem.setModel(model);
			}

			String attribute = (String) obj.get("attribute");
			if (StringUtils.isNotBlank(attribute)) {
				bizMaterialsChoiceBillItem.setAttribute(attribute);
			}

			String unit = (String) obj.get("unit");
			if (StringUtils.isNotBlank(unit)) {
				bizMaterialsChoiceBillItem.setUnit(unit);
			}

			String spec = (String) obj.get("spec");
			if (StringUtils.isNotBlank(spec)) {
				bizMaterialsChoiceBillItem.setSpec(spec);
			}

			String position = (String) obj.get("position");
			if (StringUtils.isNotBlank(position)) {
				bizMaterialsChoiceBillItem.setPosition(position);
			}

			String budgetNumber = (String) obj.get("budgetNumber");
			if (StringUtils.isNotBlank(budgetNumber)) {
				bizMaterialsChoiceBillItem.setBudgetNumber1(budgetNumber);
			}

			String lossRatio = (String) obj.get("lossRatio");
			if (StringUtils.isNotBlank(lossRatio)) {
				bizMaterialsChoiceBillItem.setLossRatio(Double.valueOf(lossRatio));
			}

			String includeLossNumber = (String) obj.get("includeLossNumber");
			if (StringUtils.isNotBlank(includeLossNumber)) {
				bizMaterialsChoiceBillItem.setIncludeLossNumber(Double.valueOf(includeLossNumber));
			}











			String supplierName = (String) obj.get("supplierName");
			if (StringUtils.isNotBlank(supplierName)) {
				bizMaterialsChoiceBillItem.setSupplierName(supplierName);
			}

			String supplierCode = (String) obj.get("supplierCode");
			if (StringUtils.isNotBlank(supplierCode)) {
				bizMaterialsChoiceBillItem.setSupplierNo(supplierCode);
			}

			String area = (String) obj.get("area");
			if (StringUtils.isNotBlank(area)) {
				bizMaterialsChoiceBillItem.setBudgetNumber2(area);
			}
			list.add(bizMaterialsChoiceBillItem);

		}


		if (CollectionUtils.isNotEmpty(list)) {

			boolean flag = bizMaterialsChoiceBillItemDao.insertMaterialsChoiceBillItemList(list);

			if (!flag) {

				resultMap.put("code", "500");
				resultMap.put("message", "材料单材料保存失败");
				return JSONObject.fromObject(resultMap).toString();
			}
		}


		if (null != orderId) {

			String result = saveWallFloorTileSquareBudget(orderNumber);

			if (StringUtils.isBlank(result) || !result.equals("0")) {

				resultMap.put("code", "500");
				resultMap.put("message", "墙地砖预算面积保存失败");
				return JSONObject.fromObject(resultMap).toString();
			}

		}


		resultMap.put("code", "200");
		resultMap.put("message", "成功");
		return JSONObject.fromObject(resultMap).toString();
	}


	public String saveWallFloorTileSquareBudget(String orderNumber) {

		User user = UserUtils.getUser();
		Integer empId = null;
		if (null != user && StringUtils.isNotBlank(user.getEmpId())) {
			empId = Integer.valueOf(user.getEmpId());
		}

		String result = "0";


		BizWallFloorTileOrderCount order = bizWallFloorTileOrderCountDao.findOrderMessage(orderNumber);
		if (!order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1)) {
			return result;
		}


		BizMaterialsChoiceBill materials = bizMaterialsChoiceBillDao.findWallFloorTileSquareBudgetAllCount(orderNumber);

		BizWallFloorTileOrderCount bizWallFloorTileOrderCount = new BizWallFloorTileOrderCount();

		bizWallFloorTileOrderCount.setId(order.getId());

		bizWallFloorTileOrderCount.setOrderId(order.getOrderId());

		bizWallFloorTileOrderCount.setSquareSettle(order.getSquareSettle());

		bizWallFloorTileOrderCount.setSquareMeasure(order.getSquareMeasure());


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


		if (null == bizWallFloorTileOrderCount.getId()) {


			Double squarePurchaseTotal = bizMaterialsChoiceBillDao.findSquarePurchaseTotal(order.getOrderId());

			bizWallFloorTileOrderCount.setSquarePurchaseTotal(squarePurchaseTotal);

		} else {



			bizWallFloorTileOrderCount.setSquarePurchaseTotal(order.getSquarePurchaseTotal());


			if (null != order.getSquareBudget() && null != bizWallFloorTileOrderCount.getSquareBudget() && null != order.getSquarePurchaseReal()) {
				if ((bizWallFloorTileOrderCount.getSquareBudget() < order.getSquareBudget()) && (bizWallFloorTileOrderCount.getSquareBudget() < order.getSquarePurchaseReal())) {

					BizWallFloorTileReturn bizWallFloorTileReturn = new BizWallFloorTileReturn();
					bizWallFloorTileReturn.setOrderId(order.getOrderId());
					bizWallFloorTileReturn.setSquareReturn(order.getSquareBudget() - bizWallFloorTileOrderCount.getSquareBudget());
					bizWallFloorTileReturn.setReturnDatetime(new Date());
					bizWallFloorTileReturn.preInsert();
					bizWallFloorTileReturnDao.insert(bizWallFloorTileReturn);

				}
			}

		}


		Double squareReturn = bizWallFloorTileReturnDao.findSquareReturnAll(order.getOrderId());


		bizWallFloorTileOrderCount.setSquareReturn(squareReturn);


		bizWallFloorTileOrderCount.setSquarePurchaseReal(bizWallFloorTileOrderCount.getSquarePurchaseTotal() - squareReturn);


		if (null == bizWallFloorTileOrderCount.getId()) {
			bizWallFloorTileOrderCount.preInsert();
			bizWallFloorTileOrderCountDao.insert(bizWallFloorTileOrderCount);
		} else {
			bizWallFloorTileOrderCount.preUpdate();
			bizWallFloorTileOrderCountDao.update(bizWallFloorTileOrderCount);
		}


		WallRecheck wallRecheck = wallRecheckDao.findwallRecheckMessage(order.getOrderId());


		if (null != wallRecheck) {
			wallRecheck.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_90);
			wallRecheck.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_90);
			wallRecheckDao.update(wallRecheck);
			saveBusinessStatusLog(empId, wallRecheck.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_90, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_90, null);
		}


		if (null != bizWallFloorTileOrderCount.getSquareBudget() && null != bizWallFloorTileOrderCount.getSquarePurchaseReal()) {

			BigDecimal b1 = new BigDecimal(Double.toString(bizWallFloorTileOrderCount.getSquareBudget()));

			BigDecimal b2 = new BigDecimal(Double.toString(1.08));

			Double squareBudget = b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			Double squareQuota = squareBudget + 1;

			if (squareQuota < bizWallFloorTileOrderCount.getSquarePurchaseReal()) {


				RecheckCnfg recheckCnfg = recheckCnfgDao.findRecheckCnfgMessage();


				WallRecheck wallRecheckNew = new WallRecheck();

				wallRecheckNew.setOrderId(order.getOrderId());

				wallRecheckNew.setSquareBudget(squareBudget);

				wallRecheckNew.setSquareQuota(squareQuota);

				wallRecheckNew.setSquarePurchase(bizWallFloorTileOrderCount.getSquarePurchaseTotal());

				wallRecheckNew.setSquareMeasure(wallRecheck.getSquareMeasure());

				wallRecheckNew.setPlanMeasureDate(wallRecheck.getPlanMeasureDate());

				if (StringUtils.isNotBlank(recheckCnfg.getPrice())) {
					wallRecheckNew.setPrice(Double.valueOf(recheckCnfg.getPrice()));
				}

				wallRecheckNew.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_10);

				wallRecheckNew.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_10);

				wallRecheckNew.setStatusDatetime(new Date());

				wallRecheckNew.setStatusOperateEmployeeId(empId);
				wallRecheckNew.preInsert();
				wallRecheckDao.insert(wallRecheckNew);

				saveBusinessStatusLog(empId, wallRecheckNew.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_10, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_10, null);
			}
		}

		return result;
	}


	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer managerId, Integer installId, String businessType, String status, String remarks, String dataday2) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

		bizBusinessStatusLog.setBusinessOnlyMarkInt(installId);

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

}
