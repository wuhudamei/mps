/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.mobile.Manager;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.entity.mobile.Manager.AppOrderCadfile;

/**
 * 订单管理DAO接口
 * @author wyb
 * @version 2016-09-08
 */
@MyBatisDao
public interface AppOrderDao extends CrudDao2<AppOrder>{
	
	//查询项目经理下的所有订单
	List<AppOrder> findOrderByitemManager(AppOrder appOrder);
	//查询项目经理下所有订单的状态
	List<String> selectState(int itemManagerId);
	//通过订单id查询订单详情
	AppOrder getOrder(Integer id);
	
	//确认开工中提交数据需修改delay_type(延期类型0代表公司原因1代表客户原因)
	boolean updateDelayType(String typeValue, String orderId);
	//查询房屋户型--字典表
	String findHouseType(AppOrder appOrder);
	
	//通过订单id查询订单图纸
	List<AppOrderCadfile> selectCadfile(Integer id);

	public AppOrder queryOrderByOrderTaskpackageId(Integer orderTaskpackageId);
	
	// 根据材料 订单 id 查询是否完工
	String getOrderStatusByMaterislType(@Param("orderId")String orderId,@Param("materislType")String materislType);
}