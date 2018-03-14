
package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallItemProblemVo;


@MyBatisDao
public interface InstallConstructBillProblemDao extends CrudDao2<BizOrderInstallItemProblemVo> {


	List<BizOrderInstallItemProblemVo> findExport(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo);


	
}