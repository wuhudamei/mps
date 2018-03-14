
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.modules.BizEvalRewardStar;
import cn.damei.service.modules.BizEvalRewardStarService;
import cn.damei.service.modules.BizEvalRewardTaskpackTempService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEvalRewardCfg;
import cn.damei.service.modules.BizEvalRewardCfgService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/bizevalrewardcfg/bizEvalRewardCfg")
public class BizEvalRewardCfgController extends BaseController {

	@Autowired
	private BizEvalRewardCfgService bizEvalRewardCfgService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@Autowired
	private BizEvalRewardTaskpackTempService bizEvalRewardTaskpackTempService;

	@Autowired
	private BizEvalRewardStarService bizEvalRewardStarService;

	@ModelAttribute
	public BizEvalRewardCfg get(@RequestParam(required = false) Integer id) {
		BizEvalRewardCfg entity = null;
		if (id != null) {
			entity = bizEvalRewardCfgService.get(id);
		}
		if (entity == null) {
			entity = new BizEvalRewardCfg();
		}
		return entity;
	}

	@RequiresPermissions("bizevalrewardcfg:bizEvalRewardCfg:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizEvalRewardCfg bizEvalRewardCfg, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();

		if (bizEvalRewardCfg.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizEvalRewardCfg.setStoreId(null);
			} else {
				bizEvalRewardCfg.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizEvalRewardCfg.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizEvalRewardCfg.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizEvalRewardCfg.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/bizevalrewardcfg/bizEvalRewardCfgList";
	}

	@RequiresPermissions("bizevalrewardcfg:bizEvalRewardCfg:view")
	@RequestMapping(value = { "loadList", "" })
	public String loadList(BizEvalRewardCfg bizEvalRewardCfg, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();

		if (bizEvalRewardCfg.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizEvalRewardCfg.setStoreId(null);
			} else {
				bizEvalRewardCfg.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizEvalRewardCfg.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizEvalRewardCfg.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizEvalRewardCfg.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		Page<BizEvalRewardCfg> page = bizEvalRewardCfgService.findPage(new Page<BizEvalRewardCfg>(request, response),
				bizEvalRewardCfg);
		model.addAttribute("page", page);
		return "modules/bizevalrewardcfg/bizEvalRewardCfgList";
	}

	@RequiresPermissions("bizevalrewardcfg:bizEvalRewardCfg:view")
	@RequestMapping(value = "form")
	public String form(BizEvalRewardCfg bizEvalRewardCfg, Model model) {
		User user = UserUtils.getUser();

		if (bizEvalRewardCfg.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isNoneBlank(storeId)) {
				bizEvalRewardCfg.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizEvalRewardCfg.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizEvalRewardCfg.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizEvalRewardCfg.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		model.addAttribute("bizEvalRewardCfg", bizEvalRewardCfg);
		return "modules/bizevalrewardcfg/bizEvalRewardCfgForm";
	}

	@RequiresPermissions("bizevalrewardcfg:bizEvalRewardCfg:view")
	@RequestMapping(value = "detail")
	public String detail(Integer id, Model model) {

		BizEvalRewardCfg bizEvalRewardCfg = bizEvalRewardCfgService.get(id);

		List<String> templatNameList = bizEvalRewardTaskpackTempService.queryEvalRewardTaskpackTempByRewardCfgId(id);

		List<BizEvalRewardStar> bizEvalRewardStarList = bizEvalRewardStarService
				.queryEvalRewardStarByEvalRewardCfgId(id);

		model.addAttribute("bizEvalRewardCfg", bizEvalRewardCfg);
		model.addAttribute("templatNameList", templatNameList);
		model.addAttribute("bizEvalRewardStarList", bizEvalRewardStarList);
		return "modules/bizevalrewardcfg/bizEvalRewardCfgDetail";
	}

	@RequiresPermissions("bizevalrewardcfg:bizEvalRewardCfg:edit")
	@RequestMapping(value = "save")
	public String save(BizEvalRewardCfg bizEvalRewardCfg, Integer[] taskpackTempId,
			RedirectAttributes redirectAttributes) {
		bizEvalRewardCfgService.add(bizEvalRewardCfg, taskpackTempId);
		addMessage(redirectAttributes, "保存评价奖励设置成功");
		return "redirect:" + Global.getAdminPath() + "/bizevalrewardcfg/bizEvalRewardCfg/loadList";
	}

	@RequestMapping(value = "checkTaskPackage")
	public @ResponseBody String checkTaskPackage(BizEvalRewardCfg bizEvalRewardCfg, String taskpackTempId) {
		List<Integer> list = new ArrayList<Integer>();
		if (StringUtils.isNoneBlank(taskpackTempId)) {
			String[] ids = taskpackTempId.split(",");
			for (String id : ids) {
				list.add(Integer.parseInt(id));
			}
		}
		Integer count = bizEvalRewardCfgService.queryCountByCondition(bizEvalRewardCfg, list);
		if (count > 0) {
			return "1";
		}
		return "0";
	}

	@RequiresPermissions("bizevalrewardcfg:bizEvalRewardCfg:edit")
	@RequestMapping(value = "isEnabledYes")
	public String isEnabledYes(Integer id, RedirectAttributes redirectAttributes) {
		BizEvalRewardCfg bizEvalRewardCfg = bizEvalRewardCfgService.get(id);
		bizEvalRewardCfg.setIsEnabled(ConstantUtils.IS_ENABLED_0);
		bizEvalRewardCfg.setUpdateBy(UserUtils.getUser());
		bizEvalRewardCfg.setUpdateDate(new Date());
		bizEvalRewardCfgService.update(bizEvalRewardCfg);

		addMessage(redirectAttributes, "停用评价奖励设置成功");
		return "redirect:" + Global.getAdminPath() + "/bizevalrewardcfg/bizEvalRewardCfg/loadList";
	}

	@RequestMapping(value = "checkIsEnabledNo")
	public @ResponseBody String checkIsEnabledNo(Integer id) {
		BizEvalRewardCfg bizEvalRewardCfg = bizEvalRewardCfgService.get(id);
		List<Integer> list = bizEvalRewardTaskpackTempService.queryTaskpackTempIdByEvalRewardCfgId(id);
		Integer count = bizEvalRewardCfgService.queryCountByCondition(bizEvalRewardCfg, list);
		if (count > 0) {
			return "1";
		}
		return "0";
	}

	@RequiresPermissions("bizevalrewardcfg:bizEvalRewardCfg:edit")
	@RequestMapping(value = "isEnabledNo")
	public String isEnabledNo(Integer id, RedirectAttributes redirectAttributes) {
		BizEvalRewardCfg bizEvalRewardCfg = bizEvalRewardCfgService.get(id);
		bizEvalRewardCfg.setIsEnabled(ConstantUtils.IS_ENABLED_1);
		bizEvalRewardCfg.setUpdateBy(UserUtils.getUser());
		bizEvalRewardCfg.setUpdateDate(new Date());
		bizEvalRewardCfgService.update(bizEvalRewardCfg);

		addMessage(redirectAttributes, "启用评价奖励设置成功");
		return "redirect:" + Global.getAdminPath() + "/bizevalrewardcfg/bizEvalRewardCfg/loadList";
	}
}