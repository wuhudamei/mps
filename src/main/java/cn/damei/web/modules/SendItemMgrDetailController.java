package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.SendItemMgrDetail;
import cn.damei.service.modules.SendItemMgrDetailService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/sendItemMgrDetail/sendItemMgrDetail")
public class SendItemMgrDetailController extends BaseController {

	@Autowired
	private SendItemMgrDetailService itemMgrDetailService;
	
	@RequiresPermissions("sendItemMgrDetail:sendItemMgrDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(SendItemMgrDetail sendItemMgrDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();


		if(StringUtils.isBlank(sendItemMgrDetail.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				sendItemMgrDetail.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				sendItemMgrDetail.setProjectMode(user.getProjectMode());
			}
		}
		return "modules/senditemmgrdetail/sendItemMgrDetail";
	}
	
	@RequiresPermissions("sendItemMgrDetail:sendItemMgrDetail:view")
	@RequestMapping(value = { "senditemMgrDetailList", "" })
	public String senditemMgrDetailList(SendItemMgrDetail sendItemMgrDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		

		if(StringUtils.isBlank(sendItemMgrDetail.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				sendItemMgrDetail.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				sendItemMgrDetail.setProjectMode(user.getProjectMode());
			}
		}
		
		Page<SendItemMgrDetail> page = itemMgrDetailService.findPage(new Page<SendItemMgrDetail>(request, response), sendItemMgrDetail);
		model.addAttribute("page", page);
		return "modules/senditemmgrdetail/sendItemMgrDetail";
	}
	
	@RequestMapping(value="exportExcel")
	public void exportExcel(SendItemMgrDetail sendItemMgrDetail, HttpServletResponse response) throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String fileName = "项目经理星级接单明细表-" + sf.format(sendItemMgrDetail.getBeginCreateDate()) + "至" + sf.format(sendItemMgrDetail.getEndCreateDate());
		HSSFWorkbook excel = itemMgrDetailService.exportExcel(sendItemMgrDetail);
		
		ServletOutputStream out= null;
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr =new String(fileName.getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(out!=null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	@RequestMapping(value="exportDetailExcel")
	public void exportDetailExcel(SendItemMgrDetail sendItemMgrDetail, HttpServletResponse response) throws Exception{
			itemMgrDetailService.exportDetailExcel(sendItemMgrDetail,response);
		}
	}
