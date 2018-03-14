package cn.damei.web.modules;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.service.modules.DataAuthorityService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DataAuthorityConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderDisclose;
import cn.damei.service.modules.BizOrderDiscloseService;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/bizorderdisclose/bizOrderDisclose")
public class BizOrderDiscloseController extends BaseController {

	@Autowired
	private BizOrderDiscloseService bizOrderDiscloseService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	@Autowired
	private DataAuthorityService dataAuthorityService;
	@ModelAttribute
	public BizOrderDisclose get(@RequestParam(required = false) Integer id) {
		BizOrderDisclose entity = null;
		if (StringUtils.isNotBlank(id + "")) {

			entity = bizOrderDiscloseService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderDisclose();
		}
		return entity;
	}

	@RequiresPermissions("bizorderdisclose:bizOrderDisclose:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(BizOrderDisclose bizOrderDisclose, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();



		if(StringUtils.isBlank(bizOrderDisclose.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderDisclose.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderDisclose.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/bizOrderDisclose/discloseList";
	}
	
	
	@RequiresPermissions("bizorderdisclose:bizOrderDisclose:view")
	@RequestMapping(value = { "list2", "" })
	public String list2(BizOrderDisclose bizOrderDisclose, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderDisclose.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		

		
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_DD);
		bizOrderDisclose.setPhones(orderdPhones);
		String userId = user.getId();
		bizOrderDisclose.setUserId(userId);
		
		
		

		if(StringUtils.isBlank(bizOrderDisclose.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderDisclose.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderDisclose.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		Page<BizOrderDisclose> page = bizOrderDiscloseService.findPage(new Page<BizOrderDisclose>(request, response),
				bizOrderDisclose);
		model.addAttribute("bizOrderDisclose",bizOrderDisclose);
		
		model.addAttribute("page", page);
		return "modules/bizOrderDisclose/discloseList";
	}
	
	
	
	
	@RequiresPermissions("bizorderdisclose:bizOrderDisclose:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderDisclose bizOrderDisclose, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		
		
		User user = UserUtils.getUser();


		

		
		

		if(StringUtils.isBlank(bizOrderDisclose.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderDisclose.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderDisclose.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		Page<BizOrderDisclose> page = bizOrderDiscloseService.findPage(new Page<BizOrderDisclose>(request, response),
				bizOrderDisclose);
		model.addAttribute("bizOrderDisclose",bizOrderDisclose);
		
		model.addAttribute("page", page);
		return "modules/bizOrderDisclose/discloseList";
	}

	@RequiresPermissions("bizorderdisclose:bizOrderDisclose:view")
	@RequestMapping(value = { "showOrderDisclosePhoto", "" })
	public String showOrderDisclosePhoto(BizOrderDisclose bizOrderDisclose, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Integer orderDiscloseId = Integer.valueOf(request.getParameter("orderDiscloseId"));
		logger.info("现场交底主键编号：" + orderDiscloseId);
		
		
		
		return null;
	}
	@RequestMapping(value="exportDiscloseExcel")
	public void exportInspectorFineMoneyDetailToExcel(HttpServletResponse response,BizOrderDisclose bizOrderDisclose, HttpServletRequest request){

		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());



		HSSFWorkbook excel = bizOrderDiscloseService.exportExcel(bizOrderDisclose);


		ServletOutputStream out = null;

		try {

			response.setContentType("application/binary;charset=UTF-8");
			String excelHead= new String(("设计交底信息-"+now).getBytes(),"ISO8859-1");

			response.setHeader("Content-disposition", "attachment; filename="+excelHead+".xls");
			out=response.getOutputStream();
			excel.write(out);
		} catch (Exception e) {
			e.printStackTrace();
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
