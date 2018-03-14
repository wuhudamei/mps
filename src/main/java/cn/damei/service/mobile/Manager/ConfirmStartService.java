package cn.damei.service.mobile.Manager;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.modules.BizCustomerReturnVisitTraditionOrderData;
import cn.damei.service.modules.BizCustomerReturnVisitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.toDoConstant.ApplyCheckToDoConstatntUtil;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.Base64Util;
import cn.damei.common.MD5Utils;
import cn.damei.dao.mobile.Inspector.CheckConfirmDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.dao.mobile.Manager.ConfirmStartDao;
import cn.damei.dao.mobile.Manager.OrderConfirmStartworkDao;
import cn.damei.dao.mobile.Manager.OrderConfirmStartworkPicDao;
import cn.damei.entity.mobile.Manager.ConfirmStartOrder;
import cn.damei.entity.mobile.Manager.OrderConfirmStartworkPic;
import cn.damei.dao.mobile.Manager.OrderInstallItemDao;
import cn.damei.entity.mobile.Manager.OrderInstallItem;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.dao.mobile.Manager.AppOrderDao;
import cn.damei.service.mobile.home.BroadCastService;
import cn.damei.dao.modules.ToDoItemDao;
import cn.damei.entity.modules.ToDoItemEntity;
import cn.damei.dao.modules.BizSynDataDao;
import cn.damei.entity.modules.BizSynData;
import cn.damei.dao.modules.BizConstructionScheduleDao;
import cn.damei.entity.modules.BizConstructionSchedule;
import cn.damei.dao.modules.OrderDao2;
import cn.damei.entity.modules.Order2;
import cn.damei.dao.modules.BizPhoneMsgDao;
import cn.damei.entity.modules.BizPhoneMsg;

import net.sf.json.JSONObject;


@Service
@Transactional(readOnly = true)
public class ConfirmStartService extends CrudService<ConfirmStartDao, ConfirmStartOrder>{

	@Autowired
	private ConfirmStartDao confirmStartDao;
	@Autowired
	private OrderConfirmStartworkDao orderConfirmStartworkDao;
	@Autowired
	private BizConstructionScheduleDao bizConstructionScheduleDao;
	@Autowired
    private OrderConfirmStartworkService orderConfirmStartworkService;
	@Autowired
    private BroadCastService broadCastService;
	@Autowired
	private OrderConfirmStartworkPicDao orderConfirmStartworkPicDao;
	@Autowired
	private CheckConfirmDao checkConfirmDao;
	@Autowired
    private NodePlanService nodePlanService;
	@Autowired
	private OrderDao2 orderDao2;
	@Autowired
	private BizSynDataDao bizSynDataDao;
	@Autowired
	private AppOrderDao appOrderDao;
	@Autowired
	private OrderInstallItemDao orderInstallItemDao;
	@Autowired
    private OrderInstallPlanService orderInstallPlanService;
	@Autowired
    private ToDoItemDao toDoItemDao;
	@Autowired
	private BizPhoneMsgDao bizPhoneMsgDao;
	@Autowired
	private BizCustomerReturnVisitRecordService bizCustomerReturnVisitRecordService;
	
	public List<ConfirmStartOrder> queryList(Integer id) {
		return confirmStartDao.queryList(id);
	}

	public ConfirmStartOrder getByOrderId(Integer orderId) {
		return confirmStartDao.getByOrderId(orderId);
	}

	@Transactional(readOnly = false)
	public boolean updateByOrderStatusNumber(String orderStatusNumber, String orderStatusDescription,
			String actualStartDate,String orderId) {
		return (confirmStartDao.updateByOrderStatusNumber(orderStatusNumber,orderStatusDescription,actualStartDate,orderId))?true:false;
	}


	public List<ConfirmStartOrder> queryByManagerIdList(Integer managerId) {
		return confirmStartDao.queryByManagerIdList(managerId);
	}

	public ConfirmStartOrder getByManagerId(Integer managerId) {
		return confirmStartDao.getByManagerId(managerId);
	}
	@Transactional(readOnly = false)
	public void updateOrderModified(int i, Integer id) {
		confirmStartDao.updateOrderModified(i,id);
	}

	

