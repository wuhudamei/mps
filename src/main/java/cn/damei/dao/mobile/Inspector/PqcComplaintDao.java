package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/7/4.
 */
@MyBatisDao
public interface PqcComplaintDao {

//    List<Map<String,Object>> findProblemByOrderId(Integer orderId);


    Map<String,Object> findProblemByHandleId(Integer handleId);

    Integer findIssueIsDoneByHandleId(Integer handleId);

    void saveHandleDescribeByHandleIdAndDealDescribe(Map<String,String> map);
}
