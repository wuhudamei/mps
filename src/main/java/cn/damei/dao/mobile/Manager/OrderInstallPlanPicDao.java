package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderInstallPlanPic;

/**
 * 工程安装
 * 
 * @author wyb
 */
@MyBatisDao
public interface OrderInstallPlanPicDao extends CrudDao2<OrderInstallPlanPic> {

	void deletePic(Integer valueOf);

	void saveInstallPlanPicBatch(List<OrderInstallPlanPic> pList);




}
