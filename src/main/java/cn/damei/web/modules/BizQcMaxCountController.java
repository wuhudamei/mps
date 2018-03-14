
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizQcMaxCount;
import cn.damei.service.modules.BizQcMaxCountService;


@Controller
@RequestMapping(value = "${adminPath}/bizqccount/bizQcMaxCount")
public class BizQcMaxCountController extends BaseController {

	@Autowired
	private BizQcMaxCountService bizQcMaxCountService;
	
	@ModelAttribute
	public BizQcMaxCount get(@RequestParam(required=false) String id) {
		BizQcMaxCount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizQcMaxCountService.get(id);
		}
		if (entity == null){
			entity = new BizQcMaxCount();
		}
		return entity;
	}
	
	@RequiresPermissions("bizqccount:bizQcMaxCount:view")
	@RequestMapping(value = {"list"})
	public String list(BizQcMaxCount bizQcMaxCount, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/bizqccount/bizQcMaxCountList";
	}
    @RequiresPermissions("bizqccount:bizQcMaxCount:view")
    @RequestMapping(value = {"findList"})
    public String findList(BizQcMaxCount bizQcMaxCount, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<BizQcMaxCount> page = bizQcMaxCountService.findPage(new Page<BizQcMaxCount>(request, response), bizQcMaxCount);
        model.addAttribute("page", page);
        return "modules/bizqccount/bizQcMaxCountList";
    }

	@RequiresPermissions("bizqccount:bizQcMaxCount:view")
	@RequestMapping(value = "form")
	public String form(BizQcMaxCount bizQcMaxCount, Model model) {
		model.addAttribute("bizQcMaxCount", bizQcMaxCount);
		return "modules/bizqccount/bizQcMaxCountForm";
	}

	@RequiresPermissions("bizqccount:bizQcMaxCount:edit")
	@RequestMapping(value = "save")
	public
	String save(BizQcMaxCount bizQcMaxCount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcMaxCount)){
			return form(bizQcMaxCount, model);
		}
		String storeId = bizQcMaxCount.getStoreId();
		if (null != storeId && !"".equals(storeId.trim())) {
			try {

				Integer count =bizQcMaxCountService.storeOnlyForPqcCount(Integer.parseInt(storeId));

				if(count>0){

					addMessage(redirectAttributes, "该门店已经配置过了");
				}else{
					bizQcMaxCountService.save(bizQcMaxCount);
					addMessage(redirectAttributes, "保存约检数量配置成功");
				}

			} catch (ClassCastException e) {


				e.printStackTrace();
				addMessage(redirectAttributes, "门店参数有误");
			}


		}
		return "redirect:"+Global.getAdminPath()+"/bizqccount/bizQcMaxCount/?repage";
	}
	
	@RequiresPermissions("bizqccount:bizQcMaxCount:edit")
	@RequestMapping(value = "delete")
	public @ResponseBody  String delete(BizQcMaxCount bizQcMaxCount, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		bizQcMaxCountService.delete(bizQcMaxCount);

		return "1";
	}


	@RequiresPermissions("bizqccount:bizQcMaxCount:edit")
	@RequestMapping(value = "ajaxChangeCount")
	public @ResponseBody
	String ajaxChangeCount(BizQcMaxCount bizQcMaxCount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcMaxCount)){
			return form(bizQcMaxCount, model);
		}
		bizQcMaxCountService.save(bizQcMaxCount);

		return "1";
	}
	@RequiresPermissions("qcapplycheckdelete:bizApplyCheckDelete:edit")
	@RequestMapping(value = "storeOnly")
	public @ResponseBody
	String storeOnly(BizQcMaxCount bizQcMaxCount, RedirectAttributes redirectAttributes) {

		String storeId = bizQcMaxCount.getStoreId();

		if (null != storeId && !"".equals(storeId.trim())) {
			try {

				Integer count =bizQcMaxCountService.storeOnlyForPqcCount(Integer.parseInt(storeId));

				if(count>0){

					return "3";
				}else{

					return "1";
				}

			} catch (ClassCastException e) {


				e.printStackTrace();
				return "2";
			}


		}else{
			return "2";
		}


	}

}