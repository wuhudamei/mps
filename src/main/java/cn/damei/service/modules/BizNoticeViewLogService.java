
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizNoticeViewLog;
import cn.damei.dao.modules.BizNoticeViewLogDao;


@Service
@Transactional(readOnly = true)
public class BizNoticeViewLogService extends CrudService2<BizNoticeViewLogDao, BizNoticeViewLog> {

	public BizNoticeViewLog get(Integer id) {
		return super.get(id);
	}
	
	public List<BizNoticeViewLog> findList(BizNoticeViewLog bizNoticeViewLog) {
		return super.findList(bizNoticeViewLog);
	}
	
	public Page<BizNoticeViewLog> findPage(Page<BizNoticeViewLog> page, BizNoticeViewLog bizNoticeViewLog) {
		return super.findPage(page, bizNoticeViewLog);
	}
	
	@Transactional(readOnly = false)
	public void save(BizNoticeViewLog bizNoticeViewLog) {
		super.save(bizNoticeViewLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizNoticeViewLog bizNoticeViewLog) {
		super.delete(bizNoticeViewLog);
	}

	public List<BizNoticeViewLog> queryNoticeViewLogByNoticeId(Integer noticeId) {
		return dao.queryNoticeViewLogByNoticeId(noticeId);
	}

	public BizNoticeViewLog queryByNoticeIdAndEmployeeId(BizNoticeViewLog log) {
		return dao.queryByNoticeIdAndEmployeeId(log);
	}

	@Transactional(readOnly = false)
	public void insert(BizNoticeViewLog bizNoticeViewLog) {
		dao.insert(bizNoticeViewLog);
	}

	@Transactional(readOnly = false)
	public void update(BizNoticeViewLog bizNoticeViewLog) {
		dao.update(bizNoticeViewLog);
	}

	public String findNoticeSum(Integer id) {

		return dao.findNoticeSum(id);
	}

	public String findYesNoticeSum(Integer id) {

		return dao.findYesNoticeSum(id);
	}
}