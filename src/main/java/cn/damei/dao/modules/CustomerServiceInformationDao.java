package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;


@MyBatisDao
public interface CustomerServiceInformationDao {
	void updateOne(Map<String,Object> map);
	
}