package cn.damei.web.mobile.Manager;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.entity.mobile.Manager.Manager;


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class ProgressMianController {

	private Logger logger  = org.slf4j.LoggerFactory.getLogger(MaterialManagementController.class);

	@RequestMapping(value = { "progressList", "" })
	public String progressMain(HttpServletRequest request, Model model) {
		
		String index = (String)request.getSession().getAttribute("index");
		request.getSession().removeAttribute("index");
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		model.addAttribute("manager", manager);
		if(null!=index){
			if("0".equals(index)){

				return "mobile/modules/Manager/progressMain/progressMain";
			}else if("1".equals(index)){

				return "mobile/modules/Manager/manager_index";
			}else{
				
				logger.warn("工程安装路径参数index有误  index:"+index);
				return "mobile/modules/Manager/manager_index";
			}
			
		}else{
			
			logger.warn("工程安装路径参数为空");
			return "mobile/modules/Manager/progressMain/progressMain";
		}
		
	}

}
