/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 标化筒灯约检节点配置Service
 * @author lft
 * @version 2017-05-25
 */
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
	//获取 筒灯灯带 和标化辅料 
	public	List<Map<String,String>>getMaterialsTypeByType(String type){
	  return dao.getMaterialsTypeByType(type);
	}
	//通过门店 和工程模式 获取 约检节点列表
	public	List<Map<String,String>>getCheckNodeListByStoreIdAndMode(@Param("storeId")String storeId,@Param("projectMode")String projectMode){
		return dao.getCheckNodeListByStoreIdAndMode(storeId, projectMode);
	}
	//根据 门店 模式 材料大类 获取 条数
	public	Integer getCheckNodeByOther(String storeId,String projectMode,String materialType){
		return dao.getCheckNodeByOther(storeId, projectMode, materialType);	
	}
}