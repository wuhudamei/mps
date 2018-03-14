/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ComplaintForOtherDepartMentDeal;

import java.util.List;
import java.util.Map;

/**
 * 其他部门投诉DAO接口
 *
 * @author mh
 * @version 2017-07-24
 */
@MyBatisDao
public interface ComplaintForOtherDepartMentDealDao extends CrudDao<ComplaintForOtherDepartMentDeal> {


    void updatePreComplaintStatus(Map<String, String> paraMap);


    Map<String, Object> findOrderInfoByPreComplaintId(String id);


    List<Map<String, String>> findComplaintTypeByStoreId(String storeId);

    List<Map<String, String>> findComplaintItemByTypeId(String complaintTypeId);


    /**
     * 保存投诉单
     *
     * @param map
     */
    void saveComplaintInfo(Map<String, Object> map);

    /**
     * 保存投诉问题
     *
     * @param map
     */
    void saveComplaintProblem(Map<String, Object> map);

    /**
     * 保存问题和问题项映射
     *
     * @param map
     */
    void saveProblemAndItemMapping(Map<String, Object> map);

    /**
     * 保存拆分问题处理数据
     */
    void saveProblemHandleInfo(Map<String, Object> map);


    /**
     * 根据type查询数据
     * @param typeId
     * @return
     */
    Map<String,Integer> selectInfoByTypeId(String typeId);


    /**
     * 查询任务包数据
     */

    Map<String,String> findPackInfoByTemplateIdAndOrderId(String orderId,String templateId);



    List<String> findWorkerInfo(String orderId,String templateId);


    /**
     * 预投诉图片
     */
    List<Map<String,String>> selectPicByPreId(String preComplaintId);


    /**
     * 删除图片
     */

    void deletePrePic(String picId);
}