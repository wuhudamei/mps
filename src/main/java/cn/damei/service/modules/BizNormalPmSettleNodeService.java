/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizNormalPmSettleNode;
import cn.damei.dao.modules.BizNormalPmSettleNodeDao;

/**
 * 结算节点配置Service
 * @author 梅浩
 * @version 2017-04-15
 */
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

	/**
	 * 节点存在就更新,不存在就插入
	 * @param storeId
	 * @param settleNodeIndex
	 * @return
	 */

	public  Integer checkCheckNodeIsExist(Integer storeId,Integer settleNodeIndex){

		return dao.checkCheckNodeIsExist(storeId,settleNodeIndex);
	}

	/**
	 * 存在, 更新结算节点
	 * @param node
	 */
	@Transactional(readOnly = false)
	public void updateSettleNodeByStoreIdAndSettleNodeIndex(BizNormalPmSettleNode node){


	dao.updateSettleNodeByStoreIdAndSettleNodeIndex(node);
	}
	/**
	 * 修改的回显
	 * @param storeId
	 */
	public BizNormalPmSettleNode findSettleNodeListByStoreId(Integer storeId){


		return dao.findSettleNodeListByStoreId(storeId);
	}

	public Integer checkCheckNodeIsExistPulas(Integer storeId, Integer projectMode) {
		// TODO Auto-generated method stub
		return dao.checkCheckNodeIsExistPulas(storeId,projectMode);
	}

	public Integer findSettleStage(BizNormalPmSettleNode bizNormalPmSettleNode) {
		// TODO Auto-generated method stub
		return dao.findSettleStage(bizNormalPmSettleNode);
	}

	public BizNormalPmSettleNode findSettleNodeList(BizNormalPmSettleNode bizNormalPmSettleNode) {
		// TODO Auto-generated method stub
		return dao.findSettleNodeList(bizNormalPmSettleNode);
	}

	public Integer checkCheckNodeIsExistChecked(BizNormalPmSettleNode bizNormalPmSettleNode) {
		// TODO Auto-generated method stub
		return dao.checkCheckNodeIsExistChecked(bizNormalPmSettleNode);
	}

	public BizNormalPmSettleNode querySettleNodeByParam(Map<String,Object> param){
		return dao.querySettleNodeByParam(param);
	}
	
}