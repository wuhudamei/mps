/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderMaterialsStandard;

/**
 * 标化辅料（筒灯灯带）订单DAO接口
 * @author lft
 * @version 2017-05-12
 */
@MyBatisDao
public interface BizOrderMaterialsStandardDao extends CrudDao2<BizOrderMaterialsStandard> {
	//通过订单id 和门店 id 查询辅料列表
	public List<BizOrderMaterialsStandard> getList(@Param("storeId")String storeId,@Param("orderId")String orderId,@Param("materialsLargeType")String materialsLargeType);
	//通过订单id 和辅料id 查询辅料
	public BizOrderMaterialsStandard getBizOrderMaterialsStandard(@Param("materialsStandardId")String materialsStandardId,@Param("orderId")String orderId);
	//通過订单id 获取没被查看的 bill id
	String getMaterialIsView(String orderId);
	//通過 billId查看是否查看過
	String selectBillView(String billId);
	
	//通过billid 修改bill的查看与否
	void updateBill(@Param("isViewed")String isViewed,
			@Param("viewDatetime")Date viewDatetime,
			@Param("id")String id
			);
	//根据orderid 和材料ID 修改数量 和金额 和 待领取数
	void updateOrderMaterialsByOrderIdAndStandId(BizOrderMaterialsStandard bizOrderMaterialsStandard);
	//根据orderId查询  列表
	List<BizOrderMaterialsStandard> getMaterialsByOrderId(@Param("orderId")String orderId,@Param("materialsLargeType")String materialsLargeType);
}