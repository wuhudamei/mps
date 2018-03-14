package cn.damei.service.mobile.Inspector;

import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.Base64Util;
import cn.damei.common.ProjectIssueUtil.ProjectUtil;
import cn.damei.common.SessionUtils;
import cn.damei.dao.mobile.Inspector.PqcComplaintDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.*;

/**
 * Created by joseph on 2017/7/4.
 */

@Service
@Transactional(readOnly = true)
public class PqcComplaintService {


    @Autowired
    private PqcComplaintDao dao;

    @Autowired
    private ProjectUtil util;

    public List<Map<String,Object>> list(Integer pqcId){
       List<Map<String,Object>> maps= util.findProjectProblemByDealPersonId(pqcId);

       return maps;

    }


    public List<Map<String, Object>> findProblemByOrderId(Map<String, Object> map) {

        return util.findProblemByMap(map);
    }


    public Map<String, Object> findProblemByHandleId(Integer handleId) {

        return dao.findProblemByHandleId(handleId);
    }


    @Transactional(readOnly = false)
    public String savePqcDeal(HttpServletRequest request, String dealDescribe, Integer handleId, String[] photos) {
        //去重复数据校验, 查询是否已经处理过该问题
        Integer isExist = dao.findIssueIsDoneByHandleId(handleId);
        if (null != isExist && isExist == 0) {
            Date date = new Date();
            Map<String, String> map = new HashMap<>();
            //保存答复内容
            //更新处理表状态-->20
            //更新投诉问题状态->30 (已处理)
            map.put("handleId", String.valueOf(handleId));
            map.put("dealDescribe", dealDescribe);
            dao.saveHandleDescribeByHandleIdAndDealDescribe(map);
            map.put("handleStatus", ProjectProblemConstantUtil.PROJECT_PROBLEM_STATUS_20);
            map.put("itemStatus", ProjectProblemConstantUtil.PROJECT_PROBLEM_ITEM_STATUS_30);
            util.updateHandleStatusDataById(map);


            if (null != photos && photos.length > 0) {
                //保存图片


                List<Map<String, Object>> picMapList = new ArrayList<>();


                for (int x = 0; x < photos.length; x++) {

                    Map<String, Object> map1 = new HashMap();
                    map1.put("businessType", PictureTypeContantUtil.PICTURE_TYPE_112);
                    map1.put("businessIdInt", handleId);
                    map1.put("picUrl", savePic(request, photos[x]));
                    map1.put("picDateTime", date);
                    picMapList.add(map1);

                }
                util.saveProjectIssuePic(picMapList);

            }


            //保存对应log

            Map<String,Object> logMap = new HashMap<>();

            logMap.put("dealId",handleId);
            logMap.put("dealPersonType",ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_PQC_3);
            logMap.put("dealPersonId", SessionUtils.getInspectorSession(request).getId());
            logMap.put("dealStatus",ProjectProblemConstantUtil.PROJECT_PROBLEM_STATUS_20);
            logMap.put("dealDateTime",date);
            logMap.put("dealDescribe",dealDescribe);

            util.saveHandleLog(logMap);
            return "1";
        } else {

            //已经处理过的
            return "2";
        }


    }

    public String savePic(HttpServletRequest request, String photo) {


        String rootPath = request.getSession().getServletContext().getRealPath("");
        File filePath = new File(rootPath + PicturePathContantUtil.UPLOAD_PQC_PROJECT_DEAL + DateUtils.getDate1());


        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        // String rootPath = RootName.SystemEnvironment(request);

        // 判断该文件是否存在
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdirs();
        }
        String filepath = filePath + filePath.separator + uuid + ".jpeg";
        Base64Util.generateImage(photo, filepath);

        String picpath = PicturePathContantUtil.UPLOAD_PQC_PROJECT_DEAL  + DateUtils.getDate1() + filePath.separator + uuid
                + ".jpeg";

        return picpath;


    }

    /**
     * 查看图片
     *
     *
     * @return
     */
    public List<String> findPic( Map<String,String> map ){

       List<String>list = util.findPicByIdAndType(map);

       return list;
    }


	public int selectCountNoDealByWorkOrderCode(String workOrderCode) {
		return util.selectCountNoDealByWorkOrderCode(workOrderCode);
	}
}
