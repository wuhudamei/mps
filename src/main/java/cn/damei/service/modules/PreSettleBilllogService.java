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

			if(settleBillType.equals("1")){

				preSettleBilllog.setStatus("2");
				
				List<String> idsqpc = dao.findQualityId(preSettleBilllog);
				if(idsqpc !=null && idsqpc.size()>0){

					preSettleBilllog.setStatus("30");

					String joinqpc = StringUtils.join(idsqpc.toArray(),",");
					log.setBusinessType("3200");
					log.setBusinessOnlyMarkVarchar(joinqpc);

					List<BizBusinessStatusLog> qpcList = bizBusinessStatusLogDao.findListByVarchar(log);
					findAllList.addAll(0, qpcList);
				}
				

				List<String> ids = dao.findQualityId(preSettleBilllog);
				if(ids!=null && ids.size() >0){
					String join = StringUtils.join(ids.toArray(),",");
					log.setBusinessOnlyMarkVarchar(join);
					log.setBusinessType("3100");

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

			if(settleBillType.equals("2")){
				
				log.setBusinessType("303");
				log.setBusinessOnlyMarkInt(Integer.parseInt(preSettleBilllog.getOrderId()));
				List<BizBusinessStatusLog> completionAudit = bizBusinessStatusLogDao.findMyList(log);
				if(completionAudit!=null && completionAudit.size()>0){
					findAllList.addAll(0, completionAudit);
				}
				

				preSettleBilllog.setStatus("300");

				List<String> idsqpc = dao.findOrderFinishBill(preSettleBilllog);
				if(idsqpc!=null && idsqpc.size()>0){
					String joinqpc = StringUtils.join(idsqpc.toArray(),",");
					log.setBusinessType("4100");
					log.setBusinessOnlyMarkVarchar(joinqpc);

					List<BizBusinessStatusLog> qpcList = bizBusinessStatusLogDao.findListByVarchar(log);
					findAllList.addAll(0, qpcList);
				}
			}
			return findAllList;
		}
		return null;
	}

}
