package cn.damei.web.mobile.Inspector;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.SessionUtils;
import cn.damei.service.mobile.Inspector.IssueReportService;
import cn.damei.web.mobile.home.JobSiteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = {"${adminPath}/app/pqc/issueReport"})
public class IssueReportController {

private static Map<String,String> mapInfo;
@Autowired
private IssueReportService service;


    @RequestMapping(value = {"toIssueReportIndex"})
    public String toIssueReportIndex(){


        return "mobile/modules/pqc/issueReport/quesUpList";
    }


    @RequestMapping(value = {"issueReport"})
    public String issueReport(String qcId,Model model){


      List<Map<String,Object>> mapList =  service.findIssueInfoByQcId(qcId);

         model.addAttribute("entityInfo",mapList.size()<0?null:mapList.isEmpty()?null:mapList.get(0));
        model.addAttribute("mapList",mapList);
        return "mobile/modules/pqc/issueReport/ques";
    }





    @RequestMapping(value = {"saveIssueReport"})
    @ResponseBody
    public String saveIssueReport( HttpServletRequest request,String qcId,String problemDescribe ,String delayDays,String typeId ){



        Map<String,Object> map2 = new HashMap<>(24);

        map2.put("pqcId",SessionUtils.getInspectorSession(request).getId());
        map2.put("status", BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_60);
        map2.put("isDelay","".equals(delayDays.toString().trim())?0:!JobSiteController.isNum(delayDays.toString())?0:Integer.valueOf(delayDays)>0?1:0);
        map2.put("businessType",BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_3);
        map2.put("createDate",new Date());
        map2.put("qcId",qcId);
        map2.put("problemDescribe",problemDescribe);
        map2.put("delayDays","".equals(delayDays.toString().trim())?0:delayDays);
        map2.put("typeId",typeId);




        this.service.saveIssueReport(map2);
        return qcId;


    }








    @RequestMapping(value = {"issueReportRecord"})
    public String issueReportRecord(String qcId,Model model){

            List<Map<String,Object>>mapList = this.service.findIssueReportRecord(qcId);

        model.addAttribute("mapList",mapList);
        return "mobile/modules/pqc/issueReport/quesUpRecordList";
    }



    @RequestMapping(value = {"issueReportDetail"})
    public String issueReportDetail(String issueId,Model model){
       Map<String,String> map =  this.service.findIssueReportDetail(issueId);
        model.addAttribute("map",map);
        return "mobile/modules/pqc/issueReport/quesUpDetails";
    }



    @RequestMapping(value = {"issueReportIndex"})
    @ResponseBody
    public List<Map<String,Object>> issueReportIndex(String text, HttpServletRequest request){

        String pqcId = String.valueOf(SessionUtils.getInspectorSession(request).getId());
        if(null==mapInfo){
            mapInfo = new HashMap<>();

        }
        mapInfo.clear();
            mapInfo.put("pqcId",pqcId);
        if(null!=text && !text.trim().equals("")){
            mapInfo.put("text",text);

        }

        List<Map<String,Object>>mapList = this.service.issueReportList(mapInfo);

        return mapList;
    }



}
