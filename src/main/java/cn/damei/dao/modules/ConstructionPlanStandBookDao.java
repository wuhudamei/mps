package cn.damei.dao.modules;

import cn.damei.entity.modules.BizNodePlanExtend;
import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;




@MyBatisDao
public interface ConstructionPlanStandBookDao{


     List<BizNodePlanExtend> findPlanAndDoneTimeByOrderNo(String orderno);

}
