/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.MaterialSelfbuyConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizMaterialSelfbuy;
import cn.damei.service.modules.BizMaterialSelfbuyService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 材料自采表Controller
 * @author wyb
 * @version 2017-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/bizmaterialselfbuy/bizMaterialSelfbuy")
public class BizMaterialSelfbuyController extends BaseController {

	@Autowired
	private BizMaterialSelfbuyService bizMaterialSelfbuyService;
	
	@ModelAttribute
	public BizMaterialSelfbuy get(@RequestParam(required=false) Integer id) {
		BizMaterialSelfbuy entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizMaterialSelfbuyService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialSelfbuy();
		}
		return entity;
	}
	
	@RequiresPermissions("bizmaterialselfbuy:bizMaterialSelfbuy:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BizMaterialSelfbuy bizMaterialSelfbuy, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤工程模式
		if(StringUtils.isBlank(bizMaterialSelfbuy.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizMaterialSelfbuy.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizMaterialSelfbuy.setProjectMode(user.getProjectMode());
			}
		}
				
		return "modules/bizmaterialselfbuy/bizMaterialSelfbuyList";
	}
	
	@RequiresPermissions("bizmaterialselfbuy:bizMaterialSelfbuy:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialSelfbuy bizMaterialSelfbuy, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤工程模式
		if(StringUtils.isBlank(bizMaterialSelfbuy.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizMaterialSelfbuy.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizMaterialSelfbuy.setProjectMode(user.getProjectMode());
			}
		}
		
		Page<BizMaterialSelfbuy> page = bizMaterialSelfbuyService.findPage(new Page<BizMaterialSelfbuy>(request, response), bizMaterialSelfbuy); 
		model.addAttribute("page", page);
		return "modules/bizmaterialselfbuy/bizMaterialSelfbuyList";
	}

	@RequiresPermissions("bizmaterialselfbuy:bizMaterialSelfbuy:view")
	@RequestMapping(value = "form")
	public String form(BizMaterialSelfbuy bizMaterialSelfbuy, Model model) {
		
		if(null==bizMaterialSelfbuy.getId()){
			bizMaterialSelfbuy.setIsEnabled(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_IS_ENABLED_1);
			bizMaterialSelfbuy.setProjectMode(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_PROJECT_MODE_1);
			bizMaterialSelfbuy.setSettleStage(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_SETTLE_STAGE_2);
		}
		
		model.addAttribute("bizMaterialSelfbuy", bizMaterialSelfbuy);
		return "modules/bizmaterialselfbuy/bizMaterialSelfbuyForm";
	}

	@RequiresPermissions("bizmaterialselfbuy:bizMaterialSelfbuy:edit")
	@RequestMapping(value = "save")
	public String save(BizMaterialSelfbuy bizMaterialSelfbuy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMaterialSelfbuy)){
			return form(bizMaterialSelfbuy, model);
		}
		
		double b = bizMaterialSelfbuy.getSettleRate()*0.01;
		bizMaterialSelfbuy.setSettleRateTwo(b);
        
		bizMaterialSelfbuyService.save(bizMaterialSelfbuy);
		addMessage(redirectAttributes, "保存材料自采表成功");
		return "redirect:"+Global.getAdminPath()+"/bizmaterialselfbuy/bizMaterialSelfbuy/list?repage";
	}
	
	@RequiresPermissions("bizmaterialselfbuy:bizMaterialSelfbuy:edit")
	@RequestMapping(value = "delete")
	public @ResponseBody String delete(String id,String isEnabled) {
		
		String result = "0";
		//1.材料自选id
		if(StringUtils.isBlank(id)){
			result = "1";
			return result;
		}
		//2.状态
		if(StringUtils.isBlank(isEnabled)){
			result = "2";
			return result;
		}
		//3.状态转换
		BizMaterialSelfbuy bizMaterialSelfbuy = new BizMaterialSelfbuy();
		bizMaterialSelfbuy.setId(Integer.valueOf(id));
		bizMaterialSelfbuy.preUpdate();
		if (isEnabled.equals(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_IS_ENABLED_1)) {
			//3.1  停用成功
			bizMaterialSelfbuy.setIsEnabled(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_IS_ENABLED_0);
			bizMaterialSelfbuyService.delete(bizMaterialSelfbuy);
			result = "3";
		} else {
			//3.1  启用成功
			bizMaterialSelfbuy.setIsEnabled(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_IS_ENABLED_1);
			bizMaterialSelfbuyService.delete(bizMaterialSelfbuy);
			result = "4";
		}
		
		return result;
	}
	
	/**
	 * 同一门店+工程模式下，自采材料名称不允许重复 
	 * 0:可以保存  1：不可保存
	 * @param bizMaterialSelfbuy
	 * @return
	 */
	@RequestMapping(value = "material_selfbuy_ajax_isExists")
	public @ResponseBody String materialSelfbuyAjaxIsExists(BizMaterialSelfbuy bizMaterialSelfbuy) {
		
		String result = "1";
		//门店+工程模式+自采材料名称 = 自采材料清单
		List<BizMaterialSelfbuy> list = bizMaterialSelfbuyService.findMaterialList(bizMaterialSelfbuy);
		
		//如果数据库中存在
		if(CollectionUtils.isNotEmpty(list)){
			if(null!=bizMaterialSelfbuy.getId()){
				//修改
				for(BizMaterialSelfbuy last:list){
					if(last.getId().equals(bizMaterialSelfbuy.getId())){
						result = "0";
						break;
					}
				}
			}
		}else{
			result = "0";
		}
		return result;
	}
	
	
	
	/**
	 * 根据门店和工程模式  动态加载自选材料列表
	 * @param storeId
	 * @param projectModeValue
	 * @return
	 */
	@RequestMapping(value = "find_materialSelfbuy_list_ajax")
	public @ResponseBody List<BizMaterialSelfbuy> findMaterialSelfbuyListAjax(String storeId,String projectModeValue) {
		
		BizMaterialSelfbuy bizMaterialSelfbuy = new BizMaterialSelfbuy();

		//1.材料自选id
		if(StringUtils.isNotBlank(storeId)){
			bizMaterialSelfbuy.setStoreId(Integer.valueOf(storeId));
		}
		//2.状态
		if(StringUtils.isNotBlank(projectModeValue)){
			bizMaterialSelfbuy.setProjectMode(projectModeValue);
		}
		bizMaterialSelfbuy.setIsEnabled(MaterialSelfbuyConstantUtil.MATERIAL_SELFBUY_IS_ENABLED_1);
		
		return bizMaterialSelfbuyService.findMaterialSelfbuyListAjax(bizMaterialSelfbuy);
	}
	

}