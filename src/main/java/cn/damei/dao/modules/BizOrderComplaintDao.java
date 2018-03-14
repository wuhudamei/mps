/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderComplaint;
import cn.damei.entity.modules.ComplaintLogEntity;

/**
 * 订单投诉问题DAO接口
 * 
 * @author ztw
 * @version 2017-07-04
 */
@MyBatisDao
public interface BizOrderComplaintDao extends CrudDao<BizOrderComplaint> {

	List<BizOrderComplaint> findPageList(BizOrderComplaint bizOrderComplaint);

	List<Map<String, Object>> findEmployeeNameAndPhoneByIds(List<Integer> list);

	List<String> findItemNameByIds(List<Integer> list);

	List<ComplaintLogEntity> findLogByComplaintId(String complaintId);

	List<String> findComplaintLogPicByMap(Map<String, Object> map);

	List<BizOrderComplaint> findPageListall(BizOrderComplaint bizOrderComplaint);

	List<ComplaintLogEntity> findLogByComplaintIdz(String complaintId);


	List<BizOrderComplaint> findComplaintListForMap(BizOrderComplaint orderComplaint);

}