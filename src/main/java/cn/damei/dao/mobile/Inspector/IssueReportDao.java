package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/5/24.
 */
@MyBatisDao
public interface IssueReportDao {


    /**
     * 根据质检员id查询名下的约检信息(text 为搜索)
     * @param mapInfo (pqcId,text)
     * @return
     */
    List<Map<String,Object>> issueReportList(Map<String,String> mapInfo);






    /**
     * 查询问题信息
     * @param qcId
     * @return
     */
    List<Map<String,Object>> findIssueInfoByQcId(String qcId);






    /**
     * 保存上报问题信息
     * @param map
     */
    void saveIssueReport(Map map);






    /**
     * 查询问题记录
     * @param qcId
     * @return
     */
    List<Map<String,Object>>  findIssueReportRecord(String qcId);




    /**
     * 查询问题详情
     * @param issueId
     * @return
     */
   Map<String,String>  findIssueReportDetail(String issueId);

}
