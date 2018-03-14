package cn.damei.service.modules;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;
import cn.damei.dao.modules.OrderFloorDao;
import cn.damei.entity.modules.OrderFloor;
import cn.damei.entity.modules.OrderFloor2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 订单地板地砖面积Service
 * Created by hyh on 2017/11/6.
 */
@Service
@Transactional(readOnly = true)
public class OrderFloorService  extends CrudService2<OrderFloorDao,OrderFloor2>{

    public OrderFloor findOrderFloorByOrderNumber(String orderNumber){
        return dao.findOrderFloorByOrderNumber(orderNumber);
    }

    public List<BizOrderTaskpackageProcedure> queryProduceInfoByParam(Map<String,Object> param){
        return dao.queryProduceInfoByParam(param);
    }

}
