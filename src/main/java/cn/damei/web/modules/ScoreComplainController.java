package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ExportComplainOrder;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.ScoreOrderComplain;
import cn.damei.service.modules.ScoreOrderService;

/**
 * 客诉Controller
 * 
 * @author lft
 * @version 2017-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/score/complain")
public class ScoreComplainController extends BaseController {

	@Autowired
	private ScoreOrderService scoreOrderService ;	
	
	@ModelAttribute
	public ScoreOrderComplain getScoreOrderQuery() {
		ScoreOrderComplain entity = null;
		if (entity == null){
			entity = new ScoreOrderComplain();
		}
		return entity;
	}
	/**
	 * 
	 * @param model
	 * @param scoreOrderQuery
	 * @param request
	 * @param response
	 * @return 根据 条件查询列表
	 */
	@RequiresPermissions("scoreOrderComplainList:view")
	@RequestMapping(value = { "ScoreOrderlist2", "" })	
	public String ScoreOrderlist(Model model,ScoreOrderComplain scoreOrderComplain,HttpServletRequest request,HttpServletResponse  response) {
        //过滤门店
        User user = UserUtils.getUser();
        if(null==scoreOrderComplain.getName()){
            if(null!=user.getStoreId()){
                scoreOrderComplain.setName(user.getStoreId());
            }
        }
		String query = scoreOrderComplain.getQuery();
		String name = scoreOrderComplain.getName();
		String label = scoreOrderComplain.getLabel();
		if(	StringUtils.isBlank(name)){
			scoreOrderComplain.setName("2");
		}
		if(StringUtils.isBlank(query)){
			scoreOrderComplain.setQuery(null);
		}
		if(StringUtils.isBlank(label)){
			scoreOrderComplain.setLabel(null);
		}
		Page<ScoreOrderComplain> page = new Page<ScoreOrderComplain>(request, response);
		List<ScoreOrderComplain> selectScoreOrderComplainQuery = scoreOrderService.selectScoreOrderComplainQuery(page,scoreOrderComplain);
		page.setList(selectScoreOrderComplainQuery);
		model.addAttribute("page", page);
		return  "modules/score/complainOrderList";
	}
	
	@RequiresPermissions("scoreOrderComplainList:view")
	@RequestMapping(value = { "export", "" })	
	public void export(ScoreOrderComplain scoreOrderComplain,HttpServletRequest request,HttpServletResponse  response) {
		String query = scoreOrderComplain.getQuery();
		String name = scoreOrderComplain.getName();
		String label = scoreOrderComplain.getLabel();
		if(	StringUtils.isBlank(name)){
			scoreOrderComplain.setName("2");
		}
		if(StringUtils.isBlank(query)){
			scoreOrderComplain.setQuery(null);
		}
		if(StringUtils.isBlank(label)){
			scoreOrderComplain.setLabel(null);
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		ServletOutputStream ouputStream= null;//创建一个输出流对象
		List<ScoreOrderComplain> selectScoreOrderComplainQuery = scoreOrderService.selectScoreOrderComplainQuery(null,scoreOrderComplain);
		HSSFWorkbook problemDetail = ExportComplainOrder.exportComplainOrder(selectScoreOrderComplainQuery);
		try {  
			response.setContentType("application/binary;charset=utf-8"); 
			String headerStr =new String(("客诉统计表"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码  
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
			ouputStream = response.getOutputStream();    
			problemDetail.write(ouputStream);  
			ouputStream.flush();    
			ouputStream.close();
		} catch (IOException ex) {  
            ex.printStackTrace();  
        }
	}
}