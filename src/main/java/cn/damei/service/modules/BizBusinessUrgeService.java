
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.modules.BizBusinessUrge;
import cn.damei.entity.modules.InstallBusinessUrge;
import cn.damei.entity.modules.WallAndFloorBusinessUrge;
import cn.damei.dao.modules.BizBusinessUrgeDao;


@Service
@Transactional(readOnly = true)
public class BizBusinessUrgeService extends CrudService2<BizBusinessUrgeDao, BizBusinessUrge> {

	public BizBusinessUrge get(Integer id) {
		return super.get(id);
	}
	
	public List<BizBusinessUrge> findList(BizBusinessUrge bizBusinessUrge) {
		return super.findList(bizBusinessUrge);
	}
	
	public Page<BizBusinessUrge> findPage(Page<BizBusinessUrge> page, BizBusinessUrge bizBusinessUrge) {
		return super.findPage(page, bizBusinessUrge);
	}
	
	@Transactional(readOnly = false)
	public void save(BizBusinessUrge bizBusinessUrge) {
		super.save(bizBusinessUrge);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizBusinessUrge bizBusinessUrge) {
		super.delete(bizBusinessUrge);
	}

	

	@Transactional(readOnly = false)
	public Integer saveBusinessUrge(Integer managerId, String operateContent, Integer id, String businessUrgeBusinessType1,
			String businessUrgeOperateType1, String businessUrgeOperatorType1) {
		
		BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();

		bizBusinessUrge.setBusinessOnlyMarkInt(id);

		bizBusinessUrge.setBusinesType(businessUrgeBusinessType1);

		bizBusinessUrge.setOperateType(businessUrgeOperateType1);

		bizBusinessUrge.setOperateContent(operateContent);

		bizBusinessUrge.setOperatorEmployeeId(managerId);

		bizBusinessUrge.setOperatorType(businessUrgeOperatorType1);

		bizBusinessUrge.setOperateDatetime(new Date());
		
		bizBusinessUrge.preInsert();
		
		dao.insert(bizBusinessUrge);
		
		return bizBusinessUrge.getId();
	}


	public Integer findCount(Manager manager, Integer id, String businessUrgeBusinessType1, String businessUrgeOperateType1, String businessUrgeOperatorType1) {
		
		BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();

		bizBusinessUrge.setBusinessOnlyMarkInt(id);

		bizBusinessUrge.setBusinesType(businessUrgeBusinessType1);

		bizBusinessUrge.setOperateType(businessUrgeOperateType1);

		bizBusinessUrge.setOperatorEmployeeId(manager.getId());

		bizBusinessUrge.setOperatorType(businessUrgeOperatorType1);
		
		return dao.findCount(bizBusinessUrge);
	}


	public Integer findCountByfiveTime(Integer id, Integer managerId, String businessUrgeBusinessType1,
			String businessUrgeOperateType2, String businessUrgeOperatorType1) {
		
		BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();

		bizBusinessUrge.setBusinessOnlyMarkInt(id);

		bizBusinessUrge.setBusinesType(businessUrgeBusinessType1);

		bizBusinessUrge.setOperateType(businessUrgeOperateType2);

		bizBusinessUrge.setOperatorEmployeeId(managerId);

		bizBusinessUrge.setOperatorType(businessUrgeOperatorType1);
		
		return dao.findCountByfiveTime(bizBusinessUrge);
	}


	public Page<InstallBusinessUrge> findInstallSelectPage(Page<InstallBusinessUrge> page,
			InstallBusinessUrge installBusinessUrge) {
		
		installBusinessUrge.setPage(page);
		page.setList(dao.findInstallSelectList(installBusinessUrge));
		return page;
	}
	

	public Page<WallAndFloorBusinessUrge> findWallAndFloorSelectPage(Page<WallAndFloorBusinessUrge> page, WallAndFloorBusinessUrge wallAndFloorBusinessUrge) {
		
		wallAndFloorBusinessUrge.setPage(page);
		page.setList(dao.findWallAndFloorSelectList(wallAndFloorBusinessUrge));
		return page;
		
	}


    public List<BizBusinessUrge> findUnqualifiedAcceptLog(Map<String, String> map) {
        return dao.findUnqualifiedAcceptLog(map);
    }
}