/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Inspector.InspectItem;
import cn.damei.dao.modules.BizOrderQcBillDao;
import cn.damei.entity.modules.BizOrderQcBill;
import cn.damei.entity.modules.BizQcBill;
import cn.damei.entity.modules.ReportCheckDetails;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.entity.modules.BizQcCheckKind;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单质检报告Service
 * @author wyb
 * @version 2016-10-31
 */
@Service
@Transactional(readOnly = true)
public class BizOrderQcBillService extends CrudService2<BizOrderQcBillDao, BizOrderQcBill> {

	public BizOrderQcBill get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderQcBill> findList(BizOrderQcBill bizOrderQcBill) {
		return super.findList(bizOrderQcBill);
	}
	
	public Page<BizOrderQcBill> findPage(Page<BizOrderQcBill> page, BizOrderQcBill bizOrderQcBill) {
		return super.findPage(page, bizOrderQcBill);
	}

	//通过订单id查询所属订单的报告单
	public List<BizQcBill> findReport(int orderId) {
		return dao.findReport(orderId);
	}

	//通过订单id查询订单
	public BizOrderQcBill findOrder(int orderId) {
		return dao.findOrder(orderId);
	}

	//通过质检单id查询质检单信息
	public BizQcBill findReportDetails(int qcBillId) {
		return dao.findReportDetails(qcBillId);
	}

	//报告详情
	public List<ReportCheckDetails> finditemById(ReportCheckDetails reportCheckDetails) {
		List<ReportCheckDetails> list = dao.finditemById(reportCheckDetails);
		Double a = 0.00;
		if(null!=list && list.size()>0){
			for(ReportCheckDetails item :list){
				if(null!=item.getIsPunishMoney() && !"".equals(item.getIsPunishMoney()) && item.getIsPunishMoney().equals("1")){
					
					if(null==item.getPunishMoneyAmountReal()){
						item.setPunishMoneyAmountReal(a);
					}
					if( null==item.getPmPunishScore() ){
						item.setPmPunishScore(0);
					}
					if(null==item.getWorkerPunishAmount()){
						item.setWorkerPunishAmount(a);
					}
					if( null==item.getWorkerPunishScore() ){
						item.setWorkerPunishScore(0);
					}
					if(null==item.getQcPunishAmount()){
						item.setQcPunishAmount(a);
					}
					if(null==item.getQcPunishScore() ){
						item.setQcPunishScore(0);
					}
				}
				//查询任务包名称

				List<InspectItem> workerManagerInspectorPackageInfoByOrderId = dao.findWorkerManagerInspectorPackageInfoByOrderId(reportCheckDetails.getQcBillId());

				for (InspectItem inspectItem : workerManagerInspectorPackageInfoByOrderId) {
					if(inspectItem.getWorkerGroupLeaderName().equals(item.getWorkGroupPerson())){
						item.setTaskName(inspectItem.getPackName());
					}
				}

			}
			
			return list;
		}else{
			return null;
		}
		
	}
	
	//查询所有的检查分类
	public List<BizQcCheckKind> findCheckKind() {
		return dao.findCheckKind();
	}

	//通过质检单id查询质检图片
	public List<ReportCheckDetailsPic> findPic(int qcBillId) {
		return dao.findPic(qcBillId);
	}
	
	
	
}