package cn.damei.web.mobile.Worker;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.service.mobile.Worker.InstallIndexService;
import cn.damei.entity.mobile.Worker.Worker;

@Controller
@RequestMapping(value="${adminPath}/app/worker/install/installIndex")
public class InstallIndexController {
	
	@Autowired
	private InstallIndexService installIndexService;

	

	@RequestMapping(value="toindex")
	public String toindex(Model model,HttpServletRequest request){
		

		Worker worker = (Worker)request.getSession().getAttribute("worker");
		
		Integer unfinishedCount = 0;
		Integer finishedCount = 0;
		Integer allCount = 0;
		
		if(worker != null && null!=worker.getEmgrouprelationId()){
			

			unfinishedCount = installIndexService.findUnfinishedCount(worker.getEmgrouprelationId());
			

			finishedCount = installIndexService.findFinishedCount(worker.getEmgrouprelationId());
			

			allCount = unfinishedCount + finishedCount;
		}
	
		
		model.addAttribute("worker", worker);
		model.addAttribute("unfinishedCount", unfinishedCount);
		model.addAttribute("finishedCount", finishedCount);
		model.addAttribute("allCount", allCount);
		
		return "mobile/modules/Worker/installer/installIndex/homePage";
	}
	
	
	
}
