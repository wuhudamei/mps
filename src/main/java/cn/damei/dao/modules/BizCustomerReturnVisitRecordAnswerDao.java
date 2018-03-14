/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitRecordAnswer;

/**
 * 客户回访答案DAO接口
 * @author lft
 * @version 2017-05-26
 */
@MyBatisDao
public interface BizCustomerReturnVisitRecordAnswerDao extends CrudDao2<BizCustomerReturnVisitRecordAnswer> {
	//根据回访记录id 查询 问题和答案
	List<BizCustomerReturnVisitRecordAnswer> getListByRecordId(Integer returnVisitRecordId);
	//根据回访记录id集合 查询 问题和答案
	List<BizCustomerReturnVisitRecordAnswer> getListByRecordIds(List<Integer> list);

	void insertBatch(List<BizCustomerReturnVisitRecordAnswer> list);
}