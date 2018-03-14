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
import cn.damei.entity.modules.ToDoItemEntity;
import cn.damei.service.modules.ToDoItemService;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/toDoItemController")
public class ToDoItemController {
	@Autowired
	private ToDoItemService toDoItemService;
	@ModelAttribute
	public ToDoItemEntity get(@RequestParam(required=false) String id) {
		ToDoItemEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = toDoItemService.get(id);
		}
		if (entity == null){
			entity = new ToDoItemEntity();
		}
		return entity;
	}
	@RequiresPermissions("toDoItem:toDoItem:view")
	@RequestMapping(value = {"toDoItemList", ""})
	public String list(ToDoItemEntity toDoItemEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ToDoItemEntity> page = toDoItemService.findPage(new Page<ToDoItemEntity>(request, response), toDoItemEntity); 
		model.addAttribute("page", page);
		model.addAttribute("toDoItemEntity", toDoItemEntity);
		return "modules/PQC/toDoItem/toDoItemList";
	}
	

	@RequiresPermissions("toDoItem:toDoItem:view")
	@RequestMapping(value = {"toDoItemListToday", ""})
	public String todaylist(ToDoItemEntity toDoItemEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ToDoItemEntity> page = toDoItemService.findPage1(new Page<ToDoItemEntity>(request, response), toDoItemEntity); 
		model.addAttribute("page", page);
		model.addAttribute("toDoItemEntity", toDoItemEntity);
		return "modules/PQC/toDoItem/toDoItemList";
	}
	@RequiresPermissions("toDoItem:toDoItem:view")
	@RequestMapping(value = "toDoItemListInfo")
	public String inspectSignListCheck(ToDoItemEntity toDoItemEntity, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		int x = 0;

		if (!UserUtils.getUser().getOffice().getId().equals("1")) {

			toDoItemEntity.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}

		Page<ToDoItemEntity> page = toDoItemService
				.findPage(new Page<ToDoItemEntity>(request, response), toDoItemEntity);

		if (x > 0) {
			toDoItemEntity.setStoreId(null);

		}
		model.addAttribute("page", page);
		model.addAttribute("checkEntity", toDoItemEntity);

		return "modules/PQC/toDoItem/toDoItemList";
	}
	@RequestMapping(value = {"save", ""})
	public String save(ToDoItemEntity toDoItemEntity){
		toDoItemService.save(toDoItemEntity);
		return "modules/PQC/toDoItem/toDoItemList";
	}

	@RequestMapping(value = {"updateViewdOrSolvedByObj", ""})
	public String updateViewdOrSolvedByObj(String id,String type){
		toDoItemService.updateViewdOrSolvedByObj(id,type);
		return "modules/PQC/toDoItem/toDoItemList";
	}
}
