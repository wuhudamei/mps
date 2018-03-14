
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizBizEmployeegroupVoDao;
import cn.damei.entity.modules.BizEmployeegroupVO;
import cn.damei.entity.modules.OrderTaskpackage;


@Service

@Transactional(readOnly = true)
public class BizEmployeegroupVoService extends CrudService<BizBizEmployeegroupVoDao, BizEmployeegroupVO> {

	@Autowired
	private BizBizEmployeegroupVoDao bizBizEmployeegroupVoDao;
	
	public BizEmployeegroupVO get(String id) {
		return super.get(id);
	}

	public List<BizEmployeegroupVO> findList(BizEmployeegroupVO bizEmployeegroup) {
		return super.findList(bizEmployeegroup);
	}

	public Page<BizEmployeegroupVO> findPage(Page<BizEmployeegroupVO> page, BizEmployeegroupVO bizEmployeegroup) {
		page.setOrderBy(" storeName,elactricationName ASC");
		if (bizEmployeegroup.getWorkType() != null && bizEmployeegroup.getWorkType() == 0) {
			bizEmployeegroup.setWorkType(100);
		}
		return super.findPage(page, bizEmployeegroup);
	}

	@Transactional(readOnly = false)
	public void insertStarLog(double beforeScore, double afterScore,Integer empGroupId){
		bizBizEmployeegroupVoDao.insertStarLog(beforeScore, afterScore, empGroupId);
	}
	
	@Transactional(readOnly = false)
	public void save(BizEmployeegroupVO bizEmployeegroup) {
		super.save(bizEmployeegroup);
	}

	@Transactional(readOnly = false)
	public void delete(BizEmployeegroupVO bizEmployeegroup) {
		super.delete(bizEmployeegroup);
	}

	public Page<BizEmployeegroupVO> findFreeLeader(Page<BizEmployeegroupVO> page, BizEmployeegroupVO bizEmployeegroup) {
		bizEmployeegroup.setPage(page);

		List<BizEmployeegroupVO> findFreeLeader = dao.findFreeLeader(bizEmployeegroup);

		if (findFreeLeader.size() > 0) {


			List<OrderTaskpackage> packList = dao.findAllPackageWhomHasEmpGroups(findFreeLeader);


			List<Integer> wantedDeleteWorkerId = new ArrayList<>();

			for (OrderTaskpackage orderTaskpackage : packList) {

				if (null != orderTaskpackage.getPlanStartdate() && null != orderTaskpackage.getPlanEnddate() && null != bizEmployeegroup.getFreeBeginDate() && null != bizEmployeegroup.getFreeEndDate()) {






					if ((orderTaskpackage.getPlanStartdate().getTime() > bizEmployeegroup.getFreeEndDate().getTime()) || (orderTaskpackage.getPlanEnddate().getTime() < bizEmployeegroup.getFreeBeginDate().getTime())) {
					} else {

						wantedDeleteWorkerId.add(Integer.parseInt(orderTaskpackage.getEmpGroupid()));
					}
				}

			}


			Iterator<BizEmployeegroupVO> iterator = findFreeLeader.iterator();
			while (iterator.hasNext()) {
				BizEmployeegroupVO bizEmployeegroupVO2 = iterator.next();

				if (wantedDeleteWorkerId.contains(bizEmployeegroupVO2.getWorkerGroupId())) {
					iterator.remove();
				}
			}
		}
		page.setList(findFreeLeader);
		page.setCount(findFreeLeader.size());
		return page;

	}

	public List<BizEmployeegroupVO> queryemployeegroup(BizEmployeegroupVO bizEmployeegroupVO) {
		return dao.queryemployeegroup(bizEmployeegroupVO);
	}
}