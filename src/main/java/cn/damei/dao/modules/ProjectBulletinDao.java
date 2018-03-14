
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProjectBulletin;


@MyBatisDao
public interface ProjectBulletinDao extends CrudDao2<ProjectBulletin> {

	List<ProjectBulletin> getByIdAndNodePlanOrderId(Integer id);

	List<ProjectBulletin> getByShowViewOrderId(Integer orderId);

	ProjectBulletin getById(Integer orderId);
	
}