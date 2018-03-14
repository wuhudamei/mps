package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.AppManagerOrderDao;
import cn.damei.entity.mobile.Manager.AppManagerOrder;


@Service
@Transactional(readOnly = true)
public class AppManagerOrderService extends CrudService2<AppManagerOrderDao, AppManagerOrder>{

	@Autowired
	private AppManagerOrderDao appManagerOrderDao;


	@Transactional(readOnly = true)
	public List<AppManagerOrder> getByItemManagerId(Integer managerId) {
		return appManagerOrderDao.getByItemManagerId(managerId);
	}


	@Transactional(readOnly = true)
	public AppManagerOrder getById(Integer id) {
		return appManagerOrderDao.getById(id);
	}


	@Transactional(readOnly = false)
	public String updateByOrderStatusNumber(String orderstatus130Value, String orderstatus130ValueRemark,
			Integer id) {
		return appManagerOrderDao.updateByOrderStatusNumber(orderstatus130Value,orderstatus130ValueRemark,id) ? "0" : "3";
	}
}
