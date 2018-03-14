package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizWorkerScore;
/**
 * 分数排名DAO接口
 * @author ws
 * @version 2017-09-14
 */
@MyBatisDao
public interface BizWorkerScoreDao extends CrudDao<BizWorkerScore> {

	List<BizWorkerScore> findSort(BizWorkerScore bizWorkerScore);
	List<BizWorkerScore> queryBizWorkerScoreDetail(BizWorkerScore bizWorkerScore2);
}