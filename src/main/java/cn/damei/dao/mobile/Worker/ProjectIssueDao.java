package cn.damei.dao.mobile.Worker;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;



@MyBatisDao
public interface ProjectIssueDao {




    Integer  updateHandleStatusDataById(Integer handleId);
    void  updateProblemItemStatusDataById(Integer problemItemId);
}
