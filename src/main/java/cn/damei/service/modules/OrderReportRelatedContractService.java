
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

			logRelatedMap.put("logId",logEntity.getId());
			logRelatedMap.put("orderNums",sb.toString());

			orderReportLogDao.saveLogRelatedOrderNumber(logRelatedMap);
























			}

	}




	@Transactional(readOnly = false)
	public void deleteRelatedInfoByOrderId2(OrderReportRelatedContract orderReportRelatedContract) {

		dao.deleteRelatedInfoByOrderId2(orderReportRelatedContract);

	}


}