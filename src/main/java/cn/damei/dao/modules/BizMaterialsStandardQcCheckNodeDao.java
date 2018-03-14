/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsStandardQcCheckNode;

/**
 * 标化筒灯约检节点配置DAO接口
 * @author lft
 * @version 2017-05-25
 */
@MyBatisDao
public interface BizMaterialsStandardQcCheckNodeDao extends CrudDao2<BizMaterialsStandardQcCheckNode> {
	//获取 筒灯灯带 和标化辅料 
	List<Map<String,String>>getMaterialsTypeByType(String type);
	//通过门店 和工程模式 获取 约检节点列表
	List<Map<String,String>>getCheckNodeListByStoreIdAndMode(@Param("storeId")String storeId,@Param("projectMode")String projectMode);
	//根据 门店 模式 材料大类 获取 条数
	Integer getCheckNodeByOther(@Param("storeId")String storeId,@Param("projectMode")String projectMode,@Param("materialType")String materialType);
}