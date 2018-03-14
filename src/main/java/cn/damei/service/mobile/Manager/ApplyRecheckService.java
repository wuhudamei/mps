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
	
	
	/**
	 * 根据项目经理id查询订单下的质检单的复检单
	 * @param managerId
	 * @return
	 */
	public List<ApplyRecheckEntity> findRecheckList(Integer managerId){
		
		return dao.findRecheckList(managerId);
	}
	

	/**
	 * 根据复检单id 查询不合格的检查项
	 * @param reCheckId
	 * @return
	 */
	public List<ApplyRecheckEntity> selectCheckItemByRecheckId(Integer reCheckId){
		
		return dao.selectCheckItemByRecheckId(reCheckId);
		
	}
	

	/**
	 * 保存经理申请复检时的照片
	 * @param pic
	 */
	@Transactional(readOnly=false)
	public void savePic(ReportCheckDetailsPic pic){
		
	dao.savePic(pic);	
		
	}
	/**
	 * 更新复检单  status  申请人,期望日期,等
	 *
	 * @param entity
	 */
	@Transactional(readOnly=false)
	public void updateRecheckStatus(ApplyRecheckEntity  entity){
		
		dao.updateRecheckStatus(entity);
	}
	
}
