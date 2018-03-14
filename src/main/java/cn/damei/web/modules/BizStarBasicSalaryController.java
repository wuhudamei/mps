package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizStarBasicSalary;
import cn.damei.service.modules.BizStarBasicSalaryService;

/**
 * 项目经理星级和底薪设置
 * @author cgh
 *
 */
@RequestMapping(value="${adminPath}/bizstar/bizManagerStar/bizStarBasicSalary/")
@Controller
public class BizStarBasicSalaryController extends BaseController{

	@Autowired 
	private BizStarBasicSalaryService bizStarBasicSalaryService;
	
	@ModelAttribute
	public BizStarBasicSalary get(@RequestParam(required=false) Integer id) {
		BizStarBasicSalary entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizStarBasicSalaryService.get(id);
		}
		if (entity == null){
			entity = new BizStarBasicSalary();
		}
		return entity;
	}
	
	/**
	 * bizStarBasicSalary LIST
	 * @param bizStarBasicSalary
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizmanagerstar:bizmanagerstar:view")
	@RequestMapping(value="list")
	public String basicSalaryList(BizStarBasicSalary bizStarBasicSalary,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<BizStarBasicSalary> page = bizStarBasicSalaryService.findPage(new Page<BizStarBasicSalary>(request, response), bizStarBasicSalary); 
		model.addAttribute("page", page);
		return "modules/bizstar/bizStarBasicSalaryList";
	}
	
	/**
	 * 更新版本
	 * @param id
	 * @param status
	 * @return
	 */
	@RequiresPermissions("bizmanagerstar:bizmanagerstar:edit")
	@RequestMapping(value="updateStatus")
	public String updateStatus(Integer id,String status){
		
		if(id != null){
			BizStarBasicSalary bizStarBasicSalary = bizStarBasicSalaryService.get(id);
		
			BizStarBasicSalary bizStarBasicSalary2 = new BizStarBasicSalary();
			//状态
			bizStarBasicSalary2.setStatus(status);
			bizStarBasicSalary2.setId(bizStarBasicSalary.getId());
			bizStarBasicSalary2.setBasicSalary(bizStarBasicSalary.getBasicSalary());
			bizStarBasicSalary2.setBizManagerStar(bizStarBasicSalary.getBizManagerStar());
			bizStarBasicSalary2.setEffectDate(bizStarBasicSalary.getEffectDate());
			bizStarBasicSalary2.setProjectMode(bizStarBasicSalary.getProjectMode());
			bizStarBasicSalary2.setStarLevel(bizStarBasicSalary.getStarLevel());
			bizStarBasicSalary2.setStoreId(bizStarBasicSalary.getStoreId());
			//版本
			bizStarBasicSalary2.setVersion(bizStarBasicSalary.getVersion());
			bizStarBasicSalaryService.save(bizStarBasicSalary2);
		}
		return "redirect:"+Global.getAdminPath()+"/bizstar/bizManagerStar/bizStarBasicSalary/list?repage";
	}
	
}
