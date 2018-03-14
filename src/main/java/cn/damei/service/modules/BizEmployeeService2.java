/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizEmployeeGuaranteeMoney;
import cn.damei.entity.modules.LaborCapital;
import cn.damei.web.modules.LaborCapitalController;
import cn.damei.entity.modules.BizStar;
import cn.damei.dao.modules.UserDao;
import cn.damei.entity.modules.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.dao.modules.BizEmployeeDao2;
import cn.damei.entity.modules.BizEmployee2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 员工信息Service
 * 
 * @author qhy
 * @version 2016-08-24
 */
@Service
@Transactional(readOnly = true)
public class BizEmployeeService2 extends
		CrudService2<BizEmployeeDao2, BizEmployee2> {

	@Autowired
	private BizEmployeeDao2 bizEmployeeDao2;
	@Autowired
	private BizEmployeeDao bizEmployeeDao;
	@Autowired
	private UserDao userDao;
	public BizEmployee2 findEmployeeNameById(Integer id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(BizEmployee2 bizEmployee2){
		super.delete(bizEmployee2);
		User user = new User();
		String userId = bizEmployeeDao2.findsysUserId(bizEmployee2.getId());
		user.setId(userId);
		userDao.empDelete(user);
		//如果工人组被删除了，也要还原出来
		//判断是否组长，如果是组长，还原，如果不是，不还原工人组
		List<String> leader = bizEmployeeDao2.findIsLeader(bizEmployee2.getId());
		if(leader != null && leader.size() > 0){
			bizEmployee2.setDelFlag("0");
			bizEmployeeDao2.updateGroupState(bizEmployee2);
		}
		
	}
	
	public BizEmployee2 findEmployeeById(Integer id) {
		
		return dao.get(id);
	}

	public BizEmployee2 get(Integer id) {
		return super.get(id);
	}

	/**
	 * @param list
	 */
	public List<BizEmployee2> getById(List<Integer> list) {
		return bizEmployeeDao2.getById(list);
	}


	public BizEmployee2 queryEmployeeByPhoneAndEmployeeType(String mobilePhone, String appType) {
		
		return dao.queryEmployeeByPhoneAndEmployeeType(mobilePhone,appType);
	}

	public Page<BizEmployee2> findWorkerGroupGuaranteeMoneyPage(Page<BizEmployee2> page, BizEmployee2 entity) {
		entity.setPage(page);
		page.setList(dao.findGuaranteeMoneyList(entity));
		return page;
	}

	public List<BizEmployeeGuaranteeMoney> queryGuaranteeMoneyList(Integer employeeId){
		return dao.queryGuaranteeMoneyList(employeeId);
	}

	public Double queryGuaranteeMoneyTotal(Integer employeeId){
		return dao.queryGuaranteeMoneyTotal(employeeId);
	}

	public Page<BizEmployee2> findManagerGuaranteeMoneyPage(Page<BizEmployee2> page, BizEmployee2 entity) {
		entity.setEmpType(Integer.parseInt(ConstantUtils.EMP_TYPE_3));
		entity.setPage(page);
		page.setList(dao.findManagerGuaranteeMoneyList(entity));
		return page;
	}

	public Page<BizEmployee2> findDeleteEmployeePage(Page<BizEmployee2> page, BizEmployee2 entity) {
		entity.setPage(page);
		page.setList(dao.findDeleteEmployeeList(entity));
		return page;
	}


	public List<Integer> findEngineIdsByEmpId(int empId) {
		return bizEmployeeDao.findEngineIdsByEmpId(empId);
	}


	public BizEmployee2 queryEmployeeByPhoneAndEmployeeTypeOrIsLeader(String mobilePhone, String appType) {
		return dao.queryEmployeeByPhoneAndEmployeeTypeOrIsLeader(mobilePhone,appType);
	}

	public List<BizStar> findListByBizStar(BizStar bizStar) {
		return bizEmployeeDao2.findListByBizStar(bizStar);
	}

    public void projectMode(@ModelAttribute LaborCapital laborCapital, Model model, User user, LaborCapitalController laborCapitalController) {
        // 过滤工程模式
        if (StringUtils.isBlank(laborCapital.getProjectMode())) {
            if (null != user.getEmpId()) {
                BizEmployee2 be = get(Integer.parseInt(user.getEmpId()));
                if (StringUtils.isBlank(be.getProjectMode())) {
                    model.addAttribute("gongcheng", true);
                } else {
                    if (be.getProjectMode().equals("3")) {
                        model.addAttribute("gongcheng", true);
                    } else {
                        laborCapital.setProjectMode(be.getProjectMode());
                    }
                }
            } else {
                model.addAttribute("gongcheng", true);
            }
        } else {
            if (null != user.getEmpId()) {
                BizEmployee2 be = get(Integer.parseInt(user.getEmpId()));
                if (StringUtils.isBlank(be.getProjectMode())) {
                    model.addAttribute("gongcheng", true);
                } else {
                    if (be.getProjectMode().equals("3")) {
                        model.addAttribute("gongcheng", true);
                    } else {
                        laborCapital.setProjectMode(be.getProjectMode());
                    }
                }
            } else {
                model.addAttribute("gongcheng", true);
            }
        }
    }
}