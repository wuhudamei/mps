package cn.damei.web.mobile.Manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.PackProcedure;
import cn.damei.entity.mobile.Manager.TaskPackage;
import cn.damei.service.mobile.Manager.TaskPackageService;


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class TaskPackageController {

	@Autowired
	private TaskPackageService packageService;

	@RequestMapping(value = { "packList", "" })
	public String packageList(TaskPackage taskPackage, Model model, HttpServletRequest request) {


Manager manager =	SessionUtils.getManagerSession(request);
		
		taskPackage.setItemManagerId(manager.getId());

		List<TaskPackage> packList = packageService.getAllPackage(taskPackage);
		List<String> stateName = packageService.selectStateName(manager.getId());
		Set<String> nameList = 	new HashSet<String>();
		
		for (TaskPackage name : packList) {
			nameList.add(name.getPackageName());
			
		}
		
	model.addAttribute("nameList", nameList);
		model.addAttribute("packList", packList);
		model.addAttribute("stateName", stateName);

		return "mobile/modules/Manager/task_query";
	}

	@RequestMapping(value = { "packDetail", "" })
	public String packDetail(TaskPackage taskPackage, Model model) {


		TaskPackage pack = packageService.getPackById(taskPackage.getPackageId());


		pack.setLeaderPhone(packageService.getLeaderPhoneById(pack.getLeaderId()));


		model.addAttribute("pack", pack);

		List<PackProcedure> procedureList = packageService.findProcedureByPackId(taskPackage.getPackageId());

		model.addAttribute("procedureList", procedureList);

		return "mobile/modules/Manager/budget";
	}


	@RequestMapping(value={"packCondition",""})
	public @ResponseBody List<TaskPackage> packCondition(TaskPackage taskPack,HttpServletRequest request) {
		{
			Manager manager = (Manager) request.getSession().getAttribute("manager");
			taskPack.setItemManagerId(manager.getId());

			List<TaskPackage> packList = packageService.getAllPackage(taskPack);
		
		
		
		return packList;
		
	}

	}
	
}
