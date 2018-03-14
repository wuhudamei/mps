
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizMaterialsStandardQcCheckNodeDao;
import cn.damei.entity.modules.BizMaterialsStandardQcCheckNode;


@Service
@Transactional(readOnly = true)
public class BizMaterialsStandardQcCheckNodeService extends CrudService2<BizMaterialsStandardQcCheckNodeDao, BizMaterialsStandardQcCheckNode> {

	public BizMaterialsStandardQcCheckNode get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMaterialsStandardQcCheckNode> findList(BizMaterialsStandardQcCheckNode bizMaterialsStandardQcCheckNode) {
		return super.findList(bizMaterialsStandardQcCheckNode);
	}
	
	public Page<BizMaterialsStandardQcCheckNode> findPage(Page<BizMaterialsStandardQcCheckNode> page, BizMaterialsStandardQcCheckNode bizMaterialsStandardQcCheckNode) {
		return super.findPage(page, bizMaterialsStandardQcCheckNode);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialsStandardQcCheckNode bizMaterialsStandardQcCheckNode) {
		super.save(bizMaterialsStandardQcCheckNode);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialsStandardQcCheckNode bizMaterialsStandardQcCheckNode) {
		super.delete(bizMaterialsStandardQcCheckNode);
	}

	public	List<Map<String,String>>getMaterialsTypeByType(String type){
	  return dao.getMaterialsTypeByType(type);
	}

	public	List<Map<String,String>>getCheckNodeListByStoreIdAndMode(@Param("storeId")String storeId,@Param("projectMode")String projectMode){
		return dao.getCheckNodeListByStoreIdAndMode(storeId, projectMode);
	}

	public	Integer getCheckNodeByOther(String storeId,String projectMode,String materialType){
		return dao.getCheckNodeByOther(storeId, projectMode, materialType);	
	}
}