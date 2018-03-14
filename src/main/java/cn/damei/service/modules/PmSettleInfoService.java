package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.modules.PmSettleInfoDao;
import cn.damei.entity.modules.PmSettleInfo;

@Service
@Transactional(readOnly = true)
public class PmSettleInfoService {

	@Autowired
	private PmSettleInfoDao pmSettleInfoDao;
	
	public List<PmSettleInfo> queryPmSettleInfoByOrderNumber(String orderNum){
		return pmSettleInfoDao.queryPmSettleInfoByOrderNumber(orderNum);
	}
}
