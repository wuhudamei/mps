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

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月27日 下午3:55:52 
 */

@Controller
@RequestMapping(value = "${adminPath}/app/manager/auxiliaryApplyRecord")
public class AuxiliaryApplyRecordController {
	
	@Autowired
	private AuxiliaryApplyService auxiliaryApplyService;
	
	
	@RequestMapping(value="record")
	public String record(Model model , String orderId){
		
		//根据orderId 查询采购单
		
		List<PurchaseTwoVo> purchaseList= auxiliaryApplyService.selectPurchaseByOrderId(Integer.parseInt(orderId));
		//采购单包括: 1:编号  2:状态  3:第几次申请  4:期望进场日期  5:申请时间
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
										//采购单Id 				//顾客信息
	public String auxiliaryDetails(String purchaseId,String applyTime,Model model,HttpServletRequest request) throws Exception{
		

		// 插入已阅读log表
		ViewLog log = new ViewLog();
		log.setBusinessIdInt(Integer.parseInt(purchaseId));
		log.setBusinessType("302");// 辅料申请
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
		
		
		
		
		//根据采购单编号 查询详情 
		Double totalMoney =(double)0;
		Integer totalCount = 0;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		if(StringUtils.isBlank(applyTime)){
//			applyTime = sdf.format(new Date());
//		}
		
		//根据采购单id查询采购单信息
		PurchaseTwoVo purchaseVo = auxiliaryApplyService.findPurchaseDetails(Integer.valueOf(purchaseId));
		String customerInfo = purchaseVo.getCommunityName() +"-"+purchaseVo.getBuildNumber()+"-"+purchaseVo.getBuildUnit()+"-"+purchaseVo.getBuildRoom()+"-"+purchaseVo.getCustomerName();	
		
		
		//辅料详情
		List<PurchaseDetailsVo> purchaseDetail = auxiliaryApplyService.selectPurchaseDetailsByPurchaseId(Integer.valueOf(purchaseId));
		
		//如果有详情
		if(null!=purchaseDetail && purchaseDetail.size()>0){
			

			//设置辅料小计总金额
			for (PurchaseDetailsVo detail : purchaseDetail) {
		
				//如果价格和数量不为0 不位空
				if(detail.getPrice()!=0&&detail.getCount()!=0 || null!=detail.getPrice()&& null!=detail.getCount()){
					detail.setAuxiliaryMoney(detail.getPrice()*detail.getCount());	
					//总价
					totalMoney+=detail.getAuxiliaryMoney();
					totalCount += detail.getCount();
				
				
				}
			}
			//所有辅料的总价
			purchaseVo.setAuxiliaryAllMoney(totalMoney);

			//所有辅料的数量
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
		//到采购单详情页
		return "mobile/modules/Manager/auxiliary_apply_details";
	}
	
	
	
	
	
	
}