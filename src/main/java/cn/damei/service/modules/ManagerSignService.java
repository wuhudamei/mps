/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizPmAttendCnfgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.ManagerSignDao;
import cn.damei.entity.modules.BizPmAttendDayOrder;
import cn.damei.entity.modules.ManagerSign;

/**
 * 项目经理签到查询Service
 * @author 梅浩
 * @version 2016-09-26
 */
@Service
@Transactional(readOnly = true)
public class ManagerSignService extends CrudService2<ManagerSignDao, ManagerSign> {
	@Autowired
	private ManagerSignDao managerSignDao;
	@Autowired
	private BizPmAttendCnfgDao bizPmAttendCnfgDao;
	@Transactional(readOnly = false)
	public ManagerSign get(Integer id) {
		ManagerSign managerSign = super.get(id);
		return managerSign;
	}
	@Transactional(readOnly = false)
	public List<ManagerSign> findList(ManagerSign managerSign) {
		return super.findList(managerSign);
	}
	
	public Page<ManagerSign> findPage(Page<ManagerSign> page, ManagerSign managerSign) {
		return super.findPage(page, managerSign);
	}
	public Page<BizPmAttendDayOrder> findPage1(Page<BizPmAttendDayOrder> page, BizPmAttendDayOrder bizPmAttendDayOrder) {
		bizPmAttendDayOrder.setPage(page);
		page.setList(dao.findList1(bizPmAttendDayOrder));
		return page;
	}
	@Transactional(readOnly = false)
	public void save(ManagerSign managerSign) {
		super.save(managerSign);
	}
	
	@Transactional(readOnly = false)
	public void delete(ManagerSign managerSign) {
		super.delete(managerSign);
	}
	@Transactional(readOnly = false)
	public void updateIsValiddById(String id,String isValid ,String signId) {
		//更新biz_pm_attend_day_order合格状态
		if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(isValid) ){
			dao.updateIsValiddById(id, isValid);
		}
		//更新biz_sign合格状态
		if(StringUtils.isNotBlank(signId) && StringUtils.isNotBlank(isValid) ){
			bizPmAttendCnfgDao.updateSignEnabled(signId,isValid);
		}
	}
	public String getMonthStatusByDayOrderId(String id) {
		return dao.getMonthStatusByDayOrderId(id);
	}

    public Boolean isApplyAttendMonth(String id) {
		return dao.isApplyAttendMonth(id);
    }
}