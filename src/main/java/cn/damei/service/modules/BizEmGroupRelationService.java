
package cn.damei.service.modules;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizEmGroupRelationDao;
import cn.damei.entity.modules.BizEmgrouprelation;


@Service
public class BizEmGroupRelationService extends CrudService<BizEmGroupRelationDao, BizEmgrouprelation> {

	@Resource
	private BizEmGroupRelationDao bizEmGroupRelationDao;

	@Transactional(readOnly = false)
	public void InsertEmpGroupRelations(List<BizEmgrouprelation> bizEmgrouprelation, String groupId) {

		for (BizEmgrouprelation emp : bizEmgrouprelation) {
			emp.setGroupId(groupId);
			bizEmGroupRelationDao.insertEmpGroupRelation(emp);
		}
	}

	@Transactional(readOnly = false)
	public void deleteRelation(Integer id) {
		bizEmGroupRelationDao.deleteRelation(id);
	}


	@Transactional(readOnly = false)
	public void deleteEmgrouprelationByGroupId(String groupId) {
		bizEmGroupRelationDao.deleteEmgrouprelationByGroupId(groupId);
	}

}