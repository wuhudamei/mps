
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


	@RequiresPermissions("purchase:bizPurchaseMainTile:view")
	@RequestMapping(value = "tileListPage")
	public String tileListPage(BizPurchaseMainTile bizPurchaseMainTile, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();

		if (null == bizPurchaseMainTile.getStoreId()) {
			if (null != user.getStoreId()) {
				bizPurchaseMainTile.setStoreId(Integer.parseInt(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

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

		if (null == bizPurchaseMainTile.getStoreId()) {
			if (null != user.getStoreId()) {
				bizPurchaseMainTile.setStoreId(Integer.parseInt(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

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


		if (null == bizPurchaseMainTile.getStatus()) {
			bizPurchaseMainTile.setStatus("10");
		}

		bizPurchaseMainTile.setPurchaseType(ConstantUtils.WALL_FLOOR_BRICK_NUMBER);

		Page<BizPurchaseMainTile> page = bizPurchaseMainTileService.findPage(new Page<BizPurchaseMainTile>(request, response), bizPurchaseMainTile);
		model.addAttribute("page", page);

		return "modules/purchase/bizPurchaseMainTileList";
	}


	@RequestMapping(value = "save_wallAndFloor_reply")
	public @ResponseBody String saveWallAndFloorReply(String purchaseId, String urgeReply, HttpServletRequest request) {

		String result = "0";


		if (null == purchaseId || purchaseId.equals("")) {
			result = "1";
			return result;
		}

		User user = UserUtils.getUser();
		Integer managerId = null;
		if (null != user && null != user.getEmpId() && !user.getEmpId().equals("")) {
			managerId = Integer.valueOf(user.getEmpId());
		}

		Integer count = bizBusinessUrgeService.findCountByfiveTime(Integer.valueOf(purchaseId), managerId, BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_2);
		if (null == count || count > 0) {
			result = "3";
			return result;
		}

		if (null == urgeReply || urgeReply.equals("")) {
			result = "4";
			return result;
		}

		Integer urgeId = bizBusinessUrgeService.saveBusinessUrge(managerId, urgeReply, Integer.valueOf(purchaseId), BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_2);
		if (null == urgeId) {
			result = "5";
			return result;
		}


		return result;

	}


	@RequestMapping(value = "wallAndFloorUrgeLog")
	public String wallAndFloorUrgeLog(Integer purchaseId, HttpServletRequest request, HttpServletResponse response, Model model) {


		BizPurchaseMainTile bizPurchaseMainTile = null;

		List<BizBusinessUrge> businessUrgeList = null;

		if (null != purchaseId) {

			bizPurchaseMainTile = bizPurchaseMainTileService.findById(purchaseId);

			BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();

			bizBusinessUrge.setBusinessOnlyMarkInt(purchaseId);

			bizBusinessUrge.setBusinesType(BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2);

			businessUrgeList = bizBusinessUrgeService.findList(bizBusinessUrge);
		}
		model.addAttribute("bizPurchaseMainTile", bizPurchaseMainTile);
		model.addAttribute("businessUrgeList", businessUrgeList);

		return "modules/purchase/wallAndFloorUrgeLog";
	}


	@RequiresPermissions("purchase:bizPurchaseMainTile:view")
	@RequestMapping(value = "mainTileDetails")
	public String mainTileDetails(Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {

		BizPurchaseMainTile bizPurchaseMainTile = bizPurchaseMainTileService.findById(id);
		List<MainTile> mainTiles = mainTileService.findListByPurchaseId(id);
		model.addAttribute("bizPurchaseMainTile", bizPurchaseMainTile);
		model.addAttribute("mainTiles", mainTiles);
		return "modules/purchase/mainTileDetails";
	}


	@RequestMapping(value = "photo")
	public String photo(Integer id, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		List<BizPurchasePicture> pictures = bizPurchasePictureService.findPictureByPurchaseId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("pictures", pictures);

		return "modules/purchase/tilePhoto";
	}


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