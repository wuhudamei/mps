package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderInstallAcceptanceDetailDao;
import cn.damei.entity.modules.BizOrderInstallAcceptanceDetail;

/**
 * 订单交底
 * @author llp
 */
@Service
@Transactional(readOnly = true)
public class BizOrderInstallAcceptanceDetailService extends CrudService2<BizOrderInstallAcceptanceDetailDao, BizOrderInstallAcceptanceDetail>{
	
	@Autowired
	private BizOrderInstallAcceptanceDetailDao bizOrderInstallDetailDao;
	
	public BizOrderInstallAcceptanceDetail get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderInstallAcceptanceDetail> findList(BizOrderInstallAcceptanceDetail bizOrderInstallDetail) {
		return super.findList(bizOrderInstallDetail);
	}
	
}
