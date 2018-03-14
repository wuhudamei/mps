package cn.damei.web.mobile.home;

import java.util.Map;

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
import cn.damei.entity.mobile.home.HomeLoginLogoutLog;
import cn.damei.service.mobile.home.HomeLoginLogoutLogService;


@Controller
@RequestMapping(value="${adminPath}/app/homeLog")
public class HomeLoginLogoutLogController {
	@Autowired
	private HomeLoginLogoutLogService homeLoginLogoutLogService;
	
	@ModelAttribute
	public HomeLoginLogoutLog get(@RequestParam(required = false) Integer id)
	{
		HomeLoginLogoutLog entity = null;
		
		if (entity == null)
		{
			entity = new HomeLoginLogoutLog();
		}
		return entity;
	}
	
	@RequiresPermissions("homeApplog:view")
    @RequestMapping(value = { "list", "" })
    public String list(HomeLoginLogoutLog homeLoginLogoutLog, HttpServletRequest request, HttpServletResponse response, Model model) {
        
		
        Page<HomeLoginLogoutLog> page = homeLoginLogoutLogService.findPage(new Page<HomeLoginLogoutLog>(request, response), homeLoginLogoutLog);
        model.addAttribute("page", page);
        Map<String,Object> resultMap = homeLoginLogoutLogService.summaryQueryByCondition(homeLoginLogoutLog);
        model.addAttribute("actualLogNum", resultMap.get("totalNum") == null ? 0 : resultMap.get("totalNum") );
        model.addAttribute("appNum", resultMap.get("appNum") == null ? 0 : resultMap.get("appNum") );
        model.addAttribute("wechatNum", resultMap.get("wechatNum") == null ? 0 : resultMap.get("wechatNum") );
        
        return "modules/bizAppHomeLog/LoginLogoutLogList";
    }
}
