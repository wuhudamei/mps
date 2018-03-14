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
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.ArrangeCheckDoneDetailQuery;
import cn.damei.service.modules.ArrangeCheckDoneDetailQueryService;

/**
 * 约检节点验收明细查询Controller
 * 
 * @author mh
 * @version 2017-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/arrangecheckdonedetailquery/arrangeCheckDoneDetailQuery")
public class ArrangeCheckDoneDetailQueryController extends BaseController {

	@Autowired
	private ArrangeCheckDoneDetailQueryService arrangeCheckDoneDetailQueryService;

	@ModelAttribute
	public ArrangeCheckDoneDetailQuery get(@RequestParam(required = false) String id) {
		ArrangeCheckDoneDetailQuery entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = arrangeCheckDoneDetailQueryService.get(id);
		}
		if (entity == null) {
			entity = new ArrangeCheckDoneDetailQuery();
		}
		return entity;
	}

	@RequiresPermissions("arrangecheckdonedetailquery:arrangeCheckDoneDetailQuery:view")
	@RequestMapping(value = { "list", "" })
	public String list(ArrangeCheckDoneDetailQuery arrangeCheckDoneDetailQuery, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/PQC/arrangecheckdonedetailquery/arrangeCheckDoneDetailQueryList";
	}

	@RequiresPermissions("arrangecheckdonedetailquery:arrangeCheckDoneDetailQuery:view")
	@RequestMapping(value = { "list2", "" })
	public String list2(ArrangeCheckDoneDetailQuery arrangeCheckDoneDetailQuery, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ArrangeCheckDoneDetailQuery> page = arrangeCheckDoneDetailQueryService.findPage(new Page<ArrangeCheckDoneDetailQuery>(request, response), arrangeCheckDoneDetailQuery);
		model.addAttribute("page", page);
		model.addAttribute("entity", arrangeCheckDoneDetailQuery);
		return "modules/PQC/arrangecheckdonedetailquery/arrangeCheckDoneDetailQueryList";
	}

	@RequiresPermissions("arrangecheckdonedetailquery:arrangeCheckDoneDetailQuery:view")
	@RequestMapping(value = "findPqcByBillId")
	public String findPqcByBillId(String qcbillId, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> pqcList = arrangeCheckDoneDetailQueryService.findPqcByBillId(qcbillId);
		model.addAttribute("picList", pqcList);

		return "modules/PQC/arrangecheckdonedetailquery/arrangeCheckDonePqc";
	}

	@RequestMapping(value = "ajaxFindPqcByBillId")
	@ResponseBody
	public Map<Object, Object> ajaxFindPqcByBillId(String qcbillId, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> pqcList = arrangeCheckDoneDetailQueryService.findPqcByBillId(qcbillId);
		model.addAttribute("picList", pqcList);

		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pqcList);

		return mapObject;
	}

}