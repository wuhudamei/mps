package cn.damei.entity.modules;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderConstructionDao;
import cn.damei.entity.modules.BizOrderConstruction;

/**
 * 大统计表-施工中
 * @author llp
 * @version 2016-11-28
 */
@Service
@Transactional(readOnly = true)
public class BizOrderConstructionService extends CrudService2<BizOrderConstructionDao, BizOrderConstruction>{

	public BizOrderConstruction get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderConstruction> findList(BizOrderConstruction bizOrderConstruction) {
		return super.findList(bizOrderConstruction);
	}
}
