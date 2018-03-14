package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ExportScoreOrderEmployee;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.ScorOrderEmployee;
import cn.damei.service.modules.ScoreOrderService;


@Controller
@RequestMapping(value = "${adminPath}/score/employee")
public class ScoreOrderEmployeeController extends BaseController {
	
	@Autowired
	private ScoreOrderService scoreOrderService ;	
	
	@ModelAttribute
	public ScorOrderEmployee getScoreOrderQuery() {
		ScorOrderEmployee entity = null;
		if (entity == null){
			entity = new ScorOrderEmployee();
		}
		return entity;
	}

	@RequiresPermissions("scoreOrderEmployeeList:view")
	@RequestMapping(value = { "scoreOrderEmployeelist2", "" })
	public String  ScoreOrderEmployeelist2(Model model,ScorOrderEmployee scorOrderEmployee,HttpServletRequest request,HttpServletResponse  response) {

            User user = UserUtils.getUser();
            if(null==scorOrderEmployee.getStoreId()){
                if(null!=user.getStoreId()){
                    scorOrderEmployee.setStoreId(user.getStoreId());
                }
            }
			String name = scorOrderEmployee.getName();
			if( StringUtils.isBlank( name ) ){
				scorOrderEmployee.setName("2");
			}
			if( StringUtils.isBlank( scorOrderEmployee.getQuery() ) ){
				scorOrderEmployee.setQuery(null);;
			}
			if( StringUtils.isBlank( scorOrderEmployee.getEmployeePost()) ){
				scorOrderEmployee.setEmployeePost(null);
			}
		Page<ScorOrderEmployee> page = new Page<ScorOrderEmployee>(request, response);
		List<ScorOrderEmployee> selectScorOrderEmployee =  
				scoreOrderService.selectScorOrderEmployeeQuery(page,scorOrderEmployee);
		page.setList(selectScorOrderEmployee);
		 model.addAttribute("page", page);
		return "modules/score/scoreOrderEmployeeList";
	}

	@RequiresPermissions("scoreOrderEmployeeList:view")
	@RequestMapping(value = { "export", "" })
	public void  export(ScorOrderEmployee scorOrderEmployee,HttpServletRequest request,HttpServletResponse  response) {
			String name = scorOrderEmployee.getName();
			if( StringUtils.isBlank( name ) ){
				scorOrderEmployee.setName("2");
			}
			if( StringUtils.isBlank( scorOrderEmployee.getQuery() ) ){
				scorOrderEmployee.setQuery(null);;
			}
			if( StringUtils.isBlank( scorOrderEmployee.getEmployeePost()) ){
				scorOrderEmployee.setEmployeePost(null);
			}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		List<ScorOrderEmployee> selectScorOrderEmployee =  
		scoreOrderService.selectScorOrderEmployeeQuery(null,scorOrderEmployee);
		HSSFWorkbook problemDetail = ExportScoreOrderEmployee.exportScoreOrderEmployee(selectScorOrderEmployee);
		ServletOutputStream ouputStream= null;
		try {  
			response.setContentType("application/binary;charset=utf-8"); 
			String headerStr =new String(("员工评分统计表"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
			ouputStream = response.getOutputStream();    
			problemDetail.write(ouputStream);  
			ouputStream.flush();    
			ouputStream.close();
		} catch (IOException ex) {  
            ex.printStackTrace();  
        }
	}
	

	@RequestMapping(value = { "selectPositionTypeByStoreId", "" })
	public @ResponseBody  List<Map<String,Object>> selectPositionTypeByStoreId(String storeId) {
		return scoreOrderService.selectPositionTypeByStoreId(storeId);
	}
	
}