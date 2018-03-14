/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Manager.BusinessPicture;
import cn.damei.entity.mobile.Manager.ReceivedAuxiliary;
import cn.damei.entity.mobile.Manager.ReceivedPanel;
import cn.damei.entity.mobile.Manager.ReceivedTile;
import cn.damei.service.mobile.Manager.BusinessPictureService;
import cn.damei.service.mobile.Manager.ReceivedAuxiliaryService;
import cn.damei.service.mobile.Manager.ReceivedPanelService;
import cn.damei.service.mobile.Manager.ReceivedTileService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.PurchaseReceiveBill;
import cn.damei.service.modules.PurchaseReceiveBillService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 收货单Controller
 * 
 * @author www
 * @version 2016-11-10
 */
@Controller
@RequestMapping(value = "${adminPath}/receipt/purchaseReceiveBill")
public class PurchaseReceiveBillController extends BaseController {

	@Autowired
	private PurchaseReceiveBillService purchaseReceiveBillService;
	@Autowired
	private ReceivedAuxiliaryService receivedAuxiliaryService;
	@Autowired
	private ReceivedTileService receivedTileService;
	@Autowired
	private ReceivedPanelService receivedPanelService;
	@Autowired
	private BusinessPictureService businessPictureService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@ModelAttribute
	public PurchaseReceiveBill get(@RequestParam(required = false) Integer id) {
		PurchaseReceiveBill entity = null;
		if (id != null) {
			entity = purchaseReceiveBillService.get(id);
		}
		if (entity == null) {
			entity = new PurchaseReceiveBill();
		}
		return entity;
	}

	@RequiresPermissions("receipt:purchaseReceiveBill:view")
	@RequestMapping(value = "listPage")
	public String listPage(PurchaseReceiveBill purchaseReceiveBill, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			purchaseReceiveBill.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {
			// 门店是总部的查询所有部门信息
			if (purchaseReceiveBill.getStoreId() != null && purchaseReceiveBill.getStoreId() == 1) {
				// 总部
				purchaseReceiveBill.setStoreId(null);
			}
		}
		if (purchaseReceiveBill.getProjectMode() == null) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					purchaseReceiveBill.setProjectMode(null);// 表示查询所有的采购单
				} else {
					purchaseReceiveBill.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				purchaseReceiveBill.setProjectMode(null);// 表示查询所有的采购单
			}
		}
		return "modules/receipt/purchaseReceiveBillList";
	}

	@RequiresPermissions("receipt:purchaseReceiveBill:view")
	@RequestMapping(value = { "list", "" })
	public String list(PurchaseReceiveBill purchaseReceiveBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			purchaseReceiveBill.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {
			// 门店是总部的查询所有部门信息
			if (purchaseReceiveBill.getStoreId() != null && purchaseReceiveBill.getStoreId() == 1) {
				// 总部
				purchaseReceiveBill.setStoreId(null);
			}
		}
		if (purchaseReceiveBill.getProjectMode() == null || "".equals(purchaseReceiveBill.getProjectMode())) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					purchaseReceiveBill.setProjectMode(null);// 表示查询所有的采购单
				} else {
					purchaseReceiveBill.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				purchaseReceiveBill.setProjectMode(null);// 表示查询所有的采购单
			}
		}
		Page<PurchaseReceiveBill> page = purchaseReceiveBillService.findPage(new Page<PurchaseReceiveBill>(request, response), purchaseReceiveBill);
		model.addAttribute("page", page);
		return "modules/receipt/purchaseReceiveBillList";
	}

	@RequiresPermissions("receipt:purchaseReceiveBill:edit")
	@RequestMapping(value = "receiptDetail")
	public String receiptDetail(Integer id, Model model) {

		PurchaseReceiveBill purchaseReceiveBill = purchaseReceiveBillService.get(id);
		// 根据收货单id查询收货的商品
		String purchaseType = purchaseReceiveBill.getPurchaseType();

		if (ConstantUtils.AUXILIARY_NUMBER.equals(purchaseType)) {
			// 根据采购单id查询对应的商品
			List<ReceivedAuxiliary> list = receivedAuxiliaryService.queryAuxiliaryByReceiveBillId(id);
			model.addAttribute("list", list);
		} else if (ConstantUtils.WALL_FLOOR_BRICK_NUMBER.equals(purchaseType)) {
			List<ReceivedTile> list = receivedTileService.queryTileByReceiveBillId(id);
			for (ReceivedTile receivedTile : list) {
				if (receivedTile.getMainMateType().equals(ConstantUtils.FLOOR_BRICK_NUMBER)) {
					receivedTile.setName("地砖");
				} else {
					receivedTile.setName("墙砖");
				}
			}
			model.addAttribute("list", list);
		} else {
			List<ReceivedPanel> list = receivedPanelService.queryPanelByReceiveBillId(id);
			model.addAttribute("list", list);
		}
		model.addAttribute("purchaseReceiveBill", purchaseReceiveBill);

		return "modules/receipt/detail";
	}

/*	@RequestMapping(value = "seePhoto")
	public String seePhoto(Integer id, Model model) throws IOException {
		List<BusinessPicture> pictures = businessPictureService.queryByReceiveBillId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		return "modules/receipt/photo";
	}*/
	/**
	 * 查看收货单
	 * @param
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "seePhoto")
	@ResponseBody
	public Map<Object, Object> seePhoto(Integer id, Model model, HttpServletRequest request) throws IOException{
		List<BusinessPicture> pictures = businessPictureService.queryByReceiveBillId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);
		return mapObject;
	}

	@RequestMapping(value = "ajaxseePhoto")
	@ResponseBody
	public Map<Object, Object> ajaxseePhoto(Integer id, Model model) throws IOException {
		List<BusinessPicture> pictures = businessPictureService.queryByReceiveBillId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);
		return mapObject;
	}

	@RequiresPermissions("receipt:purchaseReceiveBill:view")
	@RequestMapping(value = "form")
	public String form(PurchaseReceiveBill purchaseReceiveBill, Model model) {
		model.addAttribute("purchaseReceiveBill", purchaseReceiveBill);
		return "modules/receipt/purchaseReceiveBillForm";
	}

	@RequiresPermissions("receipt:purchaseReceiveBill:edit")
	@RequestMapping(value = "save")
	public String save(PurchaseReceiveBill purchaseReceiveBill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, purchaseReceiveBill)) {
			return form(purchaseReceiveBill, model);
		}
		purchaseReceiveBillService.save(purchaseReceiveBill);
		addMessage(redirectAttributes, "保存收货单成功");
		return "redirect:" + Global.getAdminPath() + "/receipt/purchaseReceiveBill/?repage";
	}

	@RequiresPermissions("receipt:purchaseReceiveBill:edit")
	@RequestMapping(value = "delete")
	public String delete(PurchaseReceiveBill purchaseReceiveBill, RedirectAttributes redirectAttributes) {
		purchaseReceiveBillService.delete(purchaseReceiveBill);
		addMessage(redirectAttributes, "删除收货单成功");
		return "redirect:" + Global.getAdminPath() + "/receipt/purchaseReceiveBill/?repage";
	}

}