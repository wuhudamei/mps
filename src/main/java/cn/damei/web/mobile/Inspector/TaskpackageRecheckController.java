package cn.damei.web.mobile.Inspector;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageVo;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskPackage;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageProcedure;
import cn.damei.service.mobile.Inspector.PqcOrderTaskPackageService;
import cn.damei.service.mobile.Inspector.PqcOrderTaskPackageProcedureService;
import cn.damei.common.SessionUtils;

@Controller
@RequestMapping(value = "${adminPath}/app/pqc/taskpackagerecheck")
public class TaskpackageRecheckController {

	@Autowired
	private PqcOrderTaskPackageService orderTaskPackageService;
	
	@Autowired
	private PqcOrderTaskPackageProcedureService pqcOrderTaskPackageProcedureService;

	@RequestMapping(value = "taskpackageRecheckList")
	public String taskpackageRecheckList(Model model, HttpServletRequest request) {
		Inspector inspector = SessionUtils.getInspectorSession(request);
		List<PqcOrderTaskPackage> list = orderTaskPackageService.queryTaskPackageByInspectorId(inspector.getId());
		model.addAttribute("list", list);
		return "mobile/modules/pqc/taskpackagerecheck/recheck";
	}
	
	@RequestMapping(value = "toTaskpackageRecheck")
	public String toTaskpackageRecheck(Model model, Integer id,String backUrl) {
		PqcOrderTaskpackageVo vo = orderTaskPackageService.queryTaskPackageRecheck(id);
		List<PqcOrderTaskpackageProcedure> list = pqcOrderTaskPackageProcedureService.queryBizOrderTaskpackageProcedure(id);
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);

		//梅浩加入返回url
		if(null!=backUrl &&!"".equals(backUrl)){

			model.addAttribute("backUrl",backUrl);

		}
		return "mobile/modules/pqc/taskpackagerecheck/recheck_sub";
	}
	
	@RequestMapping(value = "confirmTaskpackageRecheck")
	public @ResponseBody String confirmTaskpackageRecheck(Model model, PqcOrderTaskPackage task) {
		String flag = "error";
		try{
			orderTaskPackageService.confirmTaskpackageRecheck(task);
			flag = "success";
		}catch(Exception e){
			e.printStackTrace();
			flag = "error";
			throw e;
		}
		return flag;
	}
}
