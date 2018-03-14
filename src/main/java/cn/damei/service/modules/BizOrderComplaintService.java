
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.damei.common.utils.DateUtils;
import cn.damei.entity.modules.BizOrderComplaintProblemDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.dao.modules.BizComplaintProblemItemDao;
import cn.damei.dao.modules.BizComplaintProblemTypeDao;
import cn.damei.entity.modules.BizComplaintProblemType;
import cn.damei.dao.modules.BizOrderComplaintDao;
import cn.damei.dao.modules.BizOrderComplaintProblemDao;
import cn.damei.dao.modules.BizOrderComplaintProblemItemDao;
import cn.damei.entity.modules.BizOrderComplaint;
import cn.damei.entity.modules.ComplaintLogEntity;


@Service
@Transactional(readOnly = true)
public class BizOrderComplaintService extends CrudService<BizOrderComplaintDao, BizOrderComplaint> {

    @Autowired
    private BizOrderComplaintDao bizOrderComplaintDao;
    @Autowired
    private BizComplaintProblemTypeDao bizComplaintProblemTypeDao;

    @Autowired
    private BizOrderComplaintProblemDao bizOrderComplaintProblemDao;

    @Autowired
    private BizOrderComplaintProblemItemDao bizOrderComplaintProblemItemDao;
    @Autowired
    private BizComplaintProblemItemDao bizComplaintProblemItemDao;

    public BizOrderComplaint get(String id) {
        return super.get(id);
    }

    public List<BizOrderComplaint> findList(BizOrderComplaint bizOrderComplaint) {
        return super.findList(bizOrderComplaint);
    }

    public Page<BizOrderComplaint> findPage(Page<BizOrderComplaint> page, BizOrderComplaint bizOrderComplaint) {
        return super.findPage(page, bizOrderComplaint);
    }

    @Transactional(readOnly = false)
    public void save(BizOrderComplaint bizOrderComplaint) {
        super.save(bizOrderComplaint);
    }

    @Transactional(readOnly = false)
    public void delete(BizOrderComplaint bizOrderComplaint) {
        super.delete(bizOrderComplaint);
    }

    @Transactional(readOnly = false)
    public Integer Insert(BizOrderComplaint bizOrderComplaint2) {
        return bizOrderComplaintDao.insert(bizOrderComplaint2);

    }

    public Page<BizOrderComplaint> findPageList(Page<BizOrderComplaint> page, BizOrderComplaint bizOrderComplaint) {
        bizOrderComplaint.setPage(page);
        List<BizOrderComplaint> findAllList = bizOrderComplaintDao.findPageList(bizOrderComplaint);
        page.setList(findAllList);
        return page;
    }

    public Page<BizOrderComplaint> findPageListall(Page<BizOrderComplaint> page, BizOrderComplaint bizOrderComplaint) {
        bizOrderComplaint.setPage(page);
        List<BizOrderComplaint> findAllList = bizOrderComplaintDao.findPageListall(bizOrderComplaint);
        page.setList(findAllList);
        return page;
    }


