/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.entity.modules.BizEmployeeGuaranteeMoney;
import cn.damei.entity.modules.BizNotice;
import cn.damei.entity.modules.BizStar;

/**
 * 员工信息DAO接口
 * @author qhy
 * @version 2016-08-24
 */
@MyBatisDao
public interface BizEmployeeDao2 extends CrudDao2<BizEmployee2> {

	public List<BizEmployee2> getById(List<Integer> list);

	public BizEmployee2 queryEmployeeByPhoneAndEmployeeType(String mobilePhone, String appType);

	public List<BizEmployee2> findGuaranteeMoneyList(BizEmployee2 employee2);

	public List<BizEmployeeGuaranteeMoney> queryGuaranteeMoneyList(Integer employeeId);

	public Double queryGuaranteeMoneyTotal(Integer employeeId);
	public List<BizEmployee2> queryEmployeeByEmpType(Integer empType);

	public List<BizEmployee2> queryEmployeeByEmpType2(BizNotice receivertemp);

	public List<BizEmployee2> findManagerGuaranteeMoneyList(BizEmployee2 employee2);

	public List<BizEmployee2> findDeleteEmployeeList(BizEmployee2 employee2);

	public BizEmployee2 queryEmployeeByPhoneAndEmployeeTypeOrIsLeader(String mobilePhone, String appType);
	/**
	 * 查询员工对应的用户ID
	 * @param id
	 * @return
	 */
	public String findsysUserId(Integer id);

	public List<BizStar> findListByBizStar(BizStar bizStar);

	public List<String> findIsLeader(Integer id);

	public void updateGroupState(BizEmployee2 bizEmployee2);
}