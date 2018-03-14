/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.*;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.service.modules.BizBusinessStatusLogService;
import cn.damei.dao.modules.BizOrderReportDao;
import cn.damei.dao.modules.BizOrderReportLogDao;
import cn.damei.entity.modules.OrderReportLogEntity;
import cn.damei.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.OrderReportRelatedContract;
import cn.damei.dao.modules.OrderReportRelatedContractDao;

/**
 * 返单关联合同信息Service
 * @author mh
 * @version 2017-05-08
 */
@Service
@Transactional(readOnly = true)
public class OrderReportRelatedContractService extends CrudService<OrderReportRelatedContractDao, OrderReportRelatedContract> {

	public OrderReportRelatedContract get(String id) {
		return super.get(id);
	}

	public List<OrderReportRelatedContract> findList(OrderReportRelatedContract orderReportRelatedContract) {
		return super.findList(orderReportRelatedContract);
	}

	public Page<OrderReportRelatedContract> findPage(Page<OrderReportRelatedContract> page, OrderReportRelatedContract orderReportRelatedContract) {
		return super.findPage(page, orderReportRelatedContract);
	}

	@Transactional(readOnly = false)
	public void save(OrderReportRelatedContract orderReportRelatedContract) {
		super.save(orderReportRelatedContract);
	}

	@Transactional(readOnly = false)
	public void delete(OrderReportRelatedContract orderReportRelatedContract) {
		super.delete(orderReportRelatedContract);
	}


	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;

	public List<OrderReportRelatedContract> findOrderInfoByReportId(String reportId) {

		return dao.findOrderInfoByReportId(reportId);

	}

@Autowired
private BizOrderReportDao reportDao;

	@Autowired
	private BizOrderReportLogDao orderReportLogDao;
	@Autowired
	private PhoneMessageDao messageDao;
	/**
	 * 更新返单状态, 删除不是参数orderIds的 关联信息
	 *
	 * @param reportId
	 */
	@Transactional(readOnly = false)
	public void updateOrderReportStatusById(String reportId, String[] orderIds,String []orderNums) {


		Map<String, Object> orderIdMap = new HashMap<>();


		if (null == orderIds || orderIds.length == 0) {

			orderIdMap.put("reportId", reportId);
			orderIdMap.put("orderIds", null);
			dao.deleteRelatedInfoByOrderId(orderIdMap);
		} else {
			List<String> list = new ArrayList<>();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < orderIds.length; i++) {

				list.add(orderIds[i]);
			sb.append(i==orderIds.length-1?orderNums[i]:orderNums[i]+",");
			}

			orderIdMap.put("reportId", reportId);
			orderIdMap.put("orderIds", list);
			dao.deleteRelatedInfoByOrderId(orderIdMap);
			dao.updateOrderReportStatusById(reportId);




//			List<OrderReportRelatedContract> orderInfo=	dao.findOrderInfoByReportId(reportId);






//		BizOrderReport orderReport= reportDao.get(Integer.valueOf(reportId));

			//插入log
			// 返单上报日志
			OrderReportLogEntity logEntity = new OrderReportLogEntity();
			Map<String,Object> logRelatedMap = new HashMap<>();
			logEntity.setOperateSource(BizOrderReportConstantUtil.REPORT_SOURCE_TYPE_4);
			logEntity.setOrderReportId(Integer.valueOf(reportId));
			logEntity.setLogType(BizOrderReportConstantUtil.SIGN_TYPE_1);
			logEntity.setLogDateTime(new Date());

			logEntity.setOperateDateTime(new Date());
			logEntity.setOperateEmployeeName(UserUtils.getUser().getName());
			logEntity.preInsert();
			orderReportLogDao.saveSignLog(logEntity);
			//保存该次log关联订单
			logRelatedMap.put("logId",logEntity.getId());
			logRelatedMap.put("orderNums",sb.toString());

			orderReportLogDao.saveLogRelatedOrderNumber(logRelatedMap);




			/**
			 * 发送短信
			 */


			//2017-08-15 停掉短信
			//PhoneMessageEntity phone = new PhoneMessageEntity();
            //
            //
			//String content = "【大美装饰管理平台】亲爱的大美装饰管理平台员工，您推荐的客户"+orderReport.getCustomerName()+"与大美装饰管理平台签订装修合同了，您的奖励将在下月兑现。如有新的客户，记得再次向“小美返单”推荐哦";
            //
			//phone.setReceivePhone(orderReport.getReporterPhone());
			//phone.setMessageContent(content);
			//phone.setMessageGenerateTime(new Date());
			//phone.setStatus(ConstantUtils.SEND_MSG_STATUS_0);
			//phone.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_66666);
			//phone.setRelatedBusinessId(Integer.valueOf(reportId));
			//phone.setRelatedBusinessVarchar(logEntity.getId());
            //
            //
			//	messageDao.saveMessageContent(phone);

			}

	}




	@Transactional(readOnly = false)
	public void deleteRelatedInfoByOrderId2(OrderReportRelatedContract orderReportRelatedContract) {

		dao.deleteRelatedInfoByOrderId2(orderReportRelatedContract);

	}


}