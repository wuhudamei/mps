package cn.damei.web.mobile.Manager;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.entity.mobile.Manager.OrderMainMate;
import cn.damei.entity.mobile.Manager.Purchase;
import cn.damei.entity.mobile.Manager.WallRecheckManage;
import cn.damei.service.mobile.Manager.WallAndFloorService;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.service.modules.BizMaterialsChoiceBillService;
import cn.damei.service.modules.BizWallFloorTileReturnService;
import cn.damei.entity.modules.RecheckCnfg;
import cn.damei.service.modules.RecheckCnfgService;

/**
 * 材料管理 申请墙地砖Controller
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/app/manager/materialManagement/wallAndFloorNew")
public class WallAndFloorController {

	@Autowired
	private WallAndFloorService wallAndFloorService;
	@Autowired
	private RecheckCnfgService recheckCnfgService;
	@Autowired
	private BizWallFloorTileReturnService bizWallFloorTileReturnService;
	@Autowired
	private BizMaterialsChoiceBillService bizMaterialsChoiceBillService;

	/**
	 * 申请墙地砖页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request) {
		return "mobile/modules/Manager/materialManagement/wallFloorNew/wallList";
	}

	/**
	 * 动态加载申请墙地砖订单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "apply_wallFloor_ajax_list")
	public @ResponseBody List<MaterialManagement> applyWallFloorAjaxList(HttpServletRequest request) {

		// 获得项目经理
		Manager manager = (Manager) request.getSession().getAttribute("manager");

		List<MaterialManagement> list = null;
		if (null != manager.getId()) {
			list = wallAndFloorService.findOrderList(manager.getId());
		}

		return list;

	}

	/**
	 * 申请墙地砖校验（5分钟和查看详情）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "apply_wallFloor_viewAndTime_ajax")
	public @ResponseBody String apply_wallFloor_viewAndTime_ajax(String orderId, HttpServletRequest request) {

		String result = "0";

		// 1.订单id为空
		if (StringUtils.isBlank(orderId)) {
			result = "1";
			return result;
		}

		// 申请墙地砖5分钟校验和是否查看详情
		Purchase purchase = wallAndFloorService.findPurchaseViewAndTimeMessage(Integer.valueOf(orderId));
		if (null != purchase) {
			// 2.如果小于5分钟 则不允许申请墙地砖,并给出提示
			if (purchase.getApplyFiveMinuteCount() > 0) {
				result = "2";
				return result;
			}
			// 3.如果没有查看详情 则不允许申请墙地砖,并给出提示
			if (purchase.getCount() < 1) {
				result = "3";
				return result;
			}

		}
		return result;

	}

	/**
	 * 申请墙地砖采购单页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "wallFloorApply", "" })
	public String wallFloorApply(String orderId, HttpServletRequest request, Model model) {

		MaterialManagement order = null;
		MaterialManagement order1 = null;
		RecheckCnfg recheckCnfg = null;
		Manager manager = null;
		List<OrderMainMate> wall = null;
		List<OrderMainMate> floor = null;
		Double squarePurchaseTotal = 0.00;
		Double squareActual = 0.00;
		if (StringUtils.isNotBlank(orderId)) {

			// 1.根据订单id查询订单信息
			order = wallAndFloorService.findOrderMessage(Integer.valueOf(orderId));
			// // 从前地砖表中表中查询订单预算面积然后set到 order中
			// order1 =
			// wallAndFloorService.findOrderMessage1(Integer.valueOf(orderId));
			// order.setSquareBudgetOne(order1.getSquareBudgetOne1());
			// 1.1：如果预算面积*108% || (order.getSquareBudget() <= 0)
			if ((null == order.getSquareBudget()) || (order.getSquareBudget() == 0)) {
				// 查询选材清单
				BizMaterialsChoiceBill materials = bizMaterialsChoiceBillService.findWallFloorTileSquareBudgetAllCount(order.getOrderNumber());
				if (null != materials) {
					if (null != materials.getWallFloorTileSquareBudget()) {
						BigDecimal b1 = new BigDecimal(Double.toString(materials.getWallFloorTileSquareBudget()));
						BigDecimal b2 = new BigDecimal(Double.toString(1.08));
						Double squareBudget = b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						order.setSquareBudgetOne(squareBudget);
					}
				}
			}
			// 1.2：如果退货面积为空 || (order.getSquareReturn() <= 0)
			if ((null == order.getSquareReturn())) {
				// 查询退货单的面积总和
				Double squareReturn = bizWallFloorTileReturnService.findSquareReturnAll(Integer.valueOf(orderId));
				order.setSquareReturn(squareReturn);
			}
			// 2.查询墙地砖复尺配置表（材料单价和面积超出上限比例）
			recheckCnfg = recheckCnfgService.findRecheckCnfgMessage() ;
			// 3.查询项目经理信息
			manager = (Manager) request.getSession().getAttribute("manager");
			// 4.通过订单id查询订单主材表的墙砖+
			wall = wallAndFloorService.findWallByOrderId(Integer.valueOf(orderId));
			// 5.通过订单id查询订单主材表的地砖
			floor = wallAndFloorService.findFloorByOrderId(Integer.valueOf(orderId));
			// 6.查询采购单面积之和（有效面积）
			squarePurchaseTotal = wallAndFloorService.findSquarePurchaseTotal(order.getOrderId());
			// 7.采购单的实际申请面积（申请面积-退货面积）
			if (null != order.getSquareReturn() && order.getSquareReturn() > 0) {
				squarePurchaseTotal = squarePurchaseTotal - order.getSquareReturn();
			}
			// 8.根据订单id查询获取实测面积
			WallRecheckManage wallRecheckManage = wallAndFloorService.findWallRecheckManage(order.getOrderId());
			if (null != wallRecheckManage) {
				if ((wallRecheckManage.getStatus().equals("10")) || (wallRecheckManage.getStatus().equals("40")) || (wallRecheckManage.getStatus().equals("20"))) {
					// 8.根据订单id查询获取实测面积
					squareActual = wallAndFloorService.findSquareActual(order.getOrderId());
				} else {
					squareActual = wallRecheckManage.getSquareMeasure();
				}
			} else {
				// 8.根据订单id查询获取实测面积
				squareActual = wallAndFloorService.findSquareActual(order.getOrderId());

			}
		}

		model.addAttribute("order", order);
		model.addAttribute("recheckCnfg", recheckCnfg);
		model.addAttribute("manager", manager);
		model.addAttribute("wall", wall);
		model.addAttribute("floor", floor);
		model.addAttribute("squarePurchaseTotal", squarePurchaseTotal);
		if (null == squareActual) {
			model.addAttribute("squareActual", "0.0");
		} else {
			model.addAttribute("squareActual", squareActual);
		}

		if (CollectionUtils.isEmpty(wall) && CollectionUtils.isEmpty(floor)) {

			return "mobile/modules/Manager/materialManagement/wallFloorNew/wallSub_null";
		}
		return "mobile/modules/Manager/materialManagement/wallFloorNew/wallSub";
	}

	/**
	 * 墙地砖采购单提交申请
	 * 
	 * @Title: applyWallFloorSubmitAjax
	 * @Description: TODO
	 * @param @param orderId
	 * @param @param projectMode
	 * @param @param inputDate
	 * @param @param purchaseRemarks
	 * @param @param applyCountaTotal
	 * @param @param photo
	 * @param @param id
	 * @param @param applyCounta
	 * @param @param applySquare
	 * @param @param remarks
	 * @param @param squareBudgetOne
	 * @param @param squareBudgetTwo
	 * @param @param squarePurchaseTotal
	 * @param @param price
	 * @param @param request
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = "apply_wallFloor_submit_ajax", method = RequestMethod.POST)
	public @ResponseBody String applyWallFloorSubmitAjax(String orderId, String projectMode, String inputDate, String purchaseRemarks, String applyCountaTotal, String[] photo, String[] id, String[] applyCounta, String[] applySquare, String[] remarks, String squareBudgetOne, String squareBudgetTwo, String squarePurchaseTotal, String price, HttpServletRequest request) {

		String result = "0";

		// 1.订单id为空
		if (StringUtils.isBlank(orderId)) {
			result = "1";
			return result;
		}
		// 2.订单工程模式为空
		if (StringUtils.isBlank(projectMode)) {
			result = "2";
			return result;
		}
		// 3.期望到场日期为空
		if (StringUtils.isBlank(projectMode)) {
			result = "3";
			return result;
		}
		// 4.备注为空
		if (StringUtils.isBlank(projectMode)) {
			result = "4";
			return result;
		}
		// 5.当工程模式为传统时，必须上传图片
		if (projectMode.equals(OrderConstantUtil.ORDER_PROJECT_MODE_TRADITION_2)) {
			if (null == photo || photo.length < 1) {
				result = "5";
				return result;
			}
		}
		// 6.当工程模式为准产业或产业时，必须有商品
		if (projectMode.equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4) || projectMode.equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1)) {
			if (StringUtils.isBlank(applyCountaTotal) || applyCountaTotal.equals("0")) {
				result = "6";
				return result;
			}
		}
		// 7.当工程模式为产业时，（可能会有墙地砖复尺单的生成）
		// ||StringUtils.isBlank(squarePurchaseTotal)
		if (projectMode.equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1)) {
			if (StringUtils.isBlank(squareBudgetOne) || StringUtils.isBlank(squareBudgetTwo)) {
				result = "7";
				return result;
			}
		}
		// 8.当前登录的项目经理
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if (null == manager) {
			result = "8";
			return result;
		}
		// 9.保存采购单
		result = wallAndFloorService.savePurchaseWallFloor(Integer.valueOf(orderId), projectMode, inputDate, purchaseRemarks, applyCountaTotal, photo, id, applyCounta, applySquare, remarks, squareBudgetOne, squareBudgetTwo, squarePurchaseTotal, price, manager, request);

		return result;
	}

}