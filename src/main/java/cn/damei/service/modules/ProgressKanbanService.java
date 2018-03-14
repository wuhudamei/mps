package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.ProgressKanbanDao;
import cn.damei.entity.modules.ProgressKanban;


@Service
@Transactional(readOnly = true)
public class ProgressKanbanService extends CrudService2<ProgressKanbanDao, ProgressKanban>{

	@Autowired
	private ProgressKanbanDao progressKanbanDao;
	
	public ProgressKanban get(Integer id) {
		return super.get(id);
	}
	
	public List<ProgressKanban> findList(ProgressKanban progressKanban) {
		return super.findList(progressKanban);
	}
	
	public Page<ProgressKanban> findPage(Page<ProgressKanban> page, ProgressKanban progressKanban) {
		return super.findPage(page, progressKanban);
	}

	public List<ProgressKanban> findOrderByStoreId(Integer storeID) {
		return progressKanbanDao.findOrderByStoreId(storeID);
	}
	
}
