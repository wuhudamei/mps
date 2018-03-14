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
import cn.damei.entity.modules.InspectSignQueryEntity;
import cn.damei.service.modules.InspectSignQueryService;
import cn.damei.common.utils.UserUtils;

@Controller
@RequestMapping(value="${adminPath}/pqc/inspectSignQuery")
public class InspectSignQueryController extends BaseController{

	@Autowired
	private InspectSignQueryService service;
	
	@ModelAttribute
	public InspectSignQueryEntity get(@RequestParam(required = false) String id) {
		InspectSignQueryEntity entity =null;
		if (StringUtils.isNotBlank(id)){
			entity = service.get(id);
		}
		if (entity == null){
			entity = new InspectSignQueryEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("inspectSignQuery:inspectSignQuery:view")
	@RequestMapping(value="inspectSignList")
	public String    inspectSignList(InspectSignQueryEntity sign,HttpServletRequest request, HttpServletResponse response, Model model){
	
		return "modules/PQC/inspectSignQuery/list";
	}
	@RequiresPermissions("inspectSignQuery:inspectSignQuery:view")
	@RequestMapping(value="inspectSignListCheck")
	public String    inspectSignListCheck( InspectSignQueryEntity sign,HttpServletRequest request, HttpServletResponse response, Model model){
		int x = 0;
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			sign.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}
			if(null!=sign.getStartDate()){
				
				model.addAttribute("startDate", sign.getStartDate());
			}
			if(null!=sign.getEndDate()){
				
				model.addAttribute("endDate", sign.getEndDate());
			}
		
		Page<InspectSignQueryEntity> page = service.findPage(new Page<InspectSignQueryEntity>(request, response), sign); 
		if (x > 0) {
			sign.setStoreId(null);

		}
		
		
		model.addAttribute("page", page);
		
		
		return "modules/PQC/inspectSignQuery/list";
	}
}
