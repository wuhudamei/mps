package cn.damei.web.modules;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.BizStarConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizManagerStar;
import cn.damei.entity.modules.BizStarBasicSalary;
import cn.damei.service.modules.BizManagerStarService;
import cn.damei.service.modules.BizStarBasicSalaryService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 项目经理星级和底薪设置
 * @author cgh
 *
 */
@RequestMapping(value="${adminPath}/bizstar/bizManagerStar/")
@Controller
public class BizManagerStarController extends BaseController{

	@Autowired
	private BizManagerStarService bizManagerStarService;
	
	@Autowired 
	private BizStarBasicSalaryService bizStarBasicSalaryService;
	
	@ModelAttribute
	public BizManagerStar get(@RequestParam(required=false) Integer id) {
		BizManagerStar entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizManagerStarService.get(id);
		}
		if (entity == null){
			entity = new BizManagerStar();
		}
		return entity;
	}
	
	/**
	 * BizManagerStar list
	 * @param bizManagerStar
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizmanagerstar:bizmanagerstar:view")
	@RequestMapping(value = "list")
	public String list(BizManagerStar bizManagerStar,HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizManagerStar.getStoreId()){
			if(null!=user.getStoreId()){
				bizManagerStar.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		Page<BizManagerStar> page = bizManagerStarService.findPage(new Page<BizManagerStar>(request, response), bizManagerStar); 
		model.addAttribute("page", page);
		return "modules/bizstar/bizManagerStarList";
	}
	
	@RequiresPermissions("bizmanagerstar:bizmanagerstar:view")
	@RequestMapping(value="form")
	public String form(Model model,BizManagerStar bizManagerStar){
		model.addAttribute("bizManagerStar", bizManagerStar);
		return "modules/bizstar/bizManagerStarForm";
	}
	
	@RequiresPermissions("bizmanagerstar:bizmanagerstar:edit")
	@RequestMapping(value = "save")
	public String save(BizManagerStar bizManagerStar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizManagerStar)){
			return form(model,bizManagerStar);
		}
		//人员类型
		bizManagerStar.setStarType(BizStarConstantUtil.BIZ_STAR_TYPE);
		bizManagerStarService.save(bizManagerStar);
		addMessage(redirectAttributes, "保存设置成功");
		

		return "redirect:"+Global.getAdminPath()+"/bizstar/bizManagerStar/list?repage";
	}
	
	/**
	 * 修改启用状态
	 * @param id
	 * @return
	 */
	@RequiresPermissions("bizmanagerstar:bizmanagerstar:edit")
	@RequestMapping(value="updateStatus")
	public String updateStatus(Integer id,String status){
		
		if(id!= null){
			BizManagerStar bizManagerStar = bizManagerStarService.get(id);
			
			BizManagerStar bizManagerStar2 = new BizManagerStar();
			bizManagerStar2.setId(bizManagerStar.getId());
			
			bizManagerStar2.setStarLevel(bizManagerStar.getStarLevel());
			
			bizManagerStar2.setStarType(bizManagerStar.getStarType());
			bizManagerStar2.setProjectMode(bizManagerStar.getProjectMode());
			//启用状态
			bizManagerStar2.setStatus(status);
			bizManagerStar2.setUpdateBy(UserUtils.getUser());
			bizManagerStar2.setUpdateDate(new Date());
			bizManagerStar2.setRemarks(bizManagerStar.getRemarks());
			bizManagerStar2.setStoreId(bizManagerStar.getStoreId());
			bizManagerStarService.save(bizManagerStar2);
			
		}
		return "redirect:"+Global.getAdminPath()+"/bizstar/bizManagerStar/list?repage";
	}
	
	/**
	 * 设置底薪
	 * @return
	 */
	@RequiresPermissions("bizmanagerstar:bizmanagerstar:edit")
	@RequestMapping(value="setBasicSalary")
	public String setBasicSalary(Integer id,Model model){
		if(id != null){
			BizManagerStar bizManagerStar = bizManagerStarService.get(id);
			model.addAttribute("bizManagerStar", bizManagerStar);
		}
		return "modules/bizstar/bizStarBasicSalaryForm";
	}
	
	
	/**
	 * 保存底薪设置
	 * @param id
	 * @param bizStarBasicSalary
	 * @return
	 */
	@RequiresPermissions("bizmanagerstar:bizmanagerstar:edit")
	@RequestMapping(value="bizStarBasicSalary/save")
	public String basicSalary(Integer id,String status,Double basicSalary,Date effectDate){
		if(id != null){
			BizManagerStar bizManagerStar = bizManagerStarService.get(id);
			
			BizStarBasicSalary bizStarBasicSalary = new BizStarBasicSalary();
			
			bizStarBasicSalary.setBizManagerStar(bizManagerStar);
			//版本
			bizStarBasicSalary.setVersion(bizStarBasicSalaryService.getMaxVersion());
			//状态
			bizStarBasicSalary.setStatus(status);
			//薪资
			bizStarBasicSalary.setBasicSalary(basicSalary);
			//生效时间
			bizStarBasicSalary.setEffectDate(effectDate);
			
			bizStarBasicSalaryService.save(bizStarBasicSalary);
		}
		
		return "redirect:"+Global.getAdminPath()+"/bizstar/bizManagerStar/list?repage";
	}
}
