package cn.damei.service.modules;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.toDoConstant.ApplyCheckToDoConstatntUtil;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.mobile.Manager.OrderConfirmStartworkDao;
import cn.damei.entity.mobile.Manager.ConfirmStartOrder;
import cn.damei.service.mobile.Manager.ConfirmStartService;
import cn.damei.service.mobile.Manager.OrderConfirmStartworkService;
import cn.damei.dao.mobile.Manager.OrderInstallItemDao;
import cn.damei.entity.mobile.Manager.OrderInstallItem;
import cn.damei.service.mobile.Manager.OrderInstallPlanService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.service.mobile.Manager.NodePlanService;
import cn.damei.dao.mobile.Manager.AppOrderDao;
import cn.damei.service.mobile.home.BroadCastService;
import cn.damei.dao.modules.ToDoItemDao;
import cn.damei.service.modules.BizBusinessStatusLogService;
import cn.damei.dao.modules.BizConstructionScheduleDao;
import cn.damei.entity.modules.BizConstructionSchedule;
import cn.damei.dao.modules.OrderStartWorkerDateSetDao;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

@Service
@Transactional(readOnly=false)
public class OrderStartWorkerDateSetService extends CrudService<OrderStartWorkerDateSetDao, ConfirmStartOrder>{
	
	@Autowired
	private OrderConfirmStartworkDao orderConfirmStartworkDao;
	@Autowired
	private BizConstructionScheduleDao bizConstructionScheduleDao;
	@Autowired
    private OrderConfirmStartworkService orderConfirmStartworkService;
	@Autowired
    private BroadCastService broadCastService;
	@Autowired
    private NodePlanService nodePlanService;
	@Autowired
	private AppOrderDao appOrderDao;
	@Autowired
	private OrderInstallItemDao orderInstallItemDao;
	@Autowired
    private OrderInstallPlanService orderInstallPlanService;
	@Autowired
    private ToDoItemDao toDoItemDao;
	@Autowired
    private ConfirmStartService confirmStartService;
	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	
	public ConfirmStartOrder findDetail(ConfirmStartOrder confirmStartOrder) {
		return dao.findDetail(confirmStartOrder);
	}
	

	@Transactional(readOnly=false)
	public String saveConfirmStart(String houseIsNew, String projectMode, String storeId, String orderId,
			String input_date, String startRemark, String dateCompare, String delayType, String decorateDelayDays,
			String isSelfDecorateNeedSign, String[] photos, String isNeedSign, HttpServletRequest request) {
		
		String result = "0";
		
		try {
			

			if(StringUtils.isBlank(orderId)){
				result = "1";
				return result;
			}
			Integer orderIdInt = Integer.valueOf(orderId);

			ConfirmStartOrder confirmStartOrder = dao.get(orderId);
			Manager manager = new Manager();
			if(StringUtils.isNotBlank(confirmStartOrder.getItemManagerId())){
				manager.setId(Integer.parseInt(confirmStartOrder.getItemManagerId()));
			}else{
				result = "1";
				return result;
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
				boolean picsFlag = confirmStartService.savePicConfirmStartAndBroadCast(photos,orderConfirmStartworkID,broadCastId,startRemark,request);
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
			

			boolean orderFlag = confirmStartService.updateByOrderStatusNumber(ConstantUtils.ORDERSTATUS_200_VALUE,
                    ConstantUtils.ORDERSTATUS_200_VALUE_REMARK, input_date, orderId);
			if(!orderFlag){
				result = "11";
				return result;
			}
			

			try {
				confirmStartService.saveBizSynDate(orderIdInt);
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

	            	confirmStartService.saveToDoItem(orderId);
	            }
	            
			}
			

			confirmStartService.saveMessage(orderIdInt);
			

			confirmStartService.updateOrderModified(1,orderIdInt);
	        

			Integer empId = null;
			User user = UserUtils.getUser1();
	        if (null != user && StringUtils.isNotBlank(user.getEmpId())) {
	        	empId = Integer.parseInt(user.getEmpId());
	        }
			bizBusinessStatusLogService.saveBusinessStatusLog(empId, orderIdInt, 
					BusinessLogConstantUtil.PC_CONFIRM_START, ConstantUtils. ORDERSTATUS_200_VALUE, "后台确认开工");
			
		} catch (NumberFormatException e) {
			result = "30";
			e.printStackTrace();
		}

		return result;
	}

}
