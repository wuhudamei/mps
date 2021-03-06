
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.BusinessUrgeConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizBusinessUrge;
import cn.damei.entity.modules.InstallBusinessUrge;
import cn.damei.entity.modules.WallAndFloorBusinessUrge;
import cn.damei.service.modules.BizBusinessUrgeService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizbusinessurge/bizBusinessUrge")
public class BizBusinessUrgeController extends BaseController {

	@Autowired
	private BizBusinessUrgeService bizBusinessUrgeService;
	
	@ModelAttribute
	public BizBusinessUrge get(@RequestParam(required=false) Integer id) {
		BizBusinessUrge entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizBusinessUrgeService.get(id);
		}
		if (entity == null){
			entity = new BizBusinessUrge();
		}
		return entity;
	}
	

	@RequiresPermissions("bizbusinessurge:bizBusinessUrge:view")
	@RequestMapping(value = {"list", ""})
	public String list(InstallBusinessUrge installBusinessUrge, HttpServletRequest request, HttpServletResponse response, Model model) {
	
		User user = UserUtils.getUser();

		if(null==installBusinessUrge.getStoreId()){
			if(null!=user.getStoreId()){
				installBusinessUrge.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(installBusinessUrge.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				installBusinessUrge.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				installBusinessUrge.setProjectMode(user.getProjectMode());
			}
		}
		
		return "modules/bizbusinessurge/installBusinessUrgeList";
	}
	

	@RequiresPermissions("bizbusinessurge:bizBusinessUrge:view")
	@RequestMapping(value = {"list2", ""})
	public String list2(InstallBusinessUrge installBusinessUrge, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==installBusinessUrge.getStoreId()){
			if(null!=user.getStoreId()){
				installBusinessUrge.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(installBusinessUrge.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				installBusinessUrge.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				installBusinessUrge.setProjectMode(user.getProjectMode());
			}
		}
		

		installBusinessUrge.setBusinesType(BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1);

		installBusinessUrge.setOperateType(BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1);
		

		List<Integer> projectInstallItemIdList = new ArrayList<Integer>();
		if(null != installBusinessUrge.getProjectInstallItemId()){
			projectInstallItemIdList.add(installBusinessUrge.getProjectInstallItemId());
		}
		if(null != installBusinessUrge.getProjectInstallItemIdStop()){
			projectInstallItemIdList.add(installBusinessUrge.getProjectInstallItemIdStop());
		}
		if(CollectionUtils.isNotEmpty(projectInstallItemIdList)){
			installBusinessUrge.setProjectInstallItemIdList(projectInstallItemIdList);
		}
				
				
		Page<InstallBusinessUrge> page = bizBusinessUrgeService.findInstallSelectPage(new Page<InstallBusinessUrge>(request, response), installBusinessUrge); 
		model.addAttribute("page", page);
		
		return "modules/bizbusinessurge/installBusinessUrgeList";
	}

	

	@RequiresPermissions("bizbusinessurge:bizBusinessUrge:view")
	@RequestMapping(value = {"wallList", ""})
	public String wallList(WallAndFloorBusinessUrge wallAndFloorBusinessUrge, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==wallAndFloorBusinessUrge.getStoreId()){
			if(null!=user.getStoreId()){
				wallAndFloorBusinessUrge.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(wallAndFloorBusinessUrge.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				wallAndFloorBusinessUrge.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				wallAndFloorBusinessUrge.setProjectMode(user.getProjectMode());
			}
		}
		
		return "modules/bizbusinessurge/wallAndFloorBusinessUrgeList";
	}
	

	@RequiresPermissions("bizbusinessurge:bizBusinessUrge:view")
	@RequestMapping(value = {"wallList2", ""})
	public String wallList2(WallAndFloorBusinessUrge wallAndFloorBusinessUrge, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==wallAndFloorBusinessUrge.getStoreId()){
			if(null!=user.getStoreId()){
				wallAndFloorBusinessUrge.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(wallAndFloorBusinessUrge.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				wallAndFloorBusinessUrge.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				wallAndFloorBusinessUrge.setProjectMode(user.getProjectMode());
			}
		}

		wallAndFloorBusinessUrge.setBusinesType(BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2);

		wallAndFloorBusinessUrge.setOperateType(BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1);
		
		Page<WallAndFloorBusinessUrge> page = bizBusinessUrgeService.findWallAndFloorSelectPage(new Page<WallAndFloorBusinessUrge>(request, response), wallAndFloorBusinessUrge); 
		model.addAttribute("page", page);
		
		return "modules/bizbusinessurge/wallAndFloorBusinessUrgeList";
	}
	
	
	
	
	
	
	@RequiresPermissions("bizbusinessurge:bizBusinessUrge:view")
	@RequestMapping(value = "form")
	public String form(BizBusinessUrge bizBusinessUrge, Model model) {
		model.addAttribute("bizBusinessUrge", bizBusinessUrge);
		return "modules/bizbusinessurge/bizBusinessUrgeForm";
	}

	@RequiresPermissions("bizbusinessurge:bizBusinessUrge:edit")
	@RequestMapping(value = "save")
	public String save(BizBusinessUrge bizBusinessUrge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizBusinessUrge)){
			return form(bizBusinessUrge, model);
		}
		bizBusinessUrgeService.save(bizBusinessUrge);
		addMessage(redirectAttributes, "保存业务催促表成功");
		return "redirect:"+Global.getAdminPath()+"/bizbusinessurge/bizBusinessUrge/?repage";
	}
	
	@RequiresPermissions("bizbusinessurge:bizBusinessUrge:edit")
	@RequestMapping(value = "delete")
	public String delete(BizBusinessUrge bizBusinessUrge, RedirectAttributes redirectAttributes) {
		bizBusinessUrgeService.delete(bizBusinessUrge);
		addMessage(redirectAttributes, "删除业务催促表成功");
		return "redirect:"+Global.getAdminPath()+"/bizbusinessurge/bizBusinessUrge/?repage";
	}

}