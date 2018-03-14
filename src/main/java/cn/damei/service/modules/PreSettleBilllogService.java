package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.mobile.Manager.ProjectManagerSettlement;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.dao.modules.PreSettleBilllogDao;

@Service
@Transactional(readOnly=false)
public class PreSettleBilllogService extends CrudService2<PreSettleBilllogDao, ProjectManagerSettlement>{

	@Autowired
	BizBusinessStatusLogDao bizBusinessStatusLogDao;
	public List<BizBusinessStatusLog> findDetail(ProjectManagerSettlement preSettleBilllog) {
		BizBusinessStatusLog log = new BizBusinessStatusLog();
		//先查询 项目经理申请约检节点 质检员确认验收约检节点 结算员创建结算单 结算员下发结算单
		String findBusinessOnlyMark = dao.findBusinessOnlyMark(preSettleBilllog);
		if(findBusinessOnlyMark == null){
			return null;
		}
		log.setBusinessOnlyMarkInt(Integer.parseInt(findBusinessOnlyMark));
		String str = "3300,3400,3500,3600,3700,3800,3900";
		log.setBusinessType(str);
		List<BizBusinessStatusLog> findAllList = bizBusinessStatusLogDao.findMyList(log);
		String settleBillType = preSettleBilllog.getSettleBillType();
		if(findBusinessOnlyMark != null){
			//中期
			if(settleBillType.equals("1")){
				//查询项目经理申请约检节点
				preSettleBilllog.setStatus("2");//项目经理已申请约检
				
				List<String> idsqpc = dao.findQualityId(preSettleBilllog);
				if(idsqpc !=null && idsqpc.size()>0){
					//质检员确认验收约检节点
					preSettleBilllog.setStatus("30");//项目经理已申请约检
					//查找出确认验收约检节点的ID
					String joinqpc = StringUtils.join(idsqpc.toArray(),",");
					log.setBusinessType("3200");
					log.setBusinessOnlyMarkVarchar(joinqpc);
					//根据约检的ID和类型,查找日志
					List<BizBusinessStatusLog> qpcList = bizBusinessStatusLogDao.findListByVarchar(log);
					findAllList.addAll(0, qpcList);
				}
				
				//查找出申请约检的ID
				List<String> ids = dao.findQualityId(preSettleBilllog);
				if(ids!=null && ids.size() >0){
					String join = StringUtils.join(ids.toArray(),",");
					log.setBusinessOnlyMarkVarchar(join);
					log.setBusinessType("3100");
					//根据约检的ID和类型,查找日志
					List<BizBusinessStatusLog> managerList = bizBusinessStatusLogDao.findListByVarchar(log);
					for (BizBusinessStatusLog bizBusinessStatusLog : managerList) {
						String remarks = bizBusinessStatusLog.getRemarks();
						String[] split = remarks.split(":");
						bizBusinessStatusLog.setBusinessName(split[0]);
						bizBusinessStatusLog.setBusinessDate(split[1]);
					}
					findAllList.addAll(0, managerList);
					
				}
			}
			//竣工
			if(settleBillType.equals("2")){
				
				log.setBusinessType("303");//竣工审核
				log.setBusinessOnlyMarkInt(Integer.parseInt(preSettleBilllog.getOrderId()));
				List<BizBusinessStatusLog> completionAudit = bizBusinessStatusLogDao.findMyList(log);
				if(completionAudit!=null && completionAudit.size()>0){
					findAllList.addAll(0, completionAudit);
				}
				
				//质检员确认验收约检节点
				preSettleBilllog.setStatus("300");//项目经理已申请竣工
				//查找出确认验收约检节点的ID
				List<String> idsqpc = dao.findOrderFinishBill(preSettleBilllog);
				if(idsqpc!=null && idsqpc.size()>0){
					String joinqpc = StringUtils.join(idsqpc.toArray(),",");
					log.setBusinessType("4100");
					log.setBusinessOnlyMarkVarchar(joinqpc);
					//根据约检的ID和类型,查找日志 BIZ_ORDER_FINISH_PROJECT_BILL
					List<BizBusinessStatusLog> qpcList = bizBusinessStatusLogDao.findListByVarchar(log);
					findAllList.addAll(0, qpcList);
				}
			}
			return findAllList;
		}
		return null;
	}

}
