package cn.damei.Quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.mobile.Inspector.EvaluateWorker;
import cn.damei.service.mobile.Inspector.InspectorEvaluateWorkerService;
import cn.damei.entity.mobile.Manager.BizEvalRewardTaskpack;
import cn.damei.service.mobile.Manager.BizEvalRewardTaskpackService;
import cn.damei.dao.mobile.Manager.BizEvalTaskpackRoleIndexScoreDao;
import cn.damei.entity.mobile.Manager.EvalScoreRoleIndex;
import cn.damei.service.mobile.Manager.BizEvalTaskpackRoleIndexScoreService;
import cn.damei.service.mobile.Manager.BizEvalTaskpackRoleScoreService;
import cn.damei.entity.mobile.Manager.EvalScore;
import cn.damei.service.mobile.Manager.BizEvalTaskpackScoreService;
import cn.damei.service.modules.BizEvalRewardStarService;
import cn.damei.dao.modules.BizBizEmployeegroupVoDao;
import cn.damei.entity.modules.BizEmployeegroupVO;
import cn.damei.entity.modules.BizEvalActivityIndex;


/**
 * Created by qww on 2016/12/1.
 */
public class EvalRewardQuartz {

    @Autowired
    private BizEvalTaskpackScoreService bizEvalTaskpackScoreService;

    @Autowired
    private BizEvalTaskpackRoleScoreService bizEvalTaskpackRoleScoreService;

    @Autowired
    private InspectorEvaluateWorkerService inspectorEvaluateWorkerService;

    @Autowired
    private BizEvalTaskpackRoleIndexScoreService bizEvalTaskpackRoleIndexScoreService;

    @Autowired
    private BizEvalRewardStarService bizEvalRewardStarService;

    @Autowired
    private BizEvalRewardTaskpackService bizEvalRewardTaskpackService;
    
    @Autowired
    private BizEvalTaskpackRoleIndexScoreDao bizEvalTaskpackRoleIndexScoreDao;
    
	@Autowired
	private BizBizEmployeegroupVoDao bizBizEmployeegroupVoDao;

