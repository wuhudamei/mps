package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.entity.mobile.Manager.ProjectManagerSettlement;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.service.modules.PreSettleBilllogService;
import cn.damei.common.utils.UserUtils;

@Controller
@RequestMapping(value="${adminPath}/PreSettleBilllog")
public class PreSettleBilllogController {

	@Autowired
	private PreSettleBilllogService preSettleBilllogService;
	
	@ModelAttribute
	public ProjectManagerSettlement get(@RequestParam(required=false) Integer id) {
		ProjectManagerSettlement entity = null;
		if (id != null){

		}
		if (entity == null){
			entity = new ProjectManagerSettlement();
		}
		return entity;
	}
	
	@RequestMapping(value="prelist")
	public String prelist(ProjectManagerSettlement projectManagerSettlement,HttpServletRequest request,HttpServletResponse response,Model model){
		if (UserUtils.getUser().getStoreId() != null) {

			projectManagerSettlement.setStoreId(UserUtils.getUser().getStoreId());
		} else {

			if (projectManagerSettlement.getStoreId() != null && projectManagerSettlement.getStoreId().equals("1")) {

				projectManagerSettlement.setStoreId(null);
			}
		}

		return "/modules/presettlebilllog/presettlebilllogList";
		
	}
	
	@RequestMapping(value="list")
	public String list(ProjectManagerSettlement projectManagerSettlement,HttpServletRequest request,HttpServletResponse response,Model model){
		if (UserUtils.getUser().getStoreId() != null) {

			projectManagerSettlement.setStoreId(UserUtils.getUser().getStoreId());
		} else {

			if (projectManagerSettlement.getStoreId() != null && projectManagerSettlement.getStoreId().equals("1")) {

				projectManagerSettlement.setStoreId(null);
			}
		}
		Page<ProjectManagerSettlement> findPage = preSettleBilllogService.findPage(new Page<ProjectManagerSettlement>(request, response), projectManagerSettlement);
		model.addAttribute("page", findPage);
		return "/modules/presettlebilllog/presettlebilllogList";
		
	}
	
	@RequestMapping(value="middetail")
	public String detail(ProjectManagerSettlement projectManagerSettlement,HttpServletRequest request,HttpServletResponse response,Model model){
		List<ProjectManagerSettlement> findList = preSettleBilllogService.findList(projectManagerSettlement);
		ProjectManagerSettlement projectManagerSettlement2 = findList.get(0);
		projectManagerSettlement2.setSettleBillType(projectManagerSettlement.getSettleBillType());

		List<BizBusinessStatusLog> findDetail = preSettleBilllogService.findDetail(projectManagerSettlement);
		model.addAttribute("page", projectManagerSettlement2);
		model.addAttribute("listLog", findDetail);
		return "/modules/presettlebilllog/presettlebilllogDetail";
		
	}
	
	
}
