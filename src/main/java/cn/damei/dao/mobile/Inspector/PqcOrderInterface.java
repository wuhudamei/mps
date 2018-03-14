package cn.damei.dao.mobile.Inspector;

import cn.damei.entity.mobile.Inspector.PqcOrderEntity;

import java.util.List;

/**
 * Created by joseph on 2017/6/13.
 * 订单数据接口
 */
public interface PqcOrderInterface {


    /**
     * 根据质检员id 查询订单的数据
     * @param pqcEmployeeId
     * @return
     */
    List<PqcOrderEntity> selectOrderInfoByPqcEmployeeId(Integer pqcEmployeeId);

}
