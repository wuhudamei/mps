
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
import cn.damei.entity.modules.BizAuxiliaryMaterials;
import cn.damei.entity.modules.BizAuxiliaryMaterialsService;
import cn.damei.entity.modules.BizAuxiliaryMaterialsSupplierRel;
import cn.damei.entity.modules.BizAuxiliaryMaterialsSupplierRelService;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.service.modules.BizSupplierService;


@Controller
@RequestMapping(value = "${adminPath}/auxiliarymaterialssupplier/bizAuxiliaryMaterialsSupplierRel")
public class BizAuxiliaryMaterialsSupplierRelController extends BaseController {

	@Autowired
	private BizAuxiliaryMaterialsSupplierRelService bizAuxiliaryMaterialsSupplierRelService;
	@Autowired
	private BizSupplierService bizSupplierService;
	@Autowired
	private BizAuxiliaryMaterialsService bizAuxiliaryMaterialsService;
	
	@ModelAttribute
	public BizAuxiliaryMaterialsSupplierRel get(@RequestParam(required=false) String id) {
		BizAuxiliaryMaterialsSupplierRel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizAuxiliaryMaterialsSupplierRelService.get(id);
		}
		if (entity == null){
			entity = new BizAuxiliaryMaterialsSupplierRel();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel, HttpServletRequest request, HttpServletResponse response, Model model) {
		BizAuxiliaryMaterials bizAuxiliaryMaterials = bizAuxiliaryMaterialsService.get(bizAuxiliaryMaterialsSupplierRel.getAuxiliaryMaterialsId());
		bizAuxiliaryMaterialsSupplierRel.setDelFlag("0");
		Page<BizAuxiliaryMaterialsSupplierRel> page = bizAuxiliaryMaterialsSupplierRelService.findPage(new Page<BizAuxiliaryMaterialsSupplierRel>(request, response), bizAuxiliaryMaterialsSupplierRel); 
		model.addAttribute("page", page);
		model.addAttribute("bizAuxiliaryMaterials", bizAuxiliaryMaterials);
		return "modules/auxiliarymaterialssupplier/bizAuxiliaryMaterialsSupplierRelList";
	}
	

	@RequestMapping(value = "WZlist")
	public String WZlist(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel, HttpServletRequest request, HttpServletResponse response, Model model) {
		BizAuxiliaryMaterials bizAuxiliaryMaterials = bizAuxiliaryMaterialsService.get(bizAuxiliaryMaterialsSupplierRel.getAuxiliaryMaterialsId());
		bizAuxiliaryMaterialsSupplierRel.setDelFlag("0");
		Page<BizAuxiliaryMaterialsSupplierRel> page = bizAuxiliaryMaterialsSupplierRelService.findPage(new Page<BizAuxiliaryMaterialsSupplierRel>(request, response), bizAuxiliaryMaterialsSupplierRel); 
		model.addAttribute("page", page);
		model.addAttribute("bizAuxiliaryMaterials", bizAuxiliaryMaterials);
		return "modules/auxiliarymaterialssupplier/bizWZAuxiliaryMaterialsSupplierRelList";
	}

	@RequestMapping(value = "wZform")
	public String wZform(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel, Model model) {
		List<Integer> list = bizAuxiliaryMaterialsSupplierRelService.findSupplierName(bizAuxiliaryMaterialsSupplierRel);
		if(list!=null&&list.size()>0){
			Integer id = list.get(0);
			bizAuxiliaryMaterialsSupplierRel.setSupplierId(id+"");
		}

		model.addAttribute("bizAuxiliaryMaterialsSupplierRel", bizAuxiliaryMaterialsSupplierRel);
		return "modules/auxiliarymaterialssupplier/bizWZAuxiliaryMaterialsSupplierRelForm";
	}
	

