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

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月19日 下午5:05:38 任务包查询
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class TaskPackageController {

	@Autowired
	private TaskPackageService packageService;

	@RequestMapping(value = { "packList", "" })
	public String packageList(TaskPackage taskPackage, Model model, HttpServletRequest request) {

		// 已登录的项目经理
Manager manager =	SessionUtils.getManagerSession(request);
		
		taskPackage.setItemManagerId(manager.getId());
		// 查询项目经理下所有的任务包
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
		// 1:根据任务包id 查询任务包

		TaskPackage pack = packageService.getPackById(taskPackage.getPackageId());

		// 放入组长手机
		pack.setLeaderPhone(packageService.getLeaderPhoneById(pack.getLeaderId()));

		// 2:放入model中
		model.addAttribute("pack", pack);
		// 3:根据任务包id查询工序 建立Vo
		List<PackProcedure> procedureList = packageService.findProcedureByPackId(taskPackage.getPackageId());
		// 4:查询出的工序放入model中
		model.addAttribute("procedureList", procedureList);

		return "mobile/modules/Manager/budget";
	}

	/**
	 * ajax 动态查询数据
	 * 
	 * @param taskPack
	 * @return
	 */
	@RequestMapping(value={"packCondition",""})
	public @ResponseBody List<TaskPackage> packCondition(TaskPackage taskPack,HttpServletRequest request) {
		{// 已登录的项目经理
			Manager manager = (Manager) request.getSession().getAttribute("manager");
			taskPack.setItemManagerId(manager.getId());
			// 查询项目经理下所有的任务包
			List<TaskPackage> packList = packageService.getAllPackage(taskPack);
		
		
		
		return packList;
		
	}

	}
	
}
