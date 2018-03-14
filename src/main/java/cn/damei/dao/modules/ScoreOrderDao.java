/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ScorOrderEmployee;
import cn.damei.entity.modules.ScoreOrder;
import cn.damei.entity.modules.ScoreOrderComplain;
import cn.damei.entity.modules.ScoreOrderQuery;

/**
 * 评分订单DAO接口
 * @author liwc
 * @version 2017-04-12
 */
@MyBatisDao
public interface ScoreOrderDao extends CrudDao<ScoreOrder> {
	/**
	 * 根据顾客电话查询订单
	 * @param customerPhone
	 * @return
	 */
	List<Map<String,String>> selectOrderByCustomer(@Param("customerPhone") String customerPhone);
	
	
	int getOrderStatusById(Integer orderId);
	
	/**
	 * 根据订单id获取未评分类型数据
	 * @param Map<String,Object> map
	 * @return
	 */
	List<Map<String,String>> orderNoScoreType(Map<String,Object> map);
	
	/**
	 * 根据订单id获取已评分类型历史数据
	 * @param orderId
	 * @return
	 */
	List<Map<String,String>> orderHistoryScore(Integer orderId);
	
	/**
	 * 根据订单id获取项目设计人员姓名，电话
	 * @param orderId
	 * @return
	 */
	List<Map<String,Object>> selectDesignerMemberFromOrder(Integer orderId);
	
	/**
	 * 根据订单id获取项目服务人员姓名，电话
	 * @param orderId
	 * @return
	 */
	List<Map<String,Object>> selectServiceMemberFromOrder(Integer orderId);
	
	/**
	 * 根据订单id以及评分类型获取订单对应的施工人员以及项目经理
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectTeamMemberByMap(@Param("orderId")Integer orderId,@Param("scoreType")String scoreType);
	/**
	 * 获取评分类型
	 * @return
	 */
	List<Map<String,Object>> getScoreContent();
	/**
	 * 获取评分类型根据公司
	 * @return
	 */
	List<Map<String,Object>> getScoreContentByStoreId(String storeId);
	/**
	 * 获取kesu类型
	 * @return
	 */
	List<Map<String,Object>> getScoreComplain();
	
	
	/**
	 *根据条件 查询所有订单评分统计
	 * @return
	 */
	List<ScoreOrderQuery> selectOrderScoreQuery(ScoreOrderQuery scoreOrderQuery);
	/**
	 *查询 订单客诉情况
	 * @return
	 */
	List<ScoreOrderComplain> selectScoreOrderComplain();
	/**
	 * 根据条件查询 订单客诉情况
	 * @param scoreOrderComplain
	 * @return
	 */
	List<ScoreOrderComplain> selectScoreOrderComplainQuery(ScoreOrderComplain scoreOrderComplain);
	/**
	 * 根据条件查询员工评价
	 * @return
	 */
	List<ScorOrderEmployee> selectScorOrderEmployeeQuery(ScorOrderEmployee scorOrderEmployee);
	/**
	 * 查询  岗位列表
	 * @return
	 */
	List<Map<String,Object>> selectPositionType();
	
	/**
	 * 根据公司查询  岗位列表
	 * @return
	 */
	List<Map<String,Object>> selectPositionTypeByStoreId(String storeId);
	
	/**
	 * 根据订单id获取全部干系人
	 * @param orderId
	 * @return
	 */
	List<Map<String,Object>> selectAllTeamMember(Integer orderId);
}