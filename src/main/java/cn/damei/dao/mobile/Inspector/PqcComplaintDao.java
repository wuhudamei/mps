package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface PqcComplaintDao {




    Map<String,Object> findProblemByHandleId(Integer handleId);

    Integer findIssueIsDoneByHandleId(Integer handleId);

    void saveHandleDescribeByHandleIdAndDealDescribe(Map<String,String> map);
}
