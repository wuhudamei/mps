/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcCheckItem;
import cn.damei.entity.modules.BizQcCheckKind;

/**
 * 检查项DAO接口
 * @author wyb
 * @version 2016-10-27
 */
@MyBatisDao
public interface BizQcCheckItemDao extends CrudDao2<BizQcCheckItem> {

	List<BizQcCheckKind> findCheckKind(BizQcCheckKind kind);
	
	//通过分类id查询分类
	String findName(int a);

	List<Map<String,String>> taskPackageTemplat(Integer id);

    int updatecheckItem(BizQcCheckItem bizQcCheckItem);
}