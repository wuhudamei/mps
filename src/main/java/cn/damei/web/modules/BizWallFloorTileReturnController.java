
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
import cn.damei.entity.modules.BizWallFloorTileReturn;
import cn.damei.service.modules.BizWallFloorTileReturnService;


@Controller
@RequestMapping(value = "${adminPath}/bizwallfloortilereturn/bizWallFloorTileReturn")
public class BizWallFloorTileReturnController extends BaseController {

	@Autowired
	private BizWallFloorTileReturnService bizWallFloorTileReturnService;
	
	@ModelAttribute
	public BizWallFloorTileReturn get(@RequestParam(required=false) Integer id) {
		BizWallFloorTileReturn entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizWallFloorTileReturnService.get(id);
		}
		if (entity == null){
			entity = new BizWallFloorTileReturn();
		}
		return entity;
	}
	
	@RequiresPermissions("bizwallfloortilereturn:bizWallFloorTileReturn:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizWallFloorTileReturn bizWallFloorTileReturn, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizWallFloorTileReturn> page = bizWallFloorTileReturnService.findPage(new Page<BizWallFloorTileReturn>(request, response), bizWallFloorTileReturn); 
		model.addAttribute("page", page);
		return "modules/bizwallfloortilereturn/bizWallFloorTileReturnList";
	}

	@RequiresPermissions("bizwallfloortilereturn:bizWallFloorTileReturn:view")
	@RequestMapping(value = "form")
	public String form(BizWallFloorTileReturn bizWallFloorTileReturn, Model model) {
		model.addAttribute("bizWallFloorTileReturn", bizWallFloorTileReturn);
		return "modules/bizwallfloortilereturn/bizWallFloorTileReturnForm";
	}

	@RequiresPermissions("bizwallfloortilereturn:bizWallFloorTileReturn:edit")
	@RequestMapping(value = "save")
	public String save(BizWallFloorTileReturn bizWallFloorTileReturn, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizWallFloorTileReturn)){
			return form(bizWallFloorTileReturn, model);
		}
		bizWallFloorTileReturnService.save(bizWallFloorTileReturn);
		addMessage(redirectAttributes, "保存墙地砖退货表成功");
		return "redirect:"+Global.getAdminPath()+"/bizwallfloortilereturn/bizWallFloorTileReturn/?repage";
	}
	
	@RequiresPermissions("bizwallfloortilereturn:bizWallFloorTileReturn:edit")
	@RequestMapping(value = "delete")
	public String delete(BizWallFloorTileReturn bizWallFloorTileReturn, RedirectAttributes redirectAttributes) {
		bizWallFloorTileReturnService.delete(bizWallFloorTileReturn);
		addMessage(redirectAttributes, "删除墙地砖退货表成功");
		return "redirect:"+Global.getAdminPath()+"/bizwallfloortilereturn/bizWallFloorTileReturn/?repage";
	}

}