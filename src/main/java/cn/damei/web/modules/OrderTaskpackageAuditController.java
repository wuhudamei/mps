
package cn.damei.web.modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.utils.phoneMessage.MessageEmployeePhoneAndId;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Worker.TaskPackagePicture;
import cn.damei.service.mobile.Worker.TaskPackagePictureService;
import cn.damei.entity.modules.BizMsg;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.entity.modules.OrderTaskpackVo;
import cn.damei.service.modules.OrderTaskpackServiceVo;
import cn.damei.entity.modules.BizOrderTaskpackage;
import cn.damei.entity.modules.OrderTaskpackageAudit;
import cn.damei.entity.modules.TaskpackageProceduces;
import cn.damei.entity.modules.UrgeCheck;
import cn.damei.service.modules.BizOrderTaskpackageService;
import cn.damei.service.modules.OrderTaskpackageAuditService;
import cn.damei.entity.modules.OrderTaskpackageProcedure;
import cn.damei.service.modules.OrderTaskpackageProcedureService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.entity.modules.BizProcedure;
import cn.damei.service.modules.BizProcedureService;
import cn.damei.entity.modules.Dict;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.entity.modules.BizTaskPackageTemplatBugetAmount;
import cn.damei.entity.modules.BizTaskPackageTemplatRel;
import cn.damei.service.modules.BizTaskPackageTemplatBugetAmountService;
import cn.damei.service.modules.BizTaskPackageTemplatRelService;
import cn.damei.service.modules.BizTaskPackageTemplatService;


@Controller
@RequestMapping(value = "${adminPath}/ordertaskpackageaudit/orderTaskpackageAudit")
public class OrderTaskpackageAuditController extends BaseController {

	@Autowired
	private OrderTaskpackageAuditService orderTaskpackageAuditService;

	@Autowired
	private OrderTaskpackServiceVo orderTaskpackServiceVo;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private OrderTaskpackageProcedureService orderTaskpackageProcedureService;
	@Autowired
	private BizTaskPackageTemplatService bizTaskPackageTemplatService;
	@Autowired
	private BizTaskPackageTemplatRelService bizTaskPackageTemplatRelService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizProcedureService bizProcedureService;
	@Autowired
	private BizOrderTaskpackageService bizOrderTaskpackageService;
	@Autowired
	private TaskPackagePictureService taskPackagePictureService;

	@Autowired
	private BizTaskPackageTemplatBugetAmountService bizTaskPackageTemplatBugetAmountService;

	@Autowired
	private OrderService2 orderService2;

	@Autowired
	BizProjectChangeBillService bizProjectChangeBillService;

	@ModelAttribute
	public OrderTaskpackageAudit get(@RequestParam(required = false) String id) {
		OrderTaskpackageAudit entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = orderTaskpackageAuditService.get(id);
		}
		if (entity == null) {
			entity = new OrderTaskpackageAudit();
		}
		return entity;
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(OrderTaskpackageAudit orderTaskpackageAudit, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();

		if (null == orderTaskpackageAudit.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					orderTaskpackageAudit.setEnginDepartIds(list);
				} else {
					orderTaskpackageAudit.setEnginDepartIds(null);
				}
			} else {
				orderTaskpackageAudit.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(orderTaskpackageAudit.getEngineDepartId());
			orderTaskpackageAudit.setEnginDepartIds(list);
		}

