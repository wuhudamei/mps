package cn.damei.web.mobile.Manager;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.service.mobile.Manager.ApplySandService;
import cn.damei.entity.mobile.Manager.PurchaseVo;
import cn.damei.entity.mobile.Manager.SwitchPanelOrderVo;
import cn.damei.entity.mobile.Manager.SwitchPanelPic;
import cn.damei.entity.mobile.Manager.SwitchPanelVo;
import cn.damei.service.mobile.Manager.ApplySwitchPanelService;
import cn.damei.entity.mobile.Manager.PurchaseDetailsVo;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.web.mobile.home.JobSiteController;
import cn.damei.dao.mobile.home.HomeReportDao;
import cn.damei.entity.mobile.home.ViewLog;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.service.modules.BizPurchaseMainPanelService;



@Controller
@RequestMapping(value = "${adminPath}/app/manager/applySwitchPanel")
public class ApplySwitchPanelController
{

	@Autowired
	private BizPurchaseMainPanelService bizPurchaseMainPanelService;
	@Autowired
	private ApplySwitchPanelService service;
	@Autowired
	private ApplySandService applySandService;
	@Autowired
	private OrderService2 orderService2;

	@RequestMapping(value = "orderList")
	public String orderList(Model model, HttpServletRequest request)
	{



		Manager manager = SessionUtils.getManagerSession(request);

		if (manager != null && null != manager.getId())
		{
			List<SwitchPanelOrderVo> list = service.getOrderListForSwitchPanelByManagerId(manager.getId());

			model.addAttribute("orderList", list);
		} else
			model.addAttribute("error", "出现了某些不可预估的错误 ,请联系管理员");




		return "mobile/modules/Manager/switchPanelApply/switch_panel_apply";
	}


	@RequestMapping(value = "applySwitchPanel_data_check_ajax")
	public @ResponseBody String applySwitchPanelDataCheckAjax(String orderId,HttpServletRequest request){
		
		String result = "0";

		if(StringUtils.isBlank(orderId)){

			result = "1";
			return result;
		}

		Manager manager = SessionUtils.getManagerSession(request);
		if(null==manager){

			result = "2";
			return result;
		}
		
		Order2 order = orderService2.findOrderById(Integer.valueOf(orderId));
		if(null!=order && order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4)){


			Integer count = applySandService.findQcBillAcceptStatus(Integer.valueOf(orderId));
			if(null==count || count>0){
				result = "3";
				return result;
			}
		}
		
		

		PurchaseVo purchaseVo = service.selectPurchaseByOrderIdLimitOneOrderByTime(Integer.parseInt(orderId));

		if (null != purchaseVo){

			ViewLog log = new ViewLog();
			log.setBusinessIdInt(purchaseVo.getId());
			log.setBusinessType("301");
			log.setBusinessViewerOnlyMark(manager.getPhone());
			Integer integer = logDao.findView(log);
			if (null == integer || integer == 0){
				result = "4";
				return result;
			}


			if (purchaseVo.getApplyTime().getTime() + 300 * 1000 > new Date().getTime()){
				result = "5";
				return result;
			}

		}
				
