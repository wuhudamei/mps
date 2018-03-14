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

/**
 * 上报厂家复尺
 * @author wyb
 *
 */
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
	/**
	 * 根据项目经理id查询项目经理下的状态小于300的所有订单(加搜索条件)
	 * @param managerId
	 * @param text
	 * @return
	 */
	public List<ChecksizeOrder> findOrderByManagerId(Integer managerId,String text) {
		ChecksizeOrder checksizeOrder = new ChecksizeOrder();
		//项目经理
		checksizeOrder.setItemManagerId(managerId);
		//搜索文本框
		checksizeOrder.setText(text);
		
		return dao.findOrderByManagerId(checksizeOrder);
	}
	
	
	/**
	 * 查询订单可复尺的安装项数量
	 * @param orderId
	 * @return
	 */
	public Integer findCanApplyChecksizeCount(Integer orderId) {
		return dao.findCanApplyChecksizeCount(orderId);
	}


	/**
	 * 同一个订单两次厂家复尺申请操作时间必须间隔5分钟，请过5分钟后再申请
	 * @param orderId
	 * @return
	 */
	public Integer findFiveTimeChecksizeCount(Integer orderId) {
		return dao.findFiveTimeChecksizeCount(orderId);
	}
	
	
	/**
	 * 查询订单
	 * @param orderId
	 * @return
	 */
	public ChecksizeOrder findOrder(Integer orderId) {
		return dao.findOrder(orderId);
	}
	
	

	/**
	 * 查询订单可复尺的安装项列表
	 * @param orderId
	 * @return
	 */
	public List<ChecksizeType> findChecksizeTypeList(Integer orderId) {
		return dao.findChecksizeTypeList(orderId);
	}
	

	/**
	 * 校验该安装项是否已经申请
	 * @param orderId
	 * @param orderInstallItemId
	 * @return
	 */
	public Integer findOrderInstallItemChecksizeCount(Integer orderId, String orderInstallItemId) {
		
		Checksize checksize = new Checksize();
		checksize.setOrderId(orderId);
		checksize.setOrderInstallItemId(orderInstallItemId);
		return dao.findOrderInstallItemChecksizeCount(checksize);
	}
	
	
	/**
	 * 校验：该工地2017-7-1开工，按照工程部规定主材（橱柜、台面）开工10天后（2017-7-21）才可以申请橱柜复尺，如有提前完工或疑问请联系大区经理。
	 * @param orderInstallItemId
	 * @return
	 */
	public Checksize findChecksizeCanApplyDate(String orderInstallItemId) {
		return dao.findChecksizeCanApplyDate(orderInstallItemId);
	}
	
	
	/**
	 * 保存上报厂家复尺
	 * @param orderId
	 * @param checksizeDate
	 * @param orderInstallItemId
	 * @param remarks
	 * @param photo
	 * @param request
	 * @param manager
	 */
	@Transactional(readOnly=false)
	public String saveChecksize(String orderId, String checksizeDate, String orderInstallItemId, String remarks, String[] photo, HttpServletRequest request, Manager manager) {

		
		String result = "0";
		try {
			
			//1.订单id为空
			if(StringUtils.isBlank(orderId)){
				result = "1";
				return result;
			}
			
			//2.期望复尺日期为空
			if(StringUtils.isBlank(checksizeDate)){
				result = "2";
				return result;
			}
			
			//3.主材复尺项为空
			if(StringUtils.isBlank(orderInstallItemId)){
				result = "3";
				return result;
			}
			
			//4.获取当前登录的项目经理
			if(null == manager || null == manager.getId()){
				result = "4";
				return result;
			}
			
			Integer orderIdInt = Integer.valueOf(orderId);
			//5.保存厂家复尺单
			Integer checksizeId = saveChecksizeMessage(orderIdInt, orderInstallItemId, checksizeDate,manager.getId(), remarks);
			
			//6.保存厂家复尺图片
			saveChecksizePicMessage(checksizeId,photo,request);
			
			//7.发送短信
			sendMessage(orderIdInt,orderInstallItemId,checksizeId,manager);
			
		} catch (NumberFormatException e) {
			result = "9";
			e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * 保存上报厂家复尺信息
	 * @param orderId
	 * @param checksizeType
	 * @param checksizeDate
	 * @param checksizeEmployeeId
	 * @param remarks
	 * @return
	 */
	@Transactional(readOnly=false)
	public Integer saveChecksizeMessage(Integer orderId, String orderInstallItemId, String checksizeDate,Integer managerId, String remarks) {
		
		Checksize checksize = new Checksize();
		//1.订单id
		checksize.setOrderId(orderId);
		//2.订单安装项id
		checksize.setOrderInstallItemId(orderInstallItemId);
		//3.期望复尺日期
		checksize.setChecksizeDate(DateUtils.parseDate(checksizeDate));
		//4.复尺人员工id
		checksize.setChecksizeEmployeeId(managerId);
		//5.复尺备注信息
		checksize.setRemarks(remarks);
		//6.复尺状态
		checksize.setChecksizeStatus(ConstantUtils.CHECKSIZE_STATUS_10);
		//7.复尺状态时间
		checksize.setChecksizeStatusDatetime(new Date());
		//8.1查询该主材复尺项对应的复尺类型
		String checksizeType = dao.findChecksizeTypeDictValue(orderInstallItemId);
		//8.2复尺类型
		checksize.setChecksizeType(checksizeType);
		checksize.preInsert();
		
		//保存上报厂家复尺信息
		dao.saveChecksize(checksize);
		
		return checksize.getId();
	}
	
	
	
	/**
	 * 保存厂家复尺图片
	 * @param request 
	 * @param photo 
	 * @param checksizeId 
	 */
	@Transactional(readOnly=false)
	public void saveChecksizePicMessage(Integer checksizeId, String[] photo, HttpServletRequest request) {
		
		List<ChecksizePic> list = new ArrayList<ChecksizePic>();
		if (null!=photo && photo.length>0) {

			for (String p : photo) {
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_CHECKSIZE + DateUtils.getDate1());
				//判断该文件是否存在
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

			//批量插入上报厂家复尺图片
			if(CollectionUtils.isNotEmpty(list)){
				dao.saveChecksizePicAll(list);
			}
			
		}
		
		
	}
	
	
	/**
	 * 申请厂家复尺发送短信
	 * @param orderId
	 * @param orderInstallItemId
	 * @param checksizeId
	 * @param manager
	 */
	@Transactional(readOnly=false)
	public void sendMessage(Integer orderId, String orderInstallItemId, Integer checksizeId, Manager manager) {
		
		//查询订单信息
		ChecksizeOrder order = dao.findOrder(orderId);
		//查询复尺内容
		String check = dao.findInstallItemName(orderInstallItemId);
		
		/**
		 * 项目经理申请厂家复尺==材料员
		 */
		//材料员【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），项目经理（姓名-手机号），项目经理已申请复尺（复尺内容），请及时登录系统联系供应商。
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
	
	
	/**
	 * 根据订单ID查询所有的厂家复尺
	 * @param orderId
	 * @param text 
	 * @return
	 */
	public List<Checksize> findCheckSizeList(Integer orderId, String text) {
		Checksize checksize = new Checksize();
		checksize.setOrderId(orderId);
		checksize.setText(text);
		return dao.findCheckSizeList(checksize);
	}


	/**
	 * 校验
	 * 1：该订单是否有可复尺的安装项
	 * 2：同一个订单两次厂家复尺申请操作时间必须间隔5分钟，请过5分钟后再申请
	 * 3：该复尺项是否已申请
	 * 4：该工地2017-7-1开工，按照工程部规定主材（橱柜、台面）开工10天后（2017-7-21）才可以申请橱柜复尺，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。
	 * 5:每个安装项厂家复尺【提前申请】只能申请一次
	 * @param orderId
	 * @param type
	 * @param orderInstallItemId 
	 * @return
	 */
	public String queryChecksizeCheckData(String orderId, String type, String orderInstallItemId) {
		
		String result = "0";
		try {
			//1.如果订单id为空
			if (StringUtils.isBlank(orderId)) {
				result = "1";
				return result;
			}
			Integer orderIdInt = Integer.valueOf(orderId);
			//2.查询订单可复尺的安装项数量
			Integer checksizeCount = dao.findCanApplyChecksizeCount(orderIdInt);
			if (null == checksizeCount || checksizeCount < 1) {
				result = "2";
				return result;
			}

			//3.同一个订单两次厂家复尺申请操作时间必须间隔5分钟，请过5分钟后再申请
			Integer fiveTimeCount = dao.findFiveTimeChecksizeCount(orderIdInt);
			if (null == fiveTimeCount || fiveTimeCount > 0) {
				result = "3";
				return result;
			}

			//4.如果该页面是提交复尺页面（——校验该安装项是否已经申请——）
			if (StringUtils.isNotBlank(type) && type.equals("2")) {
				if (StringUtils.isBlank(orderInstallItemId)) {
					//主材安装项为空
					result = "4";
					return result;
				} else {
					//5.校验该安装项是否已经申请
					Integer orderInstallItemChecksizeCount = findOrderInstallItemChecksizeCount(orderIdInt, orderInstallItemId);
					if (null == orderInstallItemChecksizeCount || orderInstallItemChecksizeCount > 0) {
						result = "5";
						return result;
					}
					//6.校验：该工地2017-7-1开工，按照工程部规定主材（橱柜、台面）开工10天后（2017-7-21）才可以申请橱柜复尺，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。
					Checksize checksize = dao.findChecksizeCanApplyDate(orderInstallItemId);
					if (null != checksize) {
						
						//该工地2017-7-1开工，按照工程部规定主材（橱柜、台面）开工10天后（2017-7-21）才可以申请橱柜复尺，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。
//						result = "该工地" + checksize.getOrderActualStartDateString() + "开工，按照工程部规定主材（"+ checksize.getInstallItemName() + "）开工" + checksize.getDaysPlanChecksizeString()+ "天后（" + checksize.getCanApplyChecksizeDateString() + "）才可以申请"+ checksize.getInstallItemName() + "复尺，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。";
						
						//查询是否已经提交过该主材安装项厂家复尺的提前申请
						//每个安装项厂家复尺【提前申请】只能申请一次。
						BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply = bizOrderInstallPlanAdvanceApplyService.findInstallPlanAdvanceApplyLastRecord(Integer.valueOf(orderInstallItemId),OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_2);
						//7.如果没有提交过申请
						if(null == bizOrderInstallPlanAdvanceApply){
							String str = "{code:7,orderActualStartDateString:"+checksize.getOrderActualStartDateString()+",daysPlanChecksizeString:"+checksize.getDaysPlanChecksizeString()+",canApplyChecksizeDateString:"+checksize.getCanApplyChecksizeDateString()+"}";
							JSONObject fromObject = JSONObject.fromObject(str);
							String string = fromObject.toString();
							return string;
						}else if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){
							//8.如果已经提交过申请（但是没有审核通过）
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


	/**
	 * 主材安装项复尺内容及时间
	 * @param orderInstallItemId
	 * @return
	 */
	public OrderInstallPlan queryInstallItemDetail(Integer orderInstallItemId) {
		return dao.queryInstallItemDetail(orderInstallItemId);
	}


	/**
	 * ajax 主材复尺项提前申请记录保存【提前申请】
	 * @param orderInstallItemId
	 * @param photo
	 * @param manager
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveInstallApplyChecksizeAdvanceApplyAjax(String orderInstallItemId, String[] photo, Manager manager,
			HttpServletRequest request) {
		
		String result = "0";

		try {
			// 1.主材复尺项id为空
			if (StringUtils.isBlank(orderInstallItemId)) {
				result = "1";
				return result;
			}

			// 2.请上传至少一张图片
			if (null == photo || photo.length < 1) {
				result = "2";
				return result;
			}

			// 3.查询该安装项【提前申请】的次数
			// 每个安装项【提前申请】只能申请一次。
			Integer count = bizOrderInstallPlanAdvanceApplyService.findInstallPlanAdvanceApplyCount(
					Integer.valueOf(orderInstallItemId),
					OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_2);
			if (null != count && count > 0) {
				result = "3";
				return result;
			}

			// 4.项目经理登录
			if (null == manager || null == manager.getId()) {
				result = "4";
				return result;
			}
			
			Integer orderItemId = Integer.valueOf(orderInstallItemId);
			
			// 5.查询安装项详情
			OrderInstallPlan installPlan = dao.queryInstallItemDetail(orderItemId);
			
			// 6.保存该安装项【提前申请】
			Integer advanceApplyId = bizOrderInstallPlanAdvanceApplyService.insertOrderInstallPlanAdvanceApply(installPlan.getOrderId(),installPlan.getInstallItemName(),installPlan.getAllowApplyChecksizeDate(), orderItemId, manager,
					OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_2,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1);
			
			// 7.保存该安装项【提前申请】的图片
			saveProblemPic(advanceApplyId, PictureTypeContantUtil.PICTURE_TYPE_2075, photo,
					PicturePathContantUtil.UPLOAD_INSTALL_CHECKSIZE_ADVANCE_APPLY_PATH, request);
			
			// 8.保存该安装项【提前申请】对应的状态日志
			bizBusinessStatusLogService.saveBusinessStatusLog(manager.getId(), advanceApplyId, BusinessLogConstantUtil.BUSINESS_TYPE_9022,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_1, null);
		} catch (NumberFormatException e) {
			result = "5";
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * 保存图片
	 * 
	 * @param businessId
	 * @param pictureType
	 * @param photo
	 * @param uploadSiteDesignProblemManagerApply
	 * @param request
	 */
	@Transactional(readOnly = false)
	public void saveProblemPic(Integer businessId, String pictureType, String[] photo,
			String uploadSiteDesignProblemManagerApply, HttpServletRequest request) {

		Date date = new Date();
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();
		// 保存图片
		if (null != photo && photo.length > 0) {

			for (String p : photo) {

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");

				// String rootPath = RootName.SystemEnvironment(request);
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + uploadSiteDesignProblemManagerApply + DateUtils.getDate1());
				// 判断该文件是否存在
				if (!filePath.exists() && !filePath.isDirectory()) {
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);

				String picpath = uploadSiteDesignProblemManagerApply + DateUtils.getDate1() + filePath.separator + uuid
						+ ".jpeg";

				// 保存图片到数据库
				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				reportCheckDetailsPic.setBusinessIdInt(businessId);
				reportCheckDetailsPic.setBusinessType(pictureType);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.preInsert();
				pList.add(reportCheckDetailsPic);
			}
			// 批量插入图片
			checkConfirmDao.savePicAll(pList);
		}

	}

	

}
