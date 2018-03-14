package cn.damei.web.modules;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.MaterialScheduleQueryEntity;
import cn.damei.service.modules.MaterialScheduleQueryService;
import cn.damei.common.utils.UserUtils;

@Controller
@RequestMapping(value="${adminPath}/materialScheduleQuery")
public class MaterialScheduleQueryController  extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(MaterialScheduleQueryController.class);
	
	@Autowired
	private MaterialScheduleQueryService service;
	
	
	public MaterialScheduleQueryEntity get(@RequestParam(required = false) Integer id) {
		MaterialScheduleQueryEntity entity =null;
		if (StringUtils.isNotBlank(id+"")){
			entity = service.get(id);
		}
		if (entity == null){
			entity = new MaterialScheduleQueryEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("materialScheduleQuery:materialScheduleQuery:view")
	@RequestMapping(value="list")
	public String    inspectSignList(MaterialScheduleQueryEntity checkEntity,HttpServletRequest request, HttpServletResponse response, Model model){
	
		return "modules/materialScheduleQuery/list";
	}
	@RequiresPermissions("materialScheduleQuery:materialScheduleQuery:view")
	@RequestMapping(value="listInfo")
	public String    inspectSignListCheck( MaterialScheduleQueryEntity checkEntity,HttpServletRequest request, HttpServletResponse response, Model model){
		if(checkEntity.getStatus()!=null){
			String[] status = checkEntity.getStatus().split(",");
			if(null!=status && status.length>0){
				checkEntity.setPurchaseStatusList(Arrays.asList(status));
			}
		}
		int x = 0;

		if(!UserUtils.getUser().getOffice().getId().equals("1")){

			checkEntity.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}
		
		Page<MaterialScheduleQueryEntity> page = service.findPage(new Page<MaterialScheduleQueryEntity>(request, response), checkEntity); 
		if(x>0){
			checkEntity.setStoreId(null);
			
		}
		
		model.addAttribute("page", page);
		
		
		return "modules/materialScheduleQuery/list";
	}
	

	
	
}