	@RequestMapping(value = "saveWz")
	public String saveWz(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizAuxiliaryMaterialsSupplierRel)){
			return wZform(bizAuxiliaryMaterialsSupplierRel, model);
		}

		if (bizAuxiliaryMaterialsSupplierRel.getId() == null || "".equals(bizAuxiliaryMaterialsSupplierRel.getId())) {
			BizSupplier sup=bizSupplierService.get(bizAuxiliaryMaterialsSupplierRel.getSupplierId());
			if(sup!=null){
				bizAuxiliaryMaterialsSupplierRel.setSupplierNo(sup.getSupplierNo());
			}
        }

		Integer maxVersion=bizAuxiliaryMaterialsSupplierRelService.getMaxVersion(bizAuxiliaryMaterialsSupplierRel);
		if(maxVersion==null){
			maxVersion=0;
		}
		maxVersion=maxVersion+1;
		bizAuxiliaryMaterialsSupplierRel.setVersion(String.valueOf(maxVersion));
		
		bizAuxiliaryMaterialsSupplierRelService.saveWz(bizAuxiliaryMaterialsSupplierRel);
		addMessage(redirectAttributes, "保存辅料对应供应商成功");
		return "redirect:"+Global.getAdminPath()+"/auxiliarymaterialssupplier/bizAuxiliaryMaterialsSupplierRel/WZlist?auxiliaryMaterialsId="+bizAuxiliaryMaterialsSupplierRel.getAuxiliaryMaterialsId()+"&repage";
	}
	
	

	@RequestMapping(value = "supplierList")
	public String supplierList(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel, HttpServletRequest request, HttpServletResponse response, Model model) {
		BizAuxiliaryMaterials bizAuxiliaryMaterials = bizAuxiliaryMaterialsService.get(bizAuxiliaryMaterialsSupplierRel.getAuxiliaryMaterialsId());
		bizAuxiliaryMaterialsSupplierRel.setDelFlag("0");
		Page<BizAuxiliaryMaterialsSupplierRel> page = bizAuxiliaryMaterialsSupplierRelService.findPage(new Page<BizAuxiliaryMaterialsSupplierRel>(request, response), bizAuxiliaryMaterialsSupplierRel); 
		model.addAttribute("page", page);
		model.addAttribute("bizAuxiliaryMaterials", bizAuxiliaryMaterials);
		return "modules/auxiliarymaterialssupplier/bizSupplierListAuxiliaryMaterialsSupplierRelList";
	}
	

	@RequestMapping(value = "supplierform")
	public String supplierForm(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel, Model model) {
		List<Integer> list = bizAuxiliaryMaterialsSupplierRelService.findSupplierName(bizAuxiliaryMaterialsSupplierRel);
		if(list!=null&&list.size()>0){
			Integer id = list.get(0);
			bizAuxiliaryMaterialsSupplierRel.setSupplierId(id+"");
		}

		model.addAttribute("bizAuxiliaryMaterialsSupplierRel", bizAuxiliaryMaterialsSupplierRel);
		return "modules/auxiliarymaterialssupplier/bizSupplierAuxiliaryMaterialsSupplierRelForm";
	}
	

	@RequestMapping(value = "saveSupplier")
	public String saveSupplier(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizAuxiliaryMaterialsSupplierRel)){
			return wZform(bizAuxiliaryMaterialsSupplierRel, model);
		}

		if (bizAuxiliaryMaterialsSupplierRel.getId() == null || "".equals(bizAuxiliaryMaterialsSupplierRel.getId())) {
			BizSupplier sup=bizSupplierService.get(bizAuxiliaryMaterialsSupplierRel.getSupplierId());
			if(sup!=null){
				bizAuxiliaryMaterialsSupplierRel.setSupplierNo(sup.getSupplierNo());
			}
        }

		Integer maxVersion=bizAuxiliaryMaterialsSupplierRelService.getMaxVersion(bizAuxiliaryMaterialsSupplierRel);
		if(maxVersion==null){
			maxVersion=0;
		}
		maxVersion=maxVersion+1;
		bizAuxiliaryMaterialsSupplierRel.setVersion(String.valueOf(maxVersion));
		
		bizAuxiliaryMaterialsSupplierRelService.saveSupplier(bizAuxiliaryMaterialsSupplierRel);
		addMessage(redirectAttributes, "保存辅料对应供应商成功");
		return "redirect:"+Global.getAdminPath()+"/auxiliarymaterialssupplier/bizAuxiliaryMaterialsSupplierRel/supplierList?auxiliaryMaterialsId="+bizAuxiliaryMaterialsSupplierRel.getAuxiliaryMaterialsId()+"&repage";
	}
	

	@RequestMapping(value = "form")
	public String form(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel, Model model) {
		List<Integer> list = bizAuxiliaryMaterialsSupplierRelService.findSupplierName(bizAuxiliaryMaterialsSupplierRel);
		if(list!=null&&list.size()>0){
			Integer id = list.get(0);
			bizAuxiliaryMaterialsSupplierRel.setSupplierId(id+"");
		}

		model.addAttribute("bizAuxiliaryMaterialsSupplierRel", bizAuxiliaryMaterialsSupplierRel);
		return "modules/auxiliarymaterialssupplier/bizAuxiliaryMaterialsSupplierRelForm";
	}


	@RequestMapping(value = "save")
	public String save(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizAuxiliaryMaterialsSupplierRel)){
			return form(bizAuxiliaryMaterialsSupplierRel, model);
		}

		if (bizAuxiliaryMaterialsSupplierRel.getId() == null || "".equals(bizAuxiliaryMaterialsSupplierRel.getId())) {
			BizSupplier sup=bizSupplierService.get(bizAuxiliaryMaterialsSupplierRel.getSupplierId());
			if(sup!=null){
				bizAuxiliaryMaterialsSupplierRel.setSupplierNo(sup.getSupplierNo());
			}
        }

		Integer maxVersion=bizAuxiliaryMaterialsSupplierRelService.getMaxVersion(bizAuxiliaryMaterialsSupplierRel);
		if(maxVersion==null){
			maxVersion=0;
		}
		maxVersion=maxVersion+1;
		bizAuxiliaryMaterialsSupplierRel.setVersion(String.valueOf(maxVersion));
		
		bizAuxiliaryMaterialsSupplierRelService.save(bizAuxiliaryMaterialsSupplierRel);
		addMessage(redirectAttributes, "保存辅料对应供应商成功");
		return "redirect:"+Global.getAdminPath()+"/auxiliarymaterialssupplier/bizAuxiliaryMaterialsSupplierRel/?auxiliaryMaterialsId="+bizAuxiliaryMaterialsSupplierRel.getAuxiliaryMaterialsId()+"&repage";
	}
	

	@RequestMapping(value = "checkEffectiveDate" ,method= RequestMethod.POST)
	public @ResponseBody String checkEffectiveDate(@RequestParam(value="id" )String id,@RequestParam(value="auxiliaryMaterialsId" )String auxiliaryMaterialsId,@RequestParam(value="supplierId" )String supplierId,@RequestParam(value="effectiveDate" )Date effectiveDate) {

		BizAuxiliaryMaterialsSupplierRel prm=new BizAuxiliaryMaterialsSupplierRel();
		prm.setAuxiliaryMaterialsId(auxiliaryMaterialsId);
		prm.setSupplierId(supplierId);
		prm.setEffectiveDate(effectiveDate);
		prm.setDelFlag("0");
		List<BizAuxiliaryMaterialsSupplierRel> list=bizAuxiliaryMaterialsSupplierRelService.findList(prm);
		if(list!=null&&list.size()>0){
		for (BizAuxiliaryMaterialsSupplierRel as : list) {
			if(as.getDelFlag().equals("0")){
				if(as.getId().equals(id)){
					return "1";
				}
				return "0";
				}
			}
			
		}
		return "1";
	}
	
	
	
	

	@RequestMapping(value = "delete")
	public String delete(BizAuxiliaryMaterialsSupplierRel bizAuxiliaryMaterialsSupplierRel, RedirectAttributes redirectAttributes) {
		bizAuxiliaryMaterialsSupplierRelService.delete(bizAuxiliaryMaterialsSupplierRel);
		addMessage(redirectAttributes, "删除辅料对应供应商成功");
		return "redirect:"+Global.getAdminPath()+"/auxiliarymaterialssupplier/bizAuxiliaryMaterialsSupplierRel/?auxiliaryMaterialsId="+bizAuxiliaryMaterialsSupplierRel.getAuxiliaryMaterialsId()+"&repage";
	}

}