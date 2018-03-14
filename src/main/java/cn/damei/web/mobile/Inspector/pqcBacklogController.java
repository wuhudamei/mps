package cn.damei.web.mobile.Inspector;

import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.service.mobile.Inspector.PqcBacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping(value = "${adminPath}/app/pqc/backlog")
public class pqcBacklogController {

    @Autowired
    private PqcBacklogService service;


    @RequestMapping(value = "{backlog}")
    public String backlogList(@PathVariable(value = "backlog") String backlog) {


        return "mobile/modules/pqc/backlog/backlogList";
    }


    @RequestMapping(value = "pack-recheck-info.php")
    @ResponseBody
    public List<Map<String, String>> packCheckInfo(HttpServletRequest request) {
        Inspector inspector = SessionUtils.getInspectorSession(request);

        List<Map<String, String>> packInfoList = service.getPackRecheckInfoByPqcEmployeeId(inspector.getId());

        return packInfoList;


    }

    @RequestMapping(value = "apply-check-done.php")
    @ResponseBody
    public List<Map<String, String>> applyCheckDone(HttpServletRequest request) {
        Inspector inspector = SessionUtils.getInspectorSession(request);

        List<Map<String, String>> applyCheckDoneList = service.getApplyCheckDoneInfoByPqcEmployeeId(inspector.getId());

        return applyCheckDoneList;
    }
}
