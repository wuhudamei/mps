/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderMaterialsStandardQuery;

/**
 * 标化辅料（筒灯灯带）查询 DAO接口
 * @author lft
 * @version 2017-05-16
 */
@MyBatisDao
public interface BizOrderMaterialsStandardQueryDao extends CrudDao<BizOrderMaterialsStandardQuery> {
	
	//根据条件查询
	List<BizOrderMaterialsStandardQuery> getBizOrderMaterialsStandardQueryList(BizOrderMaterialsStandardQuery bizOrderMaterialsStandardQuery);
	//查询 一个 根据 orderId
	BizOrderMaterialsStandardQuery getBizOrderMaterialsStandardQueryListByOrderId(@Param("orderId")String orderId,@Param("materialsLargeType")String materialsLargeType);
}