package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.Quartz.OrderComplaintMsgAndPhoneQuartz;

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
import cn.damei.common.constantUtils.EmployeeContantUtil;
import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.SpringContextHolder;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.BizPrepareOrderService;
import cn.damei.entity.modules.BizCusServiceProblem;
import cn.damei.dao.modules.BizCustomerReturnVisitOrderEnableDao;
import cn.damei.service.modules.BizCustomerReturnVisitRecordService;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.Order;
import cn.damei.entity.modules.OrderInstallItemVo;
import cn.damei.service.modules.OrderService;
import cn.damei.common.utils.UserUtils;

/**
 * 订单管理Controller
 * 
 * @author wyb
 * @version 2016-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/order/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private BizPrepareOrderService bizPrepareOrderService;
	@Autowired
	private OrderComplaintMsgAndPhoneQuartz quartz;
	
	@Autowired
	BizCustomerReturnVisitOrderEnableDao bizCustomerReturnVisitOrderEnableDao;
	
	@Autowired
	BizCustomerReturnVisitRecordService bizCustomerReturnVisitRecordService;

	@ModelAttribute
	public Order get(@RequestParam(required = false) String id) {
		Order entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = orderService.get(id);
		}
		if (entity == null) {
			entity = new Order();
		}
		return entity;
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "list", "" })
	public String list(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {
		//订单--是否作废--> 0:否
		order.setIsScrap(OrderConstantUtil.ORDER_IS_SCRAP_NO_0);
		return "modules/order/orderList";
	}
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = {"execute"})
	public void execute(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {
		quartz.execute();
	}

	/**
	 * 根据客户姓名/手机号/订单编号查询订单
	 * 
	 * @Title: listProject
	 * @Description: TODO
	 * @param @param bizCusServiceProblem
	 * @param @param order
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @param oNumAndcusNameIph
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = { "listProject", "" })
	public String listProject(BizCusServiceProblem bizCusServiceProblem, Order order, HttpServletRequest request, HttpServletResponse response, Model model, String oNumAndcusNameIph) {
		order.setAcceptArea(bizCusServiceProblem.getWorkOrderCode());
		Page<BizCusServiceProblem> findList = orderService.findlistProject(new Page<BizCusServiceProblem>(request, response), bizCusServiceProblem);
		model.addAttribute("page", findList);
		return "modules/cusserviceproblem/bizCusServiceProblemProjectV";
	}

	@RequestMapping(value = "listProjectaflt")
	public String listProjectaflt(BizCusServiceProblem bizCusServiceProblem, Order order, HttpServletRequest request, HttpServletResponse response, Model model, String oNumAndcusNameIph) {
		order.setAcceptArea(bizCusServiceProblem.getWorkOrderCode());
		Page<BizCusServiceProblem> findList = orderService.findlistProject(new Page<BizCusServiceProblem>(request, response), bizCusServiceProblem);
		model.addAttribute("page", findList);
		model.addAttribute("bizCusServiceProblem", bizCusServiceProblem);
		return "modules/cusserviceproblem/CusServicecalft";
	}

	private static BizEmployeeDao employeeDao = SpringContextHolder.getBean(BizEmployeeDao.class);

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "list1" })
	public String list1(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (null != order.getAcceptOrderDate()) {

			model.addAttribute("acceptOrderDate", order.getAcceptOrderDate());
		}

		int x = 0;
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			order.setStoreId(UserUtils.getUser().getStoreId());
			x++;
		}
		String loginUserEmpId = UserUtils.getUser().getEmpId();
		if (null != loginUserEmpId) {
			// 根据员工id 查询工程区域
			List<Integer> list = employeeDao.findEngineIdsByEmpId(Integer.parseInt(loginUserEmpId));
			if (null != list && list.size() > 0) {

				if (null == order.getEngineDepartId()) {

					order.setEmpEngineDepartIds(list);
				}

			}
		}

		Page<Order> page = orderService.findPage(new Page<Order>(request, response), order);
		
		if (x > 0) {
			order.setStoreId(null);

		}
		model.addAttribute("page", page);
		return "modules/order/orderList";
	}
	/**
	 * 停用
	 * @return
	 */
	@RequestMapping(value = "enable")
	public String enable(Integer id,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("orderId"+id);
		Integer a=bizCustomerReturnVisitOrderEnableDao.selectCount(id);
		System.out.println("这是AAAA========"+a);
		if(a>0){
			bizCustomerReturnVisitRecordService.updateStop(id);
		}else{
			bizCustomerReturnVisitRecordService.saveStop(id);
		}
		return "redirect:" + Global.getAdminPath() + "/order/order/list1";
	}
	/**
	 * 启用
	 * @param bizTaskPackageType
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "able")
	public String able(Integer id,HttpServletResponse response,HttpServletRequest request ) {
		System.out.println("orderId"+id);
		bizCustomerReturnVisitRecordService.deleteStop(id);
		return "redirect:" + Global.getAdminPath() + "/order/order/list1";
	}
	/**
	 * 订单修改, 涉及 订单的修改和 安装项的 业务
	 * 
	 * @param order
	 * @param model
	 * @return
	 */
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "form")
	public String form(Order order, Model model) {
		// 根据门店和产业模式 及当前用户查询 工程区域
		String loginUserEmpId = UserUtils.getUser().getEmpId();
		order.setEmpId(loginUserEmpId);
		List<Order> engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);
		if (engineList.size() == 0) {
			order.setEmpId(null);
			engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);

		}

		//设计师列表
		List<BizEmployee> employeeListByType = bizPrepareOrderService.getEmployeeListByType(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_5);
		//审计员列表
		List<BizEmployee> auditorList = bizPrepareOrderService.getEmployeeListByType(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_8);
		
		model.addAttribute("empList", employeeListByType);
		model.addAttribute("auditorList", auditorList);
		model.addAttribute("engineList", engineList);

		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());

		List<Order> list = orderService.getAcceptAreaForOrder(order);
		// Sasiki ----->: 修改
		if (null != order.getOrderId()) {
			// 对应的接单区域

			if (null != list && list.size() > 0) {
				for (Order order2 : list) {
					if (null != order.getAcceptAreaId()) {
						if (order.getAcceptAreaId().equals(order2.getAcceptAreaId())) {

							order.setAcceptArea(order2.getAcceptArea());
						}
					}
				}
			}
			// Sasiki 修改 是根据orderid 查询对应的安装项, 回显

			List<OrderInstallItemVo> installItems = null;
			List<OrderInstallItemVo> installItemByStoreId = null;
			// 该门店下工程模式的所有安装项(模板)
			installItemByStoreId = orderService.findInstallItemByStoreId(order);
			// 该订单下的所有安装项
			installItems = orderService.findInstallItemByOrderId(order.getOrderId());

			// ORDERSTATUS:如果订单状态大于等于200
			if (Integer.parseInt(order.getOrderStatusNumber()) >= 200) {
				// ORDERSTATUS----->: 要判断订单下安装项计划的状态是否为 2,3 4如果为2 3 4 不允许修改,
				// 为1时,可以修改

				// 1: 查询该订单下的安装项的状态
				List<OrderInstallItemVo> planStatus = orderService.findOrderInstallItemPlanStatus(order.getOrderId());
				if (null != planStatus && planStatus.size() > 0)
					for (OrderInstallItemVo orderInstallItemVo : planStatus) {
						if (orderInstallItemVo.getStatus().equals("2") || orderInstallItemVo.getStatus().equals("3") || orderInstallItemVo.getStatus().equals("4")) {
							// 2: 如果安装项计划中的安装项状态为 2 或者3 4,不允许修改

							h: for (OrderInstallItemVo vo : installItems) {
								if (vo.getId().equals(orderInstallItemVo.getId())) {

									for (OrderInstallItemVo v : installItemByStoreId) {
										// 把这个安装项加个标识
										if (v.getProjectInstallItemId().equals(vo.getProjectInstallItemId())) {
											v.setStatus("1");
											break h;

										}

									}

								}

							}
						} else {
							// 2--->3: 如果为1 可以修改

						}
					}

			}
			// ORDERSTATUS:如果订单状态小于200 或者 安装项为1 允许随便改
			// Naruto1 集合
			List<OrderInstallItemVo> naruto = new ArrayList<OrderInstallItemVo>();

			// 进行笛卡尔积比较

			// 该订单下的安装项
			for (OrderInstallItemVo itemVo : installItems) {
				// Naruto1---->:用于判断 是否存在 模板中已经删除的,但删除前该订单已经申请了的安装项
				// 总得模板
				for (OrderInstallItemVo orderInstallItemVo : installItemByStoreId) {

					if (itemVo.getProjectInstallItemId().equals(orderInstallItemVo.getProjectInstallItemId())) {
						// 如果一样 标记1
						orderInstallItemVo.setIsChoosed("1");
						itemVo.setIsChoosed("1");
						break;

					} else {
						// 不一样, 也不能覆盖之前一样的
						if (null == orderInstallItemVo.getIsChoosed() || !orderInstallItemVo.getIsChoosed().equals("1")) {

							// 不一样 标记0
							orderInstallItemVo.setIsChoosed("0");

						}

					}

				}
				// 如果遍历一圈,还没有把该订单下的安装项设置成 isChoosed =="1" 那么表示Naruto1 成立
				if (null == itemVo.getIsChoosed()) {
					itemVo.setIsChoosed("1");

					// 如果该安装项的计划中是已转给供应商,已验收, 则不可修改, 不然可修改
					List<OrderInstallItemVo> status = orderService.findOrderInstallItemPlanStatus(order.getOrderId());
					if (status.size() > 0) {

						for (OrderInstallItemVo orderInstallItemVo : status) {
							if (itemVo.getId().equals(orderInstallItemVo.getId())) {

								if (Integer.parseInt(orderInstallItemVo.getStatus()) > 1) {
									itemVo.setStatus("1");
									break;
								} else {
									itemVo.setStatus("0");
									break;
								}

							}

						}
					}

					naruto.add(itemVo);
				}

			}

			// Naruto

			if (naruto.size() > 0) {

				for (OrderInstallItemVo orderInstallItemVo : naruto) {

					installItemByStoreId.add(orderInstallItemVo);

				}
			}

			// 以供回显
			model.addAttribute("installItemList", installItemByStoreId);

		} else {
			// 新增
		}

		// 接单区域, id和name
		model.addAttribute("acceptAreaList", list);
		model.addAttribute("order", order);

		// TODO 临时解决
		if (order.getId() != null) {
			return "modules/order/orderFormUser";
		} else {
			return "modules/order/orderForm";

		}
	}

	/**
	 * 订单修改, 涉及 订单的修改和 安装项的 业务
	 * 
	 * @param order
	 * @param model
	 * @return
	 */
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "formUser")
	public String formUser(Order order, Model model) {
		// 根据门店和产业模式 及当前用户查询 工程区域
		String loginUserEmpId = UserUtils.getUser().getEmpId();
		order.setEmpId(loginUserEmpId);
		List<Order> engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);
		if (engineList.size() == 0) {
			order.setEmpId(null);
			engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);

		}
		model.addAttribute("engineList", engineList);

		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());

		
		//设计师列表
		List<BizEmployee> employeeListByType = bizPrepareOrderService.getEmployeeListByType(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_5);
		//审计员列表
		List<BizEmployee> auditorList = bizPrepareOrderService.getEmployeeListByType(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_8);
		
		model.addAttribute("empList", employeeListByType);
		model.addAttribute("auditorList", auditorList);
				
		List<Order> list = orderService.getAcceptAreaForOrder(order);
		// Sasiki ----->: 修改
		if (null != order.getOrderId()) {
			// 对应的接单区域

			if (null != list && list.size() > 0) {
				for (Order order2 : list) {
					if (null != order.getAcceptAreaId()) {
						if (order.getAcceptAreaId().equals(order2.getAcceptAreaId())) {

							order.setAcceptArea(order2.getAcceptArea());
						}
					}
				}
			}
			// Sasiki 修改 是根据orderid 查询对应的安装项, 回显

			List<OrderInstallItemVo> installItems = null;
			List<OrderInstallItemVo> installItemByStoreId = null;
			// 该门店下工程模式的所有安装项(模板)
			installItemByStoreId = orderService.findInstallItemByStoreId(order);
			// 该订单下的所有安装项
			installItems = orderService.findInstallItemByOrderId(order.getOrderId());

			// ORDERSTATUS:如果订单状态大于等于200
			if (Integer.parseInt(order.getOrderStatusNumber()) >= 200) {
				// ORDERSTATUS----->: 要判断订单下安装项计划的状态是否为 2,3 4如果为2 3 4 不允许修改,
				// 为1时,可以修改

				// 1: 查询该订单下的安装项的状态
				List<OrderInstallItemVo> planStatus = orderService.findOrderInstallItemPlanStatus(order.getOrderId());
				if (null != planStatus && planStatus.size() > 0)
					for (OrderInstallItemVo orderInstallItemVo : planStatus) {
						if (orderInstallItemVo.getStatus().equals("2") || orderInstallItemVo.getStatus().equals("3") || orderInstallItemVo.getStatus().equals("4")) {
							// 2: 如果安装项计划中的安装项状态为 2 或者3 4,不允许修改

							h: for (OrderInstallItemVo vo : installItems) {
								if (vo.getId().equals(orderInstallItemVo.getId())) {

									for (OrderInstallItemVo v : installItemByStoreId) {
										// 把这个安装项加个标识
										if (v.getProjectInstallItemId().equals(vo.getProjectInstallItemId())) {
											v.setStatus("1");
											break h;

										}

									}

								}

							}
						} else {
							// 2--->3: 如果为1 可以修改

						}
					}

			}
			// ORDERSTATUS:如果订单状态小于200 或者 安装项为1 允许随便改
			// Naruto1 集合
			List<OrderInstallItemVo> naruto = new ArrayList<OrderInstallItemVo>();

			// 进行笛卡尔积比较

			// 该订单下的安装项
			for (OrderInstallItemVo itemVo : installItems) {
				// Naruto1---->:用于判断 是否存在 模板中已经删除的,但删除前该订单已经申请了的安装项
				// 总得模板
				for (OrderInstallItemVo orderInstallItemVo : installItemByStoreId) {

					if (itemVo.getProjectInstallItemId().equals(orderInstallItemVo.getProjectInstallItemId())) {
						// 如果一样 标记1
						orderInstallItemVo.setIsChoosed("1");
						itemVo.setIsChoosed("1");
						break;

					} else {
						// 不一样, 也不能覆盖之前一样的
						if (null == orderInstallItemVo.getIsChoosed() || !orderInstallItemVo.getIsChoosed().equals("1")) {

							// 不一样 标记0
							orderInstallItemVo.setIsChoosed("0");

						}

					}

				}
				// 如果遍历一圈,还没有把该订单下的安装项设置成 isChoosed =="1" 那么表示Naruto1 成立
				if (null == itemVo.getIsChoosed()) {
					itemVo.setIsChoosed("1");

					// 如果该安装项的计划中是已转给供应商,已验收, 则不可修改, 不然可修改
					List<OrderInstallItemVo> status = orderService.findOrderInstallItemPlanStatus(order.getOrderId());
					if (status.size() > 0) {

						for (OrderInstallItemVo orderInstallItemVo : status) {
							if (itemVo.getId().equals(orderInstallItemVo.getId())) {

								if (Integer.parseInt(orderInstallItemVo.getStatus()) > 1) {
									itemVo.setStatus("1");
									break;
								} else {
									itemVo.setStatus("0");
									break;
								}

							}

						}
					}

					naruto.add(itemVo);
				}

			}

			// Naruto

			if (naruto.size() > 0) {

				for (OrderInstallItemVo orderInstallItemVo : naruto) {

					installItemByStoreId.add(orderInstallItemVo);

				}
			}

			// 以供回显
			model.addAttribute("installItemList", installItemByStoreId);

		} else {
			// 新增
		}

		// 接单区域, id和name
		model.addAttribute("acceptAreaList", list);
		model.addAttribute("order", order);
		return "modules/order/orderForm";
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "details")
	public String details(Order order, Model model) {
		model.addAttribute("order", order);
		List<Order> list = orderService.getAcceptAreaForOrder(order);
		// 接单区域, id和name
		if (null != order.getAcceptAreaId()) {
			for (Order order2 : list) {

				if (order.getAcceptAreaId().equals(order2.getAcceptAreaId())) {

					order.setAcceptArea(order2.getAcceptArea());
				}
			}

		}

		// 安装项'

//		List<OrderInstallItemVoDetails> installItems = null;
//		List<OrderInstallItemVoDetails> installItemByStoreId = null;
//		// 该门店下相同工程模式下的所有安装项(模板)
//		installItemByStoreId = orderService.findInstallItemByStoreId(order);
//		// 该订单下的所有安装项
//		installItems = orderService.findInstallItemByOrderId(order.getOrderId());
//
//		// Naruto1 集合
//		List<OrderInstallItemVoDetails> naruto = new ArrayList<OrderInstallItemVoDetails>();
//
//		// 进行笛卡尔积比较
//
//		// 该订单下的安装项
//		for (OrderInstallItemVoDetails itemVo : installItems) {
//			// Naruto1---->:用于判断 是否存在 模板中已经删除的,但删除前该订单已经申请了的安装项
//			// 总得模板
//			for (OrderInstallItemVoDetails orderInstallItemVo : installItemByStoreId) {
//
//				if (itemVo.getProjectInstallItemId().equals(orderInstallItemVo.getProjectInstallItemId())) {
//					// 如果一样 标记1
//					orderInstallItemVo.setIsChoosed("1");
//					itemVo.setIsChoosed("1");
//					break;
//
//				} else {
//					// 如果不一样且之前是1的也不能覆盖
//					if (null == orderInstallItemVo.getIsChoosed() || !orderInstallItemVo.getIsChoosed().equals("1")) {
//
//						// 不一样 标记0
//						orderInstallItemVo.setIsChoosed("0");
//
//					}
//
//				}
//
//			}
//			// 如果遍历一圈,还没有把该订单下的安装项设置成 isChoosed =="1" 那么表示Naruto1 成立
//			if (null == itemVo.getIsChoosed()) {
//				itemVo.setIsChoosed("1");
//				naruto.add(itemVo);
//			}
//
//		}

		// Naruto

//		if (naruto.size() > 0) {
//
//			for (OrderInstallItemVoDetails orderInstallItemVo : naruto) {
//
//				installItemByStoreId.add(orderInstallItemVo);
//
//			}
//		}

		// 以供回显
		//model.addAttribute("installItemList", installItemByStoreId);
		model.addAttribute("acceptAreaList", list);
		return "modules/order/orderFormDetails";
	}

	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "save")
	public String save(Order order, Model model, RedirectAttributes redirectAttributes, String[] installItemIds) {

		orderService.save(order, installItemIds, redirectAttributes);

		return "redirect:" + Global.getAdminPath() + "/order/order/list";
	}

	@RequestMapping(value = "saveUser")
	public String saveUser(Order order, Model model, RedirectAttributes redirectAttributes, String[] installItemIds) {

		orderService.save(order, installItemIds, redirectAttributes);

		return "redirect:" + Global.getAdminPath() + "/order/order/list";
	}

	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "delete")
	public String delete(Order order, RedirectAttributes redirectAttributes) {
		orderService.delete(order);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:" + Global.getAdminPath() + "/order/order/?repage";
	}

	// ajax 订单编号重复 的处理
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "checkOrderNumber")
	public @ResponseBody String orderNumberAjax(String orderNumber) {

		Integer result = orderService.getOrderNumberById(orderNumber);

		if (null == result || 0 == result) {
			return "0";

		} else {
			return String.valueOf(result);

		}

	}

	@RequestMapping(value = "findInstallItemByStoreId")
	public @ResponseBody List<OrderInstallItemVo> findInstallItemByStoreId(String storeId, String projectModeValue) {
		Order order = new Order();
		order.setStoreId(storeId);
		order.setProjectMode(projectModeValue);
		List<OrderInstallItemVo> list = orderService.findInstallItemByStoreId(order);

		if (null != list && list.size() > 0) {
			// 如果该门店下有相关安装项

			return list;

		} else {
			// 该门店下没有相关安装项

			return null;

		}

	}

	@RequestMapping(value = "findacceptAreaBystoreIdAndProjectMode")
	public @ResponseBody List<Order> findacceptAreaBystoreIdAndProjectMode(String storeId, String projectModeValue) {
		Order order = new Order();
		order.setStoreId(storeId);
		order.setProjectMode(projectModeValue);
		List<Order> list = orderService.getAcceptAreaForOrder(order);

		if (null != list && list.size() > 0) {
			return list;
		} else {

			return null;

		}
	}

	/**
	 * 根据门店、工程模式、区域查询订单
	 * 
	 * @param storeId
	 * @param projectModeValue
	 * @param engineDepartId
	 * @return
	 */
	@RequestMapping(value = "findOrderBystoreIdAndProjectModeAndengineDepartId")
	public @ResponseBody List<Order> findOrderBystoreIdAndProjectModeAndengineDepartId(String storeId, String projectModeValue, Integer engineDepartId) {
		Order order = new Order();
		order.setStoreId(storeId);
		order.setProjectMode(projectModeValue);
		order.setEngineDepartId(engineDepartId);
		List<Order> list = orderService.findList(order);
		if (null != list && list.size() > 0) {
			return list;
		} else {

			return null;

		}
	}
	
	@RequestMapping(value = "findOrderByParam")
	public @ResponseBody List<Order> findOrderByParam(String storeId, String projectModeValue, Integer engineDepartId,String orderNumber) {
		Order order = new Order();
		order.setStoreId(storeId);
		order.setProjectMode(projectModeValue);
		order.setEngineDepartId(engineDepartId);
		order.setOrderNumber(orderNumber);
		List<Order> list = orderService.findOrderByParam(order);
		if (null != list && list.size() > 0) {
			return list;
		} else {
			return null;

		}
	}  

	@RequestMapping(value = "findOrderById")
	public @ResponseBody Order findOrderById(String orderId) {
		Order order = orderService.get(orderId);
		return order;
	}

	@RequestMapping(value = "findEngineDepartmentBystoreIdAndProjectMode")
	public @ResponseBody List<Order> findEngineDepartmentBystoreIdAndProjectMode(String storeId, String projectModeValue) {
		// 根据门店和产业模式 及当前用户查询 工程区域
		String loginUserEmpId = UserUtils.getUser().getEmpId();
		Order order = new Order();
		order.setEmpId(loginUserEmpId);
		order.setProjectMode(projectModeValue);
		order.setStoreId(storeId);
		List<Order> list = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);
		if (list.size() < 1) {
			order.setEmpId(null);
			order.setProjectMode(projectModeValue);
			order.setStoreId(storeId);
			List<Order> list2 = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);
			return list2;
		}

		return list;

	}

}