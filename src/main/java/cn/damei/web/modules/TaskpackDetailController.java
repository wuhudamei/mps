/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.TaskPackDetails;
import cn.damei.entity.modules.WorkerInfo;
import cn.damei.service.modules.TaskpackDetailService;

/**
 * 任务包详情
 * @author 张康健
 * @version 2017-3-25
 */
@Controller
@RequestMapping(value = "${adminPath}/TaskpackDetail/TaskpackageDtailsLook")
public class TaskpackDetailController extends BaseController {
	@Autowired
	private TaskpackDetailService taskpackDetailService;
/*	@ModelAttribute
	public TaskPackDetails get(@RequestParam(required = false) Integer id) {
		TaskPackDetails entity = null;
		if (id != null){
			entity = taskpackDetailService.get(id);
		}
		if (entity == null){
			entity = new TaskPackDetails();
		}
		return entity;
	}*/



	@RequestMapping(value ="details")
	public String details(String id,Model model){
		TaskPackDetails tpd = taskpackDetailService.findTaskPackDetailsById(id);
		model.addAttribute("details", tpd);
		List<WorkerInfo> list = taskpackDetailService.findWorkerInfoByid(id);
		model.addAttribute("workers", list);
		return "modules/taskpackdetails/taskpackdetails";
		
	}

}