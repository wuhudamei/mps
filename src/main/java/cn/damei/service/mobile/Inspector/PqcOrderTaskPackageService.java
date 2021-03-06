package cn.damei.service.mobile.Inspector;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.entity.modules.BizMsg;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.mobile.Inspector.PqcOrderTaskPackageDao;
import cn.damei.dao.mobile.Inspector.PqcOrderTaskpackageProcedureDao;
import cn.damei.dao.mobile.Inspector.PqcOrderTaskpackageSettlementDao;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskPackage;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageProcedure;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageSettlement;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageVo;


@Service
@Transactional(readOnly=true)
public class PqcOrderTaskPackageService  extends CrudService2<PqcOrderTaskPackageDao, PqcOrderTaskPackage>{
	
	@Autowired
	private PqcOrderTaskpackageSettlementDao settlementDao;
	
	@Autowired
	private PqcOrderTaskpackageProcedureDao procedureDao;

	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	

	public List<PqcOrderTaskPackage> queryTaskPackageByInspectorId(Integer inspectorId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inspectorId", inspectorId);
		map.put("packageStateId", ConstantUtils.ORDER_TASK_STATUS_90_VALUE);
		return dao.queryTaskPackageByInspectorId(map);
	}
	

	public PqcOrderTaskpackageVo queryTaskPackageRecheck(Integer id){
		return dao.queryTaskPackageRecheck(id);
	}
	

	@Transactional(readOnly=false)
	public void confirmTaskpackageRecheck(PqcOrderTaskPackage task) {

			Date date = new Date();

			task.setPackageStateId(ConstantUtils.ORDER_TASK_STATUS_95_VALUE);
			task.setPackageStatename(ConstantUtils.ORDER_TASK_STATUS_95_VALUE_REMARK);
			task.setUpdateDate(date);
			dao.updatePackageStateIdById(task);


			PqcOrderTaskpackageSettlement settlement = settlementDao.querySettlementByOrderTaskpackageId(task.getId());
			if(settlement != null){
				settlement.setStatus(ConstantUtils.SETTLEMENT_STATUS_20);
				settlement.setStatusDate(date);
				settlement.setUpdateDate(date);
				settlement.setRecheckDatetime(date);
				settlementDao.update(settlement);
			}


			if(CollectionUtils.isNotEmpty(task.getProcedureList())){
				for(PqcOrderTaskpackageProcedure procedure:task.getProcedureList()){
					PqcOrderTaskpackageProcedure p = procedureDao.get(procedure.getId());
					p.setLaborAuxiliaryMaterialsSettleAmount(procedure.getLaborAuxiliaryMaterialsSettleAmount());
					p.setLaborSettleAmount(procedure.getLaborSettleAmount());
					p.setAuxiliaryMaterialsSettleAmount(procedure.getAuxiliaryMaterialsSettleAmount());
					p.setRecheckNumber(procedure.getRecheckNumber());
					p.setSettlementNumber(procedure.getSettlementNumber());
					p.setRecheckRemarks(procedure.getRecheckRemarks());
					procedureDao.update(p);
				}
			}


			PqcOrderTaskPackage taskPackage = dao.querySmsMessageForManager(task.getId());
			String content = "订单（"+taskPackage.getCustomerMessage()+"-"+taskPackage.getCustomerName()+"-"+taskPackage.getCustomerPhone()+"）的任务包（"+taskPackage.getPackageName()+
					"），质检员（"+taskPackage.getInspectorName()+"-"+taskPackage.getInspectorPhone()+"）已提交复核，请在【任务包结算】中及时验收任务包。";
			BizPhoneMsg msg = new BizPhoneMsg();
			msg.setReceiveEmployeeId(taskPackage.getManagerId());
			msg.setReceivePhone(taskPackage.getManagerPhone());
			msg.setMsgContent(content);
			msg.setMsgGenerateDatetime(date);
			msg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			msg.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200802);
			msg.setRelatedBusinessIdInt(taskPackage.getSettlementId());
			bizPhoneMsgService.insert(msg);


			BizMsg bizMsg = new BizMsg();
			bizMsg.setMsgTitle("质检员已提交复核");
			bizMsg.setMsgTime(date);
			bizMsg.setMsgContent(content);
			bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
			bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100006001);
			bizMsg.setBusiIdInt(taskPackage.getSettlementId());
			bizMsg.setEmployeeId(taskPackage.getManagerId());
			bizProjectChangeBillService.saveBizMsg(bizMsg);


	}
	
	public PqcOrderTaskPackage queryOrderTaskPackageByParam(Map<String,Object> param){
		return dao.queryOrderTaskPackageByParam(param);
	}
}
