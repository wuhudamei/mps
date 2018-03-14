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

/**
 * 待办事项controller
 * @author lzm
 * @version 2017-7-14
 */
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
	
	//今日待办查询
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
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			toDoItemEntity.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}

		Page<ToDoItemEntity> page = toDoItemService
				.findPage(new Page<ToDoItemEntity>(request, response), toDoItemEntity);
		/*for (QualityControlFineUpdateEntity item : page.getList()) {
			StringBuilder sb = new StringBuilder();
			List<QualityControlFineUpdateEntity> name = qualityControlFineUpdateService.findName(item.getCheckItemId());

			for (QualityControlFineUpdateEntity qualityControlFineUpdateEntity : name) {
				sb.append(qualityControlFineUpdateEntity.getIllegalName());
				sb.append(",");
			}
			item.setIllegalName(sb.toString());

		}
*/
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
	/*
	 * 更新是否查看或处理，type:表示查看或处理，0表示更新查看，1表示更新处理,2表示都更新
	 */
	@RequestMapping(value = {"updateViewdOrSolvedByObj", ""})
	public String updateViewdOrSolvedByObj(String id,String type){
		toDoItemService.updateViewdOrSolvedByObj(id,type);
		return "modules/PQC/toDoItem/toDoItemList";
	}
}
