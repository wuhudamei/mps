package cn.damei.web.mobile.Manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Worker.Worker;

@Controller
public class MethodController {
	@RequestMapping("${adminPath}/app/{people}/method/{path}")
	public String path(@PathVariable String people,@PathVariable String path,HttpServletRequest request,Model model){
		Manager  manager =  (Manager)request.getSession().getAttribute("manager");
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		Inspector inspector = (Inspector)request.getSession().getAttribute("inspector");
		
		if(manager!=null&&manager.getEmpType()==3){
			
			model.addAttribute("path","manager/indexMine");
		}
		if(worker!=null&&worker.getEmpType()==2){
			
			model.addAttribute("path","worker/myindex");
		}
		if(inspector!=null&&inspector.getEmpType()==1){
			
			model.addAttribute("path","pqc/pqcIndex");
		}
		return "mobile/modules/Manager/method/"+path;
	}
}
