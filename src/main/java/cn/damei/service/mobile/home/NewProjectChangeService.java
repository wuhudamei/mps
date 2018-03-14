package cn.damei.service.mobile.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.dao.mobile.home.NewProjectChangeDao;
import cn.damei.entity.mobile.home.BizOrder;
import cn.damei.entity.mobile.home.BizProjectChangeBill;
import cn.damei.dao.mobile.home.HomeReportDao;
import cn.damei.entity.mobile.home.ViewLog;
import cn.damei.entity.modules.BizMsg;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
/**
 * 施工变更单
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly=true)
public class NewProjectChangeService {

	@Autowired
	private NewProjectChangeDao dao;
	@Autowired
	private HomeReportDao homeReportDao;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	
	
	/**
	 * 查询订单列表
	 * @param customerPhone
	 * @return
	 */
	public List<BizOrder> findOrderList(String customerPhone) {
		List<BizOrder> list = dao.findOrderList(customerPhone);
		if(null!=list && list.size()>0){
			return list;
		}else{
			return null;
		}
	}


	/**
	 * 查询施工变更单列表
	 * @param customerPhone
	 * @param orderId
	 * @return
	 */
	public BizOrder findProjectChangeBillList(String customerPhone, Integer orderId) {
		
		BizOrder bizOrder = new BizOrder();
		bizOrder.setCustomerPhone(customerPhone);
		bizOrder.setOrderId(orderId);
		BizOrder order = dao.findProjectChangeBillList(bizOrder);
		
		if(null!=order){
			return order;
		}else{
			return null;
		}
	}


	/**
	 * 查看消息是否已读
	 * @param qcBillId
	 * @return
	 */
	public Integer findView(Integer projectChangeId) {
		
		ViewLog viewLog = new ViewLog();
		viewLog.setBusinessIdInt(projectChangeId);
		viewLog.setBusinessType("5");
		
		return homeReportDao.findView(viewLog);
	}


	/**
	 * 如果未读则插入已读消息
	 * @param projectChangeId
	 */
	@Transactional(readOnly=false)
	public void insertView(Integer projectChangeId) {
		
		Date date = new Date();
		ViewLog viewLog = new ViewLog();
		viewLog.setBusinessIdInt(projectChangeId);
		viewLog.setBusinessType("5");
		viewLog.setBusinessViewDatetime(date);
		viewLog.setBusinessViewerOnlyMark("施工变更单");
		viewLog.setCreateDate(date);
		viewLog.setUpdateDate(date);
		viewLog.setDelFlag("0");
		
		homeReportDao.insertView(viewLog);
		
	}


	/**
	 * 变更单详情
	 * @param projectChangeId
	 * @return
	 */
	public BizProjectChangeBill projectChangeDetail(Integer projectChangeId) {
		
		BizProjectChangeBill projectChange = dao.projectChangeDetail(projectChangeId);
		if(null!=projectChange){
			return projectChange;
		}else{
			return null;
		}
	}


	/**
	 * 客户审核
	 * @param projectChangeId
	 * @param reason
	 * @param status 
	 */
	@Transactional(readOnly=false)
	public void updateChangeBill(Integer projectChangeId, String reason, String status) {
		BizProjectChangeBill bizProjectChangeBill = new BizProjectChangeBill();
		bizProjectChangeBill.setProjectChangeId(projectChangeId);
		bizProjectChangeBill.setStatus(status);
		bizProjectChangeBill.setCheckDate(new Date());
		bizProjectChangeBill.setCheckEmployeeId(Integer.valueOf("0"));
		bizProjectChangeBill.setCheckWords(reason);
		dao.updateChangeBill(bizProjectChangeBill);
		
	}


	/**
	 * 审核通过  短信发送
	 * @param projectChangeId
	 */
	@Transactional(readOnly = false)
	public void mesesagePass(Integer projectChangeId) {
		
		//通过变更单id查询客户信息
		BizProjectChangeBill change = dao.projectChangeDetail(projectChangeId);
		
		Date date = new Date();
		
		//=====================================短信start========================================================
		/**
		 * 1.客户审核==通过==项目经理
		 */
		//【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），施工变更单客户已审核通过，请登录APP查看详情。
		String content = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getCustomerPhone()+"），施工变更单客户已审核通过，请登录APP查看详情。";
		BizPhoneMsg phone = new BizPhoneMsg();
		phone.setReceiveEmployeeId(change.getItemManagerId());
		phone.setReceivePhone(change.getItemManagerPhone());
		phone.setMsgContent(content);
		phone.setMsgGenerateDatetime(date);
		phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
		phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400401);
		phone.setRelatedBusinessIdInt(projectChangeId);
		bizPhoneMsgService.insert(phone);

		/**
		 * 2.客户审核==通过==财务
		 */
		//【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），施工变更单客户已审核通过，请及时登录系统存档。
		String content2 = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getCustomerPhone()+"），施工变更单客户已审核通过，请及时登录系统存档。";
		BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(change.getStoreId(),"10");
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
					
					BizPhoneMsg phone2 = new BizPhoneMsg();
					phone2.setReceiveEmployeeId(bizEmployee2.getId());
					phone2.setReceivePhone(bizEmployee2.getPhone());
					phone2.setMsgContent(content2);
					phone2.setMsgGenerateDatetime(date);
					phone2.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
					phone2.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200202);
					phone2.setRelatedBusinessIdInt(projectChangeId);
					bizPhoneMsgService.insert(phone2);
				}
			}
		}
		/**
		 * 3.客户审核==通过==设计师
		 */
		//【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），施工变更单客户已审核通过，请登录系统查看详情。
		String content3 = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getCustomerPhone()+"），施工变更单客户已审核通过，请登录系统查看详情。";
		BizPhoneMsg phone3 = new BizPhoneMsg();
		phone3.setReceivePhone(change.getDesignerPhone());
		phone3.setMsgContent(content3);
		phone3.setMsgGenerateDatetime(date);
		phone3.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
		phone3.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400403);
		phone3.setRelatedBusinessIdInt(projectChangeId);
		bizPhoneMsgService.insert(phone3);
		
		/**
		 * 4.客户审核==通过==审计员
		 */
		//【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），施工变更单客户已审核通过，请登录系统查看详情。
		String content4 = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getCustomerPhone()+"），施工变更单客户已审核通过，请登录系统查看详情。";
		BizMessagegroup bizMessagegroup2 = bizMessagegroupService.getByStoreId(change.getStoreId(),"11");
		List<Integer> list2 = new ArrayList<Integer>();
		List<BizEmployee2> employeelist2 = null;
		if(null != bizMessagegroup ){
			String[] str = bizMessagegroup2.getEmployees().split(",");
			for(String id1: str){
				list2.add(Integer.valueOf(id1));
			}
			employeelist2 = bizEmployeeService2.getById(list2);
			if(null != employeelist2 && employeelist2.size()>0){
				for (BizEmployee2 bizEmployee2 : employeelist2) {
					
					BizPhoneMsg phone4 = new BizPhoneMsg();
					phone4.setReceiveEmployeeId(bizEmployee2.getId());
					phone4.setReceivePhone(bizEmployee2.getPhone());
					phone4.setMsgContent(content4);
					phone4.setMsgGenerateDatetime(date);
					phone4.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
					phone4.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400402);
					phone4.setRelatedBusinessIdInt(projectChangeId);
					bizPhoneMsgService.insert(phone4);
				}
			}
		}
		
		//=====================================短信end========================================================
		

		//=====================================消息推送start========================================================
		
		/**
		 * 消息推送   消息推送类型 101001005-通知项目经理-客户审核通过
		 */
		BizMsg bizMsg = new BizMsg();
		bizMsg.setMsgTitle("施工变更单客户审核通过");
		bizMsg.setMsgTime(date);
		bizMsg.setMsgContent(content);
		bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
		bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_101001005);
		bizMsg.setBusiIdInt(projectChangeId);
		bizMsg.setEmployeeId(change.getItemManagerId());
		bizProjectChangeBillService.saveBizMsg(bizMsg);
		//=====================================消息推送end========================================================
		
		
	}


	/**
	 * 审核驳回   短信发送
	 * @param projectChangeId
	 * @param reason 
	 */
	@Transactional(readOnly = false)
	public void mesesageRefuse(Integer projectChangeId, String reason) {
		
		Date date = new Date();
		//通过变更单id查询客户信息
		BizProjectChangeBill change = dao.projectChangeDetail(projectChangeId);
		
		//=====================================短信start========================================================
		/**
		 * 客户审核==驳回==项目经理
		 */
		//【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），施工变更单客户审核不通过，驳回原因（原因），请登录APP重新提交。
		String content = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getCustomerPhone()+"），施工变更单客户审核不通过，驳回原因（"+reason+"），请登录APP重新提交。";
		BizPhoneMsg phone = new BizPhoneMsg();
		phone.setReceiveEmployeeId(change.getItemManagerId());
		phone.setReceivePhone(change.getItemManagerPhone());
		phone.setMsgContent(content);
		phone.setMsgGenerateDatetime(new Date());
		phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
		phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400405);
		phone.setRelatedBusinessIdInt(projectChangeId);
		bizPhoneMsgService.insert(phone);
		//=====================================短信end========================================================
		
		//=====================================消息推送start========================================================
		
		/**
		 * 消息推送   消息推送类型 101001006-通知项目经理-客户审核不通过
		 */
		BizMsg bizMsg = new BizMsg();
		bizMsg.setMsgTitle("施工变更单客户审核不通过");
		bizMsg.setMsgTime(date);
		bizMsg.setMsgContent(content);
		bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
		bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_101001006);
		bizMsg.setBusiIdInt(projectChangeId);
		bizMsg.setEmployeeId(change.getItemManagerId());
		bizProjectChangeBillService.saveBizMsg(bizMsg);
		//=====================================消息推送end========================================================
				
		
	}

	
}