	@Transactional(readOnly = false)
	public String saveConfirmStart(String houseIsNew, String projectMode, String storeId, String orderId,
			String input_date, String startRemark, String dateCompare, String delayType, String decorateDelayDays,
			String isSelfDecorateNeedSign, String[] photos, String isNeedSign, HttpServletRequest request,
			Manager manager){
		
		String result = "0";
		
		try {
			

			if(StringUtils.isBlank(orderId)){
				result = "1";
				return result;
			}
			Integer orderIdInt = Integer.valueOf(orderId);


			List<Map<String,Object>>ll=bizCustomerReturnVisitRecordService.findIsThereNode(storeId,0);
			if(ll.size()>0){

				Integer i=0;
				if(null!=orderId){
					i=bizCustomerReturnVisitRecordService.findExistCount(orderIdInt,0);
				}
				if(i==0){
					BizCustomerReturnVisitTraditionOrderData bto=new BizCustomerReturnVisitTraditionOrderData();
					bto.setOrderId(orderId);
					bto.setReturnVisitNode("0");
					bto.setReturnVisitStatus(1);
					Date date=new Date();
					bto.setCreateDate(date);
					bizCustomerReturnVisitRecordService.insertBizCustomerReturnVisitTraditionOrderData(bto);
					bto.preInsert();
				}
			}

			List<BizCustomerReturnVisitTraditionOrderData>list=bizCustomerReturnVisitRecordService.findReturnVisitNode(orderIdInt);
			if(list.size()>0){
				for(BizCustomerReturnVisitTraditionOrderData bto:list){
					bto.setReturnVisitStatus(0);
					bizCustomerReturnVisitRecordService.updateStatus(bto);
				}
			}

			Integer existOrderId = orderConfirmStartworkDao.findByOrderId(orderIdInt);
			if(null != existOrderId){
				result = "2";
				return result;
			}
			

			if(StringUtils.isBlank(projectMode)){
				result = "3";
				return result;
			}
			

			if(null==photos || photos.length < 1){
				result = "4";
				return result;
			}
			

			List<BizConstructionSchedule> listBcs = bizConstructionScheduleDao.getConsScheduleByIsOldHouseAndStoreId(storeId, houseIsNew, projectMode);
			if(null==listBcs || listBcs.size()<1){
				result = "5";
				return result;
			}
			

			List<OrderInstallItem> installItemList = orderInstallItemDao.getByOrderIdList(orderIdInt);
			if(null == installItemList || installItemList.size() < 1){
				result = "6";
				return result;
			}
			


			if(StringUtils.isBlank(isNeedSign)){
				isNeedSign = "1";
			}

			if(StringUtils.isBlank(isSelfDecorateNeedSign)){
				isSelfDecorateNeedSign = "1";
			}
			Integer orderConfirmStartworkID = orderConfirmStartworkService.insertConfirmStartwork
					(decorateDelayDays, isSelfDecorateNeedSign, orderId, isNeedSign, startRemark, manager);
			if(null==orderConfirmStartworkID || orderConfirmStartworkID < 1){
				result = "7";
				return result;
			}
			

			Integer broadCastId = broadCastService.saveBroadCast(orderIdInt,photos,manager);
			if(null == broadCastId || broadCastId < 1){
				result = "8";
				return result;
			}

			try {
				boolean picsFlag = savePicConfirmStartAndBroadCast(photos,orderConfirmStartworkID,broadCastId,startRemark,request);
				if(!picsFlag){
					result = "9";
					return result;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
				result = "9";
				return result;
			}
			

			boolean nodePlanFlag = nodePlanService.saveNodePlanList(listBcs,orderId, startRemark, input_date, manager);
			if(!nodePlanFlag){
				result = "10";
				return result;
			}
			

			boolean orderFlag = updateByOrderStatusNumber(ConstantUtils.ORDERSTATUS_200_VALUE,
                    ConstantUtils.ORDERSTATUS_200_VALUE_REMARK, input_date, orderId);
			if(!orderFlag){
				result = "11";
				return result;
			}
			

			try {
				saveBizSynDate(orderIdInt);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				result = "12";
				return result;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				result = "12";
				return result;
			}
			

			if("3".equals(dateCompare)){
				delayType = "";
			}else if(StringUtils.isBlank(delayType)){
				delayType = "0";
			}
			boolean delayTypeFlag = appOrderDao.updateDelayType(delayType, orderId);
			if(!delayTypeFlag){
				result = "13";
				return result;
			}
			

			boolean installPlanFlag = orderInstallPlanService.saveInstallPlanList(installItemList, input_date, manager); 
			if(!installPlanFlag){
				result = "14";
				return result;
			}
			

			try {
				orderInstallPlanService.sendNodePlan(orderId);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				result = "15";
				return result;
			}
			

			if("1".equals(projectMode)){

				Map<String, String> map = new HashMap<String, String>();
		        map.put("relatedBusinessType", ApplyCheckToDoConstatntUtil.APPLY_CHECK_TO_DO_BUSINESS_TYPE);
		        map.put("relatedBusinessId", null);
		        map.put("orderId", orderId);
	            String id = toDoItemDao.selectId(map);
	            if(StringUtils.isBlank(id)){

	            	saveToDoItem(orderId);
	            }
	            
			}
			

			saveMessage(orderIdInt);

		} catch (NumberFormatException e) {
			result = "30";
			e.printStackTrace();
		}
		return result;
	}
	

	@Transactional(readOnly = false)
	public void saveMessage(Integer orderId) {
		

        Order2 order = orderDao2.get(orderId);

        String id = orderDao2.findDesignerId(order);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        BizPhoneMsg p = new BizPhoneMsg();
        p.setId(null);
        p.setReceivePhone(order.getDesignerPhone().trim());
        if(id != null){
        	p.setReceiveEmployeeId(Integer.parseInt(id));
        }
        p.setMsgContent(order.getDesignerName()+",您好！您的客户订单"+ order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName()
                + "-" + order.getCustomerPhone() + "，项目经理" + order.getItemManager() + "，于"+format+"已经确认开工，请实时关注！");
        p.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
        p.setMsgTosendDatetime(new Date());
        p.setMsgSendedDatetime(new Date());
        p.setMsgStatus("0");
        p.setRelatedBusinessType(SendMsgBusinessType.CONFIRM_STARTWORK_991100);
        p.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
        p.setRelatedBusinessIdVarchar("");
        bizPhoneMsgDao.insert(p);
        
	}


	@Transactional(readOnly = false)
	public void saveToDoItem(String orderId) {
		

        Map<String, String> paraMap = new HashMap<>(12);
        paraMap.put("orderId", orderId);
        paraMap.put("relatedBusinessType", ApplyCheckToDoConstatntUtil.RELATED_BUSINESS_TYPE);
        Map<String, Object> map = toDoItemDao.getToDoInfoByMap(paraMap);
        if (null != map) {
        	Date date = new Date();


            String url = "/app/manager/qualityCheck?id=" + orderId;
            ToDoItemEntity entity = new ToDoItemEntity();

            entity.setToDoItemTypeId(Integer.valueOf(map.get("relatedTypeId") == null ? "0" :map.get("relatedTypeId").toString()));
            entity.setRelatedBusinessId(Integer.valueOf(map.get("relatedBusinessId") == null ? "0" :  map.get("relatedBusinessId").toString()));
            entity.setRelatedBusinessType(ApplyCheckToDoConstatntUtil.APPLY_CHECK_TO_DO_BUSINESS_TYPE);
            entity.setOrderId(Integer.valueOf(orderId));
            entity.setToDoItemEmployeeId(Integer.valueOf(map.get("toDoEmployeeId") == null ? "0" :  map.get("toDoEmployeeId").toString()));
            entity.setToDoItemRemindTitle(map.get("remindTitle") == null ? "0" :  map.get("remindTitle").toString());
            entity.setToDoItemDealUrl(url);
            entity.setToDoItemGeneratedDatetime(date);
            entity.setToDoItemRemindDatetime(DateUtils.addDate(date, Integer.valueOf(map.get("toDoItemRemindTime") == null ? "0" : map.get("toDoItemRemindTime").toString())));

            entity.setIsViewd("0");
            entity.setIsSolved("0");
            entity.setId(null);

            toDoItemDao.insert(entity);
        }
        
        
	}


	@Transactional(readOnly = false)
	public void saveBizSynDate(Integer orderId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		Order2 order = orderDao2.get(orderId);

        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put("time", DateUtils.formatDateTime(new Date()));
        jsonMap.put("type", "1");
        jsonMap.put("orderId", order.getOrderNumber());
        String key = MD5Utils.MD5Secret(jsonMap);
        jsonMap.put("key", key);
        BizSynData bizSynData = new BizSynData();
        bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
        bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_301);
        bizSynData.setBusinessOnlyMarkInt(orderId);
        bizSynData.setBusinessData(JSONObject.fromObject(jsonMap).toString());
        bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
        bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
        bizSynData.preInsert();
        bizSynDataDao.insert(bizSynData);
	}


