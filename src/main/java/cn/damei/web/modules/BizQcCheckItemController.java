
package cn.damei.web.modules;

import java.util.List;
import java.util.Map;

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
import cn.damei.entity.modules.BizQcCheckItem;
import cn.damei.service.modules.BizQcCheckItemService;
import cn.damei.entity.modules.BizQcCheckKind;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizqccheckitem/bizQcCheckItem")
public class BizQcCheckItemController extends BaseController {

	@Autowired
	private BizQcCheckItemService bizQcCheckItemService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizQcCheckItem get(@RequestParam(required=false) Integer id) {
		BizQcCheckItem entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizQcCheckItemService.get(id);
		}
		if (entity == null){
			entity = new BizQcCheckItem();
		}
		return entity;
	}
	
	@RequiresPermissions("bizqccheckitem:bizQcCheckItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizQcCheckItem bizQcCheckItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizQcCheckItem.getStoreId()){
			if(null!=user.getStoreId()){
				bizQcCheckItem.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizQcCheckItem.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizQcCheckItem.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizQcCheckItem.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/bizqccheckitem/bizQcCheckItemList";
	}
	
	@RequestMapping(value = "checkKind")
	public @ResponseBody List<BizQcCheckKind> checkKind(String storeId,String projectMode,HttpServletRequest request, HttpServletResponse response, Model model) {
		BizQcCheckKind kind = new BizQcCheckKind();
		kind.setStoreId(storeId);
		kind.setProjectMode(projectMode);
		List<BizQcCheckKind> checkKindList = bizQcCheckItemService.findCheckKind(kind);
		return checkKindList;
	}
	
	@RequiresPermissions("bizqccheckitem:bizQcCheckItem:view")
	@RequestMapping(value = {"itemList", ""})
	public String itemList(BizQcCheckItem bizQcCheckItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizQcCheckItem.getStoreId()){
			if(null!=user.getStoreId()){
				bizQcCheckItem.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizQcCheckItem.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizQcCheckItem.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizQcCheckItem.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizQcCheckItem> page = bizQcCheckItemService.findPage(new Page<BizQcCheckItem>(request, response), bizQcCheckItem); 
		model.addAttribute("page", page);
		return "modules/bizqccheckitem/bizQcCheckItemList";
	}

	@RequiresPermissions("bizqccheckitem:bizQcCheckItem:view")
	@RequestMapping(value = "form")
	public String form(BizQcCheckItem bizQcCheckItem, Model model) {
		Integer a = bizQcCheckItem.getQcCheckKindId();
		if(null!=a && a>=1){
			String name = bizQcCheckItemService.findName(a);
			model.addAttribute("a", a);
			model.addAttribute("name", name);
			
		}
		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
		model.addAttribute("bizQcCheckItem", bizQcCheckItem);
		return "modules/bizqccheckitem/bizQcCheckItemForm";
	}

	@RequiresPermissions("bizqccheckitem:bizQcCheckItem:edit")
	@RequestMapping(value = "save")
	public String save(BizQcCheckItem bizQcCheckItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizQcCheckItem)){
			return form(bizQcCheckItem, model);
		}
		bizQcCheckItemService.save(bizQcCheckItem);
		addMessage(redirectAttributes, "保存检查项成功");
		return "redirect:"+Global.getAdminPath()+"/bizqccheckitem/bizQcCheckItem/itemList?repage";
	}
	
	@RequiresPermissions("bizqccheckitem:bizQcCheckItem:edit")
	@RequestMapping(value = "delete")
	public String delete(BizQcCheckItem bizQcCheckItem, RedirectAttributes redirectAttributes) {
		
		if (bizQcCheckItem.getStatus().equals("1")) {
			bizQcCheckItem.setStatus("0");
		} else {
			bizQcCheckItem.setStatus("1");
		}
		bizQcCheckItemService.delete(bizQcCheckItem);
		addMessage(redirectAttributes, "删除检查项成功");
		return "redirect:"+Global.getAdminPath()+"/bizqccheckitem/bizQcCheckItem/itemList?repage";
	}
	@RequestMapping(value = "taskPackageTemplat")
	@ResponseBody
	public List<Map<String,String>> taskPackageTemplat(Integer id) {

		List<Map<String,String>> list=	bizQcCheckItemService.taskPackageTemplat(id);

		return list;
	}



	@RequestMapping(value = "/ajaxQcCheckKindAll")
	@ResponseBody
	public Object ajaxQcCheckKindAll(BizQcCheckItem bizQcCheckItem, Model model, RedirectAttributes redirectAttributes) {

		List<Map<String,String>> list=	bizQcCheckItemService.ueryQcCheckKindAll(bizQcCheckItem);
		return  list;
	}

	@RequestMapping(value = "/ajaxUpdatecheckItem")
	@ResponseBody
	public Object ajaxUpdatecheckItem(BizQcCheckItem bizQcCheckItem, Model model, RedirectAttributes redirectAttributes) {

		List<Map<String,String>> list=	bizQcCheckItemService.updatecheckItem(bizQcCheckItem);
		return  list;
	}
}