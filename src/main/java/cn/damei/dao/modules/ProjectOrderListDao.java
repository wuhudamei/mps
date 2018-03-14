package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.entity.modules.ProjectOrderList;

@MyBatisDao
public interface ProjectOrderListDao extends CrudDao<ProjectOrderList> {

	List<BizMaterialsChoiceBillItem> findMaterialsChoice(Integer id);

	List<String> findDrawing(ProjectOrderList projectOrderList);

	List<String> findOrderIds(List<String> list);

	List<String> findwallAndFloor(ProjectOrderList projectOrderList);

	List<String> findAttached(ProjectOrderList projectOrderList);

	BizMaterialsChoiceBill findChoiceBillId(Integer orderID);

	List<String> findMaterial(ProjectOrderList projectOrderList);

	String findInstallModel(String id);

	List<String> isdealedwallfloor(ProjectOrderList projectOrderList);

	List<String> isdealedmainmaterial(ProjectOrderList projectOrderList);

	void updateStatusMain(String wallFloorIstrue, Integer orderId);

	void updateStatusWall(String wallFloorIstrue, Integer orderId);

	BizMaterialsChoiceBill findChoiceBillCount(BizMaterialsChoiceBill bizMaterialsChoiceBill);

	String queryDealedmainmaterial(String string);

	String queryDealedwallfloor(String orderNumber);

	List<BizMaterialsChoiceBillItem> findMaterialsChoicez(Integer id);

	List<String> queryinOrder(ProjectOrderList projectOrderList);

	List<String> queryinOrder2(ProjectOrderList projectOrderList);

	List<ProjectOrderList> findNotMaterialPage(ProjectOrderList projectOrderList);

	List<String> queryinOrder3(ProjectOrderList projectOrderList);

	List<String> queryinOrderNot56(ProjectOrderList projectOrderList);

}
