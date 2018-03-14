/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMsg;
import cn.damei.entity.modules.BizProjectChangeBill;
import cn.damei.entity.modules.BizProjectChangeBillItem;
import cn.damei.entity.modules.User;

/**
 * 设计师审核DAO接口
 * @author wyb
 * @version 2016-11-16
 */
@MyBatisDao
public interface BizProjectChangeBillDao extends CrudDao<BizProjectChangeBill> {

	/**
	 * 变更单审核
	 * @param bizProjectChangeBill
	 */
	void updateStatus(BizProjectChangeBill bizProjectChangeBill);

	/**
	 * 查看变更单详情
	 * @param projectChangeId
	 * @return
	 */
	BizProjectChangeBill findDetails(Integer projectChangeId);

	/**
	 * 增项清单
	 * @param projectChangeId
	 * @return
	 */
	List<BizProjectChangeBillItem> findAddItem(Integer projectChangeId);

	/**
	 * 减项清单
	 * @param projectChangeId
	 * @return
	 */
	List<BizProjectChangeBillItem> findSubItem(Integer projectChangeId);

	/**
	 * 消息推送
	 * @param bizMsg
	 */
	void saveBizMsg(BizMsg bizMsg);

	public String findDescribeByRoleId(String roleId);

	List<BizProjectChangeBill> findAllStore(String string);

	User getCountByPhone(String phone);
	
	/**
	 * 申请单详情
	 * @return
	 */
	public BizProjectChangeBill findApplyDetails(Integer projectChangeId);
	/**
	 * 查询
	 * @param bizProjectChangeBill
	 * @return
	 */
	List<BizProjectChangeBill> findList2(BizProjectChangeBill bizProjectChangeBill);

	List<BizProjectChangeBill> findDetail(BizProjectChangeBill bizProjectChangeBill);

	void updataIsDealed(BizProjectChangeBill bizProjectChangeBill);
}