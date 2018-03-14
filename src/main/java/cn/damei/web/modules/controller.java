package cn.damei.web.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.Order;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.orderVo;
import cn.damei.service.modules.service;


@Controller
@RequestMapping(value = "${adminPath}/taskpackageschedule")
public class controller extends BaseController {

	@Autowired
	private service service;

	@RequiresPermissions("taskpackageschedule:taskpackageschedule:view")
	@RequestMapping(value = { "list", "" })
	public String list(orderVo orderVo, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();
		if (orderVo.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderVo.setStoreId(null);
			} else {
				orderVo.setStoreId(storeId);
			}
		}

		if (StringUtils.isBlank(orderVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderVo.setProjectMode(be.getProjectMode());
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
						orderVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/taskPackageSchedule/taskPackageSchedule";
	}
	@RequiresPermissions("taskpackageschedule:taskpackageschedule:view")
	@RequestMapping(value = { "list2", "" })
	public String list2(orderVo orderVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		int x = 0;

		if (!UserUtils.getUser().getOffice().getId().equals("1")) {

			orderVo.setStoreId(UserUtils.getUser().getStoreId());
			x++;
		}

		User user = UserUtils.getUser();
		if (orderVo.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderVo.setStoreId(null);
			} else {
				orderVo.setStoreId(storeId);
			}
		}

		if (StringUtils.isBlank(orderVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						orderVo.setProjectMode(be.getProjectMode());
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
						orderVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<orderVo> page = service.findPage(new Page<orderVo>(request, response), orderVo);
		model.addAttribute("page", page);
		return "modules/taskPackageSchedule/taskPackageSchedule";
	}

	@RequiresPermissions("taskpackageschedule:taskpackageschedule:view")
	@RequestMapping(value = { "changepackageplan", "" })
	public String changepackageplan(Order order, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		List<OrderTaskpackage> list = service.getPackageByOrderId(order.getOrderId());

		if (null != list && list.size() > 0) {

			String customerMessage = list.get(0).getCustomerMessage() + "-" + list.get(0).getCustomerName();
			model.addAttribute("customerMessage", customerMessage);
			model.addAttribute("list", list);
			model.addAttribute("orderId", order.getOrderId());
		} else {
			model.addAttribute("customerMessage", "该订单暂无任务包计划");
			model.addAttribute("list", list);
		}

		return "modules/taskPackageSchedule/changePackPlan";
	}

	@RequiresPermissions("taskpackageschedule:taskpackageschedule:edit")
	@RequestMapping(value = { "reallychange", "" })
	public String reallychange(String orderId, String id[], String planStartdate[], String customerMessage,
			String planEnddate[], HttpServletRequest request, HttpServletResponse response, Model model,
			RedirectAttributes redirectAttributes) throws ParseException {


		List<OrderTaskpackage> list = service.getPackageByOrderId(Integer.parseInt(orderId));
		int i = id.length;
		String packName;
		for (OrderTaskpackage orderTaskpackage : list) {


			for (int v = 0; v < id.length; v++) {


				if (id[v].equals(orderTaskpackage.getId())) {

					packName = orderTaskpackage.getPackageName();

 					if (null == orderTaskpackage.getPlanStartdate() || null == orderTaskpackage.getPlanEnddate()) {



 						if (null != planStartdate[v]&& planStartdate[v]!=""&&null != planEnddate[v]&&""!=planEnddate[v]) {



						} else {

							i--;

							if (i == 0) {
								return "redirect:" + Global.getAdminPath() + "/taskpackageschedule/list";

							}

						}
					} else {




						if (null != planStartdate[v]&& planStartdate[v]!=""&&null != planEnddate[v]&&""!=planEnddate[v]) {



							if (!planStartdate[v].equals(
									new SimpleDateFormat("yyyy-MM-dd").format(orderTaskpackage.getPlanStartdate()))
									|| (!planEnddate[v].equals(new SimpleDateFormat("yyyy-MM-dd")
											.format(orderTaskpackage.getPlanEnddate())))) {






							} else {
								i--;

								if (i == 0) {
									return "redirect:" + Global.getAdminPath() + "/taskpackageschedule/list";

								}

							}

						}
					}
				}

			}
		}

		addMessage(redirectAttributes, "计划调整成功");
		return "redirect:" + Global.getAdminPath() + "/taskpackageschedule/list";

	}

	public void sendMessageByPackId(String packId) {


				

	}

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

}