
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMsg;
import cn.damei.entity.modules.BizProjectChangeBill;
import cn.damei.entity.modules.BizProjectChangeBillItem;
import cn.damei.entity.modules.User;


@MyBatisDao
public interface BizProjectChangeBillDao extends CrudDao<BizProjectChangeBill> {


	void updateStatus(BizProjectChangeBill bizProjectChangeBill);


	BizProjectChangeBill findDetails(Integer projectChangeId);


	List<BizProjectChangeBillItem> findAddItem(Integer projectChangeId);


	List<BizProjectChangeBillItem> findSubItem(Integer projectChangeId);


	void saveBizMsg(BizMsg bizMsg);

	public String findDescribeByRoleId(String roleId);

	List<BizProjectChangeBill> findAllStore(String string);

	User getCountByPhone(String phone);
	

	public BizProjectChangeBill findApplyDetails(Integer projectChangeId);

	List<BizProjectChangeBill> findList2(BizProjectChangeBill bizProjectChangeBill);

	List<BizProjectChangeBill> findDetail(BizProjectChangeBill bizProjectChangeBill);

	void updataIsDealed(BizProjectChangeBill bizProjectChangeBill);
}