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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.InspectPunishMoneyQueryEntity;
import cn.damei.service.modules.InspectPunishMoneyQueryService;
import cn.damei.common.utils.UserUtils;
/**
 * 
 * @author 梅浩
 * 质检罚款明细查询
 */
@Controller
@RequestMapping(value = "${adminPath}/inspectPunishMoneyQuery")
public class InspectPunishMoneyQueryController extends BaseController {

	@Autowired
	private InspectPunishMoneyQueryService service;

	@ModelAttribute
	public InspectPunishMoneyQueryEntity get(@RequestParam(required = false) String id) {
		InspectPunishMoneyQueryEntity entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = service.get(id);
		}
		if (entity == null) {
			entity = new InspectPunishMoneyQueryEntity();
		}
		return entity;
	}

	@RequiresPermissions("inspectPunishMoneyQuery:inspectPunishMoneyQuery:view")
	@RequestMapping(value = "list")
	public String inspectSignList(InspectPunishMoneyQueryEntity checkEntity, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return "modules/PQC/InspectPunishMoneyQuery/list";
	}

	@RequiresPermissions("inspectPunishMoneyQuery:inspectPunishMoneyQuery:view")
	@RequestMapping(value = "listInfo")
	public String inspectSignListCheck(InspectPunishMoneyQueryEntity checkEntity, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		if (null != checkEntity.getStartDate()) {
			model.addAttribute("startDate", checkEntity.getStartDate());
		}
		if (null != checkEntity.getEndDate()) {

			model.addAttribute("endDate", checkEntity.getEndDate());
		}
		
		
		int x = 0;
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			checkEntity.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}

		Page<InspectPunishMoneyQueryEntity> page = service
				.findPage(new Page<InspectPunishMoneyQueryEntity>(request, response), checkEntity);
		for (InspectPunishMoneyQueryEntity item : page.getList()) {
			StringBuilder sb = new StringBuilder();
			List<InspectPunishMoneyQueryEntity> name = service.findName(item.getCheckItemId());

			if(null != name && name.size() > 0){
				for (InspectPunishMoneyQueryEntity inspectPunishMoneyQueryEntity : name) {
					if(sb.length() > 0){
						sb.append(",");
						sb.append(inspectPunishMoneyQueryEntity.getIllegalName());
					}else{
						sb.append(inspectPunishMoneyQueryEntity.getIllegalName());
					}
				}
			}
			item.setIllegalName(sb.toString());

		}

		if (x > 0) {
			checkEntity.setStoreId(null);

		}
		model.addAttribute("page", page);
		model.addAttribute("checkEntity", checkEntity);

		return "modules/PQC/InspectPunishMoneyQuery/list";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="exportInspectorFineMoneyDetailToExcel")
	public void exportInspectorFineMoneyDetailToExcel(HttpServletResponse response,InspectPunishMoneyQueryEntity checkEntity,HttpServletRequest request){
	
		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		
		
		HSSFWorkbook excel = service.exportExcel(checkEntity);
		
		
		ServletOutputStream out = null;
		
		try {
			
			response.setContentType("application/binary;charset=UTF-8");
			String excelHead= new String(("质检处罚表-"+now).getBytes(),"ISO8859-1");
			
			response.setHeader("Content-disposition", "attachment; filename="+excelHead+".xls");
			out=response.getOutputStream();
			excel.write(out);
		} catch (Exception e) {
			e.printStackTrace();
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
