
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizProcedureWorkerCrossReferences;
import cn.damei.service.modules.BizProcedureWorkerCrossReferencesService;
import cn.damei.entity.modules.Dict;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.BizTaskPackageTemplatDao;
import cn.damei.dao.modules.BizTaskPackageTemplatRelDao;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.entity.modules.BizTaskPackageTemplatRelProcedure;


@Controller
@RequestMapping(value = "${adminPath}/procedureworkercross/bizProcedureWorkerCrossReferences")
public class BizProcedureWorkerCrossReferencesController extends BaseController {

	@Autowired
	private BizProcedureWorkerCrossReferencesService bizProcedureWorkerCrossReferencesService;
	@Autowired
	private BizTaskPackageTemplatDao bizTaskPackageTemplatDao;
	
	@Autowired
	private BizTaskPackageTemplatRelDao BbzTaskPackageTemplatRelDao;
	
	@ModelAttribute
	public BizProcedureWorkerCrossReferences get(@RequestParam(required=false) String id) {
		BizProcedureWorkerCrossReferences entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizProcedureWorkerCrossReferencesService.get(id);
		}
		if (entity == null){
			entity = new BizProcedureWorkerCrossReferences();
		}
		return entity;
	}
	
	@RequiresPermissions("procedureworkercross:bizProcedureWorkerCrossReferences:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizProcedureWorkerCrossReferences bizProcedureWorkerCrossReferences, HttpServletRequest request, HttpServletResponse response, Model model) {

		if(StringUtils.isBlank(bizProcedureWorkerCrossReferences.getStoreId())){
			bizProcedureWorkerCrossReferences.setStoreId(UserUtils.getUser().getStoreId());
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}else{
			model.addAttribute("hiddenStoreId", UserUtils.getUser().getStoreId());
		}
		Page<BizProcedureWorkerCrossReferences> page = bizProcedureWorkerCrossReferencesService.findPage(new Page<BizProcedureWorkerCrossReferences>(request, response), bizProcedureWorkerCrossReferences); 
		model.addAttribute("page", page);
		return "modules/procedureworkercross/bizProcedureWorkerCrossReferencesList";
	}

	@RequiresPermissions("procedureworkercross:bizProcedureWorkerCrossReferences:view")
	@RequestMapping(value = "form")
	public String form(BizProcedureWorkerCrossReferences bizProcedureWorkerCrossReferences, Model model) {
		if(StringUtils.isBlank(bizProcedureWorkerCrossReferences.getStoreId())){
			bizProcedureWorkerCrossReferences.setStoreId(UserUtils.getUser().getStoreId());
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}else{
			model.addAttribute("hiddenStoreId", UserUtils.getUser().getStoreId());
		}
		
		model.addAttribute("bizProcedureWorkerCrossReferences", bizProcedureWorkerCrossReferences);
		return "modules/procedureworkercross/bizProcedureWorkerCrossReferencesForm";
	}

	@RequiresPermissions("procedureworkercross:bizProcedureWorkerCrossReferences:edit")
	@RequestMapping(value = "save")
	public String save(BizProcedureWorkerCrossReferences bizProcedureWorkerCrossReferences, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizProcedureWorkerCrossReferences)){
			return form(bizProcedureWorkerCrossReferences, model);
		}
		bizProcedureWorkerCrossReferencesService.save(bizProcedureWorkerCrossReferences);
		addMessage(redirectAttributes, "保存工序和工人星级对照成功");
		return "redirect:"+Global.getAdminPath()+"/procedureworkercross/bizProcedureWorkerCrossReferences/?repage";
	}
	
	@RequiresPermissions("procedureworkercross:bizProcedureWorkerCrossReferences:edit")
	@RequestMapping(value = "delete")
	public String delete(BizProcedureWorkerCrossReferences bizProcedureWorkerCrossReferences, RedirectAttributes redirectAttributes) {
		bizProcedureWorkerCrossReferencesService.delete(bizProcedureWorkerCrossReferences);
		addMessage(redirectAttributes, "删除工序和工人星级对照成功");
		return "redirect:"+Global.getAdminPath()+"/procedureworkercross/bizProcedureWorkerCrossReferences/?repage";
	}
	
	@RequestMapping(value = "selectTaskPakgDictByStoreId" ,method= RequestMethod.POST)
	public @ResponseBody List<Dict> selectTaskPakgDictByStoreId(@RequestParam(value="storeId" )String storeId) {
		List<Dict> dictList=new ArrayList<Dict>();
		List<BizTaskPackageTemplat> list = bizTaskPackageTemplatDao.findTaskPackageList(storeId);
		Dict dict=null;
		for (BizTaskPackageTemplat bizTaskPackageTemplat : list){
			dict=new Dict();
			dict.setLabel(bizTaskPackageTemplat.getTemplatName());
			dict.setValue(bizTaskPackageTemplat.getId());
			dictList.add(dict);
		}
		return dictList;
	}
	
	@RequestMapping(value = "selectprocedureDictByTaskPakgId" ,method= RequestMethod.POST)
	public @ResponseBody List<Dict> selectprocedureDictByTaskPakgId(@RequestParam(value="taskPackageTemplatId" )String taskPackageTemplatId) {
		List<Dict> dictList=new ArrayList<Dict>();
		List<BizTaskPackageTemplatRelProcedure> list=BbzTaskPackageTemplatRelDao.findByTaskPackageRelProcedureTemplateId(taskPackageTemplatId);
		Dict dict=null;
		for (BizTaskPackageTemplatRelProcedure rel : list){
			dict=new Dict();
			dict.setLabel(rel.getProcedureName());
			dict.setValue(rel.getProcedureNo());
			dictList.add(dict);
		}
		return dictList;
	}

}