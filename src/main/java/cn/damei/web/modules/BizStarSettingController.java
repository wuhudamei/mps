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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.web.BaseController;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizBizEmployeegroupVoDao;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizStarSetting;
import cn.damei.service.modules.BizStarSettingService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/starSetting/starRatingSetting")
public class BizStarSettingController extends BaseController {

	@Autowired
	private BizStarSettingService bizStarSettingService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@Autowired   
	private BizBizEmployeegroupVoDao bizBizEmployeegroupVoDao;
	
	@ModelAttribute
	public BizStarSetting get(@RequestParam(required=false) Integer id) {
		BizStarSetting entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizStarSettingService.get(id);
		}
		if (entity == null){
			entity = new BizStarSetting();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(BizStarSetting bizStarSetting, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizStarSetting.getStoreId()){
			if(null!=user.getStoreId()){
				bizStarSetting.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		
		Page<BizStarSetting> page = bizStarSettingService.findPage(new Page<BizStarSetting>(request, response), bizStarSetting); 
		model.addAttribute("page", page);
		return "modules/bizstarsetting/bizStarSettingList";		
	}
		
		@RequestMapping(value = "list1")
		public String list1(BizStarSetting bizStarSetting, HttpServletRequest request, HttpServletResponse response, Model model) {
			
			User user = UserUtils.getUser();

			if(null==bizStarSetting.getStoreId()){
				if(null!=user.getStoreId()){
					bizStarSetting.setStoreId(Integer.valueOf(user.getStoreId()));
				}
			}
			Page<BizStarSetting> page = bizStarSettingService.findPage(new Page<BizStarSetting>(request, response), bizStarSetting); 
			model.addAttribute("page", page);
			return "modules/bizstarsetting/bizStarSettingList";
		}
		
	@RequestMapping(value = "findIsCopy")
	@ResponseBody
	public BizStarSetting findIsCopy(BizStarSetting bizStarSetting,HttpServletRequest request, Model model) {
		List<BizStarSetting>bbb= bizStarSettingService.findIsCopyStar(bizStarSetting);
		if(bbb.size()>0){
			BizStarSetting k=new BizStarSetting();
			k.setStartScore(-10.0);
			 return k;
		}
		else{
			List<BizStarSetting>st= bizStarSettingService.findIsCopy(bizStarSetting);
			if(st.size()>0){
				Double min = null; 
				Double max = null;
				Double startScore = bizStarSetting.getStartScore();
				Double endScore = bizStarSetting.getEndScore();
				
					for (BizStarSetting temp : st) {
						 min = temp.getStartScore();
						 max = temp.getEndScore();
					}
					if((startScore>=max&&endScore>max) || (startScore<=min&&endScore<=min) ){
						BizStarSetting k=new BizStarSetting();
						k.setStartScore(10000.0);
						return k;
						}
					else{
							BizStarSetting k=new BizStarSetting();
							k.setStartScore(min);
							k.setEndScore(max);
							return k;
						}
					
				}else{
					BizStarSetting k=new BizStarSetting();
					k.setStartScore(10000.0);
					return k;
				}
			
			}
	
		}
	
	@RequestMapping(value = "findIsNotCopyStar")
	@ResponseBody
	public BizStarSetting findIsNotCopyStar(BizStarSetting bizStarSetting,HttpServletRequest request, Model model) {
			List<BizStarSetting>st= bizStarSettingService.findIsNotCopyStar(bizStarSetting.getStoreId(),bizStarSetting.getProjectMode(),bizStarSetting.getStar()-1);
			List<BizStarSetting>stBehind= bizStarSettingService.findIsNotCopyStar(bizStarSetting.getStoreId(),bizStarSetting.getProjectMode(),bizStarSetting.getStar()+1);
			Double min = null; 
			Double max = null;
			Double startScore = bizStarSetting.getStartScore();
			Double endScore = bizStarSetting.getEndScore();
			if(st.size()>0){
				for (BizStarSetting temp : st) {
					min = temp.getStartScore();
					 max = temp.getEndScore();
					 if((startScore<max)){
							BizStarSetting k=new BizStarSetting();
							k.setStartScore(min);
							k.setEndScore(max);
							return k;
						}
				}
			}
			if(stBehind.size()>0){
				for (BizStarSetting temp : stBehind) {
					min = temp.getStartScore();
					 max = temp.getEndScore();
					 if((endScore>min && endScore<max)){
							BizStarSetting k=new BizStarSetting();
							k.setStartScore(min);
							k.setEndScore(max);
							return k;
						}
				}
			}

		return null;
	
		}

	@RequestMapping(value = "form")
	public String form(BizStarSetting bizStarSetting, Model model) {
		
		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
		model.addAttribute("bizStarSetting", bizStarSetting);
		return "modules/bizstarsetting/bizStarSettingForm";
	}

	@RequestMapping(value = "save")
	public String save(BizStarSetting bizStarSetting, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizStarSetting)){
			return form(bizStarSetting, model);
		}
		bizStarSettingService.save(bizStarSetting);
		addMessage(redirectAttributes, "保存星级分数区间成功");
		return "redirect:"+Global.getAdminPath()+"/starSetting/starRatingSetting/list";
	}
	
	@RequestMapping(value = "update")
	public String update(BizStarSetting bizStarSetting, Model model, RedirectAttributes redirectAttributes,Integer id) {
		if (!beanValidator(model, bizStarSetting)){
			return form(bizStarSetting, model);
		}
		bizStarSettingService.update(bizStarSetting);

		return "redirect:"+Global.getAdminPath()+"/starSetting/starRatingSetting/list";
	}
	
	@RequestMapping(value = "formEdit")
	public String formEdit(BizStarSetting bizStarSetting, Model model, RedirectAttributes redirectAttributes,Integer id) {
		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
		model.addAttribute("bizStarSetting", bizStarSetting);
		return "modules/bizstarsetting/bizStarSettingFormEdit";
	}
	
	@RequestMapping(value = "delete")
	public String delete(BizStarSetting bizStarSetting, RedirectAttributes redirectAttributes) {
		
		if (bizStarSetting.getIsEnabled().equals("1")) {
			bizStarSetting.setIsEnabled("0");
		} else {
			bizStarSetting.setIsEnabled("1");
		}
			
		bizStarSettingService.delete(bizStarSetting);
		addMessage(redirectAttributes, "修改星级分数区间状态成功");
		return "redirect:"+Global.getAdminPath()+"/starSetting/starRatingSetting/list";
	}
	

}