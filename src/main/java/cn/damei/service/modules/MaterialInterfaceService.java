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

	/**
	 * 选材清单接口
	 *
	 * @param map
	 * @return
	 */
	public String receiveJsonData(Map<String, Object> map) {

		// 1.key
		String key = (String) map.get("key");
		// 2.订单编号
		String orderNumber = (String) map.get("orderNumber");
		// //3.总价（选材清单总价）
		// String materialsChoiceTotalAmount = (String)
		// map.get("materialsChoiceTotalAmount");

		// String[] array = new
		// String[]{"orderNumber:"+orderNumber,"materialsChoiceTotalAmount:"+materialsChoiceTotalAmount};
		String[] array = new String[] { "orderNumber:" + orderNumber };

		// MD5加密
		String md5 = KeyAuthenticateUtils.getAndKey(array, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);

		Map<String,String> resultMap = new HashMap<String,String>();
		// 校验加密
		if (!md5.equals(key)) {
//			return "{'code':406,'message':'签名认证失败'}";
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

	/**
	 * 保存接收的数据
	 *
	 * @param map
	 * @return
	 */
	public String saveJSONDate(Map<String, Object> map) {

		// 一、选材单
		// 1.订单编号
		String orderNumber = (String) map.get("orderNumber");
		// //2.总价（选材清单总价）
		// String materialsChoiceTotalAmount = (String)
		// map.get("materialsChoiceTotalAmount");

		Map<String,String> resultMap = new HashMap<String,String>();

		// 二、数据校验
		if (StringUtils.isBlank(orderNumber)) {
//			return "{'code':401,'message':'必填参数订单编号为空'}";
			resultMap.put("code", "401");
			resultMap.put("message", "必填参数订单编号为空");
			return JSONObject.fromObject(resultMap).toString();
		}

		// 三、选材单材料
		Object object = map.get("materialInfo");
		JSONArray jsonArray = JSONArray.fromObject(object);

		List<Map<String, String>> mapListJson = (List<Map<String, String>>) jsonArray;
		// 四、数据校验
		if (CollectionUtils.isEmpty(mapListJson)) {
//			return "{'code':401,'message':'选材单材料为空'}";
			resultMap.put("code", "401");
			resultMap.put("message", "选材单材料为空");
			return JSONObject.fromObject(resultMap).toString();
		}

		// 五、判断是否存在该选材单
		// 5.1.查询选材单表是否存在
		BizMaterialsChoiceBill bizMaterialsChoiceBill = bizMaterialsChoiceBillDao.findorderAndMaterialsMessage(orderNumber);
		if (null != bizMaterialsChoiceBill && null != bizMaterialsChoiceBill.getId()) {
			// 5.2如果存在--删除之前该订单的选材清单以及材料信息
			bizMaterialsChoiceBillDao.deleteMaterialsChoiceBill(bizMaterialsChoiceBill.getId());
			bizMaterialsChoiceBillItemDao.deleteMaterialsChoiceBillItem(bizMaterialsChoiceBill.getId());
		}

		// 六、查询订单id
		Integer orderId = bizMaterialsChoiceBillDao.findOrderIdMessage(orderNumber);

		// 七、保存选材单
		BizMaterialsChoiceBill materialsChoiceBill = new BizMaterialsChoiceBill();
		materialsChoiceBill.setOrderId(orderId);
		materialsChoiceBill.setOrderNumber(orderNumber);
		materialsChoiceBill.setIsdealedMain(WallfloorConstant.WAll_FLOOR_IFALSE0);
		materialsChoiceBill.setIsdealedWallFloor(WallfloorConstant.WAll_FLOOR_IFALSE0);
		// if(StringUtils.isBlank(materialsChoiceTotalAmount)){
		// materialsChoiceBill.setMaterialsChoiceTotalAmount(Double.valueOf(materialsChoiceTotalAmount));
		// }
		materialsChoiceBill.preInsert();
		bizMaterialsChoiceBillDao.insert(materialsChoiceBill);

		// 八、判断选材单是否保存成功
		if (null == materialsChoiceBill.getId()) {
			// 8.1 保存失败
//			return "{'code':500,'message':'材料单保存失败'}";
			resultMap.put("code", "500");
			resultMap.put("message", "材料单保存失败");
			return JSONObject.fromObject(resultMap).toString();
		}

		// 九、保存选材单材料--集合中
		List<BizMaterialsChoiceBillItem> list = new ArrayList<BizMaterialsChoiceBillItem>();
		for (int i = 0; i < mapListJson.size(); i++) {
			Map<String, String> obj = mapListJson.get(i);

			BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
			bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(materialsChoiceBill.getId());

			// 1.类型
			String materialsChoiceType = (String) obj.get("materialsChoiceType");
			if (StringUtils.isNotBlank(materialsChoiceType)) {
				bizMaterialsChoiceBillItem.setMaterialsChoiceType(materialsChoiceType);
			}
			// 2.商品类目
			String materialsChoiceCategoryCode = (String) obj.get("materialsChoiceCategoryCode");
			if (StringUtils.isNotBlank(materialsChoiceCategoryCode)) {
				bizMaterialsChoiceBillItem.setMaterialsChoiceCategoryCode(materialsChoiceCategoryCode);
			}
			// 3.品牌
			String brand = (String) obj.get("brand");
			if (StringUtils.isNotBlank(brand)) {
				bizMaterialsChoiceBillItem.setBrand(brand);
			}
			// 4.型号
			String model = (String) obj.get("model");
			if (StringUtils.isNotBlank(model)) {
				bizMaterialsChoiceBillItem.setModel(model);
			}
			// 5.属性
			String attribute = (String) obj.get("attribute");
			if (StringUtils.isNotBlank(attribute)) {
				bizMaterialsChoiceBillItem.setAttribute(attribute);
			}
			// 6.单位
			String unit = (String) obj.get("unit");
			if (StringUtils.isNotBlank(unit)) {
				bizMaterialsChoiceBillItem.setUnit(unit);
			}
			// 7.规格
			String spec = (String) obj.get("spec");
			if (StringUtils.isNotBlank(spec)) {
				bizMaterialsChoiceBillItem.setSpec(spec);
			}
			// 8.位置
			String position = (String) obj.get("position");
			if (StringUtils.isNotBlank(position)) {
				bizMaterialsChoiceBillItem.setPosition(position);
			}
			// 9.预算用量
			String budgetNumber = (String) obj.get("budgetNumber");
			if (StringUtils.isNotBlank(budgetNumber)) {
				bizMaterialsChoiceBillItem.setBudgetNumber1(budgetNumber);
			}
			// 10.损耗系数
			String lossRatio = (String) obj.get("lossRatio");
			if (StringUtils.isNotBlank(lossRatio)) {
				bizMaterialsChoiceBillItem.setLossRatio(Double.valueOf(lossRatio));
			}
			// 11.含损耗用量
			String includeLossNumber = (String) obj.get("includeLossNumber");
			if (StringUtils.isNotBlank(includeLossNumber)) {
				bizMaterialsChoiceBillItem.setIncludeLossNumber(Double.valueOf(includeLossNumber));
			}
			// //12.单价
			// String unitPrice = (String) obj.get("unitPrice");
			// if(StringUtils.isNotBlank(unitPrice)){
			// bizMaterialsChoiceBillItem.setUnitPrice(Double.valueOf(unitPrice));
			// }
			// //13.合价（单个商品的总价）
			// String totalAmount = (String) obj.get("totalAmount");
			// if(StringUtils.isNotBlank(totalAmount)){
			// bizMaterialsChoiceBillItem.setTotalAmount(Double.valueOf(totalAmount));
			// }
			// 14.供应商名称
			String supplierName = (String) obj.get("supplierName");
			if (StringUtils.isNotBlank(supplierName)) {
				bizMaterialsChoiceBillItem.setSupplierName(supplierName);
			}
			// 15.供应商编码
			String supplierCode = (String) obj.get("supplierCode");
			if (StringUtils.isNotBlank(supplierCode)) {
				bizMaterialsChoiceBillItem.setSupplierNo(supplierCode);
			}
			// 16.预算用量面积
			String area = (String) obj.get("area");
			if (StringUtils.isNotBlank(area)) {
				bizMaterialsChoiceBillItem.setBudgetNumber2(area);
			}
			list.add(bizMaterialsChoiceBillItem);

		}

		// 十、如果集合不为空--保存选材单材料
		if (CollectionUtils.isNotEmpty(list)) {
			// 10.1批量保存选材单材料
			boolean flag = bizMaterialsChoiceBillItemDao.insertMaterialsChoiceBillItemList(list);
			// 10.2如果保存失败
			if (!flag) {
//				return "{'code':500,'message':'材料单材料保存失败'}";
				resultMap.put("code", "500");
				resultMap.put("message", "材料单材料保存失败");
				return JSONObject.fromObject(resultMap).toString();
			}
		}

		// 十一、墙地砖预算面积
		if (null != orderId) {

			String result = saveWallFloorTileSquareBudget(orderNumber);

			if (StringUtils.isBlank(result) || !result.equals("0")) {
//				return "{'code':500,'message':'墙地砖预算面积保存失败'}";
				resultMap.put("code", "500");
				resultMap.put("message", "墙地砖预算面积保存失败");
				return JSONObject.fromObject(resultMap).toString();
			}

		}

//		return "{'code':200,'message':'成功'}";
		resultMap.put("code", "200");
		resultMap.put("message", "成功");
		return JSONObject.fromObject(resultMap).toString();
	}

	/**
	 * 选材清单计算墙地砖预算面积
	 * 
	 * @param orderNumber
	 * @return
	 */
	public String saveWallFloorTileSquareBudget(String orderNumber) {

		User user = UserUtils.getUser();
		Integer empId = null;
		if (null != user && StringUtils.isNotBlank(user.getEmpId())) {
			empId = Integer.valueOf(user.getEmpId());
		}

		String result = "0";

		// 一、根据订单编号查询订单的相关信息
		BizWallFloorTileOrderCount order = bizWallFloorTileOrderCountDao.findOrderMessage(orderNumber);
		if (!order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1)) {
			return result;
		}

		// 二、根据订单编号查询订单选材清单的墙地砖预算面积
		BizMaterialsChoiceBill materials = bizMaterialsChoiceBillDao.findWallFloorTileSquareBudgetAllCount(orderNumber);

		BizWallFloorTileOrderCount bizWallFloorTileOrderCount = new BizWallFloorTileOrderCount();
		// a.1:id
		bizWallFloorTileOrderCount.setId(order.getId());
		// a.2:订单id
		bizWallFloorTileOrderCount.setOrderId(order.getOrderId());
		// a.3:结算面积
		bizWallFloorTileOrderCount.setSquareSettle(order.getSquareSettle());
		// a.4:实测面积
		bizWallFloorTileOrderCount.setSquareMeasure(order.getSquareMeasure());

		// 三、如果选材清单的订单id为空，则更新选材清单中的订单id
		if (null != materials) {
			if (null == materials.getOrderId()) {
				materials.setOrderId(order.getOrderId());
				bizMaterialsChoiceBillDao.updateMaterialsChoiceBill(materials);
			}
			// a.5:预算面积
			if (null != materials.getWallFloorTileSquareBudget()) {
				bizWallFloorTileOrderCount.setSquareBudget(materials.getWallFloorTileSquareBudget());
			} else {
				bizWallFloorTileOrderCount.setSquareBudget(0.00);
			}
		} else {
			// a.5:预算面积
			bizWallFloorTileOrderCount.setSquareBudget(0.00);

		}

		// 四、如果订单的相关信息为空时
		if (null == bizWallFloorTileOrderCount.getId()) {

			// 根据订单id查询采购单合计面积
			Double squarePurchaseTotal = bizMaterialsChoiceBillDao.findSquarePurchaseTotal(order.getOrderId());
			// a.6:采购单合计面积
			bizWallFloorTileOrderCount.setSquarePurchaseTotal(squarePurchaseTotal);

		} else {
			// 五、如果订单的相关信息不为空时

			// a.6:采购单合计面积
			bizWallFloorTileOrderCount.setSquarePurchaseTotal(order.getSquarePurchaseTotal());

			// 六、如果选材清单中的面积小于订单的预算面积，同时预算小于采购实际面积，则生成退货单
			if (null != order.getSquareBudget() && null != bizWallFloorTileOrderCount.getSquareBudget() && null != order.getSquarePurchaseReal()) {
				if ((bizWallFloorTileOrderCount.getSquareBudget() < order.getSquareBudget()) && (bizWallFloorTileOrderCount.getSquareBudget() < order.getSquarePurchaseReal())) {
					// 生成退货单
					BizWallFloorTileReturn bizWallFloorTileReturn = new BizWallFloorTileReturn();
					bizWallFloorTileReturn.setOrderId(order.getOrderId());
					bizWallFloorTileReturn.setSquareReturn(order.getSquareBudget() - bizWallFloorTileOrderCount.getSquareBudget());
					bizWallFloorTileReturn.setReturnDatetime(new Date());
					bizWallFloorTileReturn.preInsert();
					bizWallFloorTileReturnDao.insert(bizWallFloorTileReturn);

				}
			}

		}

		// 七、查询退货单的面积总和
		Double squareReturn = bizWallFloorTileReturnDao.findSquareReturnAll(order.getOrderId());

		// a.7:退货面积
		bizWallFloorTileOrderCount.setSquareReturn(squareReturn);

		// a.8:采购实际面积
		bizWallFloorTileOrderCount.setSquarePurchaseReal(bizWallFloorTileOrderCount.getSquarePurchaseTotal() - squareReturn);

		// 八、更新订单的相关数据
		if (null == bizWallFloorTileOrderCount.getId()) {
			bizWallFloorTileOrderCount.preInsert();
			bizWallFloorTileOrderCountDao.insert(bizWallFloorTileOrderCount);
		} else {
			bizWallFloorTileOrderCount.preUpdate();
			bizWallFloorTileOrderCountDao.update(bizWallFloorTileOrderCount);
		}

		// 九、查询是否有复尺单
		WallRecheck wallRecheck = wallRecheckDao.findwallRecheckMessage(order.getOrderId());

		// 十、如果存在复尺单，则删除
		if (null != wallRecheck) {
			wallRecheck.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_90);
			wallRecheck.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_90);
			wallRecheckDao.update(wallRecheck);
			saveBusinessStatusLog(empId, wallRecheck.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_90, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_90, null);
		}

		// 十一、判断是否生成复尺单（预算面积+1 小于采购实际面积）
		if (null != bizWallFloorTileOrderCount.getSquareBudget() && null != bizWallFloorTileOrderCount.getSquarePurchaseReal()) {

			BigDecimal b1 = new BigDecimal(Double.toString(bizWallFloorTileOrderCount.getSquareBudget()));

			BigDecimal b2 = new BigDecimal(Double.toString(1.08));

			Double squareBudget = b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			Double squareQuota = squareBudget + 1;

			if (squareQuota < bizWallFloorTileOrderCount.getSquarePurchaseReal()) {

				// 查询墙地砖复尺配置表（材料单价）
				RecheckCnfg recheckCnfg = recheckCnfgDao.findRecheckCnfgMessage();

				// 生成新的复尺单
				WallRecheck wallRecheckNew = new WallRecheck();
				// b.1:订单id
				wallRecheckNew.setOrderId(order.getOrderId());
				// b.2:预算面积
				wallRecheckNew.setSquareBudget(squareBudget);
				// b.3:定额面积
				wallRecheckNew.setSquareQuota(squareQuota);
				// b.4:实际下单面积
				wallRecheckNew.setSquarePurchase(bizWallFloorTileOrderCount.getSquarePurchaseTotal());
				// b.5:实测面积
				wallRecheckNew.setSquareMeasure(wallRecheck.getSquareMeasure());
				// b.6:计划测量日期
				wallRecheckNew.setPlanMeasureDate(wallRecheck.getPlanMeasureDate());
				// b.9:墙地砖单价
				if (StringUtils.isNotBlank(recheckCnfg.getPrice())) {
					wallRecheckNew.setPrice(Double.valueOf(recheckCnfg.getPrice()));
				}
				// b.10:状态
				wallRecheckNew.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_10);
				// b.11:状态描述
				wallRecheckNew.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_10);
				// b.12:状态日期
				wallRecheckNew.setStatusDatetime(new Date());
				// b.13:状态操作人员ID
				wallRecheckNew.setStatusOperateEmployeeId(empId);
				wallRecheckNew.preInsert();
				wallRecheckDao.insert(wallRecheckNew);

				saveBusinessStatusLog(empId, wallRecheckNew.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_10, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_10, null);
			}
		}

		return result;
	}

	/**
	 * 保存状态日志
	 * @param managerId
	 * @param installId
	 * @param businessType
	 * @param status
	 * @param remarks
	 * @param dataday2
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer managerId, Integer installId, String businessType, String status, String remarks, String dataday2) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		// 1.唯一标识
		bizBusinessStatusLog.setBusinessOnlyMarkInt(installId);
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

}
