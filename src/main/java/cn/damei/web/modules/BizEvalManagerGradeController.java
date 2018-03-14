package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizEvalActivityIndex;
import cn.damei.entity.modules.BizEvalWorkGrade;
import cn.damei.service.modules.BizEvalActivityService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/evaluate/bizEvalManagerGrade")
public class BizEvalManagerGradeController extends BaseController{

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@Autowired
	private BizEvalActivityService bizEvalActivityService;
	
	@RequestMapping(value = "openBizEvalManagerGradePage")
	public String openBizEvalManagerGradePage(BizEvalWorkGrade bizEvalWorkGrade, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();

		if(null==bizEvalWorkGrade.getStoreId()){
			if(null!=user.getStoreId()){
				bizEvalWorkGrade.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizEvalWorkGrade.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizEvalWorkGrade.setProjectMode(be.getProjectMode());
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
						bizEvalWorkGrade.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/evaluate/bizevalactivity/bizEvalManagerGradeList";
	}
	
	@RequestMapping(value = "queryBizEvalManagerGrade")
	public String queryBizEvalManagerGrade(BizEvalWorkGrade bizEvalWorkGrade, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();

		if(null==bizEvalWorkGrade.getStoreId()){
			if(null!=user.getStoreId()){
				bizEvalWorkGrade.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizEvalWorkGrade.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizEvalWorkGrade.setProjectMode(be.getProjectMode());
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
						bizEvalWorkGrade.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizEvalWorkGrade> page = bizEvalActivityService.queryBizEvalManagerGradePage(new Page<BizEvalWorkGrade>(request, response), bizEvalWorkGrade);
		if(page!=null && page.getList()!=null && page.getList().size()>0){
			for(BizEvalWorkGrade grade :page.getList()){
				List<Integer> list =new ArrayList<Integer>();
				if(grade.getEvalRoleType() != null){
					 if(grade.getEvalRoleType() == 2){
						list.add(201);
						list.add(202);
					}else if(grade.getEvalRoleType() == 3){
						list.add(301);
						list.add(302);
					}
					
					Map<String,Object> map =new HashMap<String, Object>();
					map.put("relatedBusinessId", grade.getRelatedBusinessId());
					map.put("evalType", "2");
					map.put("list", list);
					map.put("storeId", Integer.parseInt(grade.getStoreId()));
					map.put("projectModel", Integer.parseInt(grade.getProjectMode()));
					List<BizEvalActivityIndex> indexList = bizEvalActivityService.queryEvalIndexRoleScore(map);
					Double gradtotalScore = 0.0;
					for(BizEvalActivityIndex bizEvalActivityIndex : indexList){
						
						if(bizEvalActivityIndex.getIndexScore() != null){
							gradtotalScore=gradtotalScore+bizEvalActivityIndex.getIndexScore();
						}
					}
					grade.setGradtotalScore(gradtotalScore);
				    grade.setBizEvalActivityIndexList(indexList);
				}
			}
		}
		
		model.addAttribute("page", page);

		return "modules/evaluate/bizevalactivity/bizEvalManagerGradeList";
	}
}
