/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProComplaintForfeit;

/**
 * 客诉罚款DAO接口
 * 
 * @author ZTW
 * @version 2017-10-27
 */
@MyBatisDao
public interface ProComplaintForfeitDao extends CrudDao<ProComplaintForfeit> {

	void updateAgreeForfeit(ProComplaintForfeit proComplaintForfeit);

	void updateRefuseForfeit(ProComplaintForfeit proComplaintForfeit);

}