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


	@RequestMapping(value = "list")
	public String list(HttpServletRequest request) {
		return "mobile/modules/Manager/materialManagement/wallFloorNew/wallList";
	}


	@RequestMapping(value = "apply_wallFloor_ajax_list")
	public @ResponseBody List<MaterialManagement> applyWallFloorAjaxList(HttpServletRequest request) {


		Manager manager = (Manager) request.getSession().getAttribute("manager");

		List<MaterialManagement> list = null;
		if (null != manager.getId()) {
			list = wallAndFloorService.findOrderList(manager.getId());
		}

		return list;

	}


	@RequestMapping(value = "apply_wallFloor_viewAndTime_ajax")
	public @ResponseBody String apply_wallFloor_viewAndTime_ajax(String orderId, HttpServletRequest request) {

		String result = "0";


		if (StringUtils.isBlank(orderId)) {
			result = "1";
			return result;
		}


		Purchase purchase = wallAndFloorService.findPurchaseViewAndTimeMessage(Integer.valueOf(orderId));
		if (null != purchase) {

			if (purchase.getApplyFiveMinuteCount() > 0) {
				result = "2";
				return result;
			}

			if (purchase.getCount() < 1) {
				result = "3";
				return result;
			}

		}
		return result;

	}


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


			order = wallAndFloorService.findOrderMessage(Integer.valueOf(orderId));





			if ((null == order.getSquareBudget()) || (order.getSquareBudget() == 0)) {

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

			if ((null == order.getSquareReturn())) {

				Double squareReturn = bizWallFloorTileReturnService.findSquareReturnAll(Integer.valueOf(orderId));
				order.setSquareReturn(squareReturn);
			}

			recheckCnfg = recheckCnfgService.findRecheckCnfgMessage() ;

			manager = (Manager) request.getSession().getAttribute("manager");

			wall = wallAndFloorService.findWallByOrderId(Integer.valueOf(orderId));

			floor = wallAndFloorService.findFloorByOrderId(Integer.valueOf(orderId));

			squarePurchaseTotal = wallAndFloorService.findSquarePurchaseTotal(order.getOrderId());

			if (null != order.getSquareReturn() && order.getSquareReturn() > 0) {
				squarePurchaseTotal = squarePurchaseTotal - order.getSquareReturn();
			}

			WallRecheckManage wallRecheckManage = wallAndFloorService.findWallRecheckManage(order.getOrderId());
			if (null != wallRecheckManage) {
				if ((wallRecheckManage.getStatus().equals("10")) || (wallRecheckManage.getStatus().equals("40")) || (wallRecheckManage.getStatus().equals("20"))) {

					squareActual = wallAndFloorService.findSquareActual(order.getOrderId());
				} else {
					squareActual = wallRecheckManage.getSquareMeasure();
				}
			} else {

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


	@RequestMapping(value = "apply_wallFloor_submit_ajax", method = RequestMethod.POST)
	public @ResponseBody String applyWallFloorSubmitAjax(String orderId, String projectMode, String inputDate, String purchaseRemarks, String applyCountaTotal, String[] photo, String[] id, String[] applyCounta, String[] applySquare, String[] remarks, String squareBudgetOne, String squareBudgetTwo, String squarePurchaseTotal, String price, HttpServletRequest request) {

		String result = "0";


		if (StringUtils.isBlank(orderId)) {
			result = "1";
			return result;
		}

		if (StringUtils.isBlank(projectMode)) {
			result = "2";
			return result;
		}

		if (StringUtils.isBlank(projectMode)) {
			result = "3";
			return result;
		}

		if (StringUtils.isBlank(projectMode)) {
			result = "4";
			return result;
		}

		if (projectMode.equals(OrderConstantUtil.ORDER_PROJECT_MODE_TRADITION_2)) {
			if (null == photo || photo.length < 1) {
				result = "5";
				return result;
			}
		}

		if (projectMode.equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4) || projectMode.equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1)) {
			if (StringUtils.isBlank(applyCountaTotal) || applyCountaTotal.equals("0")) {
				result = "6";
				return result;
			}
		}


		if (projectMode.equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1)) {
			if (StringUtils.isBlank(squareBudgetOne) || StringUtils.isBlank(squareBudgetTwo)) {
				result = "7";
				return result;
			}
		}

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if (null == manager) {
			result = "8";
			return result;
		}

		result = wallAndFloorService.savePurchaseWallFloor(Integer.valueOf(orderId), projectMode, inputDate, purchaseRemarks, applyCountaTotal, photo, id, applyCounta, applySquare, remarks, squareBudgetOne, squareBudgetTwo, squarePurchaseTotal, price, manager, request);

		return result;
	}

}