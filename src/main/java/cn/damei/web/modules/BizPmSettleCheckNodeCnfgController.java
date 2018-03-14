/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.CheckNode;
import cn.damei.service.modules.CheckNodeService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizPmSettleCheckNodeCnfg;
import cn.damei.service.modules.BizPmSettleCheckNodeCnfgService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.common.utils.StringUtils;

/**
 * 项目经理结算关联约检节点设置Controller
 * @author wyb
 * @version 2016-12-26
 */
@Controller
@RequestMapping(value = "${adminPath}/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg")
public class BizPmSettleCheckNodeCnfgController extends BaseController {

	@Autowired
	private BizPmSettleCheckNodeCnfgService bizPmSettleCheckNodeCnfgService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private CheckNodeService checkNodeService;
	
	@ModelAttribute
	public BizPmSettleCheckNodeCnfg get(@RequestParam(required=false) Integer id) {
		BizPmSettleCheckNodeCnfg entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizPmSettleCheckNodeCnfgService.get(id);
		}
		if (entity == null){
			entity = new BizPmSettleCheckNodeCnfg();
		}
		return entity;
	}
	
	@RequiresPermissions("bizpmsettlechecknodecnfg:bizPmSettleCheckNodeCnfg:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmSettleCheckNodeCnfg bizPmSettleCheckNodeCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizPmSettleCheckNodeCnfg.getStoreId()){
			if(null!=user.getStoreId()){
				bizPmSettleCheckNodeCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizPmSettleCheckNodeCnfg.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizPmSettleCheckNodeCnfg.setProjectMode(be.getProjectMode());
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
						bizPmSettleCheckNodeCnfg.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfgList";
	}
	@RequiresPermissions("bizpmsettlechecknodecnfg:bizPmSettleCheckNodeCnfg:view")
	@RequestMapping(value = {"list2", ""})
	public String list2(BizPmSettleCheckNodeCnfg bizPmSettleCheckNodeCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizPmSettleCheckNodeCnfg.getStoreId()){
			if(null!=user.getStoreId()){
				bizPmSettleCheckNodeCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizPmSettleCheckNodeCnfg.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizPmSettleCheckNodeCnfg.setProjectMode(be.getProjectMode());
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
						bizPmSettleCheckNodeCnfg.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizPmSettleCheckNodeCnfg> page = bizPmSettleCheckNodeCnfgService.findPage(new Page<BizPmSettleCheckNodeCnfg>(request, response), bizPmSettleCheckNodeCnfg); 
		model.addAttribute("page", page);
		return "modules/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfgList";
	}

	
	/**
	 * 加载约检节点
	 * @param storeId
	 * @param projectMode
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "checkNode")
	public @ResponseBody List<CheckNode> checkNode(String storeId,String projectMode,HttpServletRequest request, HttpServletResponse response, Model model) {
		CheckNode node = new CheckNode();
		if(StringUtils.isNotBlank(storeId)){
			node.setStoreId(Integer.valueOf(storeId));
		}
		node.setProjectMode(projectMode);
		//根据门店和工程模式查找约检节点
		List<CheckNode> checkNodeList = checkNodeService.findList(node);
		return checkNodeList;
	}
	
	/**
	 * 加载约检节点Two
	 * @param storeId
	 * @param projectMode
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "checkNodeTwo")
	public @ResponseBody List<CheckNode> checkNodeTwo(String storeId,String projectMode,String qcCheckNodeId,HttpServletRequest request, HttpServletResponse response, Model model) {
		CheckNode node = new CheckNode();
		if(StringUtils.isNotBlank(storeId)){
			node.setStoreId(Integer.valueOf(storeId));
		}
		node.setProjectMode(projectMode);
		//根据门店和工程模式查找约检节点
		List<CheckNode> checkNodeList = checkNodeService.findList(node);
		
		if(StringUtils.isNotBlank(qcCheckNodeId)){
				Iterator<CheckNode> iterator = checkNodeList.iterator();
				while (iterator.hasNext()) {
					CheckNode a = iterator.next();
					if (a.getId().equals(Integer.valueOf(qcCheckNodeId))) {
						iterator.remove();
					}
				}
		}
		
		
		return checkNodeList;
	}
	
	
	@RequiresPermissions("bizpmsettlechecknodecnfg:bizPmSettleCheckNodeCnfg:view")
	@RequestMapping(value = "form")
	public String form(BizPmSettleCheckNodeCnfg bizPmSettleCheckNodeCnfg, Model model) {
		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
		model.addAttribute("bizPmSettleCheckNodeCnfg", bizPmSettleCheckNodeCnfg);
		return "modules/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfgForm";
	}

	@RequiresPermissions("bizpmsettlechecknodecnfg:bizPmSettleCheckNodeCnfg:edit")
	@RequestMapping(value = "save")
	public String save(BizPmSettleCheckNodeCnfg bizPmSettleCheckNodeCnfg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmSettleCheckNodeCnfg)){
			return form(bizPmSettleCheckNodeCnfg, model);
		}
		
		//修改
		if(null!=bizPmSettleCheckNodeCnfg.getId()){
			List<BizPmSettleCheckNodeCnfg> list = bizPmSettleCheckNodeCnfgService.findList(bizPmSettleCheckNodeCnfg);
			if(null!=list && list.size()>0){
				for(BizPmSettleCheckNodeCnfg last:list){
					if(last.getId().equals(bizPmSettleCheckNodeCnfg.getId())){
						bizPmSettleCheckNodeCnfgService.save(bizPmSettleCheckNodeCnfg);
						addMessage(redirectAttributes, "保存项目经理结算关联约检节点设置成功");
					}else{
						addMessage(redirectAttributes, "项目经理结算关联约检节点设置已经存在，保存失败");
					}
				}
			}else{
				bizPmSettleCheckNodeCnfgService.save(bizPmSettleCheckNodeCnfg);
				addMessage(redirectAttributes, "保存项目经理结算关联约检节点设置成功");
			}
		}else{
			//新增
			List<BizPmSettleCheckNodeCnfg> list = bizPmSettleCheckNodeCnfgService.findList(bizPmSettleCheckNodeCnfg);
			if(null!=list && list.size()>0){
				addMessage(redirectAttributes, "项目经理结算关联约检节点设置已经存在,保存失败");
			}else{
				bizPmSettleCheckNodeCnfgService.save(bizPmSettleCheckNodeCnfg);
				addMessage(redirectAttributes, "保存项目经理结算关联约检节点设置成功");
			}
		}
		
		
		return "redirect:"+Global.getAdminPath()+"/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/list2?repage";
	}
	
	@RequiresPermissions("bizpmsettlechecknodecnfg:bizPmSettleCheckNodeCnfg:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmSettleCheckNodeCnfg bizPmSettleCheckNodeCnfg, RedirectAttributes redirectAttributes) {
		
		if (bizPmSettleCheckNodeCnfg.getIsEnabled().equals("1")) {
			bizPmSettleCheckNodeCnfg.setIsEnabled("0");
		} else {
			bizPmSettleCheckNodeCnfg.setIsEnabled("1");
		}
		
		bizPmSettleCheckNodeCnfgService.delete(bizPmSettleCheckNodeCnfg);
		
		if(bizPmSettleCheckNodeCnfg.getIsEnabled().equals("0")){
			addMessage(redirectAttributes, "停用成功");
		}else{
			addMessage(redirectAttributes, "启用成功");
		}
		
		return "redirect:"+Global.getAdminPath()+"/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/list2?repage";
	}

}