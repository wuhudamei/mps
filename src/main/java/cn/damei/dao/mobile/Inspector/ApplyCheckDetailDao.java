package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ApplyCheckOrderEntity;


import java.util.List;


@MyBatisDao
public interface ApplyCheckDetailDao {


    List<ApplyCheckOrderEntity> findCheckDoneOrderListByManagerId(ApplyCheckOrderEntity orderEntity);


    ApplyCheckOrderEntity applyCheckDetailByOrderId(Integer orderId);


    List<String> picList(Integer qcBillId);
}
