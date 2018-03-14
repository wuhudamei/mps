package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BaseEntity;
import cn.damei.entity.modules.MaterialMappingRelation;
@MyBatisDao
public interface MaterialMappingRelationDao extends CrudDao<MaterialMappingRelation>{

	List<BaseEntity> findMainItem(String storeId, String projectMode);

	List<BaseEntity> findOneCategory(String level);

	List<BaseEntity> findTowCategory(String level, String parentId);

	void insertChoiceCategory(List<MaterialMappingRelation> list);

	void deleteRelation(String id);

}
