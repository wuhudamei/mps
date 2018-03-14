/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.entity.modules.Dict;
import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitRecord;

/**
 * 客户回访记录DAO接口
 * @author lft
 * @version 2017-05-26
 */
@MyBatisDao
public interface BizCustomerReturnVisitRecordDao extends CrudDao2<BizCustomerReturnVisitRecord> {
	
	/**
	 * 待回访订单查询
	 * @param bizCustomerReturnVisitRecord
	 * @return
	 */
	List<BizCustomerReturnVisitRecord> findPageForChecking(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord);
	
	/**
	 * 回访管理查询
	 * @param bizCustomerReturnVisitRecord
	 * @return
	 */
	List<BizCustomerReturnVisitRecord> findPageForChecking1(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord);
	
	/**
	 * 根据订单ID和回访节点获取订单详情
	 * @param bizCustomerReturnVisitRecord
	 * @return
	 */
	Map<String,String> getOrderInfoForCheck(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord);
	
	/**
	 * 客户回访汇总查询，根据实际访问节点以及客服人员分组
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> visitRecordSummaryQuery(Map<String,String> param);
	
	/**
	 * 回访满意度汇总查询，根据部门以及满意情况分组
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> satisfactionDegreeQuery(Map<String,String> param);
	
	
	List<Map<String,String>>  getOrderHistoryVisitRecord(@Param("orderId")Integer orderId );
	
	/**
	 * 无效回访记录查询
	 * @param param
	 * @return
	 */
	List<BizCustomerReturnVisitRecord> invalidList(BizCustomerReturnVisitRecord bizCustomerReturnVisitRecord);

	/**
	 * 查询相应的工程模式
	 * @param param
	 * @return
	 */
    List<Dict> findProjectMode();

}