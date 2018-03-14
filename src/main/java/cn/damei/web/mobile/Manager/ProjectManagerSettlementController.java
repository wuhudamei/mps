package cn.damei.web.mobile.Manager;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.modules.BizPmPreIndustrySettleBill;
import cn.damei.service.modules.BizPmPreIndustrySettleBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.config.Global;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.ProjectManagerSettlement;
import cn.damei.service.mobile.Manager.ProjectManagerSettlementService;

@Controller
@RequestMapping(value="${adminPath}/app/manager/projectManagerSettlement")
public class ProjectManagerSettlementController {

	@Autowired
	private ProjectManagerSettlementService projectManagerSettlementService;

	@Autowired
	private BizPmPreIndustrySettleBillService bizPmPreIndustrySettleBillService;

	@RequestMapping(value="managerBudget")
	public String list(){
		
		return "/mobile/modules/Manager/projectmanagersettlement/managerBudget";
	}

	@RequestMapping(value="budgetSureList")
	public String list2(ProjectManagerSettlement projectManagerSettlement,String myflag,Model model,HttpServletRequest request){
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		projectManagerSettlement.setItemManagerId(manager.getId());
		List<ProjectManagerSettlement> findList = projectManagerSettlementService.findList(projectManagerSettlement);
		model.addAttribute("list", findList);
		model.addAttribute("myflag", myflag);
		return "/mobile/modules/Manager/projectmanagersettlement/budgetSureList";
	}

	@RequestMapping(value="confirmAccountEnd")
	public String list3(ProjectManagerSettlement projectManagerSettlement,Model model,HttpServletRequest request){
		List<ProjectManagerSettlement> findList = projectManagerSettlementService.findList(projectManagerSettlement);
		if(findList == null || findList.size() == 0){
			return "redirect:"+Global.getAdminPath()+"/app/manager/projectManagerSettlement/budgetSureList";
		}

		ProjectManagerSettlement projectManagerSettlement1 = findList.get(0);
		if(projectManagerSettlement.getSettleBillType().equals("2")){

			Map<String,Object> midwaySettleBillParam = new HashMap<String,Object>();
			midwaySettleBillParam.put("orderId", findList.get(0).getOrderId());
			midwaySettleBillParam.put("settleBillType", 1);
			BizPmPreIndustrySettleBill midwaySettleBill = bizPmPreIndustrySettleBillService.queryPmPreIndustrySettleBillByParam(midwaySettleBillParam);
			model.addAttribute("midwaySettleBill", midwaySettleBill);

		}
		model.addAttribute("settleBill", findList.get(0));
        if(projectManagerSettlement1.getIsNewSettleBill().equals("0")){


            return "/mobile/modules/Manager/projectmanagersettlement/confirmAccountMid";
        }else {
            return "/mobile/modules/Manager/projectmanagersettlement/newConfirmAccountMid";
        }
	}

	@RequestMapping(value="orderBudgetList")
	public String list4(Model model,ProjectManagerSettlement projectManagerSettlement,HttpServletRequest request){
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		projectManagerSettlement.setItemManagerId(manager.getId());
		List<ProjectManagerSettlement> findList = projectManagerSettlementService.findSettlementEndList(projectManagerSettlement);
		model.addAttribute("list", findList);
		model.addAttribute("projectManagerSettlement", projectManagerSettlement);
		return "/mobile/modules/Manager/projectmanagersettlement/orderBudgetList";
	}
	

	@RequestMapping(value="detailsAccountMid")
	public String list5(ProjectManagerSettlement projectManagerSettlement,Model model){
		
		ProjectManagerSettlement detail = projectManagerSettlementService.findSettlementEndDetails(projectManagerSettlement);
		
		if(detail.getSettleBillType().equals("2")){


			Map<String,Object> midwaySettleBillParam = new HashMap<String,Object>();
			midwaySettleBillParam.put("orderId", detail.getOrderId());
			midwaySettleBillParam.put("settleBillType", 1);
			BizPmPreIndustrySettleBill midwaySettleBill = bizPmPreIndustrySettleBillService.queryPmPreIndustrySettleBillByParam(midwaySettleBillParam);
			model.addAttribute("midwaySettleBill", midwaySettleBill);
		}
		model.addAttribute("settleBill", detail);
		model.addAttribute("flag", 1);
		if(detail.getIsNewSettleBill().equals("0")){
            return "/mobile/modules/Manager/projectmanagersettlement/confirmAccountMid";
        }else {
            return "/mobile/modules/Manager/projectmanagersettlement/newConfirmAccountMid";
        }

	}

	@RequestMapping(value="detailsAccountEnd")
	public String list6(){
		return "/mobile/modules/Manager/projectmanagersettlement/detailsAccountEnd";
	}

	@RequestMapping(value="agree")
	public String agree(ProjectManagerSettlement projectManagerSettlement,HttpServletRequest request,Model model) throws UnsupportedEncodingException{
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		ProjectManagerSettlement projectManagerSettlement2 = projectManagerSettlementService.get(projectManagerSettlement);
		if(projectManagerSettlement2!=null){
			return "redirect:"+Global.getAdminPath()+"/app/manager/projectManagerSettlement/budgetSureList?myflag=1";
		}
		projectManagerSettlementService.updateManagerSettlement(projectManagerSettlement,manager);
		return "redirect:"+Global.getAdminPath()+"/app/manager/projectManagerSettlement/budgetSureList";
	}
	
}
