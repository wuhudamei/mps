/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.CheckSelect;
import cn.damei.service.modules.CheckSelectService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 约检明细查询Controller
 * @author wyb
 * @version 2016-10-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pqc/checkSelect/checkSelect")
public class CheckSelectController extends BaseController {

	@Autowired
	private CheckSelectService checkSelectService;
	
	@ModelAttribute
	public CheckSelect get(@RequestParam(required=false) String id) {
		CheckSelect entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = checkSelectService.get(id);
		}
		if (entity == null){
			entity = new CheckSelect();
		}
		return entity;
	}
	
	@RequiresPermissions("checkSelect:checkSelect:view")
	@RequestMapping(value = {"list", ""})
	public String list(CheckSelect checkSelect, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==checkSelect.getStoreId()){
			if(null!=user.getStoreId()){
				checkSelect.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(checkSelect.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				checkSelect.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				checkSelect.setProjectMode(user.getProjectMode());
			}
		}
		
		return "modules/PQC/checkSelect/checkSelectList";
	}
	@RequiresPermissions("checkSelect:checkSelect:view")
	@RequestMapping(value = {"checkSelectList", ""})
	public String checkSelectList(CheckSelect checkSelect, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==checkSelect.getStoreId()){
			if(null!=user.getStoreId()){
				checkSelect.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(checkSelect.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				checkSelect.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				checkSelect.setProjectMode(user.getProjectMode());
			}
		}
		checkSelect.setIsRecheck(ConstantUtils.IS_RECHECK_0);
		checkSelect.setQcBillType(ConstantUtils.QC_BILL_TYPE_1);
		checkSelect.setSignType(ConstantUtils.SIGN_TYPE_INSPECTOR_CHECK_301);
		Page<CheckSelect> page = checkSelectService.findPage(new Page<CheckSelect>(request, response), checkSelect); 
		model.addAttribute("page", page);
		return "modules/PQC/checkSelect/checkSelectList";
	}
	
	/**
	 * 导出excel--项目约检信息
	 * @param checkSelect
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="exportExcel")
	public void exportExcel(CheckSelect checkSelect, HttpServletResponse response) throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = checkSelectService.exportExcel(checkSelect);
		ServletOutputStream out= null;//创建一个输出流对象
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr =new String(("项目约检信息-"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(out!=null){
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}