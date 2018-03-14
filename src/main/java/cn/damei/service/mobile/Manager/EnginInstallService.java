package cn.damei.service.mobile.Manager;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.EnginInstallDao;
import cn.damei.entity.mobile.Manager.EnginInstall;

/**
 * 工程安装
 * 
 * @author wyb
 */
@Service
@Transactional(readOnly = true)
public class EnginInstallService extends CrudService2<EnginInstallDao, EnginInstall> {

	/**
	 * 根据订单id获取订单详情
	 * 
	 * @param  orderId
	 * @return
	 */
	public EnginInstall queryOrderDetails(Integer orderId) {
		return dao.queryOrderDetails(orderId);
	}


}
