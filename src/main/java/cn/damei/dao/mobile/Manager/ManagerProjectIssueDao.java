package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ManagerProjectIssueBean;


@MyBatisDao
public interface ManagerProjectIssueDao {


	List<Map<String, Object>> findWorkerInfoByRelatedId(Integer relatedId);

	Map<String, Object> findProblemByHandleId(Integer handleId);


	Integer findIssueIsDoneByHandleId(Integer handleId);


	void saveHandleDescribeByHandleIdAndDealDescribe(Map<String, String> map);

	void updateStatus(ManagerProjectIssueBean bizCusServiceProblem);

	void saveCustomerComForfeit(Map<String, Object> mapForfeit);
}
