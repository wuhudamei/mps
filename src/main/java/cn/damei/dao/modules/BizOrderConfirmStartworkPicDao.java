
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderConfirmStartworkPic;


@MyBatisDao
public interface BizOrderConfirmStartworkPicDao extends CrudDao2<BizOrderConfirmStartworkPic>{

	List<BizOrderConfirmStartworkPic> getByConfirmStartWorkID(Integer startWorkID);

}