
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizMaterialsStandardReceiveDetail;
import cn.damei.service.modules.BizMaterialsStandardReceiveDetailService;


@Controller
@RequestMapping(value = "${adminPath}/standradmaterialsdetail/bizMaterialsStandardReceiveDetail")
public class BizMaterialsStandardReceiveDetailController extends BaseController {

	@Autowired
	private BizMaterialsStandardReceiveDetailService bizMaterialsStandardReceiveDetailService;
	
	@ModelAttribute
	public BizMaterialsStandardReceiveDetail get(@RequestParam(required=false) Integer id) {
		BizMaterialsStandardReceiveDetail entity = null;
		if (id != null){
			entity = bizMaterialsStandardReceiveDetailService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialsStandardReceiveDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("standradmaterialsdetail:bizMaterialsStandardReceiveDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMaterialsStandardReceiveDetail> page = bizMaterialsStandardReceiveDetailService.findPage(new Page<BizMaterialsStandardReceiveDetail>(request, response), bizMaterialsStandardReceiveDetail); 
		model.addAttribute("page", page);
		return "modules/managerSettlement/standradmaterialsdetail/bizMaterialsStandardReceiveDetailList";
	}

	@RequiresPermissions("standradmaterialsdetail:bizMaterialsStandardReceiveDetail:view")
	@RequestMapping(value = "form")
	public String form(BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail, Model model) {
		model.addAttribute("bizMaterialsStandardReceiveDetail", bizMaterialsStandardReceiveDetail);
		return "modules/managerSettlement/standradmaterialsdetail/bizMaterialsStandardReceiveDetailForm";
	}

	@RequiresPermissions("standradmaterialsdetail:bizMaterialsStandardReceiveDetail:edit")
	@RequestMapping(value = "save")
	public String save(BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMaterialsStandardReceiveDetail)){
			return form(bizMaterialsStandardReceiveDetail, model);
		}
		bizMaterialsStandardReceiveDetailService.save(bizMaterialsStandardReceiveDetail);
		addMessage(redirectAttributes, "保存标化辅材领取详情成功");
		return "redirect:"+Global.getAdminPath()+"/standradmaterialsdetail/bizMaterialsStandardReceiveDetail/?repage";
	}
	
	@RequiresPermissions("standradmaterialsdetail:bizMaterialsStandardReceiveDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMaterialsStandardReceiveDetail bizMaterialsStandardReceiveDetail, RedirectAttributes redirectAttributes) {
		bizMaterialsStandardReceiveDetailService.delete(bizMaterialsStandardReceiveDetail);
		addMessage(redirectAttributes, "删除标化辅材领取详情成功");
		return "redirect:"+Global.getAdminPath()+"/standradmaterialsdetail/bizMaterialsStandardReceiveDetail/?repage";
	}

}