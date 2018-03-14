package cn.damei.service.mobile.Worker;

import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;
import cn.damei.common.ProjectIssueUtil.ProjectUtil;
import cn.damei.dao.mobile.Worker.ProjectIssueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
@Transactional(readOnly = true)
public class ProjectIssueService {

    @Autowired
    private ProjectIssueDao dao;

    @Autowired
    private ProjectUtil util;

    public List<Map<String, Object>> findProblemByOrderId(Map<String,Object> map) {

        return util.findProblemByMap(map);
    }


    @Transactional(readOnly = false)
    public void updateHandleStatusDataById(Integer handleId,Integer workerId) {


        Map<String,String> handleMap = new HashMap<>();
        handleMap.put("handleId",String.valueOf(handleId));
        handleMap.put("handleStatus", ProjectProblemConstantUtil.PROJECT_PROBLEM_STATUS_10);
        handleMap.put("itemStatus", ProjectProblemConstantUtil.PROJECT_PROBLEM_ITEM_STATUS_20);

        util.updateHandleStatusDataById(handleMap);
        Map<String,Object> map =  new HashMap<>();

        map.put("dealId",handleId);
        map.put("dealPersonType",ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_WORKER_2);
        map.put("dealPersonId", workerId);
        map.put("dealStatus",ProjectProblemConstantUtil.PROJECT_PROBLEM_STATUS_10);
        map.put("dealDateTime",new Date());
        map.put("dealDescribe","");


        util.saveHandleLog(map);

    }


    public List<String> findPic(Map<String, String> map) {

        return util.findPicByIdAndType(map);
    }
}