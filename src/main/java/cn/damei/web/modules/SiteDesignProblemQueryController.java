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

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DataAuthorityConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.service.modules.DataAuthorityService;
import cn.damei.entity.modules.SiteDesignProblem;
import cn.damei.service.modules.SiteDesignProblemPCService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/bizorderinstallitemproblem/siteDesignProblem/query")
public class SiteDesignProblemQueryController {
	@Autowired
	private SiteDesignProblemPCService siteDesignProblemService;
	@Autowired
	private DataAuthorityService dataAuthorityService;

	@ModelAttribute
	public SiteDesignProblem getOrderInstallItemProblemVo(@RequestParam(required=false) Integer id,SiteDesignProblem siteDesignProblem) {
		
		if (siteDesignProblem == null){
			SiteDesignProblem site  = new SiteDesignProblem();
			siteDesignProblem = site;
			
		}else{
			
			
		}
		
		return siteDesignProblem;
	}
	
	@RequestMapping(value="list")
	public String query(SiteDesignProblem siteDesignProblem,Model model,HttpServletRequest request,HttpServletResponse response){
		
		//权限控制
		User user = UserUtils.getUser();
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_GDSJ);
		siteDesignProblem.setPhones(orderdPhones);
		String userId = user.getId();
		siteDesignProblem.setUserId(userId);
		//过滤工程模式
		if(StringUtils.isBlank(siteDesignProblem.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				siteDesignProblem.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				siteDesignProblem.setProjectMode(user.getProjectMode());
			}
		}
	
		//业务类型
		siteDesignProblem.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_4);
		//照片类型
		siteDesignProblem.setPictureType(PictureTypeContantUtil.PICTURE_TYPE_2081);
		//材料部处理角色
		siteDesignProblem.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_5);
		//材料部处理状态
		siteDesignProblem.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_70);
		Page<SiteDesignProblem> page = siteDesignProblemService.findVoPage(new Page<SiteDesignProblem>(request, response), siteDesignProblem);
		model.addAttribute("page", page);
		return "modules/bizorderinstallitemproblem/siteDesignProblem/siteDesignProblemQuery";
	}
	
}
