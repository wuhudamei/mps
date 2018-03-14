package cn.damei.service.mobile.Manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.OrderInstallPlanAdvanceApplyConstantUtil;
import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.Base64Util;
import cn.damei.dao.mobile.Inspector.CheckConfirmDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.dao.mobile.Manager.ChecksizeNewDao;
import cn.damei.entity.mobile.Manager.Checksize;
import cn.damei.entity.mobile.Manager.ChecksizeOrder;
import cn.damei.entity.mobile.Manager.ChecksizePic;
import cn.damei.entity.mobile.Manager.ChecksizeType;
import cn.damei.entity.mobile.Manager.OrderInstallPlan;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.service.modules.BizBusinessStatusLogService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.BizOrderInstallPlanAdvanceApply;
import cn.damei.service.modules.BizOrderInstallPlanAdvanceApplyService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;

import net.sf.json.JSONObject;


@Service
@Transactional(readOnly = true)
public class ChecksizeNewService extends CrudService2<ChecksizeNewDao,Checksize>{

	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizOrderInstallPlanAdvanceApplyService bizOrderInstallPlanAdvanceApplyService;
	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	@Autowired
	private CheckConfirmDao checkConfirmDao;

	public List<ChecksizeOrder> findOrderByManagerId(Integer managerId,String text) {
		ChecksizeOrder checksizeOrder = new ChecksizeOrder();

		checksizeOrder.setItemManagerId(managerId);

		checksizeOrder.setText(text);
		
		return dao.findOrderByManagerId(checksizeOrder);
	}
	
	

	public Integer findCanApplyChecksizeCount(Integer orderId) {
		return dao.findCanApplyChecksizeCount(orderId);
	}



	public Integer findFiveTimeChecksizeCount(Integer orderId) {
		return dao.findFiveTimeChecksizeCount(orderId);
	}
	
	

	public ChecksizeOrder findOrder(Integer orderId) {
		return dao.findOrder(orderId);
	}
	
	


	public List<ChecksizeType> findChecksizeTypeList(Integer orderId) {
		return dao.findChecksizeTypeList(orderId);
	}
	


	public Integer findOrderInstallItemChecksizeCount(Integer orderId, String orderInstallItemId) {
		
		Checksize checksize = new Checksize();
		checksize.setOrderId(orderId);
		checksize.setOrderInstallItemId(orderInstallItemId);
		return dao.findOrderInstallItemChecksizeCount(checksize);
	}
	
	

	public Checksize findChecksizeCanApplyDate(String orderInstallItemId) {
		return dao.findChecksizeCanApplyDate(orderInstallItemId);
	}
	
	

	@Transactional(readOnly=false)
	public String saveChecksize(String orderId, String checksizeDate, String orderInstallItemId, String remarks, String[] photo, HttpServletRequest request, Manager manager) {

		
		String result = "0";
		try {
			

			if(StringUtils.isBlank(orderId)){
				result = "1";
				return result;
			}
			

			if(StringUtils.isBlank(checksizeDate)){
				result = "2";
				return result;
			}
			

			if(StringUtils.isBlank(orderInstallItemId)){
				result = "3";
				return result;
			}
			

			if(null == manager || null == manager.getId()){
				result = "4";
				return result;
			}
			
			Integer orderIdInt = Integer.valueOf(orderId);

			Integer checksizeId = saveChecksizeMessage(orderIdInt, orderInstallItemId, checksizeDate,manager.getId(), remarks);
			

			saveChecksizePicMessage(checksizeId,photo,request);
			

			sendMessage(orderIdInt,orderInstallItemId,checksizeId,manager);
			
		} catch (NumberFormatException e) {
			result = "9";
			e.printStackTrace();
		}
		return result;
	}
	


