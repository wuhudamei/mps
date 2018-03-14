/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizEvalRewardTaskpackTemp;
import cn.damei.dao.modules.BizEvalRewardTaskpackTempDao;

/**
 * 评价奖励任务包模板Service
 * @author qww
 * @version 2017-02-24
 */
@Service
@Transactional(readOnly = true)
public class BizEvalRewardTaskpackTempService extends CrudService2<BizEvalRewardTaskpackTempDao, BizEvalRewardTaskpackTemp> {

	public BizEvalRewardTaskpackTemp get(Integer id) {
		return super.get(id);
	}
	
	public List<BizEvalRewardTaskpackTemp> findList(BizEvalRewardTaskpackTemp bizEvalRewardTaskpackTemp) {
		return super.findList(bizEvalRewardTaskpackTemp);
	}
	
	public Page<BizEvalRewardTaskpackTemp> findPage(Page<BizEvalRewardTaskpackTemp> page, BizEvalRewardTaskpackTemp bizEvalRewardTaskpackTemp) {
		return super.findPage(page, bizEvalRewardTaskpackTemp);
	}
	
	@Transactional(readOnly = false)
	public void save(BizEvalRewardTaskpackTemp bizEvalRewardTaskpackTemp) {
		super.save(bizEvalRewardTaskpackTemp);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizEvalRewardTaskpackTemp bizEvalRewardTaskpackTemp) {
		super.delete(bizEvalRewardTaskpackTemp);
	}

	public List<Integer> queryTaskpackTempIdByEvalRewardCfgId(Integer evalRewardCfgId){
		return dao.queryTaskpackTempIdByEvalRewardCfgId(evalRewardCfgId);
	}

	public List<String> queryEvalRewardTaskpackTempByRewardCfgId(Integer evalRewardCfgId){
		return  dao.queryEvalRewardTaskpackTempByRewardCfgId(evalRewardCfgId);
	}
}