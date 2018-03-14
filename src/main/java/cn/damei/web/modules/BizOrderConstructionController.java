package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizNodePlan;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderConstruction;
import cn.damei.entity.modules.BizOrderConstructionService;
import cn.damei.service.modules.BizNodePlanService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * @author llp
 * 只查询订单状态是200-施工中 的项目
 * @version 2016-11-26
 */
@Controller
@RequestMapping(value = "${adminPath}/bizconstruction/bizConstruction")
public class BizOrderConstructionController extends BaseController {

	/*
	 * private static Logger logger =
	 * LoggerFactory.getLogger(BizConfirmStartController.class);
	 */

	@Autowired
	private BizOrderConstructionService bizOrderConstructionService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizNodePlanService bizNodePlanService;

	/**
	 * 准备列出施工中项目列表
	 */
	@RequiresPermissions("bizconstruction:bizConstruction:view")
	@RequestMapping(value = { "preList", "" })
	public String packageList(BizOrderConstruction bizOrderConstruction, Model model, HttpServletRequest request) {
		logger.info("当前登陆人ID："+UserUtils.getUser().getId());
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null == bizOrderConstruction.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderConstruction.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizOrderConstruction.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderConstruction.setProjectMode(be.getProjectMode());
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
						bizOrderConstruction.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/bizconfirmstart/bizConstructionList";
	}

	/**
	 * 列出施工中项目列表
	 */
	@RequiresPermissions("bizconstruction:bizConstruction:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderConstruction bizOrderConstruction, Model model, HttpServletResponse response,
			HttpServletRequest request) {
		User user = UserUtils.getUser();
		//过滤门店
		if(null == bizOrderConstruction.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderConstruction.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizOrderConstruction.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderConstruction.setProjectMode(be.getProjectMode());
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
						bizOrderConstruction.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}

		Page<BizOrderConstruction> page = bizOrderConstructionService
				.findPage(new Page<BizOrderConstruction>(request, response), bizOrderConstruction);
		List<BizNodePlan> list = bizNodePlanService.getbyOrderIDList();
		
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		return "modules/bizconfirmstart/bizConstructionList";
	}
}
