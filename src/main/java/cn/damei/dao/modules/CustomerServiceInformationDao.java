package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;

/**
 * 远程调用 客服信息同步
 * @author lft
 *	 2017-5-9
 */
@MyBatisDao
public interface CustomerServiceInformationDao {
	void updateOne(Map<String,Object> map);
	
}