    public Map<String, Object> formDetails(BizOrderComplaint bizOrderComplaint) {



        List<BizOrderComplaint> OrderComplaintList = bizOrderComplaintDao.findPageList(bizOrderComplaint);

        List<ComplaintLogEntity> entities = bizOrderComplaintDao.findLogByComplaintIdz(bizOrderComplaint.getComplaintId());
        bizOrderComplaint = OrderComplaintList.get(0);

        int size = entities.size();
        if (size > 0) {

            for (int i = 0; i < size; i++) {
                List<BizOrderComplaintProblemDeal> deals = entities.get(i).getDealList();
                int dealListSize = entities.get(i).getDealList().size();

                if (dealListSize > 0) {

                    for (int v = 0; v < dealListSize; v++) {
                        Double executeTime = deals.get(v).getMsgsndPeriod();
                        Date createDate = entities.get(i).getInfoDate();
                        Date doneDate = DateUtils.addDate(createDate, executeTime.intValue());



                        Date statesDateTime = deals.get(v).getDealStatusDatetime();



                        if(null!=statesDateTime){

                            if (statesDateTime.getTime() > doneDate.getTime()) {

                                deals.get(v).setIsdatefla("0");
                                Double isOverDays = Math.abs(DateUtils.getDistanceOfTwoDate(statesDateTime, doneDate)) > 0 ? Math.abs(DateUtils.getDistanceOfTwoDate(statesDateTime, doneDate))+1 : 1;
                                deals.get(v).setOverDays(isOverDays.intValue());


                            } else {
                                deals.get(v).setIsdatefla("1");

                            }
                        }



                    }

                }

            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("entity", bizOrderComplaint);
        map.put("list", entities);
        return map;
    }

    public List<BizComplaintProblemType> getSrmsModelListByBrandId(Integer brandId) {


        BizComplaintProblemType bizComplaintProblemType = new BizComplaintProblemType();
        bizComplaintProblemType.setStoreId(brandId);
        return bizComplaintProblemTypeDao.queryComTypeAll(bizComplaintProblemType);
    }

    @Autowired
    private PhoneMessageDao messageDao;

    @Transactional(readOnly = false)
    public void saveMessageContent(Integer orderComplaintId, String customerAddress, String customerName, String itemIds, Integer... employeeId) {

        String itemNames = "";

        if (null != itemIds) {
            List<Integer> itemIdList = new ArrayList<>();
            String[] ids = itemIds.split(",");
            Integer itemLength = ids.length;

            for (int index = 0; index < itemLength; index++) {

                itemIdList.add(Integer.valueOf(ids[index]));

            }

            List<String> itemNameList = bizOrderComplaintDao.findItemNameByIds(itemIdList);

            if (itemIdList.size() > 0) {
                StringBuilder sb = new StringBuilder();
                Iterator<String> iterator = itemNameList.iterator();

                while (iterator.hasNext()) {

                    String itemName = iterator.next();
                    sb.append(itemName + "  ");

                }

                itemNames = sb.toString();

            }

        }

        Date date = new Date();

        if (null != employeeId) {

            Integer length = employeeId.length;

            if (length > 0) {
                List<Integer> employeeIds = new ArrayList<>();

                for (int emp = 0; emp < length; emp++) {

                    employeeIds.add(employeeId[emp]);
                }

                List<Map<String, Object>> nameAndPhoneList = bizOrderComplaintDao.findEmployeeNameAndPhoneByIds(employeeIds);

                if (nameAndPhoneList.size() > 0) {

                    Iterator<Map<String, Object>> iterator = nameAndPhoneList.iterator();
                    PhoneMessageEntity entity = new PhoneMessageEntity();
                    entity.setStatus("0");
                    entity.setRelatedBusinessType("747200");
                    entity.setMessageGenerateTime(date);
                    while (iterator.hasNext()) {

                        Map<String, Object> nextEmployeeId = iterator.next();

                        String messageContent = "工程投诉，" + customerName + "+" + customerAddress + "的客户存在  " + itemNames + "  的工程问题，请您及时到大美装饰管理平台工程APP的工程投诉中查看投诉问题内容，并尽快处理";

                        entity.setMessageContent(messageContent);

                        entity.setReceiveEmployeeId((Integer) nextEmployeeId.get("empId"));
                        entity.setReceivePhone((String) nextEmployeeId.get("phone"));

                        entity.setRelatedBusinessId(orderComplaintId);
                        this.messageDao.saveMessageContent(entity);

                    }
                }
            }
        }
    }

    public List<String> findComplaintLogPicByMap(Integer businessIdInt, String businessType) {
        Map<String, Object> map = new HashMap<>();

        map.put("businessIdInt", businessIdInt);
        map.put("businessType", businessType);

        return dao.findComplaintLogPicByMap(map);
    }

    public Map<String, Object> formDetailsz(BizOrderComplaint bizOrderComplaint) {



        List<BizOrderComplaint> OrderComplaintList = bizOrderComplaintDao.findPageList(bizOrderComplaint);

        List<ComplaintLogEntity> entities = bizOrderComplaintDao.findLogByComplaintIdz(bizOrderComplaint.getComplaintId());
        bizOrderComplaint = OrderComplaintList.get(0);

        Map<String, Object> map = new HashMap<>();
        map.put("entity", bizOrderComplaint);
        map.put("list", entities);
        return map;
    }



    public 	List<BizOrderComplaint> findComplaintListForMap(BizOrderComplaint orderComplaint){


        return dao.findComplaintListForMap(orderComplaint);
    }

}