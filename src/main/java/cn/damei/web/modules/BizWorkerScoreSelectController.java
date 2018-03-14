/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.dao.modules.BizBizEmployeegroupVoDao;
import cn.damei.entity.modules.BizEmgrouprelation;
import cn.damei.entity.modules.BizEmployeegroupVO;
import cn.damei.service.modules.BizEmGroupRelationService;
import cn.damei.service.modules.BizEmployeegroupVoService;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizEvalActivityIndex;
import cn.damei.service.modules.BizEvalActivityService;
import cn.damei.entity.modules.BizEvalIndex;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.BizWorkerScoreDao;
import cn.damei.entity.modules.BizWorkerScore;
import cn.damei.service.modules.BizWorkerScoreService;

/**
 * 工人组分数查询Controller
 * 
 * @author ws
 * @version 2017-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/workerScore/workerScoreSelect")
public class BizWorkerScoreSelectController extends BaseController {

	@Autowired
	private BizWorkerScoreService bizWorkerScoreService;

	@Autowired
	private BizEmployeegroupVoService bizEmployeegroupVoService;

	@Autowired
	private BizEmGroupRelationService bizEmGroupRelationService;

	@Autowired
	private BizEmployeeService bizEmployeeService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@Autowired   
	private BizBizEmployeegroupVoDao bizBizEmployeegroupVoDao;
	
	@Autowired 
	private BizWorkerScoreDao bizWorkerScoreDao;
	
	@Autowired 
	private BizEvalActivityService bizEvalActivityService;
	
	@ModelAttribute
	public BizWorkerScore get(@RequestParam(required = false) String id) {
		BizWorkerScore entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizWorkerScoreService.get(id);
			// 关联数据
			BizEmgrouprelation relation = new BizEmgrouprelation();
			relation.setGroupId(id);
			List<BizEmgrouprelation> empGropRelation = bizEmGroupRelationService.findList(relation);
			for (BizEmgrouprelation reg : empGropRelation) {
				BizEmployee manager = bizEmployeeService.get(reg.getManagerId());
				if (manager != null) {
					reg.setManagerName(manager.getRealname());
				}
			}
			entity.setEmpGropRelation(empGropRelation);
		}
		if (entity == null) {
			entity = new BizWorkerScore();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(BizWorkerScore bizWorkerScore, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizWorkerScore.setStoreId(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if (bizWorkerScore.getStoreId() == null || bizWorkerScore.getStoreId().equals("1")) {
				bizWorkerScore.setStoreId("2");
			}
		}
		/**
		 * 工程模式控制
		 */
		User user = UserUtils.getUser();
		String projectMode = user.getProjectMode();
		if(projectMode.equals("3") || projectMode.equals("2")){
			/*bizEmployeegroup.setProjectMode(null);*/
		}else{
			bizWorkerScore.setProjectMode(projectMode);
			model.addAttribute("projectModeEnable", true);
		}
		

		// 区域
		if (StringUtils.isBlank(bizWorkerScore.getElactricationId())) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizWorkerScore.setEnginDepartIds(list);
				} else {
					bizWorkerScore.setEnginDepartIds(null);
				}
			} else {
				bizWorkerScore.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(Integer.parseInt(bizWorkerScore.getElactricationId()));
			bizWorkerScore.setEnginDepartIds(list);
		}

		//Collections.sort(page.getList());
		List<BizWorkerScore>llist=bizWorkerScoreDao.findSort(bizWorkerScore);
		List ll=new ArrayList<>(); 
		//System.out.println(llist.size());
		if(llist.size()>0){
		int index=1;
		llist.get(0).setSort(index);
			for(int i=0;i<llist.size()-1;i++){
					if(llist.get(i).getStarScore().equals(llist.get(i+1).getStarScore())){
						llist.get(i+1).setSort(index);
					}else{
						index++;
						llist.get(i+1).setSort(index);
					}
					System.out.println(llist.get(i).getSort());
					ll.add(llist.get(i).getSort());
		}
			String no = request.getParameter("pageNo");
			model.addAttribute("bb",ll);
			if(no!=null&&no!=""){
				model.addAttribute("no",no);
			}else{
			model.addAttribute("no",1);
			}
		}
		Page<BizWorkerScore> page = bizWorkerScoreService.findPage(new Page<BizWorkerScore>(request, response), bizWorkerScore);
		model.addAttribute("page", page);
		
		bizWorkerScore.getElactricationId();
		model.addAttribute("bizWorkerScore", bizWorkerScore);
		model.addAttribute("userProjectMode", UserUtils.getUser().getProjectMode());
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		request.setAttribute("ElactricationId", bizWorkerScore.getElactricationId());
		request.setAttribute("EmpId", bizWorkerScore.getEmpId());
		return "modules/workerScore/bizStarScoreList";
	}
	
	
	@RequestMapping(value = "queryBizWorkerScoreDetail")
	public String queryBizWorkerScoreDetail(BizWorkerScore bizWorkerScore,String empId,String projectMode,String storeId, HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("projectMode", projectMode);
		session.setAttribute("storeId", storeId);
		session.setAttribute("empId", empId);
		System.out.println();								
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizWorkerScore.getStoreId()){
			if(null!=user.getStoreId()){
				bizWorkerScore.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizWorkerScore.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizWorkerScore.setProjectMode(be.getProjectMode());
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
						bizWorkerScore.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		BizEvalIndex bizEvalIndex = new BizEvalIndex();
		bizEvalIndex.setStoreId(Integer.parseInt(bizWorkerScore.getStoreId()));
		bizEvalIndex.setProjectMode(bizEvalIndex.getProjectMode());
		bizEvalIndex.setIsEnabled("1");
		
		Page<BizWorkerScore> page = bizWorkerScoreService.queryBizWorkerScoreDetail(new Page<BizWorkerScore>(request, response), bizWorkerScore,empId);
		if(page!=null && page.getList()!=null && page.getList().size()>0){
			for(BizWorkerScore grade :page.getList()){
				List<Integer> list =new ArrayList<Integer>();
				if(grade.getEvalRoleType() != null){
					if(grade.getEvalRoleType()  == 1){//项目经理
						list.add(101);
						list.add(102);
					}else if(grade.getEvalRoleType() == 2){//质检
						list.add(201);
						list.add(202);
					}else if(grade.getEvalRoleType() == 3){//客户
						list.add(301);
						list.add(302);
					}
					
					Map<String,Object> map =new HashMap<String, Object>();
					map.put("relatedBusinessId", grade.getPackageId());
					map.put("evalType", "1");
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
		model.addAttribute("bizWorkerScore", bizWorkerScore);
		model.addAttribute("page", page);
		return "modules/workerScore/bizStarScoreDetail";
	}

	@ResponseBody
	@RequestMapping(value = "ajaxemployeegroup")
	public Map<String, Object> ajaxemployeegroup(BizEmployeegroupVO bizEmployeegroupVO, Model model, HttpServletRequest request) {
		// List<BizEmgrouprelation> bizEmgrouprelationlist =
		// bizEmGroupRelationService.queryemployeegroup(bizEmployeegroup);
		List<BizEmployeegroupVO> bizEmployeegroupVOList = bizEmployeegroupVoService.queryemployeegroup(bizEmployeegroupVO);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultMap", bizEmployeegroupVOList);
		return resultMap;
	}
}