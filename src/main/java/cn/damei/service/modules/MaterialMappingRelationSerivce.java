package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.MaterialMappingRelationDao;
import cn.damei.entity.modules.BaseEntity;
import cn.damei.entity.modules.MaterialMappingRelation;

@Service
@Transactional(readOnly = false)
public class MaterialMappingRelationSerivce  extends CrudService<MaterialMappingRelationDao, MaterialMappingRelation>{

	public List<BaseEntity> findMainItem(String storeId, String projectMode) {
		// TODO Auto-generated method stub
		return dao.findMainItem(storeId,projectMode);
	}

	public List<BaseEntity> findOneCategory(String level) {
		// TODO Auto-generated method stub
		return dao.findOneCategory(level);
	}

	public List<BaseEntity> findTowCategory(String level, String parentId) {
		// TODO Auto-generated method stub
		return dao.findTowCategory(level,parentId);
	}

	public void insertChoiceCategory(List<MaterialMappingRelation> list) {
		// TODO Auto-generated method stub
		 dao.insertChoiceCategory(list);
	}

	public void deleteRelation(String id) {
		// TODO Auto-generated method stub
		dao.deleteRelation(id);
	}

}
