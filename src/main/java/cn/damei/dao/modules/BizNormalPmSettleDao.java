/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNormalPmSettle;

import java.util.List;

/**
 * 经理申请结算单DAO接口
 * @author 梅浩
 * @version 2017-04-17
 */
@MyBatisDao
public interface BizNormalPmSettleDao extends CrudDao<BizNormalPmSettle> {


    /**
     *申请结算图片
     * @param settleId
     * @param picType 666
     * @return
     */
    public List<String> findSettlePicBySettleId(Integer settleId, String picType);

    /**
     * 根据门店查询结算节点
     * @param storeId
     * @return
     */
    public List<String> findSettleNodeByStoreId(Integer storeId);


    /**
     * 防止重复提交
     */

    public String checkSettleIsExist(Integer settleId);
}