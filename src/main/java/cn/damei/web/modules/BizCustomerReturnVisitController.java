/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizCustomerReturnVisit;
import cn.damei.service.modules.BizCustomerReturnVisitService;
import cn.damei.entity.modules.StatusDto;

/**
 * 客户回访节点Controller
 * @author LiwanCai
 * @version 2017-05-22
 */
@Controller
@RequestMapping(value = "${adminPath}/customerreturnvisit/bizCustomerReturnVisit")
public class BizCustomerReturnVisitController extends BaseController {

	@Autowired
	private BizCustomerReturnVisitService bizCustomerReturnVisitService;
	
	@ModelAttribute
	public BizCustomerReturnVisit get(@RequestParam(required=false) String id) {
		BizCustomerReturnVisit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizCustomerReturnVisitService.get(id);
		}
		if (entity == null){
			entity = new BizCustomerReturnVisit();
		}
		return entity;
	}
	
	@RequiresPermissions("customerreturnvisit:bizCustomerReturnVisit:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizCustomerReturnVisit bizCustomerReturnVisit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizCustomerReturnVisit> page = bizCustomerReturnVisitService.findPage(new Page<BizCustomerReturnVisit>(request, response), bizCustomerReturnVisit); 
		model.addAttribute("page", page);
		return "modules/customerreturnvisit/bizCustomerReturnVisitList";
	}

	@RequiresPermissions("customerreturnvisit:bizCustomerReturnVisit:view")
	@RequestMapping(value = "form")
	public String form(BizCustomerReturnVisit bizCustomerReturnVisit, Model model) {
		model.addAttribute("badReasonList", bizCustomerReturnVisitService.queryReturnVisitBadType());
		if( !StringUtils.isBlank( bizCustomerReturnVisit.getId() ) ){
			model.addAttribute("bizCustomerReturnVisit", bizCustomerReturnVisitService.getById(bizCustomerReturnVisit.getId()));
			model.addAttribute("projectNodeList", bizCustomerReturnVisitService.queryProjectNodeListByStoreId( Integer.parseInt( bizCustomerReturnVisit.getStoreId() ) ,bizCustomerReturnVisit.getProjectMode()));
		}
		
		return "modules/customerreturnvisit/bizCustomerReturnVisitForm";
	}
	
	@RequiresPermissions("customerreturnvisit:bizCustomerReturnVisit:edit")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	@ResponseBody
	public Object save(BizCustomerReturnVisit bizCustomerReturnVisit,HttpServletRequest request) {
		try{
			String[] questionContent = request.getParameterValues("questionContent[]");
			String[] statisticsDepartment =  request.getParameterValues("statisticsDepartment[]");
			String[] replyMode = request.getParameterValues("replyMode[]");
			String[] itemContent = request.getParameterValues("itemContent[]");
			bizCustomerReturnVisitService.saveOrUpdate(bizCustomerReturnVisit,questionContent,statisticsDepartment,replyMode,itemContent);
			return StatusDto.buildDataSuccessStatusDto("保存成功");
		}catch(Exception e){
			e.printStackTrace();
			return StatusDto.buildDataFailureStatusDto("保存失败");
		}
	}
	
	@RequiresPermissions("customerreturnvisit:bizCustomerReturnVisit:edit")
	@RequestMapping(value = "delete")
	public String delete(BizCustomerReturnVisit bizCustomerReturnVisit, RedirectAttributes redirectAttributes) {
		bizCustomerReturnVisitService.delete(bizCustomerReturnVisit);
		addMessage(redirectAttributes, "删除客户回访节点成功");
		return "redirect:"+Global.getAdminPath()+"/customerreturnvisit/bizCustomerReturnVisit/?repage";
	}
	
	/**
	 * 根据门店ID获取工程节点信息
	 * @param storeId
	 * @return
	 */
	@RequestMapping(value = "queryProjectNode")
	@ResponseBody
	public Object queryProjectNode(@RequestParam(value = "storeId", required = true) int storeId,@RequestParam(value = "projectMode", required = true) String projectMode) {
		return StatusDto.buildDataSuccessStatusDto(bizCustomerReturnVisitService.queryProjectNodeListByStoreId(storeId,projectMode));
	}
	
	/**
	 * 根据节点值判断是是否已经设置过节点问题
	 * @param projectNode
	 * @return
	 */
	@RequestMapping(value = "returnVisitNodeValidate")
	@ResponseBody
	public Object returnVisitNodeValidate(
			@RequestParam(value = "projectNode", required = true) String projectNode,
			@RequestParam(value = "storeId", required = true) String storeId,
			@RequestParam(value = "projectMode", required = true) String projectMode){
		if( StringUtils.isBlank(  bizCustomerReturnVisitService.getReturnVisitName(projectNode,storeId,projectMode) ) ){
			return StatusDto.buildDataSuccessStatusDto();
		}else{
			return StatusDto.buildDataFailureStatusDto();
		}
	}
	
	/**
	 * 根据门店id获取对应的回访节点信息
	 * @param projectNode
	 * @return
	 */
	@RequestMapping(value = "queryReturnVisitNodeByStoreId",method=RequestMethod.GET)
	@ResponseBody
	public Object queryReturnVisitNodeByStoreId(@RequestParam(value = "storeId", required = true) String storeId,@RequestParam(value = "projectMode", required = true) String projectMode){
		List<Map<String,Object>> result = bizCustomerReturnVisitService.queryReturnVisitNodeByStoreId(storeId,projectMode);
		return StatusDto.buildDataSuccessStatusDto(result);
	}
	
}