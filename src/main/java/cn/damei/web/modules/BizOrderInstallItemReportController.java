/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderInstallItemReport;
import cn.damei.service.modules.BizOrderInstallItemReportService;

/**
 * 工程部主材工期统计表
 */
@Controller
@RequestMapping(value = "${adminPath}/bizOrderInstallItemReport/bizOrderInstallItemReport")
public class BizOrderInstallItemReportController extends BaseController {

	@Autowired
	private BizOrderInstallItemReportService bizOrderInstallItemReportService;
	
	@ModelAttribute
	public BizOrderInstallItemReport get(@RequestParam(required=false) Integer id) {
		BizOrderInstallItemReport entity = null;
		if (id != null){
			entity = bizOrderInstallItemReportService.get(id);
		}
		if (entity == null){
			entity = new BizOrderInstallItemReport();
		}
		return entity;
	}
	
	@RequiresPermissions("bizOrderInstallItemReport:bizOrderInstallItemReport:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BizOrderInstallItemReport bizOrderInstallItemReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/bizorderinstallitemreport/bizOrderInstallItemReportList";
	}
	
	@RequiresPermissions("bizOrderInstallItemReport:bizOrderInstallItemReport:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOrderInstallItemReport bizOrderInstallItemReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderInstallItemReport> page = bizOrderInstallItemReportService.findPage(new Page<BizOrderInstallItemReport>(request, response), bizOrderInstallItemReport); 
		model.addAttribute("page", page);
		return "modules/bizorderinstallitemreport/bizOrderInstallItemReportList";
	}

	

	

}