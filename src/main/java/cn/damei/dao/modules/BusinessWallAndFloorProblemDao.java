
package cn.damei.dao.modules;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblemVo;


@MyBatisDao
public interface BusinessWallAndFloorProblemDao extends CrudDao2<BizOrderInstallItemProblemVo> {


	BizOrderInstallItemProblemVo findDetails(Integer problemId);


	boolean updateProblem(BizOrderInstallItemProblem bizOrderInstallItemProblem);

	
}