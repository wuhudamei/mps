/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.ProjectIssueUtil.ProjectUtil;
import cn.damei.common.savePhoto.GetBusinessPhoto;
import cn.damei.common.savePhoto.SavePhoto;
import cn.damei.dao.modules.ComplaintForOtherDepartMentDealDao;
import cn.damei.entity.modules.ComplaintForOtherDepartMentDeal;
import cn.damei.entity.modules.PreComplaintDataInfoVo;
import cn.damei.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 其他部门投诉Service
 *
 * @author mh
 * @version 2017-07-24
 */
@Service
@Transactional(readOnly = true)
public class ComplaintForOtherDepartMentDealService extends CrudService<ComplaintForOtherDepartMentDealDao, ComplaintForOtherDepartMentDeal> {
    //private PhoneMessageEntity messageEntity = null;


    //private String managerMessageContent = "";
    //private String pqcMessageContent = "";


    public ComplaintForOtherDepartMentDeal get(String id) {
        return super.get(id);
    }

    public List<ComplaintForOtherDepartMentDeal> findList(ComplaintForOtherDepartMentDeal complaintForOtherDepartMent) {
        return super.findList(complaintForOtherDepartMent);
    }

    public Page<ComplaintForOtherDepartMentDeal> findPage(Page<ComplaintForOtherDepartMentDeal> page, ComplaintForOtherDepartMentDeal complaintForOtherDepartMent) {
        return super.findPage(page, complaintForOtherDepartMent);
    }


    /**
     * 预投诉单的处理和驳回状态更新
     */

    @Transactional(readOnly = false)
    public void updatePreComplaintStatus(String id, String text, String status) {


        Map<String, String> paraMap = new HashMap<>(24);
        paraMap.put("id", id);
        paraMap.put("statusDescribe", text);
        paraMap.put("status", status);
        paraMap.put("dealEmployeeId", UserUtils.getUser().getEmpId());
        dao.updatePreComplaintStatus(paraMap);
    }


    public Map<String, Object> findOrderInfoByPreComplaintId(String id) {

        return dao.findOrderInfoByPreComplaintId(id);

    }

    public List<Map<String, String>> findComplaintTypeByStoreId(String storeId) {


        return dao.findComplaintTypeByStoreId(storeId);
    }

    public List<Map<String, String>> findComplaintItemByTypeId(String complaintTypeId) {


        return dao.findComplaintItemByTypeId(complaintTypeId);
    }

    /**
     * 预投诉图片
     */
    public List<Map<String, String>> selectPicByPreId(String preComplaintId) {

        return dao.selectPicByPreId(preComplaintId);

    }

    @Transactional(readOnly = false)
    public void deletePrePic(String picId) {

        dao.deletePrePic(picId);
    }


    //本地保存图片
    @Autowired
    private SavePhoto savePhoto;

    //数据库保存图片接口
    @Autowired
    private ProjectUtil projectUtil;

    @Autowired
    private GetBusinessPhoto photo;
    @Autowired
    private BizOrderComplaintService complaintService;

