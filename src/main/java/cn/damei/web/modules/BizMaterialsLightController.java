/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizMaterialsStandardNumberSquare;
import cn.damei.service.modules.BizMaterialsStandardNumberSquareService;
import cn.damei.entity.modules.BizMaterialsStandard;
import cn.damei.service.modules.BizMaterialsStandardService;
import cn.damei.common.utils.DictUtils;
import cn.damei.common.utils.UserUtils;

/**
 * 筒灯灯带Controller
 * @author lft
 * @version 2017-5-17
 */
@Controller
@RequestMapping(value = "${adminPath}/standradmaterials/bizMaterialsLight")
public class BizMaterialsLightController extends BaseController {

	@Autowired
	private BizMaterialsStandardService bizMaterialsStandardService;
	@Autowired
	private BizMaterialsStandardNumberSquareService bizMaterialsStandardNumberSquareService;
	@ModelAttribute
	public BizMaterialsStandard get(@RequestParam(required=false) Integer id) {
		BizMaterialsStandard entity = null;
		if (id != null){
			entity = bizMaterialsStandardService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialsStandard();
		}
		return entity;
	}
	@RequiresPermissions("standradmaterials:bizMaterialsLight:view")
	@RequestMapping(value = "listPage")
	public String listPage(BizMaterialsStandard bizMaterialsStandard, HttpServletRequest request, HttpServletResponse response, Model model){
		if(UserUtils.getUser().getStoreId()!=null){
			//当前登录用户门店
			bizMaterialsStandard.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}else{
			//门店是总部的查询所有部门信息
			if(bizMaterialsStandard.getStoreId()!=null && bizMaterialsStandard.getStoreId().equals(1)){
				//总部
				bizMaterialsStandard.setStoreId(null);
			}
		}
		return "modules/managerSettlement/standradmaterials/bizMaterialsLightList";
	}
	@RequiresPermissions("standradmaterials:bizMaterialsLight:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialsStandard bizMaterialsStandard, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getStoreId()!=null){
			//当前登录用户门店
			bizMaterialsStandard.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}else{
			//门店是总部的查询所有部门信息
			if(bizMaterialsStandard.getStoreId()!=null && bizMaterialsStandard.getStoreId().equals(1)){
				//总部
				bizMaterialsStandard.setStoreId(null);
			}
		}
		//設置筒灯灯带
		bizMaterialsStandard.setMaterialsLargeType("2");
		Page<BizMaterialsStandard> page = bizMaterialsStandardService.findPage(new Page<BizMaterialsStandard>(request, response), bizMaterialsStandard); 
		model.addAttribute("page", page);
		return "modules/managerSettlement/standradmaterials/bizMaterialsLightList";
	}
	
	@RequiresPermissions("standradmaterials:bizMaterialsLight:edit")
	@RequestMapping(value = "form")
	public String form(BizMaterialsStandard bizMaterialsStandard, Model model) {
		if(null == bizMaterialsStandard.getIsEnabled()){
			bizMaterialsStandard.setIsEnabled("1");
		}
		model.addAttribute("bizMaterialsStandard", bizMaterialsStandard);
		return "modules/managerSettlement/standradmaterials/bizMaterialsLightForm";
	}
	@Transactional
	@RequiresPermissions("standradmaterials:bizMaterialsLight:edit")
	@RequestMapping(value = "save")
	public String save(BizMaterialsStandard bizMaterialsStandard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMaterialsStandard)){
			return form(bizMaterialsStandard, model);
		}
		bizMaterialsStandard.setMaterialsLargeType("2");
		bizMaterialsStandardService.save(bizMaterialsStandard);
		//拿出限制 有限制为1  不限制0
		String isLimitMaxNumber = bizMaterialsStandard.getIsLimitMaxNumber();
		//有限制 有id / 没id
		if("1".equals(isLimitMaxNumber)){
			 setAreaObject(bizMaterialsStandard);
		}else{
			//无限制 传过来 id
			String[] getsId = bizMaterialsStandard.getsId();
				if(getsId!=null){
				for (String str : getsId) {
					BizMaterialsStandardNumberSquare bizMaterialsStandardNumberSquare = new BizMaterialsStandardNumberSquare();
					bizMaterialsStandardNumberSquare.setId(Integer.valueOf(str));
					bizMaterialsStandardNumberSquareService.delete(bizMaterialsStandardNumberSquare);
				}
			}
		}
		addMessage(redirectAttributes, "保存筒灯灯带成功");
		return "redirect:"+Global.getAdminPath()+"/standradmaterials/bizMaterialsLight/list";
	}
	
	@RequiresPermissions("standradmaterials:bizMaterialsLight:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMaterialsStandard bizMaterialsStandard, RedirectAttributes redirectAttributes) {
		bizMaterialsStandardService.delete(bizMaterialsStandard);
		addMessage(redirectAttributes, "删除筒灯灯带成功");
		return "redirect:"+Global.getAdminPath()+"/standradmaterials/bizMaterialsLight/list";
	}
	
	@RequiresPermissions("standradmaterials:bizMaterialsLight:edit")
	@RequestMapping(value = "enable")
	public String enable(BizMaterialsStandard bizMaterialsStandard , RedirectAttributes redirectAttributes) {
		Integer isEnable = 1 ^ Integer.parseInt(bizMaterialsStandard.getIsEnabled());
		bizMaterialsStandard.setIsEnabled(isEnable.toString());
		bizMaterialsStandardService.update(bizMaterialsStandard);
		addMessage(redirectAttributes, DictUtils.getDictLabel(isEnable+"", "biz_enable_status", "")+"筒灯灯带成功");
		return "redirect:"+Global.getAdminPath()+"/standradmaterials/bizMaterialsLight/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "getBizMaterialsStandard")
	public String getBizMaterialsStandard(String materialId){
		BizMaterialsStandard bizMaterialsStandard = bizMaterialsStandardService.get(Integer.parseInt(materialId));
		String a =  JsonMapper.getInstance().toJson(bizMaterialsStandard);
		return a;
	}
	//組裝 保存
	private void setAreaObject(BizMaterialsStandard bizMaterialsStandard){
		List<BizMaterialsStandardNumberSquare> list=new ArrayList<BizMaterialsStandardNumberSquare>();
		String[] numberRuleCode = bizMaterialsStandard.getNumberRuleCode();
		String[] getsId = bizMaterialsStandard.getsId();
		//筒燈id
		String id = String.valueOf(bizMaterialsStandard.getId());
		//id 数组长度
		int length =0;
		if(getsId==null){
			
		}else{
			length = getsId.length;
		}
		//获取 该筒灯灯带 的面积的记录的 id
		List<String> idByMaterialsId = bizMaterialsStandardNumberSquareService.getIdByMaterialsId(id);
		List<String> deleteId=new ArrayList<String>();
		if(length==0){
			deleteId=idByMaterialsId;
		}
		//如果 id 的个数不对则涉及到 删除未穿过来id
		if(idByMaterialsId!=null && idByMaterialsId.size()>length &&length!=0){
			//拿出未传过来的 id
			ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(getsId));
			Collection exists=new ArrayList<String>(idByMaterialsId);
			exists.removeAll(arrayList);
			deleteId = new ArrayList<String>(exists);
		}
		for (String str : deleteId) {
			BizMaterialsStandardNumberSquare bizMaterialsStandardNumberSquareDelete =new BizMaterialsStandardNumberSquare();
			bizMaterialsStandardNumberSquareDelete.setId(Integer.valueOf(str));
			bizMaterialsStandardNumberSquareService.delete(bizMaterialsStandardNumberSquareDelete);
		}
		if(numberRuleCode.length>0){
		HashMap<String, String> hashMap = new HashMap<String,String>();	
		hashMap.put("1", "71*8/100余数进1(筒灯)");
		hashMap.put("2", "合同面积*8/100余数进1(筒灯)");
		hashMap.put("3", "71*16/100余数进1(灯带)");
		hashMap.put("4", "合同面积*16/100余数进1(灯带)");
		String[] limtArea = bizMaterialsStandard.getLimtArea();
		//有id 修改 無 id 添加	
		for(int i=0;numberRuleCode.length>i;i++){
				BizMaterialsStandardNumberSquare bizMaterialsStandardNumberSquare=new BizMaterialsStandardNumberSquare();
				if(length!=0 && length>i){
					bizMaterialsStandardNumberSquare.setId(Integer.valueOf(getsId[i]));
				}
				bizMaterialsStandardNumberSquare.setMaterialsStandardId(id);
				bizMaterialsStandardNumberSquare.setNumberRuleCode(numberRuleCode[i]);
				bizMaterialsStandardNumberSquare.setNumberRuleDescribe(hashMap.get(numberRuleCode[i]));
				if(limtArea[i].equals("(0,71)㎡")){
					bizMaterialsStandardNumberSquare.setSquareMin(0);
					bizMaterialsStandardNumberSquare.setSquareMax(71);
				}else{
					bizMaterialsStandardNumberSquare.setSquareMin(71);
					bizMaterialsStandardNumberSquare.setSquareMax(999);
				}
			list.add(bizMaterialsStandardNumberSquare);	
			}
		for (BizMaterialsStandardNumberSquare bizMaterialsStandardNumberSquare : list) {
			bizMaterialsStandardNumberSquareService.save(bizMaterialsStandardNumberSquare);
		}
		
		
		}
	}
	
}