package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.AppManagerOrderDao;
import cn.damei.entity.mobile.Manager.AppManagerOrder;

/**
 * 项目经理端
 * 现场交底
 * @author llp
 * 2016/10/17
 */
@Service
@Transactional(readOnly = true)
public class AppManagerOrderService extends CrudService2<AppManagerOrderDao, AppManagerOrder>{

	@Autowired
	private AppManagerOrderDao appManagerOrderDao;

	/**
	 * 根据当前登陆项目经理的ID获取订单中所有的订单
	 * @param id
	 * @return list
	 */
	@Transactional(readOnly = true)
	public List<AppManagerOrder> getByItemManagerId(Integer managerId) {
		return appManagerOrderDao.getByItemManagerId(managerId);
	}

	/**
	 * 根据主键ID获取完整信息
	 * @param orderId
	 * @return object
	 */
	@Transactional(readOnly = true)
	public AppManagerOrder getById(Integer id) {
		return appManagerOrderDao.getById(id);
	}

	/**
	 * 更改订单状态为130-已现场交底
	 * @param orderstatus130Value
	 * @param orderstatus130ValueRemark
	 * @param valueOf
	 * @return
	 */
	@Transactional(readOnly = false)
	public String updateByOrderStatusNumber(String orderstatus130Value, String orderstatus130ValueRemark,
			Integer id) {
		return appManagerOrderDao.updateByOrderStatusNumber(orderstatus130Value,orderstatus130ValueRemark,id) ? "0" : "3";
	}
}
