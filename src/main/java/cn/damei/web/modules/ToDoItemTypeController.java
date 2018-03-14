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

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.ToDoItemTypeEntity;
import cn.damei.service.modules.ToDoItemTypeService;


@Controller
@RequestMapping(value = "${adminPath}/toDoItemTypeController")
public class ToDoItemTypeController {
	@Autowired
	private ToDoItemTypeService toDoItemTypeService;
	@ModelAttribute
	public ToDoItemTypeEntity get(@RequestParam(required=false) String id) {
		ToDoItemTypeEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = toDoItemTypeService.get(id);
		}
		if (entity == null){
			entity = new ToDoItemTypeEntity();
		}
		return entity;
	}
	@RequiresPermissions("toDoItemType:toDoItemType:view")
	@RequestMapping(value = {"list", ""})
	public String list(ToDoItemTypeEntity toDoItemTypeEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ToDoItemTypeEntity> page = toDoItemTypeService.findPage(new Page<ToDoItemTypeEntity>(request, response), toDoItemTypeEntity); 
		model.addAttribute("page", page);
		model.addAttribute("toDoItemEntity", toDoItemTypeEntity);
		return "modules/PQC/toDoItemType/toDoItemTypeList";
	}
	@RequestMapping(value = {"save", ""})
	public String save(ToDoItemTypeEntity toDoItemTypeEntity){
		toDoItemTypeService.save(toDoItemTypeEntity);
		return "modules/PQC/toDoItemType/toDoItemTypeList";
	}
	
}
