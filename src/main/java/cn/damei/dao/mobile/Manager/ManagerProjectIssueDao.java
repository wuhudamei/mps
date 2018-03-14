package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ManagerProjectIssueBean;

/**
 * Created by joseph on 2017/7/4.
 */
@MyBatisDao
public interface ManagerProjectIssueDao {

	// List<Map<String,Object>> findProblemByOrderId(Map<String,Integer> map);
	List<Map<String, Object>> findWorkerInfoByRelatedId(Integer relatedId);

	Map<String, Object> findProblemByHandleId(Integer handleId);

	/**
	 * 处理数据重复提交
	 * 
	 * @param handleId
	 * @return
	 */
	Integer findIssueIsDoneByHandleId(Integer handleId);

	/*
	 * 更新remarks
	 */
	void saveHandleDescribeByHandleIdAndDealDescribe(Map<String, String> map);

	void updateStatus(ManagerProjectIssueBean bizCusServiceProblem);

	void saveCustomerComForfeit(Map<String, Object> mapForfeit);
}
