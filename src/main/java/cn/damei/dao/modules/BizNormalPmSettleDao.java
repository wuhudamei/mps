
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNormalPmSettle;

import java.util.List;


@MyBatisDao
public interface BizNormalPmSettleDao extends CrudDao<BizNormalPmSettle> {



    public List<String> findSettlePicBySettleId(Integer settleId, String picType);


    public List<String> findSettleNodeByStoreId(Integer storeId);




    public String checkSettleIsExist(Integer settleId);
}