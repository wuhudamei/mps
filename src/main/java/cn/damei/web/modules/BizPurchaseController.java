/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Manager.BusinessPicture;
import cn.damei.service.mobile.Manager.BusinessPictureService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.ExportProjectExcel;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.Auxiliary;
import cn.damei.entity.modules.BizPurchaseVo;
import cn.damei.service.modules.AuxiliaryService;
import cn.damei.service.modules.BizPurchaseVoService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 辅材采购单Controller
 * @author 汪文文
 * @version 2016-09-28
 */
@Controller
@RequestMapping(value = "${adminPath}/purchase/bizPurchase")
public class BizPurchaseController extends BaseController {

	@Autowired
	private BizPurchaseVoService bizPurchaseVoService;
	@Autowired
	private AuxiliaryService auxiliarySerivice;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BusinessPictureService businessPictureService;
	
	@ModelAttribute
	public BizPurchaseVo get(@RequestParam(required=false) Integer id) {
		BizPurchaseVo entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizPurchaseVoService.get(id);
		}
		if (entity == null){
			entity = new BizPurchaseVo();
		}
		return entity;
	}
	
	//采购单辅材列表
	@RequiresPermissions("purchase:bizPurchase:view")
	@RequestMapping(value = "listPage")
	public String listPage(BizPurchaseVo bizPurchaseVo, HttpServletRequest request, HttpServletResponse response, Model model){
		if(UserUtils.getUser().getStoreId()!=null)
		{
			//当前登录用户门店
			bizPurchaseVo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		else{
			//门店是总部的查询所有部门信息
		if(bizPurchaseVo.getStoreId()!= null && bizPurchaseVo.getStoreId() ==1)
			{
				//总部
				bizPurchaseVo.setStoreId(null);
			}
		}
		User user = UserUtils.getUser();
		if(null !=user.getEmpId()){
			BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
			if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
				bizPurchaseVo.setProjectMode(null);//表示查询所有的采购单
			}else{
				bizPurchaseVo.setProjectMode(be.getProjectMode());
				model.addAttribute("projectModeEnable", true);
			}
		}else{
			bizPurchaseVo.setProjectMode(null);//表示查询所有的采购单
		}
		if(null == bizPurchaseVo.getStatus()|| "".equals(bizPurchaseVo.getStatus())){
			bizPurchaseVo.setStatus("10");
		}
		return "modules/purchase/bizPurchaseList";
		
	}
	@RequiresPermissions("purchase:bizPurchase:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPurchaseVo bizPurchaseVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getStoreId()!=null)
		{
			//当前登录用户门店
			bizPurchaseVo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		else{
			//门店是总部的查询所有部门信息
		if(bizPurchaseVo.getStoreId()!= null && bizPurchaseVo.getStoreId() ==1)
			{
				//总部
				bizPurchaseVo.setStoreId(null);
			}
		}
		if(null == bizPurchaseVo.getStatus()){
			bizPurchaseVo.setStatus("10");
		}
		bizPurchaseVo.setPurchaseType(ConstantUtils.AUXILIARY_NUMBER);
		if(bizPurchaseVo.getProjectMode() == null|| "".equals(bizPurchaseVo.getProjectMode())){
			User user = UserUtils.getUser();
			if(null !=user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode())|| null == be.getProjectMode()){
					bizPurchaseVo.setProjectMode(null);//表示查询所有的采购单
				}else{
					bizPurchaseVo.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			}else{
				bizPurchaseVo.setProjectMode(null);//表示查询所有的采购单
			}
		}
		Page<BizPurchaseVo> page = bizPurchaseVoService.findPage(new Page<BizPurchaseVo>(request, response), bizPurchaseVo);
		/*List<BizPurchaseVo> list = page.getList();
		for (BizPurchaseVo bizPurchaseVo2 : list) {
			
			if(bizPurchaseVo2.getApplyEmployee()!=null){
				BizEmployee2 employee = bizEmployeeService2.findEmployeeNameById(bizPurchaseVo2.getApplyEmployee());
				bizPurchaseVo2.setApplyName(employee.getRealname());
			}else{
				bizPurchaseVo2.setApplyName("");
			}
			//BizEmployee2 employee = bizEmployeeService2.findEmployeeNameById(bizPurchaseVo2.getApplyEmployee());
			//bizPurchaseVo2.setApplyName(employee.getRealname());
		}*/
		model.addAttribute("page", page);
		return "modules/purchase/bizPurchaseList";
	}
	
	//辅材明细
	@RequiresPermissions("purchase:bizPurchase:view")
	@RequestMapping(value = "details")
	public String details(Integer id , HttpServletRequest request, HttpServletResponse response, Model model){
		//id 采购单id
		BizPurchaseVo bizPurchaseVo =  bizPurchaseVoService.findById(id);
		BizEmployee2 employee = bizEmployeeService2.findEmployeeNameById(bizPurchaseVo.getApplyEmployee());
		
		bizPurchaseVo.setApplyName(employee.getRealname());
		Integer storeId = bizPurchaseVo.getStoreId();
		List<Auxiliary> auxiliarys = auxiliarySerivice.findListPriceByPurchaseId(id,storeId,bizPurchaseVo.getApplyTime());
		
		model.addAttribute("bizPurchaseVo", bizPurchaseVo);
		model.addAttribute("auxiliarys", auxiliarys);
		return "modules/purchase/auxiliaryDetails";
	}
	
