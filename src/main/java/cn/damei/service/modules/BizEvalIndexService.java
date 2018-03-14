package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizEvalIndex;
import cn.damei.dao.modules.BizEvalIndexDao;

/**
 * 评价指标设置Service
 * @author wyb
 * @version 2017-02-24
 */
@Service
@Transactional(readOnly = true)
public class BizEvalIndexService extends CrudService2<BizEvalIndexDao, BizEvalIndex> {

	public BizEvalIndex get(Integer id) {
		return super.get(id);
	}
	
	public List<BizEvalIndex> findList(BizEvalIndex bizEvalIndex) {
		return super.findList(bizEvalIndex);
	}
	
	public Page<BizEvalIndex> findPage(Page<BizEvalIndex> page, BizEvalIndex bizEvalIndex) {
		return super.findPage(page, bizEvalIndex);
	}
	
	@Transactional(readOnly = false)
	public void save(BizEvalIndex bizEvalIndex) {
		super.save(bizEvalIndex);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizEvalIndex bizEvalIndex) {
		super.delete(bizEvalIndex);
	}
}