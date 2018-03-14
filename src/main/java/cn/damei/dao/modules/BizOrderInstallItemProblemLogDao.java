
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallItemProblemLog;


@MyBatisDao
public interface BizOrderInstallItemProblemLogDao extends CrudDao2<BizOrderInstallItemProblemLog> {

	BizOrderInstallItemProblemLog queryByProblemId(Map<String, Object> map);
	
}