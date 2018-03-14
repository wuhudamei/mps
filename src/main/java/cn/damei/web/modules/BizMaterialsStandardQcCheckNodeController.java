/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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
import cn.damei.entity.modules.BizMaterialsStandardQcCheckNode;
import cn.damei.service.modules.BizMaterialsStandardQcCheckNodeService;

/**
 * 标化筒灯约检节点配置Controller
 * @author lft
 * @version 2017-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode")
public class BizMaterialsStandardQcCheckNodeController extends BaseController {

	@Autowired
	private BizMaterialsStandardQcCheckNodeService bizMaterialsStandardQcCheckNodeService;
	
	@ModelAttribute
	public BizMaterialsStandardQcCheckNode get(@RequestParam(required=false) Integer id) {
		BizMaterialsStandardQcCheckNode entity = null;
		if (id!=null){
			entity = bizMaterialsStandardQcCheckNodeService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialsStandardQcCheckNode();
		}
		return entity;
	}
	
	@RequiresPermissions("bizmaterialsstandardchecknode:bizMaterialsStandardQcCheckNode:view")
	@RequestMapping(value = {"list"})
	public String list(BizMaterialsStandardQcCheckNode bizMaterialsStandardQcCheckNode, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<BizMaterialsStandardQcCheckNode> page = bizMaterialsStandardQcCheckNodeService.findPage(new Page<BizMaterialsStandardQcCheckNode>(request, response), bizMaterialsStandardQcCheckNode);
		model.addAttribute("page", page);*/
		return "modules/managerSettlement/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNodeList";
	}

    @RequiresPermissions("bizmaterialsstandardchecknode:bizMaterialsStandardQcCheckNode:view")
    @RequestMapping(value = {"findList"})
    public String findList(BizMaterialsStandardQcCheckNode bizMaterialsStandardQcCheckNode, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<BizMaterialsStandardQcCheckNode> page = bizMaterialsStandardQcCheckNodeService.findPage(new Page<BizMaterialsStandardQcCheckNode>(request, response), bizMaterialsStandardQcCheckNode);
        model.addAttribute("page", page);
        return "modules/managerSettlement/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNodeList";
    }


	@RequiresPermissions("bizmaterialsstandardchecknode:bizMaterialsStandardQcCheckNode:view")
	@RequestMapping(value = "form")
	public String form(BizMaterialsStandardQcCheckNode bizMaterialsStandardQcCheckNode, Model model) {
		model.addAttribute("bizMaterialsStandardQcCheckNode", bizMaterialsStandardQcCheckNode);
		return "modules/managerSettlement/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNodeForm";
	}

	@RequiresPermissions("bizmaterialsstandardchecknode:bizMaterialsStandardQcCheckNode:edit")
	@RequestMapping(value = "save")
	public String save(BizMaterialsStandardQcCheckNode bizMaterialsStandardQcCheckNode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMaterialsStandardQcCheckNode)){
			return form(bizMaterialsStandardQcCheckNode, model);
		}
		//查询节点 有没有添加
		Integer checkNodeByOther = bizMaterialsStandardQcCheckNodeService.getCheckNodeByOther(String.valueOf(bizMaterialsStandardQcCheckNode.getStoreId()), bizMaterialsStandardQcCheckNode.getProjectMode(), bizMaterialsStandardQcCheckNode.getMaterialType());
		//有则不添加
		if(checkNodeByOther>0){
			model.addAttribute("bizMaterialsStandardQcCheckNode", bizMaterialsStandardQcCheckNode);
			//已经添加过的标记
			model.addAttribute("flag", false);
			return "modules/managerSettlement/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNodeForm";
		}else{ //没有则 添加
		String remarks = bizMaterialsStandardQcCheckNode.getRemarks();
		if(remarks==null||remarks.equals("")){
			bizMaterialsStandardQcCheckNode.setRemarks("此节点质检验收通过后，项目经理将不可再次申请标化材料");
		}
		bizMaterialsStandardQcCheckNodeService.save(bizMaterialsStandardQcCheckNode);
		addMessage(redirectAttributes, "保存节点成功");
		}
		return "redirect:"+Global.getAdminPath()+"/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/findList?repage";
	}
	
	@RequiresPermissions("bizmaterialsstandardchecknode:bizMaterialsStandardQcCheckNode:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMaterialsStandardQcCheckNode bizMaterialsStandardQcCheckNode, RedirectAttributes redirectAttributes) {
		bizMaterialsStandardQcCheckNodeService.delete(bizMaterialsStandardQcCheckNode);
		addMessage(redirectAttributes, "删除节点成功");
		return "redirect:"+Global.getAdminPath()+"/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/findList?repage";
	}
	
	//获取 材料列表
	@RequestMapping(value = "getMaterialsType")
	public @ResponseBody List<Map<String,String>>getMaterialsTypeByType(String type){
		return bizMaterialsStandardQcCheckNodeService.getMaterialsTypeByType(type);
	}
	//获取 节点列表
	@RequestMapping(value = "getCheckNode")
	public @ResponseBody List<Map<String,String>>getCheckNodeListByStoreIdAndMode(String storeId,String projectMode){
		return bizMaterialsStandardQcCheckNodeService.getCheckNodeListByStoreIdAndMode(storeId,projectMode);
	}
	

}