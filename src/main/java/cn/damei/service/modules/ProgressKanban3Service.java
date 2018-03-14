package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.ProgressKanban3Dao;
import cn.damei.entity.modules.ProgressKanban3;


@Service
@Transactional(readOnly = true)
public class ProgressKanban3Service extends CrudService2<ProgressKanban3Dao, ProgressKanban3>{

	@Autowired
	private ProgressKanban3Dao progressKanban3Dao;
	
	public ProgressKanban3 get(Integer id) {
		return super.get(id);
	}
	
	public List<ProgressKanban3> findList(ProgressKanban3 progressKanban) {
		return super.findList(progressKanban);
	}
	
	public Page<ProgressKanban3> findPage(Page<ProgressKanban3> page, ProgressKanban3 progressKanban) {
		return super.findPage(page, progressKanban);
	}

	public List<ProgressKanban3> findOrderByStoreId(ProgressKanban3 progressKanban) {
		return progressKanban3Dao.findOrderByStoreId(progressKanban);
	}
	
}
