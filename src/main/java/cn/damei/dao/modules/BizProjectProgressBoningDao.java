
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizProjectProgressBoning;

import java.util.List;


@MyBatisDao
public interface BizProjectProgressBoningDao extends CrudDao2<BizProjectProgressBoning> {


    public List<BizProjectProgressBoning> queryOrderByCondition();


    public BizProjectProgressBoning queryOrderByOrderId(Integer orderId);


    public BizProjectProgressBoning queryByOrderId(Integer orderId);


    public void updateByOrderId(BizProjectProgressBoning boning);


    public List<BizProjectProgressBoning> queryNodePlanByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode1ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode2ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode3ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode4ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode5ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode6ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode7ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode8ByOrderId(Integer orderId);
    

    public BizProjectProgressBoning queryNode8ActualDateByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode9ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode10ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode11ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode12ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode13ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode14ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode15ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode16ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode17ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode18ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode19ByOrderId(Integer orderId);
    

    
    public BizProjectProgressBoning queryNode20ActualDateByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode21ByOrderId(Integer orderId);


    public BizProjectProgressBoning queryNode22ByOrderId(Integer orderId);
    
    public void updateOrder(Integer orderId);
    
    public List<BizProjectProgressBoning> queryIsScrapOrder();
    
    public BizProjectProgressBoning queryDelayDaysByOrderId(Integer orderId);

}