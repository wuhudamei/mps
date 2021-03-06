package cn.damei.service.mobile.home;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.dao.mobile.Inspector.ReportCheckDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.dao.mobile.home.HomeReportDao;
import cn.damei.entity.mobile.home.CheckBreak;
import cn.damei.entity.mobile.home.CheckItem;
import cn.damei.entity.mobile.home.Order;
import cn.damei.entity.mobile.home.Report;
import cn.damei.entity.mobile.home.ViewLog;

@Service
@Transactional(readOnly=true)
public class HomeReportService {

	
	@Autowired
	private HomeReportDao dao;
	@Autowired
	private ReportCheckDao reportCheckDao;


	public List<Order> findOrderList(String customerPhone) {
		Order order = new Order();
		order.setCustomerPhone(customerPhone);
		List<Order> list = dao.findOrderList(order);
		if(null!=list && list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	
	

	public Order findQcBill(String customerPhone, Integer orderId) {

		Order order = new Order();
		order.setOrderId(orderId);
		order.setCustomerPhone(customerPhone);
		Order list = dao.findQcBill(order);
	
		if(null!=list){
			return list;
		}else{
			return null;
		}
	}



	public Report reportDetail(Integer qcBillId) {

		Report report = dao.reportDetail(qcBillId);
		if(null!=report){
			return report;
		}else{
			
			return null;
		}
	}



	public List<ReportCheckDetailsPic> findPic(Integer qcBillId) {
		List<ReportCheckDetailsPic> picList = reportCheckDao.findPic(qcBillId);
		
		if(null!=picList && picList.size()>0){
			return picList;
		}else{
			return null;
		}
		
	}

	public String findPicBefore() {
		String baseUrl = null;
		try {
			baseUrl = PicRootName.picPrefixName();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(null!=baseUrl && !baseUrl.equals("")){
			return baseUrl;
		}else{
			return null;
		}
		
	}



	public CheckItem findCheckBreak(Integer qcBillItemId) {
		
		CheckItem checkItem = dao.findCheckBreak(qcBillItemId);
		Double a = 0.00;
		if(null!=checkItem){
			if(null!=checkItem.getIsPunishMoney() && !"".equals(checkItem.getIsPunishMoney()) && checkItem.getIsPunishMoney().equals("1")){
				if(null==checkItem.getPunishMoneyAmountReal()){
					checkItem.setPunishMoneyAmountReal(a);
				}
				if(null==checkItem.getPmPunishScore()){
					checkItem.setPmPunishScore(0);
				}
				if(null==checkItem.getWorkerPunishAmount()){
					checkItem.setWorkerPunishAmount(a);
				}
				if(null==checkItem.getWorkerPunishScore()){
					checkItem.setWorkerPunishScore(0);
				}
				if(null==checkItem.getQcPunishAmount()){
					checkItem.setQcPunishAmount(a);
				}
				if(null==checkItem.getQcPunishScore()){
					checkItem.setQcPunishScore(0);
				}
				

			}
			return checkItem;
		}else{
			return null;
		}
	}



	public Integer findView(Integer qcBillId, String customerPhone) {
		
		ViewLog viewLog = new ViewLog();
		viewLog.setBusinessIdInt(qcBillId);
		viewLog.setBusinessType(ConstantUtils.VIEW_lOG_HOME_REPORT);
		viewLog.setBusinessViewerOnlyMark(customerPhone);
		
		return dao.findView(viewLog);
	}



	@Transactional(readOnly=false)
	public void insertView(Integer qcBillId, String customerPhone) {

		Date date = new Date();
		ViewLog viewLog = new ViewLog();
		viewLog.setBusinessIdInt(qcBillId);
		viewLog.setBusinessType(ConstantUtils.VIEW_lOG_HOME_REPORT);
		viewLog.setBusinessViewDatetime(date);
		viewLog.setBusinessViewerOnlyMark(customerPhone);
		viewLog.setCreateDate(date);
		viewLog.setUpdateDate(date);
		viewLog.setDelFlag("0");
		
		dao.insertView(viewLog);
		
	}


	public List<Report> queryQcBillList(Integer id) {

		return dao.queryQcBillList(id);
	}


	public List<CheckBreak> queryCheckBreaks(Integer qcBillItemId) {

		return dao.queryCheckBreaks(qcBillItemId);
	}

	
	
}
