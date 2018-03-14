package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.ProgressWarning;
import cn.damei.service.mobile.Manager.ProgressWarningService;

@Controller
@RequestMapping(value="${adminPath}/app/manager/progressWarning")
public class ProgressWarningController {
	@Autowired
	private ProgressWarningService progressWarningService;

	@RequestMapping(value = "list")
	public String list(HttpServletRequest request, Model model){
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		List<ProgressWarning> list = progressWarningService.findDelayMaterialCount(manager.getId());
		model.addAttribute("list", list);
		int parseInt = 0;
		if(list!=null&&list.size()>0){
			for (ProgressWarning progressWarning : list) {
				String count = progressWarning.getCount();
				if(count!=null){
					parseInt += Integer.parseInt(count);
				}
				
				
			}
		}
		
		
		model.addAttribute("count", parseInt);
		return "/mobile/modules/Manager/progressWarning/forwordList";
	}
	@RequestMapping(value = "from")
	public String from(String purchaseType,HttpServletRequest request, Model model){
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		List<ProgressWarning> list = progressWarningService.findDelayMaterialInfo(manager.getId(),purchaseType);
		model.addAttribute("list", list);
		model.addAttribute("purchaseType", purchaseType);
		return "/mobile/modules/Manager/progressWarning/progressWarning";
	}
	
}
