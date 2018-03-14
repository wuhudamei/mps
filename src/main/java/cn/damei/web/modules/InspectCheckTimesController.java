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
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.InspectCheckTimesEntity;
import cn.damei.service.modules.InspectCheckTimesService;
import cn.damei.common.utils.UserUtils;

@Controller
@RequestMapping(value="${adminPath}/inspectCheckTimesQuery")
public class InspectCheckTimesController   extends BaseController{

	
	@Autowired
	private InspectCheckTimesService service;
	
	@ModelAttribute
	public InspectCheckTimesEntity get(@RequestParam(required = false) String id) {
		InspectCheckTimesEntity entity =null;
		if (StringUtils.isNotBlank(id)){
			entity = service.get(id);
		}
		if (entity == null){
			entity = new InspectCheckTimesEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("inspectCheckTimesQuery:inspectCheckTimesQuery:view")
	@RequestMapping(value="list")
	public String    inspectSignList(InspectCheckTimesEntity checkEntity,HttpServletRequest request, HttpServletResponse response, Model model){
	
		return "modules/PQC/inspectCheckTimesQuery/list";
	}
	@RequiresPermissions("inspectCheckTimesQuery:inspectCheckTimesQuery:view")
	@RequestMapping(value="listInfo")
	public String    inspectSignListCheck( InspectCheckTimesEntity checkEntity,HttpServletRequest request, HttpServletResponse response, Model model){
		if(null!=checkEntity.getCheckStartDate()){
			
			model.addAttribute("startDate", checkEntity.getCheckStartDate());
		}
		if(null!=checkEntity.getCheckEndDate()){
			
			model.addAttribute("endDate", checkEntity.getCheckEndDate());
		}
		int x = 0;
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			checkEntity.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}
		
		Page<InspectCheckTimesEntity> page = service.findPage(new Page<InspectCheckTimesEntity>(request, response), checkEntity); 
		if (x > 0) {
			checkEntity.setStoreId(null);

		}
		
		model.addAttribute("page", page);
		
		
		return "modules/PQC/inspectCheckTimesQuery/list";
	}
	
	
}
