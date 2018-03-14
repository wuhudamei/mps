package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ApplyCheckPlanEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/6/13.
 */
@MyBatisDao
public interface ApplyCheckPlanDao extends PqcOrderInterface {



    List<ApplyCheckPlanEntity> findList( Map<String,Object> map );
    List<Integer> findOrderInfoDoneByPqcId(Integer pqcId);


    List<ApplyCheckPlanEntity> allStatusNode(String OrderId);
    List<ApplyCheckPlanEntity> nodeApply(String OrderId);
}
