package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.service.modules.CheckNodeService;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizEvalActivity;
import cn.damei.entity.modules.BizEvalActivityIndex;
import cn.damei.entity.modules.BizEvalActivityStage;
import cn.damei.entity.modules.BizEvalActivityTaskpackTemp;
import cn.damei.service.modules.BizEvalActivityService;
import cn.damei.entity.modules.BizEvalIndex;
import cn.damei.service.modules.BizEvalIndexService;
import cn.damei.entity.modules.Dict;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.service.modules.BizTaskPackageTemplatService;

/**
 * 评价活动设置Controller
 * @author wyb
 * @version 2017-02-25
 */
@Controller
@RequestMapping(value = "${adminPath}/evaluate/bizevalactivity/bizEvalActivity")
public class BizEvalActivityController extends BaseController {

	@Autowired
	private BizEvalActivityService bizEvalActivityService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizTaskPackageTemplatService bizTaskPackageTemplatService;
	@Autowired
	private BizEvalIndexService bizEvalIndexService;
	@Autowired
	private CheckNodeService checkNodeService;
	
	@ModelAttribute
	public BizEvalActivity get(@RequestParam(required=false) Integer id) {
		BizEvalActivity entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizEvalActivityService.get(id);
		}
		if (entity == null){
			entity = new BizEvalActivity();
		}
		return entity;
	}
	
	/**
	 * 列表页--空
	 * @param bizEvalActivity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizevalactivity:bizEvalActivity:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizEvalActivity bizEvalActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizEvalActivity.getStoreId()){
			if(null!=user.getStoreId()){
				bizEvalActivity.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizEvalActivity.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizEvalActivity.setProjectMode(be.getProjectMode());
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
						bizEvalActivity.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/evaluate/bizevalactivity/bizEvalActivityList";
	}
	
	/**
	 * 列表页--查询
	 * @param bizEvalActivity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizevalactivity:bizEvalActivity:view")
	@RequestMapping(value = {"list1", ""})
	public String list1(BizEvalActivity bizEvalActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizEvalActivity.getStoreId()){
			if(null!=user.getStoreId()){
				bizEvalActivity.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizEvalActivity.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizEvalActivity.setProjectMode(be.getProjectMode());
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
						bizEvalActivity.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizEvalActivity> page = bizEvalActivityService.findPage(new Page<BizEvalActivity>(request, response), bizEvalActivity); 
		model.addAttribute("page", page);
		return "modules/evaluate/bizevalactivity/bizEvalActivityList";
	}

	/**
	 * 新增/修改   页
	 * @param bizEvalActivity
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizevalactivity:bizEvalActivity:view")
	@RequestMapping(value = "form")
	public String form(BizEvalActivity bizEvalActivity, Model model) {
		
		//修改操作
//		List<BizTaskPackageTemplat> taskPackageList = null;
//		List<BizEvalActivityTaskpackTemp> nowTaskPackageList = null;
//		List<BizEvalActivityIndex> nowIndexList = null;
//		List<BizEvalIndex> bizEvalIndexList = null;
//		if(null!=bizEvalActivity.getId()&&!"".equals(bizEvalActivity.getId())){
			//所有的任务包模板
//			BizTaskPackageTemplat bizTaskPackageTemplat = new BizTaskPackageTemplat();
//			bizTaskPackageTemplat.setStoreId(bizEvalActivity.getStoreId().toString());
//			bizTaskPackageTemplat.setStatus("1");
//			taskPackageList = bizTaskPackageTemplatService.findList(bizTaskPackageTemplat);
			//已选择的任务包
//			nowTaskPackageList = bizEvalActivityService.findEvalActivityTaskpackTemp(bizEvalActivity.getId());
			
			//已添加的评价设置
//			nowIndexList = bizEvalActivityService.findEvalActivityIndex(bizEvalActivity.getId());
			
			//评价指标列表
//			BizEvalIndex bizEvalIndex = new BizEvalIndex();
//			bizEvalIndex.setStoreId(bizEvalActivity.getStoreId());
//			bizEvalIndex.setProjectMode(bizEvalActivity.getProjectMode());
//			bizEvalIndex.setIsEnabled(ConstantUtils.IS_ENABLE_1);
//			bizEvalIndexList = bizEvalIndexService.findList(bizEvalIndex);
//		}
		//评价类型
		String type = "eval_role_type";
		List<Dict> dictList = bizEvalActivityService.findDict(type);
		
		//项目经理评价阶段
		String evalStageType="manager_eval_stage";
		List<Dict> managerEvalStageList = bizEvalActivityService.findDict(evalStageType);
//		model.addAttribute("taskPackageList", taskPackageList);
//		model.addAttribute("nowTaskPackageList", nowTaskPackageList);
//		model.addAttribute("nowIndexList", nowIndexList);
//		model.addAttribute("bizEvalIndexList", bizEvalIndexList);
		model.addAttribute("dictList", dictList);
		model.addAttribute("managerEvalStageList",managerEvalStageList);
		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
		model.addAttribute("bizEvalActivity", bizEvalActivity);
		return "modules/evaluate/bizevalactivity/bizEvalActivityForm";
	}
	
	/**
	 * 详情页
	 * @param bizEvalActivity
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizevalactivity:bizEvalActivity:view")
	@RequestMapping(value = "details")
	public String details(BizEvalActivity bizEvalActivity, Model model) {
				
		//修改操作
		
		List<BizEvalActivityIndex> nowIndexList = null;
		List<BizEvalIndex> bizEvalIndexList = null;
		
		if(null!=bizEvalActivity.getId()){
			bizEvalActivity = bizEvalActivityService.get(bizEvalActivity.getId());
			if(bizEvalActivity.getEvalTargetType().equals("1")){//评价工人
				List<BizTaskPackageTemplat> taskPackageList = null;
				List<BizEvalActivityTaskpackTemp> nowTaskPackageList = null;
				//所有的任务包模板
				BizTaskPackageTemplat bizTaskPackageTemplat = new BizTaskPackageTemplat();
				bizTaskPackageTemplat.setStoreId(bizEvalActivity.getStoreId().toString());
				bizTaskPackageTemplat.setProjectMode(bizEvalActivity.getProjectMode());
				bizTaskPackageTemplat.setStatus("1");
				taskPackageList = bizTaskPackageTemplatService.findList(bizTaskPackageTemplat);
				//已选择的任务包
				nowTaskPackageList = bizEvalActivityService.findEvalActivityTaskpackTemp(bizEvalActivity.getId());
				model.addAttribute("taskPackageList", taskPackageList);
				model.addAttribute("nowTaskPackageList", nowTaskPackageList);
			}else if(bizEvalActivity.getEvalTargetType().equals("2")){//评价项目经理
				List<BizEvalActivityStage> stageList = bizEvalActivityService.queryEvalStage(bizEvalActivity.getId());
				
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("storeid", bizEvalActivity.getStoreId());
				map.put("projectMode", bizEvalActivity.getProjectMode());
				List<DropModel> checkNodeList = checkNodeService.queryNodeListByStoreId(map);
				model.addAttribute("stageList", stageList);
				model.addAttribute("checkNodeList", checkNodeList);
			}
		
			//已添加的评价设置
			nowIndexList = bizEvalActivityService.findEvalActivityIndex(bizEvalActivity.getId());
		
			//评价指标列表
			BizEvalIndex bizEvalIndex = new BizEvalIndex();
			bizEvalIndex.setStoreId(bizEvalActivity.getStoreId());
			bizEvalIndex.setProjectMode(bizEvalActivity.getProjectMode());
			bizEvalIndex.setIsEnabled(ConstantUtils.IS_ENABLE_1);
			bizEvalIndexList = bizEvalIndexService.findList(bizEvalIndex);
		}
		//评价类型
		String type = "eval_role_type";
		List<Dict> dictList = bizEvalActivityService.findDict(type);
		
		
		model.addAttribute("nowIndexList", nowIndexList);
		model.addAttribute("bizEvalIndexList", bizEvalIndexList);
		model.addAttribute("dictList", dictList);
		model.addAttribute("bizEvalActivity", bizEvalActivity);
		return "modules/evaluate/bizevalactivity/bizEvalActivityDetails";
	}

	/**
	 * 新增修改  保存
	 * @param bizEvalActivity
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("bizevalactivity:bizEvalActivity:edit")
	@RequestMapping(value = "save")
	public String save(BizEvalActivity bizEvalActivity,String[] managerEvalStage,String[] evalStageCheckNode,String[] evalRoleType,String[] evalIndexId,String[] evalTotalScore,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizEvalActivity)){
			return form(bizEvalActivity, model);
		}
		bizEvalActivityService.save(bizEvalActivity,managerEvalStage,evalStageCheckNode,evalRoleType,evalIndexId,evalTotalScore);
		addMessage(redirectAttributes, "保存评价活动设置成功");
		return "redirect:"+Global.getAdminPath()+"/evaluate/bizevalactivity/bizEvalActivity/list1?repage";
	}
	
	
	/**
	 * 删除
	 * @param bizEvalActivity
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("bizevalactivity:bizEvalActivity:edit")
	@RequestMapping(value = "delete")
	public String delete(BizEvalActivity bizEvalActivity, RedirectAttributes redirectAttributes) {
		bizEvalActivityService.delete(bizEvalActivity);
		addMessage(redirectAttributes, "删除评价活动设置成功");
		return "redirect:"+Global.getAdminPath()+"/evaluate/bizevalactivity/bizEvalActivity/list1?repage";
	}
	
	/**
	 * 停启用
	 * @param bizEvalActivity
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("bizevalactivity:bizEvalActivity:edit")
	@RequestMapping(value = "isEnabled")
	public String isEnabled(BizEvalActivity bizEvalActivity, RedirectAttributes redirectAttributes) {
		
		if(bizEvalActivity.getIsEnabled().equals(ConstantUtils.IS_ENABLE_1)){
			//停用
			bizEvalActivity.setIsEnabled(ConstantUtils.IS_ENABLE_0);
			bizEvalActivity.preUpdate();
			bizEvalActivityService.isEnabled(bizEvalActivity);
			addMessage(redirectAttributes, "评价活动停用成功");
		}else{
			//启用
			bizEvalActivity.setIsEnabled(ConstantUtils.IS_ENABLE_1);
			bizEvalActivity.preUpdate();
			bizEvalActivityService.isEnabled(bizEvalActivity);
			addMessage(redirectAttributes, "评价活动启用成功");
		}
		
		
		
		return "redirect:"+Global.getAdminPath()+"/evaluate/bizevalactivity/bizEvalActivity/list1?repage";
	}
	
	/**
	 * 根据门店查询任务包模板
	 * @param storeId
	 * @return
	 */
	@RequestMapping(value = "findTaskpackage")
	public @ResponseBody List<BizTaskPackageTemplat> findTaskpackage(String storeId,String projectMode){
		
		BizTaskPackageTemplat bizTaskPackageTemplat = new BizTaskPackageTemplat();
		bizTaskPackageTemplat.setStoreId(storeId);
		bizTaskPackageTemplat.setStatus("1");
		bizTaskPackageTemplat.setProjectMode(projectMode);
		List<BizTaskPackageTemplat> list = bizTaskPackageTemplatService.findList(bizTaskPackageTemplat);
		return list;
	}
	
	/**
	 * 判断评价活动中的任务包是否已经存在
	 * @param storeId
	 * @return
	 */
	@RequestMapping(value = "isTaskpackage")
	public @ResponseBody boolean isTaskpackage(BizEvalActivity bizEvalActivity){
		
		List<Integer> list = new ArrayList<Integer>();
		if(StringUtils.isNoneBlank(bizEvalActivity.getTaskpackTempId())){
			String[] ids = bizEvalActivity.getTaskpackTempId().split(",");
			for(String id:ids){
				list.add(Integer.parseInt(id));
			}
		}
		
		Integer count = bizEvalActivityService.isTaskpackage(bizEvalActivity,list);
		boolean flag = true;
		if(count >0){
			flag = false;
		}
		return flag;
	}
	/**
	 * 判断评价项目经理活动关联的约检节点是否存在
	 * @param bizEvalActivity
	 * @return
	 */
	@RequestMapping(value = "isCheckStage")
	public  @ResponseBody boolean isCheckStage(BizEvalActivity bizEvalActivity){
		List<Integer> list = new ArrayList<Integer>();
		if(StringUtils.isNoneBlank(bizEvalActivity.getEvalStageCheckNodeList())){
			String[] ids = bizEvalActivity.getEvalStageCheckNodeList().split(",");
			for(String id:ids){
				list.add(Integer.parseInt(id));
			}
		}
		
		Integer count = bizEvalActivityService.isCheckStage(bizEvalActivity,list);
		boolean flag = true;
		if(count >0){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 添加按钮  根据门店和工程模式查询评价指标
	 * @param storeId
	 * @param projectMode
	 * @return
	 */
	@RequestMapping(value = "addEvalIndex")
	public @ResponseBody List<BizEvalIndex> addEvalIndex(String storeId,String projectMode){
		
		BizEvalIndex bizEvalIndex = new BizEvalIndex();
		bizEvalIndex.setStoreId(Integer.valueOf(storeId));
		bizEvalIndex.setProjectMode(projectMode);
		bizEvalIndex.setIsEnabled(ConstantUtils.IS_ENABLE_1);
		List<BizEvalIndex> bizEvalIndexList = bizEvalIndexService.findList(bizEvalIndex);
		
		return bizEvalIndexList;
	}
	
	/**
	 * 是否可以启用
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "isEnabledEval")
	public @ResponseBody String isEnabledEval(Integer id) {
		BizEvalActivity bizEvalActivity = bizEvalActivityService.get(id);
		List<Integer> list = bizEvalActivityService.isEnabledEval(id);
		Integer count = bizEvalActivityService.isTaskpackage(bizEvalActivity,list);
		if(count > 0){
			return "1";
		}
		return "0";
	}

}