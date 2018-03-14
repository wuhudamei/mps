/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.ProjectBulletin;
import cn.damei.service.modules.ProjectBulletinService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 订单管理Controller
 * @author llp
 * @version 2016-10-09
 */
@Controller
@RequestMapping(value = "${adminPath}/projectbulletin/projectBulletin")
public class ProjectBulletinController extends BaseController {

	@Autowired
	private ProjectBulletinService projectBulletinService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public ProjectBulletin get(@RequestParam(required=false) Integer id) {
		ProjectBulletin entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = projectBulletinService.get(id);
		}
		if (entity == null){
			entity = new ProjectBulletin();
		}
		return entity;
	}
	
	@RequiresPermissions("projectbulletin:projectBulletin:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProjectBulletin projectBulletin, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null == projectBulletin.getStoreId()){
			System.out.println(user.getStoreId());
			if(null != user.getStoreId()){
				projectBulletin.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(projectBulletin.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						projectBulletin.setProjectMode(be.getProjectMode());
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
						projectBulletin.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<ProjectBulletin> page = projectBulletinService.findPage(new Page<ProjectBulletin>(request, response), projectBulletin);
		
		model.addAttribute("page", page);
		return "modules/projectbulletin/projectBulletinList";
	}

	/**
	 * 进入主页面不进行sql查询
	 * @param projectBulletin
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("projectbulletin:projectBulletin:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(ProjectBulletin projectBulletin, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null == projectBulletin.getStoreId()){
			System.out.println(user.getStoreId());
			if(null != user.getStoreId()){
				projectBulletin.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(projectBulletin.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						projectBulletin.setProjectMode(be.getProjectMode());
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
						projectBulletin.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/projectbulletin/projectBulletinList";
	}
	
	@RequiresPermissions("projectbulletin:projectBulletin:view")
	@RequestMapping(value = "form")
	public String form(ProjectBulletin projectBulletin, Model model) {
		model.addAttribute("projectBulletin", projectBulletin);
		return "modules/projectbulletin/projectBulletinForm";
	}

	@RequiresPermissions("projectbulletin:projectBulletin:edit")
	@RequestMapping(value = "save")
	public String save(ProjectBulletin projectBulletin, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectBulletin)){
			return form(projectBulletin, model);
		}
		projectBulletinService.save(projectBulletin);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/projectbulletin/projectBulletin/?repage";
	}
	
	@RequiresPermissions("projectbulletin:projectBulletin:edit")
	@RequestMapping(value = "delete")
	public String delete(ProjectBulletin projectBulletin, RedirectAttributes redirectAttributes) {
		projectBulletinService.delete(projectBulletin);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/projectbulletin/projectBulletin/?repage";
	}
	
	@RequiresPermissions("projectbulletin:projectBulletin:view")
	@RequestMapping(value = "getByIdAndNodePlanOrderId")
	public String getByIdAndNodePlanOrderId(ProjectBulletin projectBulletin, Model model, HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		logger.info("orderId==" + id);
		
		//List<BizNodePlanProject> npList = bizNodePlanService.getByOrderId(orderId);
		List<ProjectBulletin> pbList = projectBulletinService.getByIdAndNodePlanOrderId(id);
		for(ProjectBulletin p : pbList){
			logger.info("进度节点对象循环输出节点ID：" + p.getNodeIndex() + "\t 节点名称：" + p.getNodeName() + "id编号：" + p.getNodeId());
		}
		
		logger.info("进度节点List：" + pbList.size());
		model.addAttribute("pbList", pbList);
		return "modules/projectbulletin/projectBulletinShow";
	}

	/**
	 * 查看
	 * @return
	 */
	@RequestMapping(value = "showViewNode")
	public String showViewNode(ProjectBulletin projectBulletin, Model model, HttpServletRequest request) {
		Integer orderId = Integer.valueOf(request.getParameter("id"));
		logger.info("orderId==" + orderId);
		
		//List<BizNodePlanProject> npList = bizNodePlanService.getByOrderId(orderId);
		ProjectBulletin projectBull = projectBulletinService.getById(orderId);
		List<ProjectBulletin> pbList = projectBulletinService.getByShowViewOrderId(orderId);
		if(pbList.size() > 0){
			for(ProjectBulletin p : pbList){
				logger.info("进度节点对象循环输出节点ID：" + p.getNodeIndex() + "\t 节点名称：" + p.getNodeName() + "id编号：" + p.getNodeId());
			}
		}
		
		logger.info("进度节点List：" + pbList.size());
		
		model.addAttribute("projectBulletin", projectBull);
		model.addAttribute("pbList", pbList);
		return "modules/projectbulletin/showViewNode";
	}
}