
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallPlanAdvanceApply;


@MyBatisDao
public interface BizOrderInstallPlanAdvanceApplyDao extends CrudDao2<BizOrderInstallPlanAdvanceApply> {


	BizOrderInstallPlanAdvanceApply findInstallPlanAdvanceApplyLastRecord(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply);


	Integer findInstallPlanAdvanceApplyCount(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply);


	void saveOrderInstallItemForInstallAdvanceApply(Map<String, Object> resultMap);


	void saveOrderInstallPlanForInstallAdvanceApply(Map<String, Object> resultMap);

	void saveOrderInstallItemForChecksizeAdvanceApply(Map<String, Object> resultMap);
	

	void saveOrderInstallPlanForChecksizeAdvanceApply(Map<String, Object> resultMap);
	
}