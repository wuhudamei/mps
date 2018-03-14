package cn.damei.service.modules;

import cn.damei.common.persistence.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.InspectCheckTimesDao;
import cn.damei.entity.modules.InspectCheckTimesEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional(readOnly = true)
public class InspectCheckTimesService extends CrudService<InspectCheckTimesDao, InspectCheckTimesEntity> {



    @Override
    @Transactional(readOnly = false)
    public Page<InspectCheckTimesEntity> findPage(Page<InspectCheckTimesEntity> page, InspectCheckTimesEntity entity) {
        entity.setPage(page);


        List<InspectCheckTimesEntity> allPqcId = dao.findPqcInfoByStoreIdAndProjectMode(entity);
        int pqcListSize = allPqcId.size();

        if (pqcListSize > 0) {


            Map<String, Object> queryMap = new ConcurrentHashMap<>(pqcListSize + 12);

            if (null != entity.getCheckStartDate()) {

                queryMap.put("startDate", entity.getCheckStartDate());
            }

            if (null != entity.getCheckEndDate()) {

                queryMap.put("endDate", entity.getCheckEndDate());
            }


            queryMap.put("list", allPqcId);



            List<InspectCheckTimesEntity> allCheckTimeList = dao.findCheckTimesByPqcId(queryMap);
            int checkTimesSize = allCheckTimeList.size();

            if (checkTimesSize > 0) {

                for (int i = 0; i < pqcListSize; i++) {
                    InspectCheckTimesEntity entity1 = allPqcId.get(i);

                    for (int v = 0; v < checkTimesSize; v++) {
                        InspectCheckTimesEntity entity2 = allCheckTimeList.get(v);
                        if (entity1.getInspectId().equals(entity2.getInspectId())) {

                            entity1.setCheckConstructionSiteTimes(entity2.getCheckConstructionSiteTimes());

                            break;
                        }

                    }

                }

            }



            List<InspectCheckTimesEntity> allSignInfo = dao.findSignInfoByEmpId(queryMap);


            int allSignListSize = allSignInfo.size();


            if (allSignListSize > 0) {

                for (int i = 0; i < pqcListSize; i++) {

                    for (int v = 0; v < allSignListSize; v++) {


                        if (allPqcId.get(i).getInspectId().equals(allSignInfo.get(v).getInspectId())) {

                            allPqcId.get(i).setSignTimes(allSignInfo.get(v).getSignTimes());

                            break;
                        }


                    }


                }


            }



            List<InspectCheckTimesEntity> allQcBillList = dao.findAllPqcBillByEmpId(queryMap);
            int qcBillListSize = allQcBillList.size();


            if (qcBillListSize > 0) {


                List<InspectCheckTimesEntity> itemList = dao.findPqcItemInfoByBillId(allQcBillList);
                int itemListSize = itemList.size();

                if (qcBillListSize > 0 && itemListSize > 0) {


                    for (int i = 0; i < qcBillListSize; i++) {

                        for (int v = 0; v < itemListSize; v++) {


                            if (allQcBillList.get(i).getBillId().equals(itemList.get(v).getBillId())) {


                                allQcBillList.get(i).setFindTotalMoney(itemList.get(v).getFindTotalMoney());


                                break;


                            }


                        }


                    }


                }


            }


            if (qcBillListSize > 0) {

                for (int q = 0; q < pqcListSize; q++) {


                    int selectCheckReportTimes = 0;
                    int applyCheckReportTimes = 0;
                    int recheckReportTimes = 0;
                    int recheckTimes = 0;
                    int fineMoneyTimes = 0;
                    double findMoneyTotal = 0d;
                    InspectCheckTimesEntity entity1 = allPqcId.get(q);
                    for (int x = 0; x < qcBillListSize; x++) {

                        InspectCheckTimesEntity entity2 = allQcBillList.get(x);


                        if (entity1.getInspectId().equals(entity2.getInspectId())) {


                            if (null != entity2.getFindTotalMoney()) {

                                fineMoneyTimes++;
                                findMoneyTotal += entity2.getFindTotalMoney();
                            }

                            if ("1".equals(entity2.getIsRecheck())) {

                                recheckTimes++;
                            }

                            if (null != entity2.getQcBillStatus() && Integer.valueOf(entity2.getQcBillStatus()) > 3) {


                                if ("1".equals(entity2.getIsRecheck())) {

                                    recheckReportTimes++;
                                } else {
                                    if ("1".equals(entity2.getQcBillType())) {

                                        applyCheckReportTimes++;

                                    } else if ("2".equals(entity2.getQcBillType())) {

                                        selectCheckReportTimes++;


                                    }


                                }


                            }


                        }


                    }


                    entity1.setFindTotalMoney(new Double(findMoneyTotal).intValue());
                    entity1.setFineTimes(fineMoneyTimes);

                    entity1.setRecheckTimes(recheckTimes);
                    entity1.setRecheckReportTimes(recheckReportTimes);

                    entity1.setRandomCheckReportTimes(selectCheckReportTimes);
                    entity1.setAboutCheckReportTimes(applyCheckReportTimes);

                    entity1.setInspectReportTimes(selectCheckReportTimes + applyCheckReportTimes + recheckReportTimes);

                }
            }


            page.setList(allPqcId);
            return page;

        } else {


            return null;
        }


    }
}
