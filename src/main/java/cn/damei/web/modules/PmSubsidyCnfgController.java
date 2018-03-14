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

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.entity.modules.PmSubsidyCnfg;
import cn.damei.service.modules.PmSubsidyCnfgService;

@Controller
@RequestMapping(value="${adminPath}/pmsubsidycnfg")
public class PmSubsidyCnfgController {

	@Autowired
	private PmSubsidyCnfgService pmSubsidyCnfgService;
	
	@ModelAttribute
	public PmSubsidyCnfg get(@RequestParam(required=false) Integer id) {
		PmSubsidyCnfg entity = null;
		if (id != null){
			entity = pmSubsidyCnfgService.get(id);
		}
		if (entity == null){
			entity = new PmSubsidyCnfg();
		}
		return entity;
	}
	
	@RequestMapping(value="list")
	public String list(PmSubsidyCnfg pmSubsidyCnfg,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<PmSubsidyCnfg> findPage = pmSubsidyCnfgService.findPage(new Page<PmSubsidyCnfg>(request, response), pmSubsidyCnfg);
		model.addAttribute("page", findPage);
		return "/modules/pmsubsidycnfg/pmsubsidycnfgList";
	}
	@RequestMapping(value="form")
	public String form(PmSubsidyCnfg pmSubsidyCnfg,HttpServletRequest request,HttpServletResponse response,Model model){
		return "/modules/pmsubsidycnfg/pmsubsidycnfgForm";
	}

	@RequestMapping(value="checkedSquare")
	@ResponseBody
	public PmSubsidyCnfg checkedSquare(PmSubsidyCnfg pmSubsidyCnfg){
		Double squareMax = pmSubsidyCnfg.getSquareMax();
		Double squareMin = pmSubsidyCnfg.getSquareMin();
		
		List<PmSubsidyCnfg> list = pmSubsidyCnfgService.findAllPmSubsidyCnfg(pmSubsidyCnfg);
		for (PmSubsidyCnfg temp : list) {
			Double min = temp.getSquareMin();
			Double max = temp.getSquareMax();
			Boolean isScopemax = squareMax>=min&&squareMax<=max?true:false;
			Boolean isScopemin = squareMin>=min&&squareMin<=max?true:false;
			if(isScopemax == true || isScopemin == true){
				Integer id = pmSubsidyCnfg.getId();
				Integer id2 = temp.getId();
				if(!id.equals(id2)){
					return temp;
				}
				
			}
		}
		
		for (PmSubsidyCnfg temp2 : list) {
			Double min = temp2.getSquareMin();
			Double max = temp2.getSquareMax();
			Boolean isScope = min>squareMin&&min<squareMax?true:false;
			Boolean isScope2 = max>squareMin&&max<squareMax?true:false;
			if(isScope == true || isScope2 == true){
				Integer id = pmSubsidyCnfg.getId();
				Integer id2 = temp2.getId();
				if(!id.equals(id2)){
					return temp2;
				}
			}
		}
		
		return null;
	}

	@RequestMapping(value="save")
	public String save(PmSubsidyCnfg pmSubsidyCnfg){
		pmSubsidyCnfg.preInsert();
		pmSubsidyCnfgService.save(pmSubsidyCnfg);
		return "forward:"+Global.getAdminPath()+"/pmsubsidycnfg/list";
	}

	@RequestMapping(value="update")
	public String update(PmSubsidyCnfg pmSubsidyCnfg){
		pmSubsidyCnfgService.isEnabel(pmSubsidyCnfg);
		return "forward:"+Global.getAdminPath()+"/pmsubsidycnfg/list";
	}
	
}
