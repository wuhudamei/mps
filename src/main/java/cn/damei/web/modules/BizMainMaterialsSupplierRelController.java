
package cn.damei.web.modules;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizMainMaterialsSupplierRel;
import cn.damei.service.modules.BizMainMaterialsSupplierRelService;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.service.modules.BizSupplierService;


@Controller
@RequestMapping(value = "${adminPath}/mainmaterialssupplierrel/bizMainMaterialsSupplierRel")
public class BizMainMaterialsSupplierRelController extends BaseController {

	@Autowired
	private BizMainMaterialsSupplierRelService bizMainMaterialsSupplierRelService;
	
	@Autowired
	private BizSupplierService bizSupplierService;
	
	@ModelAttribute
	public BizMainMaterialsSupplierRel get(@RequestParam(required=false) Integer id) {
		BizMainMaterialsSupplierRel entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizMainMaterialsSupplierRelService.get(id);
		}
		if (entity == null){
			entity = new BizMainMaterialsSupplierRel();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(BizMainMaterialsSupplierRel bizMainMaterialsSupplierRel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMainMaterialsSupplierRel> page = bizMainMaterialsSupplierRelService.findPage(new Page<BizMainMaterialsSupplierRel>(request, response), bizMainMaterialsSupplierRel); 
		model.addAttribute("page", page);
		return "modules/mainmaterialssupplierrel/bizMainMaterialsSupplierRelList";
	}


	@RequestMapping(value = "form")
	public String form(BizMainMaterialsSupplierRel bizMainMaterialsSupplierRel, Model model) {
		model.addAttribute("bizMainMaterialsSupplierRel", bizMainMaterialsSupplierRel);
		return "modules/mainmaterialssupplierrel/bizMainMaterialsSupplierRelForm";
	}


	@RequestMapping(value = "save")
	public String save(BizMainMaterialsSupplierRel bizMainMaterialsSupplierRel, Model model, RedirectAttributes redirectAttributes) {
		
		if (!beanValidator(model, bizMainMaterialsSupplierRel)){
			return form(bizMainMaterialsSupplierRel, model);
		}

		if (bizMainMaterialsSupplierRel.getId() == null) {
			BizSupplier sup=bizSupplierService.get(bizMainMaterialsSupplierRel.getSupplierId()+"");
			if(sup!=null){
				bizMainMaterialsSupplierRel.setSupplierNo(sup.getSupplierNo());
			}
        }
		

		Integer maxVersion=bizMainMaterialsSupplierRelService.getMaxVersion(bizMainMaterialsSupplierRel);
		if(maxVersion==null){
			maxVersion=0;
		}
		maxVersion=maxVersion+1;
		bizMainMaterialsSupplierRel.setVersion(maxVersion);
				
		if(bizMainMaterialsSupplierRel.getId() == null){
			bizMainMaterialsSupplierRel.setIsNewRecord(true);
		}
		bizMainMaterialsSupplierRelService.save(bizMainMaterialsSupplierRel);
		addMessage(redirectAttributes, "保存主材供应商管理成功");
		return "redirect:"+Global.getAdminPath()+"/mainmaterialssupplierrel/bizMainMaterialsSupplierRel/?mainMaterialsId="+bizMainMaterialsSupplierRel.getMainMaterialsId()+"&repage";
	}
	

	@RequestMapping(value = "checkEffectiveDate" ,method= RequestMethod.POST)
	public @ResponseBody String checkEffectiveDate(@RequestParam(value="mainMaterialsId" )Integer mainMaterialsId,@RequestParam(value="supplierId" )Integer supplierId,@RequestParam(value="effectiveDate" )Date effectiveDate) {

		BizMainMaterialsSupplierRel prm=new BizMainMaterialsSupplierRel();
		prm.setMainMaterialsId(mainMaterialsId);
		prm.setSupplierId(supplierId);
		prm.setEffectiveDate(effectiveDate);
		prm.setDelFlag("0");
		List<BizMainMaterialsSupplierRel> list=bizMainMaterialsSupplierRelService.findList(prm);
		if(list!=null&&list.size()>0){
			return "0";
		}
		return "1";
	}
	

	@RequestMapping(value = "delete")
	public String delete(BizMainMaterialsSupplierRel bizMainMaterialsSupplierRel, RedirectAttributes redirectAttributes) {
		bizMainMaterialsSupplierRelService.delete(bizMainMaterialsSupplierRel);
		addMessage(redirectAttributes, "删除主材供应商管理成功");
		return "redirect:"+Global.getAdminPath()+"/mainmaterialssupplierrel/bizMainMaterialsSupplierRel/?mainMaterialsId="+bizMainMaterialsSupplierRel.getMainMaterialsId()+"&repage";
	}

}