package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.MaterialManagement;


@MyBatisDao
public interface SiteDesignProblemDao {


	List<MaterialManagement> findOrderList(MaterialManagement materialManagement);


	List<Map<String, String>> findProblemLogList(Map<String, Object> map);

}