	@Transactional(readOnly = false)
	public boolean savePicConfirmStartAndBroadCast(String[] photos, Integer orderConfirmStartworkID,
			Integer broadCastId, String startRemark, HttpServletRequest request) throws IOException {
		
		Date date = new Date();
		List<OrderConfirmStartworkPic> startList = new ArrayList<OrderConfirmStartworkPic>();
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();
		boolean picsFlag = true;
		if(null != photos && photos.length > 0){
			for(String pic : photos){
				
				String rootPath = request.getSession().getServletContext().getRealPath("/");
                String imgUrl = PicRootName.getConfigValue(ConstantUtils.UPLOAD_CONFIRMSTART);

                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                File filePath = new File(rootPath + imgUrl + DateUtils.getDate1());

                if (!filePath.exists()) {
                	
                    filePath.mkdirs();
                }
                String picUrl = imgUrl + DateUtils.getDate1() + "/" + uuid + ".jpeg";
                String fullPath = filePath + filePath.separator + uuid + ".jpeg";
                logger.info("完整路径：" + fullPath);
                Base64Util.generateImage(pic, fullPath.toString());


                OrderConfirmStartworkPic workPic = new OrderConfirmStartworkPic();
        		workPic.setOrderConfirmStartworkId(orderConfirmStartworkID);
        		workPic.setPicUrl(picUrl);
        		workPic.setRemarks(startRemark);
        		workPic.preInsert();
        		startList.add(workPic);
        		

 				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
 				reportCheckDetailsPic.setBusinessIdInt(broadCastId);
 				reportCheckDetailsPic.setBusinessType("501");
 				reportCheckDetailsPic.setPicUrl(picUrl);
 				reportCheckDetailsPic.setPicDatetime(date);
 				reportCheckDetailsPic.preInsert();
 				pList.add(reportCheckDetailsPic);
 				
			}
			
			
			picsFlag = orderConfirmStartworkPicDao.saveConfirmStartPicList(startList);
			checkConfirmDao.savePicAll(pList);
			 
		}
		
		return picsFlag;
	}

}