	@Transactional(readOnly=false)
	public Integer saveChecksizeMessage(Integer orderId, String orderInstallItemId, String checksizeDate,Integer managerId, String remarks) {
		
		Checksize checksize = new Checksize();

		checksize.setOrderId(orderId);

		checksize.setOrderInstallItemId(orderInstallItemId);

		checksize.setChecksizeDate(DateUtils.parseDate(checksizeDate));

		checksize.setChecksizeEmployeeId(managerId);

		checksize.setRemarks(remarks);

		checksize.setChecksizeStatus(ConstantUtils.CHECKSIZE_STATUS_10);

		checksize.setChecksizeStatusDatetime(new Date());

		String checksizeType = dao.findChecksizeTypeDictValue(orderInstallItemId);

		checksize.setChecksizeType(checksizeType);
		checksize.preInsert();
		

		dao.saveChecksize(checksize);
		
		return checksize.getId();
	}
	
	
	

	@Transactional(readOnly=false)
	public void saveChecksizePicMessage(Integer checksizeId, String[] photo, HttpServletRequest request) {
		
		List<ChecksizePic> list = new ArrayList<ChecksizePic>();
		if (null!=photo && photo.length>0) {

			for (String p : photo) {
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_CHECKSIZE + DateUtils.getDate1());

				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = ConstantUtils.UPLOAD_CHECKSIZE + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				
				ChecksizePic checksizePic = new ChecksizePic();
				checksizePic.setPicUrl(picpath);
				checksizePic.setOrderChecksizeId(checksizeId);
				checksizePic.preInsert();
				list.add(checksizePic);
			}


			if(CollectionUtils.isNotEmpty(list)){
				dao.saveChecksizePicAll(list);
			}
			
		}
		
		
	}
	
	

	@Transactional(readOnly=false)
	public void sendMessage(Integer orderId, String orderInstallItemId, Integer checksizeId, Manager manager) {
		

		ChecksizeOrder order = dao.findOrder(orderId);

		String check = dao.findInstallItemName(orderInstallItemId);
		


		String content = "订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+"），项目经理（"+manager.getRealname()+"-"+manager.getPhone()+"），项目经理已申请复尺（"+check+"），请及时登录系统联系供应商。";
		BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId()+"","9");
		List<Integer> list = new ArrayList<Integer>();
		List<BizEmployee2> employeelist = null;
		if(null != bizMessagegroup ){
			String[] str = bizMessagegroup.getEmployees().split(",");
			for(String id1: str){
				list.add(Integer.valueOf(id1));
			}
			employeelist = bizEmployeeService2.getById(list);
			if(null != employeelist && employeelist.size()>0){
				for (BizEmployee2 bizEmployee2 : employeelist) {
					
					BizPhoneMsg phone = new BizPhoneMsg();
					phone.setReceiveEmployeeId(bizEmployee2.getId());
					phone.setReceivePhone(bizEmployee2.getPhone());
					phone.setMsgContent(content);
					phone.setMsgGenerateDatetime(new Date());
					phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
					phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_300102);
					phone.setRelatedBusinessIdInt(checksizeId);
					bizPhoneMsgService.insert(phone);
				}
			}
		}
		
		
	}
	
	

	public List<Checksize> findCheckSizeList(Integer orderId, String text) {
		Checksize checksize = new Checksize();
		checksize.setOrderId(orderId);
		checksize.setText(text);
		return dao.findCheckSizeList(checksize);
	}



	public String queryChecksizeCheckData(String orderId, String type, String orderInstallItemId) {
		
		String result = "0";
		try {

			if (StringUtils.isBlank(orderId)) {
				result = "1";
				return result;
			}
			Integer orderIdInt = Integer.valueOf(orderId);

			Integer checksizeCount = dao.findCanApplyChecksizeCount(orderIdInt);
			if (null == checksizeCount || checksizeCount < 1) {
				result = "2";
				return result;
			}


			Integer fiveTimeCount = dao.findFiveTimeChecksizeCount(orderIdInt);
			if (null == fiveTimeCount || fiveTimeCount > 0) {
				result = "3";
				return result;
			}


			if (StringUtils.isNotBlank(type) && type.equals("2")) {
				if (StringUtils.isBlank(orderInstallItemId)) {

					result = "4";
					return result;
				} else {

					Integer orderInstallItemChecksizeCount = findOrderInstallItemChecksizeCount(orderIdInt, orderInstallItemId);
					if (null == orderInstallItemChecksizeCount || orderInstallItemChecksizeCount > 0) {
						result = "5";
						return result;
					}

					Checksize checksize = dao.findChecksizeCanApplyDate(orderInstallItemId);
					if (null != checksize) {
						


						


						BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply = bizOrderInstallPlanAdvanceApplyService.findInstallPlanAdvanceApplyLastRecord(Integer.valueOf(orderInstallItemId),OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_2);

						if(null == bizOrderInstallPlanAdvanceApply){
							String str = "{code:7,orderActualStartDateString:"+checksize.getOrderActualStartDateString()+",daysPlanChecksizeString:"+checksize.getDaysPlanChecksizeString()+",canApplyChecksizeDateString:"+checksize.getCanApplyChecksizeDateString()+"}";
							JSONObject fromObject = JSONObject.fromObject(str);
							String string = fromObject.toString();
							return string;
						}else if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){

							String str = "{code:8,orderActualStartDateString:"+checksize.getOrderActualStartDateString()+",daysPlanChecksizeString:"+checksize.getDaysPlanChecksizeString()+",canApplyChecksizeDateString:"+checksize.getCanApplyChecksizeDateString()+"}";
							JSONObject fromObject = JSONObject.fromObject(str);
							String string = fromObject.toString();
							return string;
						}
					}

				}
			} 
		} catch (NumberFormatException e) {
			result = "20";
			e.printStackTrace();
		}
		return result;
	}



	public OrderInstallPlan queryInstallItemDetail(Integer orderInstallItemId) {
		return dao.queryInstallItemDetail(orderInstallItemId);
	}



	@Transactional(readOnly = false)
	public String saveInstallApplyChecksizeAdvanceApplyAjax(String orderInstallItemId, String[] photo, Manager manager,
			HttpServletRequest request) {
		
		String result = "0";

		try {

			if (StringUtils.isBlank(orderInstallItemId)) {
				result = "1";
				return result;
			}


			if (null == photo || photo.length < 1) {
				result = "2";
				return result;
			}



			Integer count = bizOrderInstallPlanAdvanceApplyService.findInstallPlanAdvanceApplyCount(
					Integer.valueOf(orderInstallItemId),
					OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_2);
			if (null != count && count > 0) {
				result = "3";
				return result;
			}


			if (null == manager || null == manager.getId()) {
				result = "4";
				return result;
			}
			
			Integer orderItemId = Integer.valueOf(orderInstallItemId);
			

			OrderInstallPlan installPlan = dao.queryInstallItemDetail(orderItemId);
			

			Integer advanceApplyId = bizOrderInstallPlanAdvanceApplyService.insertOrderInstallPlanAdvanceApply(installPlan.getOrderId(),installPlan.getInstallItemName(),installPlan.getAllowApplyChecksizeDate(), orderItemId, manager,
					OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_2,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1);
			

			saveProblemPic(advanceApplyId, PictureTypeContantUtil.PICTURE_TYPE_2075, photo,
					PicturePathContantUtil.UPLOAD_INSTALL_CHECKSIZE_ADVANCE_APPLY_PATH, request);
			

			bizBusinessStatusLogService.saveBusinessStatusLog(manager.getId(), advanceApplyId, BusinessLogConstantUtil.BUSINESS_TYPE_9022,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_1, null);
		} catch (NumberFormatException e) {
			result = "5";
			e.printStackTrace();
		}
		return result;
		
	}
	

	@Transactional(readOnly = false)
	public void saveProblemPic(Integer businessId, String pictureType, String[] photo,
			String uploadSiteDesignProblemManagerApply, HttpServletRequest request) {

		Date date = new Date();
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();

		if (null != photo && photo.length > 0) {

			for (String p : photo) {

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");


				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + uploadSiteDesignProblemManagerApply + DateUtils.getDate1());

				if (!filePath.exists() && !filePath.isDirectory()) {
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);

				String picpath = uploadSiteDesignProblemManagerApply + DateUtils.getDate1() + filePath.separator + uuid
						+ ".jpeg";


				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				reportCheckDetailsPic.setBusinessIdInt(businessId);
				reportCheckDetailsPic.setBusinessType(pictureType);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.preInsert();
				pList.add(reportCheckDetailsPic);
			}

			checkConfirmDao.savePicAll(pList);
		}

	}

	

}
