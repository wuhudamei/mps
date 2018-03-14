
package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.GuestbookDao;
import cn.damei.entity.modules.Guestbook;


@Service
@Transactional(readOnly = true)
public class GuestbookService extends CrudService<GuestbookDao, Guestbook> {

	public Guestbook get(String id) {
		return dao.get(id);
	}
	
	public Page<Guestbook> findPage(Page<Guestbook> page, Guestbook guestbook) {










		guestbook.getSqlMap().put("dsf", dataScopeFilter(guestbook.getCurrentUser(), "o", "u"));
		
		guestbook.setPage(page);
		page.setList(dao.findList(guestbook));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void delete(Guestbook guestbook, Boolean isRe) {

		dao.delete(guestbook);
	}
	

	public void createIndex(){

	}
	


	public Page<Guestbook> search(Page<Guestbook> page, String q, String beginDate, String endDate){
		






























		
		return page;
	}
	
}
