
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizBusinessProblemQuery;
import cn.damei.service.modules.BizBusinessProblemQueryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "${adminPath}/bizbusinessproblemquery/bizBusinessProblemQuery")
public class BizBusinessProblemQueryController extends BaseController {

	@Autowired
	private BizBusinessProblemQueryService bizBusinessProblemQueryService;
	
	@ModelAttribute
	public BizBusinessProblemQuery get(@RequestParam(required=false) String id) {
		BizBusinessProblemQuery entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizBusinessProblemQueryService.get(id);
		}
		if (entity == null){
			entity = new BizBusinessProblemQuery();
		}
		return entity;
	}
	
	@RequiresPermissions("bizbusinessproblemquery:bizBusinessProblemQuery:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizBusinessProblemQuery bizBusinessProblemQuery, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = UserUtils.getUser();
        if(null==bizBusinessProblemQuery.getStoreId()){
            if(null!=user.getStoreId()){
                bizBusinessProblemQuery.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
		Page<BizBusinessProblemQuery> page = bizBusinessProblemQueryService.findPage(new Page<BizBusinessProblemQuery>(request, response), bizBusinessProblemQuery); 
		model.addAttribute("page", page);
		model.addAttribute("bizBusinessProblemQuery", bizBusinessProblemQuery);
		return "modules/PQC/bizbusinessproblemquery/bizBusinessProblemQueryList";
	}

	@RequiresPermissions("bizbusinessproblemquery:bizBusinessProblemQuery:view")
	@RequestMapping(value = "form")
	public String form(String problemId,Model model ) {
		Map<String,String> map=bizBusinessProblemQueryService.findIssueDetail(problemId);

		model.addAttribute("map", map);
		return "modules/PQC/bizbusinessproblemquery/bizBusinessProblemQueryForm";
	}

	@RequiresPermissions("bizbusinessproblemquery:bizBusinessProblemQuery:view")
	@RequestMapping(value = "findInfoByStoreIdAndProjectMode")
	@ResponseBody
	public Map<String,List<BizBusinessProblemQuery>> findInfoByStoreIdAndProjectMode(String storeId,String projectMode) {
	List<BizBusinessProblemQuery>issueTypeList =  bizBusinessProblemQueryService.findIssueTypeByStoreIdAndProjectModeId(storeId, projectMode);
		List<BizBusinessProblemQuery>qcNodeList = bizBusinessProblemQueryService.findQcNodeInfoByStoreIdAndProjectModeId(storeId, projectMode);

		Map<String,List<BizBusinessProblemQuery>> mapList = new HashMap<>();
		mapList.put("issueTypeList",issueTypeList);
		mapList.put("qcNodeList",qcNodeList);


		return mapList;
	}


	@RequiresPermissions("bizbusinessproblemquery:bizBusinessProblemQuery:view")
	@RequestMapping(value = "bizproblemSynQueryList")
	public String bizproblemSynQueryList(Model model ) {

		return "modules/PQC/bizbusinessproblemquery/bizBusinessSynProblemQueryList";
	}


}