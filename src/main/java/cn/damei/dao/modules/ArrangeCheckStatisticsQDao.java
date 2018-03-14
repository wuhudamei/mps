/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ArrangeCheckStatisticsQ;

/**
 * 约检节点验收统计DAO接口
 * 
 * @author 张同维
 * @version 2017-06-22
 */
@MyBatisDao
public interface ArrangeCheckStatisticsQDao extends CrudDao<ArrangeCheckStatisticsQ>
{

	List<ArrangeCheckStatisticsQ> findPage(ArrangeCheckStatisticsQ arrangeCheckStatisticsQ);

	List<ArrangeCheckStatisticsQ> findPage(@Param("qcBillType") String qcBillType, @Param("qcBillType") String acceptCheckDatetimeString);

}
