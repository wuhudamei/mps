package cn.damei.service.modules;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.dao.modules.OrderDao;
import cn.damei.entity.modules.Order;
import cn.damei.entity.modules.OrderInstallItemVo;
import cn.damei.dao.modules.ProjectOrderListDao;
import cn.damei.entity.modules.ProjectOrderList;

@Service
public class ProjectOrderListService extends CrudService<ProjectOrderListDao, ProjectOrderList> {

	@Autowired
	OrderDao orderDao;

	public List<BizMaterialsChoiceBillItem> findMaterialsChoice(Integer id) {
		// TODO Auto-generated method stub
		return dao.findMaterialsChoice(id);
	}

	public List<BizMaterialsChoiceBillItem> findMaterialsChoicez(Integer id) {
		// TODO Auto-generated method stub
		return dao.findMaterialsChoicez(id);
	}

	public List<String> findDrawing(ProjectOrderList projectOrderList) {
		// TODO Auto-generated method stub
		return dao.findDrawing(projectOrderList);
	}

	public List<String> findAttached(ProjectOrderList projectOrderList) {
		// TODO Auto-generated method stub
		return dao.findAttached(projectOrderList);
	}

	public List<String> findwallAndFloor(ProjectOrderList projectOrderList) {
		// TODO Auto-generated method stub
		return dao.findwallAndFloor(projectOrderList);
	}

	public List<String> findMaterial(ProjectOrderList projectOrderList) {
		// TODO Auto-generated method stub
		return dao.findMaterial(projectOrderList);
	}

	public List<String> findOrderIds(List<String> list) {
		// TODO Auto-generated method stub
		return dao.findOrderIds(list);
	}

	public BizMaterialsChoiceBill findChoiceBillId(Integer orderID) {
		// TODO Auto-generated method stub
		return dao.findChoiceBillId(orderID);
	}

	@Transactional(readOnly = false)
	public void save(Order order, String installItemIds[], String installMode[]) {
		String join = StringUtils.join(installItemIds, ",");

		if (Integer.parseInt(order.getOrderStatusNumber()) >= 200 && Integer.parseInt(order.getOrderStatusNumber()) <= 400) {
			// 如果订单状态大于等于200
			
//			// 删除所有该订单下 安装项计划状态是1 的
//			orderDao.deleteOrderInstallItem(order.getOrderId());
//			orderDao.deleteOrderInstallItemPlan(order.getOrderId());
			
			
			if (null != installItemIds && installItemIds.length > 0) {
				OrderInstallItemVo vo = new OrderInstallItemVo();
				vo.setOrderId(order.getOrderId());

				// pp修改开始
				
				Map<String, Object> param = new HashMap<>();
				String installItemIdsStr = installItemIds[0];
				for (int i = 1; i < installItemIds.length; i++) {
					installItemIdsStr += "," + installItemIds[i];
				}
				param.put("projectInstallItemIds", installItemIdsStr);
				param.put("orderId", order.getOrderId());
				// 删除该订单下的订单安装项，匹配条件：不包含在提交的工程安装项id中的订单安装项
				orderDao.deleteOrderInstallItemSomeThing(param);
				// 删除该订单下的订单安装项计划，匹配条件：不包含在订单安装项中的订单安装项计划
				orderDao.deleteOrderInstallItemPlanSomeThing(order.getOrderId());
				// 查询待添加的订单安装项，匹配条件：包含在提交的工程安装项id中，并且不包含在该订单已存在的工程安装项id中
				List<OrderInstallItemVo> orderProjectInstallItemToAdd = orderDao.selectOrderProjectInstallItemToAdd(param);
                if(orderProjectInstallItemToAdd != null){
                	for (OrderInstallItemVo orderInstallItemVo : orderProjectInstallItemToAdd) {
    					Integer projectInstallItemId = orderInstallItemVo.getProjectInstallItemId();
    					for (int i = 0; i < installItemIds.length; i++) {
    						if (installItemIds[i].equals(projectInstallItemId + "")) {
    							String temp = installMode[i];
    							orderInstallItemVo.setInstallMode(temp);
    						}
    					}

    				}
                }
				

				if (orderProjectInstallItemToAdd != null && orderProjectInstallItemToAdd.size() > 0) {
					// 批量保存订单安装项
					orderDao.batchSaveOrderInstallItem(orderProjectInstallItemToAdd);

					// 批量查询订单安装项id
					param = new HashMap<>();
					installItemIdsStr = orderProjectInstallItemToAdd.get(0).getProjectInstallItemId() + "";
					for (int i = 1; i < orderProjectInstallItemToAdd.size(); i++) {
						installItemIdsStr += "," + orderProjectInstallItemToAdd.get(i).getProjectInstallItemId();
					}
					param.put("projectInstallItemIds", installItemIdsStr);
					param.put("orderId", order.getOrderId());
					List<Map<String, Object>> installItemIdMap = orderDao.selectOrderInstallItemIds(param);

					// 查询订单实际安装时间
					Date actualStartDate = orderDao.selectActualStartDate(vo);

					// 拼装订单安装计划
					for (int i = 0; i < orderProjectInstallItemToAdd.size(); i++) {
						OrderInstallItemVo orderInstallItemVo = orderProjectInstallItemToAdd.get(i);

						// 订单安装计划的订单安装项id
						for (int j = 0; j < installItemIdMap.size(); j++) {
							Map<String, Object> map = installItemIdMap.get(j);
							Object objOrderInstallItemId = map.get("orderInstallItemId");
							Object objOrderId = map.get("orderId");
							Object objProjectInstallItemId = map.get("projectInstallItemId");
							if (objOrderInstallItemId != null && objOrderId != null && objProjectInstallItemId != null) {
								// 比较订单id和安装项id，如果相等则取其订单安装项id
								Integer orderInstallItemId = (Integer) objOrderInstallItemId;
								Integer orderId = (Integer) objOrderId;
								Integer projectInstallItemId = (Integer) objProjectInstallItemId;
								if (orderInstallItemVo.getOrderId().equals(orderId) && orderInstallItemVo.getProjectInstallItemId().equals(projectInstallItemId)) {
									orderInstallItemVo.setId(orderInstallItemId);
									break;
								}
							}
						}
						orderInstallItemVo.preInsert();
						orderInstallItemVo.setStatus("1");
						orderInstallItemVo.setProjectInstallItemId(orderInstallItemVo.getId());

						orderInstallItemVo.setPlanIntoDate(new Date(actualStartDate.getTime() + orderInstallItemVo.getDaysPlanInto() * 1000l * 3600l * 24l));
						orderInstallItemVo.setPlanCompleteDate(new Date(actualStartDate.getTime() + orderInstallItemVo.getDaysPalnComplete() * 1000l * 3600l * 24l));
						if(InstallPlanConstantUtil.INSTALL_IS_TO_CHECKSIZE_1.equals(orderInstallItemVo.getIsToChecksize())){
							orderInstallItemVo.setAllowApplyChecksizeDate(new Date(actualStartDate.getTime() + orderInstallItemVo.getDaysPlanChecksize() * 1000l * 3600l * 24l));
						}
						
					}
					// 批量保存订单安装计划
					orderDao.batchSaveOrderInstallItemPlan(orderProjectInstallItemToAdd);
				}

			} else {
				// 如果jsp没有一个安装项,表明安装项都撤了
				// 删除所有, 同时保存页面上的所有安装项
				orderDao.deleteAllInstallItem(order.getOrderId());
				orderDao.deleteAllInstallItemPlan(order.getOrderId());
				
			}
			// 更新安装模式
			// 查找所有的安装项
			Map map = new HashMap<>();
			map.put("projectInstallItemIds", join);
			map.put("orderId", order.getOrderId());
			List<OrderInstallItemVo> allInstallItem = orderDao.selectOrderInstallItemIdsList(map);
			for (int i = 0; i < installMode.length; i++) {
				allInstallItem.get(i).setInstallMode(installMode[i]);
				// 更新安装项 --表里的安装项模式
				orderDao.updateInstallMode(allInstallItem.get(i));
				// 更新安装计划 --表里的安装项模式
				orderDao.updateInstallplanMode(allInstallItem.get(i));
			}
			
			
		} else if(Integer.parseInt(order.getOrderStatusNumber()) < 200){
			
				// 订单状态小于200
				// 删除所有, 同时保存页面上的所有安装项
				orderDao.deleteAllInstallItem(order.getOrderId());
				orderDao.deleteAllInstallItemPlan(order.getOrderId());
				
				// JSP页面的安装项
				if (null != installItemIds && installItemIds.length > 0) {
					for (int v = 0; v < installItemIds.length; v++) {
						OrderInstallItemVo vo2 = orderDao.findInstallItemByInstallItemId(Integer.parseInt(installItemIds[v]));
						// insert 保存即可,
						vo2.setOrderId(order.getOrderId());
						vo2.setInstallMode(installMode[v]);
						orderDao.saveOrderInstallItem(vo2);
					}
					
				} else {
					
					// 订单小于200 jsp没有安装项, 已经删了 不用做什么事情
				}
		}

	}

