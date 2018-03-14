
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ArrangeCheckStatisticsQ;


@MyBatisDao
public interface ArrangeCheckStatisticsQDao extends CrudDao<ArrangeCheckStatisticsQ>
{

	List<ArrangeCheckStatisticsQ> findPage(ArrangeCheckStatisticsQ arrangeCheckStatisticsQ);

	List<ArrangeCheckStatisticsQ> findPage(@Param("qcBillType") String qcBillType, @Param("qcBillType") String acceptCheckDatetimeString);

}
