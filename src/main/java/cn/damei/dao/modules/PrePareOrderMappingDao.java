package cn.damei.dao.modules;

import java.util.List;

import cn.damei.Quartz.OrderJsonMapping;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Order;

@MyBatisDao
public interface PrePareOrderMappingDao {

	
	/**
	 * 直接查询所有的映射字段
	 * @return value
	 */
	public List<OrderJsonMapping> getDateTypeByDateField();
	
	/**
	 * id+json字符串
	 * @return
	 */
	public List<OrderJsonMapping>  getPreapareOrderJsonAuto();
	public void updateSynDataStatus(Integer id , String synStatus);
	
	public void  insert(Order order);
}
