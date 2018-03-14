package cn.damei.dao.modules;

import cn.damei.entity.modules.BizNodePlanExtend;
import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;


/**
 * <dl>
 * <dd>描述:施工计划台账Dao</dd>
 * <dd>公司: 大城若谷信息技术有限公司</dd>
 * <dd>创建时间：2017/7/3</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */

@MyBatisDao
public interface ConstructionPlanStandBookDao{

     /**
      * 查询计划时间和实际完成时间
      */
     List<BizNodePlanExtend> findPlanAndDoneTimeByOrderNo(String orderno);

}
