/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.InspectItem;
import cn.damei.entity.modules.BizOrderQcBill;
import cn.damei.entity.modules.BizQcBill;
import cn.damei.entity.modules.ReportCheckDetails;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.entity.modules.BizQcCheckKind;

/**
 * 质检报告DAO接口
 * @author wyb
 * @version 2016-10-31
 */
@MyBatisDao
public interface BizOrderQcBillDao extends CrudDao2<BizOrderQcBill> {

	//通过订单id查询所属订单的报告单
	List<BizQcBill> findReport(int orderId);

	//通过订单id查询订单
	BizOrderQcBill findOrder(int orderId);

	//通过质检单id查询质检单信息
	BizQcBill findReportDetails(int qcBillId);

	//报告详情
	List<ReportCheckDetails> finditemById(ReportCheckDetails reportCheckDetails);

	//查询所有的检查分类
	List<BizQcCheckKind> findCheckKind();

	//通过质检单id查询质检图片
	List<ReportCheckDetailsPic> findPic(int qcBillId);

    List<InspectItem> findWorkerManagerInspectorPackageInfoByOrderId(Integer qcBillId);
}