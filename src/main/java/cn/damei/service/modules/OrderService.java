
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

			Integer count = getOrderNumberById(order.getOrderNumber());
			if (null != count && count > 0) {

				addMessage(redirectAttributes, "该订单您已经录入过了,请不要重复录入~");

			} else {

				addMessage(redirectAttributes, "保存订单成功");


				order.setOrderStatusNumber(OrderConstantUtil.ORDER_STATUS_RECEIVE_THE_ORDER_105);
				order.setOrderStatusDescription(OrderConstantUtil.ORDER_STATUS_NAME_RECEIVE_THE_ORDER_105);
				order.setOrderTaskPackStatus("0");
				order.setIsScrap(OrderConstantUtil.ORDER_IS_SCRAP_NO_0);


				super.save(order);


				Order reportOrder = dao.findRelatedReportInfoByCustomerPhone(order.getCustomerPhone());
				if (null != reportOrder) {


					reportOrder.setOrderId(order.getOrderId());
					reportOrder.setOrderNumber(order.getOrderNumber());
					reportOrder.preInsert();
					dao.batchInsertOrderReportRelatedInfo(reportOrder);












































				}



























				String mode = order.getProjectMode();
				if (mode.equals("1")) {




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



				} else if (mode.equals("2")) {




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



				}
			}

		} else {
			addMessage(redirectAttributes, "修改订单成功");

































































































































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


	public Order getByIdAndStoreId(String id, String storeId) {
		return orderDao.getByIdAndStoreId(id, storeId);
	}


	public List<Order> getAcceptAreaForOrder(Order order) {
		return dao.getAcceptAreaForOrder(order);
	}


	public List<OrderInstallItemVo> findInstallItemByStoreId(Order order) {

		return dao.findInstallItemByStoreId(order);

	}


	public OrderInstallItemVo findInstallItemByInstallItemId(Integer installItemId) {

		return dao.findInstallItemByInstallItemId(installItemId);
	}


	public Integer isGeneratedInstallItemPlan(OrderInstallItemVo vo) {

		return dao.isGeneratedInstallItemPlan(vo);
	}


	@Transactional(readOnly = false)
	public void saveOrderInstallItem(OrderInstallItemVo vo) {

		dao.saveOrderInstallItem(vo);

	}


	public List<OrderInstallItemVo> findInstallItemByOrderId(Integer orderId) {

		return dao.findInstallItemByOrderId(orderId);
	}


	public List<OrderInstallItemVo> findOrderInstallItemPlanStatus(Integer orderId) {

		return dao.findOrderInstallItemPlanStatus(orderId);
	}


	@Transactional(readOnly = false)
	public void deleteOrderInstallItem(Integer orderId) {

		dao.deleteOrderInstallItem(orderId);
	}


	@Transactional(readOnly = false)
	public void deleteOrderInstallItemPlan(Integer orderId) {

		dao.deleteOrderInstallItemPlan(orderId);
	}


	public Integer selectInstallItemCount(OrderInstallItemVo vo) {

		return dao.selectInstallItemCount(vo);
	}


	@Transactional(readOnly = false)
	public void saveOrderInstallItemPlan(OrderInstallItemVo vo) {

		dao.saveOrderInstallItemPlan(vo);
	}


	@Transactional(readOnly = false)
	public void deleteAllInstallItem(Integer orderId) {

		dao.deleteAllInstallItem(orderId);
	}


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


	@Transactional(readOnly = false)
	public void batchInsertOrderReportRelatedInfo(Order order) {

		dao.batchInsertOrderReportRelatedInfo(order);

	}


	@Transactional(readOnly = false)
	public Order findRelatedReportInfoByCustomerPhone(String customerPhone) {

		return dao.findRelatedReportInfoByCustomerPhone(customerPhone);

	}


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