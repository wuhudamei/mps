package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;
import cn.damei.entity.modules.OrderFloor;
import cn.damei.entity.modules.OrderFloor2;

import java.util.List;
import java.util.Map;

/**
 * 订单地板地砖面积Dao
 * @author hyh
 *
 */
@MyBatisDao
public interface OrderFloorDao extends CrudDao2<OrderFloor2>{

    public OrderFloor findOrderFloorByOrderNumber(String orderNumber);

    public List<BizOrderTaskpackageProcedure> queryProduceInfoByParam(Map<String,Object> param);
}
