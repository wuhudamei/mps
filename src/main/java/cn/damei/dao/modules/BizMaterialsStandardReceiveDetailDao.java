/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsStandardReceiveDetail;

/**
 * 标化辅材领取详情DAO接口
 * @author 汪文文
 * @version 2016-12-26
 */
@MyBatisDao
public interface BizMaterialsStandardReceiveDetailDao extends CrudDao2<BizMaterialsStandardReceiveDetail> {

	List<BizMaterialsStandardReceiveDetail> findDetailsByBillId(Integer materialsStandardReceiveBillId);

	List<BizMaterialsStandardReceiveDetail> findDetailsBySettleBillId(Integer billId);

	void insert1(List<BizMaterialsStandardReceiveDetail> insertList);

	void update1(List<BizMaterialsStandardReceiveDetail> updateList);

	void updateDetails(Integer materialsId, Integer billId, Double materialsAmount,Double number);
	//根据 billId materilsId 修改detail 的快照详情
	void updateDtaileSnape(BizMaterialsStandardReceiveDetail detail);
	//领取的操作
	void updateDtaileApplySnape(String billId);
	//通过billid查询 orderid
	String getOrderId(String billId);
}