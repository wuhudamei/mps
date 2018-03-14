package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizOrderFinanceCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.BizOrderFinanceCollectionService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/bizOrderFinanceCollection/bizOrderFinanceCollection")
public class BizOrderFinanceCollectionController extends BaseController {

	@Autowired
	private BizOrderFinanceCollectionService bizOrderFinanceCollectionService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@RequestMapping(value = "openBizOrderFinanceCollectionList")
	public String openBizOrderFinanceCollectionList(BizOrderFinanceCollection bizOrderFinanceCollection,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (null == bizOrderFinanceCollection.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderFinanceCollection.setEnginDepartIds(list);
				} else {
					bizOrderFinanceCollection.setEnginDepartIds(null);
				}
			} else {
				bizOrderFinanceCollection.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderFinanceCollection.getEnginDepartId());
			bizOrderFinanceCollection.setEnginDepartIds(list);
		}

		if (null == bizOrderFinanceCollection.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderFinanceCollection.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(bizOrderFinanceCollection.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderFinanceCollection.setProjectMode(be.getProjectMode());
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
						bizOrderFinanceCollection.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		return "modules/bizOrderFinanceCollection/bizOrderFinanceCollectionList";
	}

	@RequestMapping(value = "queryBizOrderFinanceCollectionList")
	public String queryBizOrderFinanceCollectionList(BizOrderFinanceCollection bizOrderFinanceCollection,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (null == bizOrderFinanceCollection.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderFinanceCollection.setEnginDepartIds(list);
				} else {
					bizOrderFinanceCollection.setEnginDepartIds(null);
				}
			} else {
				bizOrderFinanceCollection.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderFinanceCollection.getEnginDepartId());
			bizOrderFinanceCollection.setEnginDepartIds(list);
		}

		if (null == bizOrderFinanceCollection.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderFinanceCollection.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(bizOrderFinanceCollection.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderFinanceCollection.setProjectMode(be.getProjectMode());
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
						bizOrderFinanceCollection.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		if (bizOrderFinanceCollection.getCollectionStatusList() != null) {
			String str = null;
			for (String status : bizOrderFinanceCollection.getCollectionStatusList()) {
				if (str == null) {
					str = status;
				} else {
					str = str + "," + status;
				}
			}
			bizOrderFinanceCollection.setCollectionStatusStrs(str);
		}
		Page<BizOrderFinanceCollection> page = bizOrderFinanceCollectionService
				.findPage(new Page<BizOrderFinanceCollection>(request, response), bizOrderFinanceCollection);
		model.addAttribute("page", page);
		return "modules/bizOrderFinanceCollection/bizOrderFinanceCollectionList";
	}

	@RequestMapping(value = "openBizPrePmSettleFinList")
	public String openBizPrePmSettleFinList(BizOrderFinanceCollection bizOrderFinanceCollection,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (null == bizOrderFinanceCollection.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderFinanceCollection.setEnginDepartIds(list);
				} else {
					bizOrderFinanceCollection.setEnginDepartIds(null);
				}
			} else {
				bizOrderFinanceCollection.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderFinanceCollection.getEnginDepartId());
			bizOrderFinanceCollection.setEnginDepartIds(list);
		}

		if (null == bizOrderFinanceCollection.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderFinanceCollection.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(bizOrderFinanceCollection.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderFinanceCollection.setProjectMode(be.getProjectMode());
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
						bizOrderFinanceCollection.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/bizOrderFinanceCollection/bizPrePmSettleFinList";
	}

	@RequestMapping(value = "queryfindListPrePmSettleFinList")
	public String findListPrePmSettleFinList(BizOrderFinanceCollection bizOrderFinanceCollection,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (null == bizOrderFinanceCollection.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderFinanceCollection.setEnginDepartIds(list);
				} else {
					bizOrderFinanceCollection.setEnginDepartIds(null);
				}
			} else {
				bizOrderFinanceCollection.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderFinanceCollection.getEnginDepartId());
			bizOrderFinanceCollection.setEnginDepartIds(list);
		}

		if (null == bizOrderFinanceCollection.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderFinanceCollection.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(bizOrderFinanceCollection.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderFinanceCollection.setProjectMode(be.getProjectMode());
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
						bizOrderFinanceCollection.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		if (bizOrderFinanceCollection.getCollectionStatusList() != null) {
			String str = null;
			for (String status : bizOrderFinanceCollection.getCollectionStatusList()) {
				if (str == null) {
					str = status;
				} else {
					str = str + "," + status;
				}
			}
			bizOrderFinanceCollection.setCollectionStatusStrs(str);
		}
		
		Page<BizOrderFinanceCollection> page = bizOrderFinanceCollectionService.findListPrePmSettleFinList(new Page<BizOrderFinanceCollection>(request, response), bizOrderFinanceCollection);
		model.addAttribute("page", page);
		return "modules/bizOrderFinanceCollection/bizPrePmSettleFinList";
	}
}
