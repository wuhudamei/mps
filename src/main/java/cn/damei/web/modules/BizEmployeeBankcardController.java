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
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployeeBankcard;
import cn.damei.entity.modules.BizEmployeeBankcard2;
import cn.damei.entity.modules.BizEmployeeBankcardRelation;
import cn.damei.service.modules.BizEmployeeBankcardService;
import cn.damei.service.modules.BizEmployeeBankcardService2;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.common.utils.UserUtils;

/**
 * 工人银行卡Controller
 * @author haven
 * @version 2016-09-03
 */
@Controller
@RequestMapping(value = "${adminPath}/empbankcard/bizEmployeeBankcard")
public class BizEmployeeBankcardController extends BaseController {

	@Autowired
	private BizEmployeeBankcardService bizEmployeeBankcardService;
	@Autowired
	private BizEmployeeBankcardService2 bizEmployeeBankcardService2;
	@Autowired
	private BizEmployeeService bizEmployeeService;
	
	@ModelAttribute
	public BizEmployeeBankcard get(@RequestParam(required=false) String id) {
		BizEmployeeBankcard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizEmployeeBankcardService.get(id);
			BizEmployee employee=bizEmployeeService.get(entity.getEmpId());
			if(employee!=null)
			{
				entity.setEmpRealName(employee.getRealname()+""+employee.getNo());
			}
		}
		if (entity == null){
			entity = new BizEmployeeBankcard();
		}
		return entity;
	}
	
	@RequiresPermissions("empbankcard:bizEmployeeBankcard:view")
	@RequestMapping(value = "listPage")
	public String pageList(BizEmployeeBankcard bizEmployeeBankcard, HttpServletRequest request, HttpServletResponse response, Model model){
		if(UserUtils.getUser().getStoreId()!=null)
		{
			//当前登录用户门店
			bizEmployeeBankcard.setStoreId(UserUtils.getUser().getStoreId());
		}
		return "modules/empbankcard/bizEmployeeBankcardList";
	}
	@RequiresPermissions("empbankcard:bizEmployeeBankcard:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizEmployeeBankcard bizEmployeeBankcard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizEmployeeBankcard> page = bizEmployeeBankcardService.findPage(new Page<BizEmployeeBankcard>(request, response), bizEmployeeBankcard);
		model.addAttribute("page", page);
		return "modules/empbankcard/bizEmployeeBankcardList";
	}

	@RequiresPermissions("empbankcard:bizEmployeeBankcard:view")
	@RequestMapping(value = "form")
	public String form(BizEmployeeBankcard bizEmployeeBankcard, Model model) {
		if(StringUtils.isBlank(bizEmployeeBankcard.getStoreId())){
			bizEmployeeBankcard.setStoreId(UserUtils.getUser().getStoreId());
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		model.addAttribute("bizEmployeeBankcard", bizEmployeeBankcard);
		return "modules/empbankcard/bizEmployeeBankcardForm";
	}

	@RequiresPermissions("empbankcard:bizEmployeeBankcard:edit")
	@RequestMapping(value = "save")
	public String save(BizEmployeeBankcard2 bizEmployeeBankcard, Model model, RedirectAttributes redirectAttributes) {
		/*if (!beanValidator(model, bizEmployeeBankcard)){
			return form(bizEmployeeBankcard, model);
		}*/
		boolean flag = false;
		List<BizEmployeeBankcard> list = bizEmployeeBankcardService.findAll();
		for (BizEmployeeBankcard bizEmployeeBankcard3 : list) {
			if(bizEmployeeBankcard.getEmpId().toString().equals(bizEmployeeBankcard3.getEmpId()) ){
				flag = true;
				break;
			}
		}
		if(flag){
			bizEmployeeBankcard.preUpdate();
			bizEmployeeBankcardService2.update(bizEmployeeBankcard);
		}else{
			
			bizEmployeeBankcard.preInsert();
			bizEmployeeBankcardService2.insert(bizEmployeeBankcard);
			BizEmployeeBankcard2 bizEmployeeBankcard2 = bizEmployeeBankcardService.getByBankcard(bizEmployeeBankcard.getBankCardNo());
			BizEmployee2 employee = bizEmployeeService2.findEmployeeById(bizEmployeeBankcard2.getEmpId());
			
			//BizEmployeeBankcardRelatedIdcard bankcardRelatedIdcard = new BizEmployeeBankcardRelatedIdcard();
			/*bankcardRelatedIdcard.setEmployeeBankcardId(bizEmployeeBankcard2.getId());
			bankcardRelation.setEmployeeBankcardId(bizEmployeeBankcard2.getId());
			bankcardRelation.setEmployeeName(employee.getRealname());
			bankcardRelation.setIdCardNo(employee.getIdcardno());*/
			
			bizEmployeeBankcardService.addRelation(bizEmployeeBankcard2.getId(), employee.getRealname(), employee.getIdcardno());
			addMessage(redirectAttributes, "保存工人银行卡成功");
		}	
		return "redirect:"+Global.getAdminPath()+"/empbankcard/bizEmployeeBankcard/?repage";
	}
	
	@RequiresPermissions("empbankcard:bizEmployeeBankcard:edit")
	@RequestMapping(value = "delete")
	public String delete(BizEmployeeBankcard bizEmployeeBankcard, RedirectAttributes redirectAttributes) {
		bizEmployeeBankcardService.delete(bizEmployeeBankcard);
		addMessage(redirectAttributes, "删除工人银行卡成功");
		return "redirect:"+Global.getAdminPath()+"/empbankcard/bizEmployeeBankcard/?repage";
	}
	
	/**
	 * @param id 银行卡id
	 * @return
	 */
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@RequestMapping(value="relation")
	public String relation(Integer id,Model model){
		
		List<BizEmployeeBankcardRelation> list = bizEmployeeBankcardService.queryRelationMessage(id);
		for (BizEmployeeBankcardRelation bizEmployeeBankcardRelation : list) {
			BizEmployee2 employee= bizEmployeeService2.findEmployeeById(bizEmployeeBankcardRelation.getEmployeeId());
			bizEmployeeBankcardRelation.setEmployeeName(employee.getRealname());
		}
		model.addAttribute("list", list);
		model.addAttribute("id",id);
		return "modules/empbankcard/relationList";
	}
	
	
	@RequestMapping(value = "deleteRelation")
	public String deleteRelation(Integer relationId,Integer employeeBankcardId) {
		bizEmployeeBankcardService.deleteRelation(relationId);
	
		return "redirect:"+Global.getAdminPath()+"/empbankcard/bizEmployeeBankcard/relation?id="+employeeBankcardId;
	}
	
	@RequestMapping(value="addRelation")
	public String addRelation(Integer employeeBankcardId,String name,String card){
		bizEmployeeBankcardService.addRelation(employeeBankcardId,name,card.trim());
		
		return "redirect:"+Global.getAdminPath()+"/empbankcard/bizEmployeeBankcard/relation?id="+employeeBankcardId;
	}
	@RequestMapping(value="compareIdCard")
	public @ResponseBody String compareIdCard(String identifiedCardNumber){
		if(null!=identifiedCardNumber){
			
			Map<String, Integer> checkIdCard = bizEmployeeBankcardService.checkIdCard(identifiedCardNumber.trim());
			if(checkIdCard.isEmpty()){
				//如果没查到, 就是不重复 可以提交
				return "1";
				
				
			}else{
				//不是空的 就是重复  不可以提交
				return "2";
			}
			
			
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value="checkBankCardName")
	public Integer checkBankCard(String bankCardNo,String id){
		
		return bizEmployeeBankcardService.checkBankCard(bankCardNo,id);
	}
}