
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ComplaintForOtherDepartMentDeal;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface ComplaintForOtherDepartMentDealDao extends CrudDao<ComplaintForOtherDepartMentDeal> {


    void updatePreComplaintStatus(Map<String, String> paraMap);


    Map<String, Object> findOrderInfoByPreComplaintId(String id);


    List<Map<String, String>> findComplaintTypeByStoreId(String storeId);

    List<Map<String, String>> findComplaintItemByTypeId(String complaintTypeId);



    void saveComplaintInfo(Map<String, Object> map);


    void saveComplaintProblem(Map<String, Object> map);


    void saveProblemAndItemMapping(Map<String, Object> map);


    void saveProblemHandleInfo(Map<String, Object> map);



    Map<String,Integer> selectInfoByTypeId(String typeId);




    Map<String,String> findPackInfoByTemplateIdAndOrderId(String orderId,String templateId);



    List<String> findWorkerInfo(String orderId,String templateId);



    List<Map<String,String>> selectPicByPreId(String preComplaintId);




    void deletePrePic(String picId);
}