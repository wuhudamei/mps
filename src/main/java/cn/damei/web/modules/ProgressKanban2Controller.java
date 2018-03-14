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
import cn.damei.entity.modules.ProgressKanban2;
import cn.damei.service.modules.ProgressKanban2Service;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/progresskanban2/progressKanban2")
public class ProgressKanban2Controller extends BaseController {

	@Autowired
	private ProgressKanban2Service progressKanban2Service;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizConstructionScheduleService bizConstructionScheduleService;
	
	@Autowired
	private BizNodePlanService bizNodePlanService;
	
	@Autowired
	private BizDiscloseService bizDiscloseService;

	@ModelAttribute
	public ProgressKanban2 get(@RequestParam(required = false) Integer id) {
		ProgressKanban2 entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = progressKanban2Service.get(id);
		}
		if (entity == null) {
			entity = new ProgressKanban2();
		}
		return entity;
	}

	@RequiresPermissions("progresskanban2:progressKanban2:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(ProgressKanban2 progressKanban2, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();

		if(null == progressKanban2.getStoreId()){
			if(null != user.getStoreId()){
				progressKanban2.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(progressKanban2.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						progressKanban2.setProjectMode(be.getProjectMode());
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
						progressKanban2.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/progressKanban/kanbanList2";
	}
	

	@RequiresPermissions("progresskanban2:progressKanban2:view")
	@RequestMapping(value = { "list", "" })
	public String list(ProgressKanban2 progressKanban2, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();

		if(null == progressKanban2.getStoreId()){
			if(null != user.getStoreId()){
				progressKanban2.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(progressKanban2.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						progressKanban2.setProjectMode(be.getProjectMode());
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
						progressKanban2.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		Page<ProgressKanban2> page = progressKanban2Service.findPage(new Page<ProgressKanban2>(request, response),
				progressKanban2);
		
		Integer stroeID = Integer.valueOf(progressKanban2.getStoreId());
		String isOldHouse = progressKanban2.getHouseIsNew();
		logger.info("选取的门店编号：" + stroeID+"新老房："+isOldHouse);
		

		List<BizConstructionSchedule> csList2 = bizConstructionScheduleService.getByStoreIdAndDelflag(stroeID,isOldHouse);
		for(BizConstructionSchedule bcs:csList2){
			logger.info("获得所有节点名称：" + bcs.getConstructionScheduleName());
			list.add(Integer.valueOf(bcs.getSort()));
		}
		
		List<BizNodePlan> npList2 = null;
		if(list.size() > 0){
			npList2 = bizNodePlanService.getByOrderIdListInIndex(list);
		}
		
		List<BizDisclose> odList2 = bizDiscloseService.getByList();
		
		
		model.addAttribute("orderDiscloseList", odList2);
		model.addAttribute("page", page);
		model.addAttribute("nodePlanList", npList2);
		model.addAttribute("constructionScheduleList", csList2);
		return "modules/progressKanban/kanbanList2";
	}
	


}
