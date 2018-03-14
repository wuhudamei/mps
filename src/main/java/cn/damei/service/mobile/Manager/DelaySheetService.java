package cn.damei.service.mobile.Manager;

import java.text.SimpleDateFormat;
import java.util.*;

import cn.damei.common.utils.DateUtils;
import cn.damei.dao.modules.DelayBillDao;
import cn.damei.entity.modules.BizNodePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.mobile.Manager.DelaySheetDao;
import cn.damei.entity.mobile.Manager.DelaySheet;
import cn.damei.entity.modules.DelayBill;
import cn.damei.entity.modules.Dict;

@Service
@Transactional(readOnly = false)
public class DelaySheetService extends CrudService<DelaySheetDao, DelaySheet> {


    @Autowired
    private DelayBillDao delayBillDao;


    public List<DelaySheet> findDelayOrder(Integer id) {

        return dao.findDelayOrder(id);
    }


    public List<Dict> findDelayCategory(int i) {

        return dao.findDelayCategory(i);
    }


    public List<Dict> findDelayCategorytow(String id, int i) {

        return dao.findDelayCategorytow(id, i);
    }

    public List<Dict> findDelayCategoryStatus() {
        return dao.findDelayCategoryStatus();
    }


    public List<Dict> findOrderNodePlan(String orderId) {
        List<Dict> list = new ArrayList<>();
        Map map = new HashMap();
        map.put("orderId",orderId);
        map.put("status","");

        boolean boo = dao.isApplyDelay(map);

        map.put("status","20");
        boolean bool = dao.isApplyDelay(map);

        if (boo) {

            if(bool){

                List<Dict> scraplist = dao.findScrapDelayBill(orderId);
                map.put("list",scraplist);
                if(scraplist.size() > 0){

                    List<Dict> dictList = dao.findNodePlanFinsh(map);
                        if(dictList.size() > 0){

                            for (int i = 0; i < scraplist.size(); i++) {
                                for (Dict dict: dictList) {
                                    if(scraplist.get(i).getValue().equals(dict.getValue())){
                                        scraplist.remove(i);
                                    }
                                }
                            }

                        }

                    list.addAll(scraplist);
                }
                list.addAll(dao.findOrderNodePlan(orderId));
            }else{

                list.addAll(dao.findOrderAllNode(orderId));
            }
        } else {

            list.addAll(dao.findOrderAllNode(orderId));
        }


        Map<Integer, Dict> mapsort = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            Dict dict = list.get(i);
            mapsort.put(dict.getSort(),dict);
        }
        List<Dict> listDict = new ArrayList<>();

        Set<Integer> strings = mapsort.keySet();
        List<Integer> listSort = new ArrayList<Integer>(strings);
        Collections.sort(listSort);
        for (int i = 0; i < listSort.size(); i++) {
            listDict.add(mapsort.get(listSort.get(i)));
        }
        return listDict;
    }

    public DelaySheet checkSubmit(String orderId, String stageStatus) {

        return dao.checkSubmit(orderId, stageStatus);
    }

    public DelaySheet findDelayDetails(DelaySheet delaySheet) {

        return dao.findDelayDetails(delaySheet);
    }

    public boolean pass(DelayBill delayBill) {
        try {

            DelayBill delayBillDetail = delayBillDao.findDelayBillDetailById(delayBill);

            List<BizNodePlan> nodePlan = delayBillDao.findNodePlan(delayBillDetail);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (BizNodePlan plan : nodePlan) {
                plan.setDelayDays(delayBillDetail.getDelayDays());
                Date date = DateUtils.addDate(plan.getPlanCheckTime(), Integer.parseInt(delayBillDetail.getDelayDays()));
                plan.setPlanDoneDate(date);


                delayBillDao.updataCheckTime(plan);
            }

            dao.pass(delayBill);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void refuse(DelayBill delayBill) {

        dao.refuse(delayBill);
    }

    public String checkSubmitOver(String orderId) {

        return dao.checkSubmitOver(orderId);
    }


    public List<Dict> findTemplateNodePlan(String storeId, String projectMode) {

        return dao.findTemplateNodePlan(storeId, projectMode);
    }


}
