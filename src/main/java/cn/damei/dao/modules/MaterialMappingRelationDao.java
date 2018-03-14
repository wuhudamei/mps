package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BaseEntity;
import cn.damei.entity.modules.MaterialMappingRelation;
@MyBatisDao
public interface MaterialMappingRelationDao extends CrudDao<MaterialMappingRelation>{
	/**
	 * 查询主材安装项
	 * @param storeId
	 * @param projectMode 
	 * @return
	 */
	List<BaseEntity> findMainItem(String storeId, String projectMode);
	/**
	 * 查询类目
	 * @return
	 */
	List<BaseEntity> findOneCategory(String level);
	/**
	 * 查询二级类目
	 * @param level
	 * @param parentId
	 * @return
	 */
	List<BaseEntity> findTowCategory(String level, String parentId);
	/**
	 * 插入关系表
	 * @param list
	 * @return
	 */
	void insertChoiceCategory(List<MaterialMappingRelation> list);
	/**
	 * 删除
	 * @param id
	 */
	void deleteRelation(String id);

}
