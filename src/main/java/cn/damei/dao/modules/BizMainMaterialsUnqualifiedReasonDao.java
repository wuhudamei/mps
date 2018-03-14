
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMainMaterialsUnqualifiedReason;
import cn.damei.entity.modules.BizProjectInstallItem;

import java.util.List;


@MyBatisDao
public interface BizMainMaterialsUnqualifiedReasonDao extends CrudDao2<BizMainMaterialsUnqualifiedReason> {


    List<BizProjectInstallItem> queryInstallItemList(BizProjectInstallItem bizProjectInstallItem);


    boolean updateRreasonEnable(BizMainMaterialsUnqualifiedReason bizMainMaterialsUnqualifiedReason);


    boolean updateRreasonDelete(Integer id);


    List<BizMainMaterialsUnqualifiedReason> queryUnqualifiedReasonList(Integer id);

}