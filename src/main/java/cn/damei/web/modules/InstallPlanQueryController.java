
package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.InstallPlanQuery;
import cn.damei.service.modules.InstallPlanQueryService;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizinstallplanquery/installPlanQuery")
public class InstallPlanQueryController extends BaseController {

	@Autowired
	private InstallPlanQueryService installPlanQueryService;
	
	@ModelAttribute
	public InstallPlanQuery get(@RequestParam(required=false) String id) {
		InstallPlanQuery entity = null;
	
			entity = new InstallPlanQuery();
			if(StringUtils.isNotBlank(id)){
				entity.setOrderId(Integer.parseInt(id));
				
			}
			
			
			
		
		return entity;
	}
	
	@RequiresPermissions("bizinstallplanquery:installPlanQuery:view")
	@RequestMapping(value = {"list", ""})
	public String list(InstallPlanQuery installPlanQuery, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/bizinstallplanquery/installPlanQueryList";
	}
	@RequiresPermissions("bizinstallplanquery:installPlanQuery:view")
	@RequestMapping(value ="list2")
	public String list2(InstallPlanQuery installPlanQuery, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (null != installPlanQuery.getStartDate()) {
			model.addAttribute("startDate", installPlanQuery.getStartDate());
		}
		if (null != installPlanQuery.getEndDate()) {

			model.addAttribute("endDate", installPlanQuery.getEndDate());
		}



        User user = UserUtils.getUser();
        if(null==installPlanQuery.getStoreId()){
            if(null!=user.getStoreId()){
                installPlanQuery.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
		
		Page<InstallPlanQuery> page = installPlanQueryService.findPage(new Page<InstallPlanQuery>(request, response), installPlanQuery); 
		model.addAttribute("page", page);
		return "modules/bizinstallplanquery/installPlanQueryList";
	}

	@RequiresPermissions("bizinstallplanquery:installPlanQuery:view")
	@RequestMapping(value = "form")
	public String form(InstallPlanQuery installPlanQuery, Model model) {
		List<InstallPlanQuery> list = installPlanQueryService.findDetailByOrderId(installPlanQuery.getOrderId());
		
		if(null!=list&&list.size()>0){
			
			model.addAttribute("list", list);
			
		}else{
			
			
			model.addAttribute("error", "无");
		}
		return "modules/bizinstallplanquery/installPlanQueryForm";
	}
	


}