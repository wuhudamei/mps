
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNormalPmSettleNode;


@MyBatisDao
public interface BizNormalPmSettleNodeDao extends CrudDao<BizNormalPmSettleNode> {



    public  Integer checkCheckNodeIsExist(Integer storeId,Integer settleNodeIndex);


    public void updateSettleNodeByStoreIdAndSettleNodeIndex(BizNormalPmSettleNode node);


    public BizNormalPmSettleNode findSettleNodeListByStoreId(Integer storeId);
    
    
    public BizNormalPmSettleNode querySettleNodeByParam(Map<String,Object> param);

	public Integer checkCheckNodeIsExistPulas(Integer storeId, Integer projectMode);

	public Integer findSettleStage(BizNormalPmSettleNode bizNormalPmSettleNode);

	public BizNormalPmSettleNode findSettleNodeList(BizNormalPmSettleNode bizNormalPmSettleNode);

	public Integer checkCheckNodeIsExistChecked(BizNormalPmSettleNode bizNormalPmSettleNode);

}