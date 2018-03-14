package cn.damei.web.modules;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.PurchaseStatistics;
import cn.damei.service.modules.PurchaseStatisticsService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 材料统计表Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/purchaseStatistics/purchaseStatistics")
public class PurchaseStatisticsController extends BaseController {

	@Autowired
	private PurchaseStatisticsService purchaseStatisticsService;

	/**
	 * 材料统计表
	 * @param purchaseStatistics
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("purchaseStatistics:purchaseStatistics:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(PurchaseStatistics purchaseStatistics, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==purchaseStatistics.getStoreId()){
			if(StringUtils.isNotBlank(user.getStoreId())){
				purchaseStatistics.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		//过滤工程模式
		if(StringUtils.isBlank(purchaseStatistics.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				purchaseStatistics.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				purchaseStatistics.setProjectMode(user.getProjectMode());
			}
		}
		
		return "modules/purchaseStatistics/purchaseStatisticsList";
	}
	
	/**
	 * 材料统计表
	 * @param purchaseStatistics
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("purchaseStatistics:purchaseStatistics:view")
	@RequestMapping(value = { "list", "" })
	public String list(PurchaseStatistics purchaseStatistics, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==purchaseStatistics.getStoreId()){
			if(StringUtils.isNotBlank(user.getStoreId())){
				purchaseStatistics.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		//过滤工程模式
		if(StringUtils.isBlank(purchaseStatistics.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				purchaseStatistics.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				purchaseStatistics.setProjectMode(user.getProjectMode());
			}
		}
		
		
		List<PurchaseStatistics> list = purchaseStatisticsService.findList( purchaseStatistics);
		
		model.addAttribute("list", list);
		
		return "modules/purchaseStatistics/purchaseStatisticsList";
	}

}