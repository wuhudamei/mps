/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import cn.damei.entity.modules.BizAuxiliaryMaterials;
import cn.damei.entity.modules.BizAuxiliaryMaterialsService;
import cn.damei.service.modules.SysSequenceService;
import cn.damei.common.utils.UserUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 辅料管理Controller
 * @author chy
 * @version 2016-09-09
 */
@Controller
@RequestMapping(value = "${adminPath}/auxiliarymaterials/bizAuxiliaryMaterials")
public class BizAuxiliaryMaterialsController extends BaseController {

	@Autowired
	private BizAuxiliaryMaterialsService bizAuxiliaryMaterialsService;
	@Autowired
	private SysSequenceService sysSequenceService;
	
	@ModelAttribute
	public BizAuxiliaryMaterials get(@RequestParam(required=false) String id) {
		BizAuxiliaryMaterials entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizAuxiliaryMaterialsService.get(id);
		}
		if (entity == null){
			entity = new BizAuxiliaryMaterials();
		}
		return entity;
	}
	
	@RequiresPermissions("auxiliarymaterials:bizAuxiliaryMaterials:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizAuxiliaryMaterials bizAuxiliaryMaterials, HttpServletRequest request, HttpServletResponse response, Model model) {
		//过滤门店
		if(StringUtils.isBlank(bizAuxiliaryMaterials.getStoreId())){
			bizAuxiliaryMaterials.setStoreId(UserUtils.getUser().getStoreId());
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		
		Page<BizAuxiliaryMaterials> page = bizAuxiliaryMaterialsService.findPage(new Page<BizAuxiliaryMaterials>(request, response), bizAuxiliaryMaterials); 
		model.addAttribute("page", page);
		return "modules/auxiliarymaterials/bizAuxiliaryMaterialsList";
	}
	
	/**
	 * 网真 辅料
	 * @param bizAuxiliaryMaterials
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("auxiliarymaterials:bizWZAuxiliaryMaterials:view")
	@RequestMapping(value = "WZlist")
	public String WZList(BizAuxiliaryMaterials bizAuxiliaryMaterials, HttpServletRequest request, HttpServletResponse response, Model model) {
		//过滤门店
		if(StringUtils.isBlank(bizAuxiliaryMaterials.getStoreId())){
			bizAuxiliaryMaterials.setStoreId(UserUtils.getUser().getStoreId());
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		
		Page<BizAuxiliaryMaterials> page = bizAuxiliaryMaterialsService.findPage(new Page<BizAuxiliaryMaterials>(request, response), bizAuxiliaryMaterials); 
		model.addAttribute("page", page);
		return "modules/auxiliarymaterials/bizWZAuxiliaryMaterialsList";
	}
	
	@RequiresPermissions("auxiliarymaterials:bizWZAuxiliaryMaterials:edit")
	@RequestMapping(value = "wZform")
	public String wZform(BizAuxiliaryMaterials bizAuxiliaryMaterials, Model model) {
		if(StringUtils.isBlank(bizAuxiliaryMaterials.getStoreId())){
			bizAuxiliaryMaterials.setStoreId(UserUtils.getUser().getStoreId());
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		
		model.addAttribute("bizAuxiliaryMaterials", bizAuxiliaryMaterials);
		return "modules/auxiliarymaterials/bizAuxiliaryMaterialsForm";
	}
	
	/**
	 * 供应商 辅料
	 * @param bizAuxiliaryMaterials
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("auxiliarymaterials:bizSupplierListAuxiliaryMaterials:view")
	@RequestMapping(value = "supplierList")
	public String supplierList(BizAuxiliaryMaterials bizAuxiliaryMaterials, HttpServletRequest request, HttpServletResponse response, Model model) {
		//过滤门店
		if(StringUtils.isBlank(bizAuxiliaryMaterials.getStoreId())){
			bizAuxiliaryMaterials.setStoreId(UserUtils.getUser().getStoreId());
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		Page<BizAuxiliaryMaterials> page = bizAuxiliaryMaterialsService.findPage(new Page<BizAuxiliaryMaterials>(request, response), bizAuxiliaryMaterials); 
		model.addAttribute("page", page);
		return "modules/auxiliarymaterials/bizSupplierAuxiliaryMaterialsList";
	}
	
	@RequiresPermissions("auxiliarymaterials:bizSupplierListAuxiliaryMaterials:edit")
	@RequestMapping(value = "supplierForm")
	public String supplierForm(BizAuxiliaryMaterials bizAuxiliaryMaterials, Model model) {
		if(StringUtils.isBlank(bizAuxiliaryMaterials.getStoreId())){
			bizAuxiliaryMaterials.setStoreId(UserUtils.getUser().getStoreId());
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		
		model.addAttribute("bizAuxiliaryMaterials", bizAuxiliaryMaterials);
		return "modules/auxiliarymaterials/bizAuxiliaryMaterialsForm";
	}
	
 
	@RequiresPermissions("auxiliarymaterials:bizAuxiliaryMaterials:view")
	@RequestMapping(value = "form")
	public String form(BizAuxiliaryMaterials bizAuxiliaryMaterials, Model model) {
		if(StringUtils.isBlank(bizAuxiliaryMaterials.getStoreId())){
			bizAuxiliaryMaterials.setStoreId(UserUtils.getUser().getStoreId());
		}
		if(StringUtils.isBlank(UserUtils.getUser().getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		
		model.addAttribute("bizAuxiliaryMaterials", bizAuxiliaryMaterials);
		return "modules/auxiliarymaterials/bizAuxiliaryMaterialsForm";
	}

	@RequiresPermissions("auxiliarymaterials:bizAuxiliaryMaterials:edit")
	@RequestMapping(value = "save")
	public String save(BizAuxiliaryMaterials bizAuxiliaryMaterials, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizAuxiliaryMaterials)){
			return form(bizAuxiliaryMaterials, model);
		}
		if (bizAuxiliaryMaterials.getId() == null || "".equals(bizAuxiliaryMaterials.getId())) {
			bizAuxiliaryMaterials.setAuxiliaryMaterialsNo(sysSequenceService.getSequence("FL"));
        }
		
		bizAuxiliaryMaterialsService.save(bizAuxiliaryMaterials);
		addMessage(redirectAttributes, "保存辅料管理成功");
		return "redirect:"+Global.getAdminPath()+"/auxiliarymaterials/bizAuxiliaryMaterials/?repage";
	}
	
	@RequiresPermissions("auxiliarymaterials:bizAuxiliaryMaterials:edit")
	@RequestMapping(value = "delete")
	public String delete(BizAuxiliaryMaterials bizAuxiliaryMaterials, RedirectAttributes redirectAttributes) {
		bizAuxiliaryMaterialsService.delete(bizAuxiliaryMaterials);
		addMessage(redirectAttributes, "删除辅料管理成功");
		return "redirect:"+Global.getAdminPath()+"/auxiliarymaterials/bizAuxiliaryMaterials/?repage";
	}
	@RequiresPermissions("auxiliarymaterials:bizAuxiliaryMaterials:edit")
	@RequestMapping(value = "enable")
	public String enable(BizAuxiliaryMaterials bizAuxiliaryMaterials, RedirectAttributes redirectAttributes) {
		int status = 1 ^ Integer.parseInt(bizAuxiliaryMaterials.getStatus());
		bizAuxiliaryMaterials.setStatus(status+"");
		bizAuxiliaryMaterialsService.update(bizAuxiliaryMaterials);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/auxiliarymaterials/bizAuxiliaryMaterials/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "getSKUNo")
	public List<BizAuxiliaryMaterials> getSKUNo(String storeId) {
		return bizAuxiliaryMaterialsService.getSKUNo(storeId);
	}
	
	
	/**
	 * 导出excel--辅料
	 * @param bizPmSettleBill
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="exportExcel")
	public void exportExcel(BizAuxiliaryMaterials bizAuxiliaryMaterials, HttpServletResponse response) throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = bizAuxiliaryMaterialsService.exportExcel(bizAuxiliaryMaterials);
		ServletOutputStream out= null;//创建一个输出流对象
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr =new String(("辅料商品清单-"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(out != null){
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}