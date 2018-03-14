
package cn.damei.web.modules;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderExcel;
import cn.damei.service.modules.BizOrderExcelService;


@Controller
@RequestMapping(value = "${adminPath}/orderexcel/bizOrderExcel")
public class BizOrderExcelController extends BaseController {
	@Autowired
	private BizOrderExcelService bizOrderExcelService;
	
	@ModelAttribute
	public BizOrderExcel get(@RequestParam(required=false) String id) {
		BizOrderExcel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizOrderExcelService.get(id);
		}
		if (entity == null){
			entity = new BizOrderExcel();
		}
		return entity;
	}
	
	@RequiresPermissions("orderexcel:bizOrderExcel:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOrderExcel bizOrderExcel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderExcel> page = bizOrderExcelService.findPage(new Page<BizOrderExcel>(request, response), bizOrderExcel); 
		model.addAttribute("page", page);
		model.addAttribute("bizOrderExcel", bizOrderExcel);
		return "modules/orderexcel/bizOrderExcelList";
	}

	@RequiresPermissions("orderexcel:bizOrderExcel:view")
	@RequestMapping(value = "form")
	public String form(BizOrderExcel bizOrderExcel, Model model) {
		model.addAttribute("bizOrderExcel", bizOrderExcel);
		return "modules/orderexcel/bizOrderExcelForm";
	}
	@RequiresPermissions("orderexcel:bizOrderExcel:view")
	@RequestMapping(value = "details")
	public String details(BizOrderExcel bizOrderExcel, Model model) {
		model.addAttribute("bizOrderExcel", bizOrderExcel);
		return "modules/orderexcel/bizOrderExcelFormDetails";
	}

	@RequiresPermissions("orderexcel:bizOrderExcel:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderExcel bizOrderExcel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderExcel)){
			return form(bizOrderExcel, model);
		}
		

		List<BizOrderExcel> list = bizOrderExcelService.findList(bizOrderExcel);
		if(null!=list){
			List<Integer> versions = new ArrayList<Integer>();
			for(BizOrderExcel excel:list){
				versions.add(excel.getVersion());
			}
			bizOrderExcel.setVersion(versions.size()+1);
		}else{
			bizOrderExcel.setVersion(1);
		}
		
		
		bizOrderExcelService.save(bizOrderExcel);
		addMessage(redirectAttributes, "保存excel文件上传成功");
		return "redirect:"+Global.getAdminPath()+"/orderexcel/bizOrderExcel/?repage&orderId="+bizOrderExcel.getOrderId()+"&flag="+bizOrderExcel.getFlag();
	}
	
	@RequiresPermissions("orderexcel:bizOrderExcel:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderExcel bizOrderExcel, RedirectAttributes redirectAttributes) {
		bizOrderExcelService.delete(bizOrderExcel);
		addMessage(redirectAttributes, "删除excel文件上传成功");
		return "redirect:"+Global.getAdminPath()+"/orderexcel/bizOrderExcel/list?repage&orderId="+bizOrderExcel.getOrderId()+"&flag="+bizOrderExcel.getFlag();
	}

}