package cn.damei.service.mobile.Inspector;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.dao.mobile.Inspector.IssueReportDao;
import cn.damei.service.mobile.Manager.WallAndFloorProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/5/24.
 */
@Service
@Transactional(readOnly = true)
public class IssueReportService {

    @Autowired
    private IssueReportDao  dao;
@Autowired
private WallAndFloorProblemService wallAndFloorProblemService;
    /**
     * 根据质检员id查询名下的约检信息(text 为搜索)
     * @param mapInfo (pqcId,text)
     * @return
     */
   public  List<Map<String,Object>> issueReportList(Map<String,String> mapInfo){

        return dao.issueReportList(mapInfo);
    }


    /**
     * 查询问题信息
     * @param qcId
     * @return
     */
    public  List<Map<String,Object>> findIssueInfoByQcId(String qcId){


        return dao.findIssueInfoByQcId(qcId);

    }
    /**
     * 保存上报问题信息
     * @param map
     */
    @Transactional(readOnly = false)
   public  void saveIssueReport(Map map){

       dao.saveIssueReport(map);
        wallAndFloorProblemService.saveProblemLog(Integer.valueOf(map.get("id").toString()),((Integer)map.get("pqcId")), BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_4,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_60,map.get("problemDescribe").toString());

   }



    /**
     * 查询问题记录
     * @param qcId
     * @return
     */
    public  List<Map<String,Object>> findIssueReportRecord(String qcId){


        return dao.findIssueReportRecord(qcId);

    }


    /**
     * 查询问题详情
     * @param issueId
     * @return
     */
   public  Map<String,String>  findIssueReportDetail(String issueId){


        return dao.findIssueReportDetail(issueId);
    }

}