/*	@RequestMapping(value = "seePhoto")
	public String seePhoto(Integer id ,Model model) throws IOException{
		List<BusinessPicture> pictures = businessPictureService.queryPicture(id, ConstantUtils.PICTURE_BUSINESS_TYPE_5);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		return "modules/purchase/photo";
	}*/
	/**
	 * 查看收货单
	 * @param
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "seePhoto")
	@ResponseBody
	public Map<Object, Object> seePhoto(Integer id, Model model, HttpServletRequest request) throws IOException{

		List<BusinessPicture> pictures = businessPictureService.queryPicture(id, ConstantUtils.PICTURE_BUSINESS_TYPE_5);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);

		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);
		return mapObject;
	}
	
	//辅料送货 --状态为40的采购单
	@RequiresPermissions("purchase:bizPurchase:view")
	@RequestMapping(value="sendAuxiliaryList")
	public String sendAuxiliaryList(BizPurchaseVo bizPurchaseVo, HttpServletRequest request, HttpServletResponse response, Model model){
		if(UserUtils.getUser().getStoreId()!=null)
		{
			//当前登录用户门店
			bizPurchaseVo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		else{
			//门店是总部的查询所有部门信息
		if(bizPurchaseVo.getStoreId()!= null && bizPurchaseVo.getStoreId() == 1)
			{
				//总部
				bizPurchaseVo.setStoreId(null);
			}
		}
		bizPurchaseVo.setStatus("40");//已转给供应商
		bizPurchaseVo.setPurchaseType(ConstantUtils.AUXILIARY_NUMBER);
		Page<BizPurchaseVo> page = bizPurchaseVoService.findPage(new Page<BizPurchaseVo>(request, response), bizPurchaseVo);
		model.addAttribute("page", page);
		return "modules/purchase/sendAuxiliaryList";
	}
	/**
	 * 导出辅材工地发货申请
	 * @param id
	 * @param response
	 * @return
	 */
	@RequiresPermissions("purchase:bizPurchase:view")
	@RequestMapping(value="exportExcel")
	public String exportExcel(Integer id,HttpServletResponse response){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		ServletOutputStream ouputStream= null;//创建一个输出流对象
		
		//id 采购单id
		BizPurchaseVo bizPurchaseVo =  bizPurchaseVoService.findById(id);
		BizEmployee2 employee = bizEmployeeService2.findEmployeeNameById(bizPurchaseVo.getApplyEmployee());
		bizPurchaseVo.setApplyName(employee.getRealname());
		Integer storeId = bizPurchaseVo.getStoreId();
		List<Auxiliary> auxiliarys = auxiliarySerivice.findListPriceByPurchaseId(id,storeId,bizPurchaseVo.getApplyTime());
		
		HSSFWorkbook projectExcel = ExportProjectExcel.exportPurchaseOrders(bizPurchaseVo,employee,auxiliarys);
		try {  
			response.setContentType("application/binary;charset=utf-8"); 
			String headerStr =new String((bizPurchaseVo.getPurchaseCode()+"--"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码  
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
			ouputStream = response.getOutputStream();    
			projectExcel.write(ouputStream);  
			ouputStream.flush();    
			ouputStream.close();
		} catch (IOException ex) {  
            ex.printStackTrace();  
        }  
		
		return "";
	}
}