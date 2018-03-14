package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderDemolitionBuild;
import cn.damei.entity.mobile.Manager.SignDetail;
import cn.damei.entity.modules.Order;
/** 
* @ClassName: OrderDemolitionBuildDao 
* @Description: 拆改交底
* @author zkj  
* @date 2017年10月19日 下午2:09:56 
* @version V1.0 
*/
@MyBatisDao
public interface OrderDemolitionBuildDao extends CrudDao2<OrderDemolitionBuild>{

	List<Order> findOrderDemolitionBuildList(Integer id);

	SignDetail findOrderSignDatetime(SignDetail signDetail);

	boolean isDisclose(String orderId);

}
