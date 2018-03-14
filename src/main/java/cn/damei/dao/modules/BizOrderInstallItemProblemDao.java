
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblemVo;
import cn.damei.entity.modules.DropModel;


@MyBatisDao
public interface BizOrderInstallItemProblemDao extends CrudDao2<BizOrderInstallItemProblem> {

	BizOrderInstallItemProblemVo getOrderInstallItemProblemVo(Integer id);


	List<BizOrderInstallItemProblemVo> queryOrderInstallItemProblemVoList(
			BizOrderInstallItemProblemVo bizOrderInstallItemProblem);

	List<DropModel> queryInstallItemProblemTypeList(Map map);

	BizOrderInstallItemProblemVo queryById(Integer id);

	Integer insert1(BizOrderInstallItemProblem bizOrderInstallItemProblem);

	List<BizOrderInstallItemProblemVo> queryOrderInstallItemProblemVoList3(
			BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo);


	void deleteProblem(Integer problemId);


	Integer findProblemCount(BizOrderInstallItemProblem bizOrderInstallItemProblem);
	
}