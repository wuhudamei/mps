package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ToDoItemTypeEntity;


@MyBatisDao
public interface ToDoItemTypeDao extends CrudDao<ToDoItemTypeEntity>{

}
