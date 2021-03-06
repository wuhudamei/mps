
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.common.utils.SpringContextHolder;
import cn.damei.entity.modules.DropModel;
import cn.damei.dao.modules.BizEngineeringDepartmentDao;
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
import cn.damei.entity.modules.BizNormalPmSettleNode;
import cn.damei.service.modules.BizNormalPmSettleNodeService;

import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/bizsettlenodeconfig/bizNormalPmSettleNode")
public class BizNormalPmSettleNodeController extends BaseController {

	@Autowired
	private BizNormalPmSettleNodeService bizNormalPmSettleNodeService;
	
	@ModelAttribute
	public BizNormalPmSettleNode get(@RequestParam(required=false) String id) {
		BizNormalPmSettleNode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizNormalPmSettleNodeService.get(id);
		}
		if (entity == null){
			entity = new BizNormalPmSettleNode();
		}
		return entity;
	}
	
	@RequiresPermissions("bizsettlenodeconfig:bizNormalPmSettleNode:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizNormalPmSettleNode bizNormalPmSettleNode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizNormalPmSettleNode> page = bizNormalPmSettleNodeService.findPage(new Page<BizNormalPmSettleNode>(request, response), bizNormalPmSettleNode); 
		model.addAttribute("page", page);
		return "modules/bizsettlenodeconfig/bizNormalPmSettleNodeList";
	}

	@RequiresPermissions("bizsettlenodeconfig:bizNormalPmSettleNode:view")
	@RequestMapping(value = "form")
	public String form(BizNormalPmSettleNode bizNormalPmSettleNode, Model model) {

		BizNormalPmSettleNode node2 = bizNormalPmSettleNodeService.findSettleNodeList(bizNormalPmSettleNode);
		
		model.addAttribute("bizNormalPmSettleNode", bizNormalPmSettleNode);
		model.addAttribute("node", node2);
		return "modules/bizsettlenodeconfig/bizNormalPmSettleNodeForm";
	}

	@RequiresPermissions("bizsettlenodeconfig:bizNormalPmSettleNode:edit")
	@RequestMapping(value = "save")
	public String save(BizNormalPmSettleNode bizNormalPmSettleNode, Model model, RedirectAttributes redirectAttributes) {

		addMessage(redirectAttributes, "保存结算节点配置成功");
		return "redirect:"+Global.getAdminPath()+"/bizsettlenodeconfig/bizNormalPmSettleNode/?repage";
	}
	
	@RequiresPermissions("bizsettlenodeconfig:bizNormalPmSettleNode:edit")
	@RequestMapping(value = "delete")
	public String delete(BizNormalPmSettleNode bizNormalPmSettleNode, RedirectAttributes redirectAttributes) {
		bizNormalPmSettleNodeService.delete(bizNormalPmSettleNode);
		addMessage(redirectAttributes, "删除结算节点配置成功");
		return "redirect:"+Global.getAdminPath()+"/bizsettlenodeconfig/bizNormalPmSettleNode/?repage";
	}

	private static BizEngineeringDepartmentDao engineeringDepartmentDao = SpringContextHolder.getBean(BizEngineeringDepartmentDao.class);





	@RequiresPermissions("bizsettlenodeconfig:bizNormalPmSettleNode:view")
	@RequestMapping(value = "getCheckNodeDoneByStoreIdAndProjectModel")
	public @ResponseBody List<DropModel> getCheckNodeDoneByStoreIdAndProjectModel(String storeId, String projectMode){



		return engineeringDepartmentDao.findCheckNodeByStoreIdAndProjectModel(storeId,projectMode);

	}


	@RequiresPermissions("bizsettlenodeconfig:bizNormalPmSettleNode:view")
	@RequestMapping(value = "saveOrUpdateSettleNode")
	public @ResponseBody String saveOrUpdateSettleNode(BizNormalPmSettleNode bizNormalPmSettleNode){


		Integer count = bizNormalPmSettleNodeService.checkCheckNodeIsExistChecked(bizNormalPmSettleNode);
		if(0==count){

			if("4".equals(bizNormalPmSettleNode.getProjectMode())){

				Integer temp = bizNormalPmSettleNodeService.findSettleStage(bizNormalPmSettleNode);

				if(temp == 0){
					bizNormalPmSettleNodeService.save(bizNormalPmSettleNode);
					return "1";
				}else{

					return "4";
				}
			}else{
				bizNormalPmSettleNodeService.save(bizNormalPmSettleNode);
				return "1";
			}
			
		
		}else{
				bizNormalPmSettleNodeService.updateSettleNodeByStoreIdAndSettleNodeIndex(bizNormalPmSettleNode);
				return "2";
		}
		

	}


	@RequiresPermissions("bizsettlenodeconfig:bizNormalPmSettleNode:view")
	@RequestMapping(value = "deleteSettleNode")
	public @ResponseBody String deleteSettleNode(BizNormalPmSettleNode bizNormalPmSettleNode){
		try{


			bizNormalPmSettleNodeService.delete(bizNormalPmSettleNode);
			return "1";
		}catch (Exception e){

			e.printStackTrace();
			return "2";
		}


	}


	@RequiresPermissions("bizsettlenodeconfig:bizNormalPmSettleNode:view")
	@RequestMapping(value = "checkStoreIsExist")
	public @ResponseBody String checkStoreIsExist(Integer storeId,Integer projectMode){
		try{
		Integer count =	bizNormalPmSettleNodeService.checkCheckNodeIsExistPulas(storeId,projectMode);

		if(0==count){
			return "1";

		}else{

			return "2";
		}

		}catch (Exception e){

			e.printStackTrace();

			return "3";
		}


	}
}