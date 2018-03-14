package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizNodePlan;
import cn.damei.service.modules.BizDiscloseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.BizNodePlanService;
import cn.damei.entity.modules.BizDisclose;
import cn.damei.entity.modules.BizConstructionSchedule;
import cn.damei.service.modules.BizConstructionScheduleService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.ProgressKanban3;
import cn.damei.service.modules.ProgressKanban3Service;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/progresskanban3/progressKanban3")
public class ProgressKanban3Controller extends BaseController {

	@Autowired
	private ProgressKanban3Service progressKanban3Service;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizConstructionScheduleService bizConstructionScheduleService;
	@Autowired
	private BizNodePlanService bizNodePlanService;
	@Autowired
	private BizDiscloseService bizDiscloseService;

	@ModelAttribute
	public ProgressKanban3 get(@RequestParam(required = false) Integer id) {
		ProgressKanban3 entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = progressKanban3Service.get(id);
		}
		if (entity == null) {
			entity = new ProgressKanban3();
		}
		return entity;
	}

	@RequiresPermissions("progresskanban3:progressKanban3:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(ProgressKanban3 progressKanban3, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();

		if(null == progressKanban3.getStoreId()){
			if(null != user.getStoreId()){
				progressKanban3.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(progressKanban3.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						progressKanban3.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						progressKanban3.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/progressKanban/kanbanList3";
	}
	

	@RequiresPermissions("progresskanban3:progressKanban3:view")
	@RequestMapping(value = { "list", "" })
	public String list(ProgressKanban3 progressKanban3, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();

		if(null == progressKanban3.getStoreId()){
			if(null != user.getStoreId()){
				progressKanban3.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(progressKanban3.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						progressKanban3.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						progressKanban3.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		Page<ProgressKanban3> page = progressKanban3Service.findPage(new Page<ProgressKanban3>(request, response),
				progressKanban3);
		
		Integer stroeID = Integer.valueOf(progressKanban3.getStoreId());
		String isOldHouse = progressKanban3.getHouseIsNew();
		logger.info("选取的门店编号：" + stroeID+"新老房："+isOldHouse);
		
		
		

		List<BizConstructionSchedule> csList3 = bizConstructionScheduleService.getByStoreIdAndDelflag(stroeID,isOldHouse);
		for(BizConstructionSchedule bcs:csList3){
			logger.info("获得所有节点名称：" + bcs.getConstructionScheduleName());
			list.add(Integer.valueOf(bcs.getSort()));
		}
		
		List<BizNodePlan> npList3 = null;
		if(list.size() > 0){
			npList3 = bizNodePlanService.getByOrderIdListInIndex(list);
		}
		
		List<BizDisclose> odList3 = bizDiscloseService.getByList();
		
		
		model.addAttribute("orderDiscloseList", odList3);
		model.addAttribute("page", page);
		model.addAttribute("nodePlanList", npList3);
		model.addAttribute("constructionScheduleList", csList3);
		return "modules/progressKanban/kanbanList3";
	}
	


}
