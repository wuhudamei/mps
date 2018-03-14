/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.DictType;

/**
 * 字典DAO接口
 * @author llp
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictTypeDao extends CrudDao<DictType> {

	List<DictType> queryListByType(String key);

}
