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

    /**
     * 查询延期订单
     *
     * @param id
     * @return
     */
    public List<DelaySheet> findDelayOrder(Integer id) {
        // TODO Auto-generated method stub
        return dao.findDelayOrder(id);
    }

    /**
     * 查询延期单类型
     *
     * @param i
     * @return
     */
    public List<Dict> findDelayCategory(int i) {
        // TODO Auto-generated method stub
        return dao.findDelayCategory(i);
    }

    /**
     * 查询而二级类目
     *
     * @param id
     * @param i
     * @return
     */
    public List<Dict> findDelayCategorytow(String id, int i) {
        // TODO Auto-generated method stub
        return dao.findDelayCategorytow(id, i);
    }

    public List<Dict> findDelayCategoryStatus() {
        return dao.findDelayCategoryStatus();
    }

    /**
     * @param
     * @return
     * @Description: 查询订单的进度节点
     * @Author zhangkangjian
     * @Date 2017/11/14 10:22
     */
    public List<Dict> findOrderNodePlan(String orderId) {
        List<Dict> list = new ArrayList<>();
        Map map = new HashMap();
        map.put("orderId",orderId);
        map.put("status","");
        //判断订单是否申请过延期单
        boolean boo = dao.isApplyDelay(map);
//        判断该订单的延期单是否有通过审核的
        map.put("status","20");
        boolean bool = dao.isApplyDelay(map);
        //申请过，查询改延期单节点以后的节点
        if (boo) {
           //
            if(bool){
                //查询改订单作废的延期单
                List<Dict> scraplist = dao.findScrapDelayBill(orderId);
                map.put("list",scraplist);
                if(scraplist.size() > 0){
                    //查询作废的延期单，审核通过的
                    List<Dict> dictList = dao.findNodePlanFinsh(map);
                        if(dictList.size() > 0){
                            //把审核通过的延期单，从作废延期单里上除掉
                            for (int i = 0; i < scraplist.size(); i++) {
                                for (Dict dict: dictList) {
                                    if(scraplist.get(i).getValue().equals(dict.getValue())){
                                        scraplist.remove(i);
                                    }
                                }
                            }

                        }
                    //再把作废的延期单拼接到列表
                    list.addAll(scraplist);
                }
                list.addAll(dao.findOrderNodePlan(orderId));
            }else{
                //有申请过延期单，但是没有审核通过的，查询所有的节点信息
                list.addAll(dao.findOrderAllNode(orderId));
            }
        } else {
            //没有申请过，查询订单的所有节点
            list.addAll(dao.findOrderAllNode(orderId));
        }
        //list 去除重复
        /*Map<String,Dict> mapList = new HashMap();*/
        Map<Integer, Dict> mapsort = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            Dict dict = list.get(i);
            mapsort.put(dict.getSort(),dict);
        }
        List<Dict> listDict = new ArrayList<>();
//      获取到map的key 进行排序
        Set<Integer> strings = mapsort.keySet();
        List<Integer> listSort = new ArrayList<Integer>(strings);
        Collections.sort(listSort);
        for (int i = 0; i < listSort.size(); i++) {
            listDict.add(mapsort.get(listSort.get(i)));
        }
        return listDict;
    }

    public DelaySheet checkSubmit(String orderId, String stageStatus) {
        // TODO Auto-generated method stub
        return dao.checkSubmit(orderId, stageStatus);
    }

    public DelaySheet findDelayDetails(DelaySheet delaySheet) {
        // TODO Auto-generated method stub
        return dao.findDelayDetails(delaySheet);
    }
    /**
    * @Description: 更新延期单状态，进度节点时间，向后推移
    * @Author zhangkangjian
    * @param
    * @return
    * @Date 2017/11/16 9:54
    */
    public boolean pass(DelayBill delayBill) {
        try {
            //	   根据延期点单ID查询延期单详细信息
            DelayBill delayBillDetail = delayBillDao.findDelayBillDetailById(delayBill);
            //     根据延期阶段查询，该节点以后的节点，包含此节点
            List<BizNodePlan> nodePlan = delayBillDao.findNodePlan(delayBillDetail);
            //    遍历节点，获取计划延期时间，加上延期天数
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (BizNodePlan plan : nodePlan) {
                plan.setDelayDays(delayBillDetail.getDelayDays());
                Date date = DateUtils.addDate(plan.getPlanCheckTime(), Integer.parseInt(delayBillDetail.getDelayDays()));
                plan.setPlanDoneDate(date);
                //    更新节点信息

                delayBillDao.updataCheckTime(plan);
            }
            // 更新延期单状态
            dao.pass(delayBill);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void refuse(DelayBill delayBill) {
        // TODO Auto-generated method stub
        dao.refuse(delayBill);
    }

    public String checkSubmitOver(String orderId) {
        // TODO Auto-generated method stub
        return dao.checkSubmitOver(orderId);
    }

    /**
     * @param
     * @return
     * @Description: 根据门店和工程模式查询进度节点
     * @Author zhangkangjian
     * @Date 2017/11/14 14:53
     */
    public List<Dict> findTemplateNodePlan(String storeId, String projectMode) {

        return dao.findTemplateNodePlan(storeId, projectMode);
    }


}
