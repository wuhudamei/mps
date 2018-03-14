package cn.damei.dao.mobile.Worker;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/7/3.
 */

@MyBatisDao
public interface ProjectIssueDao {



//    List<Map<String,Object>> findProblemByOrderId(Map<String,Integer> map);
    Integer  updateHandleStatusDataById(Integer handleId);
    void  updateProblemItemStatusDataById(Integer problemItemId);
}
