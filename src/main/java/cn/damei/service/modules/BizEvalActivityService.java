package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.modules.BizEvalActivity;
import cn.damei.entity.modules.BizEvalActivityIndex;
import cn.damei.entity.modules.BizEvalActivityStage;
import cn.damei.entity.modules.BizEvalActivityTaskpackTemp;
import cn.damei.entity.modules.BizEvalWorkGrade;
import cn.damei.entity.modules.Dict;
import cn.damei.dao.modules.BizEvalActivityDao;


@Service
@Transactional(readOnly = true)
public class BizEvalActivityService extends CrudService2<BizEvalActivityDao, BizEvalActivity> {

	public BizEvalActivity get(Integer id) {
		return super.get(id);
	}
	
	public List<BizEvalActivity> findList(BizEvalActivity bizEvalActivity) {
		return super.findList(bizEvalActivity);
	}
	
	public Page<BizEvalActivity> findPage(Page<BizEvalActivity> page, BizEvalActivity bizEvalActivity) {
		return super.findPage(page, bizEvalActivity);
	}
	
	@Transactional(readOnly = false)
	public void save(BizEvalActivity bizEvalActivity,String[] managerEvalStage,String[] evalStageCheckNode, String[] evalRoleType, String[] evalIndexId, String[] evalTotalScore) {

		super.save(bizEvalActivity);
		


		
		if(bizEvalActivity.getEvalTargetType().equals("1")){

			String[] taskpackTempId= bizEvalActivity.getTaskpackTempId().split(",");
			List<BizEvalActivityTaskpackTemp> packageList = new ArrayList<BizEvalActivityTaskpackTemp>();
			if(null!=taskpackTempId && taskpackTempId.length>0){
				for(int i=0;i<taskpackTempId.length;i++){
					BizEvalActivityTaskpackTemp item = new BizEvalActivityTaskpackTemp();
					item.setEvalActivityId(bizEvalActivity.getId());
					item.setTaskpackTempId(Integer.valueOf(taskpackTempId[i]));
					item.preInsert();
					packageList.add(item);
				}
				dao.saveEvalActivityTaskpackTemp(packageList);
			}
		}else if(bizEvalActivity.getEvalTargetType().equals("2")){
			List<BizEvalActivityStage> stageList = new ArrayList<BizEvalActivityStage>();
			for(int i = 0;i<managerEvalStage.length;i++){
				BizEvalActivityStage stage = new BizEvalActivityStage();
				stage.setEvalActivityId(bizEvalActivity.getId());
				stage.setRelatedStage(managerEvalStage[i]);
				stage.setRelatedQcCheckNodeId(Integer.parseInt(evalStageCheckNode[i]));
				stage.preInsert();
				stageList.add(stage);
			}
			dao.saveEvalActivityStage(stageList);
		}
		
		



		List<BizEvalActivityIndex> indexList = new ArrayList<BizEvalActivityIndex>();
		if(null!=evalRoleType && evalRoleType.length>0 && null!=evalIndexId && evalIndexId.length>0 && null!=evalTotalScore && evalTotalScore.length>0){
			for(int i=0;i<evalRoleType.length;i++){
				BizEvalActivityIndex item = new BizEvalActivityIndex();
				item.setEvalActivityId(bizEvalActivity.getId());
				item.setEvalRoleType(evalRoleType[i]);
				item.setEvalIndexId(Integer.valueOf(evalIndexId[i]));
				item.setEvalTotalScore(Double.valueOf(evalTotalScore[i]));
				item.preInsert();
				indexList.add(item);
			}
			dao.saveEvalActivityIndex(indexList);
		}
		
		
	}
	

	@Transactional(readOnly = false)
	public void delete(BizEvalActivity bizEvalActivity) {
		dao.deleteEvalActivityTaskpackTemp(bizEvalActivity.getId());
		dao.deleteEvalActivityIndex(bizEvalActivity.getId());
		dao.deleteEvalActivity(bizEvalActivity.getId());
	}


	@Transactional(readOnly = false)
	public void isEnabled(BizEvalActivity bizEvalActivity) {
		
		dao.isEnabled(bizEvalActivity);
	}
	

