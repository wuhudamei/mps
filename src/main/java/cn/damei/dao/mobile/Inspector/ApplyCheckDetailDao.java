package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ApplyCheckOrderEntity;


import java.util.List;

/**
 * Created by joseph on 2017/4/22.
 */
@MyBatisDao
public interface ApplyCheckDetailDao {

    /**
     * 查询项目经理下的验收订单详情
     * @param orderEntity
     * @return
     */
    List<ApplyCheckOrderEntity> findCheckDoneOrderListByManagerId(ApplyCheckOrderEntity orderEntity);

    /**
     * 根据订单Id查询详情
     * @param orderId
     * @return
     */
    ApplyCheckOrderEntity applyCheckDetailByOrderId(Integer orderId);


    List<String> picList(Integer qcBillId);
}
