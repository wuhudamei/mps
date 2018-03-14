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
import cn.damei.common.utils.ExportOrderScore;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.ScoreOrderQuery;
import cn.damei.service.modules.ScoreOrderService;

/**
 * 订单评分Controller
 * 
 * @author lft
 * @version 2017-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/score/order")
public class ScoreOrderController extends BaseController {

	@Autowired
	private ScoreOrderService scoreOrderService ;	
	
	@ModelAttribute
	public ScoreOrderQuery getScoreOrderQuery() {
		ScoreOrderQuery entity = null;
		if (entity == null){
			entity = new ScoreOrderQuery();
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
	@RequiresPermissions("scoreOrderList:view")
	@RequestMapping(value = { "ScoreOrderlist2", "" })	
	public String ScoreOrderlist(Model model,ScoreOrderQuery scoreOrderQuery,HttpServletRequest request,HttpServletResponse  response) {
        //过滤门店
        User user = UserUtils.getUser();
        if(null==scoreOrderQuery.getName()){
            if(null!=user.getStoreId()){
                scoreOrderQuery.setName(user.getStoreId());
            }
        }

		String typeCode = scoreOrderQuery.getTypeCode();
		String scoreQuery = scoreOrderQuery.getScoreQuery();
		String scoreContent = scoreOrderQuery.getScoreContent();
		if(StringUtils.isBlank(scoreContent)){
			scoreOrderQuery.setScoreContent(null);
		}

		if(StringUtils.isBlank(typeCode)){
			scoreOrderQuery.setTypeCode(null);
		}
		if(StringUtils.isBlank(scoreQuery)){
			scoreOrderQuery.setScoreQuery(null);
		}
		
		Page<ScoreOrderQuery> page = new Page<ScoreOrderQuery>(request, response);
		List<ScoreOrderQuery> selectOrderScoreQuery = scoreOrderService.selectOrderScoreQuery(page,scoreOrderQuery);
		 page.setList(selectOrderScoreQuery);
		 model.addAttribute("page", page);
		return  "modules/score/ScoreOrderList";
	}
	
	@RequiresPermissions("scoreOrderList:view")
	@RequestMapping(value = {"export", "" })	
	public void ScoreOrderlistExport(ScoreOrderQuery scoreOrderQuery,HttpServletRequest request,HttpServletResponse  response) {
		String name = scoreOrderQuery.getName();
		String typeCode = scoreOrderQuery.getTypeCode();
		String scoreQuery = scoreOrderQuery.getScoreQuery();
		String scoreContent = scoreOrderQuery.getScoreContent();
		if(StringUtils.isBlank(scoreContent)){
			scoreOrderQuery.setScoreContent(null);
		}
		if(StringUtils.isBlank(name)){
			scoreOrderQuery.setName("2");
		}
		if(StringUtils.isBlank(typeCode)){
			scoreOrderQuery.setTypeCode(null);
		}
		if(StringUtils.isBlank(scoreQuery)){
			scoreOrderQuery.setScoreQuery(null);
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		ServletOutputStream ouputStream= null;//创建一个输出流对象
		List<ScoreOrderQuery> selectOrderScoreQuery = scoreOrderService.selectOrderScoreQuery(null,scoreOrderQuery);
		scoreOrderQuery.setScoreContent(scoreContent);
		HSSFWorkbook problemDetail = ExportOrderScore.exportOrderScore(selectOrderScoreQuery);
		try {  
			response.setContentType("application/binary;charset=utf-8"); 
			String headerStr =new String(("订单评分统计表"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码  
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
			ouputStream = response.getOutputStream();    
			problemDetail.write(ouputStream);  
			ouputStream.flush();    
			ouputStream.close();
		} catch (IOException ex) {  
            ex.printStackTrace();  
        }
	}
	/**
	 * 根据门店获取订单评价类型
	 * @param storeId
	 * @return
	 */
	@RequestMapping(value = { "getScoreContentByStoreId", "" })	
	public @ResponseBody List<Map<String ,Object> > getScoreContentByStoreId(String storeId) {
		return
		scoreOrderService.getScoreContentByStoreId(storeId);
	}
}