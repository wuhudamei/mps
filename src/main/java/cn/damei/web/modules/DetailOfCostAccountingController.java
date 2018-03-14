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


/** 
* @ClassName: DetailOfCostAccountingController 
* @Description: TODO(成本核算列表查询，导出) 
* @author zkj  
* @date 2017年10月18日 上午11:34:54 
* @version V1.0 
*/
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
	

	/** 
	* @Description: 成本核算查询列表
	* @param @param request
	* @param @param response
	* @param @param detailOfCostAccounting
	* @param @param mode
	* @param @return
	* @author zkj 
	* @date 2017年10月19日 上午11:05:38 
	*/
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request,HttpServletResponse response,DetailOfCostAccounting detailOfCostAccounting,Model mode){
		detailOfCostAccountingService.storeAndProjectMode(this, detailOfCostAccounting, mode);
		Page<DetailOfCostAccounting> findPage = detailOfCostAccountingService.findPage(new Page<DetailOfCostAccounting>(request,response), detailOfCostAccounting);
		mode.addAttribute("page", findPage);
		mode.addAttribute("detailOfCostAccounting", detailOfCostAccounting);
		return "modules/detailOfCostAccounting/detailOfCostAccountingList";
	}

	
	/** 
	* @Description: 导出成本核算表
	* @param @param request
	* @param @param response
	* @param @param detailOfCostAccounting
	* @param @param mode
	* @author zkj 
	* @date 2017年10月19日 上午11:05:28 
	*/
	@RequestMapping(value="/exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,DetailOfCostAccounting detailOfCostAccounting,Model mode){
		detailOfCostAccountingService.exportDetailExcel(detailOfCostAccounting, response);
	}
	
}
