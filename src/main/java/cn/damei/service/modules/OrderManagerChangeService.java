
package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.OrderManagerChangeDao;
import cn.damei.entity.modules.OrderManagerChange;

@Service
@Transactional(readOnly = true)
public class OrderManagerChangeService extends CrudService<OrderManagerChangeDao, OrderManagerChange> {

	
}