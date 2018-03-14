
package cn.damei.service.modules;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.dao.modules.dao;
import cn.damei.entity.modules.orderVo;


@Service
@Transactional(readOnly = true)
public class service extends CrudService<dao, orderVo> {
	

	public List<orderVo> findList(orderVo order) {
		return super.findList(order);
	}
	public Page<orderVo> findPage(Page<orderVo> page, orderVo order) {
		return super.findPage(page, order);
	}

	public List<OrderTaskpackage> getPackageByOrderId(Integer orderId){
		
		return dao.getPackageByOrderId(orderId);
	}
	

	@Transactional(readOnly = false)
	public  void updatePackTime(OrderTaskpackage   pack){
		
		dao.updatePackTime(pack);
	}

	@Transactional(readOnly = false)
	public  void updatePackTime1(OrderTaskpackage   pack){
		
		dao.updatePackTime1(pack);
	}
	


	public List<OrderTaskpackage>sendFixedTimeMessageToManagerForPackPlanTime(String planStartdate){
		
		
		return dao.sendFixedTimeMessageToManagerForPackPlanTime(planStartdate);
	}
	
}