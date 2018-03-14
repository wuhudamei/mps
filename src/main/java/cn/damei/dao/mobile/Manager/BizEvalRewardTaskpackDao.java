package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BizEvalRewardTaskpack;
import cn.damei.entity.mobile.Manager.EmployeeRewardDetail;
import cn.damei.entity.mobile.Manager.GroupLeaderEvalReward;

import java.util.List;

/** 
* @author 邱威威qww 
* @version 创建时间：2016年10月5日 下午4:28:27 
* 类说明 
*/
@MyBatisDao
public interface BizEvalRewardTaskpackDao extends CrudDao2<BizEvalRewardTaskpack>{

    public Double queryRewardAmount(Integer orderTaskpackageId);

    public List<GroupLeaderEvalReward> queryRewardAmountByGroupLeaderEmployeeId(Integer groupLeaderEmployeeId);

    public EmployeeRewardDetail queryEmployeeRewardDetail(Integer orderTaskpackId);
}