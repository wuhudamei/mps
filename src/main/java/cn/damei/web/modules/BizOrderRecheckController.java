package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderRecheck;
import cn.damei.entity.modules.BizRecheckScaleBill;
import cn.damei.entity.modules.BizRecheckScaleBillCurtain;
import cn.damei.entity.modules.BizRecheckScaleBillFlatOpenDoor;
import cn.damei.entity.modules.BizRecheckScaleBillPushPullDoor;
import cn.damei.entity.modules.BizRecheckScaleBillRoomCabinet;
import cn.damei.entity.modules.BizRecheckScaleBillTaokou;
import cn.damei.entity.modules.BizRecheckScaleBillToilet;
import cn.damei.service.modules.BizOrderRecheckService;
import cn.damei.service.modules.BizRecheckScaleBillCurtainService;
import cn.damei.service.modules.BizRecheckScaleBillFlatOpenDoorService;
import cn.damei.service.modules.BizRecheckScaleBillPushPullDoorService;
import cn.damei.service.modules.BizRecheckScaleBillRoomCabinetService;
import cn.damei.service.modules.BizRecheckScaleBillService;
import cn.damei.service.modules.BizRecheckScaleBillTaokouService;
import cn.damei.service.modules.BizRecheckScaleBillToiletService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 项目经理复尺Controller
 * 
 * @author llp
 * @version 2016-11-21
 */
