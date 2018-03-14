package cn.damei.common.ProjectIssueUtil;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/7/3.
 */

@MyBatisDao
public interface ProjectUtilDao {


    /**
     * 根据app登录人 查询对应的处理问题
     *
     * @param employeeId
     * @return
     */
    List<Map<String, Object>> findProjectProblemByDealPersonId(Integer employeeId);

    /**
     * 根据订单id  处理人id 和处理人type 查询问题
     * @param map
     * @return
     */
    List<Map<String,Object>> findProblemByMap(Map<String,Object> map);


    /**
     * 保存handle log
     * <p>
     * 参数key:dealId dealPersonType  dealPersonId  dealStatus  dealDateTime  dealDescribe
     */
    void saveHandleLog(Map<String, Object> map);


    /**
     * 通用更新
     * 参数:  handleId ,status
     *
     *
     */
   void updateHandleStatusDataById(Map<String, String> map);

    Integer findRelatedIdByhandleId(Integer handleId);


    /**
     * 通用更新
     * 参数  relatedId ,status
     *
     * @return 返回关联id
     */
    void updateProblemItemStatusDataById(Map<String, String> map);


    /**
     * 通用插入pic
     *
     * @param map 参数:  #{businessType},
     *            #{businessIdInt},
     *            #{picUrl},
     *            #{picDateTime}
     */
    void saveProjectIssuePic(List<Map<String, Object>> map);


    /**
     * 投诉通用查看图片
     * @param map
     * @return
     */
    List<String> findPicListByRelatedIdAndType(Map<String,String> map);


    /**
     * 查询一个投诉单下的问题是否都已解决  (为后面更新状态做准备)
     * @param dealId
     * @return
     */
    Map<String,Long> checkIsComplaintAllOver(Integer dealId);


    /**
     * 更新order_Complaint中的状态  处理中:20  已处理:30
     * @param complaintMap
     */
    void updateOrderComplaintById(Map<String,String> complaintMap);


    Integer updateOrderComplaintOver(Integer handleId);

	int selectCountNoDealByWorkOrderCode(String workOrderCode);
}
