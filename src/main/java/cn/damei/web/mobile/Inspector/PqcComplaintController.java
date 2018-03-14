package cn.damei.web.mobile.Inspector;

import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.SessionUtils;
import cn.damei.service.mobile.Inspector.PqcComplaintService;
import cn.damei.entity.mobile.Inspector.Inspector;

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
@RequestMapping(value = "${adminPath}/app/pqc/project-issue")
public class PqcComplaintController {


    @Autowired
    private PqcComplaintService service;


    @RequestMapping(value = "list.php")
    public String list(HttpServletRequest request, Model model) {

        Integer pqcId = SessionUtils.getInspectorSession(request).getId();

        List<Map<String, Object>> mapList = service.list(pqcId);
        model.addAttribute("mapList", mapList);


        return "mobile/modules/pqc/complaint/complaint";
    }


    @RequestMapping(value = "checkIssueProblemByOrderId")
    public String checkIssueProblemByOrderId(HttpServletRequest request, Model model, Integer orderId) {
        Inspector pqc = SessionUtils.getInspectorSession(request);
        Map<String, Object> map = new HashMap<>(12);
        map.put("orderId", orderId);
        map.put("dealType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_PQC_3);
        map.put("dealPersonId", pqc.getId());
        List<Map<String, Object>> maps = service.findProblemByOrderId(map);
        if (maps.size() > 0) {

            model.addAttribute("customerInfo", maps.get(0).get("customerInfo"));
        }
        model.addAttribute("mapList", maps);


        return "mobile/modules/pqc/complaint/complaint2";
    }


    @RequestMapping(value = "pqcDealByHandleId")
    public String pqcDealByHandleId(HttpServletRequest request, Model model, Integer handleId) {
        Date date = new Date();
        Map<String, Object> map = service.findProblemByHandleId(handleId);

        String responseTime = map.get("responseTime").toString();

        Date problemCreateDate = (Date) map.get("createDate");



        Date problemDelayDate = DateUtils.addDate(problemCreateDate, (new Double(responseTime)).intValue());


        if (problemDelayDate.getTime() < date.getTime()) {


            map.put("delayDays", Math.abs(DateUtils.getDistanceOfTwoDate(date, problemDelayDate)) > 0 ? Math.abs(DateUtils.getDistanceOfTwoDate(date, problemDelayDate))+1 : 1);

        }
        model.addAttribute("map", map);
        return "mobile/modules/pqc/complaint/complaint3";
    }



    @RequestMapping(value = "savePqcDeal")
    @ResponseBody
    public String savePqcDeal(String photos[], String dealDescribe, HttpServletRequest request, Integer handleId, Model model) {









        String result = service.savePqcDeal(request, dealDescribe, handleId, photos);
        return result;

    }


    @RequestMapping(value = "pqcCheckDealDetail")
    public String managerCheckDealDetail(HttpServletRequest request, Integer handleId, Model model) {

        Map<String, Object> map = service.findProblemByHandleId(handleId);

        String responseTime = map.get("responseTime").toString();

        Date problemCreateDate = (Date) map.get("createDate");

        Date statusDateTime = (Date) map.get("statusDateTime");



        Date problemDelayDate = DateUtils.addDate(problemCreateDate, (new Double(responseTime)).intValue());


        if (problemDelayDate.getTime() < statusDateTime.getTime()) {

            Double value = Math.abs(DateUtils.getDistanceOfTwoDate(problemDelayDate, statusDateTime)) > 0 ? Math.abs(DateUtils.getDistanceOfTwoDate(problemDelayDate, statusDateTime))+1 : 1;
            map.put("delayDays", value);

        }

        model.addAttribute("map", map);
        return "mobile/modules/pqc/complaint/complainDetails";

    }

    @RequestMapping(value = "checkPic")
    public String checkPic(Integer handleId, Model model, HttpServletRequest request, Integer relatedId) {

        String businessIdInt;
        String businessType;

        if (null == relatedId && null != handleId) {
            businessIdInt = String.valueOf(handleId);
            businessType = PictureTypeContantUtil.PICTURE_TYPE_112;


        } else {
            businessIdInt = String.valueOf(relatedId);
            businessType = PictureTypeContantUtil.PICTURE_TYPE_200;

        }

        Map<String, String> map = new HashMap<>();

        map.put("businessIdInt", businessIdInt);
        map.put("businessType", businessType);

        List<String> list = service.findPic(map);

        model.addAttribute("picList", list);
        String baseUrl = request.getContextPath();
        model.addAttribute("baseUrl", baseUrl);

        return "mobile/modules/pqc/complaint/photo";


    }
    @RequestMapping(value = "selectCountNoDealByWorkOrderCode")
    @ResponseBody
    public int selectCountNoDealByWorkOrderCode(String workOrderCode){
    	
    	return service.selectCountNoDealByWorkOrderCode(workOrderCode);
    }

}
