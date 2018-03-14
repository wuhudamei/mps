/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

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
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizWallFloorTileOrderCount;
import cn.damei.service.modules.BizWallFloorTileOrderCountService;

/**
 * 墙地砖订单统计表Controller
 * @author wyb
 * @version 2017-08-01
 */
@Controller
@RequestMapping(value = "${adminPath}/bizwallfloortileordercount/bizWallFloorTileOrderCount")
public class BizWallFloorTileOrderCountController extends BaseController {

	@Autowired
	private BizWallFloorTileOrderCountService bizWallFloorTileOrderCountService;
	
	@ModelAttribute
	public BizWallFloorTileOrderCount get(@RequestParam(required=false) Integer id) {
		BizWallFloorTileOrderCount entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizWallFloorTileOrderCountService.get(id);
		}
		if (entity == null){
			entity = new BizWallFloorTileOrderCount();
		}
		return entity;
	}
	
	@RequiresPermissions("bizwallfloortileordercount:bizWallFloorTileOrderCount:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizWallFloorTileOrderCount bizWallFloorTileOrderCount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizWallFloorTileOrderCount> page = bizWallFloorTileOrderCountService.findPage(new Page<BizWallFloorTileOrderCount>(request, response), bizWallFloorTileOrderCount); 
		model.addAttribute("page", page);
		return "modules/bizwallfloortileordercount/bizWallFloorTileOrderCountList";
	}

	@RequiresPermissions("bizwallfloortileordercount:bizWallFloorTileOrderCount:view")
	@RequestMapping(value = "form")
	public String form(BizWallFloorTileOrderCount bizWallFloorTileOrderCount, Model model) {
		model.addAttribute("bizWallFloorTileOrderCount", bizWallFloorTileOrderCount);
		return "modules/bizwallfloortileordercount/bizWallFloorTileOrderCountForm";
	}

	@RequiresPermissions("bizwallfloortileordercount:bizWallFloorTileOrderCount:edit")
	@RequestMapping(value = "save")
	public String save(BizWallFloorTileOrderCount bizWallFloorTileOrderCount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizWallFloorTileOrderCount)){
			return form(bizWallFloorTileOrderCount, model);
		}
		bizWallFloorTileOrderCountService.save(bizWallFloorTileOrderCount);
		addMessage(redirectAttributes, "保存墙地砖订单统计表成功");
		return "redirect:"+Global.getAdminPath()+"/bizwallfloortileordercount/bizWallFloorTileOrderCount/?repage";
	}
	
	@RequiresPermissions("bizwallfloortileordercount:bizWallFloorTileOrderCount:edit")
	@RequestMapping(value = "delete")
	public String delete(BizWallFloorTileOrderCount bizWallFloorTileOrderCount, RedirectAttributes redirectAttributes) {
		bizWallFloorTileOrderCountService.delete(bizWallFloorTileOrderCount);
		addMessage(redirectAttributes, "删除墙地砖订单统计表成功");
		return "redirect:"+Global.getAdminPath()+"/bizwallfloortileordercount/bizWallFloorTileOrderCount/?repage";
	}

}