@Controller
@RequestMapping(value = "${adminPath}/bizorderrecheck/bizOrderRecheck")
public class BizOrderRecheckController extends BaseController {
	@Autowired
	private BizOrderRecheckService bizOrderRecheckService;
	@Autowired
	private BizRecheckScaleBillService bizRecheckScaleBillService;
	@Autowired
	private BizRecheckScaleBillTaokouService bizRecheckScaleBillTaokouService;
	@Autowired
	private BizRecheckScaleBillCurtainService bizRecheckScaleBillCurtainService;
	@Autowired
	private BizRecheckScaleBillPushPullDoorService bizRecheckScaleBillPushPullDoorService;
	@Autowired
	private BizRecheckScaleBillFlatOpenDoorService bizRecheckScaleBillFlatOpenDoorService;
	@Autowired
	private BizRecheckScaleBillRoomCabinetService bizRecheckScaleBillRoomCabinetService;
	@Autowired
	private BizRecheckScaleBillToiletService bizRecheckScaleBillToiletService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@ModelAttribute
	public BizOrderRecheck get(@RequestParam(required = false) Integer id) {
		BizOrderRecheck entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = bizOrderRecheckService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderRecheck();
		}
		return entity;
	}

	@RequiresPermissions("bizorderrecheck:bizOrderRecheck:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(BizOrderRecheck bizOrderRecheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizOrderRecheck.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderRecheck.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (null != user.getEmpId()) {
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if (StringUtils.isBlank(be.getProjectMode())) {
				model.addAttribute("gongcheng", true);
			} else {
				if (be.getProjectMode().equals("3")) {
					model.addAttribute("gongcheng", true);
				} else {
					bizOrderRecheck.setProjectMode(be.getProjectMode());
				}
			}
		} else {
			model.addAttribute("gongcheng", true);
		}

		return "modules/bizrecheck/recheckList";
	}

	@RequiresPermissions("bizorderrecheck:bizOrderRecheck:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderRecheck bizOrderRecheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizOrderRecheck.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderRecheck.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (null != user.getEmpId()) {
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if (StringUtils.isBlank(be.getProjectMode())) {
				model.addAttribute("gongcheng", true);
			} else {
				if (be.getProjectMode().equals("3")) {
					model.addAttribute("gongcheng", true);
				} else {
					bizOrderRecheck.setProjectMode(be.getProjectMode());
				}
			}
		} else {
			model.addAttribute("gongcheng", true);
		}

		Page<BizOrderRecheck> page = bizOrderRecheckService.findPage(new Page<BizOrderRecheck>(request, response), bizOrderRecheck);
		model.addAttribute("page", page);
		return "modules/bizrecheck/recheckList";
	}

	/**
	 * 接收
	 */
	@RequestMapping(value = { "updateByRecheckStatus", "" })
	public String updateByRecheckStatus(BizOrderRecheck bizOrderRecheck, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes, String recheckID) {
		logger.info("复尺编号：" + recheckID);
		if (recheckID != null) {
			bizRecheckScaleBillService.updateByRecheckStatus("1", "材料部已接收", Integer.valueOf(recheckID));
		}
		addMessage(redirectAttributes, "接收成功!");
		return "redirect:" + Global.getAdminPath() + "/bizorderrecheck/bizOrderRecheck/list?repage";
	}

	/**
	 * 详情
	 */
	@RequestMapping(value = { "recheckDetail", "" })
	public String recheckDetail(BizOrderRecheck bizOrderRecheck, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes, String recheckID, String type, String orderID) {
		logger.info("复尺编号：" + recheckID + "\t复尺类型：" + type + "\t订单编号：" + orderID);
		String result = "";
		BizOrderRecheck order = bizOrderRecheckService.get(Integer.valueOf(orderID));

		if (recheckID != null && type != null) {
			if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_1)) {
				List<BizRecheckScaleBillTaokou> listTaokou = bizRecheckScaleBillTaokouService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listTaokou", listTaokou);
				result = "modules/bizrecheck/bizTaokouDetail";
			} else if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_2)) {
				List<BizRecheckScaleBillCurtain> listCurtain = bizRecheckScaleBillCurtainService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listCurtain", listCurtain);
				result = "modules/bizrecheck/bizCurtainDetail";
			} else if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_3)) {
				List<BizRecheckScaleBillPushPullDoor> listPull = bizRecheckScaleBillPushPullDoorService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listPull", listPull);
				result = "modules/bizrecheck/bizPushpullDetail";
			} else if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_4)) {
				List<BizRecheckScaleBillFlatOpenDoor> listFlat = bizRecheckScaleBillFlatOpenDoorService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listFlat", listFlat);
				result = "modules/bizrecheck/bizFlatopenDetail";
			} else if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_5)) {
				List<BizRecheckScaleBillToilet> listToilet = bizRecheckScaleBillToiletService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listToilet", listToilet);
				result = "modules/bizrecheck/bizToiletDetail";
			} else if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_6)) {
				List<BizRecheckScaleBillRoomCabinet> listCabinet = bizRecheckScaleBillRoomCabinetService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listCabinet", listCabinet);
				result = "modules/bizrecheck/bizRoomcablinetDetail";
			}

			BizRecheckScaleBill scaleBill = bizRecheckScaleBillService.get(Integer.valueOf(recheckID));
			model.addAttribute("scaleBill", scaleBill);
		}

		model.addAttribute("order", order);
		return result;
	}

	/**
	 * 详情
	 */
	@RequestMapping(value = { "recheckMonitorDetail", "" })
	public String recheckMonitorDetail(BizOrderRecheck bizOrderRecheck, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes, String recheckID, String type, String orderID) {
		logger.info("复尺编号：" + recheckID + "\t复尺类型：" + type + "\t订单编号：" + orderID);
		String result = "";
		BizOrderRecheck order = bizOrderRecheckService.get(Integer.valueOf(orderID));

		if (recheckID != null && type != null) {
			if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_1)) {
				List<BizRecheckScaleBillTaokou> listTaokou = bizRecheckScaleBillTaokouService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listTaokou", listTaokou);
				result = "modules/bizrecheck/bizTaokouMonitorDetail";
			} else if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_2)) {
				List<BizRecheckScaleBillCurtain> listCurtain = bizRecheckScaleBillCurtainService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listCurtain", listCurtain);
				result = "modules/bizrecheck/bizCurtainMonitorDetail";
			} else if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_3)) {
				List<BizRecheckScaleBillPushPullDoor> listPull = bizRecheckScaleBillPushPullDoorService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listPull", listPull);
				result = "modules/bizrecheck/bizPushpullMonitorDetail";
			} else if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_4)) {
				List<BizRecheckScaleBillFlatOpenDoor> listFlat = bizRecheckScaleBillFlatOpenDoorService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listFlat", listFlat);
				result = "modules/bizrecheck/bizFlatopenMonitorDetail";
			} else if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_5)) {
				List<BizRecheckScaleBillToilet> listToilet = bizRecheckScaleBillToiletService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listToilet", listToilet);
				result = "modules/bizrecheck/bizToiletMonitorDetail";
			} else if (type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_6)) {
				List<BizRecheckScaleBillRoomCabinet> listCabinet = bizRecheckScaleBillRoomCabinetService.getByRecheckID(Integer.valueOf(recheckID));
				model.addAttribute("listCabinet", listCabinet);
				result = "modules/bizrecheck/bizRoomcablinetMonitorDetail";
			}
			BizRecheckScaleBill scaleBill = bizRecheckScaleBillService.get(Integer.valueOf(recheckID));
			model.addAttribute("scaleBill", scaleBill);
		}

		model.addAttribute("order", order);
		return result;
	}
}
