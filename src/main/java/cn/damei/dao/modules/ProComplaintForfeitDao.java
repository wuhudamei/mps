
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProComplaintForfeit;


@MyBatisDao
public interface ProComplaintForfeitDao extends CrudDao<ProComplaintForfeit> {

	void updateAgreeForfeit(ProComplaintForfeit proComplaintForfeit);

	void updateRefuseForfeit(ProComplaintForfeit proComplaintForfeit);

}