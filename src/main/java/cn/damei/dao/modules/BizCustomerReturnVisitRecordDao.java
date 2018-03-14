
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.entity.modules.Dict;
import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitRecord;


@MyBatisDao
public interface BizCustomerReturnVisitRecordDao extends CrudDao2<BizCustomerReturnVisitRecord> {
	

	List<BizCustomerReturnVisitRecord> findPageForChecking(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord);
	

	List<BizCustomerReturnVisitRecord> findPageForChecking1(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord);
	

	Map<String,String> getOrderInfoForCheck(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord);
	

	List<Map<String,Object>> visitRecordSummaryQuery(Map<String,String> param);
	

	List<Map<String,Object>> satisfactionDegreeQuery(Map<String,String> param);
	
	
	List<Map<String,String>>  getOrderHistoryVisitRecord(@Param("orderId")Integer orderId );
	

	List<BizCustomerReturnVisitRecord> invalidList(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord);


    List<Dict> findProjectMode();

}