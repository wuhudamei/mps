package cn.damei.service.modules;

import org.springframework.stereotype.Service;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.ToDoItemTypeDao;
import cn.damei.entity.modules.ToDoItemTypeEntity;


@Service
public class ToDoItemTypeService  extends CrudService<ToDoItemTypeDao, ToDoItemTypeEntity>{
	
}