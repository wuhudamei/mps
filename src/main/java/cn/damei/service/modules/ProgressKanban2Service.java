package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.ProgressKanban2Dao;
import cn.damei.entity.modules.ProgressKanban2;

/**
 * 进度看板
 * @author llp
 * 2016/10/18
 */
@Service
@Transactional(readOnly = true)
public class ProgressKanban2Service extends CrudService2<ProgressKanban2Dao, ProgressKanban2>{

	@Autowired
	private ProgressKanban2Dao progressKanban2Dao;
	
	public ProgressKanban2 get(Integer id) {
		return super.get(id);
	}
	
	public List<ProgressKanban2> findList(ProgressKanban2 progressKanban) {
		return super.findList(progressKanban);
	}
	
	public Page<ProgressKanban2> findPage(Page<ProgressKanban2> page, ProgressKanban2 progressKanban) {
		return super.findPage(page, progressKanban);
	}

	public List<ProgressKanban2> findOrderByStoreId(ProgressKanban2 progressKanban) {
		return progressKanban2Dao.findOrderByStoreId(progressKanban);
	}
	
}
