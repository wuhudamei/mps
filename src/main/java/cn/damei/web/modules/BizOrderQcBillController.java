/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

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

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderQcBill;
import cn.damei.entity.modules.BizQcBill;
import cn.damei.entity.modules.ReportCheckDetails;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.service.modules.BizOrderQcBillService;
import cn.damei.entity.modules.BizQcCheckKind;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 订单质检报告Controller
 * 
 * @author wyb
 * @version 2016-10-31
 */
@Controller
@RequestMapping(value = "${adminPath}/bizorderqcbill/bizOrderQcBill")
public class BizOrderQcBillController extends BaseController {

	@Autowired
	private BizOrderQcBillService bizOrderQcBillService;

	@ModelAttribute
	public BizOrderQcBill get(@RequestParam(required = false) Integer id) {
		BizOrderQcBill entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = bizOrderQcBillService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderQcBill();
		}
		return entity;
	}

	/**
	 * 订单报告
	 * 
	 * @param bizOrderQcBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizorderqcbill:bizOrderQcBill:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderQcBill bizOrderQcBill, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizOrderQcBill.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderQcBill.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			model.addAttribute("gongcheng", true);
		} else {
			bizOrderQcBill.setProjectMode(user.getProjectMode());
		}

		return "modules/bizqcbill/bizOrderList";

	}

	/**
	 * 订单报告
	 * 
	 * @param bizOrderQcBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizorderqcbill:bizOrderQcBill:view")
	@RequestMapping(value = { "bizOrderQcBillList", "" })
	public String bizOrderQcBillList(BizOrderQcBill bizOrderQcBill, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizOrderQcBill.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderQcBill.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			model.addAttribute("gongcheng", true);
		} else {
			bizOrderQcBill.setProjectMode(user.getProjectMode());
		}

		Page<BizOrderQcBill> page = bizOrderQcBillService.findPage(new Page<BizOrderQcBill>(request, response), bizOrderQcBill);
		model.addAttribute("page", page);
		return "modules/bizqcbill/bizOrderList";

	}

	/**
	 * 质检报告列表
	 * 
	 * @param orderId
	 * @param request
	 * @param bizQcBill
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizorderqcbill:bizOrderQcBill:view")
	@RequestMapping(value = { "report", "" })
	public String report(int orderId, HttpServletRequest request, BizQcBill bizQcBill, HttpServletResponse response, Model model) {
		// 通过订单id查询订单
		BizOrderQcBill bizOrderQcBill = bizOrderQcBillService.findOrder(orderId);
		// 通过订单id查询所属订单的报告单
		List<BizQcBill> list = bizOrderQcBillService.findReport(orderId);
		model.addAttribute("bizOrderQcBill", bizOrderQcBill);
		model.addAttribute("list", list);
		return "modules/bizqcbill/bizOrderReport";

	}

	/**
	 * 查询所有分类
	 * 
	 * @param storeId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "kind")
	public @ResponseBody List<BizQcCheckKind> kind(String storeId, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 查询所有的检查分类
		List<BizQcCheckKind> checkKindList = bizOrderQcBillService.findCheckKind();
		return checkKindList;
	}

	/**
	 * 质检报告详情
	 * 
	 * @param qcBillId
	 * @param request
	 * @param ReportCheckDetails
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("bizorderqcbill:bizOrderQcBill:view")
	@RequestMapping(value = { "details", "" })
	public String details(Integer qcBillId, HttpServletRequest request, ReportCheckDetails ReportCheckDetails, HttpServletResponse response, Model model) throws Exception {

		// 报告详情
		if (null != qcBillId && qcBillId >= 1) {
			ReportCheckDetails.setQcBillId(qcBillId);

			// 通过质检单id查询质检单信息
			BizQcBill bizQcBill = bizOrderQcBillService.findReportDetails(qcBillId);
			model.addAttribute("bizQcBill", bizQcBill);
		}
		List<ReportCheckDetails> list = bizOrderQcBillService.finditemById(ReportCheckDetails);

		// 通过质检单id查询质检图片
		if (qcBillId != null) {
			List<ReportCheckDetailsPic> picList = bizOrderQcBillService.findPic(qcBillId);
			String baseUrl = PicRootName.picPrefixName();
			if (picList != null && picList.size() > 0) {
				model.addAttribute("picListLength", picList.size());
			} else {
				int picListLength = 0;
				model.addAttribute("picListLength", picListLength);
			}

			model.addAttribute("picList", picList);
			model.addAttribute("baseUrl", baseUrl);
		}

		model.addAttribute("list", list);
		model.addAttribute("qcBillId", qcBillId);

		return "modules/bizqcbill/bizReportDetails";

	}

	@RequiresPermissions("bizorderqcbill:bizOrderQcBill:view")
	@RequestMapping(value = { "detailsPic", "" })
	public String detailsPic(Integer qcBillId, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		// 通过质检单id查询质检图片
		List<ReportCheckDetailsPic> picList = bizOrderQcBillService.findPic(qcBillId);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);

		return "modules/bizqcbill/bizReportDetailsPic";
	}

	@RequiresPermissions("bizorderqcbill:bizOrderQcBill:view")
	@RequestMapping(value = "ajaxDetailsPic")
	@ResponseBody
	public Map<Object, Object> ajaxDetailsPic(Integer qcBillId, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		// 通过质检单id查询质检图片
		List<ReportCheckDetailsPic> picList = bizOrderQcBillService.findPic(qcBillId);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);

		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", picList);

		return mapObject;
	}

}