
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizNormalPmSettleNode;
import cn.damei.dao.modules.BizNormalPmSettleNodeDao;


@Service
@Transactional(readOnly = true)
public class BizNormalPmSettleNodeService extends CrudService<BizNormalPmSettleNodeDao, BizNormalPmSettleNode> {

	public BizNormalPmSettleNode get(String id) {
		return super.get(id);
	}
	
	public List<BizNormalPmSettleNode> findList(BizNormalPmSettleNode bizNormalPmSettleNode) {
		return super.findList(bizNormalPmSettleNode);
	}
	
	public Page<BizNormalPmSettleNode> findPage(Page<BizNormalPmSettleNode> page, BizNormalPmSettleNode bizNormalPmSettleNode) {
		return super.findPage(page, bizNormalPmSettleNode);
	}
	
	@Transactional(readOnly = false)
	public void save(BizNormalPmSettleNode bizNormalPmSettleNode) {
		super.save(bizNormalPmSettleNode);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizNormalPmSettleNode bizNormalPmSettleNode) {
		super.delete(bizNormalPmSettleNode);
	}



	public  Integer checkCheckNodeIsExist(Integer storeId,Integer settleNodeIndex){

		return dao.checkCheckNodeIsExist(storeId,settleNodeIndex);
	}


	@Transactional(readOnly = false)
	public void updateSettleNodeByStoreIdAndSettleNodeIndex(BizNormalPmSettleNode node){


	dao.updateSettleNodeByStoreIdAndSettleNodeIndex(node);
	}

	public BizNormalPmSettleNode findSettleNodeListByStoreId(Integer storeId){


		return dao.findSettleNodeListByStoreId(storeId);
	}

	public Integer checkCheckNodeIsExistPulas(Integer storeId, Integer projectMode) {

		return dao.checkCheckNodeIsExistPulas(storeId,projectMode);
	}

	public Integer findSettleStage(BizNormalPmSettleNode bizNormalPmSettleNode) {

		return dao.findSettleStage(bizNormalPmSettleNode);
	}

	public BizNormalPmSettleNode findSettleNodeList(BizNormalPmSettleNode bizNormalPmSettleNode) {

		return dao.findSettleNodeList(bizNormalPmSettleNode);
	}

	public Integer checkCheckNodeIsExistChecked(BizNormalPmSettleNode bizNormalPmSettleNode) {

		return dao.checkCheckNodeIsExistChecked(bizNormalPmSettleNode);
	}

	public BizNormalPmSettleNode querySettleNodeByParam(Map<String,Object> param){
		return dao.querySettleNodeByParam(param);
	}
	
}