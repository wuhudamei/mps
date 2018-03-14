
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.EmployeeContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.utils.excel.ExportExcel;
import cn.damei.common.web.BaseController;
import cn.damei.common.ProjectIssueUtil.ProjectUtil;
import cn.damei.service.modules.BizPrepareOrderService;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.WallRecheck;
import cn.damei.service.modules.WallRecheckService;
import cn.damei.common.utils.DropUtils;
import cn.damei.common.utils.StoreUtils;


@Controller
@RequestMapping(value = "${adminPath}/materialwallfloor/wallRecheck")
public class WallRecheckController extends BaseController {

	@Autowired
	private WallRecheckService wallRecheckService;

	@Autowired
	private BizPrepareOrderService bizPrepareOrderService;

	@ModelAttribute
	public WallRecheck get(@RequestParam(required = false) Integer id) {
		WallRecheck entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = wallRecheckService.get(id);
		}
		if (entity == null) {
			entity = new WallRecheck();
		}
		return entity;
	}

	@RequiresPermissions("materialwallfloor:wallRecheck:view")
	@RequestMapping(value = "viewlist")
	public String viewlist(WallRecheck wallRecheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/materialwallfloor/wallRecheckList";
	}

	@RequiresPermissions("materialwallfloor:wallRecheck:view")
	@RequestMapping(value = "viewlistExamine")
	public String viewlistExamine(WallRecheck wallRecheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/materialwallfloor/wallRecheckListExamine";
	}

	@RequiresPermissions("materialwallfloor:wallRecheck:view")
	@RequestMapping(value = { "list", "" })
	public String list(WallRecheck wallRecheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WallRecheck> page = wallRecheckService.findPage(new Page<WallRecheck>(request, response), wallRecheck);
		model.addAttribute("page", page);
		return "modules/materialwallfloor/wallRecheckList";
	}

	@RequiresPermissions("materialwallfloor:wallRecheck:view")
	@RequestMapping(value = "listExamine")
	public String listExamine(WallRecheck wallRecheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WallRecheck> page = wallRecheckService.findPage(new Page<WallRecheck>(request, response), wallRecheck);
		model.addAttribute("page", page);
		if (null != wallRecheck.getFlagUi() && wallRecheck.getFlagUi() == 3) {

			return "modules/materialwallfloor/wallRecheckListExamineLog";
		} else {

			return "modules/materialwallfloor/wallRecheckListExamine";
		}
	}


	@RequiresPermissions("materialwallfloor:wallRecheck:view")
	@RequestMapping(value = "listExamineLog")
	public String listExamineLog(WallRecheck wallRecheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WallRecheck> page = wallRecheckService.findWallRecheckPage(new Page<WallRecheck>(request, response), wallRecheck);
		model.addAttribute("page", page);
		return "modules/materialwallfloor/wallRecheckListExamineLog";
	}

	@RequiresPermissions("materialwallfloor:wallRecheck:view")
	@RequestMapping(value = "form")
	public String form(WallRecheck wallRecheck, Model model) {
		model.addAttribute("wallRecheck", wallRecheck);
		return "modules/materialwallfloor/wallRecheckForm";
	}

	@RequiresPermissions("materialwallfloor:wallRecheck:edit")
	@RequestMapping(value = "save")
	public String save(WallRecheck wallRecheck, Model model, RedirectAttributes redirectAttributes) {



		wallRecheckService.save(wallRecheck);
		addMessage(redirectAttributes, "保存墙地砖复尺成功");
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/wallRecheck/?repage";
	}

	@RequiresPermissions("materialwallfloor:wallRecheck:edit")
	@RequestMapping(value = "delete")
	public String delete(WallRecheck wallRecheck, RedirectAttributes redirectAttributes) {
		wallRecheckService.delete(wallRecheck);
		addMessage(redirectAttributes, "删除墙地砖复尺成功");
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/wallRecheck/?repage";
	}


	@RequiresPermissions("materialwallfloor:wallRecheck:edit")
	@RequestMapping(value = "agreRecheck")
	public String agreRecheck(WallRecheck wallRecheck, RedirectAttributes redirectAttributes, Model model) {

		WallRecheck recheck = wallRecheckService.findbyid(wallRecheck);
		model.addAttribute("wallRecheck", recheck);
		return "modules/materialwallfloor/wallRecheckAgre";
	}


	@RequiresPermissions("materialwallfloor:wallRecheck:edit")
	@RequestMapping(value = "agreRecheckUpdate")
	public String agreRecheckUpdate(WallRecheck wallRecheck, RedirectAttributes redirectAttributes, Model model) {
		wallRecheckService.agreRecheckUpdate(wallRecheck);
		model.addAttribute("wallRecheck", wallRecheck);
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/wallRecheck/?repage";
	}


	@RequiresPermissions("materialwallfloor:wallRecheck:edit")
	@RequestMapping(value = "agreRecheckUpdate1")
	public String agreRecheckUpdate1(WallRecheck wallRecheck, RedirectAttributes redirectAttributes, Model model) {
		wallRecheckService.agreRecheckUpdate1(wallRecheck);
		model.addAttribute("wallRecheck", wallRecheck);
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/wallRecheck/?repage";
	}


	@RequiresPermissions("materialwallfloor:wallRecheck:edit")
	@RequestMapping(value = "agreRecheckUpdate2")
	public String agreRecheckUpdate2(WallRecheck wallRecheck, RedirectAttributes redirectAttributes, Model model) {
		wallRecheckService.agreRecheckUpdate2(wallRecheck);
		model.addAttribute("wallRecheck", wallRecheck);
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/wallRecheck/?repage";
	}


	@RequiresPermissions("materialwallfloor:wallRecheck:edit")
	@RequestMapping(value = "NotagreRecheckUpdate2")
	public String NotagreRecheckUpdate2(WallRecheck wallRecheck, RedirectAttributes redirectAttributes, Model model) {
		wallRecheckService.NotagreRecheckUpdate2(wallRecheck);
		model.addAttribute("wallRecheck", wallRecheck);
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/wallRecheck/?repage";
	}


	@RequiresPermissions("materialwallfloor:wallRecheck:edit")
	@RequestMapping(value = "queryResult")
	public String queryResult(WallRecheck wallRecheck, RedirectAttributes redirectAttributes, Model model) {

		WallRecheck recheck = wallRecheckService.findbyid(wallRecheck);
















		model.addAttribute("wallRecheck", recheck);
		return "modules/materialwallfloor/wallRecheckResult";
	}

	@SuppressWarnings("unused")
	private boolean isString(String string) {
		try {
			Integer.valueOf(string);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@RequiresPermissions("materialwallfloor:wallRecheck:edit")
	@RequestMapping(value = "queryResultExamine")
	public String queryResultExamine(WallRecheck wallRecheck, RedirectAttributes redirectAttributes, Model model) {

		List<BizEmployee> employeeListByType = bizPrepareOrderService.getEmployeeListByType(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_5);

		List<BizEmployee> auditorList = bizPrepareOrderService.getEmployeeListByType(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_3);

		model.addAttribute("empList", employeeListByType);
		model.addAttribute("auditorList", auditorList);
		WallRecheck recheck = wallRecheckService.findbyid(wallRecheck);
		model.addAttribute("wallRecheck", recheck);
		return "modules/materialwallfloor/wallRecheckResultExamine";
	}


	@RequiresPermissions("materialwallfloor:wallRecheck:view")
	@RequestMapping(value = "queryResultExamineRenk")
	public String queryResultExamineRenk(WallRecheck wallRecheck, RedirectAttributes redirectAttributes, Model model) {

		List<BizEmployee> employeeListByType = bizPrepareOrderService.getEmployeeListByType(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_5);

		List<BizEmployee> auditorList = bizPrepareOrderService.getEmployeeListByType(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_3);

		model.addAttribute("empList", employeeListByType);
		model.addAttribute("auditorList", auditorList);
		WallRecheck recheck = wallRecheckService.findbyid(wallRecheck);
		model.addAttribute("wallRecheck", recheck);
		return "modules/materialwallfloor/wallRecheckResultExamineRenk";
	}


	@RequiresPermissions("materialwallfloor:wallRecheck:edit")
	@RequestMapping(value = "updateExamine")
	public String updateExamine(WallRecheck wallRecheck, RedirectAttributes redirectAttributes, Model model) {
		WallRecheck wallRecheck2 = wallRecheckService.get(wallRecheck);
		if (wallRecheck2 != null && !wallRecheck2.getStatus().equals("70")) {
			wallRecheckService.updateExamine(wallRecheck);
		}
		model.addAttribute("wallRecheck", wallRecheck);
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/wallRecheck/listExamine?repage";
	}

	@Autowired
	private ProjectUtil PicUtil;



	@RequestMapping(value = "querypic")
	@ResponseBody
	public Map<Object, Object> querypic(Integer id, Model model, HttpServletRequest request) throws IOException{

		Map<String, String> picMap = new HashMap<String, String>();

		picMap.put("businessType", PictureTypeContantUtil.PICTURE_TYPE_2073);
		picMap.put("businessIdInt", id + "");
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
		model.addAttribute("baseUrl", baseUrl);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", rechecks);
		return mapObject;
	}


	@RequestMapping(value = "exportRecheck", method = RequestMethod.POST)
	public String exportRecheck(WallRecheck wallRecheck, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, Model model) throws IOException {
		try {
			String fileName = "审计复尺单" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<WallRecheck> page = wallRecheckService.findPage(new Page<WallRecheck>(request, response), wallRecheck);

			List<WallRecheck> list = page.getList();
			for (WallRecheck wallRecheck2 : list) {
				wallRecheck2.setStoreName(StoreUtils.getStoreLabel(wallRecheck2.getStoreId(), ""));
				wallRecheck2.setOrderaccepName(DropUtils.getElacLabel(wallRecheck2.getOrderacceptarea(), ""));




			}
			new ExportExcel("审计复尺单", WallRecheck.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出审计复尺单失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/materialwallfloor/wallRecheck/listExamine?repage";

	}
}