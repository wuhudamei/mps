/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import cn.damei.entity.modules.DropModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizNoticeReceiver;
import cn.damei.dao.modules.BizNoticeReceiverDao;

/**
 * 消息公告接收人Service
 * @author qww
 * @version 2017-01-14
 */
@Service
@Transactional(readOnly = true)
public class BizNoticeReceiverService extends CrudService2<BizNoticeReceiverDao, BizNoticeReceiver> {

	public BizNoticeReceiver get(Integer id) {
		return super.get(id);
	}
	
	public List<BizNoticeReceiver> findList(BizNoticeReceiver bizNoticeReceiver) {
		return super.findList(bizNoticeReceiver);
	}
	
	public Page<BizNoticeReceiver> findPage(Page<BizNoticeReceiver> page, BizNoticeReceiver bizNoticeReceiver) {
		return super.findPage(page, bizNoticeReceiver);
	}
	
	@Transactional(readOnly = false)
	public void save(BizNoticeReceiver bizNoticeReceiver) {
		super.save(bizNoticeReceiver);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizNoticeReceiver bizNoticeReceiver) {
		super.delete(bizNoticeReceiver);
	}

	public List<DropModel> queryNoticeReceiverEmployee(Integer noticeId){
		return dao.queryNoticeReceiverEmployee(noticeId);
	}

	public List<String> queryNoticeReceiverByNoticeId(Integer noticeId){
		return dao.queryNoticeReceiverByNoticeId(noticeId);
	}
}