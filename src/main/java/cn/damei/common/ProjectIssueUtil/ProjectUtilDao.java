package cn.damei.common.ProjectIssueUtil;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;



@MyBatisDao
public interface ProjectUtilDao {



    List<Map<String, Object>> findProjectProblemByDealPersonId(Integer employeeId);


    List<Map<String,Object>> findProblemByMap(Map<String,Object> map);



    void saveHandleLog(Map<String, Object> map);



   void updateHandleStatusDataById(Map<String, String> map);

    Integer findRelatedIdByhandleId(Integer handleId);



    void updateProblemItemStatusDataById(Map<String, String> map);



    void saveProjectIssuePic(List<Map<String, Object>> map);



    List<String> findPicListByRelatedIdAndType(Map<String,String> map);



    Map<String,Long> checkIsComplaintAllOver(Integer dealId);



    void updateOrderComplaintById(Map<String,String> complaintMap);


    Integer updateOrderComplaintOver(Integer handleId);

	int selectCountNoDealByWorkOrderCode(String workOrderCode);
}
