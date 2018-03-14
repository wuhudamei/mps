/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizProjectProgressBoning;

import java.util.List;

/**
 * 工程进度大看板DAO接口
 * @author qww
 * @version 2016-10-26
 */
@MyBatisDao
public interface BizProjectProgressBoningDao extends CrudDao2<BizProjectProgressBoning> {

    /**
     * 查询订单
     * @return
     */
    public List<BizProjectProgressBoning> queryOrderByCondition();

    /**
     * 根据订单id查询订单
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryOrderByOrderId(Integer orderId);

    /**
     * 根据订单查询
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryByOrderId(Integer orderId);

    /**
     * 根据订单id更新
     * @param boning
     */
    public void updateByOrderId(BizProjectProgressBoning boning);

    /**
     * 根据订单查询节点
     * @param orderId
     * @return
     */
    public List<BizProjectProgressBoning> queryNodePlanByOrderId(Integer orderId);

    /**
     * 查询节点1的实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode1ByOrderId(Integer orderId);

    /**
     * 查询节点2的实际日期、期望日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode2ByOrderId(Integer orderId);

    /**
     * 查询节点3的实际日期、期望日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode3ByOrderId(Integer orderId);

    /**
     * 查询节点4的实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode4ByOrderId(Integer orderId);

    /**
     * 查询节点5的实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode5ByOrderId(Integer orderId);

    /**
     * 查询节点6的实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode6ByOrderId(Integer orderId);

    /**
     * 查询节点7的实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode7ByOrderId(Integer orderId);

    /**
     * 查询节点8的催款日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode8ByOrderId(Integer orderId);
    
    /**
     * 查询节点8的实际收款时间
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode8ActualDateByOrderId(Integer orderId);

    /**
     * 查询节点9的实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode9ByOrderId(Integer orderId);

    /**
     * 查询节点10的实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode10ByOrderId(Integer orderId);

    /**
     * 查询节点11的安装日期、实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode11ByOrderId(Integer orderId);

    /**
     * 查询节点12的安装日期、实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode12ByOrderId(Integer orderId);

    /**
     * 查询节点13的安装日期、实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode13ByOrderId(Integer orderId);

    /**
     * 查询节点14的安装日期、实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode14ByOrderId(Integer orderId);

    /**
     * 查询节点15的安装日期、实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode15ByOrderId(Integer orderId);

    /**
     * 查询节点16的安装日期、实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode16ByOrderId(Integer orderId);

    /**
     * 查询节点17的安装日期、实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode17ByOrderId(Integer orderId);

    /**
     * 查询节点18的安装日期、实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode18ByOrderId(Integer orderId);

    /**
     * 查询节点19的实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode19ByOrderId(Integer orderId);
    
    /**
     * 查询节点20（尾款）的实际日期
     */
    
    public BizProjectProgressBoning queryNode20ActualDateByOrderId(Integer orderId);

    /**
     * 查询节点21的安装日期、实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode21ByOrderId(Integer orderId);

    /**
     * 查询节点22的安装日期、实际日期
     * @param orderId
     * @return
     */
    public BizProjectProgressBoning queryNode22ByOrderId(Integer orderId);
    
    public void updateOrder(Integer orderId);
    
    public List<BizProjectProgressBoning> queryIsScrapOrder();
    
    public BizProjectProgressBoning queryDelayDaysByOrderId(Integer orderId);

}