package cn.damei.web.modules;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.entity.modules.DetailOfCostAccounting;
import cn.damei.service.modules.DetailOfCostAccountingService;
import cn.damei.service.modules.BizEmployeeService2;



@Controller
@RequestMapping(value="${adminPath}/detailOfCostAccounting")
public class DetailOfCostAccountingController {

	@Autowired
	private DetailOfCostAccountingService detailOfCostAccountingService;
	@Autowired
	public BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public DetailOfCostAccounting get(@RequestParam(required = false) String id) {
		return new DetailOfCostAccounting();
	}
	


	@RequestMapping(value="/list")
	public String list(HttpServletRequest request,HttpServletResponse response,DetailOfCostAccounting detailOfCostAccounting,Model mode){
		detailOfCostAccountingService.storeAndProjectMode(this, detailOfCostAccounting, mode);
		Page<DetailOfCostAccounting> findPage = detailOfCostAccountingService.findPage(new Page<DetailOfCostAccounting>(request,response), detailOfCostAccounting);
		mode.addAttribute("page", findPage);
		mode.addAttribute("detailOfCostAccounting", detailOfCostAccounting);
		return "modules/detailOfCostAccounting/detailOfCostAccountingList";
	}

	

	@RequestMapping(value="/exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,DetailOfCostAccounting detailOfCostAccounting,Model mode){
		detailOfCostAccountingService.exportDetailExcel(detailOfCostAccounting, response);
	}
	
}
