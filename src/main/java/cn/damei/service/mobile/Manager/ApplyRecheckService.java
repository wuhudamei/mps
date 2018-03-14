package cn.damei.service.mobile.Manager;

import java.util.List;

import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.mobile.Manager.ApplyRecheckDao;
import cn.damei.entity.mobile.Manager.ApplyRecheckEntity;

@Service
@Transactional(readOnly=true)
public class ApplyRecheckService {

	
	@Autowired
	private ApplyRecheckDao dao;
	
	

	public List<ApplyRecheckEntity> findRecheckList(Integer managerId){
		
		return dao.findRecheckList(managerId);
	}
	


	public List<ApplyRecheckEntity> selectCheckItemByRecheckId(Integer reCheckId){
		
		return dao.selectCheckItemByRecheckId(reCheckId);
		
	}
	


	@Transactional(readOnly=false)
	public void savePic(ReportCheckDetailsPic pic){
		
	dao.savePic(pic);	
		
	}

	@Transactional(readOnly=false)
	public void updateRecheckStatus(ApplyRecheckEntity  entity){
		
		dao.updateRecheckStatus(entity);
	}
	
}
