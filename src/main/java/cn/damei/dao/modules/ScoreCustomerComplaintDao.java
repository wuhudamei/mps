/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ScorebizCustomerComplaint;

/**
 * 顾客投诉DAO接口
 * @author liwc
 * @version 2017-04-12
 */
@MyBatisDao
public interface ScoreCustomerComplaintDao extends CrudDao<ScorebizCustomerComplaint> {
	
	/**
	 * 获取投诉类型数据
	 * @return
	 */
	public List<Map<String,String>> getComplainType();
}