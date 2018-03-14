
package cn.damei.service.modules;

import java.util.*;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.modules.BizEvalRewardStarDao;
import cn.damei.entity.modules.BizEvalRewardStar;
import cn.damei.dao.modules.BizEvalRewardTaskpackTempDao;
import cn.damei.entity.modules.BizEvalRewardTaskpackTemp;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizEvalRewardCfg;
import cn.damei.dao.modules.BizEvalRewardCfgDao;


@Service
@Transactional(readOnly = true)
public class BizEvalRewardCfgService extends CrudService2<BizEvalRewardCfgDao, BizEvalRewardCfg> {

	@Autowired
	private BizEvalRewardTaskpackTempDao bizEvalRewardTaskpackTempDao;

	@Autowired
	private BizEvalRewardStarDao bizEvalRewardStarDao;

	public BizEvalRewardCfg get(Integer id) {
		return super.get(id);
	}
	
	public List<BizEvalRewardCfg> findList(BizEvalRewardCfg bizEvalRewardCfg) {
		return super.findList(bizEvalRewardCfg);
	}
	
	public Page<BizEvalRewardCfg> findPage(Page<BizEvalRewardCfg> page, BizEvalRewardCfg bizEvalRewardCfg) {
		return super.findPage(page, bizEvalRewardCfg);
	}
	
	@Transactional(readOnly = false)
	public void save(BizEvalRewardCfg bizEvalRewardCfg) {
		super.save(bizEvalRewardCfg);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizEvalRewardCfg bizEvalRewardCfg) {
		super.delete(bizEvalRewardCfg);
	}

	@Transactional(readOnly = false)
	public void update(BizEvalRewardCfg bizEvalRewardCfg) {
		dao.update(bizEvalRewardCfg);
	}

	@Transactional(readOnly = false)
	public void add(BizEvalRewardCfg bizEvalRewardCfg,Integer[] taskpackTempId) {
		User user = UserUtils.getUser();
		Date date = new Date();

		bizEvalRewardCfg.setIsEnabled(ConstantUtils.IS_ENABLED_1);
		bizEvalRewardCfg.setCreateDate(date);
		bizEvalRewardCfg.setCreateBy(user);
		bizEvalRewardCfg.setUpdateDate(date);
		bizEvalRewardCfg.setUpdateBy(user);
		dao.insert(bizEvalRewardCfg);


		if(taskpackTempId != null){
			for(Integer id:taskpackTempId){
				BizEvalRewardTaskpackTemp temp = new BizEvalRewardTaskpackTemp();
				temp.setEvalRewardCfgId(bizEvalRewardCfg.getId());
				temp.setTaskpackTempId(id);
				temp.setCreateBy(user);
				temp.setCreateDate(date);
				temp.setUpdateDate(date);
				temp.setUpdateBy(user);
				bizEvalRewardTaskpackTempDao.insert(temp);
			}
		}


		if(CollectionUtils.isNotEmpty(bizEvalRewardCfg.getList())){
			for(BizEvalRewardStar star:bizEvalRewardCfg.getList()){
				if(star != null && star.getStarLevel() != null) {
					star.setEvalRewardCfgId(bizEvalRewardCfg.getId());
					star.setCreateDate(date);
					star.setCreateBy(user);
					star.setUpdateDate(date);
					star.setUpdateBy(user);
					bizEvalRewardStarDao.insert(star);
				}
			}
		}
	}

	public Integer queryCountByCondition(BizEvalRewardCfg bizEvalRewardCfg, List<Integer> list){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("isEnabled", ConstantUtils.IS_ENABLED_1);
		map.put("storeId", bizEvalRewardCfg.getStoreId());
		map.put("projectMode", bizEvalRewardCfg.getProjectMode());
		map.put("rewardTargetType", bizEvalRewardCfg.getRewardTargetType());
		map.put("rewardEndDatetime", bizEvalRewardCfg.getRewardEndDatetime());
		map.put("rewardStartDatetime", bizEvalRewardCfg.getRewardStartDatetime());
		map.put("list", list);
		return dao.queryCountByCondition(map);
	}

	public Integer queryIdByMap(Map<String, Object> map){
		return dao.queryIdByMap(map);
	}
}