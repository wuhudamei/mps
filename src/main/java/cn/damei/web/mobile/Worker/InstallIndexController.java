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

	
	/**
	 * 安装工首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toindex")
	public String toindex(Model model,HttpServletRequest request){
		
		//1.已登录的安装工信息
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		
		Integer unfinishedCount = 0;
		Integer finishedCount = 0;
		Integer allCount = 0;
		
		if(worker != null && null!=worker.getEmgrouprelationId()){
			
			//2.查询工人组的施工单--未完工的数量
			unfinishedCount = installIndexService.findUnfinishedCount(worker.getEmgrouprelationId());
			
			//3.查询工人组的施工单--已完工的数量
			finishedCount = installIndexService.findFinishedCount(worker.getEmgrouprelationId());
			
			//4.总数量
			allCount = unfinishedCount + finishedCount;
		}
	
		
		model.addAttribute("worker", worker);
		model.addAttribute("unfinishedCount", unfinishedCount);
		model.addAttribute("finishedCount", finishedCount);
		model.addAttribute("allCount", allCount);
		
		return "mobile/modules/Worker/installer/installIndex/homePage";
	}
	
	
	
}
