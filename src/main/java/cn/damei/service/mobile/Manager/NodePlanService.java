package cn.damei.service.mobile.Manager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.dao.mobile.Manager.NodePlanDao;
import cn.damei.entity.mobile.Manager.NodePlan;
import cn.damei.entity.modules.BizConstructionSchedule;
import cn.damei.common.utils.UserUtils;

@Service
@Transactional(readOnly = true)
public class NodePlanService extends CrudService2<NodePlanDao, NodePlan>{

	@Autowired
	private NodePlanDao nodePlanDao;
	
	@Transactional(readOnly = false)
	public boolean insertByNodePlan(BizConstructionSchedule cs,String orderId,
			String startRemark,String actualStartDate, Manager manager) throws ParseException {
		NodePlan np = new NodePlan();
		logger.info("actualStartDate==="+actualStartDate);

		
		np.setId(null);
		np.setOrderId(Integer.valueOf(orderId));
		np.setNodeName(cs.getConstructionScheduleName());
		np.setNodeIndex(Integer.valueOf(cs.getSort()));
		np.setPlanDoneDate(DateUtils.addDate(DateUtils.parseDate(actualStartDate), Integer.valueOf(cs.getNormalCompletionDays())));
		np.setExeDoneDate(DateUtils.addDate(DateUtils.parseDate(actualStartDate), Integer.valueOf(cs.getNormalCompletionDays())));
		np.setRealDoneDate(null);
		np.setIsDone("0");
		np.setDelayDays(0);
		np.setDelayType(null);
		np.setDelayReason(startRemark);
		np.setCreateByAuthor(manager.getId().toString());
		np.setCreateDate(new Date());
		np.setUpdateByAuthor(manager.getId().toString());
		np.setUpdateDate(cs.getUpdateDate());
		np.setDelFlag("0");
		np.setConstructionScheduleId(Integer.parseInt(cs.getId()));
		return (nodePlanDao.insertByNodePlan(np)) ? true : false;
	}

	@Transactional(readOnly = true)
	public List<NodePlan> queryByOrderIdList(String orderId) {
		return nodePlanDao.queryByOrderIdList(orderId);
	}

	@Transactional(readOnly = false)
	public List<NodePlan> getByOrderIdList(String id) {
		return nodePlanDao.getByOrderIdList(id);
	}

	public NodePlan getByIdLimit(String id) {
		return nodePlanDao.getByIdLimit(id);
	}

	@Transactional(readOnly = false)
	public String updateByDate(String realDoneDate,String nodePlanId,String delayType,String delayReason) {
		String result = "0";
		NodePlan node = nodePlanDao.getById1(nodePlanId);
		Integer i = (int)(DateUtils.getDistanceOfTwoDate(node.getExeDoneDate(), DateUtils.parseDate(realDoneDate)));
		
		NodePlan np = new NodePlan();
		np.setId(node.getId());
		np.setExeDoneDate(node.getExeDoneDate());
		np.setRealDoneDate(DateUtils.parseDate(realDoneDate));

		np.setDelayDays(i);
		np.setDelayType(delayType);
		np.setDelayReason(delayReason);
		np.setIsDone("1");
		np.setUpdateDate(new Date());
		result = (nodePlanDao.updateByDate(np)) ? "0" : "2";
		
		if(result.equals("0")){
			if(i != 0){
				List<NodePlan> nodePList = nodePlanDao.queryByOrderIdListNoLimit(Integer.valueOf(node.getOrderId()));
				for(NodePlan p:nodePList){
					NodePlan n = new NodePlan();
					n.setId(p.getId());
					n.setExeDoneDate(DateUtils.addDate(p.getExeDoneDate(), i));
					n.setRealDoneDate(null);
					n.setUpdateBy(UserUtils.getUser().getUpdateBy());
					n.setDelayDays(null);
					n.setUpdateDate(new Date());
					n.setIsDone("0");
					result = nodePlanDao.updateByDateNextNodeIndexs(n) ? "0" : "3";
				}
			}
		}
		
		return result;
	}
	public List<NodePlan> justForTraditionNodePlan(Integer orderId){
		
		
		return dao.justForTraditionNodePlan(orderId);
	}


	@Transactional(readOnly = false)
	public boolean saveNodePlanList(List<BizConstructionSchedule> listBcs, String orderId, String startRemark,
			String actualStartDate, Manager manager) {
		
		List<NodePlan> list = new ArrayList<NodePlan>(); 
		boolean nodePlanFlag = true;
		
		if(null != listBcs && listBcs.size() > 0){
			for(BizConstructionSchedule bizConstructionSchedule :listBcs){
				
				NodePlan np = new NodePlan();
				
				np.setId(null);
				np.setOrderId(Integer.valueOf(orderId));
				np.setNodeName(bizConstructionSchedule.getConstructionScheduleName());
				np.setNodeIndex(Integer.valueOf(bizConstructionSchedule.getSort()));
				np.setPlanDoneDate(DateUtils.addDate(DateUtils.parseDate(actualStartDate), Integer.valueOf(bizConstructionSchedule.getNormalCompletionDays())));
				np.setExeDoneDate(DateUtils.addDate(DateUtils.parseDate(actualStartDate), Integer.valueOf(bizConstructionSchedule.getNormalCompletionDays())));

				np.setPlanCheckTime(np.getPlanDoneDate());
				np.setRealDoneDate(null);
				np.setIsDone("0");
				np.setDelayDays(0);
				np.setDelayType(null);
				np.setDelayReason(startRemark);
				np.setCreateByAuthor(manager.getId().toString());
				np.setCreateDate(new Date());
				np.setUpdateByAuthor(manager.getId().toString());
				np.setUpdateDate(bizConstructionSchedule.getUpdateDate());
				np.setDelFlag("0");
				np.setConstructionScheduleId(Integer.parseInt(bizConstructionSchedule.getId()));

				list.add(np);
			}
			
			nodePlanFlag = nodePlanDao.saveNodePlanList(list);
		}
		
		
		
		return nodePlanFlag;
	}

}
