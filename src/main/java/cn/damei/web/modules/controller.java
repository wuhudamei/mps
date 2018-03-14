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

/**
 * 任务包计划调整
 * 
 * @author 梅浩
 * @version 2016-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/taskpackageschedule")
public class controller extends BaseController {

	@Autowired
	private service service;

	@RequiresPermissions("taskpackageschedule:taskpackageschedule:view")
	@RequestMapping(value = { "list", "" })
	public String list(orderVo orderVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 过滤门店
		User user = UserUtils.getUser();
		if (orderVo.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderVo.setStoreId(null);
			} else {
				orderVo.setStoreId(storeId);
			}
		}
		// 过滤工程模式
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
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			orderVo.setStoreId(UserUtils.getUser().getStoreId());
			x++;
		}
		// 过滤门店
		User user = UserUtils.getUser();
		if (orderVo.getStoreId() == null) {
			String storeId = user.getStoreId();
			if (StringUtils.isBlank(storeId)) {
				orderVo.setStoreId(null);
			} else {
				orderVo.setStoreId(storeId);
			}
		}
		// 过滤工程模式
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
		// 根据订单id 查询该订单下的任务包
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

		// 根据订单id 查询该订单下的任务包
		List<OrderTaskpackage> list = service.getPackageByOrderId(Integer.parseInt(orderId));
		int i = id.length;
		String packName;
		for (OrderTaskpackage orderTaskpackage : list) {
			// 2 遍历

			for (int v = 0; v < id.length; v++) {

				// 3:如果任务包一样, 任务包计划开始和结束时间
				if (id[v].equals(orderTaskpackage.getId())) {

					packName = orderTaskpackage.getPackageName();
					// 4: 1: 如果数据库任务包时间为空
 					if (null == orderTaskpackage.getPlanStartdate() || null == orderTaskpackage.getPlanEnddate()) {

						// 4: 1: 1-1 页面如果不为空

 						if (null != planStartdate[v]&& planStartdate[v]!=""&&null != planEnddate[v]&&""!=planEnddate[v]) {

						/*	// 更新任务包, 并发送短信

							orderTaskpackage
									.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(planStartdate[v]));
							orderTaskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(planEnddate[v]));
							service.updatePackTime1(orderTaskpackage);
							service.updatePackTime(orderTaskpackage);
							// 发送短信
							OrderTaskpackage info = messageService.sendMessagetoWorker(Integer.parseInt(orderTaskpackage.getId()));
							//【美得你】订单（东晨小区-10-4-202-王维-13333333333）的任务包（瓦工任务包），工人调度（刘腾飞-13423455432）已调整任务包计划，请登录APP查看详情。
							PhoneMessageEntity entity = new PhoneMessageEntity();

									//给项目经理发送短信
										entity.setMessageContent("订单（" + info.getXiaoqu() + "-" + info.getLou() + "-" + info.getDanyuan()
												+ "-" + info.getShi() +"-"+info.getCustomerName()+"-"+info.getCustomerPhone() +  ") 的任务包(" + info.getPackageName() + "）,工人调度("+UserUtils.getUser().getName()+UserUtils.getUser().getPhone()+") 已调整任务包计划，请登录APP查看详情");
										entity.setReceiveEmployeeId(Integer.parseInt(info.getItemManagerId()));
										entity.setReceivePhone(info.getManagerPhone());
										entity.setMessageGenerateTime(new Date());
										entity.setRelatedBusinessId(Integer.parseInt(orderTaskpackage.getId()));
										entity.setRelatedBusinessType("200501");
										entity.setStatus("0");
										messageDao.saveMessageContent(entity);*/

						} else {

							i--;

							if (i == 0) {
								return "redirect:" + Global.getAdminPath() + "/taskpackageschedule/list";

							}

						}
					} else {

						// 4: 2: 如果数据库任务包时间不为空

						// 4: 2: 2-1 页面不为空
						if (null != planStartdate[v]&& planStartdate[v]!=""&&null != planEnddate[v]&&""!=planEnddate[v]) {

							// 4: 2: 2-2-1 如果不相等

							if (!planStartdate[v].equals(
									new SimpleDateFormat("yyyy-MM-dd").format(orderTaskpackage.getPlanStartdate()))
									|| (!planEnddate[v].equals(new SimpleDateFormat("yyyy-MM-dd")
											.format(orderTaskpackage.getPlanEnddate())))) {
								// 时间调整了

								// 更新任务包, 并发送短信

							/*	orderTaskpackage
										.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(planStartdate[v]));
								orderTaskpackage
										.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(planEnddate[v]));
								service.updatePackTime1(orderTaskpackage);
								service.updatePackTime(orderTaskpackage);
								// 发送短信
								OrderTaskpackage info = messageService.sendMessagetoWorker(Integer.parseInt(orderTaskpackage.getId()));
								//【美得你】订单（东晨小区-10-4-202-王维-13333333333）的任务包（瓦工任务包），工人调度（刘腾飞-13423455432）已调整任务包计划，请登录APP查看详情。
								PhoneMessageEntity entity = new PhoneMessageEntity();
								
										//给项目经理发送短信
											entity.setMessageContent("订单（" + info.getXiaoqu() + "-" + info.getLou() + "-" + info.getDanyuan()
													+ "-" + info.getShi()+"-"+info.getCustomerName()+"-"+info.getCustomerPhone() + ") 的任务包(" + info.getPackageName() + "）,工人调度("+UserUtils.getUser().getName()+UserUtils.getUser().getPhone()+") 已调整任务包计划，请登录APP查看详情");
											entity.setReceiveEmployeeId(Integer.parseInt(info.getItemManagerId()));
											entity.setReceivePhone(info.getManagerPhone());
											entity.setMessageGenerateTime(new Date());
											entity.setRelatedBusinessId(Integer.parseInt(orderTaskpackage.getId()));
											entity.setRelatedBusinessType("200501");
											entity.setStatus("0");
											messageDao.saveMessageContent(entity);
								*/

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
		// 5:重定向到任务包计划list
		addMessage(redirectAttributes, "计划调整成功");
		return "redirect:" + Global.getAdminPath() + "/taskpackageschedule/list";

	}

	public void sendMessageByPackId(String packId) {

	/*	// 查询出需要发送的短信内容
		OrderTaskpackage info = messageService.sendMessagetoWorker(Integer.parseInt(packId));
		// 【美得你】订单（东晨小区-10-4-202-王维-13333333333）的任务包（瓦工任务包），工人调度（刘腾飞-13423455432）已调整任务包计划，请登录APP查看详情。
		PhoneMessageEntity entity = new PhoneMessageEntity();
		
				//给项目经理发送短信
					entity.setMessageContent("订单（" + info.getXiaoqu() + "-" + info.getLou() + "-" + info.getDanyuan()
							+ "-" + info.getShi() +info.getCustomerName()+"-"+info.getCustomerPhone()+ ") 的任务包(" + info.getPackageName() + "）,工人调度("+UserUtils.getUser().getName()+UserUtils.getUser().getPhone()+")");
					entity.setReceiveEmployeeId(Integer.parseInt(info.getItemManagerId()));
					entity.setReceivePhone(info.getManagerPhone());
					entity.setMessageGenerateTime(new Date());
					entity.setRelatedBusinessId(Integer.parseInt(packId));
					entity.setRelatedBusinessType("200501");
					entity.setStatus("0");
					messageDao.saveMessageContent(entity);*/
				

	}

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

}