    public void execute() {
        try{
            Date date = new Date();
            // 查询超过系统间隔时间的任务包评价
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("evalStatus", ConstantUtils.EVAL_ROLE_STATUS_0);
            List<EvalScore> bizEvalTaskpackScoreList = bizEvalTaskpackScoreService.queryEvalRoleOvertimeByMap(map);
            
            
            for(EvalScore bizEvalTaskpackScore:bizEvalTaskpackScoreList){
            	//项目经理
              if(bizEvalTaskpackScore.getEvalType().equals("101")){
            	  bizEvalTaskpackScore.setEvalStatus("1");
            	  bizEvalTaskpackScore.setEvalType("102");
            	  bizEvalTaskpackScoreService.updateEvalRole(bizEvalTaskpackScore);
            	  //插入biz_eval_score_role_index
            	  EvaluateWorker evaluateWorker = new EvaluateWorker();
          		  evaluateWorker.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_1);
          		  evaluateWorker.setOrderTaskpackageId(bizEvalTaskpackScore.getRelatedBusinessId());
          		  List<BizEvalActivityIndex> bizEvalIndexList = inspectorEvaluateWorkerService.findEvalActivityIndex(evaluateWorker);
          		  
            	  List<EvalScoreRoleIndex> bizEvalTaskpackRoleIndexScoreList = new ArrayList<EvalScoreRoleIndex>();
					for (BizEvalActivityIndex evalActivityIndex : bizEvalIndexList) {
						EvalScoreRoleIndex bizEvalTaskpackRoleIndexScore = new EvalScoreRoleIndex();
						bizEvalTaskpackRoleIndexScore.setEvalScoreRoleId(bizEvalTaskpackScore.getId());
						bizEvalTaskpackRoleIndexScore.setEvalActivityIndexId(evalActivityIndex.getId());
						bizEvalTaskpackRoleIndexScore.setGotScore(0d);
						bizEvalTaskpackRoleIndexScore.setCreateDate(date);
						bizEvalTaskpackRoleIndexScore.setUpdateDate(date);
						bizEvalTaskpackRoleIndexScoreList.add(bizEvalTaskpackRoleIndexScore);
					}
					bizEvalTaskpackRoleIndexScoreDao.insertBatch(bizEvalTaskpackRoleIndexScoreList);
            	  
              }
              //质检
              if(bizEvalTaskpackScore.getEvalType().equals("201")){
            	  bizEvalTaskpackScore.setEvalStatus("1");
            	  bizEvalTaskpackScore.setEvalType("202");
            	  bizEvalTaskpackScoreService.updateEvalRole(bizEvalTaskpackScore);
            	  //插入biz_eval_score_role_index
            	  EvaluateWorker evaluateWorker = new EvaluateWorker();
          		  evaluateWorker.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_2);
          		  evaluateWorker.setOrderTaskpackageId(bizEvalTaskpackScore.getRelatedBusinessId());
          		  List<BizEvalActivityIndex> bizEvalIndexList = inspectorEvaluateWorkerService.findEvalActivityIndex(evaluateWorker);
          		  
            	  List<EvalScoreRoleIndex> bizEvalTaskpackRoleIndexScoreList = new ArrayList<EvalScoreRoleIndex>();
					for (BizEvalActivityIndex evalActivityIndex : bizEvalIndexList) {
						EvalScoreRoleIndex bizEvalTaskpackRoleIndexScore = new EvalScoreRoleIndex();
						bizEvalTaskpackRoleIndexScore.setEvalScoreRoleId(bizEvalTaskpackScore.getId());
						bizEvalTaskpackRoleIndexScore.setEvalActivityIndexId(evalActivityIndex.getId());
						bizEvalTaskpackRoleIndexScore.setGotScore(0d);
						bizEvalTaskpackRoleIndexScore.setCreateDate(date);
						bizEvalTaskpackRoleIndexScore.setUpdateDate(date);
						bizEvalTaskpackRoleIndexScoreList.add(bizEvalTaskpackRoleIndexScore);
					}
					bizEvalTaskpackRoleIndexScoreDao.insertBatch(bizEvalTaskpackRoleIndexScoreList);
            	  
              }
              //项目经理
              if(bizEvalTaskpackScore.getEvalType().equals("301")){
            	  bizEvalTaskpackScore.setEvalStatus("1");
            	  bizEvalTaskpackScore.setEvalType("302");
            	  bizEvalTaskpackScoreService.updateEvalRole(bizEvalTaskpackScore);
            	  //插入biz_eval_score_role_index
            	  EvaluateWorker evaluateWorker = new EvaluateWorker();
          		  evaluateWorker.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_3);
          		  evaluateWorker.setOrderTaskpackageId(bizEvalTaskpackScore.getRelatedBusinessId());
          		  List<BizEvalActivityIndex> bizEvalIndexList = inspectorEvaluateWorkerService.findEvalActivityIndex(evaluateWorker);
          		  
            	  List<EvalScoreRoleIndex> bizEvalTaskpackRoleIndexScoreList = new ArrayList<EvalScoreRoleIndex>();
					for (BizEvalActivityIndex evalActivityIndex : bizEvalIndexList) {
						EvalScoreRoleIndex bizEvalTaskpackRoleIndexScore = new EvalScoreRoleIndex();
						bizEvalTaskpackRoleIndexScore.setEvalScoreRoleId(bizEvalTaskpackScore.getId());
						bizEvalTaskpackRoleIndexScore.setEvalActivityIndexId(evalActivityIndex.getId());
						bizEvalTaskpackRoleIndexScore.setGotScore(0d);
						bizEvalTaskpackRoleIndexScore.setCreateDate(date);
						bizEvalTaskpackRoleIndexScore.setUpdateDate(date);
						bizEvalTaskpackRoleIndexScoreList.add(bizEvalTaskpackRoleIndexScore);
					}
					bizEvalTaskpackRoleIndexScoreDao.insertBatch(bizEvalTaskpackRoleIndexScoreList);
              }
               
            }
            
            
            // 查询评价总分表状态为0 和 1 的 未评价和评价中的
            List<EvalScore> list = bizEvalTaskpackScoreService.findEvalScoreByEvalStatus();
            for (EvalScore evalScore : list) {
            	Integer id = evalScore.getId();
            	//根据评价总分表ID 查询角色评分表
            	List<EvalScore> EvalRoleList = bizEvalTaskpackScoreService.findEvalRoleByEvalScoreId(id);
            	int i = 0;
            	int j = 0;
            	for (EvalScore evalScore2 : EvalRoleList) {
            		String evalStatus = evalScore2.getEvalStatus();
            		if(evalStatus == null){
            			if(evalScore2.getEvalType().equals("101")){
            				evalStatus = "1";
            			}else{
            				evalStatus = "0";
            			}
            		}
//            		0 未评价
					if(evalStatus.equals("0")){
						i++;
					}
//					1 评价完成
					if(evalStatus.equals("1")){
						j++;
					}
            		
				}
//            	没有未评价的，更新评分总表，状态改为评价完成
            	if(i == 0){
            		  Map<String,Object> getScoreMap = new HashMap<String,Object>();
                      getScoreMap.put("relatedBusinessId", evalScore.getRelatedBusinessId());
                      getScoreMap.put("evalType", evalScore.getEvalType());
                      Double gotScore = bizEvalTaskpackRoleIndexScoreService.querySumGotScoreByOrderTaskpackageId(getScoreMap);
                      evalScore.setGotScore(gotScore);
                      evalScore.setEvalStatus(ConstantUtils.EVAL_STATUS_2);
                      evalScore.setStatusDatetime(date);
                      evalScore.setUpdateDate(date);
                     
                     
                     //插入星级变化记录
                    
                      List<BizEmployeegroupVO>listqqq =bizBizEmployeegroupVoDao.getSumAvg(evalScore.getGroupLeaderEmployeeId());
              			
              			if(listqqq.size()==0){
              				bizBizEmployeegroupVoDao.insertStarLog(0,gotScore,evalScore.getGroupLeaderEmployeeId());	
              			}else{
              				for(BizEmployeegroupVO bb:listqqq){
                  				if(bb.getCiShu()!=0 && bb.getCiShu()!=null && bb.getGotScore()!=null){
    	              				double beforeScore=bb.getGotScore()/bb.getCiShu();
    	              				double afterScore=(gotScore+bb.getGotScore())/(bb.getCiShu()+1);
    	              				List<BizEmployeegroupVO>listSelect= bizBizEmployeegroupVoDao.selectStarLog(evalScore.getRelatedBusinessId());
    	              				if(listSelect.size()==0){
    	              					bizEvalTaskpackScoreService.update(evalScore);
    	              					bizBizEmployeegroupVoDao.insertStarLog(beforeScore,afterScore,evalScore.getGroupLeaderEmployeeId());	
    	              					}
                  					}
                  			}
              			}
              			bizEvalTaskpackScoreService.update(evalScore);
              			bizBizEmployeegroupVoDao.updateStar(evalScore.getGroupLeaderEmployeeId());
              			bizBizEmployeegroupVoDao.updateStarGroup(evalScore.getGroupLeaderEmployeeId());
                      // 查询奖励金额
                      Map<String, Object> rewardMap = new HashMap<String, Object>();
                      rewardMap.put("orderTaskpackId", evalScore.getRelatedBusinessId());
                      rewardMap.put("gotScore", gotScore);
                      Double rewardAmount = bizEvalRewardStarService.queryEvalRewardStarByMap(rewardMap);
                      BizEvalRewardTaskpack bizEvalRewardTaskpack = new BizEvalRewardTaskpack();
                      bizEvalRewardTaskpack.setOrderTaskpackageId(evalScore.getRelatedBusinessId());
                      bizEvalRewardTaskpack.setGroupLeaderEmployeeId(evalScore.getGroupLeaderEmployeeId());
                      bizEvalRewardTaskpack.setRewardAmount(rewardAmount);
                      bizEvalRewardTaskpack.setRewardDatetime(date);
                      bizEvalRewardTaskpack.setCreateDate(date);
                      bizEvalRewardTaskpack.setUpdateDate(date);
                      bizEvalRewardTaskpackService.insert(bizEvalRewardTaskpack);
            	}
			}
    
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}