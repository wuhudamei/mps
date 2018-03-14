/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizOrderInstallItemProblemVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Manager.BusinessPicture;
import cn.damei.service.mobile.Manager.BusinessPictureService;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.service.modules.InstallConstructBillProblemService;
import cn.damei.service.modules.BizSupplierInstallBillService;
import cn.damei.entity.modules.EnginInstallSupplier;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 安装工主材问题上报Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/installConstructBillProblem/installConstructBillProblem")
public class InstallConstructBillProblemController extends BaseController {

	@Autowired
	private InstallConstructBillProblemService installConstructBillProblemService;
	@Autowired
	private BizSupplierInstallBillService bizSupplierInstallBillService;
	@Autowired
	private BusinessPictureService businessPictureService;
	
	
	@ModelAttribute
	public BizOrderInstallItemProblemVo getOrderInstallItemProblemVo(@RequestParam(required=false) Integer id) {
		BizOrderInstallItemProblemVo entity = null;
		if (id != null){
			entity = installConstructBillProblemService.get(id);
		}
		if (entity == null){
			entity = new BizOrderInstallItemProblemVo();
		}
		return entity;
	}
	
	@RequiresPermissions("installConstructBillProblem:installConstructBillProblem:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizOrderInstallItemProblemVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		
		//过滤工程模式
		if(StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}
		//状态
		bizOrderInstallItemProblemVo.setStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_80);
		//业务类型
		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_5);
		
		//供应商列表
		List<EnginInstallSupplier> supplierList = bizSupplierInstallBillService.findSupplierList(user.getEmployeePhone());
		
		model.addAttribute("supplierList", supplierList);
		
		return "modules/bizorderinstallitemproblem/installConstructBillProblem/installConstructBillProblemList";
	}
	
	
	@RequiresPermissions("installConstructBillProblem:installConstructBillProblem:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizOrderInstallItemProblemVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		
		//过滤工程模式
		if(StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}
		
		//状态
		bizOrderInstallItemProblemVo.setStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_80);
		//业务类型
		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_5);
		
		//供应商列表
		List<EnginInstallSupplier> supplierList = bizSupplierInstallBillService.findSupplierList(user.getEmployeePhone());
				
		Page<BizOrderInstallItemProblemVo> page = installConstructBillProblemService.findPage(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo); 
		model.addAttribute("page", page);
		model.addAttribute("supplierList", supplierList);
				
		return "modules/bizorderinstallitemproblem/installConstructBillProblem/installConstructBillProblemList";
	}

	
	/**
	 * 问题上报记录--详情--图片
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="picture")
	public String picture(String problemId, HttpServletRequest request, Model model) throws IOException{
		
		List<BusinessPicture> pictures = null;
		
		if(StringUtils.isNotBlank(problemId)){
			pictures = businessPictureService.queryPictureByBussinessIdAndType(Integer.valueOf(problemId),PictureTypeContantUtil.PICTURE_TYPE_2072);
		}
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		return "modules/bizorderinstallitemproblem/photo";
	}
	
	/**
	 * 导出excel--安装工主材问题上报查询
	 * @param selectOrder
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="exportExcel")
	public void exportExcel(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletResponse response) throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = installConstructBillProblemService.exportExcel(bizOrderInstallItemProblemVo);
		ServletOutputStream out= null;//创建一个输出流对象
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr =new String(("安装工主材问题上报信息-"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(out !=  null){
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}