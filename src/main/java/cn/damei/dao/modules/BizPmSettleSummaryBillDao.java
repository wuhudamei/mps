/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmSettleSummaryBill;

import java.util.List;

/**
 * 结算汇总单DAO接口
 * @author qww
 * @version 2016-12-26
 */
@MyBatisDao
public interface BizPmSettleSummaryBillDao extends CrudDao2<BizPmSettleSummaryBill> {

    public void insertBatch(List<BizPmSettleSummaryBill> list);

    public List<BizPmSettleSummaryBill> findSummaryBillList(BizPmSettleSummaryBill bill);

    public List<BizPmSettleSummaryBill> findSummaryBillListPbc(BizPmSettleSummaryBill bill);
}