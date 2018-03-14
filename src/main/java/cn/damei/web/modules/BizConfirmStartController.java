package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DataAuthorityConstantUtils;
import cn.damei.common.utils.ExportConfirmStart;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizConfirmStartOrder;
import cn.damei.entity.modules.BizConfirmStartService;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.service.modules.DataAuthorityService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizconfirmstart/bizConfirmStart")
public class BizConfirmStartController extends BaseController {



	@Autowired
	private BizConfirmStartService bizConfirmStartService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	@Autowired
	private DataAuthorityService dataAuthorityService;
	


	@RequiresPermissions("bizconfirmstart:bizConfirmStart:view")
	@RequestMapping(value = { "preList", "" })
	public String packageList(BizConfirmStartOrder bizConfirmStartOrder, Model model, 
			HttpServletRequest request) {
		User user = UserUtils.getUser();

		if (UserUtils.getUser().getStoreId() != null) {

			bizConfirmStartOrder.setStoreId(UserUtils.getUser().getStoreId());
		} else {

			if (bizConfirmStartOrder.getStoreId() != null && bizConfirmStartOrder.getStoreId().equals("1")) {

				bizConfirmStartOrder.setStoreId(null);
			}
		}

		if(StringUtils.isBlank(bizConfirmStartOrder.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizConfirmStartOrder.setProjectMode(be.getProjectMode());
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
						bizConfirmStartOrder.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}

		return "modules/bizconfirmstart/bizConfirmStartList";
	}


	@RequiresPermissions("bizconfirmstart:bizConfirmStart:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizConfirmStartOrder bizConfirmStartOrder, Model model, HttpServletResponse response, 
			HttpServletRequest request) {

		User user = UserUtils.getUser();
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_DD);
		bizConfirmStartOrder.setPhones(orderdPhones);
		String userId = user.getId();
		bizConfirmStartOrder.setUserId(userId);

		if(StringUtils.isBlank(bizConfirmStartOrder.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizConfirmStartOrder.setProjectMode(be.getProjectMode());
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
						bizConfirmStartOrder.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizConfirmStartOrder> page = bizConfirmStartService.findPage(new Page<BizConfirmStartOrder>(request, response),
				bizConfirmStartOrder);
		model.addAttribute("bizConfirmStartOrder", bizConfirmStartOrder);
		model.addAttribute("page", page);
		return "modules/bizconfirmstart/bizConfirmStartList";
	}
	@RequestMapping(value="exportExcel")
	@ResponseBody
	public String exportExcel(BizConfirmStartOrder bizConfirmStartOrder,HttpServletResponse response){

			User user = UserUtils.getUser();
			List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_DD);
			bizConfirmStartOrder.setPhones(orderdPhones);
			String userId = user.getId();
			bizConfirmStartOrder.setUserId(userId);
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
			List<BizConfirmStartOrder> list = bizConfirmStartService.findList(bizConfirmStartOrder);
			ExportConfirmStart exs = new ExportConfirmStart();
			HSSFWorkbook exportExcel = exs.exportExcel(list);
		
			ServletOutputStream out= null;
			try {
				response.setContentType("application/binary;charset=utf-8");
				String headerStr =new String(("确认开工信息-"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
				response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
				out = response.getOutputStream();
				exportExcel.write(out);
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
			
			return null;
		}
}
