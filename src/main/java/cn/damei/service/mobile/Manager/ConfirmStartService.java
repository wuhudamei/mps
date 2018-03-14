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

/**
 * 确认开工功能
 * @author llp
 *
 */
@Service
@Transactional(readOnly = true)
public class ConfirmStartService extends CrudService<ConfirmStartDao, ConfirmStartOrder>{

	@Autowired
	private ConfirmStartDao confirmStartDao;//确认开工功能
	@Autowired
	private OrderConfirmStartworkDao orderConfirmStartworkDao;// 确认开工功能
	@Autowired
	private BizConstructionScheduleDao bizConstructionScheduleDao;
	@Autowired
    private OrderConfirmStartworkService orderConfirmStartworkService;
	@Autowired
    private BroadCastService broadCastService;
	@Autowired
	private OrderConfirmStartworkPicDao orderConfirmStartworkPicDao;//确认开工功能
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

	/**
	 * 进度管理
	 * @param integer2 
	 * @param string 
	 */
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

	
	/**
	 * 订单确认开工【保存】
	 * @param houseIsNew
	 * @param projectMode
	 * @param storeId
	 * @param orderId
	 * @param input_date
	 * @param startRemark
	 * @param dateCompare
	 * @param delayType
	 * @param decorateDelayDays
	 * @param isSelfDecorateNeedSign
	 * @param photos
	 * @param isNeedSign
	 * @param request
	 * @param manager
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveConfirmStart(String houseIsNew, String projectMode, String storeId, String orderId,
			String input_date, String startRemark, String dateCompare, String delayType, String decorateDelayDays,
			String isSelfDecorateNeedSign, String[] photos, String isNeedSign, HttpServletRequest request,
			Manager manager){
		
		String result = "0";
		
		try {
			
			//1.订单id为空
			if(StringUtils.isBlank(orderId)){
				result = "1";
				return result;
			}
			Integer orderIdInt = Integer.valueOf(orderId);

			//查询回访节点表是否设置过这个节点
			List<Map<String,Object>>ll=bizCustomerReturnVisitRecordService.findIsThereNode(storeId,0);
			if(ll.size()>0){
				//判断传统订单表中是否插入过该订单对应的节点数据
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
			//将其余的变为0状态（已作废）
			List<BizCustomerReturnVisitTraditionOrderData>list=bizCustomerReturnVisitRecordService.findReturnVisitNode(orderIdInt);
			if(list.size()>0){
				for(BizCustomerReturnVisitTraditionOrderData bto:list){
					bto.setReturnVisitStatus(0);
					bizCustomerReturnVisitRecordService.updateStatus(bto);
				}
			}
			//2.查询该订单是否已经开工
			Integer existOrderId = orderConfirmStartworkDao.findByOrderId(orderIdInt);
			if(null != existOrderId){
				result = "2";
				return result;
			}
			
			//3.工程模式为空
			if(StringUtils.isBlank(projectMode)){
				result = "3";
				return result;
			}
			
			//4.请至少上传一张图片
			if(null==photos || photos.length < 1){
				result = "4";
				return result;
			}
			
			//5.查询工程进度节点
			List<BizConstructionSchedule> listBcs = bizConstructionScheduleDao.getConsScheduleByIsOldHouseAndStoreId(storeId, houseIsNew, projectMode);
			if(null==listBcs || listBcs.size()<1){
				result = "5";
				return result;
			}
			
			//6.获取订单安装项列表
			List<OrderInstallItem> installItemList = orderInstallItemDao.getByOrderIdList(orderIdInt);
			if(null == installItemList || installItemList.size() < 1){
				result = "6";
				return result;
			}
			
			//7.保存确认开工信息到biz_order_confirm_startwork【返回主键id】
			//7.1开工客户签字【空】
			if(StringUtils.isBlank(isNeedSign)){
				isNeedSign = "1";
			}
			//7.2自装客户签字 【空】
			if(StringUtils.isBlank(isSelfDecorateNeedSign)){
				isSelfDecorateNeedSign = "1";
			}
			Integer orderConfirmStartworkID = orderConfirmStartworkService.insertConfirmStartwork
					(decorateDelayDays, isSelfDecorateNeedSign, orderId, isNeedSign, startRemark, manager);
			if(null==orderConfirmStartworkID || orderConfirmStartworkID < 1){
				result = "7";
				return result;
			}
			
			//8.保存播报图片及表报记录
			Integer broadCastId = broadCastService.saveBroadCast(orderIdInt,photos,manager);
			if(null == broadCastId || broadCastId < 1){
				result = "8";
				return result;
			}
			//9.保存图片【开工图片及播报图片】
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
			
			//10.保存订单进度节点
			boolean nodePlanFlag = nodePlanService.saveNodePlanList(listBcs,orderId, startRemark, input_date, manager);
			if(!nodePlanFlag){
				result = "10";
				return result;
			}
			
			//11.修改订单的状态
			boolean orderFlag = updateByOrderStatusNumber(ConstantUtils.ORDERSTATUS_200_VALUE,
                    ConstantUtils.ORDERSTATUS_200_VALUE_REMARK, input_date, orderId);
			if(!orderFlag){
				result = "11";
				return result;
			}
			
			//12.保存确认开工到biz_syn_date表中【同步数据表】
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
			
			//13.确认开工中提交数据需修改delay_type(延期类型0代表公司原因1代表客户原因)
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
			
			//14.批量生成主材安装项计划
			boolean installPlanFlag = orderInstallPlanService.saveInstallPlanList(installItemList, input_date, manager); 
			if(!installPlanFlag){
				result = "14";
				return result;
			}
			
			//15.推送消息给订单流转系统
			try {
				orderInstallPlanService.sendNodePlan(orderId);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				result = "15";
				return result;
			}
			
			//16.如果订单的工程模式为产业时，做待办业务【开工后为项目经理生成申请约检的一条待办信息】
			if("1".equals(projectMode)){
				//17.根据订单id 查询 (防重复插入校验)
				Map<String, String> map = new HashMap<String, String>();
		        map.put("relatedBusinessType", ApplyCheckToDoConstatntUtil.APPLY_CHECK_TO_DO_BUSINESS_TYPE);
		        map.put("relatedBusinessId", null);
		        map.put("orderId", orderId);
	            String id = toDoItemDao.selectId(map);
	            if(StringUtils.isBlank(id)){
	            	//18.开工后为项目经理生成申请约检的一条待办信息
	            	saveToDoItem(orderId);
	            }
	            
			}
			
			//19.给设计师发送短信
			saveMessage(orderIdInt);

		} catch (NumberFormatException e) {
			result = "30";
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 确认开工给设计师发送短信
	 * @param orderId
	 */
	@Transactional(readOnly = false)
	public void saveMessage(Integer orderId) {
		
		 //给设计师发短信
        Order2 order = orderDao2.get(orderId);
        //查询设计师的ID
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

	/**
	 * 开工后为项目经理生成申请约检的一条待办信息
	 * @param orderId
	 */
	@Transactional(readOnly = false)
	public void saveToDoItem(String orderId) {
		
		//1:如果没有待办信息, 根据订单id 查询出同门店,同模式下的第一条约检节点信息
        Map<String, String> paraMap = new HashMap<>(12);
        paraMap.put("orderId", orderId);
        paraMap.put("relatedBusinessType", ApplyCheckToDoConstatntUtil.RELATED_BUSINESS_TYPE);
        Map<String, Object> map = toDoItemDao.getToDoInfoByMap(paraMap);
        if (null != map) {
        	Date date = new Date();
            //2 插入待办表中

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

	/**
	 * 保存确认开工到biz_syn_date表中【同步数据表】
	 * @param orderId
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	@Transactional(readOnly = false)
	public void saveBizSynDate(Integer orderId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		Order2 order = orderDao2.get(orderId);
		//向biz_syn_data表中保存数据  --- 确认开工时间
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

	/**
	 * 保存图片【开工图片及播报图片】
	 * @param photos
	 * @param orderConfirmStartworkID
	 * @param broadCastId
	 * @param startRemark
	 * @param request
	 * @return
	 * @throws IOException
	 */
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
                //生成UUID
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                File filePath = new File(rootPath + imgUrl + DateUtils.getDate1());
                //判断该文件是否存在
                if (!filePath.exists()) {
                	
                    filePath.mkdirs();
                }
                String picUrl = imgUrl + DateUtils.getDate1() + "/" + uuid + ".jpeg";
                String fullPath = filePath + filePath.separator + uuid + ".jpeg";
                logger.info("完整路径：" + fullPath);
                Base64Util.generateImage(pic, fullPath.toString());//base64解析成图片

                //开工图片
                OrderConfirmStartworkPic workPic = new OrderConfirmStartworkPic();
        		workPic.setOrderConfirmStartworkId(orderConfirmStartworkID);
        		workPic.setPicUrl(picUrl);
        		workPic.setRemarks(startRemark);
        		workPic.preInsert();
        		startList.add(workPic);
        		
                //轮播图
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
