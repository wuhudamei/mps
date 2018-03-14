package cn.damei.dao.mobile.Manager;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.EnginInstall;

/**
 * 工程安装
 * wyb
 */
@MyBatisDao
public interface EnginInstallDao extends CrudDao2<EnginInstall> {

	/**
	 * 根据订单id获取订单详情
	 * @param id
	 * @return
	 */
	EnginInstall queryOrderDetails(Integer id);

}
