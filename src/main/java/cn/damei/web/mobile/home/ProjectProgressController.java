package cn.damei.web.mobile.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.damei.service.mobile.home.ProjectProgressService;
import cn.damei.entity.mobile.home.ProjectProgressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="${adminPath}/app/home/projectprogress")
public class ProjectProgressController {

	@Autowired
	private ProjectProgressService service;
	@Autowired
	private ProjectProgressService projectProgressService;
	
	@RequestMapping(value="list.php")
	public String   list(HttpServletRequest request ,Model model,String orderId){
		//從session拿出登录的客户
	String customerPhone =(String)request.getSession().getAttribute("customerPhone");
	if(null!=customerPhone){
		//查询该客户的订单  
		List<ProjectProgressVo> list = service.getOrderListByCustomerPhone(customerPhone);
		ProjectProgressVo vo = new ProjectProgressVo();
		for (ProjectProgressVo projectProgressVo:list) {
			vo.setOrderId(projectProgressVo.getOrderId());
			ProjectProgressVo progressVo = projectProgressService.getConfirmStartInfoByOrderId(vo);
			if(progressVo==null){
				projectProgressVo.setViewLogCount(1);
			}
		}
		if(list.size()>0){
				//如果有 ,取最近的一条或有新消息的作为显示 ,并查询该订单的确认开工的信息
				model.addAttribute("orderList", list);
				if(null!=orderId&&JobSiteController.isNum(orderId)){
					for (ProjectProgressVo projectProgressVo : list) {
						
						if(String.valueOf(projectProgressVo.getOrderId()).equals(orderId)){
							model.addAttribute("info",projectProgressVo);	
							
						}
						
					}
					
					
				}else{

					model.addAttribute("info", list.get(0));

				}
				
			}else{
				
				
				model.addAttribute("none", "1");
			}
			
	}else{
		
		model.addAttribute("none", "1");
	}
	return "mobile/modules/home/cusindex/projectprogress/buildProgressList";
		
	}
	
	
	@RequestMapping(value="findInfoByOrderId.php")
	public @ResponseBody ProjectProgressVo findInfoByOrderId(Integer orderId,Model model){
		ProjectProgressVo vo = new ProjectProgressVo();
		vo.setOrderId(orderId);
		ProjectProgressVo progressVo = service.getConfirmStartInfoByOrderId(vo);
		if(null!=progressVo){
			//該客戶最近的一個訂單的開工信息
			model.addAttribute("progressVo", progressVo);

		}

			return null;


	}
	
	@RequestMapping(value="findConfirmStartInfoByOrderId.php")
	public  String findConfirmStartInfoByOrderId(Integer orderId,Model model){
		ProjectProgressVo vo = new ProjectProgressVo();
		vo.setOrderId(orderId);
		ProjectProgressVo progressVo = service.getConfirmStartInfoByOrderId(vo);

		if(null!=progressVo){
			progressVo.setOrderId(orderId);
			//已讀
			Integer integer = service.findLog(progressVo);
			if(null!=integer&&integer==0){
				progressVo.setLogType("555");
				progressVo.setLogRelatedId(orderId);
				service.insertLogForProjectProgress(progressVo);

			}



			//該客戶最近的一個訂單的開工信息
			model.addAttribute("progressVo", progressVo);


		}else{

			model.addAttribute("none", 1);
		}


		
		return "mobile/modules/home/cusindex/projectprogress/buildProgress";
		
		
	}
	
	
	
		
	}
	
