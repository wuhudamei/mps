package cn.damei.service.mobile.Inspector;

import cn.damei.common.utils.ListSortUtils;
import cn.damei.dao.mobile.Inspector.ApplyCheckPlanDao;
import cn.damei.entity.mobile.Inspector.ApplyCheckPlanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional(readOnly = true)
public class ApplyCheckPlanService {


    @Autowired
    private ApplyCheckPlanDao dao;


    public List<ApplyCheckPlanEntity> findList(Map<String, Object> map) {

        Integer pqcId = (Integer) map.get("pqcId");


        List<Integer> orderIds = dao.findOrderInfoDoneByPqcId(pqcId);
        List<ApplyCheckPlanEntity> applyCheckPlanEntities = dao.findList(map);

        Iterator iterator = applyCheckPlanEntities.iterator();
        while (iterator.hasNext()) {

            ApplyCheckPlanEntity applyCheckPlanEntity = (ApplyCheckPlanEntity) iterator.next();
            for (Integer orderId : orderIds) {
                if (applyCheckPlanEntity.getOrderId().equals(orderId)) {


                    iterator.remove();
                    break;
                }


            }


        }
        return applyCheckPlanEntities;
    }

    public List<ApplyCheckPlanEntity> findApplyCheckPlanDetails(String orderId) {

        List<ApplyCheckPlanEntity> allStatusLNodeList = dao.allStatusNode(orderId);
        List<ApplyCheckPlanEntity> applyNodeList = dao.nodeApply(orderId);
        Integer allStatusNodeListSize = allStatusLNodeList.size();
        Integer applyNodeListSize = applyNodeList.size();

        int count = 0;
        if (allStatusNodeListSize > 0) {
            if (applyNodeListSize > 0) {


                for (int j = 0; j < applyNodeListSize; j++) {
                    for (int v = 0; v < allStatusNodeListSize; v++) {



                        if (applyNodeList.get(j).getQcNodeId().equals(allStatusLNodeList.get(v).getQcNodeId())) {

                            if (Integer.valueOf(applyNodeList.get(j).getQcStatus()) >= 30) {
                                allStatusLNodeList.get(v).setIsChecked("1");

                                count = 0;
                            }
                            break;
                        } else {
                            count++;
                            if (count == allStatusNodeListSize) {


                                if (Integer.valueOf(applyNodeList.get(j).getQcStatus()) >= 30) {
                                    applyNodeList.get(j).setIsChecked("1");

                                    count = 0;
                                }

                                allStatusLNodeList.add(applyNodeList.get(j));
                            }


                        }


                    }


                }

            }


        }

        ListSortUtils<ApplyCheckPlanEntity> utils = new ListSortUtils<>();


        utils.sort(allStatusLNodeList, "checkNodeIndex", "ASC");
        return allStatusLNodeList;


    }


}
