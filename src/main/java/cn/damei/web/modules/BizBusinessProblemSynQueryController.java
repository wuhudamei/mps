/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizBusinessSynProblemQuery;
import cn.damei.service.modules.BizBusinessProblemSynQueryService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 经理约检问题统计Controller
 * @author mh
 * @version 2017-05-27
 */
@Controller
@RequestMapping(value = "${adminPath}/bizbusinessproblemsynquery/bizBusinessProblemQuery")
public class BizBusinessProblemSynQueryController extends BaseController {

	@Autowired
	private BizBusinessProblemSynQueryService bizBusinessProblemQueryService;
	
	@ModelAttribute
	public BizBusinessSynProblemQuery  get(@RequestParam(required=false) String id) {
		BizBusinessSynProblemQuery entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizBusinessProblemQueryService.get(id);
		}
		if (entity == null){
			entity = new BizBusinessSynProblemQuery();
		}
		return entity;
	}
	
	@RequiresPermissions("bizbusinessproblemsynquery:bizBusinessProblemQuery:view")
	@RequestMapping(value = {"list"})
	public String list(BizBusinessSynProblemQuery bizBusinessProblemQuery, HttpServletRequest request, HttpServletResponse response, Model model) {
        //过滤门店
        User user = UserUtils.getUser();
        if(null==bizBusinessProblemQuery.getStoreId()){
            if(null!=user.getStoreId()){
                bizBusinessProblemQuery.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
		Page<BizBusinessSynProblemQuery> page = bizBusinessProblemQueryService.findPage(new Page<BizBusinessSynProblemQuery>(request, response), bizBusinessProblemQuery);
		model.addAttribute("page", page);
		model.addAttribute("bizBusinessProblemQuery", bizBusinessProblemQuery);
		return "modules/PQC/bizbusinessproblemquery/bizBusinessSynProblemQueryList";
	}

	@RequiresPermissions("bizbusinessproblemsynquery:bizBusinessProblemQuery:view")
	@RequestMapping(value = "form")
	public String form(String managerId, Model model) {

		List<Map<String,String>> mapList=bizBusinessProblemQueryService.findItemManagerIssueReportDetailById(managerId);
		model.addAttribute("mapList",mapList);
		return "modules/PQC/bizbusinessproblemquery/bizBusinessSynProblemQueryForm";
	}



}