/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsStandardNumberSquare;

/**
 * 筒灯面积计算类DAO接口
 * @author lft
 * @version 2017-05-19
 */
@MyBatisDao
public interface BizMaterialsStandardNumberSquareDao extends CrudDao2<BizMaterialsStandardNumberSquare> {
	//通过 materrialsId 获取 id
	List<String> getIdByMaterialsId(String materialsId);
}