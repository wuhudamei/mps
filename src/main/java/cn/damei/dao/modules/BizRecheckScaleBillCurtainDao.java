package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizRecheckScaleBillCurtain;

/**
 * 上报复尺新版(20161107-20161113)
 * @author llp
 * 2016-11-15
 * 窗帘	
 * biz_recheck_scale_bill_curtain
 */
@MyBatisDao
public interface BizRecheckScaleBillCurtainDao extends CrudDao2<BizRecheckScaleBillCurtain>{

	List<BizRecheckScaleBillCurtain> getByRecheckID(Integer recheckIDs);

}
