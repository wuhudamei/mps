package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallDetail;

/**
 * 订单安装项
 * 
 * @author llp
 */
@MyBatisDao
public interface BizOrderInstallDetailDao extends CrudDao2<BizOrderInstallDetail> {

	List<BizOrderInstallDetail> findListz(BizOrderInstallDetail bizOrderInstallDetail);

}
