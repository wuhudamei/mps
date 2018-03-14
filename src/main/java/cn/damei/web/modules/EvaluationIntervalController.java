package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.entity.modules.BizEvalActivity;
import cn.damei.service.modules.BizEvalActivityService;

@Controller
@RequestMapping(value="${adminPath}/evaluate/evaluationInterval")
public class EvaluationIntervalController {
	@Autowired
	private BizEvalActivityService bizEvalActivityService;
	
	@ModelAttribute
	public BizEvalActivity get(@RequestParam(required = false) Integer id) {
		BizEvalActivity entity = null;
		if (entity == null) {
			entity = new BizEvalActivity();
		}
		return entity;
	}
	

	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,HttpServletResponse response,BizEvalActivity bizEvalActivity,Model model) {
		model.addAttribute("bizEvalActivity", bizEvalActivity);
		Page<BizEvalActivity> page = bizEvalActivityService.findMyPage(new Page<BizEvalActivity>(request,response),bizEvalActivity);
		model.addAttribute("page", page);
		return "modules/evaluate/bizevalactivity/evaluationIntervalList";
	}
	

	@RequestMapping(value = "from")
	public String from(HttpServletRequest request,BizEvalActivity bizEvalActivity,Model model) {
		model.addAttribute("bizEvalActivity", bizEvalActivity);
		return "modules/evaluate/bizevalactivity/evaluationIntervalForm";
	}
	
	

	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,BizEvalActivity bizEvalActivity) {
		if(bizEvalActivity.getRoleCycleId() == null || bizEvalActivity.getRoleCycleId().equals("")){
			bizEvalActivityService.saveActivityRoleCycle(bizEvalActivity);
		}else{
			bizEvalActivityService.updateActivityRoleCycle(bizEvalActivity);
		}
		
		return "redirect:"+Global.getAdminPath()+"/evaluate/evaluationInterval/list?id="+bizEvalActivity.getId()+"&evalTargetType="+bizEvalActivity.getEvalTargetType()+"&storeId="+bizEvalActivity.getStoreId()+"&projectMode="+bizEvalActivity.getProjectMode();


	} 
	
	

	@RequestMapping(value = "findEvalType")
	@ResponseBody
	public List<String> findEvalType(HttpServletRequest request,BizEvalActivity bizEvalActivity) {
		List<String> list = bizEvalActivityService.findEvalType(bizEvalActivity);
		return list;
	}

	@RequestMapping(value = "checkExist")
	@ResponseBody
	public String checkExist(HttpServletRequest request,BizEvalActivity bizEvalActivity) {
		String str = bizEvalActivityService.checkExist(bizEvalActivity);

		if(bizEvalActivity.getDelFlag().equals("1")){
			if(str.equals("1")){

				String id = bizEvalActivityService.findEvalActivity(bizEvalActivity);
				if(id.equals(bizEvalActivity.getRoleCycleId())){
					return "0";
				}else{
					return "1";
				}
				
			}
		}
		return str;
	}
}
