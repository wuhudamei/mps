package cn.damei.dao.modules;

import java.util.List;

import cn.damei.Quartz.OrderJsonMapping;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Order;

@MyBatisDao
public interface PrePareOrderMappingDao {

	

	public List<OrderJsonMapping> getDateTypeByDateField();
	

	public List<OrderJsonMapping>  getPreapareOrderJsonAuto();
	public void updateSynDataStatus(Integer id , String synStatus);
	
	public void  insert(Order order);
}