		if (null == orderTaskpackageAudit.getStoreId()) {
			if (null != user.getStoreId()) {
				orderTaskpackageAudit.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(orderTaskpackageAudit.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackageAudit.setProjectMode(be.getProjectMode());
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
						orderTaskpackageAudit.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		List<String> packageStateids = orderTaskpackageAudit.getPackageStateids();
		if (null != packageStateids) {
			String statusStr = packageStateids.toString();
			statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
			orderTaskpackageAudit.setPackageStateid(statusStr);
		}
		List<Dict> dictListByType = orderTaskpackageAuditService.getPackageStateid(new Integer(20), new Integer(80));

		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("orderTaskpackageAudit", orderTaskpackageAudit);
		model.addAttribute("packageStateids", dictListByType);
		return "modules/ordertaskpackageaudit/orderTaskpackageAuditList";
	}


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = { "list", "" })
	public String list(OrderTaskpackageAudit orderTaskpackageAudit, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (null == orderTaskpackageAudit.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					orderTaskpackageAudit.setEnginDepartIds(list);
				} else {
					orderTaskpackageAudit.setEnginDepartIds(null);
				}
			} else {
				orderTaskpackageAudit.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(orderTaskpackageAudit.getEngineDepartId());
			orderTaskpackageAudit.setEnginDepartIds(list);
		}

		if (null == orderTaskpackageAudit.getStoreId()) {
			if (null != user.getStoreId()) {
				orderTaskpackageAudit.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(orderTaskpackageAudit.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackageAudit.setProjectMode(be.getProjectMode());
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
						orderTaskpackageAudit.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		List<String> packageStateids = orderTaskpackageAudit.getPackageStateids();
		if (null != packageStateids) {
			String statusStr = packageStateids.toString();
			statusStr = statusStr.replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
			orderTaskpackageAudit.setPackageStateid(statusStr);
		}
		List<Dict> dictListByType = orderTaskpackageAuditService.getPackageStateid(new Integer(20), new Integer(80));

		orderTaskpackageAudit.setStartPackageStateId(String.valueOf(20));
		orderTaskpackageAudit.setEndPackageStateId(String.valueOf(80));
		orderTaskpackageAudit.setPackageStateid(null);


		if (orderTaskpackageAudit != null) {
			if (orderTaskpackageAudit.getPackageStateids() != null && orderTaskpackageAudit.getPackageStateids().size() > 0) {
			} else {
				List<String> list = new ArrayList<String>();
				list.add("20");
				list.add("50");
				orderTaskpackageAudit.setPackageStateids(list);
			}
		}

		Page<OrderTaskpackageAudit> page = orderTaskpackageAuditService.myFindPage(new Page<OrderTaskpackageAudit>(request, response), orderTaskpackageAudit);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("size", page.getList().size());
		model.addAttribute("budget", ConstantUtils.ORDERTASKPACKAGE_AUDIT_SUCCESS);
		model.addAttribute("orderTaskpackageAudit", orderTaskpackageAudit);
		model.addAttribute("packageStateids", dictListByType);
		return "modules/ordertaskpackageaudit/orderTaskpackageAuditList";
	}


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = { "stayList", "" })
	public String stayList(OrderTaskpackageAudit orderTaskpackageAudit, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();

		if (null == orderTaskpackageAudit.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					orderTaskpackageAudit.setEnginDepartIds(list);
				} else {
					orderTaskpackageAudit.setEnginDepartIds(null);
				}
			} else {
				orderTaskpackageAudit.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(orderTaskpackageAudit.getEngineDepartId());
			orderTaskpackageAudit.setEnginDepartIds(list);
		}

		if (null == orderTaskpackageAudit.getStoreId()) {
			if (null != user.getStoreId()) {
				orderTaskpackageAudit.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(orderTaskpackageAudit.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackageAudit.setProjectMode(be.getProjectMode());
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
						orderTaskpackageAudit.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		orderTaskpackageAudit.setPackageStateid(String.valueOf(10));


		Integer stayCount = orderTaskpackageAuditService.getStayCountByStoreId(orderTaskpackageAudit);

		Page<OrderTaskpackageAudit> page = orderTaskpackageAuditService.findPage(new Page<OrderTaskpackageAudit>(request, response), orderTaskpackageAudit);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("size", page.getList().size());
		model.addAttribute("budget", ConstantUtils.ORDERTASKPACKAGE_AUDIT_SUCCESS);
		model.addAttribute("orderTaskpackageAudit", orderTaskpackageAudit);
		model.addAttribute("stayCount", stayCount);
		return "modules/ordertaskpackageaudit/orderTaskpackageStayAuditList";
	}


	@RequestMapping(value = "staySpectialList")
	public String staySpectialList(OrderTaskpackageAudit orderTaskpackageAudit, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();

		if (null == orderTaskpackageAudit.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					orderTaskpackageAudit.setEnginDepartIds(list);
				} else {
					orderTaskpackageAudit.setEnginDepartIds(null);
				}
			} else {
				orderTaskpackageAudit.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(orderTaskpackageAudit.getEngineDepartId());
			orderTaskpackageAudit.setEnginDepartIds(list);
		}

		if (null == orderTaskpackageAudit.getStoreId()) {
			if (null != user.getStoreId()) {
				orderTaskpackageAudit.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(orderTaskpackageAudit.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderTaskpackageAudit.setProjectMode(be.getProjectMode());
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
						orderTaskpackageAudit.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		orderTaskpackageAudit.setPackageStateid(String.valueOf(10));


		Integer stayCount = orderTaskpackageAuditService.getSpecialStayCountByStoreId(orderTaskpackageAudit);

		Page<OrderTaskpackageAudit> page = orderTaskpackageAuditService.findSpecialPage(new Page<OrderTaskpackageAudit>(request, response), orderTaskpackageAudit);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("size", page.getList().size());
		model.addAttribute("budget", ConstantUtils.ORDERTASKPACKAGE_AUDIT_SUCCESS);
		model.addAttribute("orderTaskpackageAudit", orderTaskpackageAudit);
		model.addAttribute("stayCount", stayCount);
		return "modules/ordertaskpackageaudit/orderTaskpackageSpecialStayAuditList";
	}


	@ResponseBody
	@RequestMapping(value = "packageNameList")
	public String packageNameList(String storeId, HttpServletRequest request) {
		List<DropModel> list = orderTaskpackageAuditService.findPackNameByStoreId(storeId);

		return JsonMapper.getInstance().toJson(list);
	}

	@RequestMapping(value = "form")
	public String form(OrderTaskpackageAudit orderTaskpackageAudit, Model model) {
		model.addAttribute("orderTaskpackageAudit", orderTaskpackageAudit);
		return "modules/ordertaskpackageaudit/orderTaskpackageAuditList";
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderTaskpackageAudit orderTaskpackageAudit, RedirectAttributes redirectAttributes) {
		orderTaskpackageAuditService.delete(orderTaskpackageAudit);
		addMessage(redirectAttributes, "删除订单任务包审核成功");
		return "redirect:" + Global.getAdminPath() + "/ordertaskpackageaudit/orderTaskpackageAudit/?repage";
	}


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = { "showView", "" })
	public String showView(OrderTaskpackageAudit orderTaskpackageAudit, String flag,String isSpecial, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<OrderTaskpackVo> list = new ArrayList<OrderTaskpackVo>();
		String orderId = request.getParameter("orderId");
		String procedureNo = request.getParameter("packCode");
		String id = request.getParameter("id");
		OrderTaskpackageAudit orderTaskpackage = orderTaskpackageAuditService.getById(id);

		list = orderTaskpackServiceVo.getByOrderIdAndTaskpacksgeId1(orderId, id);
		if(isSpecial == null){
			isSpecial = "0";
		}
		model.addAttribute("orderTaskpackage", orderTaskpackage);
		model.addAttribute("orderTaskpackageAuditList", list);
		model.addAttribute("flag", flag);
		model.addAttribute("isSpecial",isSpecial);
		return "modules/ordertaskpackageaudit/auditShowView";
	}





	@RequestMapping(value = "update")
	public @ResponseBody String update(OrderTaskpackageAudit orderTaskpackageAudit, HttpServletRequest request, String packageName) {

		String id = request.getParameter("id");
		String result = "0";
		logger.info("id======" + id + "\t任务包名称：" + packageName);
		OrderTaskpackageAudit audit = orderTaskpackageAuditService.getByIdOrEmployee(id);
		boolean flag = orderTaskpackageAuditService.updateOrderTaskpackageByPackageStatusIdReturn(ConstantUtils.ORDERTASKPACKAGE_AUDIT_SUCCESS_20, ConstantUtils.ORDERTASKPACKAGE_AUDIT_SUCCESS_20_VALUES, id, request);

		if (!flag) {
			result = "1";
		} else {
			if (audit != null) {

				BizPhoneMsg msg = new BizPhoneMsg();
				logger.info("项目经理电话：" + audit.getEmpPhone());
				msg.setId(null);
				msg.setReceivePhone(audit.getEmpPhone().trim());
				msg.setReceiveEmployeeId(audit.getEmpId());
				msg.setMsgContent("订单（" + audit.getCommunityName() + "-" + audit.getBuildNumber() + "-" + audit.getBuildUnit() + "-" + audit.getBuildRoom() + "-" + audit.getCustomerName() + "-" + audit.getCustomerPhone() + "）的任务包（" + packageName + "）已审核通过，您可登录APP查看订单详情。");
				msg.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
				msg.setMsgTosendDatetime(null);
				msg.setMsgSendedDatetime(null);
				msg.setMsgStatus("0");
				msg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200402);
				msg.setRelatedBusinessIdInt(Integer.valueOf(audit.getOrderId()));
				msg.setRelatedBusinessIdVarchar("");
				bizPhoneMsgService.save(msg);




				BizMsg bizMsg = new BizMsg();
				Date date = new Date();
				bizMsg.setMsgTitle("任务包审核通过");
				bizMsg.setMsgTime(date);
				bizMsg.setMsgContent("订单（" + audit.getCommunityName() + "-" + audit.getBuildNumber() + "-" + audit.getBuildUnit() + "-" + audit.getBuildRoom() + "-" + audit.getCustomerName() + "-" + audit.getCustomerPhone() + "）的任务包（" + packageName + "）已审核通过，请在【任务包查询】中查看详情。");
				bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
				bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100002001);

				bizMsg.setBusiIdInt(Integer.parseInt(id));
				bizMsg.setEmployeeId(audit.getEmpId());
				bizProjectChangeBillService.saveBizMsg(bizMsg);




				List<MessageEmployeePhoneAndId> diaoduList = messageDao.getEmployeePhoneAndId(Integer.parseInt(audit.getStoreId()), "6");

				if (diaoduList.size() > 0) {
					for (MessageEmployeePhoneAndId employee : diaoduList) {
						logger.info("工人调度员手机号：" + employee.getEmployeePhone());
						BizPhoneMsg ddMsg = new BizPhoneMsg();
						ddMsg.setId(null);
						ddMsg.setReceiveEmployeeId(employee.getEmployeeId());
						ddMsg.setMsgContent("订单（" + audit.getCommunityName() + "-" + audit.getBuildNumber() + "-" + audit.getBuildUnit() + "-" + audit.getBuildRoom() + "-" + audit.getCustomerName() + "-" + audit.getCustomerPhone() + "）的任务包（" + packageName + "）已审核通过，请及时分配工人。");
						ddMsg.setReceivePhone(employee.getEmployeePhone().trim());
						ddMsg.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
						ddMsg.setMsgTosendDatetime(null);
						ddMsg.setMsgSendedDatetime(null);
						ddMsg.setMsgStatus("0");
						ddMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200401);
						ddMsg.setRelatedBusinessIdInt(Integer.valueOf(audit.getId()));
						ddMsg.setRelatedBusinessIdVarchar("");
						bizPhoneMsgService.save(ddMsg);
					}
				}
			}
		}

		logger.info("预算员审核:返回结果为(0：成功；1失败)：" + result);
		return result;
	}

	@Autowired
	private PhoneMessageDao messageDao;


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "preUpdate")
	public String preUpdate(String orderId, String packCode, String taskpackageId, String flag,String isSpecial, Model model) {
		logger.info("订单编号：" + orderId + "\t 任务包模板编号：" + packCode + "\t 订单任务包编号(编号)：" + taskpackageId);
		Order2 order = orderService2.get(Integer.parseInt(orderId));
		OrderTaskpackageAudit orderTaskpackage = orderTaskpackageAuditService.getById(taskpackageId);
		List<OrderTaskpackVo> list = orderTaskpackServiceVo.getByOrderIdAndNo(orderId, taskpackageId);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeId", orderTaskpackage.getStoreId());
		param.put("projectMode", orderTaskpackage.getProjectMode());
		param.put("area", order.getContractArea());
		param.put("templatNumber", orderTaskpackage.getPackageCode());

		List<BizTaskPackageTemplatBugetAmount> templatBugetAmountList = bizTaskPackageTemplatBugetAmountService.queryTaskPackageTemplatByParam(param);

		if (templatBugetAmountList != null && templatBugetAmountList.size() > 0) {
			model.addAttribute("templatBugetAmountMax", templatBugetAmountList.get(0));
		}

		if(isSpecial ==  null){
			isSpecial = "0";
		}
		model.addAttribute("order", order);
		model.addAttribute("orderTaskpackage", orderTaskpackage);
		model.addAttribute("orderTaskpackageList", list);
		model.addAttribute("listSize", list.size());
		model.addAttribute("flag", flag);
		model.addAttribute("isSpecial", isSpecial);
		return "modules/ordertaskpackageaudit/preUpdateTaskpackage";
	}


	@RequestMapping(value = "checkTemplat")
	public @ResponseBody String checkTemplat(String packageCode){
		String result ="0";
		BizTaskPackageTemplat templat = bizTaskPackageTemplatService.getByNo(packageCode);
		if(templat == null || templat.getId() == null){
			result = "1";
		}
		return result;
	}


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "preInstallProcedure")
	public String preInstallProcedure(String packageCode, String orderId, String taskpackageId, String flag,String isSpecial, Model model) {
		List<String> list = new ArrayList<String>();
		List<String> procedureList = new ArrayList<String>();
		List<BizTaskPackageTemplatRel> templatRelList = null;
		List<BizProcedure> proList = null;
		BizTaskPackageTemplat templat = null;

		List<OrderTaskpackageProcedure> orderTaskpackageProList = orderTaskpackageProcedureService.getByTaskpackageId(Integer.valueOf(taskpackageId));
		for (OrderTaskpackageProcedure p : orderTaskpackageProList) {
			list.add(p.getProcedureNo());
		}

		if (list.size() > 0) {
			templat = bizTaskPackageTemplatService.getByNo(packageCode);

			templatRelList = bizTaskPackageTemplatRelService.getByProcedureNo(templat.getId(), list);
		}

		if (templatRelList != null && templatRelList.size() > 0) {
			for (BizTaskPackageTemplatRel procedure : templatRelList) {
				procedureList.add(procedure.getProcedureNo());
			}
			proList = bizProcedureService.getByBatchProcedureNo(procedureList);
		} else {
			templatRelList = new ArrayList<BizTaskPackageTemplatRel>();
		}

		if(isSpecial == null){
			isSpecial ="0";
		}
		model.addAttribute("orderId", orderId);
		model.addAttribute("templat", templat);
		model.addAttribute("taskpackageId", taskpackageId);
		model.addAttribute("templatRelList", templatRelList);
		model.addAttribute("procedureList", proList);
		model.addAttribute("orderTaskpackageProList", orderTaskpackageProList);
		model.addAttribute("flag", flag);
		model.addAttribute("isSpecial", isSpecial);
		return "modules/ordertaskpackageaudit/preInstallProcedure";
	}




	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "taskpackageListPage")
	public String taskpackageListPage(BizOrderTaskpackage bizOrderTaskpackage, Model model) {
		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("bizOrderTaskpackage", bizOrderTaskpackage);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackageaudit/taskpackageListPage";
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "taskpackageList")
	public String taskpackageList(BizOrderTaskpackage bizOrderTaskpackage, HttpServletRequest request, HttpServletResponse response, Model model, String[] status) throws IOException {


		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}

		User user = UserUtils.getUser();

		if (null == bizOrderTaskpackage.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderTaskpackage.setStoreId(Integer.parseInt(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		bizOrderTaskpackage.setStatusList(null);
		if (bizOrderTaskpackage.getPackageStateId() != null) {
			String[] split = bizOrderTaskpackage.getPackageStateId().split(",");
			if (split != null) {
				bizOrderTaskpackage.setStatusList(Arrays.asList(split));
			}
		}
		Page<BizOrderTaskpackage> page = bizOrderTaskpackageService.findPage(new Page<BizOrderTaskpackage>(request, response), bizOrderTaskpackage);


		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("page", page);
		model.addAttribute("bizOrderTaskpackage", bizOrderTaskpackage);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackageaudit/taskpackageListPage";
	}


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "waitAllotTaskpackagePage")
	public String waitAllotTaskpackagePage(BizOrderTaskpackage bizOrderTaskpackage, Model model) {

		return "modules/ordertaskpackageaudit/waitAllotTaskpackagePage";
	}






	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "waitAllotTaskpackage")
	public String waitAllotTaskpackage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {

		String isZero = bizOrderTaskpackage.getIsZero();
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}
		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		List<String> statusList = bizOrderTaskpackage.getStatusList();
		if ("".equals(bizOrderTaskpackage.getPackageStateId()) || bizOrderTaskpackage.getPackageStateId() == null) {

			statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_20);

			statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_55);
		} else {
			statusList.add(bizOrderTaskpackage.getPackageStateId());
		}


		Page<BizOrderTaskpackage> page = bizOrderTaskpackageService.findPage1(new Page<BizOrderTaskpackage>(request, response), bizOrderTaskpackage);

		model.addAttribute("page", page);
		model.addAttribute("isZero", isZero);
		return "modules/ordertaskpackageaudit/waitAllotTaskpackagePage";
	}


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "allotedTaskpackagePage")
	public String allotedTaskpackagePage(BizOrderTaskpackage bizOrderTaskpackage, Model model) {
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/ordertaskpackageaudit/allotedTaskpackagePage";
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "allotedTaskpackage")
	public String allotedTaskpackage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {

		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		Page<BizOrderTaskpackage> page = bizOrderTaskpackageService.findPage7(new Page<BizOrderTaskpackage>(request, response), bizOrderTaskpackage);
		model.addAttribute("page", page);
		return "modules/ordertaskpackageaudit/allotedTaskpackagePage";
	}


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "completedTaskpackagePage")
	public String completedTaskpackagePage(BizOrderTaskpackage bizOrderTaskpackage, Model model) {

		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackageaudit/completedTaskpackagePage";
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "completedTaskpackage")
	public String completedTaskpackage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {

		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}
		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		List<String> statusList = bizOrderTaskpackage.getStatusList();
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_80);
		Page<BizOrderTaskpackage> page = bizOrderTaskpackageService.findPage1(new Page<BizOrderTaskpackage>(request, response), bizOrderTaskpackage);








		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizOrderTaskpackage", bizOrderTaskpackage);
		return "modules/ordertaskpackageaudit/completedTaskpackagePage";
	}



	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "discompletedTaskpackagePage")
	public String discompletedTaskpackagePage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {

		User user = UserUtils.getUser();

		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}


		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackageaudit/discompletedTaskpackagePage";
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "discompletedTaskpackage")
	public String discompletedTaskpackage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {


		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}
		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		List<String> statusList = bizOrderTaskpackage.getStatusList();
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_70);
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_60);
		if (bizOrderTaskpackage.getPackageStateId() != null) {
			bizOrderTaskpackage.setStatusList(Arrays.asList(bizOrderTaskpackage.getPackageStateId().split(",")));
		}
		Page<BizOrderTaskpackage> page = bizOrderTaskpackageService.findPage2(new Page<BizOrderTaskpackage>(request, response), bizOrderTaskpackage);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizOrderTaskpackage", bizOrderTaskpackage);
		return "modules/ordertaskpackageaudit/discompletedTaskpackagePage";
	}


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "unsignTaskpackagePage")
	public String unsignTaskpackagePage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {

		User user = UserUtils.getUser();

		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}


		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackageaudit/unsignTaskpackagePage";
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "unsignTaskpackage")
	public String unsignTaskpackage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {

		User user = UserUtils.getUser();

		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}

		if (bizOrderTaskpackage.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackage.setStoreId(null);
			} else {
				bizOrderTaskpackage.setStoreId(Integer.parseInt(storeId));
			}
		}

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		List<String> statusList = bizOrderTaskpackage.getStatusList();
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_60);

		Page<BizOrderTaskpackage> page = bizOrderTaskpackageService.findPage3(new Page<BizOrderTaskpackage>(request, response), bizOrderTaskpackage);

		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizOrderTaskpackage", bizOrderTaskpackage);
		return "modules/ordertaskpackageaudit/unsignTaskpackagePage";
	}



	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "unacceptanceTaskpackagePage")
	public String unacceptanceTaskpackagePage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {


		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}
		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackageaudit/unacceptanceTaskpackagePage";
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "unacceptanceTaskpackage")
	public String unacceptanceTaskpackage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {


		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		List<String> statusList = bizOrderTaskpackage.getStatusList();
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_80);

		Page<BizOrderTaskpackage> page = bizOrderTaskpackageService.findPage4(new Page<BizOrderTaskpackage>(request, response), bizOrderTaskpackage);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizOrderTaskpackage", bizOrderTaskpackage);
		model.addAttribute("page", page);
		return "modules/ordertaskpackageaudit/unacceptanceTaskpackagePage";
	}



	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "urgeCheckPage")
	public String urgeCheckPage(UrgeCheck urgeCheck, Model model, HttpServletRequest request, HttpServletResponse response) {

		if (null == urgeCheck.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					urgeCheck.setEnginDepartIds(list);
				} else {
					urgeCheck.setEnginDepartIds(null);
				}
			} else {
				urgeCheck.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(urgeCheck.getEngineDepartId());
			urgeCheck.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			urgeCheck.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (urgeCheck.getStoreId() != null && urgeCheck.getStoreId() == 1) {

				urgeCheck.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(urgeCheck.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						urgeCheck.setProjectMode(be.getProjectMode());
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
						urgeCheck.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackageaudit/urgeCheckPage";
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "urgeCheckList")
	public String urgeCheckList(UrgeCheck urgeCheck, Model model, HttpServletRequest request, HttpServletResponse response) {

		if (null == urgeCheck.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					urgeCheck.setEnginDepartIds(list);
				} else {
					urgeCheck.setEnginDepartIds(null);
				}
			} else {
				urgeCheck.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(urgeCheck.getEngineDepartId());
			urgeCheck.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			urgeCheck.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (urgeCheck.getStoreId() != null && urgeCheck.getStoreId() == 1) {

				urgeCheck.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(urgeCheck.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						urgeCheck.setProjectMode(be.getProjectMode());
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
						urgeCheck.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		Page<UrgeCheck> page = bizOrderTaskpackageService.findPage5(new Page<UrgeCheck>(request, response), urgeCheck);

		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("urgeCheck", urgeCheck);
		return "modules/ordertaskpackageaudit/urgeCheckPage";
	}


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "recheckTaskpackagePage")
	public String recheckTaskpackagePage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {


		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/ordertaskpackageaudit/recheckTaskpackagePage";
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "recheckTaskpackage")
	public String recheckTaskpackage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {


		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		List<String> statusList = bizOrderTaskpackage.getStatusList();
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_90);
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_95);
		if (bizOrderTaskpackage.getPackageStateId() != null) {
			bizOrderTaskpackage.setStatusList(Arrays.asList(bizOrderTaskpackage.getPackageStateId().split(",")));
		}
		Page<BizOrderTaskpackage> page = bizOrderTaskpackageService.findPage6(new Page<BizOrderTaskpackage>(request, response), bizOrderTaskpackage);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizOrderTaskpackage", bizOrderTaskpackage);
		return "modules/ordertaskpackageaudit/recheckTaskpackagePage";
	}


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "waitModifyTaskpackagePage")
	public String waitModifyTaskpackagePage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {


		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackageaudit/waitModifyTaskpackagePage";
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "waitModifyTaskpackage")
	public String waitModifyTaskpackage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {


		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		List<String> statusList = bizOrderTaskpackage.getStatusList();
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_130);
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_110);
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_95);
		if (bizOrderTaskpackage.getPackageStateId() != null) {
			bizOrderTaskpackage.setStatusList(Arrays.asList(bizOrderTaskpackage.getPackageStateId().split(",")));
		}
		Page<BizOrderTaskpackage> page = bizOrderTaskpackageService.findPage6(new Page<BizOrderTaskpackage>(request, response), bizOrderTaskpackage);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizOrderTaskpackage", bizOrderTaskpackage);
		return "modules/ordertaskpackageaudit/waitModifyTaskpackagePage";
	}


	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "waitConfirmSalaryPage")
	public String waitConfirmSalaryPage(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {


		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackageaudit/waitConfirmSalaryPage";
	}

	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "waitConfirmSalary")
	public String waitConfirmSalary(BizOrderTaskpackage bizOrderTaskpackage, Model model, HttpServletRequest request, HttpServletResponse response) {


		if (null == bizOrderTaskpackage.getEngineDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackage.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackage.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackage.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackage.getEngineDepartId());
			bizOrderTaskpackage.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {

			bizOrderTaskpackage.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {

			if (bizOrderTaskpackage.getStoreId() != null && bizOrderTaskpackage.getStoreId() == 1) {

				bizOrderTaskpackage.setStoreId(null);
			}
		}

		User user = UserUtils.getUser();

		if (StringUtils.isBlank(bizOrderTaskpackage.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackage.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		List<String> statusList = bizOrderTaskpackage.getStatusList();
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_100);
		statusList.add(ConstantUtils.ORDER_TASKPACKAGE_STATUS_105);
		if (bizOrderTaskpackage.getPackageStateId() != null) {
			bizOrderTaskpackage.setStatusList(Arrays.asList(bizOrderTaskpackage.getPackageStateId().split(",")));
		}
		Page<BizOrderTaskpackage> page = bizOrderTaskpackageService.findPage6(new Page<BizOrderTaskpackage>(request, response), bizOrderTaskpackage);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizOrderTaskpackage", bizOrderTaskpackage);
		return "modules/ordertaskpackageaudit/waitConfirmSalaryPage";
	}



	@RequiresPermissions("ordertaskpackageaudit:orderTaskpackageAudit:view")
	@RequestMapping(value = "procedureDetail")
	public String procedureDetail(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {

		List<TaskpackageProceduces> list = bizOrderTaskpackageService.queryProceduresByPackageId(id);
		OrderTaskpackageAudit orderTaskpackage = orderTaskpackageAuditService.getById(String.valueOf(id));
		model.addAttribute("list", list);
		model.addAttribute("orderTaskpackage", orderTaskpackage);
		return "modules/ordertaskpackageaudit/detail";
	}



	@RequestMapping(value = "/completedPhoto")
	@ResponseBody
	public Map<Object, Object> completedPhoto(Integer id, Model model) throws IOException {
		List<TaskPackagePicture> pictures = taskPackagePictureService.findPicturesByPackageId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("pictures", pictures);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);


		return mapObject;
	}


	@RequestMapping(value = "/ajaxCompletedPhoto")
	@ResponseBody
	public Map<Object, Object> ajaxCompletedPhoto(Integer id, Model model) throws IOException {
		List<TaskPackagePicture> pictures = taskPackagePictureService.findPicturesByPackageId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("pictures", pictures);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);

		return mapObject;
	}

}