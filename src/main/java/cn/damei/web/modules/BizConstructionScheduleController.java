
package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizConstructionSchedule;
import cn.damei.service.modules.BizConstructionScheduleService;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/constructionschedule/bizConstructionSchedule")
public class BizConstructionScheduleController extends BaseController {

	@Autowired
	private BizConstructionScheduleService bizConstructionScheduleService;
	
	@ModelAttribute
	public BizConstructionSchedule get(@RequestParam(required=false) String id) {
		BizConstructionSchedule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizConstructionScheduleService.get(id);
		}
		if (entity == null){
			entity = new BizConstructionSchedule();
		}
		return entity;
	}
	
	@RequiresPermissions("constructionschedule:bizConstructionSchedule:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizConstructionSchedule bizConstructionSchedule, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/constructionschedule/bizConstructionScheduleList";
	}
	@RequiresPermissions("constructionschedule:bizConstructionSchedule:view")
	@RequestMapping(value = {"list1"})
	public String list1(BizConstructionSchedule bizConstructionSchedule, HttpServletRequest request, HttpServletResponse response, Model model) {

		int x =0;
				if(!UserUtils.getUser().getOffice().getId().equals("1")){

					bizConstructionSchedule.setStoreId(UserUtils.getUser().getStoreId());
					x++;
				}
				Page<BizConstructionSchedule> page = bizConstructionScheduleService.findPage(new Page<BizConstructionSchedule>(request, response), bizConstructionSchedule);
				if(x>0){
					bizConstructionSchedule.setStoreId(null);
					
				}
				model.addAttribute("page", page);
		return "modules/constructionschedule/bizConstructionScheduleList";
	}

	@RequiresPermissions("constructionschedule:bizConstructionSchedule:view")
	@RequestMapping(value = "form")
	public String form(BizConstructionSchedule bizConstructionSchedule, Model model) {
		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
		model.addAttribute("bizConstructionSchedule", bizConstructionSchedule);
		model.addAttribute("userProjectMode", UserUtils.getUser().getProjectMode());
		
		return "modules/constructionschedule/bizConstructionScheduleForm";
	}

	@RequiresPermissions("constructionschedule:bizConstructionSchedule:edit")
	@RequestMapping(value = "save")
	public String save(BizConstructionSchedule bizConstructionSchedule, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizConstructionSchedule)){
			return form(bizConstructionSchedule, model);
		}
		bizConstructionScheduleService.save(bizConstructionSchedule);
		addMessage(redirectAttributes, "保存工程进度节点成功");
		return "redirect:"+Global.getAdminPath()+"/constructionschedule/bizConstructionSchedule/?repage";
	}
	
	@RequiresPermissions("constructionschedule:bizConstructionSchedule:edit")
	@RequestMapping(value = "delete")
	public String delete(BizConstructionSchedule bizConstructionSchedule, RedirectAttributes redirectAttributes) {
		bizConstructionScheduleService.delete(bizConstructionSchedule);
		addMessage(redirectAttributes, "删除工程进度节点成功");
		return "redirect:"+Global.getAdminPath()+"/constructionschedule/bizConstructionSchedule/?repage";
	}
	
	@RequestMapping(value = "getByStoreIdList")
	public @ResponseBody List<BizConstructionSchedule> getByStoreIdList(BizConstructionSchedule constructionSchedule,HttpServletRequest request){
		String storeId = request.getParameter("storeId");
		logger.info("取到的门店ID======" + storeId );
		
		List<BizConstructionSchedule> csList = bizConstructionScheduleService.getByStoreIdList(storeId);
		
		logger.info("BizConstructionScheduleController getByStoreIdList 返回的数据个数csList.size()：" + csList.size());
		return csList;
	}

}