	public String findInstallModel(String id) {
		return dao.findInstallModel(id);
	}

	public List<String> isdealedwallfloor(ProjectOrderList projectOrderList) {
		return dao.isdealedwallfloor(projectOrderList);
	}

	public List<String> isdealedmainmaterial(ProjectOrderList projectOrderList) {
		return dao.isdealedmainmaterial(projectOrderList);
	}

	@Transactional(readOnly = false)
	public void updateStatusWall(String wallFloorIstrue, Integer orderId) {
		dao.updateStatusWall(wallFloorIstrue, orderId);

	}

	@Transactional(readOnly = false)
	public void updateStatusMain(String wallFloorIstrue, Integer orderId) {
		dao.updateStatusMain(wallFloorIstrue, orderId);

	}

	public BizMaterialsChoiceBill findChoiceBillCount(BizMaterialsChoiceBill bizMaterialsChoiceBill) {
		return dao.findChoiceBillCount(bizMaterialsChoiceBill);
	}

	public String queryDealedmainmaterial(String string) {
		return dao.queryDealedmainmaterial(string);
	}

	public String queryDealedwallfloor(String orderNumber) {
		return dao.queryDealedwallfloor(orderNumber);
	}

	public List<String> queryinOrder(ProjectOrderList projectOrderList) {
		return dao.queryinOrder(projectOrderList);
	}

	public Page<ProjectOrderList> findNotMaterialPage(Page<ProjectOrderList> page, ProjectOrderList projectOrderList) {
		projectOrderList.setPage(page);
		page.setList(dao.findNotMaterialPage(projectOrderList));
		return page;
	}

	public List<String> queryinOrder2(ProjectOrderList projectOrderList) {
		return dao.queryinOrder2(projectOrderList);
	}

	public List<String> queryinOrder3(ProjectOrderList projectOrderList) {
		return dao.queryinOrder3(projectOrderList);
	}

	public List<String> queryinOrderNot56(ProjectOrderList projectOrderList) {
		return dao.queryinOrderNot56(projectOrderList);
	}

}
