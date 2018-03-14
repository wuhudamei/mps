package cn.damei.service.modules;

import org.springframework.stereotype.Service;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.ToDoItemTypeDao;
import cn.damei.entity.modules.ToDoItemTypeEntity;

/**
 * 待办事项分类service
 * @author lzm
 * @version 2017-7-14
 */
@Service
public class ToDoItemTypeService  extends CrudService<ToDoItemTypeDao, ToDoItemTypeEntity>{
	
}