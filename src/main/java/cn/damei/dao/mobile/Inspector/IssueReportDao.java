package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface IssueReportDao {



    List<Map<String,Object>> issueReportList(Map<String,String> mapInfo);







    List<Map<String,Object>> findIssueInfoByQcId(String qcId);







    void saveIssueReport(Map map);







    List<Map<String,Object>>  findIssueReportRecord(String qcId);





   Map<String,String>  findIssueReportDetail(String issueId);

}
