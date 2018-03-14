
package cn.damei.dao.modules;


import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ScorebizCustomerComplaint;


@MyBatisDao
public interface ScoreCustomerComplaintDao extends CrudDao<ScorebizCustomerComplaint> {
	

	public List<Map<String,String>> getComplainType();
}