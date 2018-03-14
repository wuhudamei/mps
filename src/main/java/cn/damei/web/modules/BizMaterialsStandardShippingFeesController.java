
package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizMaterialsStandardShippingFees;
import cn.damei.service.modules.BizMaterialsStandardShippingFeesService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees")
public class BizMaterialsStandardShippingFeesController extends BaseController {

	@Autowired
	private BizMaterialsStandardShippingFeesService bizMaterialsStandardShippingFeesService;
	
	@ModelAttribute
	public BizMaterialsStandardShippingFees get(@RequestParam(required=false) Integer id) {
		BizMaterialsStandardShippingFees entity = null;
		if (id!=null){
			entity = bizMaterialsStandardShippingFeesService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialsStandardShippingFees();
		}
		return entity;
	}
	
	@RequiresPermissions("bizmaterialsstandardshipfees:bizMaterialsStandardShippingFees:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMaterialsStandardShippingFees> page = bizMaterialsStandardShippingFeesService.findPage(new Page<BizMaterialsStandardShippingFees>(request, response), bizMaterialsStandardShippingFees);
		List<BizMaterialsStandardShippingFees> list = page.getList();

		HashMap<String, String> stringStringHashMap = new HashMap<>();
		Integer status=1;
		for (BizMaterialsStandardShippingFees biz:list) {
			if(status.equals(biz.getStatus())){
				stringStringHashMap.put(biz.getStoreId()+biz.getBizMaterialsStandardType().toString(),"FLAG");
			}
		}
		for (BizMaterialsStandardShippingFees bizFee:list) {
			if(StringUtils.isNotBlank(stringStringHashMap.get(bizFee.getStoreId()+bizFee.getBizMaterialsStandardType().toString()))){
				bizFee.setFlag(false);
			}else{
				bizFee.setFlag(true);
			}
		}
		page.setList(list);
		model.addAttribute("page", page);
		return "modules/managerSettlement/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFeesList";
	}

	@RequiresPermissions("bizmaterialsstandardshipfees:bizMaterialsStandardShippingFees:view")
	@RequestMapping(value = "form")
	public String form(BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees, Model model) {
		model.addAttribute("bizMaterialsStandardShippingFees", bizMaterialsStandardShippingFees);
		return "modules/managerSettlement/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFeesForm";
	}

	@RequiresPermissions("bizmaterialsstandardshipfees:bizMaterialsStandardShippingFees:edit")
	@RequestMapping(value = "save")
	public String save(BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMaterialsStandardShippingFees)){
			return form(bizMaterialsStandardShippingFees, model);
		}
		bizMaterialsStandardShippingFees.setCreateDate(new Date());
		User user = UserUtils.getUser();
		bizMaterialsStandardShippingFees.setCreateId(Integer.valueOf(user.getId()));
		bizMaterialsStandardShippingFees.setCreateName(user.getName());
		bizMaterialsStandardShippingFeesService.save(bizMaterialsStandardShippingFees);
		addMessage(redirectAttributes, "保存操作成功");
		return "redirect:"+Global.getAdminPath()+"/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/?repage";
	}
	@RequiresPermissions("bizmaterialsstandardshipfees:bizMaterialsStandardShippingFees:edit")
	@RequestMapping(value = "updateStatus")
	public String updateStatus(Integer id,Integer status, Model model, RedirectAttributes redirectAttributes) {
		BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees = new BizMaterialsStandardShippingFees();
		bizMaterialsStandardShippingFees.setId(id);
		bizMaterialsStandardShippingFees.setStatus(status);
		bizMaterialsStandardShippingFeesService.save(bizMaterialsStandardShippingFees);
		addMessage(redirectAttributes, "修改操作成功");
		return "redirect:"+Global.getAdminPath()+"/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/?repage";
	}

	
	@RequiresPermissions("bizmaterialsstandardshipfees:bizMaterialsStandardShippingFees:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees, RedirectAttributes redirectAttributes) {
		bizMaterialsStandardShippingFeesService.delete(bizMaterialsStandardShippingFees);
		addMessage(redirectAttributes, "删除操作成功");
		return "redirect:"+Global.getAdminPath()+"/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/?repage";
	}

}