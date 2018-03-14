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
import cn.damei.entity.modules.unqualifiedItemsStatisticsEntity;
import cn.damei.service.modules.unqualifiedItemsStatisticsService;
import cn.damei.common.utils.UserUtils;

/**
 * 
 * @author 梅浩
 * @2016年12月7日
 * @mdn美得你
 * @author_phone : 18610507472
 * @ClassInfo:不合格项统计
 */
@Controller
@RequestMapping(value="${adminPath}/unqualifiedItemsStatistics")
public class unqualifiedItemsStatisticsController extends BaseController {
	@Autowired
	private  unqualifiedItemsStatisticsService service;
	
	@ModelAttribute
	public unqualifiedItemsStatisticsEntity get(@RequestParam(required = false) String id) {
		unqualifiedItemsStatisticsEntity entity =null;
		if (StringUtils.isNotBlank(id)){
			entity = service.get(id);
		}
		if (entity == null){
			entity = new unqualifiedItemsStatisticsEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("unqualifiedItemsStatistics:unqualifiedItemsStatistics:view")
	@RequestMapping(value="list")
	public String    inspectSignList(unqualifiedItemsStatisticsEntity sign,HttpServletRequest request, HttpServletResponse response, Model model){
	
		return "modules/PQC/unqualifiedItemsStatistics/list";
	}
	@RequiresPermissions("unqualifiedItemsStatistics:unqualifiedItemsStatistics:view")
	@RequestMapping(value="listInfo")
	public String    inspectSignListCheck( unqualifiedItemsStatisticsEntity sign,HttpServletRequest request, HttpServletResponse response, Model model){
		if(null!=sign.getStartDate()){
			model.addAttribute("startDate", sign.getStartDate());
		}
		if(null!=sign.getEndDate()){
			
			model.addAttribute("endDate", sign.getEndDate());
		}
		int x = 0;
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			sign.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}	
		
		Page<unqualifiedItemsStatisticsEntity> page = service.findPage(new Page<unqualifiedItemsStatisticsEntity>(request, response), sign); 
		
		if (x > 0) {
			sign.setStoreId(null);

		}
		model.addAttribute("page", page);
		
		
		return "modules/PQC/unqualifiedItemsStatistics/list";
	}
}
