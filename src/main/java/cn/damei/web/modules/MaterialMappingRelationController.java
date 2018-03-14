package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BaseEntity;
import cn.damei.entity.modules.MaterialMappingRelation;
import cn.damei.service.modules.MaterialMappingRelationSerivce;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value="${adminPath}/materialMappingRelation")
public class MaterialMappingRelationController {
	@Autowired
	private MaterialMappingRelationSerivce materialMappingRelationSerivce;

	@ModelAttribute
	public MaterialMappingRelation get(@RequestParam(required=false) String id) {
		MaterialMappingRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			
		}
		if (entity == null){
			entity = new MaterialMappingRelation();
		}
		return entity;
	}

	@RequestMapping(value="list")
	public String list(MaterialMappingRelation materialMappingRelation,HttpServletRequest request, HttpServletResponse response,Model model){
		
		Page<MaterialMappingRelation> findPage = materialMappingRelationSerivce.findPage(new Page<MaterialMappingRelation>(request,response), materialMappingRelation);
		model.addAttribute("page", findPage);
		return "modules/materialMappingRelation/materialMappingRelationList";
	}
	

	@RequestMapping(value="save")
	public String save(String[] installItemId,String[] categoryTwo,Model model){
		User user = UserUtils.getUser();
		if(installItemId !=null){
		List<MaterialMappingRelation> list = new ArrayList<>();
		for (int i = 0; i < categoryTwo.length; i++) {
			MaterialMappingRelation materialMappingRelation = new MaterialMappingRelation();
			materialMappingRelation.preInsert();
			materialMappingRelation.setCreateBy(user);
			materialMappingRelation.setUpdateBy(user);
			materialMappingRelation.setCategoryTwo(categoryTwo[i]);
			materialMappingRelation.setInstallItemId(installItemId[i]);
			list.add(materialMappingRelation);
		}
		materialMappingRelationSerivce.insertChoiceCategory(list);
		
		return "redirect:"+Global.getAdminPath()+"/materialMappingRelation/list";
		}else{
			return "modules/materialMappingRelation/materialMappingRelationForm";
		}
		
	}

	@RequestMapping(value="deleteRelation")
	
	public String deleteRelation(String id,HttpServletRequest request){
		materialMappingRelationSerivce.deleteRelation(id);
		return "forward:"+Global.getAdminPath()+"/materialMappingRelation/list";
	}

	@RequestMapping(value="findMainItem")
	@ResponseBody
	public String findMainItem(String storeId,String projectMode,HttpServletRequest request){
		List<BaseEntity> list = materialMappingRelationSerivce.findMainItem(storeId,projectMode);
		String string = JSONArray.fromObject(list).toString();
		return string;
	}

	@RequestMapping(value="findOneCategory")
	@ResponseBody
	public String findOneCategory(String level,HttpServletRequest request){
		List<BaseEntity> list = materialMappingRelationSerivce.findOneCategory(level);
		String string = JSONArray.fromObject(list).toString();
		return string;
	}

	@RequestMapping(value="findTowCategory")
	@ResponseBody
	public String findTowCategory(String level,String parentId,HttpServletRequest request){
		List<BaseEntity> list = materialMappingRelationSerivce.findTowCategory(level,parentId);
		String string = JSONArray.fromObject(list).toString();
		return string;
	}
	
	
	
	
}
