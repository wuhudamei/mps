/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblemVo;
import cn.damei.entity.modules.DropModel;

/**
 * 订单安装项问题DAO接口
 * @author 汪文文
 * @version 2017-02-20
 */
@MyBatisDao
public interface BizOrderInstallItemProblemDao extends CrudDao2<BizOrderInstallItemProblem> {

	BizOrderInstallItemProblemVo getOrderInstallItemProblemVo(Integer id);

	/**
	 * 工程安装问题上报申请
	 * @param bizOrderInstallItemProblem
	 * @return
	 */
	List<BizOrderInstallItemProblemVo> queryOrderInstallItemProblemVoList(
			BizOrderInstallItemProblemVo bizOrderInstallItemProblem);

	List<DropModel> queryInstallItemProblemTypeList(Map map);

	BizOrderInstallItemProblemVo queryById(Integer id);

	Integer insert1(BizOrderInstallItemProblem bizOrderInstallItemProblem);

	List<BizOrderInstallItemProblemVo> queryOrderInstallItemProblemVoList3(
			BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo);

	/**
	 * 删除问题上报
	 * @param problemId
	 */
	void deleteProblem(Integer problemId);

	/**
	 * 查询该订单5分钟内提交问题上报的数量
	 * @param bizOrderInstallItemProblem
	 * @return
	 */
	Integer findProblemCount(BizOrderInstallItemProblem bizOrderInstallItemProblem);
	
}