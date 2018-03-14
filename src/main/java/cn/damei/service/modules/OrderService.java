/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.entity.modules.BizCusServiceProblem;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.dao.modules.OrderDao;
import cn.damei.entity.modules.Order;
import cn.damei.entity.modules.OrderInstallItemVo;
import cn.damei.dao.modules.BizOrderReportDao;
import cn.damei.dao.modules.BizOrderReportLogDao;
import cn.damei.entity.modules.BizPhoneMsg;

/**
 * 订单管理Service
 * 
 * @author wyb
 * @version 2016-09-08
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends CrudService<OrderDao, Order> {

	@Autowired
	private OrderDao orderDao;

	public Order get(String id) {
		return super.get(id);
	}

	public List<Order> findList(Order order) {
		return super.findList(order);
	}

	public Page<Order> findPage(Page<Order> page, Order order) {
		return super.findPage(page, order);
	}

	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private PhoneMessageDao messageDao;
	@Autowired
	private BizOrderReportLogDao orderReportLogDao;
	@Autowired
	private BizOrderReportDao orderReportDao;
	@Autowired
	private BizEmployeeDao bizEmployeeDao;

	@Transactional(readOnly = false)
	public void save(Order order, String[] installItemIds, RedirectAttributes redirectAttributes) {
		if (null == order.getOrderStatusNumber()) {
			// 订单去重
			Integer count = getOrderNumberById(order.getOrderNumber());
			if (null != count && count > 0) {

				addMessage(redirectAttributes, "该订单您已经录入过了,请不要重复录入~");

			} else {

				addMessage(redirectAttributes, "保存订单成功");
				// 新增
				// 保存订单, 默认 105
				order.setOrderStatusNumber(OrderConstantUtil.ORDER_STATUS_RECEIVE_THE_ORDER_105);// 105
				order.setOrderStatusDescription(OrderConstantUtil.ORDER_STATUS_NAME_RECEIVE_THE_ORDER_105);// 工程部确认接受订单
				order.setOrderTaskPackStatus("0");
				order.setIsScrap(OrderConstantUtil.ORDER_IS_SCRAP_NO_0);// 订单是否作废
				// 根据地址设置经纬度
				// 保存订单
				super.save(order);

				// 2017-5-8 加入返单关联
				Order reportOrder = dao.findRelatedReportInfoByCustomerPhone(order.getCustomerPhone());
				if (null != reportOrder) {
					// 更新返单

					reportOrder.setOrderId(order.getOrderId());
					reportOrder.setOrderNumber(order.getOrderNumber());
					reportOrder.preInsert();
					dao.batchInsertOrderReportRelatedInfo(reportOrder);

					// BizOrderReport orderReport=
					// orderReportDao.get(reportOrder.getOrderReportId());
					//
					// OrderReportLogEntity logEntity = new
					// OrderReportLogEntity();
					// Map<String,Object> logRelatedMap = new HashMap<>();
					//
					// logEntity.setOrderReportId(orderReport.getId());
					// logEntity.setLogType(BizOrderReportConstantUtil.SIGN_TYPE_1);
					// logEntity.setSignBillDateTime(reportOrder.getAcceptOrderDate());
					//
					// logEntity.setOperateDateTime(new Date());
					// logEntity.setOperateEmployeeName(UserUtils.getUser().getName());
					// logEntity.preInsert();
					// orderReportLogDao.saveSignLog(logEntity);
					// //保存该次log关联订单
					// logRelatedMap.put("logId",logEntity.getId());
					// logRelatedMap.put("orderNums",reportOrder.getOrderNumber());
					//
					// orderReportLogDao.saveLogRelatedOrderNumber(logRelatedMap);
					// //保存log日志
					//
					// PhoneMessageEntity phone = new PhoneMessageEntity();
					//
					//
					// String content =
					// "【美得你】亲爱的美得你员工，您推荐的客户"+orderReport.getCustomerName()+"已进美得你门店了，您的奖励将在下月兑现。如有新的客户，记得再次向“小美返单”推荐哦";
					//
					// phone.setReceivePhone(orderReport.getReporterPhone());
					// phone.setMessageContent(content);
					// phone.setMessageGenerateTime(new Date());
					// phone.setStatus(ConstantUtils.SEND_MSG_STATUS_0);
					// phone.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_66666);
					// phone.setRelatedBusinessId(orderReport.getId());
					// phone.setRelatedBusinessVarchar(logEntity.getId());
					// Integer message
					// =messageDao.checkIsExistByTypeAndBusinessId(phone);
					// if(null==message || message==0){
					// messageDao.saveMessageContent(phone);
					//
					//
					// }

				}

//				// 保存订单的相关安装项
//				if (null != installItemIds && installItemIds.length > 0) {
//
//					for (int v = 0; v < installItemIds.length; v++) {
//
//						// 根据安装项id ,查询安装项模板,得到对象
//						OrderInstallItemVoDetails item = dao.findInstallItemByInstallItemId(Integer.parseInt(installItemIds[v]));
//						Integer installItemPlan = dao.isGeneratedInstallItemPlan(item);
//						if (null != installItemPlan && installItemPlan > 0) {
//							// 已生成计划
//							item.setIsGeneratedOrdeInstallPlan("1");
//
//						} else {
//							item.setIsGeneratedOrdeInstallPlan("0");
//						}
//						// 订单id
//						item.setOrderId(order.getOrderId());
//						// 创建时间
//						item.setCreateDate(new Date());
//						// 保存该订单安装项
//						// dao.saveOrderInstallItem(item);
//
//					}
//
//				}

				String mode = order.getProjectMode();
				if (mode.equals("1")) {
					// 产业
					// ================================短信start==============================================
					/**
					 * 添加订单==预算员
					 */
					// 【美得你】订单（订单编号：XXXXXXX，小区名-楼号-单元号-门牌号-客户姓名-手机号），接单员已接收订单，请及时生成任务包。
					String content = "订单（订单编号：" + order.getOrderNumber() + "," + order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-" + order.getCustomerPhone() + "），接单员已接收订单，请及时生成任务包。";
					BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId(), "3");
					List<Integer> list = new ArrayList<Integer>();
					List<BizEmployee2> employeelist = null;
					if (null != bizMessagegroup) {
						String[] str = bizMessagegroup.getEmployees().split(",");
						for (String id1 : str) {
							list.add(Integer.valueOf(id1));
						}
						employeelist = bizEmployeeService2.getById(list);
						if (null != employeelist && employeelist.size() > 0) {
							for (BizEmployee2 bizEmployee2 : employeelist) {

								BizPhoneMsg phone = new BizPhoneMsg();
								phone.setReceiveEmployeeId(bizEmployee2.getId());
								phone.setReceivePhone(bizEmployee2.getPhone());
								phone.setMsgContent(content);
								phone.setMsgGenerateDatetime(new Date());
								phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
								phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200102);
								phone.setRelatedBusinessIdInt(order.getOrderId());
								bizPhoneMsgService.insert(phone);
							}
						}
					}
					/**
					 * 添加订单==产业-派单员
					 */
					// 【美得你】订单（订单编号：XXXXXXX，小区名-楼号-单元号-门牌号-客户姓名-手机号），接单员已接收订单，请及时分配项目经理和质检员。
					String content2 = "订单（订单编号：" + order.getOrderNumber() + "," + order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-" + order.getCustomerPhone() + "），接单员已接收订单，请及时分配项目经理和质检员。";
					BizMessagegroup bizMessagegroup2 = bizMessagegroupService.getByStoreId(order.getStoreId(), "2");
					List<Integer> list2 = new ArrayList<Integer>();
					List<BizEmployee2> employeelist2 = null;
					if (null != bizMessagegroup2) {
						String[] str = bizMessagegroup2.getEmployees().split(",");
						for (String id1 : str) {
							list2.add(Integer.valueOf(id1));
						}
						employeelist2 = bizEmployeeService2.getById(list2);
						if (null != employeelist2 && employeelist2.size() > 0) {
							for (BizEmployee2 bizEmployee2 : employeelist2) {

								BizPhoneMsg phone2 = new BizPhoneMsg();
								phone2.setReceiveEmployeeId(bizEmployee2.getId());
								phone2.setReceivePhone(bizEmployee2.getPhone());
								phone2.setMsgContent(content2);
								phone2.setMsgGenerateDatetime(new Date());
								phone2.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
								phone2.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200103);
								phone2.setRelatedBusinessIdInt(order.getOrderId());
								bizPhoneMsgService.insert(phone2);
							}
						}
					}

					// ================================短信end==============================================

				} else if (mode.equals("2")) {
					// 传统
					// ================================短信start==============================================
					/**
					 * 添加订单==传统-派单员
					 */
					// 【美得你】订单（订单编号：XXXXXXX，小区名-楼号-单元号-门牌号-客户姓名-手机号），接单员已接收订单，请及时分配项目经理和质检员。
					String content2 = "订单（订单编号：" + order.getOrderNumber() + "," + order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-" + order.getCustomerPhone() + "），接单员已接收订单，请及时分配项目经理和质检员。";
					BizMessagegroup bizMessagegroup2 = bizMessagegroupService.getByStoreId(order.getStoreId(), "12");
					List<Integer> list2 = new ArrayList<Integer>();
					List<BizEmployee2> employeelist2 = null;
					if (null != bizMessagegroup2) {
						String[] str = bizMessagegroup2.getEmployees().split(",");
						for (String id1 : str) {
							list2.add(Integer.valueOf(id1));
						}
						employeelist2 = bizEmployeeService2.getById(list2);
						if (null != employeelist2 && employeelist2.size() > 0) {
							for (BizEmployee2 bizEmployee2 : employeelist2) {

								BizPhoneMsg phone2 = new BizPhoneMsg();
								phone2.setReceiveEmployeeId(bizEmployee2.getId());
								phone2.setReceivePhone(bizEmployee2.getPhone());
								phone2.setMsgContent(content2);
								phone2.setMsgGenerateDatetime(new Date());
								phone2.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
								phone2.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200103);
								phone2.setRelatedBusinessIdInt(order.getOrderId());
								bizPhoneMsgService.insert(phone2);
							}
						}
					}

					// ================================短信end==============================================

				}
			}

		} else {
			addMessage(redirectAttributes, "修改订单成功");

//			if (Integer.parseInt(order.getOrderStatusNumber()) >= 200 && Integer.parseInt(order.getOrderStatusNumber()) < 340) {
//				// 如果订单状态大于等于200
//				// 删除所有该订单下 安装项计划状态是1 的
//				dao.deleteOrderInstallItem(order.getOrderId());
//				dao.deleteOrderInstallItemPlan(order.getOrderId());
//				if (null != installItemIds && installItemIds.length > 0) {
//					OrderInstallItemVoDetails vo = new OrderInstallItemVoDetails();
//					vo.setOrderId(order.getOrderId());
//
//					// pp修改开始
//					// 查询待添加的订单安装项，匹配条件：包含在提交的工程安装项id中，并且不包含在该订单已存在的工程安装项id中
//					Map<String, Object> param = new HashMap<>();
//					String installItemIdsStr = installItemIds[0];
//					for (int i = 1; i < installItemIds.length; i++) {
//						installItemIdsStr += "," + installItemIds[i];
//					}
//					param.put("projectInstallItemIds", installItemIdsStr);
//					param.put("orderId", order.getOrderId());
//					List<OrderInstallItemVoDetails> orderProjectInstallItemToAdd = dao.selectOrderProjectInstallItemToAdd(param);
//
//					if (orderProjectInstallItemToAdd != null && orderProjectInstallItemToAdd.size() > 0) {
//						// 批量保存订单安装项
//						dao.batchSaveOrderInstallItem(orderProjectInstallItemToAdd);
//
//						// 批量查询订单安装项id
//						param = new HashMap<>();
//						installItemIdsStr = orderProjectInstallItemToAdd.get(0).getProjectInstallItemId() + "";
//						for (int i = 1; i < orderProjectInstallItemToAdd.size(); i++) {
//							installItemIdsStr += "," + orderProjectInstallItemToAdd.get(i).getProjectInstallItemId();
//						}
//						param.put("projectInstallItemIds", installItemIdsStr);
//						param.put("orderId", order.getOrderId());
//						List<Map<String, Object>> installItemIdMap = dao.selectOrderInstallItemIds(param);
//
//						// 查询订单实际安装时间
//						Date actualStartDate = dao.selectActualStartDate(vo);
//
//						// 拼装订单安装计划
//						for (int i = 0; i < orderProjectInstallItemToAdd.size(); i++) {
//							OrderInstallItemVoDetails orderInstallItemVo = orderProjectInstallItemToAdd.get(i);
//
//							// 订单安装计划的订单安装项id
//							for (int j = 0; j < installItemIdMap.size(); j++) {
//								Map<String, Object> map = installItemIdMap.get(j);
//								Object objOrderInstallItemId = map.get("orderInstallItemId");
//								Object objOrderId = map.get("orderId");
//								Object objProjectInstallItemId = map.get("projectInstallItemId");
//								if (objOrderInstallItemId != null && objOrderId != null && objProjectInstallItemId != null) {
//									// 比较订单id和安装项id，如果相等则取其订单安装项id
//									Integer orderInstallItemId = (Integer) objOrderInstallItemId;
//									Integer orderId = (Integer) objOrderId;
//									Integer projectInstallItemId = (Integer) objProjectInstallItemId;
//									if (orderInstallItemVo.getOrderId().equals(orderId) && orderInstallItemVo.getProjectInstallItemId().equals(projectInstallItemId)) {
//										orderInstallItemVo.setId(orderInstallItemId);
//										break;
//									}
//								}
//							}
//
//							orderInstallItemVo.setStatus("1");
//							orderInstallItemVo.setProjectInstallItemId(orderInstallItemVo.getId());
//
//							orderInstallItemVo.setPlanIntoDate(new Date(actualStartDate.getTime() + orderInstallItemVo.getDaysPlanInto() * 1000l * 3600l * 24l));
//						}
//						// 批量保存订单安装计划
//						dao.batchSaveOrderInstallItemPlan(orderProjectInstallItemToAdd);
//					}
//					// pp修改结束
//
//					// 根据jsp页面上的安装项id ,查询是否有记录
//					/*
//					 * for (int v = 0; v < installItemIds.length; v++) {
//					 * vo.setProjectInstallItemId
//					 * (Integer.parseInt(installItemIds[v]));
//					 * 
//					 * Integer count = dao.selectInstallItemCount(vo); //
//					 * 如果有记录,表明是 安装项计划为2,3 4的 已申请,已转给供应商 已验收的安装项, 不动 if (null ==
//					 * count || count == 0) { // 如果没有记录, 表明最新添加的,
//					 * 
//					 * OrderInstallItemVoDetails vo2 = dao
//					 * .findInstallItemByInstallItemId
//					 * (Integer.parseInt(installItemIds[v])); // insert 保存即可,
//					 * vo2.setOrderId(order.getOrderId());
//					 * dao.saveOrderInstallItem(vo2); // 同时更新安装项计划
//					 * vo2.setStatus("1");
//					 * vo2.setProjectInstallItemId(vo2.getId());
//					 * vo2.setPlanIntoDate(new Date(
//					 * order.getContractStartDate().getTime() +
//					 * vo2.getDaysPlanInto() * 1000 * 3600 * 24));
//					 * vo2.setApplyIntoDate(new
//					 * Date(order.getContractStartDate().getTime() +
//					 * vo2.getDaysToApplyInto() * 1000 * 3600 * 24));
//					 * vo2.setRealIntoDate(new
//					 * Date(order.getContractStartDate().getTime() +
//					 * vo2.getDaysPalnComplete() * 1000 * 3600 * 24));
//					 * dao.saveOrderInstallItemPlan(vo2); }
//					 * 
//					 * }
//					 */
//
//				} else {
//					// 如果jsp没有一个安装项,表明安装项都撤了
//
//				}
//			} else {
//				// 订单状态小于200
//				// 删除所有, 同时保存页面上的所有安装项
//				dao.deleteAllInstallItem(order.getOrderId());
//				dao.deleteAllInstallItemPlan(order.getOrderId());
//
//				// JSP页面的安装项
//				if (null != installItemIds && installItemIds.length > 0) {
//
//					for (int v = 0; v < installItemIds.length; v++) {
//						OrderInstallItemVoDetails vo2 = dao.findInstallItemByInstallItemId(Integer.parseInt(installItemIds[v]));
//						// insert 保存即可,
//						vo2.setOrderId(order.getOrderId());
//						dao.saveOrderInstallItem(vo2);
//					}
//
//				} else {
//
//					// 订单小于200 jsp没有安装项, 已经删了 不用做什么事情
//				}
//
//			}
			// 更新订单
			// 根据设计师电话和姓名查出 设计师ID
			Map<String, String> paramaterMap = new HashMap<>();
			paramaterMap.put("empName", order.getDesignerName());
			paramaterMap.put("empPhone", order.getDesignerPhone());
			List<BizEmployee> employeeListByEmpType = bizEmployeeDao.getEmployeeListByEmpType(paramaterMap);
			order.setDesignerEmployeeId(employeeListByEmpType.get(0).getId());
			super.save(order);
		}

	}

	@Transactional(readOnly = false)
	public void delete(Order order) {
		super.delete(order);
	}

	public Integer getOrderNumberById(String orderNumber) {

		return dao.getOrderNumberById(orderNumber);
	}

	public Integer getIdByOrderNumber(String orderNumber) {
		return dao.getIdByOrderNumber(orderNumber);
	}

	public Order findOrderById(String id) {
		return dao.get(id);
	}

	/**
	 * @param id
	 *            storeId
	 */
	public Order getByIdAndStoreId(String id, String storeId) {
		return orderDao.getByIdAndStoreId(id, storeId);
	}

	/**
	 * 接单区域
	 * 
	 * @return
	 */
	public List<Order> getAcceptAreaForOrder(Order order) {
		return dao.getAcceptAreaForOrder(order);
	}

	/**
	 * 参数: 门店id,工程模式value ajax 加载安装项模板
	 * 
	 * @param order
	 * @return
	 */
	public List<OrderInstallItemVo> findInstallItemByStoreId(Order order) {

		return dao.findInstallItemByStoreId(order);

	}

	/**
	 * 根据安装项id 查询该对象
	 * 
	 * @param installItemId
	 * @return
	 */
	public OrderInstallItemVo findInstallItemByInstallItemId(Integer installItemId) {

		return dao.findInstallItemByInstallItemId(installItemId);
	}

	/**
	 * 根据订单安装项,查询是否生成了 安装项计划 (订单id和 安装项id)
	 * 
	 * @param vo
	 * @return
	 */
	public Integer isGeneratedInstallItemPlan(OrderInstallItemVo vo) {

		return dao.isGeneratedInstallItemPlan(vo);
	}

	/**
	 * 保存订单安装项
	 * 
	 * @param vo
	 */
	@Transactional(readOnly = false)
	public void saveOrderInstallItem(OrderInstallItemVo vo) {

		dao.saveOrderInstallItem(vo);

	}

	/**
	 * 根据订单id查询该订单下的所有安装项
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderInstallItemVo> findInstallItemByOrderId(Integer orderId) {

		return dao.findInstallItemByOrderId(orderId);
	}

	/**
	 * 根据订单id(状态为200) 查询订单安装项计划中, 对应的安装项状态 返回值: orderId id status
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderInstallItemVo> findOrderInstallItemPlanStatus(Integer orderId) {

		return dao.findOrderInstallItemPlanStatus(orderId);
	}

	/**
	 * 根据订单id 删除不是已经申请和验收的安装项
	 * 
	 * @param orderId
	 * 
	 */
	@Transactional(readOnly = false)
	public void deleteOrderInstallItem(Integer orderId) {

		dao.deleteOrderInstallItem(orderId);
	}

	/**
	 * 根据订单id ,删除安装项计划中状态为1的安装项
	 * 
	 * @param orderId
	 */
	@Transactional(readOnly = false)
	public void deleteOrderInstallItemPlan(Integer orderId) {

		dao.deleteOrderInstallItemPlan(orderId);
	}

	/**
	 * 查询是否有记录
	 * 
	 * @param vo
	 * @return
	 */
	public Integer selectInstallItemCount(OrderInstallItemVo vo) {

		return dao.selectInstallItemCount(vo);
	}

	/**
	 * 更新安装项计划
	 * 
	 * @param vo
	 */
	@Transactional(readOnly = false)
	public void saveOrderInstallItemPlan(OrderInstallItemVo vo) {

		dao.saveOrderInstallItemPlan(vo);
	}

	/**
	 * 订单状态小于200 不必考虑 全删, 并保存最新的
	 * 
	 * @param orderId
	 */
	@Transactional(readOnly = false)
	public void deleteAllInstallItem(Integer orderId) {

		dao.deleteAllInstallItem(orderId);
	}

	/**
	 * 订单状态小于200 不必考虑 全删, 并更新计划最新的
	 * 
	 * @param orderId
	 */
	@Transactional(readOnly = false)
	public void deleteAllInstallItemPlan(Integer orderId) {

		dao.deleteAllInstallItemPlan(orderId);
	}

	public List<Order> findEngineDepartByStoreIdProjectModeIdAndEmpId(Order order) {

		List<Order> orderList = dao.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);
		if (orderList.size() == 0) {
			order.setEmpId(null);
			orderList = orderDao.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);

		}

		return orderList;
	}

	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}

	/**
	 * 普通插入返单和合同关联信息
	 * 
	 * @param order
	 */
	@Transactional(readOnly = false)
	public void batchInsertOrderReportRelatedInfo(Order order) {

		dao.batchInsertOrderReportRelatedInfo(order);

	}

	/**
	 * 根据手机号查询返单关联信息
	 * 
	 * @param customerPhone
	 * @return
	 */
	@Transactional(readOnly = false)
	public Order findRelatedReportInfoByCustomerPhone(String customerPhone) {

		return dao.findRelatedReportInfoByCustomerPhone(customerPhone);

	}

	// 根据订单 id 查询是否竣工 1 是 0 否
	public Map<String, String> selectOver(Integer orderId) {
		return dao.selectOver(orderId);
	}

	public Order getOrderByNuAndStoreId(Order order) {
		return dao.getOrderByNuAndStoreId(order);
	}

	public Page<BizCusServiceProblem> findlistProject(Page<BizCusServiceProblem> page, BizCusServiceProblem order) {
		order.setPage(page);
		List<BizCusServiceProblem> findlistProject = orderDao.findlistProject(order);
		page.setList(findlistProject);
		return page;
	}

	public Order getProjectbyId(String id) {

		return orderDao.getProjectbyId(id);
	}
	public List< Order>  getProjectName(String customerNameNot) {

		return orderDao.getProjectName(customerNameNot);
	}

	/**
	 * 根据安装项ID修改安装项
	 * 
	 * @Title: update
	 * @Description: TODO
	 * @param @param item
	 * @return void
	 * @author ZhangTongWei
	 * @throws
	 */
	@Transactional(readOnly = false)
	public void updateInstallItem(OrderInstallItemVo item) {
		dao.updateInstallItem(item);

	}

	public Integer queryCheckStatus(Integer orderId, Integer projectInstallItemId) {
		return dao.queryCheckStatus(orderId, projectInstallItemId);
	}

	public List<Order> findOrderByParam(Order order) {
		return dao.findOrderByParam(order);
	}

    public OrderInstallItemVo findInstallStatus(Integer id) {
	    return dao.findInstallStatus(id);
    }
}