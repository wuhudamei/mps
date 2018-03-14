package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ManagerNormalSettle;
import cn.damei.entity.mobile.Manager.ManagerTraditionSettleEntity;

import java.util.List;

/**
 * Created by joseph on 2017/4/14.
 */
@MyBatisDao
public interface ManagerTraditionSettleDao {

    /**
     * 根据经理id ,(搜索模糊text)查询该经理下的所有订单
     * @param entity
     * @return ManagerTraditionSettleEntity
     */
    List<ManagerTraditionSettleEntity> findSettleOrderList(ManagerTraditionSettleEntity entity);

    /**
     * 根据订单id,查询结算模板节点及对应结算状态 和一些限制条件
     * @param orderId
     * @return ManagerTraditionSettleEntity
     */
    ManagerTraditionSettleEntity findSettleInfoByOrderId(Integer orderId);

    /**
     * 第一次申请
     * @param orderId
     * @return
     */
    ManagerTraditionSettleEntity findSettleInfoDetailByIndexAndOrderId(String orderId,String index);

    /**
     * 保存申请的结算单
     * @param settle
     * @return
     */
    boolean saveSettleApplyInfo(ManagerNormalSettle settle);

    /**
     * 之后状态查看结算单的详情
     * @param settleId
     * @return 子类映射
     */
    List<ManagerNormalSettle> findSettleInfoDetailBySettleId(String settleId);

    /**
     * 防重复提交
     * @param orderId
     * @param settleNodeId
     * @return
     */
    Integer checkIsSettleExist(Integer orderId,Integer settleNodeId);
    
    
     ManagerNormalSettle queryLastNormalSettleNode(Integer orderId);


    /**
     * 重新业务梳理, 验收bug修复sql
     */
    List<Integer> findIsCheckDoneInfoByOrderId(Integer orderId);

}
