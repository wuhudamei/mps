
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitTraditionOrderData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface BizCustomerReturnVisitTraditionOrderDataDao extends CrudDao2<BizCustomerReturnVisitTraditionOrderData> {


	Integer findExistCount(Integer orderId, Integer qcCheckNodeId);


    List<BizCustomerReturnVisitTraditionOrderData> findPageForTraditionOrder(BizCustomerReturnVisitTraditionOrderData bizCustomerReturnVisitTraditionOrderData);


    List<BizCustomerReturnVisitTraditionOrderData> findReturnVisitNode(Integer orderId);


    void updateStatus(BizCustomerReturnVisitTraditionOrderData bto);

    List<Map<String,Object>> queryReturnVisitNodeByStoreId(@Param("storeId")String storeId);


    void updateStatusHaveDone(Integer orderId);


    List<Map<String,Object>> findIsThereNode(String storeId, Integer qcCheckNodeId);
}