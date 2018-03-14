
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


@Service
@Transactional(readOnly = true)
public class ComplaintForOtherDepartMentDealService extends CrudService<ComplaintForOtherDepartMentDealDao, ComplaintForOtherDepartMentDeal> {







    public ComplaintForOtherDepartMentDeal get(String id) {
        return super.get(id);
    }

    public List<ComplaintForOtherDepartMentDeal> findList(ComplaintForOtherDepartMentDeal complaintForOtherDepartMent) {
        return super.findList(complaintForOtherDepartMent);
    }

    public Page<ComplaintForOtherDepartMentDeal> findPage(Page<ComplaintForOtherDepartMentDeal> page, ComplaintForOtherDepartMentDeal complaintForOtherDepartMent) {
        return super.findPage(page, complaintForOtherDepartMent);
    }




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


    public List<Map<String, String>> selectPicByPreId(String preComplaintId) {

        return dao.selectPicByPreId(preComplaintId);

    }

    @Transactional(readOnly = false)
    public void deletePrePic(String picId) {

        dao.deletePrePic(picId);
    }



    @Autowired
    private SavePhoto savePhoto;


    @Autowired
    private ProjectUtil projectUtil;

    @Autowired
    private GetBusinessPhoto photo;
    @Autowired
    private BizOrderComplaintService complaintService;


    @Transactional(readOnly = false)
    public void saveComplaintData(HttpServletRequest request, ComplaintForOtherDepartMentDeal vo) {


        Date date = new Date();

        ComplaintForOtherDepartMentDeal departMentDeal = dao.get(vo.getId());



        updatePreComplaintStatus(vo.getId(), "", ProjectProblemConstantUtil.PROJECT_PRE_PROBLEM_COMPLAINT_STATUS_ACCEPET);



        Map<String, Object> complaintMap = new ConcurrentHashMap<>(24);
        complaintMap.put("orderId", vo.getOrderId());


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



        dao.saveComplaintInfo(complaintMap);




        List<PreComplaintDataInfoVo> list = vo.getList();

        int listSize = list.size();
        if (listSize > 0) {


            for (int i = 0; i < listSize; i++) {

                if (null != list.get(i) && null != list.get(i).getItemId() && null != list.get(i).getTypeId()) {



                    Map<String, Object> complaintProblemMap = new ConcurrentHashMap<>(48);

                    String complaintId = complaintMap.get("id").toString();


                    complaintProblemMap.put("orderComplaintId", complaintId);
                    complaintProblemMap.put("complaintProblemTypeId", list.get(i).getTypeId());



                    Map<String, Integer> typeInfoMap = dao.selectInfoByTypeId(list.get(i).getTypeId());
                    Integer templateId = typeInfoMap.get("taskPackageTemplatId");
                    if (null != templateId) {
                        complaintProblemMap.put("taskPackageTemplatId", typeInfoMap.get("taskPackageTemplatId"));


                        Map<String, String> packMapInfo = dao.findPackInfoByTemplateIdAndOrderId(vo.getOrderId(), String.valueOf(typeInfoMap.get("taskPackageTemplatId")));


                        if (null != packMapInfo) {
                            complaintProblemMap.put("orderTaskpackageId", packMapInfo.get("packId"));

                        }

                    }

                    complaintProblemMap.put("complaintRoleType", typeInfoMap.get("dealPersonType"));
                    complaintProblemMap.put("complaintProblemDescribe", list.get(i).getItemDescribe());
                    complaintProblemMap.put("status", ProjectProblemConstantUtil.PROJECT_PROBLEM_ITEM_STATUS_10);
                    complaintProblemMap.put("createDate", date);


                    dao.saveComplaintProblem(complaintProblemMap);

                    String complaintProblemId = complaintProblemMap.get("id").toString();



                    List<Map<String, Object>> picsMapList = new ArrayList<>(12);

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


                        projectUtil.saveProjectIssuePic(picsMapList);
                    }




                    Map<String, Object> problemItemMappingMap = new ConcurrentHashMap<>(12);
                    problemItemMappingMap.put("orderComplaintProblemId", complaintProblemId);
                    problemItemMappingMap.put("complaintProblemItemId", list.get(i).getItemId());
                    problemItemMappingMap.put("createDate", date);



                    dao.saveProblemAndItemMapping(problemItemMappingMap);





                    String dealPersonType = String.valueOf(typeInfoMap.get("dealPersonType"));

                    Map<String, Object> handleMap = new ConcurrentHashMap<>(24);
                    handleMap.put("orderComplaintProblemId", complaintProblemId);
                    handleMap.put("dealStatus", ProjectProblemConstantUtil.PROJECT_PROBLEM_STATUS_0);
                    handleMap.put("dealStatusDatetime", date);
                    handleMap.put("createDate", date);
                    if ("1".equals(dealPersonType)) {

                        handleMap.put("dealPersonType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_MANAGER_1);
                        handleMap.put("dealEmployeeId", departMentDeal.getManagerId());


                        saveHandleInfo(handleMap);



                    } else if ("2".equals(dealPersonType)) {

                        if (null != templateId) {


                            List<String> empIds = dao.findWorkerInfo(vo.getOrderId(), String.valueOf(templateId));
                            int size = empIds==null ? 0 : empIds.size();
                            if (size > 0 && null != empIds) {
                                handleMap.put("dealPersonType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_WORKER_2);
                                for (int q = 0; q < size; q++) {

                                    try {
                                        handleMap.put("dealEmployeeId", empIds.get(q));


                                        saveHandleInfo(handleMap);

                                    } catch (NullPointerException e) {

                                        continue;
                                    }


                                }

                            }


                        }



                        handleMap.put("dealPersonType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_MANAGER_1);
                        handleMap.put("dealEmployeeId", departMentDeal.getManagerId());


                        saveHandleInfo(handleMap);



                        complaintService.saveMessageContent(Integer.valueOf(complaintId), departMentDeal.getCustomerAddress(), departMentDeal.getCustomerName(), list.get(i).getItemId(), departMentDeal.getManagerId());
                    } else if ("3".equals(dealPersonType)) {


                        handleMap.put("dealPersonType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_PQC_3);
                        handleMap.put("dealEmployeeId", departMentDeal.getPqcId());


                        saveHandleInfo(handleMap);



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