package cn.damei.web.modules;

import java.io.IOException;
import java.util.ArrayList;
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
import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.common.ProjectIssueUtil.ProjectUtil;
import cn.damei.entity.modules.WallRecheck;
import cn.damei.entity.modules.BizOrderScrap;
import cn.damei.service.modules.BizOrderScrapService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/orderScrap/orderScrap")
public class BizOrderScrapController extends BaseController {
	@Autowired
	private BizOrderScrapService bizOrderScrapService;

	@ModelAttribute
	public BizOrderScrap get(@RequestParam(required = false) Integer id) {
		BizOrderScrap entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = bizOrderScrapService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderScrap();
		}
		return entity;
	}


	@RequiresPermissions("orderScrap:orderScrap:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(BizOrderScrap bizOrderScrap, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();


		if (null == bizOrderScrap.getStoreId()) {
			if (StringUtils.isNotBlank(user.getStoreId())) {
				bizOrderScrap.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		if (StringUtils.isBlank(bizOrderScrap.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderScrap.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderScrap.setProjectMode(user.getProjectMode());
			}
		}
		return "modules/orderScrap/bizOrderScrapList";
	}


	@RequiresPermissions("orderScrap:orderScrap:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderScrap bizOrderScrap, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();


		if (null == bizOrderScrap.getStoreId()) {
			if (StringUtils.isNotBlank(user.getStoreId())) {
				bizOrderScrap.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		if (StringUtils.isBlank(bizOrderScrap.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderScrap.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderScrap.setProjectMode(user.getProjectMode());
			}
		}


		bizOrderScrap.setIsScrap(OrderConstantUtil.ORDER_IS_SCRAP_NO_0);

		Page<BizOrderScrap> page = bizOrderScrapService.findPage(new Page<BizOrderScrap>(request, response), bizOrderScrap);
		model.addAttribute("page", page);
		model.addAttribute("bizOrderScrap", bizOrderScrap);
		return "modules/orderScrap/bizOrderScrapList";
	}


	@RequiresPermissions("orderScrap:orderScrap:view")
	@RequestMapping(value = { "preRecoveryList", "" })
	public String preRecoveryList(BizOrderScrap bizOrderScrap, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();


		if (null == bizOrderScrap.getStoreId()) {
			if (StringUtils.isNotBlank(user.getStoreId())) {
				bizOrderScrap.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		if (StringUtils.isBlank(bizOrderScrap.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderScrap.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderScrap.setProjectMode(user.getProjectMode());
			}
		}
		return "modules/orderScrap/bizOrderScrapRecoveryList";
	}


	@RequiresPermissions("orderScrap:orderScrap:view")
	@RequestMapping(value = { "recoveryList", "" })
	public String recoveryList(BizOrderScrap bizOrderScrap, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();


		if (null == bizOrderScrap.getStoreId()) {
			if (StringUtils.isNotBlank(user.getStoreId())) {
				bizOrderScrap.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		if (StringUtils.isBlank(bizOrderScrap.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderScrap.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderScrap.setProjectMode(user.getProjectMode());
			}
		}


		bizOrderScrap.setIsScrap(OrderConstantUtil.ORDER_IS_SCRAP_YES_1);

		Page<BizOrderScrap> page = bizOrderScrapService.findRecoveryPage(new Page<BizOrderScrap>(request, response), bizOrderScrap);
		model.addAttribute("page", page);
		model.addAttribute("bizOrderScrap", bizOrderScrap);
		return "modules/orderScrap/bizOrderScrapRecoveryList";
	}


	@RequiresPermissions("orderScrap:orderScrap:view")
	@RequestMapping(value = "details")
	public String details(Integer orderId, Model model) {
		BizOrderScrap bizOrderScrap = bizOrderScrapService.get(orderId);
		model.addAttribute("bizOrderScrap", bizOrderScrap);
		return "modules/orderScrap/bizOrderScrapDetails";
	}


	@RequiresPermissions("orderScrap:orderScrap:view")
	@RequestMapping(value = "scrapfrom")
	public String scrapfrom(Integer orderId, Model model) {
		BizOrderScrap bizOrderScrap1 = bizOrderScrapService.get(orderId);
		model.addAttribute("bizOrderScrap", bizOrderScrap1);
		return "modules/orderScrap/bizOrderScrapfrom";
	}


	@RequiresPermissions("orderScrap:orderScrap:view")
	@RequestMapping(value = "scrapUpdate")
	public String scrapUpdate(Integer orderId, BizOrderScrap bizOrderScrap, String[] photo, Model model, RedirectAttributes redirectAttributes, HttpServletResponse response, HttpServletRequest request) {
		BizOrderScrap bizOrderScrap1 = bizOrderScrapService.get(orderId);
		bizOrderScrapService.scrapUpdate(bizOrderScrap, photo, response, request);

		model.addAttribute("bizOrderScrap", bizOrderScrap1);
		addMessage(redirectAttributes, "订单作废成功");
		return "redirect:" + Global.getAdminPath() + "/orderScrap/orderScrap/list?repage";
	}

	@Autowired
	private ProjectUtil PicUtil;




	@RequestMapping(value = "photo")
	@ResponseBody
	public Map<Object, Object> photo(Integer orderId, Model model, HttpServletRequest request) throws IOException{

		Map<String, String> picMap = new HashMap<String, String>();
		picMap.put("businessType", ConstantUtils.UPLOAD_SCRAP);
		picMap.put("businessIdInt", orderId + "");
		List<String> findPicByIdAndType = PicUtil.findPicByIdAndType(picMap);
		List<WallRecheck> rechecks = new ArrayList<WallRecheck>();
		if (null != findPicByIdAndType && findPicByIdAndType.size() > 0) {
			for (String string : findPicByIdAndType) {
				WallRecheck recheck = new WallRecheck();
				recheck.setPicUrl(string);
				rechecks.add(recheck);
			}
		}
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("list", rechecks);
		model.addAttribute("baseUrl", baseUrl);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", rechecks);
		return mapObject;
	}

	@RequestMapping(value="orderNumberRecovery")
	public String orderNumberRecovery(RedirectAttributes redirectAttributes,BizOrderScrap bizOrderScrap, HttpServletRequest request, HttpServletResponse response, Model model){
		bizOrderScrapService.orderNumberRecovery(bizOrderScrap);
		addMessage(redirectAttributes, "订单编号回收成功");
		return "redirect:"+Global.getAdminPath()+"/orderScrap/orderScrap/recoveryList";
	}


}