    /**
     * 预投诉接收(id:预投诉id  orderId )
     */
    @Transactional(readOnly = false)
    public void saveComplaintData(HttpServletRequest request, ComplaintForOtherDepartMentDeal vo) {


        Date date = new Date();
        //查出投诉人id,姓名,手机,经理id和质检id 投诉来源
        ComplaintForOtherDepartMentDeal departMentDeal = dao.get(vo.getId());

        //1:更新预投诉单状态为 已接收

        updatePreComplaintStatus(vo.getId(), "", ProjectProblemConstantUtil.PROJECT_PRE_PROBLEM_COMPLAINT_STATUS_ACCEPET);


        //2:保存一条投诉单
        Map<String, Object> complaintMap = new ConcurrentHashMap<>(24);
        complaintMap.put("orderId", vo.getOrderId());

        //其他部门投诉
        complaintMap.put("dataInputChannel", ProjectProblemConstantUtil.PROJECT_PROBLEM_COMPLAINT_DATA_INPUT_CHANNEL_3);
        if(null!=departMentDeal.getComplaintPersonId()&&null!= departMentDeal.getComplaintPersonName()){
            complaintMap.put("complaintPersonName", departMentDeal.getComplaintPersonName());
            complaintMap.put("complaintPersonPhone", departMentDeal.getComplaintPersonPhone());
            complaintMap.put("complaintPersonEmployeeId", departMentDeal.getComplaintPersonId());

        }

        if(null!=departMentDeal.getComplaintSource()){

            complaintMap.put("complaintSource", departMentDeal.getComplaintSource());
        }

        complaintMap.put("status", ProjectProblemConstantUtil.COMPLAINT_STATUS_10);

        complaintMap.put("relatedBusinessType", ProjectProblemConstantUtil.ORDER_COMPLAINT_RELATED_BUSINESS_TYPE_1);
        complaintMap.put("relatedBusinessId", vo.getId());
        complaintMap.put("createDate", date);


        //2:保存投诉单结束
        dao.saveComplaintInfo(complaintMap);



        //3:开始  内部集合为具体问题
        List<PreComplaintDataInfoVo> list = vo.getList();

        int listSize = list.size();
        if (listSize > 0) {


            for (int i = 0; i < listSize; i++) {

                if (null != list.get(i) && null != list.get(i).getItemId() && null != list.get(i).getTypeId()) {


                    //3:保存投诉单的问题表
                    Map<String, Object> complaintProblemMap = new ConcurrentHashMap<>(48);

                    String complaintId = complaintMap.get("id").toString();


                    complaintProblemMap.put("orderComplaintId", complaintId);
                    complaintProblemMap.put("complaintProblemTypeId", list.get(i).getTypeId());


                    //查询type数据
                    Map<String, Integer> typeInfoMap = dao.selectInfoByTypeId(list.get(i).getTypeId());
                    Integer templateId = typeInfoMap.get("taskPackageTemplatId");
                    if (null != templateId) {
                        complaintProblemMap.put("taskPackageTemplatId", typeInfoMap.get("taskPackageTemplatId"));

                        //根据任务包id+订单id 查询任务包id
                        Map<String, String> packMapInfo = dao.findPackInfoByTemplateIdAndOrderId(vo.getOrderId(), String.valueOf(typeInfoMap.get("taskPackageTemplatId")));

                        //部分传统的订单可能没有任务包
                        if (null != packMapInfo) {
                            complaintProblemMap.put("orderTaskpackageId", packMapInfo.get("packId"));

                        }

                    }

                    complaintProblemMap.put("complaintRoleType", typeInfoMap.get("dealPersonType"));
                    complaintProblemMap.put("complaintProblemDescribe", list.get(i).getItemDescribe());
                    complaintProblemMap.put("status", ProjectProblemConstantUtil.PROJECT_PROBLEM_ITEM_STATUS_10);
                    complaintProblemMap.put("createDate", date);

                    //3:保存问题项结束
                    dao.saveComplaintProblem(complaintProblemMap);

                    String complaintProblemId = complaintProblemMap.get("id").toString();


                    //保存图片的集合
                    List<Map<String, Object>> picsMapList = new ArrayList<>(12);
                    //4:保存问题表对应的图片
                    String photos[] = list.get(i).getPhotos();
                    if (null != photos) {
                        int length = photos.length;
                        if (length > 0) {

                            for (int v = 0; v < length; v++) {
                                Map<String, Object> picsMap = new ConcurrentHashMap<>(12);
                                String path = savePhoto.savePic(request, photos[v], PicturePathContantUtil.UPLOAD_COMPLAINTPROBLEM_PROJECT_DEAL);


                                picsMap.put("businessType", PictureTypeContantUtil.PICTURE_TYPE_200);
                                picsMap.put("businessIdInt", complaintProblemId);
                                picsMap.put("picUrl", path);
                                picsMap.put("picDateTime", date);
                                picsMapList.add(picsMap);

                            }


                        }

                    }else if(i==0){

                        //第一个图片是比较特殊的, 由预投诉的图片 转成投诉的问题的第一个默认问题的图片

                        List<String> itemPhotos = photo.getBusinessPhoto(vo.getId(), PictureTypeContantUtil.PICTURE_TYPE_113);

                        int size = itemPhotos.size();
                        if (size > 0) {


                            for(int v =0;v<size;v++){

                                Map<String, Object> picsMap = new ConcurrentHashMap<>(12);
                                picsMap.put("businessType", PictureTypeContantUtil.PICTURE_TYPE_200);
                                picsMap.put("businessIdInt", complaintProblemId);
                                picsMap.put("picUrl", itemPhotos.get(v));
                                picsMap.put("picDateTime", date);
                                picsMapList.add(picsMap);

                            }

                        }
                    }


                    if(picsMapList.size()>0){

                        //4:保存问题表对应图片结束
                        projectUtil.saveProjectIssuePic(picsMapList);
                    }


                    //5:保存问题表和问题项的映射

                    Map<String, Object> problemItemMappingMap = new ConcurrentHashMap<>(12);
                    problemItemMappingMap.put("orderComplaintProblemId", complaintProblemId);
                    problemItemMappingMap.put("complaintProblemItemId", list.get(i).getItemId());
                    problemItemMappingMap.put("createDate", date);


                    //5 保存问题表和问题项的映射 结束
                    dao.saveProblemAndItemMapping(problemItemMappingMap);


                    //6:拆分问题处理表数据,并保存

                    //查看处理人员类型 1经理 2:经理+工人 3:质检
                    String dealPersonType = String.valueOf(typeInfoMap.get("dealPersonType"));

                    Map<String, Object> handleMap = new ConcurrentHashMap<>(24);
                    handleMap.put("orderComplaintProblemId", complaintProblemId);
                    handleMap.put("dealStatus", ProjectProblemConstantUtil.PROJECT_PROBLEM_STATUS_0);
                    handleMap.put("dealStatusDatetime", date);
                    handleMap.put("createDate", date);
                    if ("1".equals(dealPersonType)) {

                        handleMap.put("dealPersonType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_MANAGER_1);
                        handleMap.put("dealEmployeeId", departMentDeal.getManagerId());

                        //保存经理处理数据
                        saveHandleInfo(handleMap);


                        //2: 项目经理+工人 需要拆分
                    } else if ("2".equals(dealPersonType)) {
                        //如果问题分类有对应任务包id
                        if (null != templateId) {

                            //查询工人们
                            List<String> empIds = dao.findWorkerInfo(vo.getOrderId(), String.valueOf(templateId));
                            int size = empIds==null ? 0 : empIds.size();
                            if (size > 0 && null != empIds) {
                                handleMap.put("dealPersonType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_WORKER_2);
                                for (int q = 0; q < size; q++) {

                                    try {
                                        handleMap.put("dealEmployeeId", empIds.get(q));

                                        //有几个工人就保存几个工人的处理数据
                                        saveHandleInfo(handleMap);

                                    } catch (NullPointerException e) {

                                        continue;
                                    }


                                }

                            }


                        }


                        //接着保存一个经理的数据
                        handleMap.put("dealPersonType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_MANAGER_1);
                        handleMap.put("dealEmployeeId", departMentDeal.getManagerId());

                        //保存经理处理数据
                        saveHandleInfo(handleMap);


                        //7:发送短信
                        complaintService.saveMessageContent(Integer.valueOf(complaintId), departMentDeal.getCustomerAddress(), departMentDeal.getCustomerName(), list.get(i).getItemId(), departMentDeal.getManagerId());
                    } else if ("3".equals(dealPersonType)) {

                        //接着保存一个质检的数据
                        handleMap.put("dealPersonType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_PQC_3);
                        handleMap.put("dealEmployeeId", departMentDeal.getPqcId());

                        //保存质检员处理数据
                        saveHandleInfo(handleMap);

                        //7:发送短信

                        complaintService.saveMessageContent(Integer.valueOf(complaintId), departMentDeal.getCustomerAddress(), departMentDeal.getCustomerName(), list.get(i).getItemId(), departMentDeal.getPqcId());


                    }

                }


            }
        }


    }


    @Transactional(readOnly = false)
    private void saveHandleInfo(Map<String, Object> map) {


        dao.saveProblemHandleInfo(map);


    }


}