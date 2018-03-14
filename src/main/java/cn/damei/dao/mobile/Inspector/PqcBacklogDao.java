package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface PqcBacklogDao {



    List<Map<String,String>> getPackRecheckInfoByPqcEmployeeId(Integer pqcEmployeeId);

    List<Map<String,String>> getApplyCheckDoneInfoByPqcEmployeeId(Integer pqcEmployeeId);

}
