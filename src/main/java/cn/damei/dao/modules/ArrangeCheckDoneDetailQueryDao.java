/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ArrangeCheckDoneDetailQuery;

import java.util.List;

/**
 * 约检节点验收明细查询DAO接口
 * @author mh
 * @version 2017-06-06
 */
@MyBatisDao
public interface ArrangeCheckDoneDetailQueryDao extends CrudDao<ArrangeCheckDoneDetailQuery> {



    List<String> findPqcByBillId(String qcBillId);
}