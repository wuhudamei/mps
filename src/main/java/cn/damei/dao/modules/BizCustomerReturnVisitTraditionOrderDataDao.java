/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitTraditionOrderData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客户回访记录DAO接口
 * @author 王硕
 * @version 2017-12-08
 */
@MyBatisDao
public interface BizCustomerReturnVisitTraditionOrderDataDao extends CrudDao2<BizCustomerReturnVisitTraditionOrderData> {

	//查询表中是否存在这条数据
	Integer findExistCount(Integer orderId, Integer qcCheckNodeId);

	//传统待回访订单查询
    List<BizCustomerReturnVisitTraditionOrderData> findPageForTraditionOrder(BizCustomerReturnVisitTraditionOrderData bizCustomerReturnVisitTraditionOrderData);

    //查询需要变更状态的节点
    List<BizCustomerReturnVisitTraditionOrderData> findReturnVisitNode(Integer orderId);

    //变更状态为0（过期）
    void updateStatus(BizCustomerReturnVisitTraditionOrderData bto);
    //根据门店查询对应的回访节点
    List<Map<String,Object>> queryReturnVisitNodeByStoreId(@Param("storeId")String storeId);

    //变更状态为2（已回访）
    void updateStatusHaveDone(Integer orderId);

    //查询回访节点表是否设置过这个节点
    List<Map<String,Object>> findIsThereNode(String storeId, Integer qcCheckNodeId);
}