package cn.damei.entity.modules;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizConfirmStartDao;
import cn.damei.entity.modules.BizConfirmStartOrder;

/**
 * 确认开工功能
 * @author llp
 *
 */
@Service
@Transactional(readOnly = true)
public class BizConfirmStartService extends CrudService2<BizConfirmStartDao,BizConfirmStartOrder>{

	public BizConfirmStartOrder get(Integer id) {
		return super.get(id);
	}
	
	public List<BizConfirmStartOrder> findList(BizConfirmStartOrder bizConfirmStartOrder) {
		return super.findList(bizConfirmStartOrder);
	}
}
