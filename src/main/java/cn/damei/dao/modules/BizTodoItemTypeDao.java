/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizTodoItemType;

import java.util.List;
import java.util.Map;

/**
 * 待办配置DAO接口
 * @author mh
 * @version 2017-07-14
 */
@MyBatisDao
public interface BizTodoItemTypeDao extends CrudDao<BizTodoItemType> {


    List<Map<String,String>> findIdByBusinessType(String relatedBusinessType);

    List<Map<String,String>> findRelatedBusinessTypeByStoreIdProjectMode(String storeId,String projectMode);
	
}