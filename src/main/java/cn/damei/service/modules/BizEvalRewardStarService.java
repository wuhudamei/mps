/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizEvalRewardStar;
import cn.damei.dao.modules.BizEvalRewardStarDao;

/**
 * 评价奖励星级Service
 * @author qww
 * @version 2017-02-24
 */
@Service
@Transactional(readOnly = true)
public class BizEvalRewardStarService extends CrudService2<BizEvalRewardStarDao, BizEvalRewardStar> {

	public BizEvalRewardStar get(Integer id) {
		return super.get(id);
	}
	
	public List<BizEvalRewardStar> findList(BizEvalRewardStar bizEvalRewardStar) {
		return super.findList(bizEvalRewardStar);
	}
	
	public Page<BizEvalRewardStar> findPage(Page<BizEvalRewardStar> page, BizEvalRewardStar bizEvalRewardStar) {
		return super.findPage(page, bizEvalRewardStar);
	}
	
	@Transactional(readOnly = false)
	public void save(BizEvalRewardStar bizEvalRewardStar) {
		super.save(bizEvalRewardStar);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizEvalRewardStar bizEvalRewardStar) {
		super.delete(bizEvalRewardStar);
	}

	public Double queryEvalRewardStarByMap(Map<String, Object> map){
		return dao.queryEvalRewardStarByMap(map);
	}

	public List<BizEvalRewardStar> queryEvalRewardStarByEvalRewardCfgId(Integer evalRewardCfgId){
		return dao.queryEvalRewardStarByEvalRewardCfgId(evalRewardCfgId);
	}
}