	public Integer isTaskpackage(BizEvalActivity bizEvalActivity,List<Integer> list){
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("isEnabled", ConstantUtils.IS_ENABLED_1);
		map.put("storeId", bizEvalActivity.getStoreId());
		map.put("projectMode", bizEvalActivity.getProjectMode());
		map.put("evalTargetType", bizEvalActivity.getEvalTargetType());
		map.put("evalStartDatetime", bizEvalActivity.getEvalStartDatetime());
		map.put("evalEndDatetime", bizEvalActivity.getEvalEndDatetime());
		map.put("list", list);
		
		Integer count = dao.isTaskpackage(map);
		
		return count;
	}
	
	public Integer isCheckStage(BizEvalActivity bizEvalActivity,List<Integer> list){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("isEnabled", ConstantUtils.IS_ENABLED_1);
		map.put("storeId", bizEvalActivity.getStoreId());
		map.put("projectMode", bizEvalActivity.getProjectMode());
		map.put("evalTargetType", bizEvalActivity.getEvalTargetType());
		map.put("evalStartDatetime", bizEvalActivity.getEvalStartDatetime());
		map.put("evalEndDatetime", bizEvalActivity.getEvalEndDatetime());
		map.put("list", list);
		
		Integer count = dao.isCheckStage(map);
		
		return count;
	}


	public List<BizEvalActivityTaskpackTemp> findEvalActivityTaskpackTemp(Integer id) {
		return dao.findEvalActivityTaskpackTemp(id);
	}


	public List<BizEvalActivityIndex> findEvalActivityIndex(Integer id) {
		return dao.findEvalActivityIndex(id);
	}


	public List<Dict> findDict(String type) {
		
		return dao.findDict(type);
	}


	public Integer queryIdByMap(Map<String, Object> map){
		return dao.queryIdByMap(map);
	}


	public List<Integer> isEnabledEval(Integer id) {
		return dao.isEnabledEval(id);
	}
	

	public Page<BizEvalWorkGrade> queryBizEvalWorkGradePage(Page<BizEvalWorkGrade> page, BizEvalWorkGrade bizEvalWorkGrade){
		bizEvalWorkGrade.setPage(page);
		page.setList(dao.queryBizEvalWorkGradeList(bizEvalWorkGrade));
		return page;
	}
	
	public Page<BizEvalWorkGrade> queryBizEvalManagerGradePage(Page<BizEvalWorkGrade> page, BizEvalWorkGrade bizEvalWorkGrade){
		bizEvalWorkGrade.setPage(page);
		page.setList(dao.queryBizEvalManagerGradeList(bizEvalWorkGrade));
		return page;
	}
	

	public  List<BizEvalActivityIndex> queryEvalIndexRoleScore(Map<String,Object> map){
		return dao.queryEvalIndexRoleScore(map);
	}
	
	public List<BizEvalActivityStage> queryEvalStage(Integer evalActivityId){
		return dao.queryEvalStage(evalActivityId);
	}

	public List<String> findEvalType(BizEvalActivity bizEvalActivity) {

		return dao.findEvalType(bizEvalActivity);
	}

	public String checkExist(BizEvalActivity bizEvalActivity) {

		return dao.checkExist(bizEvalActivity);
	}
	@Transactional(readOnly=false)
	public void saveActivityRoleCycle(BizEvalActivity bizEvalActivity) {

		dao.saveActivityRoleCycle(bizEvalActivity);
	}

	public Page<BizEvalActivity> findMyPage(Page<BizEvalActivity> page, BizEvalActivity bizEvalActivity) {

		List<BizEvalActivity> list = dao.findEvalActivityRoleCycle(bizEvalActivity);
		page.setList(list);
		return page;
	}
	@Transactional(readOnly=false)
	public void updateActivityRoleCycle(BizEvalActivity bizEvalActivity) {

		dao.updateActivityRoleCycle(bizEvalActivity);
	}

	public String findEvalActivity(BizEvalActivity bizEvalActivity) {

		return dao.findEvalActivity(bizEvalActivity);
	} 
}