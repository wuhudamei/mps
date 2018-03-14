package cn.damei.web.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.entity.modules.TaskPackCount;
import cn.damei.service.modules.TaskPackCountService;

@Controller
@RequestMapping(value="${adminPath}/taskpackcount")
public class TaskPackCountController {
	@Autowired
	private TaskPackCountService tcs;
	
	
	
	@ModelAttribute
	public TaskPackCount get(@RequestParam(required=false) Integer id){
		TaskPackCount taskPackCount = null;
		if(id != null){
			taskPackCount = tcs.get(id+"");
		}else{
			taskPackCount = new TaskPackCount();
		}
		 return taskPackCount;
		
	}
	
	@RequestMapping(value="list")
	public String list(TaskPackCount taskPackCount,Model model) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(taskPackCount.getPlanStartdate()==null){
			Date date = new Date();
			
			String format = sdf.format(date);
			taskPackCount.setPlanStartdate(format);
			model.addAttribute("planStartdate", date);
		}else{
			String planStartdate = taskPackCount.getPlanStartdate();
			Date parse = sdf.parse(planStartdate);
			model.addAttribute("planStartdate", parse);
			
		}
		List<TaskPackCount> list = tcs.findCount(taskPackCount);
		model.addAttribute("list", list);
		return "/modules/taskpackcount/taskpackcount";
	}
	
	@RequestMapping(value="listtime")
	public String listtime(TaskPackCount taskPackCount,HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String planEnddate = taskPackCount.getPlanEnddate();
		String planStartdate = taskPackCount.getPlanStartdate();
		
		
		
		if(planEnddate!=null&&planStartdate!=null){

			Date parse = sdf.parse(planEnddate);
			Calendar  calendar  =  new  GregorianCalendar();
			calendar.setTime(parse);
			calendar.add(calendar.DATE,1);
			Date time = calendar.getTime();
			taskPackCount.setPlanEnddate(sdf.format(time));

			Page<TaskPackCount> page = tcs.findPage(new Page<TaskPackCount>(request,response), taskPackCount);
			model.addAttribute("page",page);
			
			Date end = sdf.parse(planEnddate);
			Date start = sdf.parse(planStartdate);
			model.addAttribute("end",end);
			model.addAttribute("start",start);
		}
		
		return "/modules/taskpackcount/taskpacktimecount";
	}
}
