/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.*;

import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;
import cn.damei.common.ProjectIssueUtil.ProjectUtil;
import cn.damei.common.savePhoto.SavePhoto;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.ComplaintForOtherDepartMent;
import cn.damei.dao.modules.ComplaintForOtherDepartMentDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 其他部门投诉Service
 *
 * @author mh
 * @version 2017-07-24
 */
@Service
@Transactional(readOnly = true)
public class ComplaintForOtherDepartMentService extends CrudService<ComplaintForOtherDepartMentDao, ComplaintForOtherDepartMent> {

    public ComplaintForOtherDepartMent get(String id) {
        return super.get(id);
    }

    public List<ComplaintForOtherDepartMent> findList(ComplaintForOtherDepartMent complaintForOtherDepartMent) {
        return super.findList(complaintForOtherDepartMent);
    }

    public Page<ComplaintForOtherDepartMent> findPage(Page<ComplaintForOtherDepartMent> page, ComplaintForOtherDepartMent complaintForOtherDepartMent) {
        return super.findPage(page, complaintForOtherDepartMent);
    }

    @Transactional(readOnly = false)
    public void save(ComplaintForOtherDepartMent complaintForOtherDepartMent) {
        super.save(complaintForOtherDepartMent);
    }

    @Transactional(readOnly = false)
    public void delete(ComplaintForOtherDepartMent complaintForOtherDepartMent) {
        super.delete(complaintForOtherDepartMent);
    }


    public List<Map<String, String>> findOrderInfoByText(String text, String orderId,  String storeId ) {

        Map<String, String> map = new HashMap<>(8);
        map.put("text", text);
        map.put("orderId", orderId);
        map.put("storeId", storeId);

        return dao.findOrderInfoByText(map);
    }


    @Autowired
    private SystemService service;
    @Autowired
    private ProjectUtil util;

    @Transactional(readOnly = false)

    public void saveComplaintInfo(HttpServletRequest request, String orderId, String complaintText, String... photos) {

        Date date = new Date();
        Map<String, Object> complaintPreMap = new HashMap<>(24);


        //保存投诉数据
        complaintPreMap.put("orderId", orderId);
        String empId = UserUtils.getUser().getEmpId();

        if (null != empId) {
            BizEmployee employee = service.getEmpInfoByUserEmpId(Integer.valueOf(empId));


            complaintPreMap.put("complaintSource", employee.getEmpType());
            complaintPreMap.put("complaintEmployeeId", employee.getId());
        }

        complaintPreMap.put("complaintStatus", ProjectProblemConstantUtil.PROJECT_PRE_PROBLEM_COMPLAINT_STATUS_CREATE);
        complaintPreMap.put("complaintDescribe", complaintText);
        complaintPreMap.put("statusDateTime", date);
        complaintPreMap.put("createDate", date);

        //保存预投诉问题
        dao.insert(complaintPreMap);

        Integer complaintPreId = (Integer) complaintPreMap.get("complaintPreId");
  //保存图片

        if (null != photos && photos.length > 0) {
            //保存图片


            List<Map<String, Object>> picMapList = new ArrayList<>();


            for (int x = 0; x < photos.length; x++) {

                Map<String, Object> map1 = new HashMap();
                map1.put("businessType", PictureTypeContantUtil.PICTURE_TYPE_113);
                map1.put("businessIdInt", complaintPreId);
                map1.put("picUrl", SavePhoto.savePic(request, photos[x], PicturePathContantUtil.UPLOAD_COMPLAINT_PRE_PATH));
                map1.put("picDateTime", date);
                picMapList.add(map1);

            }
            util.saveProjectIssuePic(picMapList);

        }


    }


    public  Map<String,Object> findDetailById(String preComplaintId){

        return dao.findDetailById(preComplaintId);
    }


}