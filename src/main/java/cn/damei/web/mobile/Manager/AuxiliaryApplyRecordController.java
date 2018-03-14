package cn.damei.web.mobile.Manager;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.PurchaseDetailsVo;
import cn.damei.entity.mobile.Manager.PurchaseTwoVo;
import cn.damei.service.mobile.Manager.AuxiliaryApplyService;
import cn.damei.web.mobile.home.JobSiteController;
import cn.damei.dao.mobile.home.HomeReportDao;
import cn.damei.entity.mobile.home.ViewLog;



@Controller
@RequestMapping(value = "${adminPath}/app/manager/auxiliaryApplyRecord")
public class AuxiliaryApplyRecordController {
	
	@Autowired
	private AuxiliaryApplyService auxiliaryApplyService;
	
	
	@RequestMapping(value="record")
	public String record(Model model , String orderId){
		

		
		List<PurchaseTwoVo> purchaseList= auxiliaryApplyService.selectPurchaseByOrderId(Integer.parseInt(orderId));

		if(null!=purchaseList&&purchaseList.size()>0){
			String customerInfo = purchaseList.get(0).getCommunityName() + "-" + purchaseList.get(0).getBuildNumber()
					+ "-" + purchaseList.get(0).getBuildUnit() + "-" + purchaseList.get(0).getBuildRoom() + "-"
					+ purchaseList.get(0).getCustomerName();	
			model.addAttribute("customerInfo", customerInfo);
			model.addAttribute("purchaseList", purchaseList);
		}else{
			model.addAttribute("error", "该订单暂无采购单申请记录");
		}
		
		return "mobile/modules/Manager/auxiliary_record";
	}
	@Autowired
	private HomeReportDao logDao;

	
	@RequestMapping(value="auxiliaryDetails")

	public String auxiliaryDetails(String purchaseId,String applyTime,Model model,HttpServletRequest request) throws Exception{
		


		ViewLog log = new ViewLog();
		log.setBusinessIdInt(Integer.parseInt(purchaseId));
		log.setBusinessType("302");
		log.setBusinessViewerOnlyMark(SessionUtils.getManagerSession(request).getPhone());
		Integer integer = logDao.findView(log);
		if (null == integer || integer == 0) {
			log.setBusinessType("302");
			Date date = new Date();
			log.setBusinessViewDatetime(date);
			log.setBusinessViewDatetime(date);
			log.setBusinessViewerOnlyMark(SessionUtils.getManagerSession(request).getPhone());
			log.setCreateDate(date);
			log.setUpdateDate(date);
			log.setDelFlag("0");
			log.setBusinessIdInt(Integer.parseInt(JobSiteController.isNum(purchaseId) ? purchaseId : "0"));
			logDao.insertView(log);
		}
		
		
		
		

		Double totalMoney =(double)0;
		Integer totalCount = 0;




		

		PurchaseTwoVo purchaseVo = auxiliaryApplyService.findPurchaseDetails(Integer.valueOf(purchaseId));
		String customerInfo = purchaseVo.getCommunityName() +"-"+purchaseVo.getBuildNumber()+"-"+purchaseVo.getBuildUnit()+"-"+purchaseVo.getBuildRoom()+"-"+purchaseVo.getCustomerName();	
		
		

		List<PurchaseDetailsVo> purchaseDetail = auxiliaryApplyService.selectPurchaseDetailsByPurchaseId(Integer.valueOf(purchaseId));
		

		if(null!=purchaseDetail && purchaseDetail.size()>0){
			


			for (PurchaseDetailsVo detail : purchaseDetail) {
		

				if(detail.getPrice()!=0&&detail.getCount()!=0 || null!=detail.getPrice()&& null!=detail.getCount()){
					detail.setAuxiliaryMoney(detail.getPrice()*detail.getCount());	

					totalMoney+=detail.getAuxiliaryMoney();
					totalCount += detail.getCount();
				
				
				}
			}

			purchaseVo.setAuxiliaryAllMoney(totalMoney);


			purchaseVo.setTotalCount(totalCount);
	
			Set<PurchaseDetailsVo> set   = new HashSet<PurchaseDetailsVo>();
			for (PurchaseDetailsVo purchaseDetailsVo : purchaseDetail) {
				set.add(purchaseDetailsVo);
			}
			model.addAttribute("purchaseDetail", set);
			
		}else{
			model.addAttribute("error", "没有采购单编号");
		}
		
		
		model.addAttribute("purchaseVo", purchaseVo);
		model.addAttribute("customerInfo", customerInfo);

		return "mobile/modules/Manager/auxiliary_apply_details";
	}
	
	
	
	
	
	
}