		return result;
		
	}
	

	@RequestMapping(value = "switchPanelApply")
	public String switchPanelApply(String orderId, Model model, HttpServletRequest request)
	{







		List<SwitchPanelVo> switchPanelList = service.selectSwitchPanelByStoreId(Integer.parseInt(orderId));
		String managerInfo = SessionUtils.getManagerSession(request).getRealname() + "-" + SessionUtils.getManagerSession(request).getPhone();





		PurchaseDetailsVo vo = service.selectOrderContractAreaAndTotalCount(Integer.parseInt(orderId));

		model.addAttribute("purchaseVo", vo);

		model.addAttribute("list", switchPanelList);
		model.addAttribute("managerInfo", managerInfo);
		model.addAttribute("orderId", orderId);
		return "mobile/modules/Manager/switchPanelApply/switch_submit";
	}


	@RequestMapping(value = "submit")
	public @ResponseBody String submit(String overCount, String currentcount, String overApplyWords, String[] photo, String totalMoney, HttpServletRequest request, String hopeForTime, String orderId, String remarks, String[] ids, String[] count, String[] brand) throws ParseException, UnsupportedEncodingException
	{
		
		Order2 order = orderService2.findOrderById(Integer.valueOf(orderId));
		if(null!=order && order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4)){
			


			Integer countApply = applySandService.findQcBillAcceptStatus(Integer.valueOf(orderId));
			if(null==countApply || countApply>0){
				return "noApply";
			}
			
		}
				
				

		PurchaseVo purchaseVo = service.selectPurchaseByOrderIdLimitOneOrderByTime(Integer.parseInt(orderId));

		if (null != purchaseVo)
		{


			if (purchaseVo.getApplyTime().getTime() + 300 * 1000 > new Date().getTime())
			{

				return "NO";
			}
		}

		Integer storeId = service.selectstoreIdByManagerId(SessionUtils.getManagerSession(request).getId());


		PurchaseVo purcharse = service.savePurchase(request, overCount, currentcount, orderId, hopeForTime, remarks, totalMoney);

		if (null != photo && photo.length > 0)
		{

			if (null == overApplyWords || "".equals(overApplyWords))
			{

				purcharse.setOverReasonType("1");

			} else
			{

				purcharse.setOverReasonType("2");

				purcharse.setOverWords(overApplyWords);
			}
			service.updatePurchaseByid(purcharse);
			for (String p : photo)
			{

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");


				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_PURCHASE_APPLY_OVER_SWITCHPANEL + DateUtils.getDate1());

				if (!filePath.exists() && !filePath.isDirectory())
				{
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);

				String picpath = ConstantUtils.UPLOAD_PURCHASE_APPLY_OVER_SWITCHPANEL + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";

				SwitchPanelPic switchPanelPic = new SwitchPanelPic();
				switchPanelPic.setBussinessType("104");
				switchPanelPic.setPicUpdateTime(new Date());
				switchPanelPic.setPicUrl(picpath);
				switchPanelPic.setRelatedBussinessId(purcharse.getId());

				service.saveSwitchPanelPic(switchPanelPic);
			}
		}
		try
		{

			service.saveSwitchPanel(request, ids, count, orderId, storeId, brand, purcharse.getId());
		} catch (Exception e)
		{

			service.deletePurchaseById(purcharse.getId());
			return "fail";
		}

		return String.valueOf(purcharse.getId());
	}

	@RequestMapping(value = "record")
	public String record(String orderId, Model model)
	{



		List<PurchaseVo> purchaseList = new ArrayList<PurchaseVo>();
		if(StringUtils.isNotBlank(orderId)){
			purchaseList = service.selectPurchaseByOrderId(Integer.parseInt(orderId));
		}

		if (null != purchaseList && purchaseList.size() > 0)
		{
			String customerInfo = purchaseList.get(0).getCommunityName() + "-" + purchaseList.get(0).getBuildNumber() + "-" + purchaseList.get(0).getBuildUnit() + "-" + purchaseList.get(0).getBuildRoom() + "-" + purchaseList.get(0).getCustomerName();

			model.addAttribute("purchaseList", purchaseList);
			model.addAttribute("customerInfo", customerInfo);
		} else
		{
			model.addAttribute("error", "该订单暂无开关面板申请记录");
		}

		return "mobile/modules/Manager/switchPanelApply/switch_record";
	}

	@Autowired
	private HomeReportDao logDao;

	@RequestMapping(value = "details")
	public String details(String id, Model model, HttpServletRequest request)
	{



		ViewLog log = new ViewLog();

		log.setBusinessIdInt(Integer.parseInt(id));
		log.setBusinessType("301");
		log.setBusinessViewerOnlyMark(SessionUtils.getManagerSession(request).getPhone());
		Integer integer = logDao.findView(log);

		if (null == integer || integer == 0)
		{

			log.setBusinessType("301");
			Date date = new Date();
			log.setBusinessViewDatetime(date);
			log.setBusinessViewDatetime(date);
			log.setBusinessViewerOnlyMark(SessionUtils.getManagerSession(request).getPhone());
			log.setCreateDate(date);
			log.setUpdateDate(date);
			log.setDelFlag("0");
			log.setBusinessIdInt(Integer.parseInt(JobSiteController.isNum(id) ? id : "0"));
			logDao.insertView(log);

		}


		Double totalMoney = (double) 0;
		Integer totalCount = 0;
		String customerInfo = "";
		List<PurchaseDetailsVo> purchaseDetail = service.selectPurchaseDetailsByPurchaseCode(Integer.parseInt(id));


		PurchaseVo purchaseVo = new PurchaseVo();

		if (null != purchaseDetail && purchaseDetail.size() > 0)
		{


			for (PurchaseDetailsVo detail : purchaseDetail)
			{



				purchaseVo.setPurchaseCode(detail.getPurchaseCode());

				purchaseVo.setStatus(detail.getStatus());

				purchaseVo.setHopeForTime(detail.getHopeForTime());

				purchaseVo.setStatusDescribe(detail.getStatusDescribe());

				purchaseVo.setStatusId(detail.getStatusId());

				customerInfo = detail.getCommunityName() + "-" + detail.getBuildNumber() + "-" + detail.getBuildUnit() + "-" + detail.getBuildRoom() + "-" + detail.getCustomerName();
				purchaseVo.setOrderId(detail.getOrderId());


				if (detail.getPrice() != 0 && detail.getCount() != 0 || null != detail.getPrice() && null != detail.getCount())
				{
					detail.setAuxiliaryMoney(detail.getPrice() * detail.getCount());

					totalMoney += detail.getAuxiliaryMoney();
					totalCount += detail.getCount();

				}
			}
			DecimalFormat format = new DecimalFormat("0.00");

			String string = format.format(totalMoney);

			purchaseVo.setAuxiliaryAllMoney(Double.parseDouble(string));


			purchaseVo.setTotalCount(totalCount);
			model.addAttribute("purchaseDetail", purchaseDetail);
			model.addAttribute("purchaseVo", purchaseVo);

		} else
		{
			model.addAttribute("error", "没有采购单编号");

		}

		model.addAttribute("customerInfo", customerInfo);

		return "mobile/modules/Manager/switchPanelApply/switch_apply_details";
	}

}