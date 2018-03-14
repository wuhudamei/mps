package cn.damei.dao.mobile.Inspector;

import cn.damei.entity.mobile.Inspector.PqcOrderEntity;

import java.util.List;


public interface PqcOrderInterface {



    List<PqcOrderEntity> selectOrderInfoByPqcEmployeeId(Integer pqcEmployeeId);

}
