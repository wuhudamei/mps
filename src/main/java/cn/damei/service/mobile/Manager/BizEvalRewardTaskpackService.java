
package cn.damei.service.mobile.Manager;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.BizEvalRewardTaskpackDao;
import cn.damei.entity.mobile.Manager.BizEvalRewardTaskpack;
import cn.damei.entity.mobile.Manager.EmployeeRewardDetail;
import cn.damei.entity.mobile.Manager.GroupLeaderEvalReward;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BizEvalRewardTaskpackService extends CrudService2<BizEvalRewardTaskpackDao, BizEvalRewardTaskpack> {

    public Double queryRewardAmount(Integer orderTaskpackageId){
        return dao.queryRewardAmount(orderTaskpackageId);
    }

    public List<GroupLeaderEvalReward> queryRewardAmountByGroupLeaderEmployeeId(Integer groupLeaderEmployeeId){
        return dao.queryRewardAmountByGroupLeaderEmployeeId(groupLeaderEmployeeId);
    }

    @Transactional(readOnly=false)
    public void insert(BizEvalRewardTaskpack bizEvalRewardTaskpack){
        dao.insert(bizEvalRewardTaskpack);
    }

    public EmployeeRewardDetail queryEmployeeRewardDetail(Integer orderTaskpackId){
        return dao.queryEmployeeRewardDetail(orderTaskpackId);
    }
}