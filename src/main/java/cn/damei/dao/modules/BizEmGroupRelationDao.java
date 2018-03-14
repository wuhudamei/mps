
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmgrouprelation;


@MyBatisDao
public interface BizEmGroupRelationDao extends CrudDao<BizEmgrouprelation> {



	public void insertEmpGroupRelation(BizEmgrouprelation bizEmployeegroup);

	public void deleteRelation(Integer id);

	public void deleteEmgrouprelationByGroupId(String groupId);

}