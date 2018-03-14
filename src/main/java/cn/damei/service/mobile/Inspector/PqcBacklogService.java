package cn.damei.service.mobile.Inspector;

import cn.damei.dao.mobile.Inspector.PqcBacklogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/6/14.
 */

@Service
@Transactional(readOnly = true)
public class PqcBacklogService{

@Autowired
private PqcBacklogDao dao;

   public  List<Map<String,String>> getPackRecheckInfoByPqcEmployeeId(Integer pqcEmployeeId){
       return dao.getPackRecheckInfoByPqcEmployeeId(pqcEmployeeId);
   }

    public List<Map<String,String>> getApplyCheckDoneInfoByPqcEmployeeId(Integer pqcEmployeeId){

       return dao.getApplyCheckDoneInfoByPqcEmployeeId(pqcEmployeeId);
    }
}
