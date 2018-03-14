/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 工人组管理Service
 * 
 * @author qhy
 * @version 2016-09-01
 */
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
		// 查找出工人组长
		List<BizEmployeegroupVO> findFreeLeader = dao.findFreeLeader(bizEmployeegroup);

		if (findFreeLeader.size() > 0) {

			// 查找出任务包
			List<OrderTaskpackage> packList = dao.findAllPackageWhomHasEmpGroups(findFreeLeader);

			// 需要删除的工人组集合
			List<Integer> wantedDeleteWorkerId = new ArrayList<>();
			// 遍历任务包 比较时间是否冲突
			for (OrderTaskpackage orderTaskpackage : packList) {

				if (null != orderTaskpackage.getPlanStartdate() && null != orderTaskpackage.getPlanEnddate() && null != bizEmployeegroup.getFreeBeginDate() && null != bizEmployeegroup.getFreeEndDate()) {

					// 判断时间是否冲突
					// 取集合的交集 为不可分配时间
					// 即 已分配的任务包 要么开始时间比 要分配的任务包的结束时间大,
					// 要么已分配的任务包的结束时间比要分配的任务包的开始时间

					if ((orderTaskpackage.getPlanStartdate().getTime() > bizEmployeegroup.getFreeEndDate().getTime()) || (orderTaskpackage.getPlanEnddate().getTime() < bizEmployeegroup.getFreeBeginDate().getTime())) {
					} else {
						// 如果冲突就删除
						wantedDeleteWorkerId.add(Integer.parseInt(orderTaskpackage.getEmpGroupid()));
					}
				}

			}

			// 迭代删除
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