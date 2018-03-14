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

import cn.damei.common.constantUtils.BusinessUrgeConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Manager.BusinessPicture;
import cn.damei.service.mobile.Manager.BusinessPictureService;
import cn.damei.entity.modules.BizBusinessUrge;
import cn.damei.service.modules.BizBusinessUrgeService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizPurchaseMainTile;
import cn.damei.entity.modules.BizPurchasePicture;
import cn.damei.entity.modules.MainTile;
import cn.damei.service.modules.BizPurchaseMainTileService;
import cn.damei.service.modules.BizPurchasePictureService;
import cn.damei.service.modules.MainTileService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 主材墙地砖采购单Controller
 * 
 * @author 汪文文
 * @version 2016-09-28
 */
@Controller
@RequestMapping(value = "${adminPath}/purchase/bizPurchaseMainTile")
public class BizPurchaseMainTileController extends BaseController {

	@Autowired
	private BizPurchaseMainTileService bizPurchaseMainTileService;
	@Autowired
	private BizPurchasePictureService bizPurchasePictureService;
	@Autowired
	private MainTileService mainTileService;
	@Autowired
	private BusinessPictureService businessPictureService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizBusinessUrgeService bizBusinessUrgeService;

	@ModelAttribute
	public BizPurchaseMainTile get(@RequestParam(required = false) Integer id) {
		BizPurchaseMainTile entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = bizPurchaseMainTileService.get(id);
		}
		if (entity == null) {
			entity = new BizPurchaseMainTile();
		}
		return entity;
	}

	// 墙地砖
	@RequiresPermissions("purchase:bizPurchaseMainTile:view")
	@RequestMapping(value = "tileListPage")
	public String tileListPage(BizPurchaseMainTile bizPurchaseMainTile, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizPurchaseMainTile.getStoreId()) {
			if (null != user.getStoreId()) {
				bizPurchaseMainTile.setStoreId(Integer.parseInt(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizPurchaseMainTile.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizPurchaseMainTile.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizPurchaseMainTile.setProjectMode(user.getProjectMode());
			}
		}

		return "modules/purchase/bizPurchaseMainTileList";
	}

	@RequiresPermissions("purchase:bizPurchaseMainTile:view")
	@RequestMapping(value = { "tileList", "" })
	public String listTile(BizPurchaseMainTile bizPurchaseMainTile, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizPurchaseMainTile.getStoreId()) {
			if (null != user.getStoreId()) {
				bizPurchaseMainTile.setStoreId(Integer.parseInt(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(bizPurchaseMainTile.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizPurchaseMainTile.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizPurchaseMainTile.setProjectMode(user.getProjectMode());
			}
		}

		// 墙地砖采购单编号
		if (null == bizPurchaseMainTile.getStatus()) {
			bizPurchaseMainTile.setStatus("10");
		}
		// 墙地砖采购单类型
		bizPurchaseMainTile.setPurchaseType(ConstantUtils.WALL_FLOOR_BRICK_NUMBER);

		Page<BizPurchaseMainTile> page = bizPurchaseMainTileService.findPage(new Page<BizPurchaseMainTile>(request, response), bizPurchaseMainTile);
		model.addAttribute("page", page);

		return "modules/purchase/bizPurchaseMainTileList";
	}

	/**
	 * 回复
	 * 
	 * @param purchaseId
	 * @param urgeReply
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save_wallAndFloor_reply")
	public @ResponseBody String saveWallAndFloorReply(String purchaseId, String urgeReply, HttpServletRequest request) {

		String result = "0";

		// 1.墙地砖申请回复 墙地砖采购单ID为空
		if (null == purchaseId || purchaseId.equals("")) {
			result = "1";
			return result;
		}
		// 2.获取材料部
		User user = UserUtils.getUser();
		Integer managerId = null;
		if (null != user && null != user.getEmpId() && !user.getEmpId().equals("")) {
			managerId = Integer.valueOf(user.getEmpId());
		}
		// 3.墙地砖采购单回复--5分钟校验
		Integer count = bizBusinessUrgeService.findCountByfiveTime(Integer.valueOf(purchaseId), managerId, BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_2);
		if (null == count || count > 0) {
			result = "3";
			return result;
		}
		// 4墙地砖采购单回复内容为空
		if (null == urgeReply || urgeReply.equals("")) {
			result = "4";
			return result;
		}
		// 5.保存墙地砖采购单回复
		Integer urgeId = bizBusinessUrgeService.saveBusinessUrge(managerId, urgeReply, Integer.valueOf(purchaseId), BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_2);
		if (null == urgeId) {
			result = "5";
			return result;
		}
		// 6.保存墙地砖采购单回复图片

		return result;

	}

	/**
	 * 墙地砖采购单日志信息
	 * 
	 * @param purchaseId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "wallAndFloorUrgeLog")
	public String wallAndFloorUrgeLog(Integer purchaseId, HttpServletRequest request, HttpServletResponse response, Model model) {

		// 采购单信息
		BizPurchaseMainTile bizPurchaseMainTile = null;
		// 催促信息
		List<BizBusinessUrge> businessUrgeList = null;

		if (null != purchaseId) {

			bizPurchaseMainTile = bizPurchaseMainTileService.findById(purchaseId);

			BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();
			// 业务唯一标识整形
			bizBusinessUrge.setBusinessOnlyMarkInt(purchaseId);
			// 业务类型
			bizBusinessUrge.setBusinesType(BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2);

			businessUrgeList = bizBusinessUrgeService.findList(bizBusinessUrge);
		}
		model.addAttribute("bizPurchaseMainTile", bizPurchaseMainTile);
		model.addAttribute("businessUrgeList", businessUrgeList);

		return "modules/purchase/wallAndFloorUrgeLog";
	}

	// 墙地砖详情
	@RequiresPermissions("purchase:bizPurchaseMainTile:view")
	@RequestMapping(value = "mainTileDetails")
	public String mainTileDetails(Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {

		BizPurchaseMainTile bizPurchaseMainTile = bizPurchaseMainTileService.findById(id);
		List<MainTile> mainTiles = mainTileService.findListByPurchaseId(id);
		model.addAttribute("bizPurchaseMainTile", bizPurchaseMainTile);
		model.addAttribute("mainTiles", mainTiles);
		return "modules/purchase/mainTileDetails";
	}

	// 图片
	@RequestMapping(value = "photo")
	public String photo(Integer id, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		List<BizPurchasePicture> pictures = bizPurchasePictureService.findPictureByPurchaseId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("pictures", pictures);

		return "modules/purchase/tilePhoto";
	}

	// 图片
	@RequestMapping(value = "/ajaxphotos")
	@ResponseBody
	public Map<Object, Object> ajaxphotos(Integer id, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		List<BizPurchasePicture> pictures = bizPurchasePictureService.findPictureByPurchaseId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("pictures", pictures);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);

		return mapObject;
	}

	// 收货单图片
/*	@RequestMapping(value = "seePhoto")
	public String seePhoto(Integer id, Model model) throws IOException {
		List<BusinessPicture> pictures = businessPictureService.queryPicture(id, ConstantUtils.PICTURE_BUSINESS_TYPE_5);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		return "modules/purchase/photo";
	}*/
	@RequestMapping(value = "seePhoto")
	@ResponseBody
	public Map<Object, Object> seePhoto(Integer id, Model model, HttpServletRequest request) throws IOException{
		List<BusinessPicture> pictures = businessPictureService.queryPicture(id, ConstantUtils.PICTURE_BUSINESS_TYPE_5);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);
		return mapObject;
	}
}