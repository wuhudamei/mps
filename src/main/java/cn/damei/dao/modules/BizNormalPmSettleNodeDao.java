/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNormalPmSettleNode;

/**
 * 结算节点配置DAO接口
 * @author 梅浩
 * @version 2017-04-15
 */
@MyBatisDao
public interface BizNormalPmSettleNodeDao extends CrudDao<BizNormalPmSettleNode> {


    /**
     * 节点存在就更新,不存在就插入
     * @param storeId
     * @param settleNodeIndex
     * @return
     */
    public  Integer checkCheckNodeIsExist(Integer storeId,Integer settleNodeIndex);

    /**
     * 存在, 更新结算节点
     * @param node
     */
    public void updateSettleNodeByStoreIdAndSettleNodeIndex(BizNormalPmSettleNode node);

    /**
     * 修改的回显
     * @param storeId
     */
    public BizNormalPmSettleNode findSettleNodeListByStoreId(Integer storeId);
    
    
    public BizNormalPmSettleNode querySettleNodeByParam(Map<String,Object> param);

	public Integer checkCheckNodeIsExistPulas(Integer storeId, Integer projectMode);

	public Integer findSettleStage(BizNormalPmSettleNode bizNormalPmSettleNode);

	public BizNormalPmSettleNode findSettleNodeList(BizNormalPmSettleNode bizNormalPmSettleNode);

	public Integer checkCheckNodeIsExistChecked(BizNormalPmSettleNode bizNormalPmSettleNode);

}