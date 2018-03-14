package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ManagerNormalSettle;
import cn.damei.entity.mobile.Manager.ManagerTraditionSettleEntity;

import java.util.List;


@MyBatisDao
public interface ManagerTraditionSettleDao {


    List<ManagerTraditionSettleEntity> findSettleOrderList(ManagerTraditionSettleEntity entity);


    ManagerTraditionSettleEntity findSettleInfoByOrderId(Integer orderId);


    ManagerTraditionSettleEntity findSettleInfoDetailByIndexAndOrderId(String orderId,String index);


    boolean saveSettleApplyInfo(ManagerNormalSettle settle);


    List<ManagerNormalSettle> findSettleInfoDetailBySettleId(String settleId);


    Integer checkIsSettleExist(Integer orderId,Integer settleNodeId);
    
    
     ManagerNormalSettle queryLastNormalSettleNode(Integer orderId);



    List<Integer> findIsCheckDoneInfoByOrderId(Integer orderId);

}
